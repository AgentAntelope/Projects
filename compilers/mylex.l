%option noyywrap

%{
#include <stdlib.h>
#include "token.h"

extern int yycolumn, yylength, yyline, yylval;
%}

/* regular definitions */

id            [a-z]+
delim         [ \t]
ws            {delim}+
newline       [\n]
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
rsqbracket    "["
rparens       "("
semicolon     ";"



%%
{newline}           {yyline++; yycolumn=0;}
{id}		    {printf("Another ID\n"); return (IDnum);}

%%

int yyline = 0;
int yycolumn = 0;

main()
{
    int lexReturn;
    do {
       lexReturn = yylex();

       printf("return token type is %d\n", lexReturn);

    } while (lexReturn != 0);
}
