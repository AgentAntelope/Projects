#Sean Myers
#READ ME!!!!!!!
#All the functions are here, but they are kind of spread throughout and the test in main only is using negate.
#The functions you are actually looking for are the first 3 on the top. The rest are helper functions.
#isNegative is the same. addTotal is add, and negate is negate.

#Some assumptions I made:
#One's complement -0 is negative.
#The user will enter a 32 bit number NO MATTER WHAT.
#If the user fudges up on either addTotal or isNegative in the binary representation, more than likely
#The representation will be wrong. No attempt at error handling was made there.
#If the user fudges up in negate by trying to add a non-1 or non-0, then it will catch and throw an error.
#
#Change test / test2 if you want to try your own code. I figured you would not want to sit there typing
#32 characters every time you ran the code, so just enter once, run multiple times.
#End Readme---------------------------------------------------------------------------------

.data
str:	.space 33	    #String Buffer
test:   .asciiz "00000000000000000000100000000001"
test2: .asciiz "00000000000000000000000010000101"
endline: .asciiz "\n"
error: .asciiz "A number you input is not binary, look at it and try again."
.text
#Main Program
la 		$a0, test
li 		$a1, 1
jal 		isNegative
move	$a0, $v0
li		$v0, 1
syscall
la		$a0, endline
li		$v0, 4
syscall
la 		$a0, test
li 		$a1, 2
jal negate
li		$v0, 4
syscall
la		$a0, endline
syscall
la		$a0, test
la		$a1, test2
la		$a2, str
li		$a3, 1
jal 		addTotal
la		$a0, str
li		$v0, 4
syscall
li 		$v0, 10
syscall

#isNegative function:
#input a0: pointer to the string buffer
#input a1: num representation
#Output v0: either 1 for neg or 0 for positive 
isNegative:
addi 		$sp, $sp, -4 	#Saves on the stack
sw 		$s0, 0($sp)	
move 	$s0, $a0		#Don't destroy registers!
lbu 		$s0, 0($s0)	#loads first char
addi 		$s0, $s0, -48	#converts from ascii to normal
move 	$v0, $s0		#return value stored.
lw		$s0, 0($sp)
addi 		$sp, $sp, 4
jr 		$ra

#a0 string 1
#a1 string 2
#a2 string output
#Char representation.
#v0 string output address.
addTotal:
addi		$sp, $sp, -44
sw		$a0, 0($sp)
sw		$a1, 4($sp)
sw		$ra, 8($sp)
sw		$a3, 12($sp)
sw		$t0, 16($sp)
sw		$a2, 20($sp)
sw		$t1, 24($sp)
sw		$t2, 28($sp)
sw		$s1 32($sp)
sw		$s2, 36($sp)
sw		$s3, 40($sp)
move	$t1, $a3
jal startRight
move 	$s1, $v0		#temporarily store the result in a3
move	$a0, $a1		#Get the second start right
jal startRight
move	$s2, $v0
move	$a0, $a2
jal startRight
move	$s3, $v0		#Ready to go!
li		$t0, 32
li		$a2, 0
#At this point, s1 and s2 are the addresses of the 2 input strings.
#a2 holds the carry number, and s3 holds the result address
addLoop:
beqz		$t0, endAddNorm
lbu		$a0, 0($s1)
lbu		$a1, 0($s2)
jal		addBasic
sb		$v0, 0($s3)
addi		$s3, $s3, -1
addi		$s1, $s1, -1
addi		$s2, $s2, -1
move	$a2, $v1
addi		$t0, $t0, -1
j 		addLoop	
endAddNorm: 
beq		$t1,2, isTwo
beqz		$s3, isTwo
move	$a0, $s3		#Moves the results over to a0 to call displace to the right.
jal		startRight
move	$a0, $v0		#Moves the results over to a0 to call one's complement add.
move	$a1, $a2		#Moves the carry here.
jal		addExtraOne
isTwo:
lw		$a0, 0($sp)
lw		$a1, 4($sp)
lw		$ra, 8($sp)
lw		$a3, 12($sp)
lw		$t0, 16($sp)
lw		$a2, 20($sp)
lw		$t1, 24($sp)
lw		$t2, 28($sp)
lw		$s1 32($sp)
lw		$s2, 36($sp)
lw		$s3, 40($sp)
addi		$sp, $sp, 44
la		$a0, str
li 		$v0, 4
jr		$ra

