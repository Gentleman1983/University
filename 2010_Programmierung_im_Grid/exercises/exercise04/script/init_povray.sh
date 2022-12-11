#!/bin/bash

cleanup_files(){
	echo "Removing tmp files"
	#rm -f ${PICTURES[@]}
	#rm -f render_job.*
	rm -rf $INIT_DIR/$WORKING_DIR
}

trap_handler(){
	echo ""
	echo "Unexspected signal caught. Terminating gracefully"
	qdel ${JOBIDS[@]} 2>/dev/null
	TIMEOUT=5
	echo "Waiting for jobs to exit (${TIMEOUT}s timeout)"
	while [ `qstat -a ${JOBIDS[@]} 2>/dev/null | tail -q -n+3 | wc -l` -gt 0 ]; do
		if [ $TIMEOUT -eq 0 ]; then
			break
		fi
		sleep 1
		let "TIMEOUT -= 1"
	done
	cleanup_files
	cd $INIT_PWD
	exit 1
}

# check parameter count
if [[ $# -lt 3  &&  $# -gt 4 ]]; then
	echo "Usage: $0 <POV-FILE> <WIDTH> <HEIGHT> [PART_CNT]"
	exit 1
fi

POV_FILE=$1
WIDTH=$2
HEIGHT=$3

# validate parameters
if [ ! -e $POV_FILE ]; then
	echo "The file $POV_FILE does not exist! Aborting!"
	exit 1
fi
if [ $WIDTH -gt 0 2>/dev/null ]; then 
	:
else
	echo "<WIDTH> $WIDTH is not a positiv integer! Aborting!"
	exit 1
fi
if [ $HEIGHT -gt 0 2>/dev/null ]; then
	:
else
	echo "<HEIGHT> $HEIGHT is not a positiv integer! Aborting!"
	exit 1
fi
if [ -n "$4" ]; then
	if [ $4 -gt 0 2>/dev/null ]; then
		:
	else
		echo "<PART_CNT> $4 is not a positiv integer! Aborting!"
		exit 1
	fi
fi

INIT_DIR=$(pwd)


FREE_NODE_CNT=`pbsnodes -l free | wc -l`
UP_NODE_CNT=`pbsnodes -l up | wc -l`

# find an not existing directory name
POSTFIX=`date +%s%N`
WORKING_DIR=render_job_$POSTFIX
while [ -d $WORKING_DIR  ]; do
	POSTFIX=`date +%s%N`
	WORKING_DIR=render_job_$POSTFIX
done

mkdir $WORKING_DIR

# changing to TMP- working directory. 
cd $WORKING_DIR
if [ $? -ne 0 ]; then 
	echo "Error changing to $WORKING_DIR"
	rm -rf $WORKING_DIR
	exit 1
fi

# set no of parts the picture will be divided into
# if cnt was given as parameter use given number
if [ ! -n "$4" ]; then
	let "PART_CNT = FREE_NODE_CNT"
	if [ $PART_CNT -lt 1 ]; then
		PART_CNT=1
	fi
else
	PART_CNT=$4
fi

# init an array listing indices of open parts
let "ROW_OFFSET = $HEIGHT / $PART_CNT - 1"
let "ROW_REMAINDER = $HEIGHT % $PART_CNT"

if [ $ROW_OFFSET -eq -1 ]; then
	echo "No. of free nodes is greater than possible parts"
	PART_CNT=$ROW_REMAINDER
	ROW_OFFSET=0
	ROW_REMAINDER=0
fi

# set trap handler to handle CTRL-C ...
trap trap_handler INT TERM

# init two arrays containing the start-row and end-row for each part
START_ROW=1
let "END_ROW = $START_ROW + $ROW_OFFSET"
I=0
while [ $I -lt $PART_CNT ]; do
	if [ $I -lt $ROW_REMAINDER ]; then
		let "END_ROW += 1"
	fi
	START_ROWS[I]=$START_ROW
	END_ROWS[I]=$END_ROW
	OPEN_PARTS[I]=$I
	let "START_ROW = $END_ROW + 1"
	let "END_ROW = $START_ROW + $ROW_OFFSET"
	let "I += 1"
done

# distribute rendering process to PART_CNT jobs
I=0
while [ $I -lt $PART_CNT ]; do
	PICTURES[I]=pic_${POSTFIX}_`printf "%0${#PART_CNT}d" $I`.tga
	JOBIDS[I]=`qsub -v "POSTFIX=$POSTFIX","POV_FILE=$POV_FILE","WIDTH=$WIDTH","HEIGHT=$HEIGHT","START_ROW=${START_ROWS[I]}","END_ROW=${END_ROWS[I]}","PICTURE=${PICTURES[I]}" $INIT_DIR/povray_job.sh`
	echo "Rows ${START_ROWS[I]}:${END_ROWS[I]} rendered to ${PICTURES[I]} by ${JOBIDS[I]}"
	let "I += 1"
done

# print status bar
echo -n "["
# store cursor position
tput sc
printf ".%.0s" ${OPEN_PARTS[@]}
echo -n "]"

while [ ${#OPEN_PARTS[@]} -gt 0 ]; do
	# poll every 2 seconds
	sleep 2
	
	# reset bar
	tput rc
	
	if [ -e $POSTFIX.err ]; then
		# if an .err file exists a critical error happened
		# the .err file contains appropriate error messages
		echo ""
		cat $POSTFIX.err
		cleanup_files
		exit 1
	fi
	QUEUED_JOBS=`qstat -f ${JOBIDS[@]} 2>/dev/null | grep 'Job Id:'`
	LAST_I=0
	for I in ${OPEN_PARTS[@]}; do
		# skip finished parts in progress bar
		let "INDEX_DIFF = $I - $LAST_I - 1"
		if [ $INDEX_DIFF -gt 0 ]; then
			echo -n -e "\033[${INDEX_DIFF}C"
		fi
		LAST_I=$I		

		
		PICTURE=${PICTURES[I]}
		if [ `echo $QUEUED_JOBS | grep ${JOBIDS[I]} | wc -l` -eq 0 ]; then
			if [ -e $PICTURE.interr ]; then
				# if an .interr file exist a job was interrupted
				# lets restart it
				JOBIDS[I]=`qsub -v "POSTFIX=$POSTFIX","POV_FILE=$POV_FILE","WIDTH=$WIDTH","HEIGHT=$HEIGHT","START_ROW=${START_ROWS[I]}","END_ROW=${END_ROWS[I]}","PICTURE=${PICTURES[I]}" $INIT_DIR/povray_job.sh`
				rm -f $PICTURE.interr
				echo -n "!"
			else
				# if an open part was rendered remove it from the list
				unset OPEN_PARTS[I]

				# this line is not needed but reduces the number of JOBIDS submitted to qstat
				unset JOBIDS[I] 
				echo -n "*"
			fi
		else
			# do not change line
			echo -n -e "\033[C"
		fi
	done
done

# all jobs have finished.. so add all missing *'s
tput rc
printf "*%.0s" `seq 1 ${PART_CNT}`

# newline so we do not write into progress-bar
echo ""

# concat files
echo "Gluing Picture ^^"
mv ${PICTURES[0]} out_$POSTFIX.tga
if [ ${#PICTURES[@]} -gt 1 ]; then
	tail -qc+19 ${PICTURES[@]:1} >> out_$POSTFIX.tga
fi

mv out_$POSTFIX.tga $INIT_DIR/
cd $INIT_PWD

cleanup_files

echo "The picture was rendered to out_$POSTFIX.tga"

exit 0
