#!/usr/bin/perl

#Manipulating files

open(FILEIN, "essay.txt");
while(<FILEIN>){ print $_} #File input operator <Name>

chdir('/usr/bin/myfiles'); #Changes the directory, or you can put in
	#The quotes as well
