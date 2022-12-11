% Erstellt einen 2D Datensatz mit Werten aus [0, 1], der um den bias Vektor (Spaltenvektor) verschoben ist 
function dataMat = biasedDataset2D(nDim, bVec)

	dataMat = rand(2, nDim);
	
	dataMat = dataMat + repmat(bVec, 1, nDim);

end