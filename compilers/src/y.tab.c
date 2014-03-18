#ifndef lint
static const char yysccsid[] = "@(#)yaccpar	1.9 (Berkeley) 02/21/93";
#endif

#define YYBYACC 1
#define YYMAJOR 1
#define YYMINOR 9
#define YYPATCH 20121003

#define YYEMPTY        (-1)
#define yyclearin      (yychar = YYEMPTY)
#define yyerrok        (yyerrflag = 0)
#define YYRECOVERING() (yyerrflag != 0)

#define YYPREFIX "yy"

#define YYPURE 0

#line 4 "gens/grammar.y"
		#include "proj2.h"
	#include<stdio.h>
	int yylex(void);
	int yyerror (char *msg) ;
 
#line 25 "src/y.tab.c"

#ifndef YYSTYPE
typedef int YYSTYPE;
#endif

/* compatibility with bison */
#ifdef YYPARSE_PARAM
/* compatibility with FreeBSD */
# ifdef YYPARSE_PARAM_TYPE
#  define YYPARSE_DECL() yyparse(YYPARSE_PARAM_TYPE YYPARSE_PARAM)
# else
#  define YYPARSE_DECL() yyparse(void *YYPARSE_PARAM)
# endif
#else
# define YYPARSE_DECL() yyparse(void)
#endif

/* Parameters sent to lex. */
#ifdef YYLEX_PARAM
# define YYLEX_DECL() yylex(void *YYLEX_PARAM)
# define YYLEX yylex(YYLEX_PARAM)
#else
# define YYLEX_DECL() yylex(void)
# define YYLEX yylex()
#endif

/* Parameters sent to yyerror. */
#ifndef YYERROR_DECL
#define YYERROR_DECL() yyerror(const char *s)
#endif
#ifndef YYERROR_CALL
#define YYERROR_CALL(msg) yyerror(msg)
#endif

extern int YYPARSE_DECL();

