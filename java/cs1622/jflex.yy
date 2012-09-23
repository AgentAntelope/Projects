/* JFlex example: part of Java language lexer specification */
import java.util.*;

%%
%int
%unicode
%class Lexer
%line
%column
%{
  int numTabs = 0;
  int columnCount = 0;
  public void printTabs(){
    for(int i = 0; i < numTabs; i++){
      System.out.print("\t");    
    }
  }
%}

/*    STATES!!!!!!     */
%state IF_WHILE
%state ELSE
%state LINE_OUT
%state LINE_OUT_BLOCK_COMMENT
%state BLOCK_COMMENT_RETURN
%state BLOCK_COMMENT

/*       MACROS!!!!!    */
Identifier = [a-zA-Z_][a-zA-Z0-9_]*
Line_comment = "//" [^\n]* "\n"

whitespace = \t|\r|\n|\r\n|" "
Integer = ('+'|'-')? (0 | [1-9][0-9]*)
Float = {Integer} "." (0 | [1-9][0-9]*)
Class = class
Comment = "/*" .*? "*/"
Type = (int | boolean | String | double) "[]"*
Keywords = class | public | abstract | static | void | return | length | new
Operators = "+" | "*" | ">" | "<" | "=" | "-" | "=="





%% 

/*  Deals with else which is different than IF because it has no parens  */
<ELSE>{
  "{"                                   { yybegin(YYINITIAL);
                                          System.out.print(" {\n");
                                          numTabs++;
                                          printTabs();
                                        }
  {whitespace} /(.*? ";")             { System.out.println();
                                          numTabs++; 
                                          printTabs(); 
                                          yybegin(LINE_OUT);
                                        }
}

/*  Get the block comment in a weird if-while-else statement.  */
<LINE_OUT_BLOCK_COMMENT>{
  "/*"                                  { System.out.print(yytext());
                                          yybegin(BLOCK_COMMENT_RETURN);
                                          columnCount = yycolumn;}

}

/*  Get the block comment in a weird if-while-else statement.  */
<BLOCK_COMMENT_RETURN>{
  "*/"                                  { System.out.println("*/");
                                          numTabs--;
                                          printTabs();
                                          yybegin(YYINITIAL);
                                          columnCount = 0;
                                        }
  [^\t\r\n\r("*/") ]+                         {
                                          System.out.print(yytext());
                                          columnCount += yytext().length();
                                          if(columnCount >= 80){
                                            System.out.println();
                                            columnCount = 0;
                                          }
                                        }                                                                             
  \r|\n|\r\n                            { System.out.print(yytext()); columnCount = 0;}

  \t|[ ]                                { System.out.print(yytext()); columnCount += 1;}
}

<BLOCK_COMMENT>{
  "*/"                                  { System.out.println("*/"); printTabs(); 
                                          yybegin(YYINITIAL);
                                          columnCount = 0;
                                        }
  [^\t\r\n\r("*/") ]+                         {
                                          System.out.print(yytext());
                                          columnCount += yytext().length();
                                          if(columnCount >= 80){
                                            System.out.println();
                                            columnCount = 0;
                                          }
                                        }                                                                             
  \r|\n|\r\n                            { System.out.print(yytext()); columnCount = 0;}

  \t|[ ]                                { System.out.print(yytext()); columnCount += 1;}
}


/*  State dealing with the famous no bracket if / else / while statements.  */
<LINE_OUT>{
  ";"                                   { System.out.print(";\n");
                                          numTabs--;
                                          printTabs();
                                          yybegin(YYINITIAL);
                                        }
  ";" /({whitespace}* "}")  { System.out.print(";\n");
                                          numTabs-=2;
                                          printTabs();
                                          yybegin(YYINITIAL);
                                        }
  ";" /(({whitespace}|{Comment})* "}")  { System.out.print(";\n");
                                          numTabs-=1;
                                          printTabs();
                                          yybegin(LINE_OUT_BLOCK_COMMENT);
                                        }
}

/*  State to catch the no bracket or go back if it is a normal backeted statement  */
<IF_WHILE>{
  ")" /([^{)]* ";")                     { System.out.print(" )\n"); numTabs++; printTabs(); yybegin(LINE_OUT);}
  ")" /({whitespace}* "{")              { System.out.print(" )"); yybegin(YYINITIAL);}

}

/*  Normal `catch all` conditions  */

  "!"                                   { System.out.print("!"); }
  "/*"                                  { System.out.print(yytext()); yybegin(BLOCK_COMMENT); columnCount = yycolumn; }
  "//" [^\n]* "\n"                      { }
  {whitespace}                          {}
  "if" | "while"                        { yybegin(IF_WHILE);
                                          System.out.print(yytext());
                                        }
  "else"                                { yybegin(ELSE);
                                          System.out.print(yytext());
                                        }
  {Keywords}                            { System.out.print(yytext() + " "); }
  {Type}                                { System.out.print(yytext() + " "); }
  {Type} /({whitespace}* "[")           { System.out.print(yytext()); }
  {Identifier}                          { System.out.print(yytext()); }
  ";"                                   { System.out.println(";");
                                          printTabs();
                                        }
  {Float}                               { System.out.print(yytext()); }
  {Integer}                             { System.out.print(yytext()); }
  ";" /({whitespace}* "}")              { System.out.println(";");
                                          numTabs--; 
                                          printTabs();
                                        }
  {Identifier} /({whitespace}* "{")     { System.out.print(yytext() + " "); }
  "{"                                   { System.out.print("{\n");
                                          numTabs++;
                                          printTabs(); 
                                        }
  "{" /(({whitespace}|{Comment})* "}")  { System.out.print("{\n");
                                          printTabs();
                                        }
  "}" /({whitespace}* "}")              {
                                          System.out.println("}");
                                          numTabs--;
                                          printTabs();
                                        }
  "}"                                   { System.out.println("}");
                                          printTabs();
                                        }
  ","                                   { System.out.print(", ");}

  {Operators}                           { System.out.print(" " + yytext() + " "); }

  /*  Rules for Parenthesis, and Square Brackets  */
  "()" {whitespace}* /"{"               { System.out.print("() "); }
  "()"                                  { System.out.print("()"); }
  "("                                   { System.out.print("( "); }
  ")" {whitespace}* /"{"                { System.out.print(" ) "); }
  ")"                                   { System.out.print(" )"); }
  "[]"                                  { System.out.print("[]"); }
  "["                                   { System.out.print("[ "); }
  "]"                                   { System.out.print(" ]"); }
  "."                                   { System.out.print(".");  }

<<EOF>>                                 { return 1;}
