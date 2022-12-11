.data
i:	.word	0
k:	.word	0
msg:	.asciiz	"i = "
newline:.asciiz	"\n"

.text
main:		li	$v0,	4		# print_string msg
		la	$a0,	msg
		syscall
		li	$v0,	5		# read_int in $a0
		syscall
		sw	$v0,	i		# store in i
# ------------------------------------------------
		add	$t2,	$zero,	$zero	# $t2 = 0 ( init k )
switch: 	lw	$t0,	i		# $t0 = i
		addi	$t1,	$zero,	1	# $t1 = 1
		beq	$t0,	$t1,	case1
		addi	$t1,	$zero,	2	# $t1 = 2
		beq	$t0,	$t1,	case2
		addi	$t1,	$zero,	3	# $t1 = 2
		beq	$t0,	$t1,	case3
		addi	$t1,	$zero,	4	# $t1 = 2
		beq	$t0,	$t1,	case4
		j	default

case1:		addi	$t2,	$t2,	1	#k=1
		j	switch_end		# break
case2:		addi	$t2,	$t2,	2       #k=2
		j	switch_end		# break
case3:		addi	$t2,	$t2,	3	#k=3
		j	switch_end		# break
case4:		addi	$t2,	$t2,	4	#k=4
		j	switch_end		# break
default:	addi	$t2,	$t2,	0xFF	# k = 0 xFF
switch_end:	sw	$t2,	k		# store k
# ------------------------------------------------
output:		li	$v0,	1		# print result
		lw	$a0,	k
		syscall
		li	$v0,	4		# print newline
		la	$a0,	newline
		syscall
finish:		li	$v0,	10		# exit program
		syscall

