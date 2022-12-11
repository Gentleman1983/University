#ifndef BRUCH_H
#define BRUCH_H

#include <stdio.h>
#include <stdlib.h>

typedef struct bruch_s
{
  int zaehler;
  int nenner;
}bruch_t;

bruch_t* buildbruch();
void killbruch(bruch_t* bp);
void brucheingabe(bruch_t* bp);
void bruchausgabe(bruch_t* bp);
void bruchadd(bruch_t* bp1, bruch_t* bp2, bruch_t* bpe);
void bruchsub(bruch_t* bp1, bruch_t* bp2, bruch_t* bpe);
void bruchmult(bruch_t* bp1, bruch_t* bp2, bruch_t* bpe);
void bruchdiv(bruch_t* bp1, bruch_t* bp2, bruch_t* bpe);
float bruchquot(bruch_t* bp);
void brucherw(bruch_t* bp, int erw);
void kuerzen(bruch_t* bp);
int ggt(int a, int b);
#endif
