% Ermittlet die Anzahl der Testfehler
function nErrors = perceptronTest(testMat, labelVec, weightsVec, biasPar)

	nErrors = 0;

	for iStep = 1:columns(testMat)	

		if(labelVec(iStep) * (weightsVec' * testMat(:, iStep) + biasPar) <= 0)

			nErrors = nErrors + 1;

		end

	end			

end