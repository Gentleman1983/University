.data
list:	.word	10, 20, -90000, -1, 22, 34, 3, 20, 100, 99
length:	.word	10

.text
main:		la	$a0,	list		# load address of label list in $a0
						# $ao points of the first element of list
		la	$t1,	length		# load address of label length in $t1
		lw	$a1,	0($t1)		# load word value at address $t1,
						# i. e. value at address length
						# $a1 is the loop counter
		li	$v0,	0x7fffffff	# init $v0 with a huge value
		li	$t8,	1		# init $t8 with 1
		blez	$a1,	end_loop	#
loop:						# if $a1 <= 0 (counter <= 0) branch to end_loop,
						# that means loop passed length times , all elements
						# are explored
		lw	$t2,	0($a0)		# load word value at address $a0 in $t2,
						# $t2 is the current list element
		addi	$a0,	$a0,	4	# $a0 +=4 , point $a0 to the next element of the list
		sub	$a1,	$a1,	$t8	# $a1-- , decrease loop counter
		sub	$t3,	$t2,	$v0	# $t3 = $t2 - $v0 , difference of current list element and $v0
		bgtz	$t3,	loop		# branch to loop if $t3 > 0, i . e . branch to loop if
						# the current element is greather than $v0
						# current element $t2 is less than or equal $v0
		move	$v0,	$t2		# move current element to $v0
		j	loop			# next pass of loop
end_loop:	move	$s0,	$v0		# store the minimum list element in $s0
		li	$v0,	1		# print result
		move	$a0,	$s0		#
		syscall				#
		li	$v0,	10		# exit program
		syscall				#
