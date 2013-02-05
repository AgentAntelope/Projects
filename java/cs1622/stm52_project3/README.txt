Author: Sean Myers
Project: Mini Java Parser

To run: 
	`make run`
currently, make run uses test.java. To change, just edit the input_file in the makefile.
To save to a file: 
	`make save` (if you want to save to a different file, just edit the output variable in the Makefile)

To recompile java files:
	`make clean`
	`make build_class`

To recompile all files and regenerate flex + cup(jflex command line needs to be installed):
	`make clean`
	`make build`

