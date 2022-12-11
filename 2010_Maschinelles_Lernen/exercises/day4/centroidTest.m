% Fuehrt ein Zentroid-basierten Test durch
function nErrors = centroidTest(testMat, labelVec, posCenterVec, negCenterVec)

	% Differenzvektoren aller Datenpunkte zu den positiven/negativen Schwerpunkten
	posDiffVecMat = testMat - repmat(posCenterVec, 1, columns(testMat));
	negDiffVecMat = testMat - repmat(negCenterVec, 1, columns(testMat));

	% Abstaende zu den positiven/negativen Schwerpunkten
	posDistVec = sqrt(sum(posDiffVecMat .^2));
	negDistVec = sqrt(sum(negDiffVecMat .^2));

	% Indexvektor der positiven/negativen Beispiele
	posLabelVec = (labelVec' == 1);
	negLabelVec = (labelVec' == -1);

	% Summe ueber die falsch klassifizierten Beispiele
	nErrors = sum((posDistVec > negDistVec) .* posLabelVec) + sum((posDistVec < negDistVec) .* negLabelVec);

	%plotLabeledDataset2D(testMat, labelVec);

	%m = -1 / ((posCenterVec(2) - negCenterVec(2)) / (posCenterVec(1) - negCenterVec(1)));

	%plot([0:2], m * [0:2] + (posCenterVec(2) - posCenterVec(1) * m + + negCenterVec(2) - negCenterVec(1) * m) / 2, 'b');

end