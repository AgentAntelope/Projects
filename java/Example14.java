import java.util.*;
public class Example14{
	public static void main(String[] args){
		Scanner keyboard = new Scanner(System.in);
		int b = 10;
		method(b);
		int answer1 = getInteger(keyboard, 0);
		int answer2 = getInteger(keyboard, answer1);
		System.out.println("Num 1: " + answer1 + " num 2:"+ answer2);
	}
	public static void method(int a){

	}
	public static int getInteger(Scanner s, int lowerBound){
		int answer = 0;
		do{
			System.out.println("Enter an integer > " + lowerBound);
			answer = s.nextInt();
		}while(answer <= lowerBound);

		return answer;
	}
}