#!/bin/bash
#PBS -q batch
#PBS -l nodes=1
#PBS -N render_job

trap_handler() {
	touch $PICTURE.interr
	exit 1
}

# change to working dir
cd $PBS_O_WORKDIR

trap trap_handler INT TERM

POVRAY_OUT=`/usr/bin/povray $POV_FILE +FT -D +H$HEIGHT +W$WIDTH +SR$START_ROW +ER$END_ROW +O$PICTURE 2>&1`
echo "POVRAY finished with $?"
if [ $? -ne 0 ]; then
	echo "There was an error with povray" > $POSTFIX.err
	exit 1
fi

# povray may be interrupted by a user signal
if [ `echo "$POVRAY_OUT" | grep 'SIGTERM' | wc -l` -ne 0 ]; then
	trap_handler
fi


# pov-File may be invalid
if [ `echo "$POVRAY_OUT" | grep 'Parse Error:' | wc -l` -ne 0 ]; then
	echo "Parse Error" > $POSTFIX.err 2>/dev/null
	exit 1
fi

echo $POVRAY_OUT

exit 0
