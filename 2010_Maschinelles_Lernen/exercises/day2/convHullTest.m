function [disjunkt] = convHullTest(XMat, YMat)

%
%NICHT FUNKTIONSFAEHIG
%
%

disjunkt = 1;
[xDims, xVecs] = size(XMat);
[yDims, yVecs] = size(YMat);

XHullVec = convhull(XMat(1,:), XMat(2,:));
YHullVec = convhull(YMat(1,:), YMat(2,:));
XHullMat = XMat(:,XHullVec);
YHullMat = YMat(:,YHullVec);
nXHVec = size(XHullVec);
nYHVec = size(YHullVec);

for i =1:1
	if  isequal([1:1:nXHVec,nXHVec], convhull(  XHullMat(1,:), XHullMat(2,:)  ) )
		disjunkt=0;
		break;
	end
end


endfunction