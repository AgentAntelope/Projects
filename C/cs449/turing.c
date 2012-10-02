#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdio.h>
#include<pthread.h>

void doStuff(int connfd){
    printf("Yes\n");
	close(connfd);
}

int main(){
	int sfd, connfd;
	struct sockaddr_in addr;
	if((sfd = socket(PF_INET, SOCK_STREAM, 0)) < 0){
		printf("Socket fail\n");
		return -1;
	}

	memset(addr.sin_zero, 0, sizeof(addr));
	addr.sin_family = AF_INET;
	addr.sin_port = htons(50030);
	addr.sin_addr.s_addr = INADDR_ANY; //automatically find IP

	if((bind(sfd, (struct sockaddr *)&addr, sizeof(addr))) < 0){
       printf("Bind fail\n");
        return -1;
	}

	if((listen(sfd,10)) < 0){
        printf("Listen failed\n");
        return 0;
	}
    while(1){
        pthread_t thread1;
        if((connfd = accept(sfd, NULL,NULL))< 0){
            printf("Accept failed\n");
            return 0;
        }
        pthread_create(&thread1, NULL, doStuff, connfd);
    }
	//char buffer[1024];
	//strcpy(buffer, "Hello there!\n");
	//send(connfd, buffer, strlen(buffer), 0);
	//close(connfd);
	close(sfd);

	return 0;
}
