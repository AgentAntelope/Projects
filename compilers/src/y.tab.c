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
	tree type_ptr;
 
#line 28 "src/y.tab.c"

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
    0,    1,    1,    2,    3,    3,    6,    6,    7,    7,
    8,   12,   12,   12,   12,   13,   13,   14,   14,   14,
   16,   15,   15,   18,   17,   17,    4,    4,    4,    4,
    5,    5,   37,   39,   39,   38,   38,   38,   38,   41,
   41,   40,   40,    9,    9,    9,    9,   10,   10,   10,
   11,   42,   42,   29,   36,   36,   30,   30,   30,   30,
   30,   30,   35,   34,   34,   33,   33,   32,   32,   32,
   31,   26,   26,   27,   27,   27,   27,   28,   28,   19,
   19,   19,   19,   19,   19,   19,   20,   20,   20,   20,
   25,   25,   25,   22,   22,   22,   22,   21,   21,   21,
   21,   24,   24,   24,   24,   23,   23,   23,   23,   23,
   23,
};
static const short yylen[] = {                            2,
    4,    1,    2,    3,    2,    3,    3,    2,    1,    2,
    2,    4,    2,    3,    5,    2,    1,    1,    1,    1,
    3,    1,    3,    2,    3,    4,    1,    2,    1,    2,
    7,    7,    1,    1,    0,    2,    5,    3,    4,    1,
    3,    2,    1,    1,    2,    1,    2,    2,    4,    3,
    1,    2,    3,    3,    1,    3,    1,    1,    1,    1,
    1,    0,    3,    3,    4,    2,    1,    3,    5,    5,
    3,    1,    2,    3,    2,    3,    4,    1,    3,    1,
    3,    3,    3,    3,    3,    3,    1,    3,    3,    3,
    2,    2,    1,    1,    3,    3,    3,    1,    3,    3,
    3,    3,    3,    3,    1,    1,    1,    1,    1,    3,
    2,
};
static const short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    0,    2,    0,    3,    0,
    4,    0,    0,    5,    0,   27,    0,    8,    0,    0,
    0,    9,    0,   33,    0,    0,    6,   30,   28,    0,
   47,   45,    7,   10,    0,   11,    0,    0,    0,    0,
    0,    0,    0,   13,    0,    0,    0,    0,   50,   52,
    0,    0,    0,    0,  107,    0,    0,    0,    0,  106,
    0,   19,   20,   18,    0,   93,    0,    0,    0,  109,
   14,    0,    0,   34,    0,    0,   51,   49,   53,    0,
    0,   73,    0,   24,    0,    0,    0,   92,  111,   91,
   12,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   40,    0,    0,    0,    0,
    0,    0,    0,    0,  110,    0,   21,   15,   86,   84,
   83,   85,   82,   81,    0,  101,  100,   99,    0,   90,
   89,   88,   64,    0,    0,    0,    0,    0,    0,   43,
   31,   32,   76,    0,    0,    0,   23,    0,    0,    0,
    0,    0,    0,   65,   39,   41,    0,    0,    0,    0,
    0,    0,   61,   60,   59,   58,   57,    0,   42,   79,
   77,   26,  104,  103,  102,   97,   95,   96,   37,    0,
    0,   66,    0,    0,   54,   71,    0,   63,   56,    0,
   69,   70,
};
static const short yydgoto[] = {                          2,
    6,    7,   11,   15,   16,  139,   21,   22,   23,   31,
   78,   36,   37,   86,   87,   62,   84,   63,   64,   65,
   66,  130,   67,  126,   68,   69,   82,  113,  140,  162,
  163,  164,  165,   70,  167,  168,   26,   74,   75,  141,
  107,   42,
};
static const short yysindex[] = {                      -187,
 -157,    0, -162, -160, -144, -160,    0, -141,    0, -254,
    0, -188, -207,    0, -249,    0, -113,    0,  -82,  -82,
 -186,    0,  -94,    0,  -89,  -83,    0,    0,    0,  -84,
    0,    0,    0,    0,  -65,    0, -146,  -62,  -61, -236,
  -58,  -59,  -42,    0,  -94, -243, -243, -183,    0,    0,
  -46, -235,  -39,  -22,    0,  -42, -175, -175, -175,    0,
 -151,    0,    0,    0,  -50,    0, -251, -190,  -57,    0,
    0,  -35,  -37,    0,  -38,  -33,    0,    0,    0,  -30,
  -22,    0,  -22,    0,  -29,  -40,  -41,    0,    0,    0,
    0,  -94,  -22,  -22,  -22,  -22,  -22,  -22, -175, -175,
 -175, -175, -175, -175,  -34,    0, -125,  -35, -252, -252,
 -235,  -26,  -19,  -15,    0,  -42,    0,    0,    0,    0,
    0,    0,    0,    0, -239,    0,    0,    0, -177,    0,
    0,    0,    0,  -13, -243,   -2, -120, -193,  -21,    0,
    0,    0,    0,  -22, -235,  -39,    0, -175, -175, -175,
 -175, -175, -175,    0,    0,    0, -243,  -22,  -22,  -22,
 -232,  -11,    0,    0,    0,    0,    0,  -18,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  -21,
  -21,    0,  -22, -193,    0,    0,   -8,    0,    0, -105,
    0,    0,
};
static const short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,  282,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   -6,    0,   19,   22,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   -4,    0,    0,    0,    0,   25,
    0,    3,    0,    0,    0,   17,   17,    0,    0,    0,
    0, -148,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   88,    0,    6,   52,  -70,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    7,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   26,    0,    0,    0,
 -122,  -76,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   32,    0,    0,    0,   72,    0,
    0,    0,    0,    0,    0,    0,   27, -257,    0,    0,
    0,    0,    0,    0,  -96, -206,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0, -205,
    0,   12,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0, -257,    0,    0, -204,    0,    0,    0,
    0,    0,
};
static const short yygindex[] = {                         0,
    0,  301,    0,    0,   21,  298,    0,  288,   -9,   54,
    0,  -36,    0,  268,  196,    0,  170,    0,  -54,  274,
  -56,  -91,  -47,  -85,    0, -118, -101, -103, -131,    0,
    0,  137,    0, -117,    0,  144,    0, -112,  284,  219,
  224,    0,
};
#define YYTABLESIZE 379
static const short yytable[] = {                         85,
   88,  134,   90,   25,   12,   99,   12,  169,   71,  143,
   89,  131,  132,   13,  127,  128,   62,  148,   13,  161,
  166,   72,  155,   48,   80,  183,  112,  100,  114,   30,
   81,   73,  138,   62,  105,   28,   14,   29,   77,  149,
  170,   27,  101,  171,  179,  129,  129,  129,  186,  187,
  112,  125,  125,  125,  150,  118,   19,   20,  191,  176,
  177,  178,  173,  174,  175,  161,  166,   25,   67,   68,
   52,   25,   18,   32,   33,   19,   20,   19,   20,  102,
   19,   20,  158,    1,   25,   67,   68,   24,   52,  112,
  159,   54,  151,   49,  129,  129,  129,  103,  160,  104,
  125,  125,  125,  180,  181,  182,    3,   55,   72,   72,
  152,    4,  153,   58,   72,   43,    5,   60,   72,    8,
   72,   72,   91,   72,   72,   72,   92,   44,  188,   72,
   72,   45,   72,   72,   75,   75,   72,   72,   72,   72,
   75,   72,   72,   10,   75,   72,   75,   75,  135,   75,
   75,   75,  136,  157,   13,   75,   75,  136,   75,   75,
   74,   74,   75,   75,   75,   75,   74,   75,   75,   35,
   74,   75,   74,   74,   38,   74,   74,   74,  159,  138,
   39,   74,   74,   30,   74,   74,  108,   40,   74,   74,
   74,   74,  108,   74,   74,   78,   78,   74,  108,  108,
   41,  108,  108,  108,   46,   47,   51,  108,  108,  105,
  108,  108,   93,   50,  108,  108,  108,  108,   94,  108,
  108,   52,   53,  108,   54,   79,   83,  108,  106,   52,
   95,   96,   54,  111,  109,   97,   98,  116,  133,  110,
   55,   52,   56,  115,   54,   57,   58,   59,   55,  117,
   60,  144,  145,   57,   58,   59,  146,   17,   60,  154,
   55,  156,  184,  138,   16,   57,   58,   59,   98,   17,
   60,  190,  185,   17,   98,   98,   16,   98,   98,   98,
   16,    1,   46,   98,   29,   44,   98,   98,   48,   35,
   98,   98,   98,   98,  105,   98,   98,   22,   36,   38,
  105,  105,   55,  105,  105,  105,    9,   17,   34,  105,
   61,  147,  105,  105,   87,  172,  105,  105,  105,  105,
   87,  105,  105,   87,   87,   87,  192,  189,  142,   87,
   76,  137,   87,   87,   94,    0,   87,   87,   87,    0,
   94,    0,   87,   94,   94,   94,    0,    0,    0,   94,
    0,    0,   94,   94,    0,    0,   94,   94,   94,   80,
   80,   80,   94,    0,    0,   80,  119,  120,  121,  122,
  123,  124,   80,    0,    0,    0,    0,    0,   80,
};
static const short yycheck[] = {                         54,
   57,  105,   59,   13,  259,  257,  259,  139,   45,  111,
   58,  103,  104,  268,  100,  101,  274,  257,  268,  138,
  138,  265,  135,  260,  260,  258,   81,  279,   83,  266,
  266,  275,  285,  291,  267,   15,  291,   17,   48,  279,
  144,  291,  294,  145,  157,  102,  103,  104,  180,  181,
  105,   99,  100,  101,  294,   92,  264,  265,  190,  151,
  152,  153,  148,  149,  150,  184,  184,  274,  274,  274,
  264,  278,  261,   20,  261,  264,  265,  264,  265,  270,
  264,  265,  276,  271,  291,  291,  291,  295,  264,  144,
  284,  267,  270,   40,  151,  152,  153,  288,  292,  290,
  148,  149,  150,  158,  159,  160,  264,  283,  257,  258,
  288,  274,  290,  289,  263,  262,  277,  293,  267,  264,
  269,  270,  274,  272,  273,  274,  278,  274,  183,  278,
  279,  278,  281,  282,  257,  258,  285,  286,  287,  288,
  263,  290,  291,  285,  267,  294,  269,  270,  274,  272,
  273,  274,  278,  274,  268,  278,  279,  278,  281,  282,
  257,  258,  285,  286,  287,  288,  263,  290,  291,  264,
  267,  294,  269,  270,  264,  272,  273,  274,  284,  285,
  264,  278,  279,  266,  281,  282,  257,  272,  285,  286,
  287,  288,  263,  290,  291,  272,  273,  294,  269,  270,
  266,  272,  273,  274,  267,  267,  266,  278,  279,  267,
  281,  282,  263,  272,  285,  286,  287,  288,  269,  290,
  291,  264,  265,  294,  267,  272,  266,  265,  264,  264,
  281,  282,  267,  264,  273,  286,  287,  278,  273,  273,
  283,  264,  285,  273,  267,  288,  289,  290,  283,  291,
  293,  278,  272,  288,  289,  290,  272,  262,  293,  273,
  283,  264,  274,  285,  262,  288,  289,  290,  263,  274,
  293,  280,  291,  278,  269,  270,  274,  272,  273,  274,
  278,    0,  264,  278,  291,  264,  281,  282,  264,  273,
  285,  286,  287,  288,  263,  290,  291,  291,  273,  273,
  269,  270,  291,  272,  273,  274,    6,   10,   21,  278,
   43,  116,  281,  282,  263,  146,  285,  286,  287,  288,
  269,  290,  291,  272,  273,  274,  190,  184,  110,  278,
   47,  108,  281,  282,  263,   -1,  285,  286,  287,   -1,
  269,   -1,  291,  272,  273,  274,   -1,   -1,   -1,  278,
   -1,   -1,  281,  282,   -1,   -1,  285,  286,  287,  272,
  273,  274,  291,   -1,   -1,  278,   93,   94,   95,   96,
   97,   98,  285,   -1,   -1,   -1,   -1,   -1,  291,
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
"variable_init_list : variable_initializer COMMAnum variable_init_list",
"array_creation : INTnum array_expr",
"array_expr : LBRACnum expression RBRACnum",
"array_expr : LBRACnum expression RBRACnum array_expr",
"method_decls : method_decl",
"method_decls : decls method_decl",
"method_decls : decls",
"method_decls : method_decls method_decl",
"method_decl : METHODnum type IDnum LPARENnum parama_bubble RPARENnum block",
"method_decl : METHODnum voids IDnum LPARENnum parama_bubble RPARENnum block",
"voids : VOIDnum",
"parama_bubble : params",
"parama_bubble :",
"params : INTnum identifier_list",
"params : VALnum INTnum identifier_list SEMInum params",
"params : VALnum INTnum identifier_list",
"params : INTnum identifier_list SEMInum params",
"identifier_list : IDnum",
"identifier_list : identifier_list COMMAnum IDnum",
"block : decls statement_list",
"block : statement_list",
"type : INTnum",
"type : INTnum type_braces",
"type : IDnum",
"type : IDnum type_braces",
"type_braces : LBRACnum RBRACnum",
"type_braces : LBRACnum RBRACnum DOTnum field_op_type",
"type_braces : LBRACnum RBRACnum type_braces",
"field_op_type : type",
"braces : LBRACnum RBRACnum",
"braces : braces LBRACnum RBRACnum",
"statement_list : LBRACEnum statement_inner RBRACEnum",
"statement_inner : statement",
"statement_inner : statement SEMInum statement_inner",
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
"simple_expression : optional_plus_minus",
"simple_expression : optional_plus_minus PLUSnum other_term",
"simple_expression : optional_plus_minus MINUSnum other_term",
"simple_expression : optional_plus_minus ORnum other_term",
"optional_plus_minus : PLUSnum term",
"optional_plus_minus : MINUSnum term",
"optional_plus_minus : term",
"other_term : term",
"other_term : term MINUSnum other_term",
"other_term : term PLUSnum other_term",
"other_term : term ORnum other_term",
"term : factor",
"term : factor TIMESnum other_factor",
"term : factor DIVIDEnum other_factor",
"term : factor ANDnum other_factor",
"other_factor : factor TIMESnum other_factor",
"other_factor : factor DIVIDEnum other_factor",
"other_factor : factor ANDnum other_factor",
"other_factor : factor",
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
#line 374 "gens/grammar.y"
int yyerror(char *s){
	printf("FAIL %s %d, line %d, column: %d\n", s, yychar, yyline, yycolumn);
}

#line 467 "src/y.tab.c"

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
case 1:
#line 62 "gens/grammar.y"
	{yyval.tptr = MakeTree(ProgramOp, yystack.l_mark[0].tptr, MakeLeaf(IDNode, yystack.l_mark[-2].intg)); printtree(yyval.tptr, 0);}
break;
case 2:
#line 67 "gens/grammar.y"
	{yyval.tptr = MakeTree(ClassOp, NullExp(), yystack.l_mark[0].tptr);}
break;
case 3:
#line 69 "gens/grammar.y"
	{yyval.tptr = MakeTree(ClassOp, yystack.l_mark[-1].tptr, yystack.l_mark[0].tptr);}
break;
case 4:
#line 73 "gens/grammar.y"
	{yyval.tptr = MakeTree(ClassDefOp, yystack.l_mark[0].tptr, MakeLeaf(IDNode, yystack.l_mark[-1].intg));}
break;
case 5:
#line 76 "gens/grammar.y"
	{yyval.tptr = NullExp();}
break;
case 6:
#line 78 "gens/grammar.y"
	{yyval.tptr = yystack.l_mark[-1].tptr;}
break;
case 7:
#line 82 "gens/grammar.y"
	{yyval.tptr = yystack.l_mark[-1].tptr;}
break;
case 8:
#line 84 "gens/grammar.y"
	{yyval.tptr = NullExp();}
break;
case 9:
#line 88 "gens/grammar.y"
	{yyval.tptr = MakeTree(DeclOp, NullExp(), yystack.l_mark[0].tptr);}
break;
case 10:
#line 90 "gens/grammar.y"
	{yyval.tptr = MakeTree(DeclOp, yystack.l_mark[-1].tptr, yystack.l_mark[0].tptr);}
break;
case 11:
#line 94 "gens/grammar.y"
	{yyval.tptr = yystack.l_mark[0].tptr;}
break;
case 12:
#line 97 "gens/grammar.y"
	{tree temp = MakeTree(CommaOp, yystack.l_mark[-3].tptr, MakeTree(CommaOp, type_ptr, yystack.l_mark[-1].tptr)); yyval.tptr = MakeTree(DeclOp, NullExp(), temp);}
break;
case 13:
#line 99 "gens/grammar.y"
	{tree temp = MakeTree(CommaOp, yystack.l_mark[-1].tptr, MakeTree(CommaOp, type_ptr, NullExp())); yyval.tptr = MakeTree(DeclOp, NullExp(), temp);}
break;
case 14:
#line 101 "gens/grammar.y"
	{tree temp = MakeTree(CommaOp, yystack.l_mark[-2].tptr, MakeTree(CommaOp, type_ptr, NullExp())); yyval.tptr = MakeTree(DeclOp, yystack.l_mark[0].tptr, temp);}
break;
case 15:
#line 103 "gens/grammar.y"
	{tree temp = MakeTree(CommaOp, yystack.l_mark[-4].tptr, MakeTree(CommaOp, type_ptr, yystack.l_mark[-2].tptr)); yyval.tptr = MakeTree(DeclOp, yystack.l_mark[0].tptr, temp);}
break;
case 16:
#line 107 "gens/grammar.y"
	{yyval.tptr = MakeLeaf(IDNode, yystack.l_mark[-1].intg);}
break;
case 17:
#line 109 "gens/grammar.y"
	{yyval.tptr = MakeLeaf(IDNode, yystack.l_mark[0].intg);}
break;
case 18:
#line 113 "gens/grammar.y"
	{yyval.tptr = yystack.l_mark[0].tptr;}
break;
case 19:
#line 115 "gens/grammar.y"
	{yyval.tptr = yystack.l_mark[0].tptr;}
break;
case 20:
#line 117 "gens/grammar.y"
	{yyval.tptr = yystack.l_mark[0].tptr;}
break;
case 21:
#line 121 "gens/grammar.y"
	{yyval.tptr = MakeTree(ArrayTypeOp, yystack.l_mark[-1].tptr, type_ptr);}
break;
case 22:
#line 124 "gens/grammar.y"
	{yyval.tptr = MakeTree(CommaOp, NullExp(), yystack.l_mark[0].tptr);}
break;
case 23:
#line 126 "gens/grammar.y"
	{yyval.tptr = MakeTree(CommaOp, yystack.l_mark[0].tptr, yystack.l_mark[-2].tptr);}
break;
case 24:
#line 130 "gens/grammar.y"
	{yyval.tptr = MakeTree(ArrayTypeOp, yystack.l_mark[0].tptr, MakeLeaf(INTEGERTNode, 0));}
break;
case 25:
#line 134 "gens/grammar.y"
	{yyval.tptr = MakeTree(BoundOp, NullExp(), yystack.l_mark[-1].tptr );}
break;
case 26:
#line 136 "gens/grammar.y"
	{yyval.tptr = MakeTree(BoundOp, yystack.l_mark[0].tptr, yystack.l_mark[-2].tptr);}
break;
case 27:
#line 140 "gens/grammar.y"
	{yyval.tptr = MakeTree(BodyOp, NullExp(), yystack.l_mark[0].tptr);}
break;
case 28:
#line 142 "gens/grammar.y"
	{yyval.tptr = MakeTree(BodyOp, yystack.l_mark[-1].tptr, yystack.l_mark[0].tptr);}
break;
case 29:
#line 144 "gens/grammar.y"
	{yyval.tptr = MakeTree(BodyOp, yystack.l_mark[0].tptr, NullExp());}
break;
case 30:
#line 146 "gens/grammar.y"
	{yyval.tptr = MakeTree(BodyOp, yystack.l_mark[-1].tptr, yystack.l_mark[0].tptr);}
break;
case 31:
#line 150 "gens/grammar.y"
	{yyval.tptr = MakeTree(MethodOp, MakeTree(HeadOp, MakeLeaf(IDNode, yystack.l_mark[-4].intg), yystack.l_mark[-2].tptr), yystack.l_mark[0].tptr);}
break;
case 32:
#line 152 "gens/grammar.y"
	{yyval.tptr = MakeTree(MethodOp, MakeTree(HeadOp, MakeLeaf(IDNode, yystack.l_mark[-4].intg), yystack.l_mark[-2].tptr), yystack.l_mark[0].tptr);}
break;
case 33:
#line 156 "gens/grammar.y"
	{type_ptr = NullExp();}
break;
case 34:
#line 160 "gens/grammar.y"
	{yyval.tptr = MakeTree(SpecOp, yystack.l_mark[0].tptr, type_ptr);}
break;
case 35:
#line 162 "gens/grammar.y"
	{yyval.tptr = MakeTree(SpecOp, NullExp(), type_ptr);}
break;
case 36:
#line 165 "gens/grammar.y"
	{yyval.tptr = MakeTree(RArgTypeOp, MakeTree(CommaOp, yystack.l_mark[0].tptr, MakeLeaf(INTEGERTNode, 0)), NullExp());}
break;
case 37:
#line 167 "gens/grammar.y"
	{yyval.tptr = MakeTree(VArgTypeOp, MakeTree(CommaOp, yystack.l_mark[-2].tptr, MakeLeaf(INTEGERTNode, 0)), yystack.l_mark[0].tptr);}
break;
case 38:
#line 169 "gens/grammar.y"
	{yyval.tptr = MakeTree(VArgTypeOp, MakeTree(CommaOp, yystack.l_mark[0].tptr, MakeLeaf(INTEGERTNode, 0)), NullExp());}
break;
case 39:
#line 171 "gens/grammar.y"
	{yyval.tptr = MakeTree(RArgTypeOp, MakeTree(CommaOp, yystack.l_mark[-2].tptr, MakeLeaf(INTEGERTNode, 0)), yystack.l_mark[0].tptr);}
break;
case 40:
#line 175 "gens/grammar.y"
	{yyval.tptr = MakeTree(CommaOp, MakeLeaf(IDNode, yystack.l_mark[0].intg), NullExp());}
break;
case 41:
#line 177 "gens/grammar.y"
	{yyval.tptr = MakeTree(CommaOp, MakeLeaf(IDNode, yystack.l_mark[0].intg), yystack.l_mark[-2].tptr);}
break;
case 42:
#line 181 "gens/grammar.y"
	{yyval.tptr = MakeTree(BodyOp, yystack.l_mark[-1].tptr, yystack.l_mark[0].tptr);}
break;
case 43:
#line 183 "gens/grammar.y"
	{yyval.tptr = MakeTree(BodyOp, NullExp(), yystack.l_mark[0].tptr);}
break;
case 44:
#line 187 "gens/grammar.y"
	{yyval.tptr = MakeTree(TypeIdOp, MakeLeaf(INTEGERTNode, 0), NullExp()); type_ptr = yyval.tptr;}
break;
case 45:
#line 189 "gens/grammar.y"
	{yyval.tptr = MakeTree(TypeIdOp,MakeLeaf(INTEGERTNode, 0), yystack.l_mark[0].tptr); type_ptr = yyval.tptr;}
break;
case 46:
#line 191 "gens/grammar.y"
	{yyval.tptr = MakeTree(TypeIdOp, MakeLeaf(IDNode, yystack.l_mark[0].intg), NullExp()); type_ptr = yyval.tptr;}
break;
case 47:
#line 193 "gens/grammar.y"
	{yyval.tptr = MakeTree(TypeIdOp, MakeLeaf(IDNode, yystack.l_mark[-1].intg), yystack.l_mark[0].tptr); type_ptr = yyval.tptr;}
break;
case 48:
#line 197 "gens/grammar.y"
	{yyval.tptr = MakeTree(IndexOp, NullExp(), NullExp());}
break;
case 49:
#line 199 "gens/grammar.y"
	{yyval.tptr = MakeTree(IndexOp, NullExp(), yystack.l_mark[0].tptr);}
break;
case 50:
#line 201 "gens/grammar.y"
	{yyval.tptr = MakeTree(IndexOp, NullExp(), yystack.l_mark[0].tptr);}
break;
case 51:
#line 205 "gens/grammar.y"
	{yyval.tptr = MakeTree(FieldOp, yystack.l_mark[0].tptr, NullExp());}
break;
case 54:
#line 213 "gens/grammar.y"
	{yyval.tptr = yystack.l_mark[-1].tptr;}
break;
case 55:
#line 217 "gens/grammar.y"
	{yyval.tptr = MakeTree(StmtOp, NullExp(), yystack.l_mark[0].tptr);}
break;
case 56:
#line 219 "gens/grammar.y"
	{yyval.tptr = MakeTree(StmtOp, yystack.l_mark[0].tptr, yystack.l_mark[-2].tptr);}
break;
case 57:
#line 223 "gens/grammar.y"
	{yyval.tptr = yystack.l_mark[0].tptr;}
break;
case 58:
#line 225 "gens/grammar.y"
	{yyval.tptr = yystack.l_mark[0].tptr;}
break;
case 59:
#line 227 "gens/grammar.y"
	{yyval.tptr = yystack.l_mark[0].tptr;}
break;
case 60:
#line 229 "gens/grammar.y"
	{yyval.tptr = yystack.l_mark[0].tptr;}
break;
case 61:
#line 231 "gens/grammar.y"
	{yyval.tptr = yystack.l_mark[0].tptr;}
break;
case 62:
#line 233 "gens/grammar.y"
	{yyval.tptr = NullExp();}
break;
case 63:
#line 237 "gens/grammar.y"
	{yyval.tptr = MakeTree(AssignOp, MakeTree(AssignOp, NullExp(), yystack.l_mark[-2].tptr), yystack.l_mark[0].tptr);}
break;
case 64:
#line 241 "gens/grammar.y"
	{yyval.tptr = MakeTree(RoutineCallOp, yystack.l_mark[-2].tptr, NullExp());}
break;
case 65:
#line 243 "gens/grammar.y"
	{yyval.tptr = MakeTree(RoutineCallOp, yystack.l_mark[-3].tptr, yystack.l_mark[-1].tptr);}
break;
case 66:
#line 248 "gens/grammar.y"
	{yyval.tptr = MakeTree(ReturnOp, yystack.l_mark[0].tptr, NullExp());}
break;
case 67:
#line 250 "gens/grammar.y"
	{yyval.tptr = MakeTree(ReturnOp, NullExp(), NullExp());}
break;
case 68:
#line 254 "gens/grammar.y"
	{yyval.tptr = MakeTree(IfElseOp, MakeTree(CommaOp, yystack.l_mark[-1].tptr, yystack.l_mark[0].tptr), NullExp());}
break;
case 69:
#line 256 "gens/grammar.y"
	{yyval.tptr = MakeTree(IfElseOp, MakeTree(CommaOp, yystack.l_mark[-3].tptr, yystack.l_mark[-2].tptr), yystack.l_mark[0].tptr);}
break;
case 70:
#line 258 "gens/grammar.y"
	{yyval.tptr = MakeTree(IfElseOp, MakeTree(CommaOp, yystack.l_mark[-3].tptr, yystack.l_mark[-2].tptr), yystack.l_mark[0].tptr);}
break;
case 71:
#line 263 "gens/grammar.y"
	{yyval.tptr = MakeTree(LoopOp, yystack.l_mark[-1].tptr, yystack.l_mark[0].tptr);}
break;
case 72:
#line 268 "gens/grammar.y"
	{yyval.tptr = MakeTree(VarOp, MakeLeaf(IDNode, yystack.l_mark[0].intg), NullExp());}
break;
case 73:
#line 270 "gens/grammar.y"
	{yyval.tptr = MakeTree(VarOp, MakeLeaf(IDNode, yystack.l_mark[-1].intg), yystack.l_mark[0].tptr);}
break;
case 74:
#line 274 "gens/grammar.y"
	{yyval.tptr = MakeTree(SelectOp, yystack.l_mark[-1].tptr, NullExp());}
break;
case 75:
#line 276 "gens/grammar.y"
	{yyval.tptr = MakeTree(SelectOp, MakeTree(FieldOp, MakeLeaf(IDNode, yystack.l_mark[0].intg), NullExp()), NullExp());}
break;
case 76:
#line 278 "gens/grammar.y"
	{yyval.tptr = MakeTree(SelectOp, MakeTree(FieldOp, MakeLeaf(IDNode, yystack.l_mark[-1].intg), NullExp()), yystack.l_mark[0].tptr);}
break;
case 77:
#line 280 "gens/grammar.y"
	{yyval.tptr = MakeTree(IndexOp, yystack.l_mark[-2].tptr, yystack.l_mark[0].tptr);}
break;
case 78:
#line 284 "gens/grammar.y"
	{yyval.tptr = MakeTree(IndexOp, yystack.l_mark[0].tptr, NullExp());}
break;
case 79:
#line 286 "gens/grammar.y"
	{yyval.tptr = MakeTree(IndexOp, yystack.l_mark[-2].tptr, yystack.l_mark[0].tptr);}
break;
case 80:
#line 290 "gens/grammar.y"
	{yyval.tptr = yystack.l_mark[0].tptr;}
break;
case 81:
#line 292 "gens/grammar.y"
	{yyval.tptr = MakeTree(LTOp, yystack.l_mark[-2].tptr, yystack.l_mark[0].tptr);}
break;
case 82:
#line 294 "gens/grammar.y"
	{yyval.tptr = MakeTree(LEOp, yystack.l_mark[-2].tptr, yystack.l_mark[0].tptr);}
break;
case 83:
#line 296 "gens/grammar.y"
	{yyval.tptr = MakeTree(EQOp, yystack.l_mark[-2].tptr, yystack.l_mark[0].tptr);}
break;
case 84:
#line 298 "gens/grammar.y"
	{yyval.tptr = MakeTree(NEOp, yystack.l_mark[-2].tptr, yystack.l_mark[0].tptr);}
break;
case 85:
#line 300 "gens/grammar.y"
	{yyval.tptr = MakeTree(GEOp, yystack.l_mark[-2].tptr, yystack.l_mark[0].tptr);}
break;
case 86:
#line 302 "gens/grammar.y"
	{yyval.tptr = MakeTree(GTOp, yystack.l_mark[-2].tptr, yystack.l_mark[0].tptr);}
break;
case 87:
#line 306 "gens/grammar.y"
	{yyval.tptr = yystack.l_mark[0].tptr;}
break;
case 88:
#line 308 "gens/grammar.y"
	{yyval.tptr = MakeTree(AddOp, yystack.l_mark[-2].tptr, yystack.l_mark[0].tptr);}
break;
case 89:
#line 310 "gens/grammar.y"
	{yyval.tptr = MakeTree(SubOp, yystack.l_mark[-2].tptr, yystack.l_mark[0].tptr);}
break;
case 90:
#line 312 "gens/grammar.y"
	{yyval.tptr = MakeTree(OrOp, yystack.l_mark[-2].tptr, yystack.l_mark[0].tptr);}
break;
case 91:
#line 316 "gens/grammar.y"
	{yyval.tptr = yystack.l_mark[0].tptr;}
break;
case 92:
#line 318 "gens/grammar.y"
	{yyval.tptr = MakeTree(UnaryNegOp, yystack.l_mark[0].tptr, NullExp());}
break;
case 93:
#line 320 "gens/grammar.y"
	{yyval.tptr = yystack.l_mark[0].tptr;}
break;
case 94:
#line 324 "gens/grammar.y"
	{yyval.tptr = yystack.l_mark[0].tptr;}
break;
case 95:
#line 326 "gens/grammar.y"
	{yyval.tptr = MakeTree(SubOp, yystack.l_mark[-2].tptr, yystack.l_mark[0].tptr);}
break;
case 96:
#line 328 "gens/grammar.y"
	{yyval.tptr = MakeTree(AddOp, yystack.l_mark[-2].tptr, yystack.l_mark[0].tptr);}
break;
case 97:
#line 330 "gens/grammar.y"
	{yyval.tptr = MakeTree(OrOp, yystack.l_mark[-2].tptr, yystack.l_mark[0].tptr);}
break;
case 98:
#line 334 "gens/grammar.y"
	{yyval.tptr = yystack.l_mark[0].tptr;}
break;
case 99:
#line 336 "gens/grammar.y"
	{yyval.tptr = MakeTree(MultOp, yystack.l_mark[-2].tptr, yystack.l_mark[0].tptr);}
break;
case 100:
#line 338 "gens/grammar.y"
	{yyval.tptr = MakeTree(DivOp, yystack.l_mark[-2].tptr, yystack.l_mark[0].tptr);}
break;
case 101:
#line 340 "gens/grammar.y"
	{yyval.tptr = MakeTree(AndOp, yystack.l_mark[-2].tptr, yystack.l_mark[0].tptr);}
break;
case 102:
#line 344 "gens/grammar.y"
	{yyval.tptr = MakeTree(MultOp, yystack.l_mark[-2].tptr, yystack.l_mark[0].tptr);}
break;
case 103:
#line 346 "gens/grammar.y"
	{yyval.tptr = MakeTree(DivOp, yystack.l_mark[-2].tptr, yystack.l_mark[0].tptr);}
break;
case 104:
#line 348 "gens/grammar.y"
	{yyval.tptr = MakeTree(AndOp, yystack.l_mark[-2].tptr, yystack.l_mark[0].tptr);}
break;
case 105:
#line 350 "gens/grammar.y"
	{yyval.tptr = yystack.l_mark[0].tptr;}
break;
case 106:
#line 355 "gens/grammar.y"
	{yyval.tptr = MakeLeaf(STRINGNode, yystack.l_mark[0].intg); }
break;
case 107:
#line 357 "gens/grammar.y"
	{yyval.tptr = MakeLeaf(NUMNode, yystack.l_mark[0].intg);}
break;
case 108:
#line 359 "gens/grammar.y"
	{yyval.tptr = yystack.l_mark[0].tptr;}
break;
case 109:
#line 361 "gens/grammar.y"
	{yyval.tptr = yystack.l_mark[0].tptr;}
break;
case 110:
#line 363 "gens/grammar.y"
	{yyval.tptr = yystack.l_mark[-1].tptr;}
break;
case 111:
#line 365 "gens/grammar.y"
	{yyval.tptr = MakeTree(NotOp, NullExp(), yystack.l_mark[0].tptr);}
break;
#line 1109 "src/y.tab.c"
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
