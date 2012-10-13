import java.io.FileInputStream;
import java.util.*;
public class WordServer
{

	String[] words;
	int count;
	Random R;

	public WordServer()
	{
		count = 0;
		words = null;
		R = new Random();  	// Note that the Random object is generated
							// in the constructor, and NOT in the getNextWord()
							// method.  We only want one object, but we want
							// to call nextInt() many times.
	}

	public void loadWords(String inputName) throws Exception
	{
		Scanner S = new Scanner(new FileInputStream(inputName));
		String data = S.nextLine();
		count = Integer.parseInt(data);
		words = new String[count];
		for (int i = 0; i < count; i++)
		{
			data = S.nextLine();
			words[i] = data;
		}
	}

	// To keep from duplicating words, the selected word is removed
	// and the remaining words are shifted over.  Alternatively (and more
	// efficiently) we could just swap the last word into the location of
	// the selected word (it would change the relative locations of the
	// words, but since access is random we probably do not care about this).
	public String getNextWord()
	{
		String val = "";
		if (count > 0)
		{
			int nextInd = R.nextInt(count);
			val = words[nextInd];
			for (int i = nextInd; i < count - 1; i++)
				words[i] = words[i + 1];
			count--;
		}
		return val;
	}
}

