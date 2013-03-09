/*
goto_range
	file: FILE to sift throguh
	range: The position you want to go to.
*/
void goto_range(FILE * file, int range);

/*
getArgs:
	option: String of option you want to get( "-d")
	ret: Where to store the result.
return: char * if found, otherwise crash.
*/

char * getArgs(char ** args, int argc, char * option);