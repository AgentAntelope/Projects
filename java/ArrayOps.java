import java.util.Arrays;

public class ArrayOps{
	public static void main(String[] args){
		double[] arr = {13.0, 18.0, 22.0, 41.0, 82.0};
		System.out.println(Arrays.toString(arr));
		System.out.printf("Max is %.02f\n", max(arr));
		System.out.printf("Min is %.02f\n", min(arr));
		System.out.printf("Sum is %.02f\n", sum(arr));
		System.out.printf("Average is %.02f\n", ave(arr));
	}

	public static double max(double [] data){
		int maxIndex = 0;
		for (int i = 0; i < data.length; i++) {
			if(data[maxIndex] < data[i]){
				maxIndex = i;
			}
		}
		return data[maxIndex];
	}

	public static double min(double [] data){
		int minIndex = 0;
		for (int i = 0; i < data.length; i++) {
			if(data[minIndex] > data[i]){
				minIndex = i;
			}			
		}
		return data[minIndex];
	}

	public static double sum(double [] data){
		double sum = 0;
		for (int i = 0; i < data.length; i++) {
			sum += data[i];	
		}
		return sum;
	}

	public static double ave(double [] data){
		double sum = sum(data);
	
		return sum/data.length;
	}
}
