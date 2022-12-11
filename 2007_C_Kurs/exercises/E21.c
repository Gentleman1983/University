#include <stdio.h>
#define MAXLENGTH 10

int permtest(const unsigned int* p, int laenge);

int main()
{
  int laenge=0, i, chk;
  unsigned int feld[MAXLENGTH]={0};
  
  printf("Bitte geben Sie die gewünschte Feldgroesse ein:\n(Maximal %d)\n=> ",MAXLENGTH);
  scanf("%d",&laenge);
  
  for(i=0;i<laenge;i++)
  {
    printf("Bitte geben Sie den Wert für Feld %d ein:\n=> ",(i+1));
    scanf("%u",&feld[i]);
  }
  
  chk=permtest(feld,laenge);
  
  for(i=0;i<laenge;i++)
  {
    printf("%d\t",(i+1));
  }
  printf("\n");
  for(i=0;i<laenge;i++)
  {
    printf("%u\t",feld[i]);
  }
  printf("\n\n");
  
  if(chk==0)
  {
    printf("ist eine Permutation!\n");
  }
  else
  {
    printf("ist keine Permutation!\n");
  }
  
  return 0;
}

int permtest(const unsigned int* p, int laenge)
{
  unsigned int feld[MAXLENGTH]={0};
  int i, j, chk=0;
  
  for(i=0;i<MAXLENGTH;i++)
  {
    feld[i]=i+1;
  }
  
  for(i=0;i<laenge;i++, p++)
  {
    for(j=0;j<laenge;j++)
    {
      if(*p==feld[j])
      {
        chk++;
	feld[j]=0;
      }
    }
  }
  
  if(chk!=laenge)
  {
    return 1;
  }
  else
  {
    return 0;
  }
}