#define ANDnum 257
#define ASSGNnum 258
#define DECLARATIONSnum 259
#define DOTnum 260
#define ENDDECLARATIONSnum 261
#define EQUALnum 262
#define GTnum 263
#define IDnum 264
#define INTnum 265
#define LBRACnum 266
#define LPARENnum 267
#define METHODnum 268
#define NEnum 269
#define ORnum 270
#define PROGRAMnum 271
#define RBRACnum 272
#define RPARENnum 273
#define SEMInum 274
#define VALnum 275
#define WHILEnum 276
#define CLASSnum 277
#define COMMAnum 278
#define DIVIDEnum 279
#define ELSEnum 280
#define EQnum 281
#define GEnum 282
#define ICONSTnum 283
#define IFnum 284
#define LBRACEnum 285
#define LEnum 286
#define LTnum 287
#define MINUSnum 288
#define NOTnum 289
#define PLUSnum 290
#define RBRACEnum 291
#define RETURNnum 292
#define SCONSTnum 293
#define TIMESnum 294
#define VOIDnum 295
#define YYERRCODE 256
static const short yylhs[] = {                           -1,
    0,    1,    1,    2,    3,    3,    3,    3,    4,    6,
    6,    7,    9,    9,    9,    9,   10,   11,   11,   11,
   14,   16,   16,   15,   17,   17,    5,    5,   18,   18,
   18,   18,   19,   19,   19,   19,   21,   21,   20,   20,
    8,    8,    8,    8,    8,    8,   12,   12,   22,   23,
   23,   24,   24,   24,   24,   24,   25,   26,   26,   27,
   27,   28,   28,   28,   29,   30,   30,   32,   32,   32,
   32,   31,   31,   13,   13,   13,   13,   13,   13,   13,
   33,   33,   34,   34,   34,   36,   36,   36,   36,   35,
   35,   38,   38,   38,   38,   37,   37,   37,   37,   37,
};
static const short yylen[] = {                            2,
    4,    1,    2,    3,    2,    3,    4,    3,    3,    1,
    2,    2,    4,    2,    3,    5,    2,    1,    1,    1,
    3,    1,    3,    2,    3,    4,    1,    2,    7,    6,
    7,    6,    2,    3,    4,    4,    1,    3,    2,    1,
    1,    2,    4,    1,    2,    4,    2,    3,    3,    2,
    3,    1,    1,    1,    1,    1,    3,    3,    4,    2,
    1,    3,    5,    5,    3,    1,    2,    3,    1,    3,
    4,    1,    3,    1,    3,    3,    3,    3,    3,    3,
    2,    3,    0,    1,    1,    2,    2,    2,    2,    1,
    2,    2,    2,    2,    2,    1,    1,    1,    3,    2,
};
static const short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    0,    2,    0,    3,    0,
    4,    0,    0,    5,    0,    0,   27,    0,    0,    0,
   10,    0,    0,    0,    6,    0,    8,   28,    0,    0,
    0,    9,   11,    0,   12,    0,    0,    0,    7,   47,
    0,    0,    0,    0,    0,   14,    0,    0,    0,   46,
   48,   43,    0,    0,   85,   84,    0,   18,   19,   20,
    0,    0,   15,    0,    0,    0,    0,    0,    0,    0,
    0,   22,    0,   13,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   97,    0,   96,   98,    0,    0,   37,
    0,    0,    0,   32,   40,    0,    0,   30,    0,    0,
    0,    0,   21,   16,   80,   78,   77,   79,   76,   75,
    0,    0,   67,    0,  100,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   52,   53,   54,   55,   56,    0,   39,    0,   31,   29,
   25,    0,   23,    0,    0,    0,   99,   88,   87,   86,
    0,   94,   93,   92,    0,   35,   38,    0,    0,   60,
   49,    0,    0,    0,   36,   26,   70,    0,    0,   65,
    0,   51,   57,   58,    0,   73,   71,    0,   59,   63,
   64,
};
static const short yydgoto[] = {                          2,
    6,    7,   11,   93,   16,   20,   21,   22,   35,   36,
   57,   30,   58,   59,   60,   73,   71,   17,   67,   94,
   91,   95,  129,  130,  131,  132,  133,  134,  135,   87,
  146,  113,   61,   62,   88,  151,   89,  155,
};
static const short yysindex[] = {                      -200,
 -198,    0, -253, -181, -153, -181,    0, -148,    0, -254,
    0,    5, -249,    0, -240, -206,    0, -126, -126,  -17,
    0, -112, -107,  -81,    0, -203,    0,    0,  -84, -243,
 -241,    0,    0, -126,    0,  -83,  -75,  -71,    0,    0,
    5,  -66,    5,  -52, -204,    0, -112,  -34,   -8,    0,
    0,    0,  -63, -204,    0,    0, -164,    0,    0,    0,
   71, -214,    0,  -46, -250,  -43,  -41, -250,  -38, -266,
  -32,    0, -189,    0, -112, -266, -266, -266, -266, -266,
 -266,  -51, -266,    0, -214,    0,    0, -101, -247,    0,
 -143, -258,  -24,    0,    0,  -46, -250,    0, -250,   12,
 -266, -204,    0,    0,    0,    0,    0,    0,    0,    0,
    6, -266,    0,    7,    0, -214, -214, -214, -101, -214,
 -214, -214, -247, -205,   22, -266, -266, -266,   -4,   17,
    0,    0,    0,    0,    0, -195,    0, -201,    0,    0,
    0,   26,    0,  -51,   15,   23,    0,    0,    0,    0,
 -101,    0,    0,    0, -247,    0,    0,  -24,  -24,    0,
    0, -258, -266,  -30,    0,    0,    0, -266,  -51,    0,
   19,    0,    0,    0,   27,    0,    0,  -11,    0,    0,
    0,
};
static const short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,  296,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   40,   46,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   48,
   49,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -57, -184,    0,    0,    0,    0,    0,
    0,    0,    0, -184,    0,    0,    0,    0,    0,    0,
   64,    0,    0,    0,    0,    0,    0,    0,    0, -184,
 -196,    0,    0,    0,    0, -184, -184, -184, -184, -184,
 -184, -140, -184,    0,    0,    0,    0,  -10,  -88,    0,
   33,    0,    0,    0,    0,    0,    0,    0,    0,    0,
 -184, -184,    0,    0,    0,    0,    0,    0,    0,    0,
 -166, -184,    0,    0,    0,    0,    0,    0,   16,    0,
    0,    0,  -62,    0,    0, -184, -184, -234,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   36,    0,    0,
    0,    0,    0,    0,   11,    0,    0,    0,    0,    0,
   42,    0,    0,    0,  -36,    0,    0,    0,    0,    0,
    0,   28, -184, -184,    0,    0,    0, -184, -114,    0,
   43,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,
};
static const short yygindex[] = {                         0,
    0,  312,    0,  311,  307,    0,  305,   -2,  -27,    0,
  -50,   -7,  -70,    0,    0,    0,    0,  110,  -48,  -61,
  230,  -91,  169,    0,    0,    0,    0,  154,    0,  -89,
    2, -115,  267,    0,   45,  247,  -77,  250,
};
#define YYTABLESIZE 358
static const short yytable[] = {                        100,
   69,  137,  136,   72,   12,   82,   98,  115,   12,  120,
   24,   31,  114,   13,   18,   19,   41,  126,   43,   63,
    4,   55,   42,   56,   42,  127,   44,   13,  167,   83,
  142,  121,   83,  128,   92,  139,   14,  140,   50,   61,
   52,  145,  152,  153,  154,   23,  122,  104,   83,   82,
   25,  143,   83,  177,   83,  158,  159,  160,   83,   64,
   53,   13,  163,   64,   13,    3,  170,  171,   84,   66,
    1,  164,  136,   66,   85,  156,  125,   24,   86,   83,
   54,   24,   83,   55,   27,   56,  180,   39,  102,  165,
   69,   69,  173,  145,   24,    5,   69,  145,   83,   69,
   69,  103,   69,   69,   83,   69,   69,   69,   83,   74,
    8,   69,   69,   75,   69,   69,   66,   66,   69,   69,
   69,   69,   66,   69,   69,   28,   66,   69,   66,   66,
  124,   66,   66,   66,  125,   28,   10,   66,   66,   29,
   66,   66,   68,   68,   66,   66,   66,   66,   68,   66,
   66,   34,   68,   66,   68,   68,   37,   68,   68,   68,
  148,  149,  150,   68,   68,  175,   68,   68,  116,  176,
   68,   68,   68,   68,   90,   68,   68,   90,   45,   68,
   90,   90,   38,   90,   90,   90,  117,   40,  118,   90,
   46,   48,   90,   90,   47,   49,   90,   90,   90,   90,
   91,   90,   90,   91,   17,   51,   91,   91,   70,   91,
   91,   91,  111,   42,  112,   91,   17,   90,   91,   91,
   17,   96,   91,   91,   91,   91,   95,   91,   91,   95,
   64,   97,   95,   95,   99,   95,   95,   95,   65,  101,
   66,   95,  174,   32,   95,   95,   18,   19,   95,   95,
   95,   95,   81,   95,   95,   81,   64,   55,   81,   56,
   92,   81,   81,   81,   68,  144,   66,   81,   18,   19,
   81,   81,  127,   92,   81,   81,   81,  141,   82,  147,
   81,   82,   72,   72,   82,  157,  161,   82,   82,   82,
  162,  166,  168,   82,  169,    1,   82,   82,  178,  179,
   82,   82,   82,   44,   89,   33,   82,   89,   34,   41,
   89,   45,   42,   89,   89,   89,   62,    9,   50,   89,
   15,   26,   89,   89,   33,  138,   89,   89,   89,   74,
  172,  181,   89,   76,  119,   74,   74,   74,  123,   77,
    0,   74,  105,  106,  107,  108,  109,  110,   74,    0,
    0,   78,   79,    0,   74,    0,   80,   81,
};
static const short yycheck[] = {                         70,
   49,   93,   92,   54,  259,  264,   68,   85,  259,  257,
   13,   19,   83,  268,  264,  265,  260,  276,  260,   47,
  274,  288,  266,  290,  266,  284,   34,  268,  144,  264,
  101,  279,  267,  292,  285,   97,  291,   99,   41,  274,
   43,  112,  120,  121,  122,  295,  294,   75,  283,  264,
  291,  102,  267,  169,  289,  126,  127,  128,  293,  265,
  265,  268,  258,  265,  268,  264,  158,  159,  283,  275,
  271,  267,  162,  275,  289,  124,  278,  274,  293,  264,
  285,  278,  267,  288,  291,  290,  178,  291,  278,  138,
  257,  258,  163,  164,  291,  277,  263,  168,  283,  266,
  267,  291,  269,  270,  289,  272,  273,  274,  293,  274,
  264,  278,  279,  278,  281,  282,  257,  258,  285,  286,
  287,  288,  263,  290,  291,   16,  267,  294,  269,  270,
  274,  272,  273,  274,  278,   26,  285,  278,  279,  266,
  281,  282,  257,  258,  285,  286,  287,  288,  263,  290,
  291,  264,  267,  294,  269,  270,  264,  272,  273,  274,
  116,  117,  118,  278,  279,  164,  281,  282,  270,  168,
  285,  286,  287,  288,  263,  290,  291,  266,  262,  294,
  269,  270,  264,  272,  273,  274,  288,  272,  290,  278,
  274,  267,  281,  282,  278,  267,  285,  286,  287,  288,
  263,  290,  291,  266,  262,  272,  269,  270,  272,  272,
  273,  274,  264,  266,  266,  278,  274,  264,  281,  282,
  278,  265,  285,  286,  287,  288,  263,  290,  291,  266,
  265,  273,  269,  270,  273,  272,  273,  274,  273,  272,
  275,  278,  273,  261,  281,  282,  264,  265,  285,  286,
  287,  288,  263,  290,  291,  266,  265,  288,  269,  290,
  285,  272,  273,  274,  273,  260,  275,  278,  264,  265,
  281,  282,  284,  285,  285,  286,  287,  266,  263,  273,
  291,  266,  272,  273,  269,  264,  291,  272,  273,  274,
  274,  266,  278,  278,  272,    0,  281,  282,  280,  273,
  285,  286,  287,  264,  263,  273,  291,  266,  273,  264,
  269,  264,  264,  272,  273,  274,  274,    6,  291,  278,
   10,   15,  281,  282,   20,   96,  285,  286,  287,  266,
  162,  178,  291,  263,   88,  272,  273,  274,   89,  269,
   -1,  278,   76,   77,   78,   79,   80,   81,  285,   -1,
   -1,  281,  282,   -1,  291,   -1,  286,  287,
};
#define YYFINAL 2
#ifndef YYDEBUG
#define YYDEBUG 0
#endif
#define YYMAXTOKEN 295
#if YYDEBUG
static const char *yyname[] = {

"end-of-file",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"ANDnum","ASSGNnum",
"DECLARATIONSnum","DOTnum","ENDDECLARATIONSnum","EQUALnum","GTnum","IDnum",
"INTnum","LBRACnum","LPARENnum","METHODnum","NEnum","ORnum","PROGRAMnum",
"RBRACnum","RPARENnum","SEMInum","VALnum","WHILEnum","CLASSnum","COMMAnum",
"DIVIDEnum","ELSEnum","EQnum","GEnum","ICONSTnum","IFnum","LBRACEnum","LEnum",
"LTnum","MINUSnum","NOTnum","PLUSnum","RBRACEnum","RETURNnum","SCONSTnum",
"TIMESnum","VOIDnum",
};
static const char *yyrule[] = {
"$accept : program",
"program : PROGRAMnum IDnum SEMInum class_decls",
"class_decls : class_decl",
"class_decls : class_decls class_decl",
"class_decl : CLASSnum IDnum class_body",
"class_body : LBRACEnum RBRACEnum",
"class_body : LBRACEnum decls RBRACEnum",
"class_body : LBRACEnum decls method_decls RBRACEnum",
"class_body : LBRACEnum method_decls RBRACEnum",
"decls : DECLARATIONSnum field_decls ENDDECLARATIONSnum",
"field_decls : field_decl",
"field_decls : field_decls field_decl",
"field_decl : type var_decl",
"var_decl : var_decl_id EQUALnum variable_initializer SEMInum",
"var_decl : var_decl_id SEMInum",
"var_decl : var_decl_id COMMAnum var_decl",
"var_decl : var_decl_id EQUALnum variable_initializer COMMAnum var_decl",
"var_decl_id : IDnum braces",
"variable_initializer : expression",
"variable_initializer : array_init",
"variable_initializer : array_creation",
"array_init : LBRACEnum variable_init_list RBRACEnum",
"variable_init_list : variable_initializer",
"variable_init_list : variable_init_list COMMAnum variable_initializer",
"array_creation : INTnum array_expr",
"array_expr : RBRACnum expression LBRACnum",
"array_expr : array_expr RBRACnum expression LBRACnum",
"method_decls : method_decl",
"method_decls : method_decls method_decl",
"method_decl : METHODnum type IDnum LPARENnum params RPARENnum block",
"method_decl : METHODnum type IDnum LPARENnum RPARENnum block",
"method_decl : METHODnum VOIDnum IDnum LPARENnum params RPARENnum block",
"method_decl : METHODnum VOIDnum IDnum LPARENnum RPARENnum block",
"params : INTnum identifier_list",
"params : VALnum INTnum identifier_list",
"params : INTnum identifier_list SEMInum params",
"params : VALnum INTnum identifier_list params",
"identifier_list : IDnum",
"identifier_list : identifier_list COMMAnum IDnum",
"block : decls statement_list",
"block : statement_list",
"type : INTnum",
"type : INTnum braces",
"type : INTnum braces DOTnum type",
"type : IDnum",
"type : IDnum braces",
"type : IDnum braces DOTnum type",
"braces : LBRACnum RBRACnum",
"braces : braces LBRACnum RBRACnum",
"statement_list : LBRACEnum statement_inner RBRACEnum",
"statement_inner : statement SEMInum",
"statement_inner : statement SEMInum statement_inner",
"statement : assign_stmt",
"statement : method_call_stmt",
"statement : return_stmt",
"statement : if_stmt",
"statement : while_stmt",
"assign_stmt : variable ASSGNnum expression",
"method_call_stmt : variable LPARENnum RPARENnum",
"method_call_stmt : variable LPARENnum indece_expr RPARENnum",
"return_stmt : RETURNnum expression",
"return_stmt : RETURNnum",
"if_stmt : IFnum expression statement_list",
"if_stmt : IFnum expression statement_list ELSEnum statement_list",
"if_stmt : IFnum expression statement_list ELSEnum if_stmt",
"while_stmt : WHILEnum expression statement_list",
"variable : IDnum",
"variable : IDnum selection",
"selection : LBRACnum indece_expr RBRACnum",
"selection : IDnum",
"selection : IDnum DOTnum selection",
"selection : LBRACnum indece_expr RBRACnum selection",
"indece_expr : expression",
"indece_expr : expression COMMAnum indece_expr",
"expression : simple_expression",
"expression : simple_expression LTnum simple_expression",
"expression : simple_expression LEnum simple_expression",
"expression : simple_expression EQnum simple_expression",
"expression : simple_expression NEnum simple_expression",
"expression : simple_expression GEnum simple_expression",
"expression : simple_expression GTnum simple_expression",
"simple_expression : optional_plus_minus term",
"simple_expression : optional_plus_minus term other_term",
"optional_plus_minus :",
"optional_plus_minus : PLUSnum",
"optional_plus_minus : MINUSnum",
"other_term : PLUSnum term",
"other_term : MINUSnum term",
"other_term : ORnum term",
"other_term : other_term other_term",
"term : factor",
"term : factor other_factor",
"other_factor : TIMESnum factor",
"other_factor : DIVIDEnum factor",
"other_factor : ANDnum factor",
"other_factor : other_factor other_factor",
"factor : SCONSTnum",
"factor : ICONSTnum",
"factor : variable",
"factor : LPARENnum expression RPARENnum",
"factor : NOTnum factor",

};
#endif

