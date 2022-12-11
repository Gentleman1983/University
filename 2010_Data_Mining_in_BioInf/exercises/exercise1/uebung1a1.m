function worked=uebung1a1()
    %{Blatt 1: Aufgabe 1%}

    %{Matrix A anlegen%}
    AMat = [3 -2; -4 1];

    %{Eigenwerte von A bestimmen%}
    EWVec=eig(AMat);

    %{Bestimmen der Eigenwertmatrix und Diagonalmatrix zu A%}
    [EVMat,DAMat]=eig(AMat);
    
    worked=true;
end
