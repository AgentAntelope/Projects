package RecursiveParser;

import java.util.ArrayList;
import java.util.Stack;

public class ParserAronis {
	private static ArrayList<Rule> rules;
	private static String[] sentence;
	private static int wordsPosition;
	private static Stack<String> a;
	public static void initialize(){
		rules = new ArrayList<Rule>();
	}
	public static ParserTree parse(String root, String parse){
		sentence = parse.split(" ");
		wordsPosition = 0;
		return parse(root);
	}
	public static ParserTree parse(String root){
		a.push(root);
		while(a!=null){
			String current = a.pop();
			if(Character.isLowerCase(current.charAt(0))){
				
			}
			
			for(Rule i: rules){
				if(current.equals(i.rule) && i.isUnary){
					a.push(i.firstR);
				}
				else if(current.equals(i.rule) && !i.isUnary){
					a.push(i.firstR);
					a.push(i.secondR);
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
