function uebung2a2()

%{Aufgabe 2a}%

hold off

load('Hidden1');

for dim=1:10,
    for scale=1:20,
        hist(Xdata(dim,:), scale*10);
        pause(0.1);
    end
    pause;
end

load('Hidden2');

for dim=1:10,
    for scale=1:20,
        hist(Xdata(dim,:), scale*10);
        pause(0.1);
    end
    pause;
end

pause;

%{Aufgabe 2b%}

u2a2plotall();

pause;

%{Aufgabe2c%}

load('Hidden1');

CMat=cov(Xdata',1);
EWVec=eig(CMat);
bar(sort(EWVec,'descend'));

pause;

%{Aufgabe2d%}

load('Hidden1');

CMat=cov(Xdata',1);
[EVMat,DMat]=eig(CMat);

UMat=EVMat(:,9:10);
scatter(test(2,:),test(1,:));

pause;

end
