#ifndef DATUMSFUNKTIONEN_H
#define DATUMSFUNKTIONEN_H
#include <stdio.h>

int sjahr(unsigned int jahr);
int tagimjahr(unsigned int tag, unsigned int monat, unsigned int jahr, int sj);
void tag2datum(unsigned int tij, int sj, int datum[]);
#endif
