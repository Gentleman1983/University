#include <sys/socket.h>
#include <sys/types.h>
#include <sys/un.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

int main(int argc, const char **argv)
{
	struct sockaddr_un sk;
	char tmp[80];
	int fd;

	if (argc < 2) {
		fprintf(stderr, "Kein Parameter angegeben - ergo nix "
		        "zu senden. Tja!\n");
		return EXIT_FAILURE;
	}

	if ((fd = socket(AF_LOCAL, SOCK_STREAM, 0)) < 0) {
		perror("socket");
		return EXIT_FAILURE;
	}

	memset(&sk, 0, sizeof(sk));
	sk.sun_family = AF_LOCAL;
	strncpy(sk.sun_path, "informatik.socket", sizeof(sk.sun_path));

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