int      yydebug;
int      yynerrs;

int      yyerrflag;
int      yychar;
YYSTYPE  yyval;
YYSTYPE  yylval;

/* define the initial stack-sizes */
#ifdef YYSTACKSIZE
#undef YYMAXDEPTH
#define YYMAXDEPTH  YYSTACKSIZE
#else
#ifdef YYMAXDEPTH
#define YYSTACKSIZE YYMAXDEPTH
#else
#define YYSTACKSIZE 500
#define YYMAXDEPTH  500
#endif
#endif

#define YYINITSTACKSIZE 500

typedef struct {
    unsigned stacksize;
    short    *s_base;
    short    *s_mark;
    short    *s_last;
    YYSTYPE  *l_base;
    YYSTYPE  *l_mark;
} YYSTACKDATA;
/* variables for the parser stack */
static YYSTACKDATA yystack;
#line 235 "gens/grammar.y"
int yyerror(char *s){
	printf("FAIL %s\n", s);
}

#line 444 "src/y.tab.c"

#if YYDEBUG
#include <stdio.h>		/* needed for printf */
#endif

#include <stdlib.h>	/* needed for malloc, etc */
#include <string.h>	/* needed for memset */

/* allocate initial stack or double stack size, up to YYMAXDEPTH */
static int yygrowstack(YYSTACKDATA *data)
{
    int i;
    unsigned newsize;
    short *newss;
    YYSTYPE *newvs;

    if ((newsize = data->stacksize) == 0)
        newsize = YYINITSTACKSIZE;
    else if (newsize >= YYMAXDEPTH)
        return -1;
    else if ((newsize *= 2) > YYMAXDEPTH)
        newsize = YYMAXDEPTH;

    i = data->s_mark - data->s_base;
    newss = (short *)realloc(data->s_base, newsize * sizeof(*newss));
    if (newss == 0)
        return -1;

    data->s_base = newss;
    data->s_mark = newss + i;

    newvs = (YYSTYPE *)realloc(data->l_base, newsize * sizeof(*newvs));
    if (newvs == 0)
        return -1;

    data->l_base = newvs;
    data->l_mark = newvs + i;

    data->stacksize = newsize;
    data->s_last = data->s_base + newsize - 1;
    return 0;
}

