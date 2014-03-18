LEX_FILE = mylex.l
YACC_FILE = grammar.y

OUTPUT_PROGRAM = mylex
OBJ_FILES = bin/main.o bin/yacc.o bin/lex.o
all: yacc lex main
	gcc -o $(OUTPUT_PROGRAM) $(OBJ_FILES)

lex:
	lex gens/$(LEX_FILE)
	mv lex.yy.c src/
	gcc -c src/lex.yy.c -o bin/lex.o

yacc:
	yacc -v -s -d -o src/y.tab.c gens/$(YACC_FILE)
	gcc -c src/y.tab.c -o bin/yacc.o

main:
	gcc -c src/main.c -o bin/main.o

clean:
	rm lex.yy.c 
	rm mylex
