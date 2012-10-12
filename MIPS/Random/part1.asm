#Sean Myers
.text
li $a0, 0xFFFF0008 #LED memory starts at this address
li $a1, 0x95FAAF56 #LEDs to turn on
jal setLED #Jump and link to setLED
jal notLED #Jump and link to notLED
li $v0, 10 #Exit
syscall
setLED:
move $t0, $a0
move $t1, $a1
sw $t1, 0($t0)
jr $ra
getLED:
move $t0, $a0
lw $v0, 0($t0)
jr $ra
notLED:
move $t0, $a0
lw $t1, 0($t0)
nor $t1, $t1, $0
sw $t1, 0($t0)
jr $ra