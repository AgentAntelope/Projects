

%{
    #include "proj2.h"
	#include<stdio.h>
	int yyline;
	int yycolumn;
	int yylex(void);
	int yyerror (char *msg) ;
 %}

%start program
%token	ANDnum			
%token ASSGNnum		
%token DECLARATIONSnum		
%token DOTnum			
%token ENDDECLARATIONSnum	
%token EQUALnum		
%token GTnum			
%token IDnum			
%token INTnum			
%token LBRACnum		
%token LPARENnum		
%token METHODnum		
%token NEnum			
%token ORnum			
%token PROGRAMnum		
%token RBRACnum		
%token RPARENnum		
%token SEMInum			
%token VALnum			
%token WHILEnum		
%token CLASSnum		
%token COMMAnum		
%token DIVIDEnum		
%token ELSEnum			
%token EQnum			
%token GEnum			
%token ICONSTnum		
%token IFnum			
%token LBRACEnum		
%token LEnum			
%token LTnum			
%token MINUSnum		
%token NOTnum			
%token PLUSnum			
%token RBRACEnum		
%token RETURNnum		
%token SCONSTnum		
%token TIMESnum		
%token VOIDnum			
%left NOTnum
%left PLUSnum
%left MINUSnum
%left TIMESnum
%left DIVIDEnum
%%
program              :  PROGRAMnum IDnum SEMInum class_decls;

class_decls          :  class_decl
                     |  class_decls class_decl
;

class_decl           :  CLASSnum IDnum class_body;

class_body           :  LBRACEnum RBRACEnum
                     |  LBRACEnum decls RBRACEnum
                     |  LBRACEnum decls method_decls RBRACEnum
                     |  LBRACEnum method_decls RBRACEnum
;

decls                :  DECLARATIONSnum field_decls ENDDECLARATIONSnum
		             |  DECLARATIONSnum ENDDECLARATIONSnum
;

field_decls          :  field_decl
                     |  field_decls field_decl
;

field_decl           :  type var_decl;

var_decl             :  var_decl_id EQUALnum variable_initializer SEMInum
                     |  var_decl_id SEMInum
                     |  var_decl_id COMMAnum var_decl
                     |  var_decl_id EQUALnum variable_initializer COMMAnum var_decl
;

var_decl_id          :  IDnum braces
                     |  IDnum
;

variable_initializer :  expression
                     |  array_init
                     |  array_creation
;

array_init        :  LBRACEnum variable_init_list RBRACEnum;

variable_init_list : variable_initializer
                   | variable_init_list COMMAnum variable_initializer
;

array_creation    :  INTnum array_expr
;

array_expr        :  LBRACnum expression RBRACnum
                  |  array_expr LBRACnum expression RBRACnum
;

method_decls      :  method_decl
                  |  method_decls method_decl
;

method_decl       :  METHODnum type IDnum LPARENnum params RPARENnum block
                  |  METHODnum type IDnum LPARENnum RPARENnum block
                  |  METHODnum VOIDnum IDnum LPARENnum params RPARENnum block
                  |  METHODnum VOIDnum IDnum LPARENnum RPARENnum block
;

params            : INTnum identifier_list
                  | VALnum INTnum identifier_list SEMInum params
                  | VALnum INTnum identifier_list
                  | INTnum identifier_list SEMInum params

;

identifier_list   :  IDnum
                  |  identifier_list COMMAnum IDnum
;

block             :   decls statement_list
                  |   statement_list
;

type              :   INTnum
                  |   INTnum braces
                  |   INTnum braces DOTnum type
                  |   IDnum 
                  |   IDnum braces
                  |   IDnum braces DOTnum type
;

braces            :  LBRACnum RBRACnum
                  |  braces LBRACnum RBRACnum
;

statement_list    :   LBRACEnum statement_inner RBRACEnum
;

statement_inner   :   statement
                  |   statement_inner SEMInum statement
				  ;
statement         :   assign_stmt
                  |   method_call_stmt
                  |   return_stmt
                  |   if_stmt
                  |   while_stmt
		          |
;

assign_stmt       :   variable ASSGNnum expression;

method_call_stmt  :   variable LPARENnum RPARENnum
                  |   variable LPARENnum indece_expr RPARENnum
;


return_stmt   :   RETURNnum expression
              |   RETURNnum
              ;

if_stmt       :   IFnum expression statement_list
              |   IFnum expression statement_list ELSEnum statement_list;
              |   IFnum expression statement_list ELSEnum if_stmt
			  ;


while_stmt       :   WHILEnum expression statement_list;


variable      :      IDnum
              |      IDnum selection 
              ;

selection     :      LBRACnum indece_expr RBRACnum
		      |      DOTnum IDnum
              |      DOTnum IDnum selection
              |      LBRACnum indece_expr RBRACnum selection
;

indece_expr   :      expression
              |      expression COMMAnum indece_expr
              ;

expression    :     simple_expression
              |     simple_expression LTnum simple_expression
              |     simple_expression LEnum simple_expression
              |     simple_expression EQnum simple_expression
              |     simple_expression NEnum simple_expression
              |     simple_expression GEnum simple_expression
              |     simple_expression GTnum simple_expression
              ;

simple_expression  : optional_plus_minus term
                   |  optional_plus_minus term other_term
;

optional_plus_minus :  
                   |  PLUSnum
                   |  MINUSnum
				   ;

other_term    :    PLUSnum term
              |    MINUSnum term
              |    ORnum term
              |    other_term other_term;
;

term          :    factor
              |    factor other_factor
;

other_factor  :   TIMESnum factor 
              |   DIVIDEnum factor 
              |   ANDnum factor 
              |   other_factor other_factor
;

factor        :   SCONSTnum
              |   ICONSTnum
              |   variable
	          |   method_call_stmt
              |   LPARENnum expression RPARENnum
              |   NOTnum factor
              ;






%%
int yyerror(char *s){
	printf("FAIL %s %d, line %d, column: %d\n", s, yychar, yyline, yycolumn);
}

