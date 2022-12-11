% Ermittelt die Fehleranzahl einer naechster-nachbar basierten Klassifikation
function nErrors = kNextNeighborTest(trainMat, trainLabelVec, testMat, testLabelVec, kPar)

	nErrors = 0;

	% Distanzvektor bestimmen
	A = sum(trainMat .^ 2, 1)';
	B = sum(testMat .^ 2, 1);

	A = repmat(A, 1, columns(B));
	B = repmat(B, rows(A), 1);

	AB = trainMat' * testMat;

	distMat = A + B - 2 * AB;

	%for iStep = 1:columns(testMat)

		% Differenzenvektor des Punktes zu allen Punkten aus der Trainingsmenge
	%	diffVecMat = trainMat - repmat(testMat(:, iStep), 1, columns(trainMat));
	
		% Distanzvektor des Punktes zu allen Punkten aus der Trainingsmenge (Euklidische Norm)
	%	distMat = sum(diffVecMat .^2);

	%end
	
	% Index des naehesten Nachbarn aus der Trainingsmenge
	[minValVec minIndexVec] = sort(distMat, 1);

	%size(minIndexVec)

  	%size(trainLabelVec(minIndexVec(1:kPar, :)'))

	% Label der k naehesten Nachbarn
	klassVec = sign(sum(trainLabelVec(minIndexVec(1:kPar, :)'), 2)');

	%size(klassVec)
	%size(testLabelVec)

	%Graphische Ausgabe
	%hold on
	%plot(trainMat(1, minIndex), trainMat(2, minIndex), 'v k');
	%if(klassVec(iStep) == 1)
	%	plot(testMat(1, iStep), testMat(2, iStep), 'x g');
	%else
	%	plot(testMat(1, iStep), testMat(2, iStep), 'x r');
	%end

	nErrors = sum(klassVec ~= testLabelVec');

end