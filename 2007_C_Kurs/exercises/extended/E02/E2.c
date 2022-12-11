#include <stdio.h>

int main()
{
  int header=0, spalte=1, zeile=1, smax=10, zmax=10; 
  printf(" * |"); /*Tabellenheader erstellen*/
  while(spalte <= smax)
  {
    printf(" %3d",spalte);
    spalte++;
  }
  printf("\n");
  
  while(header < (smax*4+5))
  {
    printf("-");
    header++;
  }
  printf("\n");
  
  spalte=1; /*Reset der Variable spalte*/
  
  while(zeile<=zmax) /*Erstellung der Einträge*/
  {
    printf("%2d |",zeile);
    
    while(spalte<=smax)
    {
      printf(" %3d",(spalte*zeile));
      spalte++;
    }
    spalte=1;
    printf("\n");
    zeile++;
  }
  
  return 0;
}
