clear

load("SeqMats.mat");

%c = ['A' 'C' 'G'; 'T' 'A' 'C'];
%[c == 'A' c=='C' c=='G' c=='T']'

codeMat = encodeMat(SeqPosMat);
