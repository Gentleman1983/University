function loadings(DataMat,V)
load('MSLabels');
%Labelstrings
LabelCell;
%ID's
IDvec;
%MmatData: Column 1 marker candidate ID = IDvec

%Visualisierung der Loadings der ersten beiden Hauptkomponenten
figure
plot(V(:,1), V(:,2),'o');
for i=1:size(IDvec);
    pos = find(DataMat(:,1) == IDvec(i));
    axx(i)=V(pos,1); 
    axy(i)= V(pos,2);
end
    [arrowx,arrowy] = dsxy2figxy(gca, axx', axy');
for i = 1:size(IDvec)
    annotation('textarrow',[arrowx(i)'+0.1,arrowx(i)'],[arrowy(i)'+0.1,arrowy(i)'], 'String' , LabelCell(i));
end

title('Sample PCA loadings');
end