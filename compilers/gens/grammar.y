

%{
    #include "proj2.h"
	#include<stdio.h>
	int yyline;
	int yycolumn;
	int yylex(void);
	int yyerror (char *msg) ;
	tree type_ptr;
 %}

%start program
%token	ANDnum			
%token ASSGNnum		
%token DECLARATIONSnum		
%token DOTnum			
%token ENDDECLARATIONSnum	
%token EQUALnum		
%token GTnum			
%token <intg> IDnum			
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
%token <intg> ICONSTnum		
%token IFnum			
%token LBRACEnum		
%token LEnum			
%token LTnum			
%token MINUSnum		
%token NOTnum			
%token PLUSnum			
%token RBRACEnum		
%token RETURNnum		
%token <intg> SCONSTnum		
%token TIMESnum		
%token VOIDnum			
%left NOTnum
%left PLUSnum
%left MINUSnum
%left TIMESnum
%left DIVIDEnum
	 %type <tptr> program, class_decls, class_decl, class_body, method_decls, method_decl, decls, field_decls, field_decl, type, type_braces, field_op_type, var_decl, var_decl_id, variable_initializer,variable_init_list, array_init, array_expr, array_creation, expression, simple_expression, term, other_term, factor, other_factor, optional_plus_minus, variable, selection, indece_expr, statement_list, statement, while_stmt, if_stmt, return_stmt, method_call_stmt, assign_stmt, statement_inner, voids, params, parama_bubble, block, identifier_list;
    
%%
program              :  PROGRAMnum IDnum SEMInum class_decls
	                   {$$ = MakeTree(ProgramOp, $4, MakeLeaf(IDNode, $2)); printtree($$, 0);}
;
                       

class_decls          :  class_decl
                       {$$ = MakeTree(ClassOp, NullExp(), $1);}
                     |  class_decls class_decl
                       {$$ = MakeTree(ClassOp, $1, $2);}
;

class_decl           :  CLASSnum IDnum class_body;
	                   {$$ = MakeTree(ClassDefOp, $3, MakeLeaf(IDNode, $2));}

class_body           :  LBRACEnum RBRACEnum
	                   {$$ = NullExp();}
                     |  LBRACEnum method_decls RBRACEnum
	                   {$$ = $2;}
;

decls                :  DECLARATIONSnum field_decls ENDDECLARATIONSnum
	                   {$$ = $2;}
                     |  DECLARATIONSnum ENDDECLARATIONSnum
	                   {$$ = NullExp();}
;

field_decls          :  field_decl
		               {$$ = MakeTree(DeclOp, NullExp(), $1);}
                     |  field_decls field_decl
	                   {$$ = MakeTree(DeclOp, $1, $2);}
;

field_decl           :  type var_decl;
	                   {$$ = $2;}

var_decl             :  var_decl_id EQUALnum variable_initializer SEMInum
	                   {tree temp = MakeTree(CommaOp, $1, MakeTree(CommaOp, type_ptr, $3)); $$ = MakeTree(DeclOp, NullExp(), temp);}
                     |  var_decl_id SEMInum
	                   {tree temp = MakeTree(CommaOp, $1, MakeTree(CommaOp, type_ptr, NullExp())); $$ = MakeTree(DeclOp, NullExp(), temp);}
                     |  var_decl_id COMMAnum var_decl
	                   {tree temp = MakeTree(CommaOp, $1, MakeTree(CommaOp, type_ptr, NullExp())); $$ = MakeTree(DeclOp, $3, temp);}
                     |  var_decl_id EQUALnum variable_initializer COMMAnum var_decl
	                   {tree temp = MakeTree(CommaOp, $1, MakeTree(CommaOp, type_ptr, $3)); $$ = MakeTree(DeclOp, $5, temp);}
;

var_decl_id          :  IDnum braces
	                   {$$ = MakeLeaf(IDNode, $1);}
                     |  IDnum
	                   {$$ = MakeLeaf(IDNode, $1);}
;

variable_initializer :  expression
		               {$$ = $1;}
                     |  array_init
					   {$$ = $1;}
                     |  array_creation
					   {$$ = $1;}
;

array_init        :  LBRACEnum variable_init_list RBRACEnum;
	                   {$$ = MakeTree(ArrayTypeOp, $2, type_ptr);}

variable_init_list : variable_initializer
		               {$$ = MakeTree(CommaOp, NullExp(), $1);}
                   | variable_initializer COMMAnum variable_init_list
	                   {$$ = MakeTree(CommaOp, $3, $1);}
;

array_creation    :  INTnum array_expr
	                   {$$ = MakeTree(ArrayTypeOp, $2, MakeLeaf(INTEGERTNode, 0));}
;

array_expr        :  LBRACnum expression RBRACnum
	                   {$$ = MakeTree(BoundOp, NullExp(), $2 );}
                  |  LBRACnum expression RBRACnum array_expr
	                   {$$ = MakeTree(BoundOp, $4, $2);}
;

