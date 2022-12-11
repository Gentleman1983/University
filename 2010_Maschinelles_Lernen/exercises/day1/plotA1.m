% Funktion erstellt zwei 2x[nDim]-Matrizen mit Zufallswerten und plottet diese mit Verschiebung und lin. Separator.
function [xMat, yMat] = plotA1(nDim, vVec)

hold off

clf

%Generiere Datensatz-Matrizen.
xMat = generate2DMatrix(nDim,1);
yMat = verschiebe2DMatrix(generate2DMatrix(nDim,1),vVec);

% Zeichne erste Punktwolke.
plot(xMat(1,:), xMat(2,:), 'o g');

hold on

% Zeichne zweite Punktwolke.
plot(yMat(1,:), yMat(2,:), 'o r');

% Zeichne Zentroidklassifikator.
plot([0:2], -[0:2]*(vVec(1)/vVec(2)) + 1 + vVec(1)*(vVec(1)/vVec(2)), 'b');

hold off



