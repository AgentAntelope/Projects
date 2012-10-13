public class Example{
	public static void wacky(int one, int two){
		for(int i = one; i <= two; i++){
			int temp = i;
			System.out.print("Value is ");
			while (temp > 0){
				System.out.print(temp + ", ");	
				temp--;
			}
			System.out.println(temp);
		}
	}
	public static void zany(int x, int y, int z){
		int temp = (x * y) % z;
		System.out.println("Temp is "+ temp);

		boolean test1 = (z >= x);
		boolean test2 = (x >= y);
		boolean test3 = (y >= z);
		if(test1 || test2 && test3){
			System.out.println("Option A");
		}
		else{
			System.out.println("Option B");
		}
		x = 10;	
	}

	public static void main(String [] args){
		wacky(3, 5);

		int first = 2, second = 4, third = 6;
		zany(first, second, third);
		first = 6; second = 2; third = 4;
		zany(first, second, third);

		System.out.println("First is now " + first);	
	}
}
