% Funktion erstellt zwei 2x[nDim]-Matrizen mit Zufallswerten und plottet diese mit Verschiebung und lin. Separator.
function plotA2(nDim, vVec, nRuns)

hold off
for run = 1:nRuns
clf

%Generiere Datensatz-Matrizen.
xMat = generate2DMatrix(nDim,1);
xConvHullIdxVec = convhull(xMat(1,:),xMat(2,:));
yMat = verschiebe2DMatrix(generate2DMatrix(nDim,1),vVec);
yConvHullIdxVec = convhull(yMat(1,:),yMat(2,:));

% Zeichne erste Punktwolke.
plot(xMat(1,:), xMat(2,:), 'o g');

hold on

% Zeichne konvexe Huelle
plot(xMat(1, xConvHullIdxVec), xMat(2, xConvHullIdxVec), 'g');

% Zeichne zweite Punktwolke.
plot(yMat(1,:), yMat(2,:), 'o r');

% Zeichne konvexe Huelle
plot(yMat(1, yConvHullIdxVec), yMat(2, yConvHullIdxVec), 'r');

% Anlegen eines Pruefcounters
check1 = 0;

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
	check1=1;
endif

% Anlegen eines Pruefvektors.
testVec = zeros(length(yConvHullIdxVec) - 1, 1);

% For/Schleife zur Bestimmung von Kollisionen
for iStep = 1:length(yConvHullIdxVec) - 1
	testMat = [xMat yMat(:,yConvHullIdxVec(iStep))];
	xTestConvHullIdxVec = convhull(testMat(1,:),testMat(2,:));
	if(length(xTestConvHullIdxVec)==length(xConvHullIdxVec))
		if(xTestConvHullIdxVec==xConvHullIdxVec)
			testVec(iStep) = 1;
		endif
	endif
endfor

if(sum(testVec==0)>0)
	check1=1;
endif

if(check1==0)
	disp("Keine Kollision");
else
	disp("Kollision");
endif

hold off
pause;
endfor;