#include <stdio.h>

int main()
{
  unsigned int eingabe;
  printf("Zum Abbrechen bitte Ctrl+d druecken...\n");
  while(scanf("%u", &eingabe)!=EOF)
  {
    
    printf("%u wurde eingegeben.\nIm Oktalsystem: %o\nIm Hexadezimalsystem: %x\n\n\n",eingabe,eingabe,eingabe);
    printf("Zum Abbrechen bitte Ctrl+d druecken...\n");
  }
  printf("\n\n\n");
  
  return 0;
}

