#include <stdio.h>
#include <stdlib.h>

double * vektorallocate(int size);
double ** matrixallocate(int col, int row);
void killmatrix(double ** mat);
void killvektor(double * vek);
