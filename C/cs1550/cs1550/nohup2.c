#include <stdio.h>
#include <unistd.h>

int main(int argc, char* argv[]){
	int i;
	if(fork()==0){
		fclose(stdin);
		freopen("stdErrRedirect.out", "a+", stderr);
		freopen("stdInRedirect.in", "a+", stdin);

		
		for(i = 1; i < argc; i++){
			argv[i-1] = argv[i];
		}	
		argv[argc-1] = NULL;
		execvp(argv[0], argv);
	}

	else{
		printf("From parent\n");
	}

}
