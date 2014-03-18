%option noyywrap

%{
#include "y.tab.h"
#include "proj2.h"
#define EOFnum 0
#define SLASHED 1
#define NONE 0
#define START_SIZE 20

void lowercase(char *, int);
int find_string(char * str, int word_size, char * str_table, int filled, int size);
char * manage_string_table(char * old_table, int current_size);
void manage_string(char * str, int size, char ** str_table, int * filled, int * str_table_size);
int check_str_table(char * needle, char * haystack);

YYSTYPE yylval;
extern int yycolumn, yylength, yyline;
char * strg_tbl;
int yyline = 0;
int yycolumn = 0;
int start = 257;
int lexReturn;
int stringtable_size = START_SIZE;
int filled = 0;
int i = 0;
char * string_table;
char * arr[] = {"ANDnum", "ASSGNnum", "DECLARATIONSnum", "DOTnum", "ENDDECLARATIONSnum", "EQUALnum", "GTnum", "IDnum", "INTnum", "LBRACnum", "LPARENnum", "METHODnum", "NEnum", "ORnum", "PROGRAMnum", "RBRACnum", "RPARENnum", "SEMInum", "VALnum", "WHILEnum", "CLASSnum", "COMMAnum", "DIVIDEnum", "ELSEnum", "EQnum", "GEnum", "ICONSTnum", "IFnum", "LBRACEnum", "LEnum", "LTnum", "MINUSnum", "NOTnum", "PLUSnum", "RBRACEnum", "RETURNnum", "SCONSTnum", "TIMESnum", "VOIDnum"};

int flag = NONE;
%}

/* regular definitions */
id            [a-zA-Z][a-zA-Z0-9]*
invalidid     [0-9][a-zA-Z0-9]*
integer       [1-9][0-9]*|0

delim         [ \t]
commentstart  "/*"
commentend    "*/"
ws            {delim}+
newline       [\n]
comment       {commentstart}({newline}|.)*/{commentend}
commentunend  {commentstart}({newline}|.)*
or            "||"
and           "&&"
assign        ":="
decls         "declarations"
dot           "."
end-decls     "enddeclarations"
equals        "="
greater       ">"
lsqbracket    "["
lparens       "("
method        "method"
notequal      "!="
program       "program"
rsqbracket    "]"
rparens       ")"
semicolon     ";"
val           [vV][aA][lL]
while         "while"
class         "class"
int           "int"
comma         ","
division      "/"
else          "else"
eq            "=="
greaterequal  ">="
if            "if"
leftbracket   "{"
lessequal     "<="
less          "<"
minus         "-"
not           "!"
plus          "+"
rightbracket  "}"
return        "return"
times         "*"
void          "void"


%s WHITE
%s STRCONST

%%
\'[^'\n]*             {yycolumn+=yyleng;if(yytext[yyleng-1] == '\\') yymore();
                      else if(yytext[yyleng-1] == '\n'){ printf("String literals cant be spaced on multiple lines: line %d, column %d", yyline, yycolumn);yymore();}
                     else{ 
                           input();
			   int pos = filled;
			   int possible_pos = 0;
			   possible_pos = find_string(yytext + 1, yyleng, string_table, filled, stringtable_size);

		           pos = (possible_pos > 0)?(possible_pos):(filled);
			   if(possible_pos < 0){
				manage_string(yytext + 1, yyleng, &string_table, &filled, &stringtable_size);
			   }
                           yylval.intg = pos;
                           return SCONSTnum;
                        }
                    }
<WHITE>"/*"         {yycolumn+=2;}
<WHITE>"*/"         {BEGIN INITIAL; yycolumn+=2; }
<WHITE>[^*\n]+      {yycolumn+=yyleng;}
<WHITE><<EOF>>      {printf("Error, unended comment! Line %d, column %d\n", yyline, yycolumn); return 0;}

"/*"                {BEGIN WHITE; yycolumn+=2; }


