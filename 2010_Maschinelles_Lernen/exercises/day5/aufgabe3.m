% Daten laden
load("SeqMats.mat");

nDim = rows(SeqPosMat) + rows(SeqNegMat);

% Codiere negative und positive Beispiele
codedPosSamplesMat = encodeMat(SeqPosMat(:, [70:130]));
codedNegSamplesMat = encodeMat(SeqNegMat(:, [70:130]));

% Anzahl von positiven und negativen Beispielen
posDim = columns(codedPosSamplesMat);
negDim = columns(codedNegSamplesMat);

kNNErrorVec = zeros(1, 13);

for kPar = 1:2:25

	for nRuns = 1:10

		% Positiven und negativen Datensatz fuer jede Iteration permutieren
		codedPosMat2 = codedPosSamplesMat(:, randperm(posDim));
		codedNegMat2 = codedNegSamplesMat(:, randperm(negDim));
		
		% Permutationsindexes um Trainings- und Testdaten zu mischen
		trainPermMat = randperm(floor(posDim / 2) + floor(negDim / 2));
		testPermMat = randperm(posDim - floor(posDim / 2) + negDim - floor(negDim / 2));

		% erste Haelfte der Daten ist zum Trainieren, der Rest zum Testen
		trainMat = [codedPosMat2(:, 1:floor(posDim / 2)) codedNegMat2(:, 1:floor(negDim / 2))];
		trainMat = trainMat(:, trainPermMat);

		testMat = [codedPosMat2(:, (floor(posDim / 2) + 1):posDim)  codedNegMat2(:, (floor(negDim / 2) + 1):negDim)];
		testMat = testMat(:, testPermMat);
		
		% zugehoerige Labels
		trainLabelVec = [ones(floor(posDim / 2), 1); -ones(floor(negDim / 2), 1)];
		trainLabelVec = trainLabelVec(trainPermMat);

		testLabelVec = [ones(posDim - floor(posDim / 2), 1); -ones(negDim - floor(negDim / 2), 1)];
		testLabelVec = testLabelVec(testPermMat);

		% codedNegSamplesMat-next-neighbor
		kNNErrorVec(ceil(kPar / 2)) += kNextNeighborTest(trainMat, trainLabelVec, testMat, testLabelVec, kPar) / length(testLabelVec);

	end

	printf('Fortschritt: %d Prozent\n', (kPar / 25) * 100);

end

kNNErrorVec = kNNErrorVec ./ 10;

bar([1:2:25], [kNNErrorVec]);
xlabel("k-Wert");
ylabel("Fehler [%]");
title("kNN-Klassifikator auf gefilterte TIS Datenmenge");
