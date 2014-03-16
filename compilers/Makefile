LEX_FILE = mylex.l
YACC_FILE = grammar.l
OUTPUT_PROGRAM = mylex

all: lex yacc
	gcc -o $(OUTPUT_PROGRAM) main.c -g

lex:
	lex $(LEX_FILE)

yacc:
	ls	#	yacc $(YACC_FILE)

clean:
	rm lex.yy.c 
	rm mylex
