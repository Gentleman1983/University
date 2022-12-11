#include <stdio.h>
#define LAENGE 10

void BubbleSort(int feld[],int l);
void WerteEingabe(int feld[],int l);
void ArrayAusgabe(int feld[],int l);

int main()
{
  /*Variablendeklaration und Initialisierung*/
  int feld[LAENGE]={0};
  
  WerteEingabe(feld,LAENGE);
  
  BubbleSort(feld,LAENGE);
  
  ArrayAusgabe(feld,LAENGE);
  
  return 0;
}

/* Hilfsfunktionen */

void BubbleSort(int feld[],int l)
{
  int i, ok, tmp;
  
  printf("Werte werden sortiert...\n");   /*Sortieren per BubbleSort*/
  do
  {
    ok=1;
    for(i=0;i<l-1;i++)
    {
      if (feld[i]>feld[i+1])
      {
        tmp=feld[i];           
        feld[i]=feld[i+1];
        feld[i+1]=tmp;
        ok=0;                  
      }
    }
  } while (!ok);
}

void WerteEingabe(int feld[],int l)
{
  int i;
  
  printf("Bitte 10 Ganzzahleneingeben...\n");
  
  for(i=1;i<=l;i++)   /*Einleseschleife (noch) ohne Typabfrage*/
  {
    printf("%d. Zahl: ",i);
    scanf("%d",&feld[i-1]);
  }
  printf("%d Werte eingelesen...\n\n", l);
}

void ArrayAusgabe(int feld[],int l)
{
  int i;
  
  printf("Sortierte Zahlen:\n\n[");   /*Ausgabeschleife*/
  for(i=0;i<l;i++)
  {
    printf(" %d",feld[i]);
    if(i!=l-1)
    {
      printf(",");
    }
  }
  printf(" ]\n\n");
}
