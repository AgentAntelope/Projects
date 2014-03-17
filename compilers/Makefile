LEX_FILE = mylex.l
YACC_FILE = grammar.y

OUTPUT_PROGRAM = mylex

all: lex yacc
	gcc -o $(OUTPUT_PROGRAM) src/main.c -g

lex:
	lex gens/$(LEX_FILE)

yacc:
	yacc -s -d -o src/y.tab.c gens/$(YACC_FILE)
clean:
	rm lex.yy.c 
	rm mylex
