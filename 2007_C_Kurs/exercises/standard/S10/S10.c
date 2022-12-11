#include <stdio.h>
#include <string.h>
#define LAENGE 49
                          /*Anzahl eingebbarer*/
int main()
{
  char a[LAENGE+1]="",b[LAENGE+1]="",c[2*LAENGE+1]=""; /*Variablendeklaration*/
  int ia=0, ib=0;
  
  printf("Bitte ersten String eingeben: "); /*Eingabeschleife erster String*/
  while((a[ia]=getchar())!='\n' && ia<=LAENGE)
  {
    ia++;
    if(ia>LAENGE && a[ia]!='\n') /*Programmabbruchschleife*/
    {
      printf("Programmfehler. String laenger als %d Zeichen!\n",LAENGE);
      
      return -1;
    }
  }
  a[ia]='\0'; /*Setzen des Abschlusses*/
  
  printf("Bitte zweiten String1 eingeben: "); /*Einlesen des zweiten Strings*/
  while((b[ib]=getchar())!='\n' && ib<=LAENGE)
  {
    ib++;
    if(ib>LAENGE && b[ib]!='\n') /*Programmabbruchschleife*/
    {
      printf("Programmfehler. String2 laenger als %d Zeichen!\n",LAENGE);
      
      return -1;
    }
  }
  b[ib]='\0'; /*Setzen des Abschlusses*/
  strcat(c,a); /*Füllen von c mit den Strings aus a und b*/
  strcat(c,b);
  printf("%s\n",c); /*Ausgabe von c*/
  
  return 0;
}
