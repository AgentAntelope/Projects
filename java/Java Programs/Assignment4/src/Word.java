
public class Word 
{
	private String ActualWord;
	private String HiddenWord;
	private char[] BrokenWord;
	
	public Word(String ActualWord)
	{
		this.ActualWord = ActualWord;
		HiddenWord = LetterHider();
		BrokenWord = new char[ActualWord.length()];
		BrokenWord =HiddenWord.toCharArray();
	}
	public Boolean checkWin()
	{
		if(ActualWord.equalsIgnoreCase(HiddenWord))
			return true;
		else
			return false;
	}
	public Boolean LetterReveal(char guess)
	{
		guess = Character.toLowerCase(guess);
		Boolean isGuess = false;
		StringBuffer AssembleWord = new StringBuffer();
		for(int i = 0; i < ActualWord.length(); i++)
		{
			if (guess== ActualWord.charAt(i))
			{
				BrokenWord[i]=guess;
				isGuess = true;
			}
			
			AssembleWord.append(Character.toUpperCase(BrokenWord[i]));

		}
		
		HiddenWord = AssembleWord.toString();
		return isGuess;
	}
	private String LetterHider()
	{
		StringBuffer Hide = new StringBuffer();
		for(int i = 0; i < ActualWord.length(); i++)
		Hide.append("?");
		return Hide.toString();
	}
	public String getHidden()
	{
		return HiddenWord;
	}
	public String getActual()
	{
		return ActualWord;
	}
}
