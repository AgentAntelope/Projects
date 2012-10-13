Name: Sean Myers
Project: JFlex Pretty Printer

Files Included:
	pretty.flex -- main jflex file.
	Main.java -- Contains the actual main method.
	ant.sh -- Run all the steps to compile and output the file in one script for linux.
	test_input -- the sample provided on the website.

How to run:
	`java Main <FILE_NAME>`  -- Prints to standard out

Impossibilities:
	For an if/while loop with no opening curly braces, you run into a parsing problem of "Where is the last closing parens?!".

example: if(herp()==derp()) System.out.println(); 

It is a valid statement, but a regular expression can't really pull out which closing parens is for the end of the if.

 A solution that wasn't implemented (due to it technically being a parser) was to make a stack pushing some start symbol onto the stack at the beginning of the ~IF/WHILE~. Then all other opening parens, push another symbol on the stack. Closing parens will pop off the stack, and if all the symbols are popped off the stack, you know it is the end of the `if/while` parens. 
