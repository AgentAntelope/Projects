.data 
a: .word 0xDEEEEEEE
.text
li $t0, 0xFFFF0008
li $s0, 0x02
la $s3, a
addi $t0,$t0, 7
mainLoop:
beq $t0, 0xFFFF0008, endProgram
lbu $s1, 0($s3)
move $a0, $s1
jal startHalfByte
srl $s2, $s1, 4
move $a0, $s2
subi $t0, $t0, 1
jal startHalfByte
subi $t0, $t0, 1
addi $s3, $s3, 1
j mainLoop
startHalfByte:
move $t1, $a0
li $t2, 0
HB_Loop:
beq $t2, 4, exit_loop
andi $t3, $t1, 1
srl $t1, $t1, 1
beqz $t3, HB_Zero
sll $t5, $t2, 1
sllv $t6, $s0, $t5
lbu $t4, 0($t0)
or $t4, $t4, $t6
sb $t4, 0($t0)
HB_Zero:
addi $t2, $t2, 1
j HB_Loop
exit_loop:
jr $ra
endProgram:
li $v0, 10
syscall