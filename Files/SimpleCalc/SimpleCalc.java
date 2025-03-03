import java.util.List;		// used by expression evaluator

/**
 *	<Description goes here>
 *
 *	@author	Petros Mzikyan
 *	@since	2/26/2025
 */
public class SimpleCalc {
	
	private ExprUtils utils;	// expression utilities
	
	private ArrayStack<Double> valueStack;		// value stack
	private ArrayStack<String> operatorStack;	// operator stack
	
	private Prompt ask;

	// constructor	
	public SimpleCalc() {
		valueStack = new ArrayStack<Double>();
		operatorStack = new ArrayStack<String>();
		utils = new ExprUtils();
		ask = new Prompt();
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
		boolean run = true;
		while (run)
		{
			System.out.println();
			String expression = ask.getString("").toLowerCase();
			switch (expression)
			{
				case "h":
					printHelp();
					break;
				case "q":
					run = false;
					break;
				default:
					System.out.println(evaluateExpression(
								utils.tokenizeExpression(expression)));
			}
		}
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
		double value = 0.0;
		boolean noValue = true;
		//boolean noOperator = true;
		
		for (String token : tokens) {
			System.out.println(token);
			if (Character.isDigit(token.charAt(0)))
				valueStack.push(Double.parseDouble(token));
			else {
				
				while (!(operatorStack.isEmpty() || 
					!hasPrecedence(token, operatorStack.peek())))
				{
					System.out.println("top is " + operatorStack.peek());
					/*if (operatorStack.peek().equals("(") ||
						operatorStack.peek().equals(")"));
					else */if (noValue)
					{
						value = evaluateSmallExpression(
							valueStack.pop(), valueStack.pop(),
							operatorStack.pop());
						noValue = false;
					}
					else
						value = evaluateSmallExpression(
							value, valueStack.pop(), operatorStack.pop());
				}
				
				operatorStack.push(token);
			}
		}
		
		while (!valueStack.isEmpty() || !operatorStack.isEmpty())
		{
			if (operatorStack.peek().equals("(") ||
						operatorStack.peek().equals(")"))
						operatorStack.pop();
			else if (noValue)
			{
				value = evaluateSmallExpression(
					valueStack.pop(), valueStack.pop(),
					operatorStack.pop());
				noValue = false;
			}
			else
				value = evaluateSmallExpression(
					value, valueStack.pop(), operatorStack.pop());
		}
		
		return value;
	}
		
	public double evaluateSmallExpression(double num1, double num2, 
														String operator)
	{
		System.out.println(num1 + " " + operator + " " + num2);
		switch (operator) {
			case "+":
				return num1 + num2;
			case "-":
				return num1 - num2;
			case "*":
				return num1 * num2;
			case "/":
				return num1 / num2;
		}
		return 0.0;
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
		System.out.println(op1 + " compared to " + op2);
		
		if (op1.equals("^")) return false;
		if (op2.equals("(") || op2.equals(")")) return false;
		if ((op1.equals("*") || op1.equals("/") || op1.equals("%")) 
				&& (op2.equals("+") || op2.equals("-")))
			return false;
		return true;
	}
	 
}
