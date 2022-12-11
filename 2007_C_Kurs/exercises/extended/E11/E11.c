#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main()
{
  int ite=0, fertig=9, i;
  int permutation[9]={0};
  
  srand(time(NULL));
  
  for(i=0;fertig>0;ite++)
  {
    int tmp=(rand()%9)+1;
    if(permutation[0]!=tmp&&permutation[1]!=tmp&&permutation[2]!=tmp&&permutation[3]!=tmp&&permutation[4]!=tmp&&permutation[5]!=tmp&&permutation[6]!=tmp&&permutation[7]!=tmp&&permutation[8]!=tmp)
    {
      permutation[i]=tmp;
      i++;
      fertig--;
    }
  }
  
  printf("( 1 2 3 4 5 6 7 8 9 )\n");
  printf("( %d %d %d %d %d %d %d %d %d )\n\n",permutation[0],permutation[1],permutation[2],permutation[3],permutation[4],permutation[5],permutation[6],permutation[7],permutation[8]);
  printf("Anzahl Berechnungsschritte:\t\t%d\n",ite);
  
  return 0;
}
