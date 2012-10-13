package Java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InReader {
	
	public static void main(String[]Args)
	{
	try{
		FileReader fr;
		String fileName = "names.txt";
		fr = new FileReader (fileName);
		BufferedReader br = new BufferedReader(fr);
		String question = br.readLine();
		for (int i = 0; i < 4; i++) {
		String answers = br.readLine();
		}
		fr.close();
		}
		catch (IOException err) {		}
	}
}
