.data
a: .word 0xFF119918
storage: .space 16
.text
li $s0, 0x11
li $t0, 0xFFFF000F
la $s1, a
lb $a0, 0($s1)
jal lightWords
li $v0, 10
syscall

lightWords:
la $t0, storage
li $t4, 0x1
li $t5, 0x3
addi $t0, $t0, 16
lw $t2, a
lightloop:
#Check to see if the current word is
and $t3, $t2, $t4
sll $t4, $t4,1 
beqz $t3, unlit
lb $t6, 0($t0)
or $t6, $t5, $t6
sb $t6, 0($t0)
unlit:
sll $t5, $t5, 1
j lightloop
endLoop:
jr $ra