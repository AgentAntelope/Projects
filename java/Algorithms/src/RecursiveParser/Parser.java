package RecursiveParser;
/**
 * @author Sean Myers
 * email: seanmyers0608@gmail.com
 *
 */
import java.util.ArrayList;


public class Parser {
	private static ArrayList<Rule> rules;
	private static String[] sentence;
	private static int wordsPosition;
	public static void initialize(){
		rules = new ArrayList<Rule>();
	}
	public static ParserTree parse(String root, String parse){
		sentence = parse.split(" ");
		wordsPosition = 0;
		return parse(root, 0);
	}
	public static ParserTree parse(String root, int depth){
		if(sentence[wordsPosition].equals(root)){
			wordsPosition++;
			return new ParserTree(root);
		}

		for(Rule i: rules){
			if(i.rule.equals(root)){
				if(i.isUnary){
					ParserTree child = parse(i.firstR, depth+1);
					if(depth == 0 && wordsPosition != sentence.length){
						wordsPosition = 0;
						continue;
					}
					if(child!= null){
						return new ParserTree(root, child);
					}
				}
				else{
					ParserTree child1 = parse(i.firstR, depth+1);
					if(child1 == null){
						continue;
					}
					ParserTree child2 = parse(i.secondR, depth+1);
					if(child2==null){
						continue;
					}
					if(depth == 0 && wordsPosition != sentence.length){
						wordsPosition = 0;
						continue;
					}

					return new ParserTree(root, child1, child2);

				}	
			}
		}
		return null;
	}

	public static void rule(String lhs, String rhs){
		Rule a = new Rule(lhs, rhs);
		rules.add(a);
	}
	public static void rule(String lhs, String rhs1, String rhs2){
		Rule a = new Rule(lhs, rhs1, rhs2);
		rules.add(a);
	}
}
