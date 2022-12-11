#include <stdio.h>

int sjahr(unsigned int jahr);
int tagimjahr(unsigned int tag, unsigned int monat, unsigned int jahr, int sj);
void tag2datum(unsigned int tij, int sj, int datum[]);

int main()
{
  unsigned int tij=0, tmp=0, jahr=0, jahr2=0;
  int sj2=0;
  int datum[2]={0};
  
  printf("Bitte Tag und Jahr eingeben:\n(Format:65.2005 fuer 65. Tag im Jahr 2005)\n=> ");
  scanf("%u.%u",&tij,&jahr2);
  
  tmp=tij;
  jahr=jahr2;
  
  sj2=sjahr(jahr2);
      
  while(tij>=(sj2+365))
  {
    tij-=(sj2+365);
    jahr2++;
    sj2=sjahr(jahr2);
  }
  if(tij==0)
  {
    jahr2--;
    sj2=sjahr(jahr2);
    tij+=sj2+365;
  }
  
  tag2datum(tij,sj2,datum);
  printf("Der %u. Tag im Jahr %u ist der %d.%d.%u!\n",tmp,jahr,datum[0],datum[1],jahr2);
  
  return 0;
}

/* Hilfsfunktionen */

int sjahr(unsigned int jahr)
{
  if(jahr%4!=0||(jahr%100==0&&jahr%400!=0))
  {
    return 0;
  }
  else
  {
    return 1;
  }
}

int tagimjahr(unsigned int tag, unsigned int monat, unsigned int jahr, int sj)
{
  int zaehler;
    
  switch(monat)
  {
    case 1:
      zaehler=tag;
      break;
    case 2:
      zaehler=tag+31;
      break;
    case 3:
      zaehler=tag+sj+59;
      break;
    case 4:
      zaehler=tag+sj+90;
      break;
    case 5:
      zaehler=tag+sj+120;
      break;
    case 6:
      zaehler=tag+sj+151;
      break;
    case 7:
      zaehler=tag+sj+181;
      break;
    case 8:
      zaehler=tag+sj+212;
      break;
    case 9:
      zaehler=tag+sj+243;
      break;
    case 10:
      zaehler=tag+sj+273;
      break;
    case 11:
      zaehler=tag+sj+304;
      break;
    case 12: 
      zaehler=tag+sj+334;
      break;
    default:
      zaehler=-1;
  }
  return zaehler;
}

void tag2datum(unsigned int tij, int sj, int datum[])
{
  int tim[12]={31,28,31,30,31,30,31,31,30,31,30,31};
  int i=0;
  
  if(sj)
  {
    tim[1]++;
  }
  
  while((int)tij-tim[i]>0)
  {
    
    tij-=(unsigned int)tim[i];
    i++;
  }
  datum[0]=(int)tij;
  datum[1]=i+1;
  
  return;
}
