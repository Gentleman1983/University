#include <stdio.h>

int main()
{
  int eingabe[9]={0};
  int chk=0;
  
  printf("Bitte geben Sie eine Permatation der Zahlen 1 bis 9 an:\n(Format: 1,2,3,4,5,6,7,8,9)\n=> ");
  scanf("%d,%d,%d,%d,%d,%d,%d,%d,%d",&eingabe[0],&eingabe[1],&eingabe[2],&eingabe[3],&eingabe[4],&eingabe[5],&eingabe[6],&eingabe[7],&eingabe[8]);
  while(chk==0)
  {
    {
      int chkvar[9]={0};
      int i;
      
      for(i=0;i<9;i++)
      {
        if(eingabe[i]<=9&&eingabe[i]>=1)
	{
	  chkvar[(eingabe[i]-1)]++;
	}
      }
      
      for(i=0;i<9;i++)
      {
        if(chkvar[i]==1)
	{
	  chk=1;
	}
	else
	{
	  chk=0;
	  break;
	}
      }
    }
    if(chk==0)
    {
      printf("Permutation ungueltig!\nBitte geben Sie eine neue Permatation der Zahlen 1 bis 9 an:\n(Format: 1,2,3,4,5,6,7,8,9)\n=> ");
      scanf("%d,%d,%d,%d,%d,%d,%d,%d,%d",&eingabe[0],&eingabe[1],&eingabe[2],&eingabe[3],&eingabe[4],&eingabe[5],&eingabe[6],&eingabe[7],&eingabe[8]);
    }
  }
  
  printf("( 1, 2, 3, 4, 5, 6, 7, 8, 9 )\n");
  printf("( %d, %d, %d, %d, %d, %d, %d, %d, %d )\n",eingabe[0],eingabe[1],eingabe[2],eingabe[3],eingabe[4],eingabe[5],eingabe[6],eingabe[7],eingabe[8]);
  
  return 0;
}
