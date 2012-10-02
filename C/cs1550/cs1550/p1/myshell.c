//Author Sean Myers
//Class: CS 1550
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <errno.h>
//////////////////////////////////////////////
extern char **getline();
void spawn_child(char**);
void change_directory(char*);
void pipeControl(int position, char **args);
void redirectCheck(char **args);
/////////////////

main() {
  int i, alreadySpawned;
  char **args; 

  while(1) {
    printf("myShell:~$ ");
    args = getline();
    alreadySpawned = 0;//Two ways to spawn children, pipe and spawn child. If this is set to one, don't run the other.

    for(i = 0; args[i] != NULL; i++) {

	if(!strcmp(args[i], "exit")){
		printf("Now exiting...");
		exit(0);
	}

	else if(!strcmp(args[i], "cd")){
		if(args[i+1]!=NULL){
			change_directory(args[i+1]);
		}
		else{
			printf("No directory specified \n");
		}
		alreadySpawned = 1; //Don't spawn children on changdir
		break;
	}
	else if(!strcmp(args[i], "|")){
		alreadySpawned = 1;
		pipeControl(i, args);
		break;
	}
    }
	if(alreadySpawned == 0)
		spawn_child(args); 
  }
}

void spawn_child(char **args){
	int child_id, wait_status, possible_failure;
	if((child_id = fork())== 0){
		redirectCheck(args);
		execvp(args[0], args);
		perror("Could not execute");
		exit(0);
	}
	else{
		wait(&wait_status);
	}

}
void change_directory(char *args){	
	int possible_failure = 0;
	possible_failure = chdir(args);
	if(possible_failure < 0){
		perror("Error changing directory");
	}
}
void redirectFile(int start_position,int position, char **args){
	if(args[position+1] == NULL){
		printf("No redirect file specified..\n");
		exit(0);
	}
	freopen(args[position+1], "w", stdout);
}

void pipeControl(int position, char **args){
	int fd[2], i;
	if(fork() == 0)
	{
		
		if(pipe(fd)== -1){
			perror("pipe");
			exit(EXIT_FAILURE);
		}
		
		if(fork() == 0)
		{
			close(fd[0]);
			if(dup2(fd[1], 1) == -1){
				perror("dup");
				exit(EXIT_FAILURE);
			}
			close(fd[1]);
			for(i = position; args[i] != NULL; i++){
				args[i] = NULL;
			}
			execvp(args[0], args);
			perror("Problem executing program");
			exit(0);	
		}
		else
		{
			
			int newPosition = 0;
			if(args[position+1] == NULL){
				printf("Invalid command --not enough arguments after pipe\n");
				exit(0);
			}
			for(i = 1; args[position+i] != NULL; i++){
				args[i-1] = args[position+i];
				newPosition++;
			}
			for(i = newPosition+1; args[i] != NULL; i++){
				args[i] = NULL;
			}

			close(fd[1]);
			dup2(fd[0],0);
			close(fd[0]);		
			redirectCheck(args);
			execvp(args[0], args);
			perror("Problem executing program");
			exit(0);
		}
	}
	else
	{
			int status;
			wait(&status);
	}
}
void redirectCheck(char **args){
	int i, startDeletingTokens;
	startDeletingTokens = 0;
	for(i = 0; args[i] != NULL; i++){
		if(!strcmp(args[i], ">")){
			if(args[i+1] == NULL){
				printf("No redirect file specified.\n");
				exit(0);
			}
			startDeletingTokens = 1;
			fclose(stdout);
			if(freopen(args[i+1], "w", stdout) == NULL){
				perror("redirection failed");
				exit(EXIT_FAILURE);
			}
		}

		if(startDeletingTokens){
			args[i] = NULL; //Assuming here that file redirect is the last thing on the command,
			args[i+1] = NULL;
			// so delete the rest of the line(should only delete the redirect token and file name)
		}
	}
}
