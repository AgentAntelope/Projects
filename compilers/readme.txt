Name: Sean Myers
Project: 1, Lexer.
Assumptions made:
* If the compiler encounters an error, such as an unended comment, it still tries to keep going. (well, not for that error, but the rest.)
* We are allowed to turn identifiers to lowercase (makes comparisons easier)
* You have lex installed for the command line

How to compile:
`make`

How to clean:
`make clean`

Ideal run scenario:
`./mylexer < <input>`
