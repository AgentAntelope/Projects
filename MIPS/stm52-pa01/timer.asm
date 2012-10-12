#Sean Myers -- Seanmyers0608@gmail.com
.data
buffer: .space 6
ask: .asciiz "Please choose a number like MM:SS or . to end the program: "
.text
#Main Program
Beginning:
la $a0, ask
li $v0, 4
syscall
li $v0, 8
li $a1, 6
la $a0, buffer
syscall
lb $t0, 0($a0)
beq $t0, 46, endProgram
jal getNums
move $s0, $v0
move $s1, $v1
li $a0, 7
li $a1, 0xFFFF0008
jal loadLED
move $a1, $s0
li $a0, 0xFFFF0008
addi $a0, $a0, 32
jal loadGreen
addi $a0, $a0, 128
move $a1, $s1
jal loadGreen
li $a0, 0xFFFF0008
move $a1, $s0
move $a2, $s1
jal countdown
jal endshow
jal endshow
jal endshow
jal endshow
jal endshow
jal endshow
jal endshow
jal endshow
j Beginning
endProgram:
li $v0, 10
syscall
#End of main program


getNums:
lb $t2, 0($a0)
lb $t1, 1($a0)
subi $t2, $t2, 48
subi $t1, $t1, 48
addi $t3, $0, 10
mul $t2, $t3, $t2
add $v0, $t1, $t2
#Get the stuff after the semicolon
lb $t2, 3($a0)
lb $t1, 4($a0)
subi $t2, $t2, 48
subi $t1, $t1, 48
addi $t3, $0, 10
mul $t2, $t3, $t2
add $v1, $t1, $t2
jr $ra

loadLED:
addi $sp, $sp, -12
sw $s0, 0($sp)
sw $ra, 4($sp)
sw $s1, 8($sp)
move $s0, $a0
move $s1, $a1
setRed:
bltz  $a0, endRed
jal lightCol
addi $a0, $a0, -1
j setRed
endRed:
lw $s0, 0($sp)
lw $ra, 4($sp)
lw $s1, 8($sp)
addi $sp, $sp, 12
jr $ra

#input: $a1 is the input second/minute
#input: $a0 is the memory to start at.
loadGreen:
addi $sp, $sp, -12
sw $a0, 0($sp)
sw $ra, 4($sp) #Break Here
sw $s0, 8($sp)
li $a2, 0xF
greenSetLoop:
beqz $a1, endGreen
jal setLight
addi $a1, $a1, -1
j greenSetLoop
endGreen:
lw $a0, 0($sp)
lw $ra, 4($sp)
lw $s0, 8($sp)
addi $sp, $sp, 12
jr $ra
#Input: $a0 is the start of memory at that row.
#Input: $a1 is the exact second/minute to light / delight
#Input: $a2 is the color to set at
setLight:
srl $t0, $a1, 1 #Divides by 2 to get the bytes into the row
add $t0, $t0, $a0 #Adds to the current row memory
lb $t1, 0($t0)
#Checks the byte. Based on if it is even/odd, it will mask and clear the current
#piece of the byte and then insert the current color
andi $t2, $a1, 1
beqz $t2 b_even
andi $t2, $t1 0xF0
or $t2, $t2, $a2
j setLight_ending
b_even:
andi $t2, $t1 0x0F
sll $t3, $a2, 4
or $t2, $t2, $t3
setLight_ending:
sb $t2, 0($t0)
sb $t2, 32($t0)
jr $ra


#Input a0 is a row, and will light the red pieces of it.
#Input a1 is the memory address
lightCol:
addi $sp, $sp, -4
sb $a0, 0($sp)
li $t5, 0x55
sll $a0, $a0, 5
add $t0, $a1, $a0
addi $t1, $t0, 31
red_loop:
slt $t2, $t0, $t1
beqz $t2, end_red
sb $t5, 0($t0)
addi $t0, $t0, 1
j red_loop
end_red:
li $t5, 0x05
sb $t5, -31($t0)
li $t5, 0x50
sb $t5, 0($t1)
lb $a0, 0($sp)
addi $sp, $sp, 4
jr $ra

#The actual part of the countdown timer.
#Input a0 is the memory address
#Input a1 is the minutes
#Input a2 is the seconds
countdown:
addi $sp, $sp, -4
sw $ra, 0($sp)
li $s0, 900
move $s2, $a2
li $a2, 0x5
move $s3, $a1
move $s4, $a0
li $v0, 32
cd_loop:
move $a0, $s0 
syscall
beqz $s2, minuteDown
addi $a0, $s4, 160
move $a1, $s2
jal setLight
addi $s2, $s2, -1
j cd_loop
minuteDown:
beqz $s3, endCD
li $s2, 59
move $a1, $s2
addi $a0, $s4, 160
jal loadGreen
li $a2, 0x5
addi $a0, $s4, 32
move $a1, $s3
jal setLight
addi $s3, $s3, -1
j cd_loop
endCD:
lw $ra, 0($sp)
addi $sp, $sp, 4
jr $ra


endshow:
la $t1,  0xFFFF0008
li $t2, 127
li $v0, 32
li $a0, 100
syscall
endLoop:
beqz $t2, endLoopShow
lw $t3, 0($t1)
nor $t3, $t3, $0
sw $t3, 0($t1)
addi $t2, $t2, -1
addi $t1, $t1, 4
j endLoop
endLoopShow:
jr $ra
