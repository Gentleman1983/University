#include <stdio.h>
#define GRENZE 1000

int main()
{
  int n,i,inc=0;
  int feld[GRENZE]={0};
  
  for(n=2;n<GRENZE;n++)
  {
    if(feld[n]==0)
    {
      int j;
      for(j=2;j*n<=GRENZE;j++)
      {
        feld[n*j]++;
	inc++;
      }
    }
  }
  for(i=1;i<GRENZE;i++)
  {
    if(feld[i]==0)
    {
      printf("%d ist eine Primzahl.\n",i);
    }
  }
  printf("\n%d Werte gestrichen.\n",inc);
  
  return 0;
}
