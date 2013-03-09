#include <stdio.h>
#include <sys/types.h>
#include <stdlib.h>
#include <string.h>
//#include <bubblesort.h>
//#include <quicksort.h>

int branch1(char * myfile, int number_of_lines)
{
	pid_t pid = fork();
    if (pid == 0) 
	{ 
		//sort the whole array, just to test
		//char * mypath = "/tmp/newFIFO"; //named pipe to communicate with the file being executed
		//char buffer[1000];
		//if (mkfifo(mypath, "r+") != 0)
			//perror("mkfifo() error");
			
		int range_start = 0;
		int range_stop = number_of_lines; 
		
		//execute the bubblesort program by using the syscall execvp
		//bubblesort_function(file, range_start, range_stop, mypath);
		//char * exec_args[] = {"test.c", myfile, "0", "10", NULL};
		//const char * path = "test.c";
		//printf("About to execute bubblesort...\n");
		//execv(path, exec_args);
		
		//char args[][200] = {"test.c", "data1.txt", "0", "10"};
		//execvp(args[0], args); 
		char * args[] = {"hello", "data", (char *)0};
		execv(args[0], args);
		
		//printf("done with bubblesort");
		//read sorted data back from pipe
		//int pipe;
		//int i;
		//pipe = open(mypath, "r"); // open a named pipe
		
		//read(pipe, buffer, number_of_lines); //write the newly sorted array to the named pipe to transfer back to parent

		// close the pipe
		//close(pipe);
	}
}

int main(int argc, char *argv[])
{
	int i;
	int number_of_lines = 0;
	char buffer[100];
    FILE * file;
	
	int depth;
	char * filename;
	int attribute_number;
	
	//char * myfile = "data1.txt";
	 for (i = 1; i < argc; i++)  /* Skip argv[0] (program name). */
    {
        if (strcmp(argv[i], "-d") == 0) 
        {
           depth = atoi(argv[i+1]);
        }
        if (strcmp(argv[i], "-f") == 0)
		{
			filename = argv[i+1];
		}
		if (strcmp(argv[i], "-a") == 0)
		{
			attribute_number = atoi(argv[i+1]);
		}
    }
	
	printf("Depth: %d\n", depth);
	printf("Filename: %s\n", filename);
	printf("Attribute number: %d\n", attribute_number);
	
    file = fopen(filename,"r"); // First, open the file for reading to determine how many lines it contains.
	
    while (!feof(file))
    {
        fgets(buffer, 100, file);
		number_of_lines++;
    }
	
    fclose(file);
	
	//enter depth one in command line
	
	if ((depth < 1) || (depth > 6))
	{
		printf("Error. You must specify a depth between 1 and 6.\n");
	}
	else if (depth == 1)
	{
		branch1(filename, number_of_lines);
	}
	
    return 0;
}
