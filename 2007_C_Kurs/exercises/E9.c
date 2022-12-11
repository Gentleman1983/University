#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define MAXWURF 10000000

int muenzwurf();

int main()
{
  int i;
  int wuerfe[6]={0};
  int wurfregister[2]={0};
  
  srand(time(NULL));
  
  wurfregister[1]=muenzwurf(); /*1. Wurf*/
  wuerfe[wurfregister[1]]++;
    
  printf("Starte Berechnung...\n");
  for(i=1;i<MAXWURF;i++)
  {
    wurfregister[0]=wurfregister[1];
    wurfregister[1]=muenzwurf();
    
    wuerfe[wurfregister[1]]++;
    if(wurfregister[1]==0)
    {
      if(wurfregister[0]==0)
      {
        wuerfe[2]++;
      }
      else
      {
        wuerfe[3]++;
      }
    }
    else
    {
      if(wurfregister[0]==0)
      {
        wuerfe[4]++;
      }
      else
      {
        wuerfe[5]++;
      }
    }
  }
  
  printf("\n\nGeworfene 0en (Kopf)\t\t%d\n",wuerfe[0]);
  printf("Geworfene 1en (Zahl)\t\t%d\n",wuerfe[1]);
  printf("Geworfene 00er \t\t\t%d\n",wuerfe[2]);
  printf("Geworfene 01er \t\t\t%d\n",wuerfe[3]);
  printf("Geworfene 10er \t\t\t%d\n",wuerfe[4]);
  printf("Geworfene 11er \t\t\t%d\n\n",wuerfe[5]);
  
  return 0;
}

/* Muenzwurffunktion */

int muenzwurf()
{
  return rand()%2;
}
