
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TextGenerator {

	/**
	 * @param args
	 * @throws KGramSizeException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws KGramSizeException, IOException {
		if(args.length < 3){
			System.out.println("Too little arguments to run program.");
			return;
		}
		StringBuilder a = new StringBuilder();
		
		BufferedReader in = new BufferedReader(new FileReader("novacky.txt"));
		String rawr = in.readLine();
		while(rawr != null){
			a.append(rawr);
			rawr = in.readLine();
		}
		String h = a.toString();
		System.out.println("--------------------------------------------");
		MarkovModel m = new MarkovModel(h, Integer.parseInt(args[0])); 
		System.out.println(h);
		System.out.println("--------------------------------------------");
		char[] holder = new char[Integer.parseInt(args[0])];
		for(int i = 0; i < holder.length; i++){
			holder[i] =h.charAt(i%h.length());
			
		}
		System.out.print(new String(holder));
		int buff = 0;
		for(int i = 0; i < Integer.parseInt(args[1]); i++){
			buff++;
			char temp = m.rand(new String(holder));
			System.out.print(temp);
			for(int j = 0; j < holder.length-1; j++){
				holder[j] = holder[j+1];
			}
			holder[holder.length-1] = temp;
			if(buff - 40 >= 0 && temp==' '){
				System.out.println();
				buff = 0;
			}
		}
		
	}

}
