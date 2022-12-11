#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define MAXWURF 1000000

int gezinkterwurf();

int main()
{
  int i;
  int wuerfe[6]={0};
  
  srand(time(NULL));
  
  printf("Starte Berechnung...\n\n\n");
  for(i=1;i<MAXWURF;i++)
  {
    wuerfe[gezinkterwurf()]++;
  }
  
  for(i=0;i<6;i++)
  {
    printf("%d:\t\t%d\n",(i+1),wuerfe[i]);
  }
  
  return 0;
}

/* Wuerfelfunktion */

int gezinkterwurf()
{
  int wurf=rand()%7;
  return wurf<6?wurf:5;
}
