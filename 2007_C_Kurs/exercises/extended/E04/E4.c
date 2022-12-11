#include <stdio.h>

int main()
{
  int i;
  
  printf("OCT\tDEC\tHEX\tCHAR\tOCT\tDEC\tHEX\tCHAR\t\n");
  printf("-----------------------------------------------------------------\n");
  for(i=0;i<=63;i++)
  {
    printf("%3o\t%3d\t%3x\t%3c\t%3o\t%3d\t%3x\t%3c\t\n",i,i,i,i,64+i,64+i,64+i,64+i);
  }
  
  return 0;
}
