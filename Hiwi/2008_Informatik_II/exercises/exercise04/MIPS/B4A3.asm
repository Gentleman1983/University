#
# quadrat
#
.data
msg:		.asciiz	"integer = "
error_msg:	.asciiz	"error integer > 0 expected\n"
newline:	.asciiz	"\n"

.text
main:		li	$v0,	4		# print_string msg
		la	$a0,	msg
		syscall
		li	$v0,	5		# read_int in $a0
		syscall
		move	$a0,	$v0		# $a0 = n
# ------------------------------------------------
		bltz	$a0,	error		# check $a0 < 0
		add	$t0,	$zero,	$zero	# $t0 = 0     sum
		addi	$t1,	$zero,	1	# $t1 = 1     counter
		addi	$t2,	$zero,	1	# $t2 = 1     add
loop:		sub	$t9,	$a0,	$t1	# $t9 = $a0 - $t1 = n - counter
		bltz	$t9,	end_loop	# branch to end_loop if $t9 < 0 ,
						# i . e . counter > n
		add	$t0,	$t0,	$t2	# sum += add
		addi	$t2,	$t2,	2	# add += 2
		addi	$t1,	$t1,	1	# counter ++
		j	loop
end_loop:	move	$s0,	$t0		# $s0 = n ^2
# ------------------------------------------------
output:		li	$v0,	1		# print result
		move	$a0,	$s0
		syscall
		li	$v0,	4		# print newline
		la	$a0,	newline
		syscall
		j	finish
error:		li	$v0,	4		# print error
		la	$a0,	error_msg
		syscall
finish:		li	$v0,	10		# exit program
		syscall
