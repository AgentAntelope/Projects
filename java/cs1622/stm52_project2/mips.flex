/* JFlex example: part of Java language lexer specification */
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
identifier = [a-zA-Z_][a-zA-Z0-9_]* 
comment = "#" [^\n]* "\n"
whitespace = \t|\r|\n|\r\n|" " | ({comment}) | ","
string = \" .*? \"
integer = ('+'|'-')? (0 | [1-9][0-9]*)
register = "$r"([1-9][0-9]? | 0)
j_opt_code = j
i_opt_code = addi | beq | bne | lw | sw | lui | ori
r_opt_code = add | sub | slt | sll | and | nor | syscall
label = {identifier}
new_line = \r|\n|\r\n|\z;
immediate = integer
%% 
/* Possible R-Calls  */
/* Possible i-type calls */
".data"     {yybegin(DATA_STATE); return new Symbol(DATA, yyline +1, yycolumn +1, yytext());}
".text"     {yybegin(TEXT_STATE); return new Symbol(TEXT, yyline+ 1, yycolumn + 1, yytext());}
":"  { }
{whitespace} {}
{new_line} {}
<<EOF>>                                 { return new Symbol(EOF, yyline +1, yycolumn + 1);}


<DATA_STATE>{
  {label}/":"	{ System.out.println(yytext()); return new Symbol(LABEL, yyline +1, yycolumn + 1, yytext());}
  ".asciiz" {return new Symbol(ASCIIZ, yyline +1, yycolumn + 1, yytext());}
  {string}		{System.out.println(yytext()); return new Symbol(STRING, yyline +1, yycolumn + 1, yytext());}

}
<TEXT_STATE>{

  {register}		 {return new Symbol(REGISTER, yyline +1, yycolumn + 1, yytext());}
  {r_opt_code}		 {return new Symbol(R_OPT, yyline +1, yycolumn + 1, yytext());}
  {j_opt_code}		 {return new Symbol(J_OPT, yyline +1, yycolumn + 1, yytext());}
  {i_opt_code}		 {return new Symbol(I_OPT, yyline +1, yycolumn + 1, yytext());}
  {integer}			 {return new Symbol(INT, yyline +1, yycolumn + 1, new Integer(yytext()));}
  {label}/":"		{return new Symbol(LABEL, yyline +1, yycolumn + 1, yytext());}
  {label}			{return new Symbol(IMMEDIATE, yyline +1, yycolumn + 1, yytext());}
}
