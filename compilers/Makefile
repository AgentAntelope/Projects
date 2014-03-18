LEX_FILE = mylex.l
YACC_FILE = grammar.y

OUTPUT_PROGRAM = mylex
OBJ_FILES = bin/main.o bin/yacc.o bin/lex.o bin/proj2.o


all: yacc lex proj2 main
	gcc -o $(OUTPUT_PROGRAM) $(OBJ_FILES)

lex:
	lex gens/$(LEX_FILE)
	mv lex.yy.c src/
	gcc -c src/lex.yy.c -o bin/lex.o

yacc:
	yacc -v -s -d -o src/y.tab.c gens/$(YACC_FILE)
	gcc -c src/y.tab.c -o bin/yacc.o

proj2:
	gcc -c src/proj2.c -o bin/proj2.o

main:
	gcc -c src/main.c -o bin/main.o

clean:
	rm lex.yy.c 
	rm mylex
