#include <stdio.h>
#define FORMAT 12

unsigned long ggt(unsigned long a, unsigned long b);

int main()
{
  unsigned long i,j;
  for(i=1;i<=FORMAT;i++)
  {
    for(j=1;j<=FORMAT;j++)
    {
      printf("%3lu ",ggt(i,j));
    }
    printf("\n");
  }
  
  return 0;
}

/* GGT-Funktion */

unsigned long ggt(unsigned long a, unsigned long b)
{
  if(b==0)
  {
    return a;
  }
  else
  {
    return ggt(b, a%b);
  }
}
