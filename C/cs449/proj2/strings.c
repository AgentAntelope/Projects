#include <stdio.h>

int main(int argc, char* argv[]){
	char c;
	int fSeekNum = 0;
	int printNum = 0;
        FILE *sfile = fopen(argv[1], "rb");

	/* Checks for null file */
	if(sfile == NULL){
		printf("The file you input was not in the working directory or was not found");
	return 0;
	fseek (sfile , 0 , SEEK_SET);
	}
	while(!feof(sfile)){
	fread(&c, 1, 1, sfile);
	if(c >= 32 && c <= 126){
		printNum++;
	}
	else if(printNum >= 4){
		fSeekNum = printNum;
		fSeekNum = fSeekNum+2;
		fSeekNum = 0-fSeekNum;
		fseek(sfile,fSeekNum , SEEK_CUR); 
		while(printNum >= 0){
			fread(&c,1,1,sfile);
			printf("%c", c);
			printNum--;
		}
		printf("\n");
	}
	else{
		printNum = 0;
	}
	}
	fclose(sfile);
 return 0;   
}

