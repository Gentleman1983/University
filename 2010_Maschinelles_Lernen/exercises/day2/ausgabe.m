function ausgabe (XMat, YMat, WeightsVec, bPar)

	x = [-1,3];


	axis([-1,3,-1,3]);
	plot(XMat(1,:), XMat(2,:), 'xr', YMat(1,:), YMat(2,:), '+b')
	plot(x, -bPar/WeightsVec(2) - WeightsVec(1)/WeightsVec(2)*x)
	pause(0.1)
	
endfunction