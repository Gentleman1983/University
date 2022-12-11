#include <stdio.h>
#define SPALTEN 8

int main()
{
  char c; /*Variablendeklaration*/
  int calc[256]={0}, i, nl=1;
  
  printf("Bitte Text eingeben. Strg + D zum Beenden.\n"); /*Eingabedialog*/
  while((c=getchar())!=EOF) /*Eingabeschleife*/
  {
    calc[(unsigned char) c]++; /*Zeicheneingabezählung*/
  }
  printf("\n");
  
  for (i=0; i<256; i++, nl++) /*Ausgabeschleife*/
  {
    printf("%c: %d\t",(char) i,calc[i]);
    if(nl==SPALTEN) /*Tabellenformatierungsschleife*/
    {
      printf("\n");
      nl=1;
    }
  }
  
  printf("\n");
  
  return 0;
}
