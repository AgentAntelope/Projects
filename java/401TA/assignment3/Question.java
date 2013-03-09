public class Question{
	private String question;
	private String[] answers;
	private int answerCount, numTries, numCorrect, correctIndex, totalAnswers;
	private boolean gotAnswerRight;

	public Question(String q, int num){
		question = q;
		answers = new String[num];
		totalAnswers = num;
		gotAnswerRight = false;
	}

	public void addAnswer(String answer){
		System.out.println(answer);
		answers[answerCount] = answer;
		answerCount++;
	}

	public void setTries(int t){
		numTries = t;
	}

	public void setCorrect(int c){
		numCorrect = c;
	}
	public void setCorrectAnswer(int a){
		correctIndex = a;
	}
	public void populateAnswer(int indexChosen){
		gotAnswerRight = indexChosen == correctIndex;
	}

	public boolean isCorrectAnswer(){
		return gotAnswerRight;
	}
	public int getCorrectAnswer(){
		return correctIndex;
	}
	public int getCorrect(){
		return numCorrect;
	}

	public int getTries(){
		return numTries;
	}

	public double getPercentRight(){
		return (double)numCorrect / (double) numTries;
	}

	public String toString(){
		String ret = String.format("Question: %s\n", question);
		for (int i = 0; i < answerCount; i++) {
			ret += String.format("\t(%d):%s\n", i, answers[i]);
		}
		return ret;
	}
	public String toStringAnswer(){
		String ret = String.format("Question: %s\n", question);
		ret += String.format("Correct Answer(%d): %s\n", correctIndex, answers[correctIndex]);
		return ret;
	}	
	public String toStringFull(){
		String ret = String.format("Question: %s\n", question);
		for (int i = 0; i < answerCount; i++) {
			ret += String.format("\t(%d):%s\n", i, answers[i]);
		}

		ret += String.format("Correct Tries: %d\nTries:%d\n", numCorrect, numTries);
		return ret;	
	}
}