{delim}             {yycolumn+=yyleng;}
{newline}           {yyline++; yycolumn=0;}
{int}               {yycolumn+=yyleng; return (INTnum);}
{or}                {yycolumn+=yyleng; return (ORnum);}
{and}               {yycolumn+=yyleng; return (ANDnum);}
{assign}            {yycolumn+=yyleng; return (ASSGNnum); }
{decls}             {yycolumn+=yyleng; return (DECLARATIONSnum);}
{dot}               {yycolumn+=yyleng; return (DOTnum);}
{end-decls}         {yycolumn+=yyleng; return (ENDDECLARATIONSnum);}
{equals}            {yycolumn+=yyleng; return (EQUALnum); }
{greater}           {yycolumn+=yyleng; return (GTnum);}
{lsqbracket}        {yycolumn+=yyleng; return (LBRACnum);}
{lparens}           {yycolumn+=yyleng; return (LPARENnum);}
{method}            {yycolumn+=yyleng; return (METHODnum);}
{notequal}          {yycolumn+=yyleng; return (NEnum);}
{program}           {yycolumn+=yyleng; return (PROGRAMnum);}
{rsqbracket}        {yycolumn+=yyleng; return (RBRACnum);}
{rparens}           {yycolumn+=yyleng; return (RPARENnum);}
{semicolon}         {yycolumn+=yyleng; return (SEMInum);}
{val}               {yycolumn+=yyleng; return (VALnum);}
{while}             {yycolumn+=yyleng; return (WHILEnum);}
{class}             {yycolumn+=yyleng; return (CLASSnum);}
{comma}             {yycolumn+=yyleng; return (COMMAnum);}
{division}          {yycolumn+=yyleng; return (DIVIDEnum);}
{else}              {yycolumn+=yyleng; return (ELSEnum);}
{eq}                {yycolumn+=yyleng; return (EQnum);}
{greaterequal}      {yycolumn+=yyleng; return (GEnum);}
{if}                {yycolumn+=yyleng; return (IFnum);}
{leftbracket}       {yycolumn+=yyleng; return (LBRACEnum);}
{lessequal}         {yycolumn+=yyleng; return (LEnum);}
{less}              {yycolumn+=yyleng; return (LTnum);}
{minus}             {yycolumn+=yyleng; return (MINUSnum);}
{not}               {yycolumn+=yyleng; return (NOTnum);}
{plus}              {yycolumn+=yyleng; return (PLUSnum);}
{rightbracket}      {yycolumn+=yyleng; return (RBRACEnum);}
{return}            {yycolumn+=yyleng; return (RETURNnum);}
{times}             {yycolumn+=yyleng; return (TIMESnum);}
{void}              {yycolumn+=yyleng; return (VOIDnum);}
{id}                {yycolumn+=yyleng; 
                        lowercase(yytext, yyleng); 
			int pos = filled;
			int possible_pos = 0;
			possible_pos = find_string(yytext, yyleng+1,string_table, filled, stringtable_size);

			pos = (possible_pos >= 0)?(possible_pos):(filled);
			if(possible_pos < 0){
				manage_string(yytext, yyleng +1, &string_table, &filled, &stringtable_size);
                        }
                        yylval.intg = pos;
                        return (IDnum);}
{integer}           {   yycolumn+=yyleng; 
                        yylval.intg = atoi(yytext);
                      return ICONSTnum;}
{invalidid}         {yycolumn+=yyleng;printf("Error. Identifiers can't start with numbers: line %d, column %d.\n", yyline, yycolumn);}
"//"[^\n]*             {}
[%\^#@!$&*_|~?]     {yycolumn+=yyleng;printf("Malformed token at: Line  %d, Column %d\n", yyline, yycolumn);}
%%

/* Turns anything in the buffer to lowercase character */
void lowercase(char * buffer, int length){
   char amt =  97 - 65;
   int i;
   for(i = 0; i < length; i++){
	   if(buffer[i] >= 65 && buffer[i] <= 90){
	        buffer[i] += amt;
	   }
   }
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

void startup(){
    string_table = manage_string_table(0, stringtable_size);    
    strg_tbl = string_table;

}