method_decls      :  method_decl
		               {$$ = MakeTree(BodyOp, NullExp(), $1);}
                  |  decls method_decl
	                   {$$ = MakeTree(BodyOp, $1, $2);}
                  |  decls
					   {$$ = MakeTree(BodyOp, $1, NullExp());}
                  |  method_decls method_decl
	                   {$$ = MakeTree(BodyOp, $1, $2);}
;

method_decl       :  METHODnum type IDnum LPARENnum parama_bubble RPARENnum block
	                   {$$ = MakeTree(MethodOp, MakeTree(HeadOp, MakeLeaf(IDNode, $3), $5), $7);}
                  |  METHODnum voids IDnum LPARENnum parama_bubble RPARENnum block
	                   {$$ = MakeTree(MethodOp, MakeTree(HeadOp, MakeLeaf(IDNode, $3), $5), $7);}
;

voids : VOIDnum
	                   {type_ptr = NullExp();}
;

parama_bubble     : params
		               {$$ = MakeTree(SpecOp, $1, type_ptr);}
                  |
		               {$$ = MakeTree(SpecOp, NullExp(), type_ptr);}
		 ;
params            : INTnum identifier_list
	                   {$$ = MakeTree(RArgTypeOp, MakeTree(CommaOp, $2, MakeLeaf(INTEGERTNode, 0)), NullExp());}
                  | VALnum INTnum identifier_list SEMInum params
	                   {$$ = MakeTree(VArgTypeOp, MakeTree(CommaOp, $3, MakeLeaf(INTEGERTNode, 0)), $5);}
                  | VALnum INTnum identifier_list
	                   {$$ = MakeTree(VArgTypeOp, MakeTree(CommaOp, $3, MakeLeaf(INTEGERTNode, 0)), NullExp());}
                  | INTnum identifier_list SEMInum params
	                   {$$ = MakeTree(RArgTypeOp, MakeTree(CommaOp, $2, MakeLeaf(INTEGERTNode, 0)), $4);}
;

identifier_list   :  IDnum
		               {$$ = MakeTree(CommaOp, MakeLeaf(IDNode, $1), NullExp());}
                  |  identifier_list COMMAnum IDnum
	                   {$$ = MakeTree(CommaOp, MakeLeaf(IDNode, $3), $1);}
;

block             :   decls statement_list
	                   {$$ = MakeTree(BodyOp, $1, $2);}
                  |   statement_list
					   {$$ = MakeTree(BodyOp, NullExp(), $1);}
;

type              :   INTnum
		               {$$ = MakeTree(TypeIdOp, MakeLeaf(INTEGERTNode, 0), NullExp()); type_ptr = $$;}
                  |   INTnum type_braces
	                   {$$ = MakeTree(TypeIdOp,MakeLeaf(INTEGERTNode, 0), $2); type_ptr = $$;}
                  |   IDnum 
					   {$$ = MakeTree(TypeIdOp, MakeLeaf(IDNode, $1), NullExp()); type_ptr = $$;}
                  |   IDnum type_braces
	                   {$$ = MakeTree(TypeIdOp, MakeLeaf(IDNode, $1), $2); type_ptr = $$;}
;

type_braces       :  LBRACnum RBRACnum
	                   {$$ = MakeTree(IndexOp, NullExp(), NullExp());}
                  |  LBRACnum RBRACnum DOTnum field_op_type
	                   {$$ = MakeTree(IndexOp, NullExp(), $4);}
                  |  LBRACnum RBRACnum type_braces
	                   {$$ = MakeTree(IndexOp, NullExp(), $3);}
;

field_op_type     :  type
                       {$$ = MakeTree(FieldOp, $1, NullExp());}
;

braces            :  LBRACnum RBRACnum
                  |  braces LBRACnum RBRACnum
;

statement_list    :   LBRACEnum statement_inner RBRACEnum
	                   {$$ = $2;}
;

statement_inner   :   statement
		               {$$ = MakeTree(StmtOp, NullExp(), $1);}
                  |   statement SEMInum statement_inner
	                   {$$ = MakeTree(StmtOp, $3, $1);}
;

statement         :   assign_stmt
		               {$$ = $1;}
                  |   method_call_stmt
		               {$$ = $1;}
                  |   return_stmt
		               {$$ = $1;}
                  |   if_stmt
		               {$$ = $1;}
                  |   while_stmt
		               {$$ = $1;}
                  |
					   {$$ = NullExp();}
;

assign_stmt       :   variable ASSGNnum expression
					   {$$ = MakeTree(AssignOp, MakeTree(AssignOp, NullExp(), $1), $3);}
;

method_call_stmt  :   variable LPARENnum RPARENnum
	                   {$$ = MakeTree(RoutineCallOp, $1, NullExp());}
                  |   variable LPARENnum indece_expr RPARENnum
	                   {$$ = MakeTree(RoutineCallOp, $1, $3);}
;


return_stmt   :   RETURNnum expression
	                   {$$ = MakeTree(ReturnOp, $2, NullExp());}
              |   RETURNnum
					   {$$ = MakeTree(ReturnOp, NullExp(), NullExp());}
