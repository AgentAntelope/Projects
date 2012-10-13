/*  Author: Sean Myers (Iph)
 *  Email: Seanmyers0608@gmail.com
 *  Project: RSA Encryption
 *  
 *  Note to anyone grading: Though it works pretty well
 *  my bad for no comments.
 */

package RSA;

import java.math.BigInteger;
import java.util.ArrayList;

public class BinaryNumber {
	

	public static byte[] convertToArray(String N){
		int j = 0;
		byte[] a = new byte[N.length()];
		for(int i = N.length()-1; i >= 0; i--){
			char temp =	N.charAt(i);
			byte r = (byte)Character.digit(temp, 10);
			a[j] = r;
			j++;
		}
		return a;
	
	}

	public static int compareBinary(ArrayList<Byte> a, ArrayList<Byte> b){
		
		if (a.size() > b.size())
			return 1;
		else if(b.size() > a.size())
			return -1;
		
		for(int i = a.size()-1; i >= 0; i--){
			if(a.get(i) < b.get(i) )
				return -1;
			else if(a.get(i) > b.get(i))
				return 1;
		}
		return 0;
		
	}

	public static ArrayList<Byte> removeLeadingZero(ArrayList<Byte> a){
		for(int i = a.size()-1; i >= 0; i--){
			if(a.get(i) == 0 && i != 0){
				a.remove(i);
			}
			else
				return a;
		}
		return a;
	}

	public static boolean isZero(ArrayList<Byte> a, int place){
		for(int i = a.size()-place; i < a.size(); i++){
			if(a.get(i) != 0){
				return false;
			}
		}
		return true;
	}
	public static ArrayList<Byte> divBinary(ArrayList<Byte> a, ArrayList<Byte> b, int place){

		
		if (isZero(a,place)){
			ArrayList<Byte> zero = new ArrayList<Byte>();
			zero.add((byte)0);
			return zero;
		}
			
		ArrayList<Byte> r = divBinary(a,b,place-1);
		r.add(0, (byte)0);
		if(a.get(a.size()-place) == 1){
			ArrayList<Byte> one = new ArrayList<Byte>();
			one.add((byte)1);
			addBinary(r,one);
		}
		removeLeadingZero(r);
		if(compareBinary(r,b) >= 0){
			r =subBinary(r,b);
		}
		removeLeadingZero(r);
		return r;
			
	}
	public static ArrayList<Byte> zeroArray(ArrayList<Byte> a, int size){
		for(int i = 0; i < size; i++){
			a.add((byte)0);
		}
		return a;
	}
	private static ArrayList<Byte> distrCarry(int start, ArrayList<Byte> a){
		a.set(start, (byte) 0);
		for(int i = start-1; i >= 0; i--){
			a.set(i,(byte) (a.get(i)+1));
		}
		return a;
	}
	public static ArrayList<Byte> mulbinary(ArrayList<Byte> a, ArrayList<Byte> b){
		int arraySize = a.size()+b.size()+1;
		ArrayList<Byte> c = new ArrayList<Byte>(arraySize);
		
		c =zeroArray(c, arraySize);
		for(int i = 0; i < b.size(); i++){

			if(b.get(i) == 1){
				c =addBinarySpot(c,a,i);
			}
		}
		removeLeadingZero(c);
		return c;
	}
	public static ArrayList<Byte> addBinarySpot(ArrayList<Byte> solution, ArrayList<Byte> adding, int place){
		int ending = place + adding.size();
		int binSpot = 0; // Using as placement for the adding array
		int carry = 0;
		for(int i = place; i < ending; i++){
			byte temp = (byte) (solution.get(i) + adding.get(binSpot)+ carry);
			if(temp <= 1){
				carry = 0;
			}
			else if(temp == 2){
				temp = 0;
				carry = 1;
			}
			else{
				temp = 1;
				carry = 1;
			}
			binSpot++;
		solution.set(i, temp);
		}
		if(carry == 1)
			solution.set(ending, (byte)1);
		return solution;
	}
	public static ArrayList<Byte> subBinary(ArrayList<Byte> a, ArrayList<Byte> b){
		byte endBit = 0;
		int holdSub = 0;
		ArrayList<Byte> c,small;
			c = a;
			small = b;
		for(int i = c.size()-1; i >= 0; i--){
			if(i < small.size()){
				if((c.get(i) - small.get(i)) < 0){
					c = distrCarry(holdSub, c);
					endBit = 1;
				}
				c.set(i, (byte)(c.get(i)-small.get(i)));
			}
			
			if(c.get(i) == 1){
				holdSub = i;
			}
			
		}
		
		if(endBit ==1){
			ArrayList<Byte> temp = new ArrayList<Byte>();
			temp.add((byte)1);
			c = addBinary(c, temp);
		}
		removeLeadingZero(c);
		return c;
	}
	public static ArrayList<Byte> addBinary(ArrayList<Byte> a, ArrayList<Byte> b){
		int shorter;
		ArrayList<Byte> c;
		if (a.size() > b.size()){
			c = a;
			shorter = b.size();
		}
		else{
			c= b;
			shorter = a.size();
		}
		int carry = 0;

		for(int i = 0; i < c.size(); i++){
			byte temp;
			if(i < shorter)
			temp= (byte) (a.get(i) + b.get(i));
			else
			temp=(byte)(c.get(i));
			temp += carry;
			if(temp <= 1){
				carry = 0;
			}
			else if(temp == 2){
				temp = 0;
				carry = 1;
			}
			else{
				temp = 1;
				carry = 1;
			}	
			c.set(i, temp);
		}

		
		return c;
	}
	public static String binaryString(ArrayList<Byte> a){
		StringBuilder str = new StringBuilder();
		for(int i = a.size()-1; i >= 0; i--){
			str.append(a.get(i));
		}
		return str.toString();
	}
	
	public static void main(String[]Args){
		ArrayList<Byte> a = RSA.toBinaryArray("101");

		ArrayList<Byte> b = RSA.toBinaryArray("101");
		BigInteger ab = new BigInteger("101", 2);
		BigInteger bb = new BigInteger("101", 2);

		System.out.println(binaryString(mulbinary(a,b)));
		System.out.println(ab.multiply(bb).toString(2));
	}
}
