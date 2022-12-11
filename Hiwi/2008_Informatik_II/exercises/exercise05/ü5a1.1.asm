.data
mask:		.space	8
round:		.asciiz	"round\n"
term:		.asciiz	"regular termination\n"
whitespace:	.asciiz	" "
newline:	.asciiz	"\n"
num:		.float	1.0, -1.0, 2.0, 4.0, 8.0, 16.0, 1024.0, 0.01, 0.75, 0.625, 0.500244140625, 99999999999999999999999999.99999, -99999999999999999999999999999999999.999999
							# 1/2 + 1/4 = 0.75
							# 1/2 + 1/8 = 0.625
							# 1/2 + 1/2^12 = 0.500244140625
lenth:		.word	13

.text
# =====================================================================
main:
# init ---------------------------------------------------------------
		lw	$s7,	length			# number of floats in num
# num_loop -----------------------------------------------------------
		move	$s5,	$zero			# loop counter for num_loop
		move	$s6,	$zero			# field counter for num
num_loop:
		lw	$a0,	num($s6)		# load the 32-Bit of the float number [$s6] in $a0
		jal	bits_out
# not requested by task ----------------------------------------------
		la	$a0,	whitespace		# print whitespace
		li	$v0,	4
		syscall
		lwc1	$f12, 	num($s6)		# print number
		li	$v0,	2
		syscall
		la	$a0,	newline			# print newline
		li	$v0,	4
		syscall
# --------------------------------------------------------------------
		addi	$s5,	$s5,	1		# increment loop counter
		addi	$s6,	$s6,	4		# increment field counter
		blt	$s5,	$s7,	num_loop	# if $s5 < $s7 jump to num_loop
# exit ---------------------------------------------------------------
		la	$a0,	newline
		li	$v0,	4
		syscall
		la	$a0,	term
		li	$v0,	4
		syscall
		li	$v0,	10
		syscall
# =====================================================================
bits_out:
# init ----------------------------------------------------------------
		move	$t1,	$a0			# store $a0 in $t1
		li	$t0,	32			# loop counter is number bits of float
		li	$t9,	1			# init mask
		sllv	$t9,	31			# start with most significant bit
# bit loop, upper part ------------------------------------------------
bit_loop:
		and	$t2,	$t1,	$t9		# is mask bit in $t1 set?
		beq	$t2,	$zero,	zero		# if $t2 = 0 bit is not set, jump to zero
one:
		li	$a0,	1			# print 1
		li	$v0,	1
		syscall
		j	done
zero:
		li	$a0,	0			# print 0
		li	$v0,	1
		syscall
		j	done

done:
# whitspaces, not requested by task ------------------------------------
		li	$t3,	32			# whitespace after bit 32 (sign)
		beq	$t0,	$t3,	wspace
		li	$t3,	24			# whitespace after bit 26 (characteristic)
		beq	$t0,	$t3,	wspace
		j	no_wspace
wspace:
		la	$a0,	whitespace		# print whitespace
		li	$v0,	4
		syscall
no_wspace:
# -----------------------------------------------------------------------
# bit loop, lower part --------------------------------------------------
		srl	$t9,	$t9,	1		# shift mask bit right
		addi	$t0,	$t0,	-1		# decrement loop counter
		bgtz	$t0,	bit_loop		# if $t0 > 0 jump to bit_loop
# return ----------------------------------------------------------------
		jr	$ra


