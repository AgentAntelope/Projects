#Author: Sean Myers
#Purpose: Create a program that shows that I can use syscalls correctly.
.data
msg: .asciiz "The difference of X and Y is: "
prmpt1: .asciiz "This program takes 2 integers and subtracts them\n"
prmpt2: .asciiz "Please enter an X value:\n "
prmpt3: .asciiz "Please enter a Y value:\n "

.text
#Displays the prompt
la $a0, prmpt1
li $v0, 4
syscall 
#
la $a0, prmpt2
syscall
addi $v0, $v0, 1
syscall
move $t0, $v0
#
#Do prompt two
li $v0, 4
la $a0, prmpt3
syscall
li $v0, 5
syscall
#Calculate and display
sub $t1, $t0, $v0
la $a0, msg
li $v0, 4
syscall
move $a0, $t1
li $v0, 1
syscall
li $v0 10
syscall