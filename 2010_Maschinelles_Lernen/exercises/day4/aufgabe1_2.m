clear
clf

nDim = 100;

[trainMat trainLabelVec] = genDataset(nDim, [0.9 0.9]');
[testMat testLabelVec] = genDataset(nDim, [0.9 0.9]');

[posCenterVec negCenterVec] = centroidTrain(trainMat, trainLabelVec);

nErrors = centroidTest(testMat, testLabelVec, posCenterVec, negCenterVec);

printf('Klassifikationsfehler: %f\n', nErrors);