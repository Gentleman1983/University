nDims = 2
nVecs = 200


etaPar = 0.1;
verschiebungPar = 0.9

XMat = [rand(nDims, nVecs)];
YMat = [ ( rand(nDims, nVecs)+verschiebungPar*ones(nDims,nVecs) ) ];

%[disjunkt] = convHullTest(XMat, YMat)



XTrainMat = [XMat, YMat];
YTrainVec = [repmat(1, 1, size(XMat, 2) ), repmat(-1, 1, size(YMat, 2) )];



[WeightsVec bPar] = perceptronTrain (XTrainMat, YTrainVec, etaPar )
clf; hold
ausgabe(XMat, YMat, WeightsVec, bPar)


for i = 1:10
	XMat = [rand(nDims, nVecs)];
	YMat = [ ( rand(nDims, nVecs)+verschiebungPar*ones(nDims,nVecs) ) ];
	
	XTestMat = [XMat, YMat];
	YTestVec = [repmat(1, 1, size(XMat, 2) ), repmat(-1, 1, size(YMat, 2) )];
	
	[nErrors] = perceptronTest (XTestMat, YTestVec , WeightsVec, bPar)
end
