all: otherfile.o hellomakefile.o
	gcc otherfile.o hellomakefile.o -o helloMake
otherfile.o: otherfile.c
	gcc -c otherfile.c
hellomakefile.o: otherfile.h hellomakefile.c
	gcc -c hellomakefile.c

debug:otherfile.o hellomakefile.o
	gcc otherfile.o hellomakefile.o -g -o helloMake
	gdb helloMake
clean:
	rm otherfile.o hellomakefile.o
