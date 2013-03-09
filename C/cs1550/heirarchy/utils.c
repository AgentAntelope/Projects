#include <stdio.h>
#include <stdlib.h>
#include "utils.h"
int main(int argc, char** argv){
	if(argc < 2){
		printf("Not enough arguments, try again\n");
		return -1;
	}
	char fname[200];
	char lname[200];
	int id, income;
	char * file_name = getArgs(argv, argc, "-f");
	printf("%s\n", file_name);
	FILE * f = fopen(file_name, "r");
	char * d = getArgs(argv, argc, "-d");
	printf("%s\n", d);
	gotoRange(f, 8);
	fscanf(f, "%d %s %s %d\n", &id, fname, lname, &income);
	printf("%d %s %s %d\n", id, fname, lname, income);

	return 0;
}


void goto_range(FILE * file, int range){
	int i = 0;
	fseek(file, 0, SEEK_SET);
	for(i = 1; i < range; i++){
		char fname[200];
		char lname[200];
		int id, income;
		fscanf(file, "%d %s %s %d\n", &id, fname, lname, &income);
		//printf("%d %s %s %d\n", id, fname, lname, income);
	}
}

char * get_args(char ** args, int argc, char * option){
	
	int i = 0;
	for (i = 0; i < argc; ++i)
	{
		if(strcmp(args[i], option) == 0 && (i + 1) < argc){

			return args[i + 1];
		}
	}
	printf("Couldn't find a valid arg: %s, exiting\n", option);
	exit(EXIT_FAILURE);
}
