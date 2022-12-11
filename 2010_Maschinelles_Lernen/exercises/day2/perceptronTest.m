function [nErrors] = perceptronTest (XTestMat, YTestVec , WeightsVec, bPar)
[nDim nVec] = size(XTestMat);

nErrors=0;

printf("Teste Datensatz: ")

r =  max ( sqrt( sum(XTestMat.^2, 1) ) );

for n=1:nVec
	if YTestVec(n)*(  (dot(XTestMat(:,n),WeightsVec))+bPar  )<=0
		nErrors=nErrors+1;
	end
end



endfunction