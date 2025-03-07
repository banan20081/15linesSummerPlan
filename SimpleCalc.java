import java.util.List;		// used by expression evaluator
// banan added:
import java.util.Scanner;

/**
 *	<Description goes here>
 *
 *	@author	
 *	@since	
 */
public class SimpleCalc {
	
	private ExprUtils utils;	// expression utilities
	
	private ArrayStack<Double> valueStack;		// value stack
	private ArrayStack<String> operatorStack;	// operator stack

	// constructor	
	public SimpleCalc() {
		utils = new ExprUtils();
		valueStack = new ArrayStack<Double>();
		operatorStack = new ArrayStack<String>();
	}
	
	public static void main(String[] args) {
		SimpleCalc sc = new SimpleCalc();
		sc.run();
	}
	
	public void run() {
		System.out.println("\nWelcome to SimpleCalc!!!");
		runCalc();
		System.out.println("\nThanks for using SimpleCalc! Goodbye.\n");
	}
	
	/**
	 *	Prompt the user for expressions, run the expression evaluator,
	 *	and display the answer.
	 */
	public void runCalc() {
		String input = "";
		double value =0.0;
		do{
			input = Prompt.getString("");
			if(input != null){
				if(input.equals("q")) return;
				if(input.equals("h")) printHelp();
				boolean valid = true;
				for(int i=0;i<input.length(); i++){
					if (isPunctuation(input.charAt(i))) valid = false;
					//~ if (!Character.isDigit(input.charAt(i)) && !utils.isOperator(input.charAt(i))) valid = false;
				}
				if(valid){
					System.out.println("valid");
					List<String> tokens = utils.tokenizeExpression(input);
					value = evaluateExpression(tokens);
					System.out.println(value);
				}
				
			}
			else if(input != null && input.equals("l")){ //display current variables;
				
					
			
			}
		}while(input != null && !input.equals("q"));
	}
	
	public boolean isPunctuation(char c){
		return "!_@#$&<>?:;\"{}[]|~`".indexOf(c) != -1;
	}
	
	/**	Print help */
	public void printHelp() {
		System.out.println("Help:");
		System.out.println("  h - this message\n  q - quit\n");
		System.out.println("Expressions can contain:");
		System.out.println("  integers or decimal numbers");
		System.out.println("  arithmetic operators +, -, *, /, %, ^");
		System.out.println("  parentheses '(' and ')'");
	}
	
	/**
	 *	Evaluate expression and return the value
	 *	@param tokens	a List of String tokens making up an arithmetic expression
	 *	@return			a double value of the evaluated expression
	 */
	public double evaluateExpression(List<String> tokens) {
		double value = 0;
		for(int i =0; i < tokens.size(); i++){
			if(tokens.get(i).length() == 1 && utils.isOperator(tokens.get(i).charAt(0)) ) {
				operatorStack.push(tokens.get(i));
			}
			else{
				valueStack.push(Double.parseDouble(tokens.get(i)));
			}
		}
		
		
		//~ check if ( add to stack 
		//~ if ) loop backwards to ( , get the values out of stack and solve it until you get to the ( paranthesis
		//~ popped the left paranthesis 
		//~ create a method that solves any two values with any operator 
		//~ switch 
		
		
		
		//~ while(!operatorStack.isEmpty()){
			//~ String op1 = operatorStack.pop();
			//~ if(operatorStack.isEmpty()){
				
			//~ }
		//~ }
		
		//~ while(!valueStack.isEmpty()){
			//~ System.out.println(valueStack.pop()+ " " );
		//~ }
		//~ while(!operatorStack.isEmpty()){
			//~ System.out.println(operatorStack.pop()+ " " );
		//~ }
		
		return value;
	}
	
	/**
	 *	Precedence of operators
	 *	@param op1	operator 1
	 *	@param op2	operator 2
	 *	@return		true if op2 has higher or same precedence as op1; false otherwise
	 *	Algorithm:
	 *		if op1 is exponent, then false
	 *		if op2 is either left or right parenthesis, then false
	 *		if op1 is multiplication or division or modulus and 
	 *				op2 is addition or subtraction, then false
	 *		otherwise true
	 */
	private boolean hasPrecedence(String op1, String op2) {
		if (op1.equals("^")) return false;
		if (op2.equals("(") || op2.equals(")")) return false;
		if ((op1.equals("*") || op1.equals("/") || op1.equals("%")) 
				&& (op2.equals("+") || op2.equals("-")))
			return false;
		return true;
	}
	 
}
