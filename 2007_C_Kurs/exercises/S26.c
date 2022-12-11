#include "bruch.h"

int main()
{
  bruch_t *bruch1, *bruch2, *bruch3, *bruch4;
  float b1, b2, b3, b4;
  
  bruch1=buildbruch();
  bruch2=buildbruch();
  bruch3=buildbruch();
  bruch4=buildbruch();
  
  brucheingabe(bruch1);
  brucheingabe(bruch2);
  brucheingabe(bruch3);
  bruchausgabe(bruch1);
  bruchausgabe(bruch2);
  bruchausgabe(bruch3);
  
  printf("\nAddition von Bruch 1 mit Bruch 2...\n");
  bruchadd(bruch1, bruch2, bruch4);
  bruchausgabe(bruch4);
  
  printf("\nSubstraktion von Bruch 3 und Bruch 2...\n");
  bruchsub(bruch3, bruch2, bruch4);
  bruchausgabe(bruch4);
  
  printf("\nMultiplikation von Bruch 1 mit Bruch 3...\n");
  bruchmult(bruch1, bruch3, bruch4);
  bruchausgabe(bruch4);
  
  printf("\nDivision von Bruch 2 durch Bruch 1...\n");
  bruchdiv(bruch2, bruch1, bruch4);
  bruchausgabe(bruch4);
  
  printf("\nAddition von Bruch 1 mit Bruch 2...\n");
  bruchadd(bruch1, bruch2, bruch4);
  bruchausgabe(bruch4);
  
  printf("\nBerechnung der Werte als Float...\n");
  b1=bruchquot(bruch1);
  b2=bruchquot(bruch2);
  b3=bruchquot(bruch3);
  b4=bruchquot(bruch4);
  printf("\nBruch1 = %f\n",b1);
  printf("Bruch2 = %f\n",b2);
  printf("Bruch3 = %f\n",b3);
  printf("Bruch4 = %f\n",b4);
  
  printf("\nErweitern von Bruch4 mit 10...\n");
  brucherw(bruch4,10);
  bruchausgabe(bruch4);
  
  killbruch(bruch1);
  killbruch(bruch2);
  killbruch(bruch3);
  killbruch(bruch4);
    
  return 0;
}
