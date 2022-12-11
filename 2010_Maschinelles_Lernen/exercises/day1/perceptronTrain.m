function [WeightsVec, biasPar, nErrors] = perceptronTrain(XTrainMat, YTrainVec, etaPar=.1)

nErrors=1
while(nErrors>0)
	nErrors=0;
	for iSteps=1:n
		if(yi*(w(k)*x(i)+b(k))<=0)
			w(k+1)=w(k)+etaPar*y(i)*x(i)
			b(k+1)=b(k)+etaPar*y(i)*R**2
			k=k+1
		endif
	endfor
endwhile