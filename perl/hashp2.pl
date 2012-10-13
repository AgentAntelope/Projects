#!/usr/bin/perl

use strict;

my %JazzCDColl;
$JazzCDColl {'Milestones'} = 9699852032;
$JazzCDColl{'Workin'} = 2521862962;
$JazzCDColl{"Kind of Blue"} = 75435089;
delete($JazzCDColl{'Workin'});
print %JazzCDColl;
