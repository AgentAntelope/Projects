#!/usr/bin/perl

use strict;
my %table = qw/Simpson Bart Hill Hank Flanders Ned/;
my ($key, $value);
while ( ($key, $value) = each(%table) ){
print $key, ' ', $value, "\n";
}
