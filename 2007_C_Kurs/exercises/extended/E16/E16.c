#include <stdio.h>

int main()
{
  char c;
  short s;
  int i;
  long l;
  
  printf("Speicherverbrauch der verschiedenen Ganzzahltypen:\n");
  printf("-CHAR:   %3ld\n",sizeof(c));
  printf("-SHORT:  %3ld\n",sizeof(s));
  printf("-INT:    %3ld\n",sizeof(i));
  printf("-LONG:   %3ld\n\n",sizeof(l));
  
  return 0;    
}
