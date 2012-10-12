.data
a: .asciiz "this bit is 0"
b: .asciiz "this bit is 1"
.text
li $t0, 0x8FFF
addi $v0, $0, 4
srl $t0, $t0, 14
andi $t0, $t0, 1
beq $t0, $0, zero
la $a0, b
j end
zero:
la $a0, a
end:
syscall
addi $v0, $v0, 6
syscall