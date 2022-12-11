% Erstellt einen 2D Datensatz mit Werten aus [0, 1] fuer Klasse 1 (1) und [0, 1] + biasVec fuer Klasse 2 (-1). Diese werden durchgemischt.
% Die Label werden in dem Vektor labelVec gespeichert.
function [dataMat, labelVec] = genDataset(nDim, biasVec)

	dataMat = [dataset2D(nDim) biasedDataset2D(nDim, biasVec)];
	labelVec = [ones(nDim, 1); -ones(nDim, 1)];

	shuffleMat = randperm(2 * nDim);

	dataMat = dataMat(:, shuffleMat);
	labelVec = labelVec(shuffleMat);
	
end