import java.util.List;		// used by expression evaluator
import java.util.ArrayList;		

/**
 *	<Description goes here>
 *
 *	@author	Banan Badran
 *	@since	3/10/2025
 */
public class SimpleCalc {
	
	private ExprUtils utils;	// expression utilities
	
	private ArrayStack<Double> valueStack;		// value stack
	private ArrayStack<String> operatorStack;	// operator stack
	
	//banan added:
	private ArrayList<Identifier> tracker;

	// constructor	
	public SimpleCalc() {
		utils = new ExprUtils();
		valueStack = new ArrayStack<Double>();
		operatorStack = new ArrayStack<String>();
		
		tracker = new ArrayList<Identifier>();
		tracker.add(new Identifier("e", Math.E));
        tracker.add(new Identifier("pi", Math.PI));
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
	public void runCalc(){
		String input;
		do{
			input = Prompt.getString("");
			if (input != null) {
                if (input.equals("q")) return;
                if (input.equals("h")) printHelp();
                else if (input.equals("l")) {
					System.out.println("Identifiers:");
					for (Identifier id : tracker) {
						System.out.println(" " + id.getName() + " = " + id.getValue());
					}
				}
                else {
					boolean valid =true;
					boolean hasLetter = false;
					boolean notExpre = false;
					if(input.indexOf("=") ==-1) true; 
					for(int i=0;i<input.length(); i++){
						if (isPunctuation(input.charAt(i))) valid = false;
						if ((input.charAt(i) >= 'A' && input.charAt(i) <= 'Z')
							||(input.charAt(i) >= 'a' && input.charAt(i) <= 'z')){
								hasLetter = true;
							}
						if(notExpre){
							if(isNumeric(input.substring(0,i)) && isNumeric())
						}else{
							
						}
					}
					
					if( valid && !input.trim().isEmpty()) processInput(input);
				}
            }
		}while(input != null && !input.equals("q"));
	}
	
	private boolean isPunctuation(char c){
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
        for (int i = 0; i < tokens.size(); i++) {
            String token = tokens.get(i);
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
                Identifier id = findIdentifier(token);
                if (id != null) {
                    valueStack.push(id.getValue());
                } else if (!isOperator(token)) {
                    valueStack.push(0.0);
                } else {
                    while (!operatorStack.isEmpty() && hasPrecedence(token, operatorStack.peek())) {
                        processOperator();
                    }
                    operatorStack.push(token);
                }
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
        double calculatedVal =0.0;
        switch (operator) {
            case "+" -> calculatedVal = a + b;
            case "-" -> calculatedVal = a - b;
            case "*" -> calculatedVal = a * b;
            case "/" -> calculatedVal = a / b;
            case "%" -> calculatedVal = a % b;
            case "^" -> {
				calculatedVal = Math.pow(a, b);
				System.out.println(calculatedVal);
			}
		}
        
        valueStack.push(calculatedVal);
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

    private void processInput(String input) {
        List<String> tokens = utils.tokenizeExpression(input);
        if (tokens.size() >= 3 && tokens.get(1).equals("=")) {
			if(tokens.get(0).equals("e") ||tokens.get(0).equals("pi") ){
				Identifier id = findIdentifier(tokens.get(0));
				System.out.println(tokens.get(0) + " = " + id.getValue());
			} else processAssignment(tokens);
        } else {
            double value = evaluateExpression(tokens);
            System.out.println(value);
        }
    }

    private void processAssignment(List<String> tokens) {
        String varName = tokens.get(0);
        List<String> exprTokens = tokens.subList(2, tokens.size());
        double value = evaluateExpression(exprTokens);
        Identifier id = findIdentifier(varName);
        if (id != null) {
            id.setValue(value);
        } else {
            tracker.add(new Identifier(varName, value));
        }
        System.out.println(varName + " = " + value);
    }

    private Identifier findIdentifier(String name) {
        for (Identifier id : tracker) {
            if (id.getName().equals(name)) {
                return id;
            }
        }
        return null;
    }

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("%") || token.equals("^");
    }
	 
}

class Identifier {
    private String name;
    private double value;
    
    public Identifier(String name, double value) {
        this.name = name;
        this.value = value;
    }
    
    public String getName() {
        return name;
    }
    
    public double getValue() {
        return value;
    }
    
    public void setValue(double value) {
        this.value = value;
    }
}
