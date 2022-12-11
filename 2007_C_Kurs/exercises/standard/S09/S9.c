#include <stdio.h>
#define LAENGE 10

int main()
{
  int i,ok,tmp;   /*Variablendeklaration und Initialisierung*/
  int feld[LAENGE]={0};
  
  printf("Bitte 10 Ganzzahleneingeben...\n");
  
  for(i=1;i<=LAENGE;i++)   /*Einleseschleife (noch) ohne Typabfrage*/
  {
    printf("%d. Zahl: ",i);
    scanf("%d",&feld[i-1]);
  }
  printf("%d Werte eingelesen...\n", LAENGE);
  
  printf("Werte werden sortiert...\n");   /*Sortieren per BubbleSort*/
  do
  {
    ok=1;
    for(i=0;i<LAENGE-1;i++)
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
  
  printf("Sortierte Zahlen:\n\n[");   /*Ausgabeschleife*/
  for(i=0;i<LAENGE;i++)
  {
    printf(" %d",feld[i]);
    if(i!=LAENGE-1)
    {
      printf(",");
    }
  }
  printf(" ]\n\n");
  
  return 0;
}
