import java.util.*;

public class RandomTest {
Random rawr = new Random(999);
int[] array8 = new int[8];
int[] array16 = new int[8];
int[] array32 = new int[8];
int[] array64 = new int[8];
int[] array128 = new int[8];
int[] array256 = new int[8];
}
public static Integer[] makeRandomArray(int length, int maxElement) {
    Integer[] result = new Integer[length] ;
    Random generator = new Random() ;
    for (int i=0 ; i<length ; i++) {
      result[i] = Math.abs(generator.nextInt() % maxElement) ;
    }
    return result ;
  }