# CS / COE 447 Keypad + LED Display Simulator Demo

	.data
	.align 2
up:	    .word 0x00800200,0x00800200,0x00800200,0x00800200,0x00000000,0x00000000,0x00000000,0x00000000
down:	.word 0x00000000,0x00000000,0x00000000,0x00000000,0x00C00300,0x00C00300,0x00C00300,0x00C00300
left:	.word 0x00000000,0x00000000,0x00000000,0x00005500,0x00005500,0x00000000,0x00000000,0x00000000
right:	.word 0x00000000,0x00000000,0x00000000,0x00550000,0x00550000,0x00000000,0x00000000,0x00000000
	
	.text

	la		$t0,0xffff0000		# memory mapped keypad input status
	la		$t1,up
poll:
	lbu		$s0,0($t0)
	andi	$s0,$s0,1
	beq		$s0,$0,poll			# loop until status==1 for a key press
	lbu		$s0,4($t0)
	addi	$s0,$s0,-224		# convert key value to number 0-3
	sll		$s0,$s0,5			# convert number to byte offset
	add		$s0,$t1,$s0		
	li		$s1,8
	la		$s2,0xffff0020
led:							# copy bit patterns to LED
	lw		$s3,0($s0)
	sw		$s3,0($s2)
	addi	$s0,$s0,4
	addi	$s2,$s2,32
	addi	$s1,$s1,-1
	bne		$s1,$0,led
	j		poll				# continue - poll for more input

	