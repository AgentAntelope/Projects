package MergeSort;
import java.util.*;
public class RandomNumberGenerator {
public static void maim(String[]Args)
{
	Random A = new Random();
int[] B = new int[6];

for(int i = 0; i < B.length; i++)
	{
	B[i]=A.nextInt(9);
	System.out.print(B[i]);
	}

}
}