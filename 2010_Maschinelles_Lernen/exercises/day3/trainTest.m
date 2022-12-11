function [avgErrors avgRuns] = trainTest(etaPar = 0.1, nRuns = 10)

	load("SeqMats.mat");

	codePosMat = encodeMat(SeqPosMat);
	codeNegMat = encodeMat(SeqNegMat);
	
	posDim = columns(codePosMat);
	negDim = columns(codeNegMat);
	
	avgErrors = 0;
	avgRuns = 0;
	
	for iStep = 1:nRuns
	
		code2PosMat = codePosMat(:, randperm(posDim));
		code2NegMat = codeNegMat(:, randperm(negDim));
		
		trainPermMat = randperm(floor(posDim / 2) + floor(negDim / 2));
		testPermMat = randperm(posDim - floor(posDim / 2) + negDim - floor(negDim / 2));

		trainMat = [code2PosMat(:, 1:floor(posDim / 2)) code2NegMat(:, 1:floor(negDim / 2))];
		trainMat = trainMat(:, trainPermMat);

		testMat = [code2PosMat(:, (floor(posDim / 2) + 1):posDim)  code2NegMat(:, (floor(negDim / 2) + 1):negDim)];
		testMat = testMat(:, testPermMat);
		
		trainLabelVec = [ones(floor(posDim / 2), 1); -ones(floor(negDim / 2), 1)];
		trainLabelVec = trainLabelVec(trainPermMat);

		testLabelVec = [ones(posDim - floor(posDim / 2), 1); -ones(negDim - floor(negDim / 2), 1)];
		testLabelVec = testLabelVec(testPermMat);
		
		[weightsVec, biasPar, nErrors, numberRuns] = perceptronTrain(trainMat, trainLabelVec, etaPar);
		
		avgErrors += perceptronTest(testMat, testLabelVec, weightsVec, biasPar);
		avgRuns += numberRuns;
	
	end
	
	avgErrors = avgErrors / nRuns;
	avgRuns = avgRuns / nRuns;

end