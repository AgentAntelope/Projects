/* JFlex example: part of Java language lexer specification */
import java.util.*;
/**
 * This class is a simple example lexer.
 */
%%
%int
%class Lexer
%line
%column
%{
  int numTabs = 0;
  Stack a;
  public void printTabs(){
    for(int i = 0; i < numTabs; i++){
      System.out.print("\t");    
    }
  }
%}
%state STRING
%state NEWLINE
Identifier = [a-zA-Z_][a-zA-Z0-9_]*
whitespace = \t|\r|\n|\r\n|[ ]
Integer = 0 | [1-9][0-9]*
Class = class
Keywords = class | public | abstract | static | void | int | boolean | String
%%
"+" {System.out.print("  " + yytext() + " ");}
{Integer} {System.out.print(yytext());}
{whitespace} {}
"}"                   { numTabs--; printTabs(); System.out.print("}\n"); yybegin(NEWLINE);}

<YYINITIAL> {

  {Keywords}            { System.out.print(yytext() + " "); }
  {Identifier}          { System.out.print(yytext()); }
  ";"                   { System.out.println(";");
                          yybegin(NEWLINE);
                        }
  {Identifier}          { System.out.print(yytext()); }
  "{"                   { System.out.print(" {\n");
                          numTabs++;
                          printTabs(); 
                        }
}
<NEWLINE> {
  {Keywords}            {
                          printTabs();
                          System.out.print(yytext() + " ");
                        }
  {Identifier}          { 
                          printTabs();
                          System.out.print(yytext());
                          yybegin(YYINITIAL);
                        }
}
<<EOF>> {}
