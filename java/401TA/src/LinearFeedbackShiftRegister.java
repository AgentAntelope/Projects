/**
 * 
 * @author sean
 */
public class LinearFeedbackShiftRegister extends ShiftRegister {
	private int bitNumber;
	/**
	 * 
	 * @param n - Size of the register.
	 * @param bit the bitNumber to be used for the XOR operation in {@link shiftLeft()}
	 * @throws IllegalArgument exception if you attempt to enter n that is not 8,16, or 32.
	 */
	public LinearFeedbackShiftRegister(int n, int bit) {
		super(n);
		bitNumber = bit;
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * @param n - Number of times to shift left
	 * @return will return the bits in order of called. For example, if I enter
	 * 2, and my first shiftLeft = 0 and my second call is 1, then the return is "01"
	 */
	public String generateBits(int n){
		String ret = "";
		for(int i = 0; i < length(); i++){
			if(shiftLeft()){
				ret += 1;
			}
			else{
				ret += 0;
			}
		}
		return ret;
	}
	/**
	 * @return: will return the exclusive or of bitNumber and super.shiftLeft().
	 * 
	 * You also put that value in the first index in the array of the register.
	 */
	public boolean shiftLeft(){
		boolean exclusiveBit = getRegister(bitNumber);
		boolean otherBit = super.shiftLeft();
		boolean ret =((!otherBit && exclusiveBit)||(otherBit && !exclusiveBit));
		setRegister(0, ret);
		return ret;
	}


}
