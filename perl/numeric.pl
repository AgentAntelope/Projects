#!/usr/bin/perl

use strict;


my $abc = 5;
my $efg = $abc-- + 5;
my $xyz = ++$efg - --$abc;

print $abc, "\n";
print $efg, "\n";
print $xyz, "\n";
