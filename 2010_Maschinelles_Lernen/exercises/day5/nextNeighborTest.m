% Ermittelt die Fehleranzahl einer naechster-nachbar basierten Klassifikation
function nErrors = nextNeighborTest(trainMat, trainLabelVec, testMat, testLabelVec)

	nErrors = 0;

	for iStep = 1:columns(testMat)

		diffVecMat = trainMat - repmat(testMat(:, iStep), 1, columns(trainMat));
	
		distMat = sqrt(sum(diffVecMat .^2));
	
		[minVal minIndex] = min(distMat);

		klassVec(iStep) = trainLabelVec(minIndex);

		%hold on
		%plot(trainMat(1, minIndex), trainMat(2, minIndex), 'v k');

		%if(klassVec(iStep) == 1)
		%	plot(testMat(1, iStep), testMat(2, iStep), 'x g');
		%else
		%	plot(testMat(1, iStep), testMat(2, iStep), 'x r');
		%end

	end

	nErrors = sum(klassVec ~= testLabelVec');

end