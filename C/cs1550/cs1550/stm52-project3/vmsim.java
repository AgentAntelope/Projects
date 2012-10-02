/**
 * Sean Myers -vmsimulator.
 */

import java.io.IOException;


public class vmsim {
	public static void main(String[]args) throws IOException{
		int size = -1; //Since the table size can't be negative, this is if the size is wrong/formatted wrong.
		String algorithm = null;
		String traceFile = null;
		
		//Check arguments
		for(int i = 0; i < args.length; i++){
			if(args[i].equals("-n") && (i+1 < args.length)){
				try{
				size = Integer.parseInt(args[i+1]);
				}
				catch(NumberFormatException e){
					size = -1;
				}
			}
			else if(args[i].equals("-a")&& (i+1 < args.length)){
				algorithm = args[i+1];
			}
		}
		if(args.length> 0){
			 traceFile = args[args.length-1];
		}
	
		if(size == -1 || algorithm == null|| traceFile == null){
			System.out.println("The arguments you input are incorrect, please try again");
			return;
		}
		if(algorithm.equalsIgnoreCase("opt")){
			new OptAlgorithm(traceFile, size);
		}
		else if(algorithm.equalsIgnoreCase("nru")){
			new NRUAlgorithm(traceFile, size);
		}
		else if(algorithm.equalsIgnoreCase("clock")){
			new ClockAlgorithm(traceFile, size);
		}
		else{
			System.out.println("The algorithm you input is not offered, please try again");
		}
	}
}
