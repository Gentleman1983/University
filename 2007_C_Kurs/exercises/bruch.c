#include "bruch.h"

bruch_t* buildbruch()
{
  bruch_t* bp=malloc(sizeof(bruch_t));
  if(bp==NULL)
  {
    printf("Fehler! Speicher nicht ausreichend!!!\n");
  }
  
  return bp;
}

void killbruch(bruch_t* bp)
{
  free(bp);
  printf("Bruch geloescht...\n");
  
  return;
}

void brucheingabe(bruch_t* bp)
{
  printf("Bitte geben Sie Zaehler ein.\n=> ");
  scanf("%d",&bp->zaehler);
  while(getchar()!='\n');
  printf("Bitte geben Sie Nenner ein.\n");
  do
  {
    printf("=> ");
    scanf("%d",&bp->nenner);
    while(getchar()!='\n');
  }
  while(bp->nenner==0);
  
  if(bp->nenner<0)
  {
    bp->zaehler*=-1;
    bp->nenner*=-1;
  }
  
  return;
}

void bruchausgabe(bruch_t* bp)
{
  if(bp->nenner!=1)
  {
    printf("%d/%d\n",bp->zaehler,bp->nenner);
  }
  else
  {
    printf("%d\n",bp->zaehler);
  }
  
  return;
}

void bruchadd(bruch_t* bp1, bruch_t* bp2, bruch_t* bpe)
{
  int n1=bp1->nenner, n2=bp2->nenner, z1=bp1->zaehler, z2=bp2->zaehler;
  
  z2*=n1;
  n1*=n2;
  z1*=n2;
  z1+=z2;
  
  bpe->zaehler=z1;
  bpe->nenner=n1;
  
  kuerzen(bpe);
  
  return;
}

void bruchsub(bruch_t* bp1, bruch_t* bp2, bruch_t* bpe)
{
  int n1=bp1->nenner, n2=bp2->nenner, z1=bp1->zaehler, z2=bp2->zaehler;
  
  z2*=n1;
  n1*=n2;
  z1*=n2;
  z1-=z2;
  
  bpe->zaehler=z1;
  bpe->nenner=n1;
  
  kuerzen(bpe);
  
  return;
}

void bruchmult(bruch_t* bp1, bruch_t* bp2, bruch_t* bpe)
{
  int n1=bp1->nenner, n2=bp2->nenner, z1=bp1->zaehler, z2=bp2->zaehler;
  
  n1*=n2;
  z1*=z2;
    
  bpe->zaehler=z1;
  bpe->nenner=n1;
  
  kuerzen(bpe);
  
  return;
}

void bruchdiv(bruch_t* bp1, bruch_t* bp2, bruch_t* bpe)
{
  int n1=bp1->nenner, n2=bp2->nenner, z1=bp1->zaehler, z2=bp2->zaehler;
  
  n1*=z2;
  z1*=n2;
  
  if(n1==0)
  {
    printf("Fehler! Division durch 0!!! Wert NICHT ZUGEWIESEN!!!\n");
    
    return;
  }
  
  if(n1<0)
  {
    z1*=-1;
    n1*=-1;
  }
    
  bpe->zaehler=z1;
  bpe->nenner=n1;
  
  kuerzen(bpe);
  
  return;
}

float bruchquot(bruch_t* bp)
{
  return (float)bp->zaehler/(float)bp->nenner;
}

void brucherw(bruch_t* bp, int erw)
{
  bp->zaehler*=erw;
  bp->nenner*=erw;
  
  return;
}

void kuerzen(bruch_t* bp)
{
  int ggtwert=ggt(abs(bp->zaehler),abs(bp->nenner));
  bp->zaehler/=ggtwert;
  bp->nenner/=ggtwert;
  
  return;
}

int ggt(int a, int b)
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
