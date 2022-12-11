#include <stdio.h>

unsigned int quersumme(unsigned int eingabe);

int main()
{
  unsigned int eingabe, qsum=0;
  printf("Bitte eine nichtnegative Ganzzahl eingeben...\n\n=> ");
  scanf("%u", &eingabe);
  
  qsum=quersumme(eingabe);
  
  printf("Die Quersumme betraegt:   %u\n\n",qsum);
  
  return 0;
}

/*Quersumme berechnen*/

unsigned int quersumme(unsigned int eingabe)
{
  if(eingabe==0)
  {
    return 0;
  }
  else
  {
    return eingabe%10 + quersumme(eingabe/10);
  }
}
