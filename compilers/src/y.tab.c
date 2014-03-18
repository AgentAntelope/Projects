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
	int yyline;
	int yycolumn;
	int yylex(void);
	int yyerror (char *msg) ;
 
#line 27 "src/y.tab.c"

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
    0,    1,    1,    2,    3,    3,    3,    3,    4,    4,
    6,    6,    7,    9,    9,    9,    9,   10,   10,   11,
   11,   11,   14,   16,   16,   15,   17,   17,    5,    5,
   18,   18,   18,   18,   19,   19,   19,   19,   21,   21,
   20,   20,    8,    8,    8,    8,    8,    8,   12,   12,
   22,   23,   23,   24,   24,   24,   24,   24,   24,   25,
   26,   26,   27,   27,   28,   28,   28,   29,   30,   30,
   32,   32,   32,   32,   31,   31,   13,   13,   13,   13,
   13,   13,   13,   33,   33,   34,   34,   34,   36,   36,
   36,   36,   35,   35,   38,   38,   38,   38,   37,   37,
   37,   37,   37,   37,
};
static const short yylen[] = {                            2,
    4,    1,    2,    3,    2,    3,    4,    3,    3,    2,
    1,    2,    2,    4,    2,    3,    5,    2,    1,    1,
    1,    1,    3,    1,    3,    2,    3,    4,    1,    2,
    7,    6,    7,    6,    2,    5,    3,    4,    1,    3,
    2,    1,    1,    2,    4,    1,    2,    4,    2,    3,
    3,    1,    3,    1,    1,    1,    1,    1,    0,    3,
    3,    4,    2,    1,    3,    5,    5,    3,    1,    2,
    3,    2,    3,    4,    1,    3,    1,    3,    3,    3,
    3,    3,    3,    2,    3,    0,    1,    1,    2,    2,
    2,    2,    1,    2,    2,    2,    2,    2,    1,    1,
    1,    1,    3,    2,
};
static const short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    0,    2,    0,    3,    0,
    4,    0,    0,    5,    0,    0,   29,   10,    0,    0,
    0,   11,    0,    0,    0,    6,    0,    8,   30,    0,
    0,    0,    9,   12,    0,   13,    0,    0,    0,    7,
   49,    0,    0,    0,    0,    0,   15,    0,    0,    0,
   48,   50,   45,    0,    0,   88,   87,    0,   20,   21,
   22,    0,    0,   16,    0,    0,    0,    0,    0,    0,
    0,    0,   24,    0,   14,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  100,    0,   99,  102,    0,    0,
    0,   39,    0,    0,    0,   34,   42,    0,    0,   32,
    0,    0,    0,    0,   23,   17,   83,   81,   80,   82,
   79,   78,    0,    0,   70,    0,  104,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   52,   54,   55,   56,   57,   58,    0,   41,
    0,   33,   31,   27,    0,   25,    0,    0,    0,  103,
   61,    0,   91,   90,   89,    0,   97,   96,   95,    0,
   38,   40,    0,    0,   63,    0,   51,    0,    0,   28,
   73,    0,    0,   62,   68,    0,   53,   60,   36,   76,
   74,    0,   66,   67,
};
static const short yydgoto[] = {                          2,
    6,    7,   11,   95,   16,   21,   22,   23,   36,   37,
   58,   31,   59,   60,   61,   74,   72,   17,   68,   96,
   93,   97,  132,  133,  134,   88,  136,  137,  138,   89,
  149,  115,   62,   63,   90,  156,   91,  160,
};
static const short yysindex[] = {                      -242,
 -199,    0, -182, -203, -169, -203,    0, -157,    0, -250,
    0,  -30, -249,    0, -257, -255,    0,    0, -163, -163,
   22,    0, -134, -108,  -88,    0, -248,    0,    0,  -83,
  -58,  -28,    0,    0, -163,    0,  -59,  -79,  -69,    0,
    0,  -84,  -63,  -84,  -53, -197,    0, -134,    9,   42,
    0,    0,    0,  -36, -197,    0,    0, -145,    0,    0,
    0,   74,  -82,    0,  -25, -238,  -18,  -17, -238,  -16,
 -262,   12,    0, -259,    0, -134, -262, -262, -262, -262,
 -262, -262,   -8, -262,    0,  -82,    0,    0,    6,  -76,
 -252,    0, -124, -254,  -10,    0,    0,  -25, -238,    0,
 -238,   19, -262, -197,    0,    0,    0,    0,    0,    0,
    0,    0,   58, -262,    0,   33,    0, -209,  -82,  -82,
  -82,  -76,  -82,  -82,  -82, -252, -120,   59, -262, -262,
 -262, -208,    0,    0,    0,    0,    0,    0, -200,    0,
 -115,    0,    0,    0,   39,    0,   -8,   46,   54,    0,
    0,   62,    0,    0,    0,  -76,    0,    0,    0, -252,
    0,    0,  -10,  -10,    0, -254,    0, -262, -120,    0,
    0, -262,   -8,    0,    0,   51,    0,    0,    0,    0,
    0, -113,    0,    0,
};
static const short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,  327,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   78,   80,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   82,   83,    0,    0,  -14,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  -13,  -67,    0,    0,    0,    0,
    0,    0,    0,    0,  -67,    0,    0,    0,    0,    0,
    0,  -68,    0,    0,    0,    0,    0,    0,    0,    0,
  -67, -205,    0,    0,    0,    0,  -67,  -67,  -67,  -67,
  -67,  -67, -173,  -67,    0,    0,    0,    0,  -95,   27,
  -45,    0,   77, -187,    0,    0,    0,    0,    0,    0,
    0,    0,  -67,  -67,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -67,    0,    0,    0,  -67,    0,    0,
    0,   47,    0,    0,    0,  -19,    0,    0,  -67,  -67,
 -211,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   84,    0,    0,    0,    0,    0, -147,  -52,    0,    0,
    0,    0,    0,    0,    0,   67,    0,    0,    0,    7,
    0,    0,    0,    0,    0, -187,    0,  -67,    0,    0,
    0,  -67, -121,    0,    0, -167,    0,    0,    0,    0,
    0,    0,    0,    0,
};
static const short yygindex[] = {                         0,
    0,  345,    0,  349,  353,    0,  348,   10,  -31,    0,
  -49,    5,  -70,    0,    0,    0,    0,    8,  -50,  -62,
  272,  -93,    0,  205,    0,  -91,    0,  190,    0,  -90,
 -110, -116,  285,    0,  183,  283,  -74,  284,
};
#define YYTABLESIZE 375
static const short yytable[] = {                         70,
  102,  140,  135,  139,  123,   73,  100,  152,   12,   83,
   13,  117,   13,  116,   19,   20,   64,   13,  104,   13,
   12,  129,   25,   29,   32,   56,  124,   57,    1,  130,
  171,  105,  145,   26,   29,   28,  142,  131,  143,   45,
   14,  125,   40,  148,  106,   24,   94,  148,  157,  158,
  159,   51,   86,   53,  146,   86,  181,  168,  163,  164,
  165,  180,   64,  151,    3,  166,  118,   54,   26,  175,
  176,   86,   26,    5,  135,  139,  161,   86,   56,   64,
   57,   86,  167,   69,   69,   26,   59,   55,  183,   69,
   56,    4,   57,   69,    8,   69,   69,  178,   69,   69,
   69,  148,   30,   59,   69,   69,   65,   69,   69,   72,
   72,   69,   69,   69,   69,   72,   69,   69,  179,   72,
   69,   72,   72,   65,   72,   72,   72,   10,   75,   35,
   72,   72,   76,   72,   72,   71,   71,   72,   72,   72,
   72,   71,   72,   72,   65,   71,   72,   71,   71,  127,
   71,   71,   71,  128,   67,   38,   71,   71,  169,   71,
   71,  101,  128,   71,   71,   71,   71,  101,   71,   71,
  130,   94,   71,  101,  101,   39,  101,  101,  101,   19,
   20,   83,  101,  101,   84,  101,  101,   49,   41,  101,
  101,  101,  101,  119,  101,  101,   86,   50,  101,   86,
   85,   42,   46,   77,   77,   77,   86,   43,   52,   77,
   87,  120,   43,  121,   47,   86,   77,   93,   48,   75,
   75,   86,   77,   93,   93,   86,   93,   93,   93,   71,
   18,   44,   93,   19,   20,   93,   93,   43,   92,   93,
   93,   93,   93,   94,   93,   93,   98,   19,   18,   94,
   94,  113,   94,   94,   94,   99,  101,  114,   94,   19,
   18,   94,   94,   19,   18,   94,   94,   94,   94,   98,
   94,   94,  118,   65,   94,   98,   98,  103,   98,   98,
   98,   66,   33,   67,   98,   19,   20,   98,   98,   84,
  144,   98,   98,   98,   98,   84,   98,   98,   84,   84,
   84,  153,  154,  155,   84,  150,   65,   84,   84,   85,
  170,   84,   84,   84,   69,   85,   67,   84,   85,   85,
   85,  147,  162,  172,   85,  173,    1,   85,   85,   92,
  182,   85,   85,   85,  174,   92,   77,   85,   92,   92,
   92,   46,   78,   43,   92,   47,   44,   92,   92,   35,
    9,   92,   92,   92,   79,   80,   37,   92,   15,   81,
   82,  107,  108,  109,  110,  111,  112,   27,   34,  141,
  177,  184,  122,    0,  126,
};
static const short yycheck[] = {                         50,
   71,   95,   94,   94,  257,   55,   69,  118,  259,  264,
  268,   86,  268,   84,  264,  265,   48,  268,  278,  268,
  259,  276,   13,   16,   20,  288,  279,  290,  271,  284,
  147,  291,  103,  291,   27,  291,   99,  292,  101,   35,
  291,  294,  291,  114,   76,  295,  285,  118,  123,  124,
  125,   42,  264,   44,  104,  267,  173,  258,  129,  130,
  131,  172,  274,  273,  264,  274,  267,  265,  274,  163,
  164,  283,  278,  277,  166,  166,  127,  289,  288,  291,
  290,  293,  291,  257,  258,  291,  274,  285,  182,  263,
  288,  274,  290,  267,  264,  269,  270,  168,  272,  273,
  274,  172,  266,  291,  278,  279,  274,  281,  282,  257,
  258,  285,  286,  287,  288,  263,  290,  291,  169,  267,
  294,  269,  270,  291,  272,  273,  274,  285,  274,  264,
  278,  279,  278,  281,  282,  257,  258,  285,  286,  287,
  288,  263,  290,  291,  265,  267,  294,  269,  270,  274,
  272,  273,  274,  278,  275,  264,  278,  279,  274,  281,
  282,  257,  278,  285,  286,  287,  288,  263,  290,  291,
  284,  285,  294,  269,  270,  264,  272,  273,  274,  264,
  265,  264,  278,  279,  267,  281,  282,  267,  272,  285,
  286,  287,  288,  270,  290,  291,  264,  267,  294,  267,
  283,  260,  262,  272,  273,  274,  289,  266,  272,  278,
  293,  288,  266,  290,  274,  283,  285,  263,  278,  272,
  273,  289,  291,  269,  270,  293,  272,  273,  274,  266,
  261,  260,  278,  264,  265,  281,  282,  266,  264,  285,
  286,  287,  288,  263,  290,  291,  265,  262,  262,  269,
  270,  260,  272,  273,  274,  273,  273,  266,  278,  274,
  274,  281,  282,  278,  278,  285,  286,  287,  288,  263,
  290,  291,  267,  265,  285,  269,  270,  266,  272,  273,
  274,  273,  261,  275,  278,  264,  265,  281,  282,  263,
  272,  285,  286,  287,  288,  269,  290,  291,  272,  273,
  274,  119,  120,  121,  278,  273,  265,  281,  282,  263,
  272,  285,  286,  287,  273,  269,  275,  291,  272,  273,
  274,  264,  264,  278,  278,  272,    0,  281,  282,  263,
  280,  285,  286,  287,  273,  269,  263,  291,  272,  273,
  274,  264,  269,  264,  278,  264,  264,  281,  282,  273,
    6,  285,  286,  287,  281,  282,  273,  291,   10,  286,
  287,   77,   78,   79,   80,   81,   82,   15,   21,   98,
  166,  182,   90,   -1,   91,
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
"decls : DECLARATIONSnum ENDDECLARATIONSnum",
"field_decls : field_decl",
"field_decls : field_decls field_decl",
"field_decl : type var_decl",
"var_decl : var_decl_id EQUALnum variable_initializer SEMInum",
"var_decl : var_decl_id SEMInum",
"var_decl : var_decl_id COMMAnum var_decl",
"var_decl : var_decl_id EQUALnum variable_initializer COMMAnum var_decl",
"var_decl_id : IDnum braces",
"var_decl_id : IDnum",
"variable_initializer : expression",
"variable_initializer : array_init",
"variable_initializer : array_creation",
"array_init : LBRACEnum variable_init_list RBRACEnum",
"variable_init_list : variable_initializer",
"variable_init_list : variable_init_list COMMAnum variable_initializer",
"array_creation : INTnum array_expr",
"array_expr : LBRACnum expression RBRACnum",
"array_expr : array_expr LBRACnum expression RBRACnum",
"method_decls : method_decl",
"method_decls : method_decls method_decl",
"method_decl : METHODnum type IDnum LPARENnum params RPARENnum block",
"method_decl : METHODnum type IDnum LPARENnum RPARENnum block",
"method_decl : METHODnum VOIDnum IDnum LPARENnum params RPARENnum block",
"method_decl : METHODnum VOIDnum IDnum LPARENnum RPARENnum block",
"params : INTnum identifier_list",
"params : VALnum INTnum identifier_list SEMInum params",
"params : VALnum INTnum identifier_list",
"params : INTnum identifier_list SEMInum params",
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
"statement_inner : statement",
"statement_inner : statement_inner SEMInum statement",
"statement : assign_stmt",
"statement : method_call_stmt",
"statement : return_stmt",
"statement : if_stmt",
"statement : while_stmt",
"statement :",
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
"selection : DOTnum IDnum",
"selection : DOTnum IDnum selection",
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
"factor : method_call_stmt",
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
#line 243 "gens/grammar.y"
int yyerror(char *s){
	printf("FAIL %s %d, line %d, column: %d\n", s, yychar, yyline, yycolumn);
}

#line 456 "src/y.tab.c"

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
