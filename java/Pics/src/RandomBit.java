
public class RandomBit {
	public static int step(StringBuffer bits, int tapbit){
		char lastBit = bits.charAt(0);
		char tap = bits.charAt(bits.length()-1-tapbit);
		if(lastBit == tap){
			return 0;
		}
		else{
			return 1;
		}
	}
	public static String generate(int k, StringBuffer bits, int tapbit){
		StringBuffer kbits = new StringBuffer();
		for(int i = 0; i < k; i++){
			int xor = step(bits,tapbit);
			bits.append(xor);
			kbits.append(xor);
			bits.deleteCharAt(0);
		}
		return kbits.toString();
	}
	   public static void main(String[] args){ 
           StringBuffer bits = new StringBuffer("01101000010"); 
           int tapbit = 8; 
	       if(generate(100,bits,tapbit).equals("1100100100111101101110010110101110011000101111110100100001001101001011110011001001111111011100000101")){
	    	   System.out.println("YAAAY");
	       }
        } 
}
