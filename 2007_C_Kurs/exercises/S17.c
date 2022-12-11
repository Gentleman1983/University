#include <stdio.h>

int sjahr(unsigned int jahr);

int main()
{
  unsigned int jahr=0;
  
  printf("Bitte Jahr eingeben:\n=> ");
  scanf("%u",&jahr);
  
  if(sjahr(jahr))
  {
    printf("%u ist ein Schaltjahr\n",jahr);
  }
  else
  {
    printf("%u ist kein Schaltjahr\n",jahr);
  }
  
  return 0;
}

/*Berechnungsalgorithmus zur Berechnung auf Schaltjahre*/

int sjahr(unsigned int jahr)
{
  if(jahr%4!=0)
  {
    return 0;
  }
  else if(jahr%400==0)
  {
    return 1;
  }
  else if(jahr%100==0)
  {
    return 0;
  }
  else
  {
    return 1;
  }
}
