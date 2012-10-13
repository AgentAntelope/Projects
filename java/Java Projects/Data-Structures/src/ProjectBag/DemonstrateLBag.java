package ProjectBag;

public class DemonstrateLBag {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LBag<String> a = new LBag<String>();
		a.add("Hi");
		a.add("Hdsgi");
		a.add("Hasdi");
		a.add("asHi");
		a.add("Hhfdi");
		for(String b: a)
			System.out.println(b);

	}

}
