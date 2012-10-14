RUN_FILE = palindrome.mips
OUTPUT = test.asm

all: build run

build: build_tools build_class

build_tools:
	java -jar java-cup-11a.jar -interface mini_mips.cup
	jflex mips.flex

build_class:
	javac -classpath `pwd`/java-cup-11a.jar:`pwd`/ main.java
run:
	java -classpath `pwd`/java-cup-11a.jar:`pwd`/ main $(RUN_FILE)

save:
	java -classpath `pwd`/java-cup-11a.jar:`pwd`/ main $(RUN_FILE) > $(OUTPUT)
clean:
	rm *.class  parser/*.class  parser/instructions/*.class
