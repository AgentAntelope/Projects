#!/usr/bin/perl

use strict;
use warnings;

print "Please enter your username ";
my $username;
$username = <STDIN>;
chomp($username);
print "Hello, $username. \n";
