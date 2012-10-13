#!/usr/bin/perl

use strict;

my @junk = qw/this that theother/;
my @stuff = (10,20, 30, 40);
my $SingleThing = "Is in my drawer";
my @collection = (@junk,@stuff, $SingleThing);
print @collection;
