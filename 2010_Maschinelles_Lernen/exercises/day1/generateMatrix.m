% Funktion erstellt eine [nRows]x[nCols]-Matrix mit Zufallswerten oder Nullen.
function [mMat] = generateMatrix(nRows, nCols, randomValue)

% Wenn randomValue 0 ist, wird ein 0-Matrix erstellt, bei anderen Werten ZufallsMatrix
if(randomValue ~= 0)
	mMat = rand(nRows,nCols);
else
	mMat = zeros(nRows,nCols);
endif;
