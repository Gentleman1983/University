function u2a2plotall()
    
load('Hidden1');

for dimA=1:10,
    for dimB=dimA+1:10,
        subplot(10,10,dimA*10+dimB); scatter(Xdata(dimA,:),Xdata(dimB,:))
        i=dimA+dimB*10;
        title(i);
    end
end
end