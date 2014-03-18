#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include "proj2.h"


int yyparse();
void startup();
int filled;
int i;
extern char * string_table;
FILE *treelst;

int main()
{
	treelst = stdout;
	startup();
	yyparse();

    return 0;
}
