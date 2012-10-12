#Author: Sean Myers
#Program: String Manipulation
.data
computerResponse: .asciiz "I'm sorry, Dave. I'm afraid I can't do that."
.text
la $t0, computerResponse
TOP:
lbu $t1, 0($t0)
beq $t1, $0, END
subi $s0, $t1, 97
bgez  $s0, PEW
addi $t0, $t0, 1
j TOP 
PEW:
subi $t1, $t1, 32
sb $t1, 0($t0)
addi $t0,$t0,1
j TOP
END:
la $a0, computerResponse
li $v0, 4
syscall
li $v0, 10
syscall
