.data
prompt: .asciiz "Please enter a string: "
buff1: .space 50
buff2: .space 50

.text
la $a0, prompt
li $v0, 4
syscall
la $a0, buff1
li $a1, 49
li $v0, 8
syscall

la $t0, buff1
la $t1, buff2
remove_white:
lbu $t2, 0($t0)
beq $t2, $0, END
beq $t2, 32, incr_jump
beq $t2, 9, incr_jump
sb $t2, 0($t1)
addi $t1, $t1, 1
incr_jump:
addi $t0,$t0, 1
j remove_white
END:
la $a0, buff2
li $v0, 4
syscall