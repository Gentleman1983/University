% Fuehrt ein Zentroid-basiertes Training durch
function [posCenterVec negCenterVec] = centroidTrain(trainMat, labelVec)

	posCenterVec = [mean(trainMat(1, labelVec == 1)); mean(trainMat(2, labelVec == 1))];
	negCenterVec = [mean(trainMat(1, labelVec == -1)); mean(trainMat(2, labelVec == -1))];	

	%hold on
	%axis([0, 2, 0, 2])

	%plotLabeledDataset2D(trainMat, labelVec);

	%plot(posCenterVec(1), posCenterVec(2), 'x b');
	%plot(negCenterVec(1), negCenterVec(2), 'x b');

end