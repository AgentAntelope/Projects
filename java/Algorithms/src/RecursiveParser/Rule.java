package RecursiveParser;
/**
 * @author Sean Myers
 * email: seanmyers0608@gmail.com
 *
 */
public class Rule {
	boolean isWord;
	boolean isUnary;
	String rule;
	String firstR;
	String secondR;
	
	/**
	 * @param rule- the rule to be added
	 * @param a- the definition of the rule.
	 */
	public Rule(String rule, String b){
		isUnary = true;
		isWord =Character.isLowerCase(b.charAt(0));
		this.rule = rule;
		firstR = b;
	}
	/**
	 * @param rule-the rule to be added
	 * @param a- the first part of the definition of the rule
	 * @param b- the 2nd part of the definition of the rule.
	 */
	public Rule(String rule, String a, String b){
		isWord = false;
		isUnary = false;
		this.rule = rule;
		firstR = a;
		secondR = b;
	}

	
}
