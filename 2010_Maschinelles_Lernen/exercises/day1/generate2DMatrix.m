% Funktion erstellt eine 2D-Matrix mit nDim Zufallswerten oder Nullen.
function [m2DMat] = generate2DMatrix(nDim, randomValue)

% Nutzung der Hilfsfunktion generateMatrix.
m2DMat = generateMatrix(2, nDim, randomValue);

