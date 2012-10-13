#!/usr/bin/perl

open(FILEIN, "arrays2.pl");
@Filestat = stat(FILEIN);
$Filesize = $Filestat[9];
$Filesize = $Filesize/3600;
$Filesize = $Filesize/24;
print $Filesize;
