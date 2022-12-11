#include <netinet/in.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <sys/un.h>
#include <pthread.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#ifndef IPPROTO_DCCP
#	define IPPROTO_DCCP 33
#endif

static void *handle_client(void *arg)
{
	int fd = (long)arg;
	char buf[80];

	memset(buf, 0, sizeof(buf));
	read(fd, buf, sizeof(buf));
	printf("%.*s\n", sizeof(buf), buf);
	return NULL;
}

int main(void)
{
	struct sockaddr_in6 local_sk;
	const int y = true;
	int local_fd;

	if ((local_fd = socket(AF_INET6, SOCK_STREAM, IPPROTO_TCP)) < 0) {
		perror("socket");
		return EXIT_FAILURE;
	}

	if (setsockopt(local_fd, SOL_SOCKET, SO_REUSEADDR, &y, sizeof(y)) < 0) {
		perror("setsockopt");
		return EXIT_FAILURE;
	}

	memset(&local_sk, 0, sizeof(local_sk));
	local_sk.sin6_family      = AF_INET6;
	memset(&local_sk.sin6_addr, 0, sizeof(local_sk.sin6_addr));
	local_sk.sin6_port        = htons(52000);

	if (bind(local_fd, (const void *)&local_sk, sizeof(local_sk)) < 0) {
		perror("bind");
		return EXIT_FAILURE;
	}

	if (listen(local_fd, SOMAXCONN) < 0) {
		perror("listen");
		return EXIT_FAILURE;
	}

	while (true) {
		struct sockaddr_in6 remote_sk;
		socklen_t remote_sklen = sizeof(remote_sk);
		int remote_fd;
		pthread_t tid;

		remote_fd = accept(local_fd, (void *)&remote_sk, &remote_sklen);
		if (remote_fd < 0) {
			perror("accept");
			continue;
		}

		pthread_create(&tid, NULL, handle_client,
		               (void *)(long)remote_fd);
		pthread_detach(tid);
	}

	return EXIT_SUCCESS;
}
