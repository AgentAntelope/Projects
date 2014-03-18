Name: Sean Myers
Project: 2, Parser
Assumptions made:

* I took some liberties with FormalParameterList since the instructions were a bit unclear. Namely, I made commas separate identifiers while the first comma is the int symbol.
* Some other dummy nodes may be placed where I thought it was a bit better than the current instructions implementation. 
* Since all 10 samples ran without error, my code shouldn't crash.. (hopefully, haha)

* You have lex installed for the command line
* You have yacc installed for the command line
* You are running on a unix system

* The makefile is a bit disorganized due to object creation. Sorry bout that.
How to compile:
`make`

How to clean:
`make clean`

Ideal run scenario:
`./mylexer < <input>`

(I should really name it something different..)


Here is a nice ascii picture of a giraffe: 

         _  o o
         \ \|/ _,
      __.'   /`_/
    /`    u   ;#
    `c-_..__,/ ##
           );:'##
           |   ##
           |:.:##
           |.  ##
           |:.'##
           |.::##
          /  ' ##
          |.:'  ##
          ;::' .:#
         / '     '#
         |  .: '::.`'-..__
         |:.         .::' `',
         |:::   ':.       .:,\
          \ ', .  .::' .::  | |
          |'.|.:|  '     '  /\#
          |  \ '|._.::  |   |##
          | /|.:|  `"";`| .:|##
          / ||  /     | ;  '|
          \ // |      \:'\  |
          | /\ /       ; ;| \
          | || |       | || /
          | || |       | || |
         _/ j| |      _/ J| |
        (/_/_/ J     (/_/_/ j
    jgs    (/_/         (/_/
 
