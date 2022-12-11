clear
clf

nDim = 100;

[trainMat trainLabelVec] = genDataset(nDim, [0.9 0.9]');
[testMat testLabelVec] = genDataset(nDim, [0.9 0.9]');

[weightsVec biasPar nErrors nRuns] = perceptronTrain(trainMat, trainLabelVec);

nErrors = perceptronTest(testMat, testLabelVec, weightsVec, biasPar);

printf('Klassifikationsfehler: %f\n', nErrors);