%Einlesen der Datei EcoliRSCU
%Beinhaltet Datenmatrix Vectors (Vektoren zu jedem Gen der RSCU's) und
%Vektor Labels (Charakterisierung der Gene (pA: 1, nat:0 rib:2))
load('EcoliRSCU');

%Berechnen der Cityblock-Abstaende 
%Die Manhattan-Metrik ist eine Metrik, in der die Distanz zwischen zwei 
%Punkten als die Summe der absoluten Differenzen ihrer Einzelkoordinaten 
%definiert wird. d(a,b) = sum(abs(a-b))
%Dmat Distanzmatrix aller wechselseitigen Cityblock-Abstaende der
%Datenmatrix Vectors


[nDim nData] = size(Vectors);
Dmat=zeros(nData,nData);

%Laufvariablen i & j
for i = 1:nData
    for j = 1:nData
        Dmat(i,j) = sum(abs(Vectors(:,i)-Vectors(:,j)));
    end
end

%Dmat --> Gmat
%g_{ij}= -0.5(d_{ij}^2 - 1/N sum_{k=1}^{N}(d_{ik}^2 - 1/N sum_{k=1}^{N}(d_{kj}^2)+ 1/N^2 sum_{k}sum_(l)d_{kl}^2))
%g_{ij}= -0.5(d_{ij}^2 -Zeilenmittel - Spaltenmittel + Gesamtmittel)
%d_{ij}^2 = Dmat.^2
Dmat = Dmat.^2;

DmatMeanVec = mean(Dmat);

%Anlegen einer Matrix mit den Zeilen- und Spaltenmitteln
%Spaltenmittel
SmeanMat = repmat(DmatMeanVec,nData,1);
%Zeilenmittel = SmeanMat'
%Gesamtmittel
gMean = mean(mean(Dmat));

Gmat=-0.5*(Dmat-SmeanMat'-SmeanMat+gMean);

clear SmeanMat;

%Multidimensionale Skalierung

[V2 D2] = eigs(Gmat,2);
z=V2'*Gmat;

%Hauptkomponenten-Repraesentation
hold
scatter(z(1,find(Labels==0)), z(2,find(Labels==0)), 'filled');
scatter(z(1,find(Labels==1)), z(2,find(Labels==1)), 'filled');
scatter(z(1,find(Labels==2)), z(2,find(Labels==2)), 'filled');
title('EcoliRSCU');
legend('native','pA', 'PHX');
hold off;

pause;

%und gleich wieder für den anderen Gen-Stamm
load('BsubRSCU');

[nDim nData] = size(Vectors);
Dmat=zeros(nData,nData);

%Laufvariablen i & j
for i = 1:nData
    for j = 1:nData
        Dmat(i,j) = sum(abs(Vectors(:,i)-Vectors(:,j)));
    end
end

%Dmat --> Gmat
%g_{ij}= -0.5(d_{ij}^2 - 1/N sum_{k=1}^{N}(d_{ik}^2 - 1/N sum_{k=1}^{N}(d_{kj}^2)+ 1/N^2 sum_{k}sum_(l)d_{kl}^2))
%g_{ij}= -0.5(d_{ij}^2 -Zeilenmittel - Spaltenmittel + Gesamtmittel)
%d_{ij}^2 = Dmat.^2
Dmat = Dmat.^2;

DmatMeanVec = mean(Dmat);

%Anlegen einer Matrix mit den Zeilen- und Spaltenmitteln
%Spaltenmittel
SmeanMat = repmat(DmatMeanVec,nData,1);
%Zeilenmittel = SmeanMat'
%Gesamtmittel
gMean = mean(mean(Dmat));

Gmat=-0.5*(Dmat-SmeanMat'-SmeanMat+gMean);

clear SmeanMat;

%Multidimensionale Skalierung

[V2 D2] = eigs(Gmat,2);
z=V2'*Gmat;

%Hauptkomponenten-Repraesentation
hold
scatter(z(1,find(Labels==0)), z(2,find(Labels==0)), 'filled');
scatter(z(1,find(Labels==1)), z(2,find(Labels==1)), 'filled');
scatter(z(1,find(Labels==2)), z(2,find(Labels==2)), 'filled');
title('BsubRSCU');
legend('native','pA', 'PHX');
hold off;