#include <sys/socket.h>
#include <sys/types.h>
#include <sys/un.h>
#include <pthread.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

static void *handle_client(void *arg)
{
	int fd = (long)arg;
	char buf[80];

	memset(buf, 0, sizeof(buf));
	read(fd, buf, sizeof(buf));
	printf("Client sendete uns: %.*s\n", sizeof(buf), buf);
	return NULL;
}

int main(void)
{
	struct sockaddr_un local_sk;
	int local_fd;

	if ((local_fd = socket(AF_LOCAL, SOCK_STREAM, 0)) < 0) {
		perror("socket");
		return EXIT_FAILURE;
	}

	memset(&local_sk, 0, sizeof(local_sk));
	local_sk.sun_family = AF_LOCAL;
	strncpy(local_sk.sun_path, "informatik.socket",
	        sizeof(local_sk.sun_path));
	unlink("informatik.socket");
	if (bind(local_fd, (const void *)&local_sk, sizeof(local_sk)) < 0) {
		perror("bind");
		return EXIT_FAILURE;
	}

	if (listen(local_fd, SOMAXCONN) < 0) {
		perror("bind");
		abort();
	}

	while (true) {
		struct sockaddr_un remote_sk;
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
