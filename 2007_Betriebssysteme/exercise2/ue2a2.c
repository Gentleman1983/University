#define _GNU_SOURCE 1
#include <math.h>
#include <poll.h>
#include <pthread.h>
#include <stdbool.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

static const uint64_t prime_start = 1ULL << 39;

static bool prime_check(uint64_t n)
{
	uint64_t i, up = sqrt(n);

	for (i = prime_start; i <= up; ++i)
		if (n % i == 0)
			return false;

	return true;
}

static void *prime_thread(void *unused)
{
	uint64_t n;

	for (n = prime_start; n < prime_start + 5500; n += 2)
		if (prime_check(n))
			printf("\t%llu\n", (unsigned long long)n);

	return NULL;
}

static int part1(void)
{
	pthread_t id;

	pthread_create(&id, NULL, prime_thread, NULL);
	while (getchar() != '\n')
		;
	pthread_cancel(id);
	printf("Exit\n");
	return EXIT_SUCCESS;
}

static int part2(void)
{
	struct pollfd pfd = {
		.fd     = STDIN_FILENO,
		.events = POLLIN,
	};
	uint64_t n;

	for (n = prime_start; n < prime_start + 5500; n += 2) {
		if (prime_check(n))
			printf("\t%llu\n", (unsigned long long)n);
		if (poll(&pfd, 1, 0) > 0 && getchar() == '\n')
			break;
	}

	printf("Exit\n");
	return EXIT_SUCCESS;
}

int main(int argc, const char **argv)
{
	setvbuf(stdout, NULL, _IOLBF, 0);

	if (argc >= 2)
		return part2();

	return part1();
}
