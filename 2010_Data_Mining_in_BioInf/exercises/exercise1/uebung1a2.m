function worked=uebung1a2()
    %{Blatt 1: Aufgabe 2%}
    %{Dimension und Anzahl der Vektoren speichern%}
    nVecs=100;
    nDims=2;

    %{Zufaellige Matrix anlegen%}
    XdataMat=rand(nDims,nVecs);
    %{Datenpunkte plotten%}
    plot(XdataMat(1,:),XdataMat(2,:),'.')
    %{3. Parameter in Aposthrophs gibt kodierung der Verbindungsart an. Sind properties des Plot-Befehls.%}
    
    hold off
    %pause 5.0

    %{Mittelwertsvektor erstellen%}
    MeanVec=mean(XdataMat,2);
    %{gemittelte Matrix anlegen und Werte korrigieren%}
    XdataMeanMat=XdataMat;
    for i=1:nVecs,
        XdataMeanMat(:,i)=XdataMeanMat(:,i)-MeanVec;
    end
    %{Zentrierung um den Nullpunkt verifizieren%}
    mean(XdataMeanMat,2)
    %{Zentriertes DataSet plotten%}
    plot(XdataMeanMat(1,:),XdataMeanMat(2,:),'.')
    
    hold off
    %pause 5.0

    %{Matrix um alpha rotieren, o.B.d.A. hier mal 45 Grad gewaehlt%}
    RotatedMat = rotateMatrix(XdataMeanMat,pi/4,nVecs);
    %{Rotierte Matrix ploten%}
    plot(RotatedMat(1,:),RotatedMat(2,:),'.')
    
    hold on
    %pause 5.0

    %{berechne Varianz in der 1. Dimension aller Datenpunkte. Dimension wird ueber den letzten Parameter spezifiziert, Abschaetzung mit 1/n ueber den 2.%}
    variance=var(RotatedMat');
    
    %pause 5.0
    
    %{Berechne Varianzenvektor fuer alle 360 Rotationswinkel%}
    VarianceVec=zeros(1,360);
    %{maxVar enthaelt in Spalte 1 das aktuelle Varianzmaximum und in Spalte 2 den korrespondierenden Winkel%}
    maxVar=[-1 -1];
    for alpha=1:360,
        RotatedByAlphaMat=rotateMatrix(XdataMeanMat,pi*alpha/180,nVecs);
        VarianceVec(alpha)=var(RotatedByAlphaMat(1,:)');
        if maxVar(1)<VarianceVec(alpha)
            maxVar(1)=VarianceVec(alpha);
            maxVar(2)=alpha;
        end
    end
    
    %{Hilfsvektor fuer Plot anlegen%}
    VvPlotHelpVec=zeros(1,360);
    for alpha=1:360,
        VvPlotHelpVec(alpha)=alpha;
    end
    
    %{Und plotten%}
    plot(VvPlotHelpVec(:),VarianceVec(:),'.')
    
    hold off
    %pause 5.0
    
    %{Nun nochmal das ganze mit verschiedenen groessen des Zufallszahlenpools%}
    hold off
    maxRuns=10;
    maxRVar=zeros(maxRuns,2);
    for runs=1:maxRuns
        nVec=10^runs;
        nDims=2;
        
    	for dimruns=1:10,
		XdataRMat=rand(nDims,nVecs);
	    	MeanRVec=mean(XdataRMat,2);
	    	XdataMeanRMat=XdataRMat;
	    	for i=1:nVecs,
	        	XdataMeanRMat(:,i)=XdataMeanRMat(:,i)-MeanRVec;
	    	end
	    	
		VarianceRVec=zeros(1,360);
	    	maxRVar(runs,:)=[-1 -1];
	    	for alpha=1:360,
	        	RotatedByAlphaRMat=rotateMatrix(XdataMeanRMat,pi*alpha/180,nVecs);
	        	VarianceRVec(alpha)=var(RotatedByAlphaRMat(1,:)');
	        	if maxRVar(runs,1)<VarianceRVec(alpha)
				maxRVar(runs,1)=VarianceRVec(alpha);
				maxRVar(runs,2)=alpha;
	   		end
		end
	    
	    	VvPlotHelpRVec=zeros(1,360);
	    	for alpha=1:360,
	        	VvPlotHelpRVec(alpha)=alpha;
	    	end
	    
	    	scatter(VvPlotHelpRVec(:),VarianceRVec(:))
		hold on   
		pause(0.1) 
		
	    end
	hold off
    	pause(1.5)
end
    
    worked = true;
end
