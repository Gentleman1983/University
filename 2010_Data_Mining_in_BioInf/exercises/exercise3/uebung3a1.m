function uebung3a1

%Einlesen der Datei EcoliRSCU
%Beinhaltet Datenmatrix Vectors (Vektoren zu jedem Gen der RSCU's) und
%Vektor Labels (Charakterisierung der Gene (pA: 1, nat:0 rib:2))

load('EcoliRSCU');

%nDim/nData Dimensionalitaet des eingelesen Datenvektors Vectors
[nDim nData] = size(Vectors);

%Darstellung des Eigenwertsspektrum der Daten als Balkendiagramme in
%absteigender Reihenfolge von links nach rechts
XdataCov = cov(Vectors',1);
%Rueckgabe der 2 groessten Eigenvektoren der Kovarianzmatrix
[V D] = eig(XdataCov);
sortEVec = sort(diag(D),'descend');
bar(sortEVec);

pause;
minGesVar = 70;

gesVar=sum(sortEVec);
tmpVar=0;

i=1;
while (tmpVar/gesVar)<(minGesVar/100)
    tmpVar = tmpVar + (sortEVec(i)); 
    i = i+1;
end


%Anzahl Eigenvektoren um mindestens die eingestellte Prozentzahl der
%Gesamtverianz zu erhalten
minAnzEVec = i;


pause;

clf;

%Hauptkomponenten
[V2 D2] = eigs(XdataCov,2);
z=V2'*Vectors;
hold on;
scatter(z(1,find(Labels==0)), z(2,find(Labels==0)), 'filled');
scatter(z(1,find(Labels==1)), z(2,find(Labels==1)), 'filled');
scatter(z(1,find(Labels==2)), z(2,find(Labels==2)), 'filled');
title('EcoliRSCU 70%');
legend('native','pA', 'PHX');
hold off;

%Und noch einmal fuer 90%

pause;

minGesVar = 90;

gesVar=sum(sortEVec);
tmpVar=0;

i=1;
while (tmpVar/gesVar)<(minGesVar/100)
    tmpVar = tmpVar + (sortEVec(i)); 
    i = i+1;
end


%Anzahl Eigenvektoren um mindestens die eingestellte Prozentzahl der
%Gesamtverianz zu erhalten
minAnzEVec = i;


pause;

clf;

%Hauptkomponenten
[V2 D2] = eigs(XdataCov,2);
z=V2'*Vectors;
hold on;
scatter(z(1,find(Labels==0)), z(2,find(Labels==0)), 'filled');
scatter(z(1,find(Labels==1)), z(2,find(Labels==1)), 'filled');
scatter(z(1,find(Labels==2)), z(2,find(Labels==2)), 'filled');
title('EcoliRSCU 90%');
legend('native','pA', 'PHX');
hold off;

pause;

%Und gleich nochmal fuer das andere Gen...

%Beinhaltet Datenmatrix Vectors (Vektoren zu jedem Gen der RSCU's) und
%Vektor Labels (Charakterisierung der Gene (pA: 1, nat:0 rib:2))

load('BsubRSCU');

%nDim/nData Dimensionalitaet des eingelesen Datenvektors Vectors
[nDim nData] = size(Vectors);

%Darstellung des Eigenwertsspektrum der Daten als Balkendiagramme in
%absteigender Reihenfolge von links nach rechts
XdataCov = cov(Vectors',1);
%Rueckgabe der 2 groessten Eigenvektoren der Kovarianzmatrix
[V D] = eig(XdataCov);
sortEVec = sort(diag(D),'descend');
bar(sortEVec);

pause;
minGesVar = 70;

gesVar=sum(sortEVec);
tmpVar=0;

i=1;
while (tmpVar/gesVar)<(minGesVar/100)
    tmpVar = tmpVar + (sortEVec(i)); 
    i = i+1;
end


%Anzahl Eigenvektoren um mindestens die eingestellte Prozentzahl der
%Gesamtverianz zu erhalten
minAnzEVec = i;


pause;

clf;

%Hauptkomponenten
[V2 D2] = eigs(XdataCov,2);
z=V2'*Vectors;
hold on;
scatter(z(1,find(Labels==0)), z(2,find(Labels==0)), 'filled');
scatter(z(1,find(Labels==1)), z(2,find(Labels==1)), 'filled');
scatter(z(1,find(Labels==2)), z(2,find(Labels==2)), 'filled');
title('BsubRSCU 70%');
legend('native','pA', 'PHX');
hold off;

%Und noch einmal fuer 90%

pause;

minGesVar = 90;

gesVar=sum(sortEVec);
tmpVar=0;

i=1;
while (tmpVar/gesVar)<(minGesVar/100)
    tmpVar = tmpVar + (sortEVec(i)); 
    i = i+1;
end


%Anzahl Eigenvektoren um mindestens die eingestellte Prozentzahl der
%Gesamtverianz zu erhalten
minAnzEVec = i;


pause;

clf;

%Hauptkomponenten
[V2 D2] = eigs(XdataCov,2);
z=V2'*Vectors;
hold on;
scatter(z(1,find(Labels==0)), z(2,find(Labels==0)), 'filled');
scatter(z(1,find(Labels==1)), z(2,find(Labels==1)), 'filled');
scatter(z(1,find(Labels==2)), z(2,find(Labels==2)), 'filled');
title('BsubRSCU 90%');
legend('native','pA', 'PHX');
hold off;

end