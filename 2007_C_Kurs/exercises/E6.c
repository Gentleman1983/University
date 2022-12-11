#include <stdio.h>
#include <stdlib.h>

int main(int cnt, char* EventArgs[])
{
  int i=2;
  int zahl= (int) atoi(EventArgs[1]);
   
  while(zahl!=1)
  {
    if((zahl%i)==0)
    {
      zahl/=i;
      printf("%d ",i);
    }
    else
    {
      i++;
    }
  }
  
  printf("\n");
  
  return 0;
}
