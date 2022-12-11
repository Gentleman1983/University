#include <stdio.h>

int main()
{
  unsigned int eingabe, qsum=0;
  printf("Bitte eine nichtnegative Ganzzahl eingeben...\n\n=> ");
  scanf("%u", &eingabe);
  
  while(eingabe!=0)
  {
    qsum += eingabe%10;
    eingabe /= 10;
  }
  
  printf("Die Quersumme betraegt:   %u\n\n",qsum);
  
  return 0;
}
