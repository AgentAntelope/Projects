package Lab7;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String Yaar = "yaaar";
		char[] HiddenWordArray = new char[Yaar.length()];
		HiddenWordArray=Yaar.toCharArray();
		System.out.println(HiddenWordArray[0]);
		HiddenWordArray[1]='g';
		StringBuffer PewPew = new StringBuffer();
		yag(PewPew);
		System.out.println(PewPew);
	}
	public static void yag(StringBuffer x)
	{
		x.append("hiii");
	}

}
