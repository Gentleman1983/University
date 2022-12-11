#!/bin/bash
#PBS -q batch
#PBS -l nodes=1
#PBS -N render_job

# create an "interr(upt)"-file to signal the job-dispatcher script
# that this job needs to be restarted
trap_handler() {
	touch $PICTURE.interr
	exit 1
}

# change to working dir
cd $PBS_O_WORKDIR

# get id (index in array) of job
ID=$PBS_ARRAYID

# calculate needed parameters:
# (	since all array-jobs must be started identically	)
# (	this must be done inside the job-script			)
let "START_ROW= $ID * (ROW_OFFSET + 1) + 1"
if [ $ID -lt $ROW_REMAINDER ]; then
	let "START_ROW += $ID"
	let "END_ROW = $START_ROW + $ROW_OFFSET + 1"
else
	let "START_ROW += $ROW_REMAINDER"
	let "END_ROW = $START_ROW + $ROW_OFFSET + 1"
fi

# set output path
PICTURE=pic_${POSTFIX}_`printf "%0${#PART_CNT}d" $ID`.tga

# set include path for povray
# this is needed for the pov-file to find additional ressources
POV_FILE_PATH="${POV_FILE%/*}/"

# set traph handler. In case of a signal (TERM or INT) the jobs
# needs to be restarted
trap trap_handler INT TERM
POVRAY_OUT=`/usr/bin/povray $POV_FILE +L$POV_FILE_PATH +FT -D +H$HEIGHT +W$WIDTH +SR$START_ROW +ER$END_ROW +O$PICTURE 2>&1`

if [ $? -ne 0 ]; then
	echo "There was an error with povray" > $POSTFIX.err
	exit 1
fi

# povray may be interrupted by a user signal
if [ `echo "$POVRAY_OUT" | egrep "SIGTERM|SIGINT" | wc -l` -ne 0 ]; then
	trap_handler
fi

# pov-File may be invalid
if [ `echo "$POVRAY_OUT" | grep 'Parse Error:' | wc -l` -ne 0 ]; then
	echo "Parse Error" > $POSTFIX.err 2>/dev/null
	exit 1
fi

touch $PICTURE.finish
exit 0
