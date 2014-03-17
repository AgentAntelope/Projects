CUP_FILE = tools/mini_mips.cup
FLEX_FILE = tools/mips.flex
RUN_FILE = palindrome.mips
OUTPUT = test.asm

all: build run

build: build_tools build_class

build_tools:
	java -jar tools/java-cup-11a.jar -interface $(CUP_FILE)
	jflex $(FLEX_FILE)
build_class:
	javac -classpath `pwd`/tools/java-cup-11a.jar:`pwd`/ main.java
run:
	java -classpath `pwd`/tools/java-cup-11a.jar:`pwd`/ main $(RUN_FILE)

save:
	java -classpath `pwd`/tools/java-cup-11a.jar:`pwd`/ main $(RUN_FILE) > $(OUTPUT)
clean:
	rm *.class  parser/*.class  parser/instructions/*.class tools/*.class
