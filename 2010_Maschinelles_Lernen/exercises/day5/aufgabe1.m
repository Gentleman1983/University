clear
clf

nDim = 100;

load("SeqMats.mat");
%nDim = rows(SeqPosMat) + rows(SeqNegMat);

codedPosMat = encodeMat(SeqPosMat);
codedNegMat = encodeMat(SeqNegMat);

posDim = columns(codedPosMat);
negDim = columns(codedNegMat);

trainingMat = [codedPosMat, codedNegMat];
permVec = randperm(columns(trainingMat));
trainingMat = trainingMat(:, permVec);

trainingLabelVec = [ones(posDim, 1); -1*ones(negDim, 1)];
trainingLabelVec = trainingLabelVec(permVec);

[posCenterVec negCenterVec] = centroidTrain(trainingMat, trainingLabelVec);

%weightVec = negCenterVec - posCenterVec;
weightVec = posCenterVec - negCenterVec;
%dataMat = trainingMat .* repmat(weightVec, 1, columns(trainingMat));
dataPixelMat = reshape(weightVec', columns(weightVec')/4, 4);
%dataPixelMat = reshape(posCenterVec', columns(posCenterVec')/4, 4);
%dataPixelMat = reshape(negCenterVec', columns(negCenterVec')/4, 4);

imagesc(dataPixelMat');
colorbar;