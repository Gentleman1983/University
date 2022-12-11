clear
clf

perceptronErrorVec = zeros(1, 1000 / 40);
centroidErrorVec = zeros(1, 1000 / 40);
nextNeighborErrorVec = zeros(1, 1000 / 40);

for nDim = 40:40:1000

	for nRuns = 1:10

		% Trainingsdaten fuer den aktuellen Durchlauf generieren
		[trainMat trainLabelVec] = genDataset(nDim, [0.9 0.9]');
		[testMat testLabelVec] = genDataset(nDim, [0.9 0.9]');
		
		% 1) Perzepron
		[weightsVec biasPar nErrors nRuns] = perceptronTrain(trainMat, trainLabelVec);
	
		perceptronErrorVec(nDim / 40) += perceptronTest(testMat, testLabelVec, weightsVec, biasPar) / nDim;
	
		% 2) Zentroid
		[posCenterVec negCenterVec] = centroidTrain(trainMat, trainLabelVec);
	
		centroidErrorVec(nDim / 40) += centroidTest(testMat, testLabelVec, posCenterVec, negCenterVec) / nDim;
	
		% 3) Next-neighbor
		nextNeighborErrorVec(nDim / 40) += nextNeighborTest(trainMat, trainLabelVec, testMat, testLabelVec) / nDim;

	end

	printf('Fortschritt: %f Prozent\n', (nDim / 1000) * 100);

end

perceptronErrorVec = perceptronErrorVec ./ 10;
centroidErrorVec = centroidErrorVec ./ 10;
nextNeighborErrorVec = nextNeighborErrorVec ./ 10;

hold on
plot([40:40:1000], perceptronErrorVec, 'r');
plot([40:40:1000], centroidErrorVec, 'b');
plot([40:40:1000], nextNeighborErrorVec, 'g');