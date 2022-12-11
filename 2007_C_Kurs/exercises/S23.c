#include "datumsfunktionen.h"

int main()
{
  unsigned int jahr=0, monat=0, tag=0;
  int sj=0, zaehler=0, i=0;
  char wochentag[7][11]={"Montag","Dienstag","Mittwoch","Donnerstag","Freitag","Samstag","Sonntag"};
    
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
  else if(jahr>=5000||jahr<1582||(jahr==1582&&((monat==10&&tag<15)||monat<10)))
  {
    printf("Datum ungueltig; Datum vor dem 15.10.1582 oder nach dem 31.12.4999)!\n");
    return 4;
  }
  if(jahr>=1600)
  {
    zaehler=tagimjahr(tag,monat,jahr,sj);
    zaehler+=4;
    for(i=1600;i<(int)jahr;i++)
    {
      zaehler+=sjahr(i)+365;
      zaehler%=7;
    }    
  }
  else
  {
    zaehler=365+sj-tagimjahr(tag,monat,jahr,sj);
    
    for(i=jahr+1;i<1600;i++)
    {
      zaehler+=sjahr(i)+365;
    }
    zaehler%=7;
    zaehler=11-zaehler;
    zaehler%=7;
  }
  
  /*zaehler+=2; Datenverschiebung*/
  zaehler%=7;
  
  printf("Der %u.%u.%u ist ein %s!\n",tag,monat,jahr,wochentag[zaehler]);
  
  
  return 0;
}
