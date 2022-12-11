 #
 # check if little - or big - endian byteorder is used
 #
 .data
 checker:	.half	0x0201
 little_msg:	.asciiz	"little-endian\n"
 big_msg:	.asciiz	"big-endian\n"

.text
main:	lb	$t0	checker		# first stored byte
	lb	$t1,	checker+1	# next stored byte
	sub	$t2,	$t0,	$t1	# $t2 < 0 if $t1 > $t0
	bltz	$t2,	little		# branch if $t2 < 0 , i . e . $t1 =02 , $t0 =01
big:	li	$v0,	4		# print big - endian
	la	$a0,	big_msg
	syscall
	j	finish
little:	li	$v0,	4		# print little - endian
	la	$a0,	little_msg
	syscall
finish:	li	$v0,	10		# exit program
	syscall
