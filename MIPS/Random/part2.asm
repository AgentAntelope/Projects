.text
li $a0, 0xFFFF0008 #LED memory starts at this address
li $a1, 0x95FAAF56 #LEDs to turn on
li $a2, 4 #Draw $a1's pattern 4 times in a row
jal setLEDRange #Jump and link to setLEDRange
li $v0, 10 #Exit
syscall
setLEDRange:
move $s0, $ra
loop:
beq $a2, $0, endLoop
jal setLED
subi $a2, $a2, 1
addi $a0, $a0, 4
j loop
endLoop:
jr $s0
setLED:
move $t0, $a0
move $t1, $a1
sw $t1, 0($t0)
jr $ra
getLED:
move $t0, $a0
lw $v0, 0($t0)
jr $ra