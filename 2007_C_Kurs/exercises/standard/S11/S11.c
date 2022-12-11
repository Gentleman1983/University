#include <stdio.h>
#include <ctype.h>
#define BINBITS 8

int main()
{
  char c; /*Variablendeklaration und Initialisierung*/
  int i=1, started=0;
  long unsigned int zahl=0;
                                     /*Eingabedialog*/
  printf("Bitte geben Sie eine %d-Bit-Binaerzahl ein:\n=> ",BINBITS);
  
  while((c=getchar())!='\n') /*Auswertungsschleife*/
  {
    if(started==0) /*Prüfung auf White Spaces vor der Binärzahl*/
    {
      if(isspace(c)==0)
      {
        started=1;  /*Im Falle des Zahlenbeginns Umsetzen des started-Flags*/               
      }
      else
      {
        continue;   /*Ansonsten nächstes Zeichen einlesen*/
      }
    }
    
    if(i>BINBITS) /*Prüfung der Zahlenbits*/
    {
      printf("Eingabefehler: Eingegebene Binaerzahl ist zu lang!!!\n\n");
      return 2;     
    }
    else if(c=='0') /*Bearbeitung des Falles einer Eingabe von 0*/
    {
      zahl*=2;
    }
    else if(c=='1') /*Bearbeitung des Falles einer Eingabe von 1*/
    {
      zahl*=2;
      zahl++;
    }
    else if(c==EOF) /*Schleifenabbruch bei EOF-Signal (Strg + D)*/
    {
      break;
    }
    else /*Programmabbruch bei sonstigen Zeichen*/
    {
      printf("Eingabefehler: Zahl ist keine Binaerzahl!!!\n\n");
      return 1;
    }
    i++; /*Zaehlerflag für Zahlenbits nach Beginn wird je Zahl inkrementiert*/
  }
                                      /*Ausgabe der Zahl*/
  printf("\nDie Zahl ist im Dezimalsystem: %ld\n\n",zahl);
  
  return 0;
}
