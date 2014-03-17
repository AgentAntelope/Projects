%{
//#include "token.h"	
 %}

%start derp
%token GEnum
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

%%

derp    :   GEnum ;
%%

int yyerror (char *msg) {
	return fprintf (stderr, "YACC: %s; yychar=%d\n", msg, yychar);
	}
