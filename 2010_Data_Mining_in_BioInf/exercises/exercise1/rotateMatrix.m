function RotatedMatrixMat=rotateMatrix(XdataMat,alphaAngle,nVecs)
	%{Rotationsmatrix anlegen%}
	RotMat=[cos(alphaAngle) sin(alphaAngle);-1*sin(alphaAngle) cos(alphaAngle)];
	%{Matrix rotieren%}
	RotatedMatrixMat=XdataMat;
	for i=1:nVecs,
		RotatedMatrixMat(:,i)=RotMat*RotatedMatrixMat(:,i);
	end
end
