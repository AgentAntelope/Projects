#!/usr/bin/perl
use strict;
my %table = qw/Simpson Bart Hill Hank Flanders Ned/;
my @LastNames = keys %table;
my @FirstNames = values %table;
print @FirstNames "\n";
