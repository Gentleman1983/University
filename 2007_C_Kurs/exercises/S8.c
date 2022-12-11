#include <stdio.h>

int main()
{
  double durchschn=0.0;
  int anz=0;
  float eingabe;
  
  printf("Bitte Zahlen eingeben. Ctrl + D zum Abbruch!\n");
  while(scanf("%f",&eingabe)!= EOF)
  {
    durchschn = ((durchschn*anz)+eingabe)/(anz+1);
    anz++;
    printf("Aktueller Mittelwert beträgt: %f\n", durchschn);
  }
  
  return 0;
}
