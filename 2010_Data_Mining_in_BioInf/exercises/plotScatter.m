function [V,D]=plotScatter(DataMat,OrgMat)
[nDim nData] = size(OrgMat);
MdataCov = cov(DataMat',1);

[V D] = eigs(MdataCov,2);
z=V'*OrgMat;

figure
%Hauptkomponenten-Repraesentation
hold on
i = 1;

while i<nData
    scatter(z(1,i:i+8)', z(2,i:i+8)','filled');
    i = i+9;
end

hold off
legend('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h');
end