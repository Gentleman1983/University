.data
index:		.asciiz	"max index:"
newline:	.asciiz "\n"

.text
# =====================================================================
main:
# input ---------------------------------------------------------------
		la	$a0,	index			# read max index to $s0
		li	$v0,	4
		syscall
		li	$v0,	5
		syscall
		move	$s0,	$v0
# output --------------------------------------------------------------
out:
		move	$a0,	$s0
		jal	fib
		move	$a0,	$v0
		li	$v0,	1
		syscall
		la	$a0,	newline
		li	$v0,	4
		syscall
		addi	$s0,	$s0,	-1
		bgez	$s0,	out
# exit ----------------------------------------------------------------
		li	$v0,	10
		syscall
# =====================================================================
fib:
# save register -------------------------------------------------------
		addi	$sp,	$sp,	-16		# reserve space on stack
							# for $ra, $fp and two local words
		sw	$ra,	16($sp)			# save return address on stack
		sw	$fp,	12($sp)			# save frame pointer on stack
		add	$fp,	$sp,	16		# new frame pointer is old stack pointer
# return conditions ---------------------------------------------------
							# $a0 = i
		li	$v0,	0			# f_0 = 0
		blez	$a0,	return			# return condition i = 0
		addi	$a0,	$a0,	-1		# $a0 = i - 1
		li	$v0,	1			# f_1 = 1
		blez	$a0,	return			# return condition i = 1
# computation ----------------------------------------------------------
							# $a0 = i - 1
		sw	$a0,	8($sp)			# save $a0 on stack, IMPORTANT
		jal	fib				# recursion with i - 1
		lw	$a0,	8($sp)			# restore $a0
		sw	$v0,	4($sp)			# save $v0 on stack, IMPORTANT
		addi	$a0,	$a0,	-1		# $a0 = i - 2
		jal	fib				# recursion with i - 1
		move	$t0,	$v0			# move new $v0 to $t0
		lw	$v0,	4($sp)			# restore previous $v0
		add	$v0,	$v0,	$t0		# f_i = f_ (i -1) + f_ (i -2)
# restore register ------------------------------------------------------
return:
		lw	$fp,	12($sp)			# restore $fp from stack
		lw	$ra,	16($sp)			# restore $ra from stack
		add	$sp,	$sp,	16		# restore stack pointer
# return ----------------------------------------------------------------
		jr	$ra

