% Fuehrt die Perzeptron-Lernregel fuer die Trainingsmenge trainMat und die zugehoerigen Label labelVec durch
function [weightsVec, biasPar, nErrors, nRuns] = perceptronTrain(trainMat, labelVec, etaPar)

	[nDim mDim] = size(trainMat);

	% bei nicht gegebenem Eta, die Standardlernschrittweite von 0.1 nutzen
	if(!exist('etaPar'))
		etaPar = 0.1;
	end
	
	% Initialisierung
	weightsVec = ones(nDim, 1);
	biasPar	= 0;
	nErrors = 0;
	rPar = max(sqrt(sum(trainMat.^2, 1)));
	
	maxRuns = 1000;
	nRuns = 0;
	
	%hold on
	%plotLabeledDataset2D(trainMat, labelVec);
	%axis([0, 2, 0, 2])
	
	do
	
		nErrors = 0;

		for iStep = 1:mDim
			
			if(labelVec(iStep) * (weightsVec' * trainMat(:, iStep) + biasPar) <= 0)
			
				weightsVec = weightsVec + etaPar * labelVec(iStep) * trainMat(:, iStep);
				
				biasPar = biasPar + etaPar * labelVec(iStep) * rPar^2;
				
				nErrors = nErrors + 1;
			
			end
		
		end
		
		maxRuns = maxRuns - 1;
	
		nRuns++;

	until(nErrors == 0 | maxRuns <= 0)

end