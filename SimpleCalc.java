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
				else{
					boolean valid = true;
					boolean hasLetter = false;
					for(int i=0;i<input.length(); i++){
						if (isPunctuation(input.charAt(i))) valid = false;
						else if ((input.charAt(i) >= 'A' && input.charAt(i) <= 'Z')
							||(input.charAt(i) >= 'a' && input.charAt(i) <= 'z')){
								hasLetter = true;
							}
							if((isNumeric(input.substring(0,i)) || isNumeric(input.substring(i))) && hasLetter){
									valid = false;
									System.out.println("is false  letter");
								}
								else{
									//~ valid =true;
									System.out.println("real letter");
								}
						//~ if (!Character.isDigit(input.charAt(i)) && !utils.isOperator(input.charAt(i))) valid = false;
					}
					if(valid){
						//System.out.println("valid");
						List<String> tokens = utils.tokenizeExpression(input);
						value = evaluateExpression(tokens);
						System.out.println(value);
					}
				}
				
			}
			else if(input != null && input.equals("l")){ //display current variables;
				// display current ariables
					
			
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
        for (String token : tokens) {
            if (isNumeric(token)) {
                valueStack.push(Double.parseDouble(token));
            } else if (token.equals("(")) {
                operatorStack.push(token);
            } else if (token.equals(")")) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    processOperator();
                }
                operatorStack.pop();
            } else {
                while (!operatorStack.isEmpty() && hasPrecedence(token, operatorStack.peek())) {
                    processOperator();
                }
                operatorStack.push(token);
            }
        }
        while (!operatorStack.isEmpty()) {
            processOperator();
        }
        return valueStack.pop();
    }

    private void processOperator() {
        String operator = operatorStack.pop();
        double b = valueStack.pop();
        double a = valueStack.pop();
        valueStack.push(applyOperator(operator, a, b));
    }

    private double applyOperator(String operator, double a, double b) {
        return switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            case "%" -> a % b;
            case "^" -> Math.pow(a, b);
            default -> throw new IllegalArgumentException("Invalid operator: " + operator);
        };
    }
    
        private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
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