;

if_stmt       :   IFnum expression statement_list
	                   {$$ = MakeTree(IfElseOp, MakeTree(CommaOp, $2, $3), NullExp());}
              |   IFnum expression statement_list ELSEnum statement_list;
	                   {$$ = MakeTree(IfElseOp, MakeTree(CommaOp, $2, $3), $5);}
              |   IFnum expression statement_list ELSEnum if_stmt
	                   {$$ = MakeTree(IfElseOp, MakeTree(CommaOp, $2, $3), $5);}
;


while_stmt       :   WHILEnum expression statement_list
	                   {$$ = MakeTree(LoopOp, $2, $3);}
;


variable      :      IDnum
		               {$$ = MakeTree(VarOp, MakeLeaf(IDNode, $1), NullExp());}
              |      IDnum selection 
	                   {$$ = MakeTree(VarOp, MakeLeaf(IDNode, $1), $2);}
;

selection     :      LBRACnum indece_expr RBRACnum
	                   {$$ = MakeTree(SelectOp, $2, NullExp());}
		      |      DOTnum IDnum
	                   {$$ = MakeTree(SelectOp, MakeTree(FieldOp, MakeLeaf(IDNode, $2), NullExp()), NullExp());}
              |      DOTnum IDnum selection
	                   {$$ = MakeTree(SelectOp, MakeTree(FieldOp, MakeLeaf(IDNode, $2), NullExp()), $3);}
              |      LBRACnum indece_expr RBRACnum selection
	                   {$$ = MakeTree(IndexOp, $2, $4);} 
;

indece_expr   :      expression
		               {$$ = MakeTree(IndexOp, $1, NullExp());}
              |      expression COMMAnum indece_expr
	                   {$$ = MakeTree(IndexOp, $1, $3);}
              ;

expression    :     simple_expression
		               {$$ = $1;}
              |     simple_expression LTnum simple_expression
	                   {$$ = MakeTree(LTOp, $1, $3);}
              |     simple_expression LEnum simple_expression
	                   {$$ = MakeTree(LEOp, $1, $3);}
              |     simple_expression EQnum simple_expression
	                   {$$ = MakeTree(EQOp, $1, $3);}
              |     simple_expression NEnum simple_expression
	                   {$$ = MakeTree(NEOp, $1, $3);}
              |     simple_expression GEnum simple_expression
	                   {$$ = MakeTree(GEOp, $1, $3);}
              |     simple_expression GTnum simple_expression
	                   {$$ = MakeTree(GTOp, $1, $3);}
;

simple_expression  : optional_plus_minus
	                   {$$ = $1;}
                   |  optional_plus_minus PLUSnum other_term
	                   {$$ = MakeTree(AddOp, $1, $3);}
                   |  optional_plus_minus MINUSnum other_term
	                   {$$ = MakeTree(SubOp, $1, $3);}
                   |  optional_plus_minus ORnum other_term
	                   {$$ = MakeTree(OrOp, $1, $3);}
;

optional_plus_minus :  PLUSnum term
	                   {$$ = $2;}
                    |  MINUSnum term
	                   {$$ = MakeTree(UnaryNegOp, $2, NullExp());}
                    |  term
					   {$$ = $1;}
;

other_term    :    term
		               {$$ = $1;}
              |    term MINUSnum other_term
	                   {$$ = MakeTree(SubOp, $1, $3);}
              |    term PLUSnum other_term
	                   {$$ = MakeTree(AddOp, $1, $3);}
              |    term ORnum other_term
	                   {$$ = MakeTree(OrOp, $1, $3);}
;

term          :    factor
		               {$$ = $1;}
              |    factor TIMESnum other_factor
	                   {$$ = MakeTree(MultOp, $1, $3);}
              |    factor DIVIDEnum other_factor
	                   {$$ = MakeTree(DivOp, $1, $3);}
              |    factor ANDnum other_factor
	                   {$$ = MakeTree(AndOp, $1, $3);}
;

other_factor  :   factor TIMESnum other_factor
	                   {$$ = MakeTree(MultOp, $1, $3);}
              |   factor DIVIDEnum other_factor
	                   {$$ = MakeTree(DivOp, $1, $3);}
              |   factor ANDnum other_factor
	                   {$$ = MakeTree(AndOp, $1, $3);}
              |   factor
	                   {$$ = $1;}

;

factor        :   SCONSTnum
		              {$$ = MakeLeaf(STRINGNode, $1); }
              |   ICONSTnum
					  {$$ = MakeLeaf(NUMNode, $1);}
              |   variable
					  {$$ = $1;}
              |   method_call_stmt
					  {$$ = $1;}
              |   LPARENnum expression RPARENnum
     	              {$$ = $2;}
              |   NOTnum factor
	                  {$$ = MakeTree(NotOp, NullExp(), $2);}
;






%%
int yyerror(char *s){
	printf("FAIL %s %d, line %d, column: %d\n", s, yychar, yyline, yycolumn);
}

