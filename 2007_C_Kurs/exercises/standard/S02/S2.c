#include <stdio.h>

int main()
{
  printf("Hier wird \\b getestet:\n|......|\b|......|\n\n");
  printf("Hier wird \\n getestet:\n|......|\n|......|\n\n");
  printf("Hier wird \\r getestet:\n|......|\r|......|\n\n");
  printf("Hier wird \\t getestet:\n|......|\t|......|\n\n");
  printf("Hier wird \\\" getestet:\n|......|\"|......|\n\n");
  printf("Hier wird \\\\ getestet:\n|......|\\|......|\n");
  
  return 0;
}
