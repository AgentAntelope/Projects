public class TwoComp{
	public static void main(String[]Args){
		System.out.println(twosComp("111100"));
	}
	public static String twosComp(String binaryNum){
		int finalNum = 0;
		if(binaryNum.charAt(0) == '0'){
			for(int i = 0; i < binaryNum.length(); i++){if(binaryNum.charAt(binaryNum.length()-1-i) == '1'){finalNum += (Math.pow(2, i));}}
			return finalNum + "";
		}
		else{
			binaryNum= flipBits(binaryNum); //Flips the bits since it is negative			
			for(int i = 0; i < binaryNum.length(); i++){if(binaryNum.charAt(binaryNum.length()-1-i) == '1'){finalNum += (Math.pow(2, i));}}
			return "-"+(finalNum+1);
		}
	}
	public static String flipBits(String binaryNum){
		String newNum = "";
		for(int i = 0; i < binaryNum.length(); i++){if(binaryNum.charAt(i) == '0'){newNum += "1";}else{newNum += "0";}}return newNum;
	}}