#a0 is string
#a1 is the number representation
negate:
addi 		$sp, $sp, -20
sw		$a0, 0($sp)
sw		$a1, 4($sp)
sw		$t0, 8($sp)
sw		$t1, 12($sp)
sw		$ra, 16($sp)
li		$t0, 32
negateLoop:
beqz 	$t0, endNegate
lbu		$t1, 0($a0)
beq 		$t1, 48, isZero
beq		$t1, 49, isOne
la $a0, error
li  $a1, 1
li $v0, 55
syscall
li $v0, 10
syscall
isOne:
addi 		$t1, $t1, -1
sb 		$t1, 0($a0)
addi 		$a0, $a0, 1
addi 		$t0, $t0, -1
j	 negateLoop
isZero:
addi 		$t1, $t1, 1
sb 		$t1, 0($a0)
addi 		$a0, $a0, 1
addi 		$t0, $t0, -1
j 	negateLoop
endNegate:
beq		$a1, 1, notTwo
addi		$a0, $a0, -2
addi		$a1, $0, 1
jal		addExtraOne
notTwo:
lw		$ra, 16($sp)
lw		$a0, 0($sp)
lw		$a1, 4($sp)
lw		$t0, 8($sp)
lw		$t1, 12($sp)
addi 		$sp, $sp, 20
jr $ra
#Helper Functions-----------------------------------------------------------------------------------------------------------

#Useful for adding 1 to the string.
#a0 string to start adding the one's to, at the right most element.
#a1 is the carry bit.
addExtraOne:
addi		$sp, $sp, -24
sw		$s0, 0($sp)
sw		$s1, 4($sp)
sw		$a1, 8($sp)
sw		$a2, 12($sp)
sw		$a0,16($sp)
sw		$ra, 20($sp)
move	$a2, $a1		
move	$s1, $a0
li		$a1,48
li		$s0, 31
addExtraLoop:
beqz 	$a2, endOne	#if there is no carry, stop.
beqz		$s0, endOne
lbu		$a0, 1($s1) 		#Kept acting weird and shifting over one less from startRight..dunno why
jal		addBasic			#So I just loaded from one byte over..
sb		$v0, 1($s1)
move	$a2, $v1
addi		$s1, $s1, -1
j addExtraLoop
endOne:
lw		$s0, 0($sp)
lw		$s1, 4($sp)
lw		$a1, 8($sp)
lw		$a2, 12($sp)
lw		$a0,16($sp)
lw		$ra, 20($sp)
addi		$sp, $sp, 24
jr		$ra

#a0:One char in the register
#a1: One char in the register
#a2: 1 or 0 in the register.
#v0 returns byte of Result, in Ascii.
#v1 returns carry, if there is one.
addBasic:
addi		$sp, $sp, -8
sw		$t0, 0($sp)
sw 		$t1, 4($sp)
move 	$t1, $a0
move	$t0, $a1
add 		$t0, $t1, $t0
add 		$t0, $t0, $a2
addi 		$t0, $t0, -96
beq 		$t0, 3, b_three
beq 		$t0, 2, b_two
beq 		$t0, 1, b_one
beq 		$t0, 0, b_zero
b_zero:
addi 		$v0, $0, 48
addi 		$v1, $0, 0
j 		endaddBasic
b_one:
addi 		$v0, $0, 49
addi 		$v1, $0, 0
j 		endaddBasic
b_two:
addi 		$v0, $0, 48
addi 		$v1, $0, 1
j 		endaddBasic
b_three:
addi 		$v0, $0, 49
addi 		$v1, $0, 1
endaddBasic:
lw 		$t0, 0($sp)
lw 		$t1, 4($sp)
addi		$sp, $sp, 8
jr 		$ra

#input a0 of the string buffer.
#v0 pointer to the end of the string, not including \n
startRight:
addi 		$sp, $sp, -8
sw 		$t0, 0($sp)
sw		$t1, 0($sp)
move	$t1, $a0
li		$t0, 31
startRightLoop:
beqz 	$t0, foundEnd
addi		$t1, $t1, 1
addi		$t0, $t0, -1
j 		startRightLoop
foundEnd:
move 	$v0, $t1
lw 		$t0, 0($sp)
lw		$t1, 0($sp)
addi		$sp, $sp, 8
jr		$ra