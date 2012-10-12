.data
x: .byte 10 0 0 0
y: .byte 5 0 0 0
z: .byte 0 0 0 0

.text
lw $t1, x
lw $t2, y
la $t3, x
sub $t4, $t1, $t2
sw $t4, 0($t3)
sw $t4, 4($t3)
sw $t4, 8($t3)