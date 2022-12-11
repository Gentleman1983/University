function codeVec = encode(inputVal)

	codeVec = [(inputVal == 'A') (inputVal == 'C') (inputVal == 'G') (inputVal == 'T')]';

end