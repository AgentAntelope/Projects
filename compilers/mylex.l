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
rsqbracket    "]"
rparens       ")"
semicolon     ";"
val           "val"
while         "while"
class         "class"
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





%%
{newline}           {yyline++; yycolumn=0;}
{or}                { yycolumn+=2;return (ORnum);}
{and}               { yycolumn+=2;return (ANDnum);}
{assign}            { yycolumn+=2;return (ASSGNnum); }
{decls}             { yycolumn+=12;return (DECLARATIONSnum);}
{dot}               { yycolumn+=1;return (DOTnum);}
{end-decls}         {  yycolumn+=15;return (ENDDECLARATIONSnum);}
{equals}            { yycolumn+=1;return (EQUALnum); }
{greater}           { yycolumn+=1;return (GTnum);}
{lsqbracket}        {yycolumn+=1;return (LBRACnum);}
{lparens}           {yycolumn+=1;return (LPARENnum);}
{method}            {yycolumn+=6;return (METHODnum);}
{notequal}          {yycolumn+=2;return (NEnum);}
{program}           {yycolumn+=7;return (PROGRAMnum);}
{rsqbracket}        {yycolumn+=1;return (RBRACnum);}
{rparens}           {yycolumn+=1;return (RPARENnum);}
{semicolon}         {yycolumn+=1;return (SEMInum);}
{val}               {yycolumn+=3;return (VALnum);}
{while}             {yycolumn+=5;return (WHILEnum);}
{class}             {yycolumn+=5;return (CLASSnum);}
{comma}             {yycolumn+=1;return (COMMAnum);}
{division}          {yycolumn+=1;return (DIVIDEnum);}
{else}              {yycolumn+=4;return (ELSEnum);}
{eq}                {yycolumn+=2;return (EQnum);}
{greaterequal}      {yycolumn+=1;return (GEnum);}
{if}                {yycolumn+=2;return (IFnum);}
{leftbracket}       {yycolumn+=1;return (LBRACEnum);}
{lessequal}         {yycolumn+=2;return (LEnum);}
{less}              {yycolumn+=1;return (LTnum);}
{minus}             {yycolumn+=1;return (MINUSnum);}
{not}               {yycolumn+=1;return (NOTnum);}
{plus}              {yycolumn+=1;return (PLUSnum);}
{rightbracket}      {yycolumn+=1;return (RBRACEnum);}
{return}            {yycolumn+=6;return (RETURNnum);}
{times}             {yycolumn+=1;return (TIMESnum);}
{void}              {yycolumn+=4;return (VOIDnum);}

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