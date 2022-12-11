#include <stdio.h>
#include <math.h>

int main()
{
  float zahl=-1.0, wurzel=0.0;
  
  printf("Bitte geben Sie eine beliebige positive Float-Zahl ein (z.B. 1.234)\n=> ");
  while(zahl<0)
  {
    scanf("%f",&zahl);
    printf("=> ");
  }
  
  wurzel=sqrt(zahl);
  
  printf("Raten Sie den Wert der Quadratwurzel!\n");
  do
  {
    printf("=> ");
    scanf("%f",&zahl);
    if(zahl>wurzel)
    {
      printf("Kleiner! Du bist zu hoch! Naechster Versuch\n!");
    }
    else if(zahl<wurzel)
    {
      printf("Groesser! Du bist zu niedrig! Naechster Versuch\n!");
    }
    else
    {
      printf("Richtig! Die Wurzel lautet %f!!!",wurzel);
    }
  }
  while(zahl!=wurzel); 
  
  return 0;    
}
