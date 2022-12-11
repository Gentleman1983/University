function [WeightsVec, bPar, nErrors] = perceptronTrain (XTrainMat, YTrainVec , etaPar)
[nDim nVec] = size(XTrainMat);


printf("Train Perzeptron: ")
tempbPar = 0;
TempWVec = ones(nDim, 1);


WeightsVec = TempWVec;
bPar = tempbPar;

r =  max ( sqrt( sum(XTrainMat.^2, 1) ) );

nFehler=1;
for i = 1:50
	if nFehler==0
		break;
	end
	nFehler=0;

	%klassisch
	WeightsVec=TempWVec;
	bPar = tempbPar;
	
	%versuch
	%gew = 1e7*exp(-i*1e0);
	%WeightsVec=(TempWVec.*gew+WeightsVec)./(gew+1)
	%bPar = (tempbPar.*gew+bPar)/(gew+1)

	for n=1:nVec
		if YTrainVec(n)*(  (dot(XTrainMat(:,n),WeightsVec))+bPar  )<=0
			nFehler=nFehler=1;	
			TempWVec = TempWVec + XTrainMat(:,n) *etaPar*YTrainVec(n); 
			tempbPar	   = tempbPar 	+ etaPar*YTrainVec(n)*r^2 ;
		end
	end

	clf; hold
	ausgabe(XTrainMat(:,1:nVec/2), XTrainMat(:,nVec/2+1:nVec ), WeightsVec, bPar)
end



endfunction
