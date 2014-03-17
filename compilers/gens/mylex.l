%option noyywrap

%{
//#include "y.tab.h"
#define EOFnum 0
#define SLASHED 1
#define NONE 0
void lowercase(char *, int);
extern int yycolumn, yylength, yyline, yylval;
//extern char * yytext;
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
val           "val"
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
                     else{ input(); return SCONSTnum; }  
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
{id}                {yycolumn+=yyleng; lowercase(yytext, yyleng); return (IDnum);}
{integer}           {yycolumn+=yyleng; return ICONSTnum;}
{invalidid}         {yycolumn+=yyleng;printf("Error. Identifiers can't start with numbers: line %d, column %d.\n", yyline, yycolumn);}
"//"[^\n]*             {}
[%\^#@!$&*_|~?]     {yycolumn+=yyleng;printf("Malformed token at: Line  %d, Column %d\n", yyline, yycolumn);}
%%

int yyline = 0;
int yycolumn = 0;
int start = 257;
char * arr[] = {"ANDnum", "ASSGNnum", "DECLARATIONSnum", "DOTnum", "ENDDECLARATIONSnum", "EQUALnum", "GTnum", "IDnum", "INTnum", "LBRACnum", "LPARENnum", "METHODnum", "NEnum", "ORnum", "PROGRAMnum", "RBRACnum", "RPARENnum", "SEMInum", "VALnum", "WHILEnum", "CLASSnum", "COMMAnum", "DIVIDEnum", "ELSEnum", "EQnum", "GEnum", "ICONSTnum", "IFnum", "LBRACEnum", "LEnum", "LTnum", "MINUSnum", "NOTnum", "PLUSnum", "RBRACEnum", "RETURNnum", "SCONSTnum", "TIMESnum", "VOIDnum"};

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
