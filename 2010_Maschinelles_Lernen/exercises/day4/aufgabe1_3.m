clear
clf

nDim = 100;

[trainMat trainLabelVec] = genDataset(nDim, [0.9 0.9]');
[testMat testLabelVec] = genDataset(nDim, [0.9 0.9]');

testMat = testMat;
testLabelVec = testLabelVec;

nErrors = nextNeighborTest(trainMat, trainLabelVec, testMat, testLabelVec);

hold on
axis([0, 2, 0, 2])
plotLabeledDataset2D(trainMat, trainLabelVec);

plotLabeledDataset2D(testMat, testLabelVec);

printf('Klassifikationsfehler: %f\n', nErrors);