#include "matmult.h"

double** matmult(double** mat1, double** mat2, int spalte1, int spalteerg, int zeileerg)
{
  int s,z,i;
  double **erg=matrixallocate(spalteerg,zeileerg);
  
  for(z=0;z<zeileerg;z++) /*Matrixmultiplikation*/
  {
    for(s=0;s<spalteerg;s++)
    {
      for(i=0;i<spalte1;i++)
      {
        if(i==0)
        {
          erg[s][z]=0;      
        }
        
        erg[s][z]+=mat1[i][z]*mat2[s][i];
      }
    }
  }
  
  return erg;
}
