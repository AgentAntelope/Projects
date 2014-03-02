Author: Sean Myers
Project: MIPS Assembler

To run: 
	`make run`
To save to a file: 
	`make save` (if you want to save to a different file, just edit the output variable in the Makefile)

To recompile java files:
	`make clean`
	`make build_class`

To recompile all files and regenerate flex + cup(jflex command line needs to be installed):
	`make clean`
	`make build`

Files included:
	main  -- main class
	mips.flex -- main flex file for mips.
	mini_mips.cup -- main parser file for mips.
	palindrome.mips -- test file that tests a palindrome (uses lb which I had to implement, so can't use for anyone else :( )
	parser/ -- operations dealing with Intermediate representation after parsing.
	parser/instructions/ -- all implementations of Instructions.
