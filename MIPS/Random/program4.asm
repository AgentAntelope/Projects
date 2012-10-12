.data
a: .word 0xAD15EA5E
#
#Question 4: It is on the first byte in the 4 byte hex address. Specifically, MIPS
# assigned it to address 0x10010000
#
#Question 5: The address remains unchanged, even with the different declaration style used.
#
#Question 6: Mars simulator is Little Endian which can be seen when it assigns the least significant
# byte first, which is the definition of what little endian would do. 