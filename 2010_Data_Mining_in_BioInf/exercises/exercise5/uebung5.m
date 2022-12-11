%{Blatt5%}
%Aufgabe 1

load('Hidden1');
[nDim nData] = size(Xdata);

XDataDistMat = repeatmat(diag(Xdata'*Xdata), 1, nData);

P=rand(2,nData);

P=sammon(Xdata',P');
%P = sammon(Xdata',P',100,'steps',0.2,XDataDistMat);

figure;
scatter(P(:,1),P(:,2),'filled');


