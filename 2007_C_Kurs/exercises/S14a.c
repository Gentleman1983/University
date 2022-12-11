#include <stdio.h>
#include <math.h>
#define GRENZE 1000

int main()
{
  int n;
  
  for(n=2;n<GRENZE;n++)
  {
    int k, chk=0;
    for(k=2;k<=sqrt(n);k++)
    {
      if(n%k==0)
      {
        chk=1;
      }
    }
    if(chk==0)
    {
      printf("%d ist eine Primzahl!\n",n); 
    }
  }
  
  return 0;
}
