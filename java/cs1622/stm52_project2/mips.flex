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

j_opt_code = j
i_opt_code = addi | beq | bne | lw | sw | lui | ori | lb
r_opt_code = add | sub | slt | sll | and | nor | syscall

identifier = [a-zA-Z_][a-zA-Z0-9_]* 
label = {identifier}
new_line = \r|\n|\r\n|\z;



%% 
{integer}         {return new Symbol(INT, yyline +1, yycolumn + 1, new Integer(yytext()));}

"("               {return new Symbol(LPAREN, yyline +1, yycolumn +1);}
")"               {return new Symbol(RPAREN, yyline +1, yycolumn +1);}
".data"           {yybegin(DATA_STATE); return new Symbol(DATA, yyline +1, yycolumn +1, yytext());}
".text"           {yybegin(TEXT_STATE); return new Symbol(TEXT, yyline+ 1, yycolumn + 1, yytext());}
":"               { }
{whitespace}      { }
{new_line}        { }
<<EOF>>           { return new Symbol(EOF, yyline +1, yycolumn + 1);}


<DATA_STATE>{
  {label}/":"	    {return new Symbol(LABEL, yyline +1, yycolumn + 1, yytext());}
  ".asciiz"       {return new Symbol(ASCIIZ, yyline +1, yycolumn + 1, yytext());}
  {string}		    {return new Symbol(STRING, yyline +1, yycolumn + 1, yytext());}

}

<TEXT_STATE>{

  {register}		  {return new Symbol(REGISTER, yyline +1, yycolumn + 1, yytext());}
  {r_opt_code}		{return new Symbol(R_OPT, yyline +1, yycolumn + 1, yytext());}
  {j_opt_code}		{return new Symbol(J_OPT, yyline +1, yycolumn + 1, yytext());}
  {i_opt_code}		{return new Symbol(I_OPT, yyline +1, yycolumn + 1, yytext());}
  {hex}           {String hex = yytext(); Integer converted = Integer.parseInt(hex.substring(2, hex.length()), 16); return new Symbol(INT, yyline +1, yycolumn + 1, converted);}
  {label}/":"		  {return new Symbol(LABEL, yyline +1, yycolumn + 1, yytext());}
  {label}			    {return new Symbol(IMMEDIATE, yyline +1, yycolumn + 1, yytext());}
}
