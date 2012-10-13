package Java;

public class perfect
{
      public static void main(String args[])
      	{
    	  System.out.println("Perfect numbers from 2-100");
    	  find();



      	}	
      public static void find()
      	{
    	  int sum=0,rem=0, i;
    	  int n=1;
    	  do{
			i = 1;
			sum = 0;
			while(i<=n){
				rem=n%i;
				if(rem==0){
					sum+= i;
				}
				if(n==sum/2)
					System.out.println(" "+n+" is a perfect number");
		
				i++;
			}
			n++;
    	  }
    	  while(n<=100);
		
      }
	}
