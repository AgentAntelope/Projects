import java.util.*;
import java.io.*;

public class Assignment3{
	public static void main(String[] args) throws IOException{
		ArrayList<Question> questions = new ArrayList<Question>();
		Scanner infile = new Scanner(new File(args[0]));
		Scanner keyboard = new Scanner(System.in);
		while(infile.hasNext()){
			questions.add(getQuestionFromFile(infile));
		}

		for(Question question: questions){
			askUserQuestion(question, keyboard);	
		}

		Question easiest = easiestQuestion(questions);
		Question hardest = hardestQuestion(questions);
		int correct = 0;
		int total = 0;
		for(Question question: questions){
			System.out.println(question.toStringAnswer());
			if(question.isCorrectAnswer()){
				correct++;
			}
			total++;
		}
		System.out.printf("----Question Statistics----\n");
		System.out.printf("Questions Correct: %8d/%d\n", correct, total);
		System.out.printf("Questions Incorrect: %6d/%d\n", total-correct, total);
		System.out.printf("Easiest Question: %9d/%d\n", easiest.getCorrect(), easiest.getTries());
		System.out.printf("Hardest Question: %9d/%d\n", hardest.getCorrect(), hardest.getTries());


	}
	public static Question hardestQuestion(ArrayList<Question> questions){
		Question hardest = null;
		for(Question question: questions){
			if(hardest == null){
				hardest = question;
			}

			if(hardest.getPercentRight() > question.getPercentRight()){
				hardest = question;
			}
		
		}
		return hardest;
	}

	public static Question easiestQuestion(ArrayList<Question> questions){
		Question easiest = null;
		for(Question question: questions){
			if(easiest == null){
				easiest = question;
			}

			if(easiest.getPercentRight() < question.getPercentRight()){
				easiest = question;
			}
		
		}
		return easiest;
	}

	public static void askUserQuestion(Question question, Scanner keyboard){
		System.out.println(question);
		System.out.println("Please enter a choice:");
		int choice = Integer.parseInt(keyboard.nextLine());
		question.populateAnswer(choice);
	}

	public static Question getQuestionFromFile(Scanner infile){
		String question = infile.nextLine();
		int numAnswers = Integer.parseInt(infile.nextLine());
		Question q = new Question(question, numAnswers);
		for (int i = 0; i < numAnswers; i++) {
			q.addAnswer(infile.nextLine());
		}
		int correctAnswer = Integer.parseInt(infile.nextLine());
		int numTries = Integer.parseInt(infile.nextLine());
		int numCorrect = Integer.parseInt(infile.nextLine());
		q.setTries(numTries);
		q.setCorrect(numCorrect);
		q.setCorrectAnswer(correctAnswer);
		return q;
	}
}