.data
print: .asciiz "It is negative"

.text
li $t0, -1
la $a0, print
bgtz $t0, end
addi $v0, $0, 4
syscall
end:
