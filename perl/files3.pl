#!/usr/bin/perl

open(FILEIN, "essay.txt");
if(-e FILEIN) { print "The Files lives\n"} #-e exists
if(-w FILEIN){ print "Can write\n"} #-w can be written to.
if( -B FILEIN){ print "Binary\n"} #Binary file
if(-T FILEIN) { print "Text\n"} #Text file

#-r is readable
#-w is writeable
#-x is executable
#-o is owned by user
#-e exists
#-z has size of zero, but exists
#-f plain file
#-d entry is directory
#-T text
# -B is binary


while(<FILEIN>){ print $_}
