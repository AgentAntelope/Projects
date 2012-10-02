/*
Author: Sean Myers
Purpose: To make a simple web server to demonstrate mastery of pthreads and berkely sockets.
*/


//Include files/////////////////////////////////////////////////////
#include <pthread.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
///////////////////////////////////////////////////////////

pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;//Global variable delcaractions..

///////////////////////////////////////////////////////////
/*
Function: writeStat
purpose: To write to the stats page that holds all the results of previous page file requests.

implementation notes: If the file is not found, it will be created.
Takes the name as an input, no output.
*/
//////////////////////////////////////////////////////////
void writeStat(char fileName[]){
    pthread_mutex_lock(&mutex);
    FILE *out;
    out = fopen("stats.txt", "ab");

    if(out == NULL){
        out = fopen("stats.txt", "wb");
    }
    fprintf(out,"Request on page: %s\n", fileName);
    fclose(out);
    pthread_mutex_unlock(&mutex);

}
///////////////////////////////////////////////////////////
/*
Function: doStuff
purpose: To have the worst name ever. That and this is the main "spawn" of the threads. It takes their
connfd and uses it to send and receive the the individual. Checks common things like "Is this a GET request". Will
attempt to parse simple file names, anything too complicated and it will probably explode (gracefully of course).

*/
//////////////////////////////////////////////////////////
void *doStuff(int connfd){
    time_t rawtime; //Time to output back to the requester
    struct tm * timeinfo;
    char buffer [80];//Buffer for the time output.
    int size, sizeRead;
    FILE *f;
    int readfile;
    char *parsed;
    int spaceChar = 0;
    int amt = 0;
    int sizeRet;//sizeRet will get the size read from the file opened and compare to amount.
    char siz[40]; //Buffer for the return size.
    char par[1024]; //Main parsed text.
    char sendStuff[1024]; //Send the main portion of non-file.
    char sendFile[1024]; // File info to be sent in packages of 1024
    char emptyBuf[1024];
    if((readfile = recv(connfd, par, 1024, 0)) < 0){
        printf("Did not receive correctly\n");
        close(connfd);
        exit(EXIT_FAILURE);
    }
    while(readfile > 1024){
       if((readfile = recv(connfd, emptyBuf, 1024, 0)) < 0){
        printf("Did not receive correctly\n");
        close(connfd);
        exit(EXIT_FAILURE);
        }
    }
    if(!strncmp(par, "GET", 3)){
        parsed = strchr(par,'/');
        parsed++;
        spaceChar = strcspn(parsed, " ");
        *(parsed + spaceChar) = '\0';
        writeStat(parsed);
        f = fopen(parsed, "rb");
        if(f == NULL){
            strcpy(sendStuff, "HTTP/1.1 404 Not Found\n\n");
            while(amt < strlen(sendStuff)){
               int ret = send(connfd, sendStuff+amt, strlen(sendStuff) - amt, 0);
                if(ret < 0){
                    printf("Send failed");
                    exit(EXIT_FAILURE);
                }
                amt+= ret;
            }
            close(connfd);
        return;
    }
    fseek(f, 0, SEEK_END); // seek to end of file
    size = ftell(f); // get current file size
    fseek(f, 0, SEEK_SET);
    strcpy(sendStuff, "HTTP/1.1 200 OK\nDate: ");
    //Outputting time//////////////////////
    time ( &rawtime );
    timeinfo = gmtime( &rawtime );
    strftime (buffer,80,"%a, %d %b %Y %X GMT\n",timeinfo);
    strcat(sendStuff, buffer);
    /////////////////////////////////////
    sprintf(siz, "Content-Length: %d\n", size);
    strcat(sendStuff, siz);
    strcat(sendStuff, "Connection: close\nContent-Type: text/html\n\n");

    //Send the above data to the person who asked for it.
    while(amt < strlen(sendStuff)){
               int ret = send(connfd, sendStuff+amt, strlen(sendStuff)-amt, 0);
                if(ret < 0){
                    printf("Send failed");
                    exit(EXIT_FAILURE);
                }
                amt+= ret;
            }
    ////////////////////////////////////////////////////

    //Send the file data to the person who asked for it//////////
    while((sizeRet=fread(sendFile, 1, 1024, f)) != 0){
        amt = 0;
        while(amt < sizeRet){
               int ret = send(connfd, sendFile+amt, sizeRet-amt, 0);
                if(ret < 0){
                    printf("Send failed");
                    exit(EXIT_FAILURE);
                }
                amt+= ret;
            }
    }
        fclose(f);

    }
    /////////////////////////////////////////////


	close(connfd);//Close
}

int main(){
	int sfd, connfd;
	struct sockaddr_in addr;
	if((sfd = socket(PF_INET, SOCK_STREAM, 0)) < 0){
		printf("Socket fail\n");
		exit(EXIT_FAILURE);
	}

	memset(addr.sin_zero, 0, sizeof(addr));
	addr.sin_family = AF_INET;
	addr.sin_port = htons(50030);
	addr.sin_addr.s_addr = INADDR_ANY; //automatically find IP

	if((bind(sfd, (struct sockaddr *)&addr, sizeof(addr))) < 0){


       printf("Bind fail\n");
        exit(EXIT_FAILURE);
	}

	if((listen(sfd,10)) < 0){
        printf("Listen failed\n");
        exit(EXIT_FAILURE);
	}
    while(1){
        pthread_t thread1;
        if((connfd = accept(sfd, NULL,NULL))< 0){
            printf("Accept failed\n");
            exit(EXIT_FAILURE);
        }
        pthread_create(&thread1, NULL, doStuff,connfd);
    }
	close(sfd);

	return 0;
}
