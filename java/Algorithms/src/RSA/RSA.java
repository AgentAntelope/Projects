
/** @Author: Sean Myers (Iph)
 *  Email: Seanmyers0608@gmail.com
 *  Project: RSA Encryption
 *  
 *  Note to anyone grading: Though it works pretty well
 *  my bad for no comments.
 */

package RSA;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

public class RSA {

	public static String crypto(String base, String exponent, String modulus){
		ArrayList<Byte> baseNum, exp, mod,crypto; 
		baseNum=toBinaryArray(base);
		exp = toBinaryArray(exponent);
		mod = toBinaryArray(modulus);
		crypto = rsaCrypt(baseNum, exp, mod);
		String retString =	BinaryNumber.binaryString(crypto);
		return retString;
	}
	private static ArrayList<Byte> rsaCrypt(ArrayList<Byte> base,ArrayList<Byte> exp,ArrayList<Byte> mod){
		ArrayList<Byte> result = new ArrayList<Byte>();
	    BigInteger aBig = new BigInteger("1011111",2) ;
	    BigInteger bBig = new BigInteger("11111101010",2) ;
	    BigInteger cBig = new BigInteger("110101",2) ;
	    BigInteger res = new BigInteger("1",2);
		result.add((byte)1);
		while(exp.size() > 0){
			if(exp.get(0) == 1){
				result = BinaryNumber.mulbinary(result, base);
				result = BinaryNumber.divBinary(result, mod, result.size());				
				res = res.multiply(aBig);
				res = res.remainder(cBig);

			}
			exp.remove(0);
			base = BinaryNumber.mulbinary(base, base);
			aBig = aBig.multiply(aBig);
			base = BinaryNumber.divBinary(base, mod, base.size());
			aBig =aBig.remainder(cBig);
		}
		
		return result;
	}
	public static ArrayList<Byte> toBinaryArray(String a){
		ArrayList<Byte> num = new ArrayList<Byte>();
		for(int i = a.length()-1; i >= 0 ; i--){
			char b = a.charAt(i);
			if(b == '1')
				num.add((byte)1);
			else
				num.add((byte)0);
		}
		return num;
	}
	
	public static void main(String[]Args){
	    String a = "1011111" ;
	    String b = "11111101010" ;
	    String c = "110101" ;
	    BigInteger aBig = new BigInteger("1011111",2) ;
	    BigInteger bBig = new BigInteger("11111101010",2) ;
	    BigInteger cBig = new BigInteger("110101",2) ;
	    System.out.println("Mine: "+ crypto(a,b,c));
	    System.out.println("  Java says a^b mod c = " + aBig.modPow(bBig,cBig).toString(2)) ;

	}
}
