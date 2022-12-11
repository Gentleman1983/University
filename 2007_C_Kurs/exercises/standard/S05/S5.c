#include <stdio.h>

int main()
{
  int zaehler = 'A'; /*Variablendeklaration und Initialisierung*/
  
  while (zaehler <= 'z')
  {
    printf("%c,\n", (char)zaehler);
    zaehler++;
  }
    
  return 0;
}
