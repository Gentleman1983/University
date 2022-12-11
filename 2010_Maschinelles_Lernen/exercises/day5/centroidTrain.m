% Fuehrt ein Zentroid-basiertes Training durch
function [posCenterVec negCenterVec] = centroidTrain(trainMat, labelVec)

	posCenterVec = [mean(trainMat(:, labelVec == 1), 2)];
	negCenterVec = [mean(trainMat(:, labelVec == -1), 2)];	

	%hold on
	%axis([0, 2, 0, 2])

	%plotLabeledDataset2D(trainMat, labelVec);

	%plot(posCenterVec(1), posCenterVec(2), 'x b');
	%plot(negCenterVec(1), negCenterVec(2), 'x b');

end