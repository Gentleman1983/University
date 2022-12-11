% Funktion erstellt zwei 2x[nDim]-Matrizen mit Zufallswerten und plottet diese mit Verschiebung und lin. Separator.
function [check] = plotA2(xMat, yMat, )
xConvHullIdxVec = convhull(xMat(1,:),xMat(2,:));
yConvHullIdxVec = convhull(yMat(1,:),yMat(2,:));

% Anlegen eines Pruefvektors.
testVec = zeros(length(xConvHullIdxVec) - 1, 1);

% For/Schleife zur Bestimmung von Kollisionen
for iStep = 1:length(xConvHullIdxVec) - 1
	testMat = [yMat xMat(:,xConvHullIdxVec(iStep))];
	yTestConvHullIdxVec = convhull(testMat(1,:),testMat(2,:));
	if(length(yTestConvHullIdxVec)==length(yConvHullIdxVec))
		if(yTestConvHullIdxVec==yConvHullIdxVec)
			testVec(iStep) = 1;
		endif
	endif
endfor

if(sum(testVec==0)>0)
	check=1;
endif