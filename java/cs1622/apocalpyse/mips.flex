import java.util.*;
import java_cup.runtime.Symbol;

%%
%state DATA_STATE
%state TEXT_STATE
%class MipsLex
%cup
%implements sym, Constants
%line
%column
%ignorecase
%eofclose
%{
  
%}


/*       MACROS!!!!!    */

comment = "#" ~ "\n"
whitespace = \t|\r|\n|\r\n|" " | ({comment}) | ","

string = \"~\"
integer = ("+"|"-")*(0|[1-9][0-9]*)
hex = 0[xX][0-9a-fA-F]+
register = "$r"([1-9][0-9]? | 0)

identifier = [a-zA-Z_][a-zA-Z0-9_]* 
label = {identifier}
new_line = \r|\n|\r\n|\z;



%% 
{integer}                   {return new Symbol(INT, yyline +1, yycolumn + 1, new Integer(yytext()));}
"("               	    {return new Symbol(LPAREN, yyline +1, yycolumn +1);}
")"               	    {return new Symbol(RPAREN, yyline +1, yycolumn +1);}
":"               	    { }
{whitespace}      	    { }
{new_line}        	    { }


"add"			    {return new Symbol(ADD, yyline +1, yycolumn + 1, yytext());}
"addi"			    {return new Symbol(ADDI , yyline +1, yycolumn + 1, yytext());}
"beq"			    {return new Symbol(BEQ , yyline +1, yycolumn + 1, yytext());}
"j"			    {return new Symbol(JUMP , yyline +1, yycolumn + 1, yytext());}
"sb"			    {return new Symbol(SB,  yyline +1, yycolumn + 1, yytext());}
"lb"			    {return new Symbol(LB,  yyline +1, yycolumn + 1, yytext());}
"rand"			    {return new Symbol(RAND,  yyline +1, yycolumn + 1, yytext());}
"disp"			    {return new Symbol(DISP,  yyline +1, yycolumn + 1, yytext());}
"sub"			    {return new Symbol(SUB, yyline +1, yycolumn + 1, yytext());}
"slt"			    {return new Symbol(SLT, yyline +1, yycolumn + 1, yytext());}
"xor"			    {return new Symbol(XOR, yyline +1, yycolumn + 1, yytext());}
"halt"			    {return new Symbol(HALT, yyline +1, yycolumn + 1, yytext());}
","			    {}
{register}		    {return new Symbol(REGISTER, yyline +1, yycolumn + 1, yytext());}
{hex}           	    {String hex = yytext(); Integer converted = Integer.parseInt(hex.substring(2, hex.length()), 16); return new Symbol(INT, yyline +1, yycolumn + 1, converted);}
{label}/":"		    {return new Symbol(LABEL, yyline +1, yycolumn + 1, yytext());}
{label}			    {return new Symbol(IMMEDIATE, yyline +1, yycolumn + 1, yytext());}
<<EOF>>           	    { return new Symbol(EOF, yyline +1, yycolumn + 1);}