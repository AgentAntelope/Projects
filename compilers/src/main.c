#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include "proj2.h"


int yyparse();
void startup();

int main()
{
	startup();
	yyparse();
/*

    printf("\t%-20s %-10s %-10s%-10s\n", "token", "line", "column", "str_table");
    do {
		lexReturn = yylex();
		if(lexReturn != 0 && lexReturn != SCONSTnum && lexReturn != IDnum && lexReturn != ICONSTnum){
			printf("\t%-20s %-10d %-10d\n", arr[lexReturn-start], yyline, yycolumn);
		}
		if(lexReturn == SCONSTnum){

		}
		if(lexReturn == IDnum){
		}
		if(lexReturn == ICONSTnum){

		}
    } while (lexReturn != 0);


    printf("String table: ");
    //// Printing string table.
    i = 0;
    while(i < filled){
		if(string_table[i] != 0)
			printf("%c", string_table[i]);
		else{
			printf(" ");
		}
		i++;
    }
    printf("\n");*/
    return 0;
}
