#include "datumsfunktionen.h"

int main()
{
  unsigned int jahr=0, monat=0, tag=0, jahr2=0, jahr3=0;
  int sj=0, zaehler=0, zaehler2=0, sj2=0;
  int datum[2]={0},datum2[2]={0};
  
  printf("Bitte ein Datum eingeben:\nFormat: TT.MM.JJJJ\n=> ");
  scanf("%u.%u.%u",&tag,&monat,&jahr);
  sj = sjahr(jahr);
  
  if (tag==0||monat==0) /* Abbruch bei Eingabefehler */
  {
    printf("Datum ungueltig! Tag und Monat duerfen nicht gleich 0 sein!!!\n");
    return 1;
  }
  else if(monat>12)
  {
    printf("Datum ungueltig; Monat zu gross!!!\n");
    return 2;
  }
  else if(tag>31||(tag>30&&monat==4)||(tag>30&&monat==6)||(tag>30&&monat==9)||(tag>30&&monat==11)||((tag>28+sj)&&monat==2))
  {
    printf("Datum ungueltig; Tag zu hoch fuer Monat!!!\n");
    return 3;
  }
  
  zaehler=tagimjahr(tag,monat,jahr,sj);
  zaehler2=zaehler;
  
  zaehler+=100;
  sj=sjahr(jahr);
  jahr2=jahr;
  if(zaehler>(365+sj))
  {
    zaehler-=(365+sj);
    jahr2++;
    sj=sjahr(jahr2);
  }
  
  zaehler2-=100;
  sj2=sjahr(jahr);
  jahr3=jahr;
  if(zaehler<1)
  {
    jahr3--;
    sj2=sjahr(jahr3);
    zaehler2+=sj2+365;
  }
  
  tag2datum(zaehler,sj,datum);
  tag2datum(zaehler2,sj2,datum2);
  
  printf("Referenzdatum ist der %u.%u.%u\n\n",tag,monat,jahr);
  printf("Der 100. Tag vor dem Referenzdatum ist der %d.%d.%u!\n",datum2[0],datum2[1],jahr3);
  printf("Der 100. Tag nach dem Referenzdatum ist der %d.%d.%u!\n",datum[0],datum[1],jahr2);
  
  return 0;
}
