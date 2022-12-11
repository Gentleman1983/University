% Plottet einen 2D Datensatz unter Beruecksichtigung von Labeln
function plotLabeledDataset2D(dataMat, labelVec)

	plot(dataMat(1, (labelVec == 1)), dataMat(2, (labelVec == 1)), 'o g');
	
	plot(dataMat(1, (labelVec == -1)), dataMat(2, (labelVec == -1)), 'o r');

end