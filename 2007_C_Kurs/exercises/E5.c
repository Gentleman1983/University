#include <stdio.h>
#define POLYGRAD 4

/*------------------------------------------------------------------------*/
/*--- Bestimmung des Wertes eines Polynoms an einer Stelle x=a         ---*/
/*------------------------------------------------------------------------*/

void  polyausgabe(int, float[]);
int   polyeingabe(int, float[]);
float polyrechnen(float, float[], int, int);

/*------------------------------------------------------------------------*/
/*--- Main-Funktion                                                    ---*/
/*------------------------------------------------------------------------*/

int main()
{
  float an[POLYGRAD+1]={0}, a, erg;
  int chk;
  
  printf("Bitte Koeffizienten fuer Polynom vom Grad %d eingeben\n",POLYGRAD);
  for(chk=0;chk==0;) /*Eingabedialogsteuerung*/
  {
    chk=polyeingabe(POLYGRAD,an);
    printf("\nEingegebenes Polynom %d. Grades lautet:\n",POLYGRAD);
    polyausgabe(POLYGRAD,an);
  }
         /*Eingabedialog zur Bestimmung der Berechnungsstelle*/
  printf("Bitte Wert fuer Wertbestimmung angeben:\n=> ");
  scanf("%f",&a);
  
         /*Berechnung des Funktionswertes und anschließende Ausgabe*/
  erg=polyrechnen(a, an, POLYGRAD, 0);
  printf("Wert des Polynoms an der Stelle %f betraegt:\n%f\n\n",a,erg);
  
  return 0;
}

/*------------------------------------------------------------------------*/
/*Hilfsfunktionen*/
/*------------------------------------------------------------------------*/

int polyeingabe(int i, float an[]) /*Polynomeingabedialog*/
{
  for(;i>=0;i--)
  {
    if(i!=0)
    {
      printf("Bitte Koeffizient fuer x^%d eingeben:\n=> ",i);
    }
    else
    {
     printf("Bitte linearen Term eingeben:\n=> ");
    }
    scanf("%f",&an[i]);
  }
  
  return 1;   
}

/*------------------------------------------------------------------------*/

void polyausgabe(int cnt, float an[]) /*Ausgabe des Polynoms*/
{
  int emp=1;
  
  for(cnt=POLYGRAD;cnt>=0;cnt--)
  {
    if(cnt==0)
    {
      if((emp==1) && (an[cnt]>=0))
      {
        printf("%f\n",an[cnt]);
      }
      else if(an[cnt]>0)
      {
        printf("+%f\n",an[cnt]);
      }
      else if(an[cnt]<0)
      {
        printf("%f\n",an[cnt]);     
      }
      else if(an[cnt]==0)
      {
        printf("\n");     
      }
    }
    else if(an[cnt]==0)
    {
      continue;
    }
    else if(an[cnt]<0)
    {
      emp=0;
      printf("%f*x^%d",an[cnt],cnt);     
    }
    else
    {
      if (emp==0)
      {
        printf("+");
      }
      emp=0;
      printf("%f*x^%d",an[cnt],cnt);
    }
  }
  
  return;
}

/*------------------------------------------------------------------------*/

float polyrechnen(float a, float an[], int grad, int rek) /*Berechnung des*/
{                                                         /*Polynoms      */
  if(rek==grad) /*maximale Rekursionstiefe erreicht?*/
  {
    return an[rek];
  }
  else
  {             /*rekursiver Aufruf*/
    return an[rek]+a*polyrechnen(a, an, grad, rek+1);
  }
}
