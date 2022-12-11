#include <stdio.h>
#define SZMAX 10

int main()
{
  float mat1[SZMAX][SZMAX]={{0}}, mat2[SZMAX][SZMAX]={{0}}, erg[SZMAX][SZMAX]={{0}};
  int zeile1=0, spalte1=0, zeile2=0, spalte2=0, zeileerg=0, spalteerg=0, z, s, i;
  
  do /*Eingabedialog Format*/
  {
    printf("Bitte geben Sie die Zahl der Zeilen der ersten Matrize ein!\n(Maximum sind %d)\n=> ",SZMAX);
    scanf("%d",&zeile1);
  }
  while((zeile1<=0) || (zeile1>SZMAX));
  do
  {
    printf("Bitte geben Sie die Zahl der Spalten der ersten Matrize ein!\n(Maximum sind %d)\n=> ",SZMAX);
    scanf("%d",&spalte1);
  }
  while((spalte1<=0) || (spalte1>SZMAX));
  printf("Fuer Matrix 1 wurde als Format eine %d-Zeilen- und %d-Spalten Matrix definiert.\n",zeile1,spalte1);
  spalte2=zeile1;
  printf("Daraus ergibt sich fuer die zweite Matrix eine Spaltenzahl von %d.\n\n",spalte2);
  do
  {
    printf("Bitte Zeilenzahl der zweiten Matrix eingeben!\n(Maximum sind %d)\n=> ",SZMAX);
    scanf("%d",&zeile2);
  }
  while((zeile2<=0) || (zeile2>SZMAX));
  
  zeileerg=zeile1;
  spalteerg=spalte2;
  
  for(z=0;z<zeile1;z++) /*Eingabedialog Werte*/
  {
    for(s=0;s<spalte1;s++)
    {
      printf("Bitte geben Sie den Wert fuer Matrix1 Spalte %d, Zeile %d ein.\n=> ",(s+1),(z+1));
      scanf("%f",&mat1[s][z]);
    }
  }
  
  for(z=0;z<zeile2;z++)
  {
    for(s=0;s<spalte2;s++)
    {
      printf("Bitte geben Sie den Wert fuer Matrix2 Spalte %d, Zeile %d ein.\n=> ",(s+1),(z+1));
      scanf("%f",&mat2[s][z]);
    }
  }
  
  for(z=0;z<zeileerg;z++) /*Matrixmultiplikation*/
  {
    for(s=0;s<spalteerg;s++)
    {
      for(i=0;i<spalte1;i++)
      {
        erg[s][z]+=mat1[i][z]*mat2[s][i];
      }
    }
  }
  
  for(z=0;z<zeileerg;z++) /*Ergebnisausgabe*/
  {
    printf("(  ");
    for(s=0;s<spalteerg;s++)
    {
      printf("%f\t",erg[s][z]);  
    }
    printf(")\n");
  }
  
  return 0;
}

