
public class HellowWorld {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
          int j = 52643, iter = 0;
          int[] printNum = new int[5];
         
       while(j > 0){
    	  int k = j % 10;
    	  printNum[iter] = k;
    	  j = j/10;
    	  iter++;
       }
       for(int i = printNum.length-1; i >= 0; i--)
       {
    	   System.out.print(printNum[i]+ " ");
       }
       
	}

}
