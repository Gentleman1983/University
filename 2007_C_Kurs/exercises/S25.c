
  #include "matmem.h"

  #include "matmult.h"

  #include <stdio.h>


int main()
{
  int s1, z1, s2, z2, es, ez, s, z;
  double **m1, **m2, **e;
  
  do
  {
    printf("Bitte geben Sie die Dimensionen der Matrizen ein:\n(SpaltenMat1 ZeilenMat1 SpaltenMat2 ZeilenMat2)\n=> ");
    scanf(" %d %d %d %d",&s1,&z1,&s2,&z2);
  }
  while(s1==0||s2==0||z1==0||z2==0||s1!=z2);
  
  m1=matrixallocate(s1,z1); /*Zuweisung der Matrizen*/
  m2=matrixallocate(s2,z2);
  
  for(z=0;z<z1;z++) /*Eingabedialog Werte*/
  {
    for(s=0;s<s1;s++)
    {
      printf("Bitte geben Sie den Wert fuer Matrix1 Spalte %d, Zeile %d ein.\n=> ",(s+1),(z+1));
      scanf("%lf",&m1[s][z]);
    }
  }
  
  for(z=0;z<z2;z++)
  {
    for(s=0;s<s2;s++)
    {
      printf("Bitte geben Sie den Wert fuer Matrix2 Spalte %d, Zeile %d ein.\n=> ",(s+1),(z+1));
      scanf("%lf",&m2[s][z]);
    }
  }
  
  es=s2; /*Anpassung der Dimension der Ergebnismatrix*/
  ez=z1;
  
  e=matmult(m1,m2,s1,es,ez); /*Matrixmultiplikation*/
  
  for(z=0;z<ez;z++) /*Ergebnisausgabe*/
  {
    printf("(  ");
    for(s=0;s<es;s++)
    {
      printf("%f\t",e[s][z]);  
    }
    printf(")\n");
  }
  
  return 0;
}
