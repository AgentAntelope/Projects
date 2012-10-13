import java.util.Scanner;

public class Lab3{
	public static void main(String[] args){
		Scanner keyboard = new Scanner(System.in);

		int base = askInputUpperBound("Please enter a base (must be > 1): ", 1, keyboard);
		int x = askInputUpperBound("Please enter X (must be positive): ", 0, keyboard);

		int exponent = 0;
		while( x >= base ){
			x /= base;
			exponent++;
		}
		System.out.println("The exponent is: " + exponent);
	}

	public static int askInputUpperBound(String output, int check, Scanner keyboard){
		int ret = -1;
		do{
			System.out.print(output);
			ret = keyboard.nextInt();
		}
		while(ret <= check);

		return ret;
	}
}