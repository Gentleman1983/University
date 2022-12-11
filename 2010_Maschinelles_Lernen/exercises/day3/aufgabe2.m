clear
clf

runVec = [];
errorVec = [];

stepWidth = 0.01;

for etaPar = stepWidth:stepWidth:1

	[nErrors nRuns] = trainTest(etaPar, 10);

	runVec = [runVec nRuns];
	errorVec = [errorVec nErrors];

	printf('Eta: %f\t Errors: %f\t Iterationen: %f\n', etaPar, nErrors, nRuns);

end

plot([stepWidth:stepWidth:1], runVec, 'b')
hold on
plot([stepWidth:stepWidth:1], errorVec / 10, 'r')
hold off