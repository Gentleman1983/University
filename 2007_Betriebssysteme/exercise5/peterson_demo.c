#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

// These variables need to be global, since they are used by the threads
long int COUNT_MAX;
volatile long int in;

enum boolean { false = 0,  true = 1 };
volatile enum boolean interested[2] = { false, false };
volatile int turn;

void *increase_in_p0(void *arg) // Called as thread
{
  long int i;
  long int next_free_slot;
  for (i = 0; i < COUNT_MAX; ++i)
    {

      interested[0] = true;
      turn = 1;
      while (interested[1] && turn == 1) /* waiting */ ;

      next_free_slot = in;
      next_free_slot = next_free_slot + 1;
      in = next_free_slot;

      interested[0] = false;

    }
  return NULL;
}

void *increase_in_p1(void *arg) // Called as thread
{
  long int i;
  long int next_free_slot;
  for (i = 0; i < COUNT_MAX; ++i)
    {

      interested[1] = true;
      turn = 0;
      while (interested[0] && turn == 0) /* waiting */ ;

      next_free_slot = in;
      next_free_slot = next_free_slot + 1;
      in = next_free_slot;

      interested[1] = false;

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
  pthread_create(&thread1_id, NULL, &increase_in_p0, NULL);
  pthread_create(&thread2_id, NULL, &increase_in_p1, NULL);
  pthread_join(thread1_id, NULL);
  pthread_join(thread2_id, NULL);
  printf("in=%ld\n", in);
  return EXIT_SUCCESS;
}
