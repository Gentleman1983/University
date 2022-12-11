#include "matmem.h"

double * vektorallocate(int size)
{
  double * vek;
  vek = (double*) malloc(sizeof(double)*size);
  
  if(vek==NULL)
  {
    printf("Nicht genügend Speicher zur Ausführung vorhanden!\n");
    return NULL;
  }
  
  return vek;
}

double** matrixallocate(int row, int col)
{
  int i;
  double ** mat, * vek;
  
  mat=(double**)malloc(row*sizeof(double*));
  vek=vektorallocate(row*col);
  
  if(mat==NULL||vek==NULL)
  {
    printf("Nicht genügend Speicher zur Ausführung vorhanden!\n");
    
    free(vek);
    free(mat);
    
    return NULL;
  }
  
  for(i=0;i<row;i++)
  {
    mat[i]=vek+i*col;
  }
  
  return mat;
}

void killmatrix(double ** mat)
{
  free(mat[0]);
  free(mat);     
}

void killvektor(double * vek)
{
  free(vek); 
}
