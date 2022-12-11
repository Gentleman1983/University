function [variance,maxVarianceAngle]=uebung1a3()
    %{Blatt 1: Aufgabe 3%}
    
    nDims=2;
    nVecs=100;

    %{Matrix A anlegen%}
    XDataMat = rand(nDims, nVecs);
    
    CovMat=cov(XDataMat',1);
    
    maxVariance=-1;
    maxVarianceAngle=-1;
    for alphaAngle = 1:360,
        DirVec=[sin(alphaAngle*pi/180);cos(alphaAngle*pi/180)];
        currentVariance=DirVec'*CovMat*DirVec;
        if(currentVariance>maxVariance)
            maxVariance=currentVariance;
            maxVarianceAngle=alphaAngle;
        end
    end
    
    variance=maxVariance;
end
