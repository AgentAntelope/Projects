public class Example8{

	public static void main(String[] args){
		int[] a = new int[1];
		a[0] = 100;
		something(a);
		System.out.println("A: " + a[0]);
	}

	public static void something(int[] num){
		System.out.println(num[0]);
		num[0]++;
	}
}