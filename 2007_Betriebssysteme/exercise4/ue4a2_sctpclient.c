#include <arpa/inet.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

int main(int argc, const char **argv)
{
	struct sockaddr_in6 sk;
	char tmp[80];
	int fd;

	if (argc < 2) {
		fprintf(stderr, "Kein Parameter angegeben - ergo nix "
		        "zu senden. Tja!\n");
		return EXIT_FAILURE;
	}

	if ((fd = socket(AF_INET6, SOCK_STREAM, IPPROTO_TCP)) < 0) {
		perror("socket");
		return EXIT_FAILURE;
	}

	memset(&sk, 0, sizeof(sk));
	sk.sin6_family = AF_INET6;
	if (inet_pton(AF_INET6, "::1", &sk.sin6_addr) < 0) {
		perror("inet_pton");
		return EXIT_FAILURE;
	}
	sk.sin6_port = htons(52000);

	if (connect(fd, (const void *)&sk, sizeof(sk)) < 0) {
		perror("connect");
		return EXIT_FAILURE;
	}

	memset(tmp, 0, sizeof(tmp));
	strncpy(tmp, argv[1], sizeof(tmp));
	write(fd, tmp, sizeof(tmp));
	close(fd);
	return EXIT_SUCCESS;
}
