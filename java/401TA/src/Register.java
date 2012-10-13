
public class Register {
	private int bits;
	
	protected boolean[] buffer;
	/**
	 * @param n - Size of the register.
	 * @throws IllegalArgument exception if you attempt to enter n that is not 8,16, or 32.
	 * 
	 * Will create a new empty boolean array of size N
	 */
	public Register(int n){
		buffer = new boolean[n];
		bits = n;
	}
	/**
	 * 
	 * @param other
	 * 
	 * Will do a copy of the other's buffer into this new buffer HINT:(try creating a new boolean array)
	 */
	public Register(Register other){
		buffer = new boolean[other.buffer.length];
		for(int i = 0; i < other.buffer.length; i++){
			buffer[i] = other.buffer[i];
		}
		bits = buffer.length;
	}
	/**
	 * 
	 * @return the size of the register
	 */
	public int length(){
		return bits;
	}
	/**
	 * 
	 * @param other
	 * 
	 * Will do an AND operation of all the buffer indeces of both the registers. Store in this array.
	 */
	public void and(Register other){
		assert(this.buffer.length == other.buffer.length);
		for(int i = 0; i < this.buffer.length; i++){
			this.buffer[i] = this.buffer[i] && other.buffer[i];
		}
	}
	
	/**
	 * 
	 * @param other
	 * 
	 * Will do an OR operation of all the buffer indeces of both the registers. Store in this array.
	 */	
	public void or(Register other){
		assert(this.buffer.length == other.buffer.length);
		for(int i = 0; i < this.buffer.length; i++){
			this.buffer[i] = this.buffer[i] || other.buffer[i];
		}		
	}
	/**
	 * 
	 * @param other
	 * 
	 * Will do an XOR operation of all the buffer indeces of both the registers. Store in this array.
	 */
	public void xor(Register other){
		assert(this.buffer.length == other.buffer.length);
		for(int i = 0; i < this.buffer.length; i++){
			this.buffer[i] = (this.buffer[i] && !other.buffer[i])||(!this.buffer[i] && other.buffer[i]);
		}		
	}
	/**
	 * 
	 * @param other
	 * 
	 * Created for you. Don't edit it unless you named your variables differently
	 */
	public void add(Register other){
	      assert(this.buffer.length==other.buffer.length);

	      boolean carry = false;;
	      for(int k=0; k<this.buffer.length-1; k++){
	         if(this.buffer[k] && other.buffer[k] && carry){
	            this.buffer[k] = true;
	            carry = true;
	         }
	         else if(this.buffer[k] && other.buffer[k] && !carry){
	            this.buffer[k] = false;
	            carry = true;
	         }
	         else if(this.buffer[k] && !other.buffer[k] && carry){
	            this.buffer[k] = false;
	            carry = true;
	         }
	         else if(this.buffer[k] && !other.buffer[k] && !carry){
	            this.buffer[k] = true;
	            carry = false;
	         }
	         else if(!this.buffer[k] && other.buffer[k] && carry){
	            this.buffer[k] = false;
	            carry = true;
	         }
	         else if(!this.buffer[k] && other.buffer[k] && !carry){
	            this.buffer[k] = true;
	            carry = false;
	         }
	         else if(!this.buffer[k] && !other.buffer[k] && carry){
	            this.buffer[k] = true;
	            carry = false;
	         }
	         else{
	            this.buffer[k] = false;
	            carry = false;
	         }
	      }
	   }
	
	/**
	 * 
	 * @param string
	 * 
	 * Will load a string from left being the last element of the register buffer to right most bit which is 0 in the array
	 */
	public void load(String string) {
		for(int i = 0; i < string.length(); i++){
			if(string.charAt(string.length()-1-i) == '1'){
				buffer[i] = true;	
			}
			else{
				buffer[i] = false;
			}
			
		}
		
	}
	/**
	 * 
	 * @param n -- index into the buffer array
	 * @return  what is at that bit value
	 */
	public boolean getRegister(int n){
		assert(n >= 0 || n < buffer.length);
		return buffer[n];
	}
	/**
	 * 
	 * @param n --array index to put value
	 * @param val -- value to put in at that bit place
	 * 
	 * 
	 */
	public void setRegister(int n, boolean val){
		buffer[n] = val;
	}
	
	/**
	 * 
	 * @return if a bit is a 1, multiply by 2^i where i is the index it is found at.
	 */
	public long value(){
		long sum = 0;
		for(int i = 0; i < bits-1; i++){
			if(buffer[i]){
				sum+= Math.pow(2, i);
			}
		}
		if(buffer[bits-1]){
			return -sum;
		}
		return sum;
	}
	/**
	 * 
	 * @return if all the bits are 0, or 10000000 then return true, otherwise false
	 */
	public boolean isZero(){
		for(int i = 0; i < bits-1; i++){
			if(buffer[i])
				return false;
		}
		return true;
	}
	public int signBit(){
		if(buffer[bits-1]){
			return 1;
		}
		else{
			return 0;
		}
	}
	public String toString(){
		String bin = "";
		for(int i = 0; i < bits; i++){
			if(buffer[i]){
				bin = "1" + bin;
			}
			else{
				bin = "0" + bin;
			}
		}
		return bin;
	}
}