#if YYPURE || defined(YY_NO_LEAKS)
static void yyfreestack(YYSTACKDATA *data)
{
    free(data->s_base);
    free(data->l_base);
    memset(data, 0, sizeof(*data));
}
#else
#define yyfreestack(data) /* nothing */
#endif

#define YYABORT  goto yyabort
#define YYREJECT goto yyabort
#define YYACCEPT goto yyaccept
#define YYERROR  goto yyerrlab

int
YYPARSE_DECL()
{
    int yym, yyn, yystate;
#if YYDEBUG
    const char *yys;

    if ((yys = getenv("YYDEBUG")) != 0)
    {
        yyn = *yys;
        if (yyn >= '0' && yyn <= '9')
            yydebug = yyn - '0';
    }
#endif

    yynerrs = 0;
    yyerrflag = 0;
    yychar = YYEMPTY;
    yystate = 0;

#if YYPURE
    memset(&yystack, 0, sizeof(yystack));
#endif

    if (yystack.s_base == NULL && yygrowstack(&yystack)) goto yyoverflow;
    yystack.s_mark = yystack.s_base;
    yystack.l_mark = yystack.l_base;
    yystate = 0;
    *yystack.s_mark = 0;

yyloop:
    if ((yyn = yydefred[yystate]) != 0) goto yyreduce;
    if (yychar < 0)
    {
        if ((yychar = YYLEX) < 0) yychar = 0;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("%sdebug: state %d, reading %d (%s)\n",
                    YYPREFIX, yystate, yychar, yys);
        }
#endif
    }
    if ((yyn = yysindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
#if YYDEBUG
        if (yydebug)
            printf("%sdebug: state %d, shifting to state %d\n",
                    YYPREFIX, yystate, yytable[yyn]);
#endif
        if (yystack.s_mark >= yystack.s_last && yygrowstack(&yystack))
        {
            goto yyoverflow;
        }
        yystate = yytable[yyn];
        *++yystack.s_mark = yytable[yyn];
        *++yystack.l_mark = yylval;
        yychar = YYEMPTY;
        if (yyerrflag > 0)  --yyerrflag;
        goto yyloop;
    }
    if ((yyn = yyrindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
        yyn = yytable[yyn];
        goto yyreduce;
    }
    if (yyerrflag) goto yyinrecovery;

    yyerror("syntax error");

    goto yyerrlab;

yyerrlab:
    ++yynerrs;

yyinrecovery:
    if (yyerrflag < 3)
    {
        yyerrflag = 3;
        for (;;)
        {
            if ((yyn = yysindex[*yystack.s_mark]) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
#if YYDEBUG
                if (yydebug)
                    printf("%sdebug: state %d, error recovery shifting\
 to state %d\n", YYPREFIX, *yystack.s_mark, yytable[yyn]);
#endif
                if (yystack.s_mark >= yystack.s_last && yygrowstack(&yystack))
                {
                    goto yyoverflow;
                }
                yystate = yytable[yyn];
                *++yystack.s_mark = yytable[yyn];
                *++yystack.l_mark = yylval;
                goto yyloop;
            }
            else
            {
#if YYDEBUG
                if (yydebug)
                    printf("%sdebug: error recovery discarding state %d\n",
                            YYPREFIX, *yystack.s_mark);
#endif
                if (yystack.s_mark <= yystack.s_base) goto yyabort;
                --yystack.s_mark;
                --yystack.l_mark;
            }
        }
    }
    else
    {
        if (yychar == 0) goto yyabort;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("%sdebug: state %d, error recovery discards token %d (%s)\n",
                    YYPREFIX, yystate, yychar, yys);
        }
#endif
        yychar = YYEMPTY;
        goto yyloop;
    }

yyreduce:
#if YYDEBUG
    if (yydebug)
        printf("%sdebug: state %d, reducing by rule %d (%s)\n",
                YYPREFIX, yystate, yyn, yyrule[yyn]);
#endif
    yym = yylen[yyn];
    if (yym)
        yyval = yystack.l_mark[1-yym];
    else
        memset(&yyval, 0, sizeof yyval);
    switch (yyn)
    {
    }
    yystack.s_mark -= yym;
    yystate = *yystack.s_mark;
    yystack.l_mark -= yym;
    yym = yylhs[yyn];
    if (yystate == 0 && yym == 0)
    {
#if YYDEBUG
        if (yydebug)
            printf("%sdebug: after reduction, shifting from state 0 to\
 state %d\n", YYPREFIX, YYFINAL);
#endif
        yystate = YYFINAL;
        *++yystack.s_mark = YYFINAL;
        *++yystack.l_mark = yyval;
        if (yychar < 0)
        {
            if ((yychar = YYLEX) < 0) yychar = 0;
#if YYDEBUG
            if (yydebug)
            {
                yys = 0;
                if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
                if (!yys) yys = "illegal-symbol";
                printf("%sdebug: state %d, reading %d (%s)\n",
                        YYPREFIX, YYFINAL, yychar, yys);
            }
#endif
        }
        if (yychar == 0) goto yyaccept;
        goto yyloop;
    }
    if ((yyn = yygindex[yym]) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn];
    else
        yystate = yydgoto[yym];
#if YYDEBUG
    if (yydebug)
        printf("%sdebug: after reduction, shifting from state %d \
to state %d\n", YYPREFIX, *yystack.s_mark, yystate);
#endif
    if (yystack.s_mark >= yystack.s_last && yygrowstack(&yystack))
    {
        goto yyoverflow;
    }
    *++yystack.s_mark = (short) yystate;
    *++yystack.l_mark = yyval;
    goto yyloop;

yyoverflow:
    yyerror("yacc stack overflow");

yyabort:
    yyfreestack(&yystack);
    return (1);

yyaccept:
    yyfreestack(&yystack);
    return (0);
}
