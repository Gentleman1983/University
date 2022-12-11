%{Blatt 4}%

%Einlesen der Datenquelle
MmatData = csvread('1748-7188-3-9-s3.csv');

%{Table of marker candidates used in the case study. The data file
% 1748-7188-3-9-s3.csv contains the marker candidates used for clustering 
% and visualization. Rows correspond to particular marker candidates. 
% The first column corresponds to marker candidate ID, the second and third
% column represent cluster ID and block ID according to table 2, respectively.
% The block IDs A, B, C, D, E and F are encoded by integers 1,..., 6. Columns
% 4 and 5 correspond to experimental nominal mass (m/z) and retention time
% (minutes), respectively. Columns 6 to 77 contain intensity values from mass
% spectrometry measurements. Here, nine successive values correspond to replicas
% of a particular experimental condition (see section "Case study for experimental
% evaluation"). The intensity values are ordered according to successive replicas
% for each condition (order of conditions according to table 1).%}

%{Aufgabe 2-3%}
% Relevanten Datenbereich in passende Datenmatrix DMat sichern.
Mmat = MmatData(:,6:77);
[nDim nData] = size(Mmat);
meanMmat = mean(Mmat,2);
MeanMatM = repmat(meanMmat,1, nData);

%%%%% Normalisierung der Spalten 
% euklidische Norm
%MmatNorm = Mmat./repmat(sqrt(sum((Mmat.^2),1)),nDim,1);
% Manhattennorm
%MmatNorm = Mmat./repmat(sum(abs(Mmat'),2),1,nDim)';
% 
% 
%%%%% Normalisierung der Zeilen
% euklidische Norm
%MmatNorm = Mmat./repmat(sqrt(sum((Mmat.^2),2)),1,nData);
% Manhattennorm
MmatNorm = Mmat./repmat((sum((abs(Mmat)),2)),1, nData);
%
%%%%% Normierung entfallen lassen
%MmatNorm = Mmat;
% 
%%%%% Mittelwertszentrierung der Matrix Mmat
%Mmat = Mmat-MeanMatM;

%Darstellung des Eigenwertsspektrum der Daten als Balkendiagramme in
%absteigender Reihenfolge von links nach rechts
MdataCov = cov(MmatNorm',1);
%Rueckgabe der 2 groessten Eigenvektoren der Kovarianzmatrix
[V D] = eig(MdataCov);
sortEVec = sort(diag(D),'descend');
bar(sortEVec);
title('PCA EW spectrum')

%und nochmal fuer die ersten 20 Eigentvektoren (bessere Uebersicht)
figure;
bar(sortEVec(1:20));
title('PCA EW spectrum, Top 20 EV')

[V2 D2] = eigs(MdataCov,2);
z=V2'*Mmat;

figure
%Hauptkomponenten-Repraesentation
hold on
i = 1;

while i<nData
    scatter(z(1,i:i+8)', z(2,i:i+8)','filled');
    i = i+9;
end

hold off
legend('wt, 0h', 'wt, 0.5 hpw', 'wt, 2hpw', 'wt, 5hpw', 'dde 2-2, 0h', 'dde 2-2, 0.5hpw', 'dde 2-2, 2hpw', 'dde 2-2, 5hpw');
title('Sample PCA')



%Ploten der Loadings
load('MSLabels');
%Labelstringsload('MSLabels');
%Labelstrings
LabelCell;
%ID's
IDvec;

LabelCell;
%ID's
IDvec;
%MmatData: Column 1 marker candidate ID = IDvec

%Visualisierung der Loadings der ersten beiden Hauptkomponenten
figure
plot(V2(:,1), V2(:,2),'.');
for i=1:size(IDvec);
    pos = find(MmatData(:,1) == IDvec(i));
    axx(i)=V2(pos,1); 
    axy(i)= V2(pos,2);
end
    [arrowx,arrowy] = dsxy2figxy(gca, axx', axy');
for i = 1:size(IDvec)
    annotation('textarrow',[arrowx(i)'+0.1,arrowx(i)'],[arrowy(i)'+0.1,arrowy(i)'], 'String' , LabelCell(i));
end
title('Sample PCA loadings');

%Intensitätsprofile
figure

%%%%% Manhattan
%LogiVec = V2(:,1) >= -0.017 & V2(:,1) <= -0.0014;
%LogiVec = LogiVec & V2(:,2)>= -0.03846 & V2(:,2)<=-0.005339;

%%%%% Euklid
%LogiVec = V2(:,1) >= -0.062 & V2(:,1) <= -0.027;
%LogiVec = LogiVec & V2(:,2)>= 0.019 & V2(:,2)<=0.062;

%LogiVec = V2(:,1) >= -0.0505 & V2(:,1) <= -0.0100;
%LogiVec = LogiVec & V2(:,2)>= -0.00765 & V2(:,2)<=-0.00531;

%LogiVec = V2(:,1) >= -0.0356 & V2(:,1) <= -0.0024;
%LogiVec = LogiVec & V2(:,2)>= -0.054 & V2(:,2)<=-0.00865;

clear NewDataMean;
NewData = MmatData(LogiVec,6:77)';
[nDimND nDataND] = size(NewData);
i = 1;
j = 1;
while i<nDimND;
    NewDataMean(j,:) = mean(NewData(i:i+8,:)',2);
    i = i+9;
    j = j+1;
end


NewDataMeanNorm = NewDataMean./repmat(sqrt(sum((NewDataMean.^2),1)),8, 1);
imagesc(NewDataMeanNorm)
