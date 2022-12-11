#include <stdio.h>
#define CURR_YEAR 2007

int main()
{
  unsigned int jahr=0;
  
  printf("Einen wunderschoenen guten Tag! Bitte geben Sie ein Jahr ein!\n=> ");
  scanf("%u",&jahr);
    
  if((jahr%4)==0)
  {
    if(jahr>CURR_YEAR)
    {
      printf("%u werden Olympische Sommerspiele stattfinden.\n",jahr);
    }
    else if(jahr<CURR_YEAR)
    {
      printf("%u fanden Olympische Sommerspiele statt.\n",jahr);
    }
    else
    {
      printf("%u finden Olympische Sommerspiele statt.\n",jahr);
    }
  }
  else if(((jahr+2)%4)==0)
  {
    if(jahr>CURR_YEAR)
    {
      printf("%u werden Olympische Winterspiele stattfinden.\n",jahr);
    }
    else if(jahr<CURR_YEAR)
    {
      printf("%u fanden Olympische Winterspiele statt.\n",jahr);
    }
    else
    {
      printf("%u finden Olympische Winterspiele statt.\n",jahr);
    }
  }
  else
  {
    if(jahr>CURR_YEAR)
    {
      printf("%u werden keine Olympischen Spiele stattfinden.\n",jahr);
    }
    else if(jahr<CURR_YEAR)
    {
      printf("%u fanden keine Olympischen Spiele statt.\n",jahr);
    }
    else
    {
      printf("%u finden keine Olympischen Spiele statt.\n",jahr);
    }
  }
    
  return 0;
}
