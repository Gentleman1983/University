function worked=uebung2a1()
    %{Blatt 2: Aufgabe 1%}
    
    hold off
    
    nDims=2;
    nVecs=100;
    numberOfRuns=100
    
    %{Lege temporaere Ergebnismatrizen fuer die einzelnen Teilaufgaben
    %an.%}
    
    VarianceVec=zeros(numberOfRuns,2);
    AngleVec=zeros(numberOfRuns,2);
    
    %{berechne anhand von blatt 1 aufgabe 3 entsprechend 100x die Werte%}
    
    for i=1:numberOfRuns,
        [VarianceVec(i,1),AngleVec(i,1)]=uebung1a3();
    end
    
    %{Plotten des 1. Teilergebnisses%}
    scatter(AngleVec(:,1),VarianceVec(:,1));
    hold on

    %{Matrix TransMat anlegen anlegen%}
    TransMat = [0.25 1.299; -0.433 0.75];
    for i=1:numberOfRuns,
        %{Zufaellige Matrix mit TransMat erzeugen%}
        XDataRMat=TransMat*rand(nDims, nVecs);
    
        CovRMat=cov(XDataRMat',1);
    
        maxVariance=-1;
        maxVarianceAngle=-1;
        for alphaAngle = 1:360,
            DirVec=[sin(alphaAngle*pi/180);cos(alphaAngle*pi/180)];
            currentVariance=DirVec'*CovRMat*DirVec;
            if(currentVariance>maxVariance)
                maxVariance=currentVariance;
                maxVarianceAngle=alphaAngle;
            end
        end
        VarianceVec(i,2)=maxVariance;
        AngleVec(i,2)=maxVarianceAngle;
    end
    
    pause(0.5)
    
    %{2. Teilergebnis plotten%}
    scatter(AngleVec(:,2),VarianceVec(:,2));
    hold off
    
    pause(5.0)
    
    %{Teil 1b%}
    
    nDims=2;
    nVecs=100;
    TransMat = [1 0; 0 1];
    %TransMat = [0.25 1.299; -0.433 0.75];
    XDataRMat=TransMat*rand(nDims, nVecs);
    
    v=rand(nDims,1);
    v=v/norm(v);
    for i=1:120,
        w=CovMat*v;
        scatter(w(1),w(2));
        pause(0.1);
        
        hold on
        
        v=w/norm(w);
    end
    hold off
    
    worked=true;
end