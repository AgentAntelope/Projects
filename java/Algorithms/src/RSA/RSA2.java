package RSA;
import java.math.BigInteger;

/**
 * RSA
 * 
 * TASK:  	performs modular exponentiation
 * 			on arrays of bits
 * 
 * AUTHOR: 	Kristy Caster, kac173@pitt.edu
 * DATE: 	10/17/2010
 *
 */
public class RSA2 {
	
	public static String crypto(String base, String exponent, String modulus){
		int[] b = toArray(base);
		int[] e = toArray(exponent);
		int[] m = toArray(modulus);
        BigInteger ab = new BigInteger(base, 2);
        BigInteger bb = new BigInteger(exponent, 2);
        BigInteger cb = new BigInteger(modulus, 2);
        BigInteger res = new BigInteger("1", 2);
		int[] result = {1};
		
		for(int index=0; index<e.length; index++){
			if(e[index]==1){
				res = res.multiply(ab);
				result = multiply(result,b);

				result = divide(result, m);
				res = res.remainder(cb);
			}
			b = multiply(b,b);
			ab = ab.multiply(ab);

			ab = ab.remainder(cb);
			b = divide(b,m);

		}
		return toString(result);
	}
	

	
	/** ADDITION
	 * adds to arrays of bits
	 * @param x first operand
	 * @param y second operand
	 * @return result of addition
	 */
	private static int[] add(int[] x, int[] y){
		int size = x.length>y.length?x.length:y.length;
		int[] sum = new int[size+1];
		
		for(int index=0; index<size; index++){
			if(index<x.length)
				sum[index] += x[index];
			if(index<y.length)
				sum[index] += y[index];
			if(sum[index]==2){
				sum[index]=0;
				sum[index+1]=1;
			}
			else if(sum[index]==3){
				sum[index]=1;
				sum[index+1]=1;
			}
		}
		return trim(sum);
	}
	
	/** SUBTRACTION
	* subtracts two arrays of bits
	* @param x first operand
	* @param y second operand
	* @return result
	*/
	public static int[] subtract(int[] x, int[] y){
	    int[] result;
	    result = x;
	    int[] extraBit = {0};
	    int carry = 0;
	    
	    for(int i = result.length-1; i >= 0; i--){
	    	if(i < y.length){
			    if((result[i] - y[i]) < 0){
			    	result = borrow(carry, result);
			    	extraBit[0] = 1;
			    }
			    result[i] -= y[i];
	    	} 
			if(result[i] == 1)
				carry = i;
	    }
	   
	    if(extraBit[0]==1)
	    	result = add(result, extraBit);
	    return trim(result);
	}
		
	/** MULTIPLICATION
	 * multiplies two arrays of bits
	 * @param x first operand
	 * @param y second operand
	 * @return result of multiplication
	 */
	private static int[] multiply(int[] x, int[] y){
		int[] product = new int[x.length + y.length];
		
		for(int index=0; index<y.length; index++){
			if(y[index]==1)
				product = add(product, x);
			x = sll(x);
		}
		return trim(product);
	}

	 /** DIVISION
	  * divides two array of bits
	  * @param x divisor
	  * @param y dividend
	  * @return remainder of division
	  */
	private static int[] divide(int[] x, int[] y){         
		if(x.length == 1 && x[0] == 0){
			int[] z = {0};
				return z;
		}
		int[] r = divide(srl(x),y);
		r = sll(r);

		if(x[0]==1){
			int[] one = {1};
			add(r,one);
		}
		//see if r is >= than y
		if(cmpBinary(r,y)>=0)
			r = subtract(r,y);     
		return r;
	}
	
///////////////////////HELPER FUNCTIONS///////////////////////
	
	/** SHIFT LEFT
	 * shifts an array of bits left by one
	 * @param x array of bits to shift
	 * @return array of bits shifted left by one
	 */
	private static boolean isZero(int[] x){
		for(int i = 0; i < x.length; i++){
			if(x[i] != 0){
				return false;
			}
		}
		return true;
	}
	private static int[] sll(int[] x){
		if(x.length==1 && x[0]==0)
			return x;
		else{
			int[] result = new int[x.length+1];
			
			result[0] = 0;
			
			for(int index=0; index<x.length; index++)
				result[index+1] = x[index];
			
			if(result.length==0)
				System.out.println("problem here");
			return trim(result);
		}
	}
	
	/** SHIFT RIGHT
	 * shifts an array of bits right by one
	 * @param x array of bits to shift
	 * @return array of bits shifted right by one
	 */
	private static int[] srl(int[] x){
		if(x.length==1 && x[0]==0){
			x[0] = 0;
			return x;
		}
		else{
			int[] result = new int[x.length-1];
			for(int index=0; index<result.length; index++)
				result[index] = x[index+1];
			return trim(result);
		}
	}
	
	/** TO ARRAY
	 * convert string (of 1s and 0s) to array of bits
	 * @param x string to convert
	 * @return array of bits
	 */
	private static int[] toArray(String x){
		int[] string = new int[x.length()];
		for(int bit=x.length()-1, index=0;bit>=0;bit--, index++){
			string[index] = x.charAt(bit)-48;
		}
		return string;
	}
	
	/** TO STRING
	 * converts of array of bits to a string
	 * @param x array of bits to convert
	 * @return string representation of a binary number
	 */
	private static String toString(int[] x){
		StringBuffer buffer = new StringBuffer();
		
		for(int index=x.length-1; index>-1; index--)
			buffer.append(x[index]);
		
		return "" + buffer;
	}
	
	/** TRIM ARRAY
	 * trims an array of bits of its leading 0s
	 * @param x array to trim
	 * @return trimmed array (left-most bit is a 1)
	 */
	private static int[] trim(int[] x){
		int index=0;
		boolean found = false;
		for(index=x.length-1; index>-1;index--){
			if(x[index]==1){
				found = true;
				break;
			}
		}
		int[] trimmed;
		if(!found){
			trimmed = new int[1];
			trimmed[0] = 0;
		}
		else{
			trimmed = new int[index+1];
			for(index=0;index<trimmed.length;index++)
				trimmed[index] = x[index];
		}
		return trimmed;
	}
	
	/**
	 * assists borrowing in subtraction function
	 */
	private static int[] borrow(int index, int[] x){
        x[index] = 0;  
        for(int i=index-1;i>-1;i--)
        	x[i] = x[i] + 1;
        return x;
	}
	
	/**
	 * compares to arrays of bits to determine is x>=y
	 */
	private static int cmpBinary(int[] x, int[] y){
		if (x.length > y.length)
			return 1;		//x>y
		else if(y.length > x.length)
			return -1;		//x<y
		for(int iindex = x.length-1; iindex >= 0; iindex--){
			if(x[iindex] < y[iindex] )
				return -1;	//x<y
			else if(x[iindex] > y[iindex])
				return 1;	//x>y
		}
		return 0;			//x=y     
	 }
	
	
	public static void main(String[] args){
		  String a = "1011111" ;
		  String b = "11111101010" ;
		  String c = "110101" ;
        int[] a1 = toArray(a);
        int[] b1 = toArray(c);
       
        String r = crypto(a,b,c);
        BigInteger ab = new BigInteger(a, 2);
        BigInteger bb = new BigInteger(b, 2);
        BigInteger cb = new BigInteger(c, 2);

        System.out.println("Encrypt: " + r);
        System.out.println("Encrypt: " +ab.modPow(bb, cb).toString(2));

       
	}
}
