#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include "lex.yy.c"
#include "token.h"

#define START_SIZE 20

extern int yyleng;
extern char * yytext;

int find_string(char * str, int word_size, char * str_table, int filled, int size);
char * manage_string_table(char * old_table, int current_size);
void manage_string(char * str, int size, char ** str_table, int * filled, int * str_table_size);
int check_str_table(char * needle, char * haystack);


int main()
{
    int lexReturn;
    int stringtable_size = START_SIZE;
    int filled = 0;
    int i = 0;
    char * string_table;
    string_table = manage_string_table(0, stringtable_size);    

    printf("\t%-20s %-10s %-10s%-10s\n", "token", "line", "column", "str_table");
    do {
       lexReturn = yylex();
       if(lexReturn != 0 && lexReturn != SCONSTnum && lexReturn != IDnum && lexReturn != ICONSTnum){
           printf("\t%-20s %-10d %-10d\n", arr[lexReturn-start], yyline, yycolumn);
       }
       if(lexReturn == SCONSTnum){
	   int pos = filled;
	   int possible_pos = 0;
	   possible_pos = find_string(yytext + 1, yyleng, string_table, filled, stringtable_size);

           pos = (possible_pos > 0)?(possible_pos):(filled);
      	   printf("\t%-20s %-10d %-10d%-10d\n", arr[lexReturn-start], yyline, yycolumn, pos);
	   if(possible_pos < 0)
	     manage_string(yytext + 1, yyleng, &string_table, &filled, &stringtable_size);

       }
       if(lexReturn == IDnum){
	   int pos = filled;
	   int possible_pos = 0;
	   possible_pos = find_string(yytext, yyleng+1,string_table, filled, stringtable_size);

           pos = (possible_pos >= 0)?(possible_pos):(filled);
      	   printf("\t%-20s %-10d %-10d%-10d\n", arr[lexReturn-start], yyline, yycolumn, pos);
	   if(possible_pos < 0)
	     manage_string(yytext, yyleng +1, &string_table, &filled, &stringtable_size);

       }
       if(lexReturn == ICONSTnum){
	   int pos = filled;
	   int possible_pos = 0;
	   possible_pos = find_string(yytext, yyleng+1,string_table, filled, stringtable_size);

           pos = (possible_pos >= 0)?(possible_pos):(filled);
      	   printf("\t%-20s %-10d %-10d%-10d\n", arr[lexReturn-start], yyline, yycolumn, pos);
	   if(possible_pos < 0)
	     manage_string(yytext, yyleng +1, &string_table, &filled, &stringtable_size);

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
    printf("\n");
    return 0;
}

/* Checks if a string is in the string table. Takes size into account, NO BUFFER OVERFLOW */
int find_string(char * str, int word_size, char * str_table, int filled, int size){
    // Check if the string is in the str_table.
    int position = 0;
    while(position < filled && word_size < size){
      if(strcmp(str, str_table + position) == 0){
	return position;
      }
      else{
	// SEEEEEEEEEK
	while(str_table[position++] != '\0');	
      }
    }
    return -1;
}

/* Puts a string into the string table. Manages size properly. */
void manage_string(char * str, int size, char ** str_table, int * filled, int * str_table_size){
    while((*str_table_size - *filled) < size){
       *str_table = manage_string_table(*str_table, *str_table_size);
       *str_table_size *= 2;
    }
    memcpy((*str_table) + *filled, str, size);

    *filled = (*filled) + size;
}

/* Takes a string table, copies it to a doubly sized array and returns that */
char * manage_string_table(char * old_table, int current_size){
     int new_size = current_size * 2;
     char * new_table = (char *) malloc(new_size);
     if(new_table == 0){ 
         exit(-1); // Bad news malloc bears.
     }
     memset(new_table, 0, new_size);

     if(old_table == 0){
         return new_table;
     }
     else{
        memcpy(new_table, old_table, current_size);
        return new_table;
     }
}
