public class trace2a{

	public static void main(String[] args){
		StringBuilder S1, S2, S3;
		S1 = new StringBuilder("Trace Two");
		S2 = new StringBuilder("Trace Two");
		S3 = S1;
		System.out.println("S1 = " + S1);
		System.out.println("S3 = " + S3);

		if (S1 == S2)
			System.out.println("S1 and S2 are equal");
		if(S1.toString().equals(S2.toString()))
			System.out.println("S1 and S2 are kind of equal");
		if( S1 == S3)
			System.out.println("S1 and S3 are equal");
		if (S1.toString().equals(S3.toString()))
			System.out.println("S1 and S3 are kind of equal");

		S1.append(" is fun");
		S2.append(" is neat");
		S3.append(" is wacky");

		System.out.println("S1 = " + S1);
		System.out.println("S2 = " + S2);
		System.out.println("S3 = " + S3);
	
	}
}
