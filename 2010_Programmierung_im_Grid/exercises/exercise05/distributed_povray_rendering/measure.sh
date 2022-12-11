#!/bin/bash
XRES=$1
YRES=$2
FILE=$3
PARTS=${@:4}

echo "Rendering locally"
SINGLE_TIME=`(time -p /usr/bin/povray $FILE +FT -D +H$YRES +W$XRES +O$I.something.tga 2>/dev/null) 2>&1 | grep 'real' | tail -qc+6`

I=0
for P_CNT in ${PARTS[@]}; do
	let "Y_CNT=$YRES/$P_CNT"
	echo "Rendering to $P_CNT Parts (~$Y_CNT)"
	DISTRIBUTED_TIME[I]=`(time -p ./init_povray.sh $FILE $XRES $YRES $P_CNT) 2>&1 | grep 'real' | tail -qc+6`
	QUOTIENT[I]=`echo "scale=2; $SINGLE_TIME / ${DISTRIBUTED_TIME[I]}" | bc`
	let "I += 1"
done

echo -n "  SINGLE"
printf "%8s" ${PARTS[@]}
echo ""
printf "%8s" $SINGLE_TIME ${DISTRIBUTED_TIME[@]}
echo ""
echo -n "        "
printf "%8s" ${QUOTIENT[@]}
echo ""
