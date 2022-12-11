.data
mask:		.byte	0, 0, 0, 0, 0, 0, 0, 0
bound:		.asciiz	"upper bound:"
whitespace:	.asciiz	" "
comma:		.asciiz	" ,"
newline:	.asciiz	"\n"

.text
main:
# input --------------------------------------------------------------
		la	$a0,	bound			# read upper bound to $t9
		li	$v0,	4
		syscall
		li	$v0,	5
		syscall
		move	$t9,	$v0
# calculate size of sieve --------------------------------------------
		li	$t8,	8			# bits per byte
		sra	$t7,	$t9,	1		# sieve contains only odd numbers
		div	$t7,	$t8
		mflo	$t7
		mfhi	$t0
							# if bound mod 2 != 0 one byte more is needed
		beqz	$t0,	no_rest
		addi	$t7,	$t7,	1
no_rest:
# init mask ----------------------------------------------------------
		sub	$sp,	$sp,	$t7		# reserve space on stack
		li	$s0,	1			# init with 00000001
		move	$t0,	$zero			# init loop counter
imask:
		sb	$s0,	mask($t0)		# store mask[0]=00000001 to mask[7]=10000000
		sll	$s0,	$s0,	1		# shift mask bit left
		addi	$t0,	$t0,	1		# increment loop counter
		blt	$t0,	$t8,	imask		# if $t0 < $t8 branch to imask
# init sieve ----------------------------------------------------------
		move	$t0,	$t7
iseq:
		add	$t1,	$t0,	$sp		# point $t1 to sieve [$t0]
		sb	$zero,	($t1)			# init each byte of sieve to 00000000
		addi	$t0,	$t0,	-1		# decrement loop counter
		bgtz	$t0,	iseq			# if $t0 > 0 branch to iseq
# outer loop to traverse all odd numbers -------------------------------
		li	$s0,	1			# first prime is 3
number:
		addi	$s0,	2			# step $s0 += 2
		mult	$s0,	$s0
							# first multiple to strike off is $s0 * $s0
		mflo	$s1
		bgt	$s1,	$t9,	done		# break condition
							# if $s1 > $t9 branch to done
 # inner loop to traverse all multiples ---------------------------------
		sll	$s2,	$s0,	1		# step in multiple is $s0 *2
multiple:
		sra	$t0,	$s1,	1		# $t0 = $s1 div 2
							# sieve contains only odd numbers
		div	$t0,	$t8			# compute position of $t0 on sieve
		mflo	$t1				# byte of sieve
		mfhi	$t2				# bit of sieve [$t1]
		add	$t1,	$t1,	$sp		# point $t1 to sieve [$t1]
		addi	$t1,	$t1,	1		# $sp points under the stack
		lb	$t4,	($t1)			# load sieve [$t1]
		lb	$t5,	mask($t2)		# mask
		or	$t0,	$t4,	$t5		# strike off number , i . e . set position to 1
		sb	$t0,	($t1)			# write back
		add	$s1,	$s1,	$s2		# step $s1 = $s1 + $s2
		ble	$s1,	$t9,	multiple	# if $s1 <= $t9 branch to multiple
# outer loop to traverse all odd numbers , footer -------------------------
		j	number				# branch to number
done:
# output ------------------------------------------------------------------
		li	$a0,	2
		li	$v0,	1
		syscall
		la	$a0,	whitespace
		li	$v0,	4
		syscall
		li	$s0,	3			# the first prime is 3
out:
		sra	$t0,	$s0,	1		# $t0 = $s0 div 2
							# sieve contains only odd numbers
		div	$t0,	$t8			# compute position of $t0 on sieve
		mflo	$t1				# byte of sieve
		mfhi	$t2				# bit of sieve[$t1]
		add	$t1,	$t1,	$sp		# point $t1 to sieve [$t1]
		addi	$t1,	$t1,	1		# $sp points under the stack
		lb	$t4,	($t1)			# load sieve [$t1]
		lb	$t5,	mask($t2)		# mask
							# is at bit $t2 on byte sieve [$t1] a one ?
		and	$t0,	$t4,	$t5
		bne	$t0,	$zero,	skip
		move	$a0,	$s0			# print $s0
		li	$v0,	1
		syscall
		la	$a0,	whitespace
		li	$v0,	4
		syscall
skip:
		addi	$s0,	2			# step $s0 += 2
		ble	$s0,	$t9,	out		# if $s0 <= $t9 branch to out
		la	$a0,	newline			# print newline
		li	$v0,	4
		syscall
# clean up -----------------------------------------------------------
		add	$sp,	$sp,	$t7		# restore stack pointer
# exit ---------------------------------------------------------------
		li	$v0,	10
		syscall

