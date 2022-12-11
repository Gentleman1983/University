#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

// These variables need to be global, since they are used by the threads
long int COUNT_MAX;
volatile long int in;

enum boolean { false = 0,  true = 1 };
volatile enum boolean interested[2] = { false, false };
volatile int turn;

void enter_criticalregion(int process)
{
  int other = 1 - process;
  interested[process] = true;
  turn = other;
  while (interested[other] && turn == other) /* waiting */ ;

}

void leave_criticalregion(int process)
{
  interested[process] = false;
}


void *increase_in(void *arg)    // Called as thread
{
  long int i;
  long int next_free_slot;
  for (i = 0; i < COUNT_MAX; ++i)
    {

      enter_criticalregion((int) arg);

      next_free_slot = in;
      next_free_slot = next_free_slot + 1;
      in = next_free_slot;

      leave_criticalregion((int) arg);

    }
  return NULL;
}

int main(int argc, char *argv[])
{
  pthread_t thread1_id;
  pthread_t thread2_id;

  if (argc != 2)
    {
      printf("Usage: %s COUNT_MAX\n", argv[0]);
      exit(EXIT_FAILURE);
    }
  sscanf(argv[1], "%lu", &COUNT_MAX);

  in = 0;
  pthread_create(&thread1_id, NULL, &increase_in, (void *) 0);
  pthread_create(&thread2_id, NULL, &increase_in, (void *) 1);
  pthread_join(thread1_id, NULL);
  pthread_join(thread2_id, NULL);
  printf("in=%ld\n", in);
  return EXIT_SUCCESS;
}
