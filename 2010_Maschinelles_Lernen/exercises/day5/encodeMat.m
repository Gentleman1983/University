function codeMat = encodeMat(inputMat)

	codeMat = [inputMat == 'A' inputMat =='C' inputMat =='G' inputMat =='T']';

	%[nDim mDim] = size(inputMat);

	%for iStep = 1:nDim

	%	for jStep = 1:mDim

	%		codeVec = encode(inputMat(iStep, jStep));

	%		for i = 1:4

	%			codeMat((jStep - 1) * 4 + i, iStep) = codeVec(i);

	%		end

	%	end

	%end

end