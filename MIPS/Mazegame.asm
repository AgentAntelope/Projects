#Maze Game
.data
inputName: .asciiz "boards.dat"
err1: .asciiz "There was a problem loading the file, exiting the program"
err2: .asciiz "There were not enough characters loaded into the program, now exiting"
inputData: .space 3072
.text
  # Open (for writing) a file that does not exist
li   		$v0, 13       	# system call for open file
la   		$a0, inputName	
li   		$a1, 0       	 # Open for reading
li   		$a2, 0        	# mode is ignored
  syscall            	# read a file (file descriptor returned in $v0)
bltz		$v0, errExit
move 	$s7, $v0      
li		$v0, 14
move	$a0, $s7
la		$a1, inputData
li		$a2, 3072
syscall
blt		$v0, $a2, errCharExit
la		$a0, inputData
jal		setBoard
jal		playGame
li		$v0, 10
syscall


#Play current board:
#s1 is the player's row
#s2 is the players column
#s3
#s3 is the number of gold pots left to get.

playGame:
li		$s2, 0
li		$s1, 0
la		$t0,0xffff0000		# memory mapped keypad input status
poll:
lbu		$t1,0($t0)
andi		$t1,$t1,1
beq		$t1,$0,poll			# loop until status==1 for a key press
lbu		$t1,4($t0)
addi		$t1,$t1,-224		# convert key value to number 0-3
beqz		$t1, upClicked
beq		$t1, 1 downClicked
beq		$t1, 2 leftClicked
beq		$t1, 3 rightClicked
upClicked:
addi		$a1, $s1, -1
move	$a0, $s0
jal		_makeMove
addi		$s1, $s1, -1
j		poll
downClicked:
addi		$a1, $s1, 1
move	$a0, $s0
jal		_makeMove
addi		$s1, $s1, 1
j		poll
leftClicked:
addi		$a0, $s0, -1
move	$a1, $s1
jal		_makeMove
addi		$s0, $s0, -1
j		poll
rightClicked:
addi		$a0, $s0, 1
move	$a1, $s1
jal		_makeMove
addi		$s0, $s0, 1
j		poll
#a0 new X to move to
#a1 new Y to move to
_makeMove:
addi		$sp, $sp, -4
sw		$ra, 0($sp)
li		$a2, 0x3
jal 		_setLED
lw		$ra, 0($sp)
addi		$sp, $sp, 8
jr		$ra
#a0 is X
#a1 is Y
#v0 is color returned.
_getLED:
li		$s1, 0xC0
li		$s7,0xFFFF0008
sll	 	$s0, $a1, 5	#Has the current row to put the color in. 
add		$s7, $s7, $s0 
srl		$s0, $a0, 2
add		$s7, $s7, $s0
andi		$s2, $a0, 0x3
li		$s3, 3
sub		$s3, $s3, $s2
sll		$s3, $s3, 1 
sll		$s2,$s2,1 # mult rem by 2 to get bit posn from left
srlv		$s1, $s1, $s2
lbu		$s0, 0($s7)
and		$s0, $s1, $s0
srlv		$v0, $s0, $s3
jr		$ra
_convCharToLED:
#Setting up the game board------------------------------------------------
#a0: Address of first char.
setBoard:
addi		$sp, $sp, -8
sw		$ra, 0($sp)
sw		$a0, 4($sp)
move	$s1, $a0
li		$a0, 0
li		$a1, 0
setBoardLoop:
bgt		$a0, 127 endBoardLoop
lbu		$s2, 0($s1)				#Loads character and checks
beq		$s2, 82, setBoardR
beq		$s2, 0x78, setBoardx
beq		$s2, 0x72	setBoardx
setBoardR:
li		$a2, 0x1
jal 		_setLED	
j		setBoard2
setBoardx:
li		$a2, 0x0
jal		_setLED
setBoard2:
addi		$s1, $s1, 1
addi		$a0, $a0, 1
j setBoardLoop

endBoardLoop:
bgt		$a1, 6, endBoardLoopReal
addi		$a1, $a1, 1
li		$a0, 0
j setBoardLoop
endBoardLoopReal:
li		$a0, 0
li		$a1, 0
li		$a2, 0x3
jal		_setLED
lw		$a0, 4($sp)
lw		$ra, 0($sp)
addi		$sp, $sp, 8
jr		$ra







#a0: X co ord
#a1:Y co ord
#a2: Color
#Using SP since this is called so many times and I do not want to constantly saving other registers.
_setLED:
addi		$sp, $sp, -20
sw		$s0, 0($sp)
sw		$s1, 4($sp)
sw		$s2, 8($sp)
sw		$s3, 12($sp)
sw		$s7, 16($sp)
li		$s0, 32
li		$s2, 0xFF
li		$s3, 0xC0
li		$s7,0xFFFF0008
sll	 	$s0, $a1, 5	#Has the current row to put the color in. 
add		$s7, $s7, $s0 
srl		$s0, $a0, 2
add		$s7, $s7, $s0
andi		$s0, $a0, 0x3
sll		$s0,$s0,1 # mult rem by 2 to get bit posn from left
sll		$s1,$a2,6 # put color value into leftmost LED position
srlv 		$s1,$s1,$s0 # shift LED value right (i.e., from the left)
srlv		$s3, $s3, $s0 #Moves the unmask
and		$s2, $s2, $s3 #Will make sure everything else is unaffected except the bits we need.
not		$s2, $s2
lbu		$s3,0($s7)
and		$s2, $s3, $s2
or		$s2, $s1, $s2
sb		$s2, 0($s7)

lw		$s0, 0($sp)
lw		$s1, 4($sp)
lw		$s2, 8($sp)
lw		$s3, 12($sp)
lw		$s7, 16($sp)
addi		$sp, $sp, 20
jr	$ra
#Basic Error Handling --------------
errExit:
la	$a0, err1
li	$v0, 4
syscall
li	$v0, 10
syscall
errCharExit:
la	$a0, err2
li	$v0, 4
syscall
li	$v0, 10
syscall
#---------------------------------------------
