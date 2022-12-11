#include <stdio.h>

int main() /*Zeichenformatierung ... ;)*/
{
  printf("Aller guten Dinge sind %d.\n",3);
  printf("Aller guten Dinge sind %3d.\n",3); /*min. 3 Stellen Länge*/
  printf("Aller guten Dinge sind %6d.\n",3); /*min. 6 Stellen Länge*/
  
  printf("Pi ist etwa %f.\n",3.141593);
  printf("Pi ist etwa %6.3f.\n",3.141593); /*min. 6 Stellen, drei davon Nachkomma!*/
  printf("Pi ist etwa %.9f.\n",3.141593); /*min. 9 Nachkommastellen*/
  
  return 0;
}
