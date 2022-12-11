% Funktion verschiebt eine 2D-Matrix um einen 2x1-Vektor.
function [returnMat] = verschiebe2DMatrix(m2DMat, verschiebeVec)

% Bestimme Dimensionalitaet der Matrix.
[rowDim, colDim] = size(m2DMat);

% Addiere vervielfaeltigten Vektor zur Matrix.
returnMat = m2DMat + repmat(verschiebeVec, 1, colDim);
