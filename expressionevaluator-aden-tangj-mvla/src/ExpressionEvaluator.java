// TODO: Auto-generated Javadoc
public class ExpressionEvaluator {
	// These are the required error strings for that MUST be returned on the appropriate error 
	// for the JUnit tests to pass
	private static final String PAREN_ERROR = "Paren Error: ";
	private static final String OP_ERROR = "Op Error: ";
	private static final String DATA_ERROR = "Data Error: ";
	private static final String DIV0_ERROR = "Div0 Error: ";

	// The placeholders for the two stacks
	private GenericStack<Double> dataStack;
	private GenericStack<String>  operStack;
	
	/**
	 * Convert to tokens. Takes a string and splits it into tokens that
	 * are either operators or data. This is where you should convert 
	 * implicit multiplication to explict multiplication. It is also a candidate
	 * for recognizing negative numbers, and then including that negative sign
	 * as part of the appropriate data token.
	 *
	 * @param str the str
	 * @return the string[]
	 */
	private String[] convertToTokens(String str) {
		str = str.replaceAll("^-\\d", "0$0"); // first term negative number
		str = str.replaceAll("\\)\\(", ")*("); // implicit multiplication
		str = str.replaceAll("(\\d)(\\()","$1*(");
		str = str.replaceAll("(\\))(\\d)",")*$2");
		str = str.replaceAll("\\+-", "-"); // negative numbers
		str = str.replaceAll("--", "+");
		str = str.replaceAll("(\\()(-)", "(0$2"); // first term in parenthesis is negative
		str = str.replaceAll("^-\\(", "(0-1*("); // implicit multiplication with negative number
		str = str.replaceAll("\\*-\\(", "*(0-1*(");
		str = str.replaceAll("/-\\(", "/(0-1*(");
		str = checkImpMultNeg(str);
		str = str.replaceAll("[^\\s^a-z^.\\d]", " $0 "); // pads tokens
		str = str.replaceAll("^[\\s]+", ""); // removes whitespace at beginning (tokens at beginning)
		String[] split = str.split("\\s+");
		return split;
	}
	
	/**
	 * Checks for implicit negative multiplication and adds the right )
	 * to balance the parenthesis
	 *
	 * @param str the input string
	 * @return the possibly fixed string
	 */
	private String checkImpMultNeg(String str) {
		int index = str.indexOf("(0-1*("); // finds index of implicit multiplication of negative number
		if (index != -1) {
			for (int i = index; i < str.length(); i++) {
				if (str.charAt(i) == ')') {
					if (!checkParen((str.substring(index,i+1)+")").split(""))) { // Checks if the parenthesis added balances it correctly
						str = str.substring(0,i+1) + ")" + str.substring(i+1);
					}
				}
			}
		}
		return str;
	}
	
	/**
	 * Evaluate expression. This is it, the big Kahuna....
	 * It is going to be called by the GUI (or the JUnit tester),
	 * and:
	 * a) convert the string to tokens
	 * b) if conversion successful, perform static error checking
	 *    - Paren Errors
	 *    - Op Errors 
	 *    - Data Errors
	 * c) if static error checking is successful:
	 *    - evaluate the expression, catching any runtime errors.
	 *      For the purpose of this project, the only runtime errors are 
	 *      divide-by-0 errors.
	 *
	 * @param str the str
	 * @return the string
	 */
	protected String evaluateExpression(String str) {
		String[] tokens = convertToTokens(str);
		String msg = "";
        dataStack =  new GenericStack<Double>();
		operStack =  new GenericStack<String>();
		if (checkParen(tokens)) return PAREN_ERROR + str; // checks errors
		if (checkData(tokens)) return DATA_ERROR + str;
		if (checkOper(tokens)) return OP_ERROR + str;
		for (int i = 0; i < tokens.length; i++) {
			if (tokens[i].matches("-") && tokens[i+1].matches("\\d+\\.*\\d*")) { // handles subtraction, converts to addition
				evaluateOperator("+");
				dataStack.push(Double.parseDouble(tokens[i]+tokens[i+1]));
				i++;
			}
			else if (tokens[i].matches("\\d+\\.*\\d*")) dataStack.push(Double.parseDouble(tokens[i])); // handles data
			else if (tokens[i].matches("\\*|/") && tokens[i+1].matches("-")) { // handles multiplication/division with a negative number
				evaluateOperator(tokens[i]);
				dataStack.push(Double.parseDouble(tokens[i+1]+tokens[i+2]));
				i+=2;
			}
			else { // handles all other operators
				msg = evaluateOperator(tokens[i]);
				if (msg.equals(DIV0_ERROR)) return DIV0_ERROR + str;
			}
		}
		while (!operStack.empty()) { // runs after tokens are all added to stacks
			msg = execute();
			if (msg.equals(DIV0_ERROR)) return DIV0_ERROR + str;
		}
		return (str+ " = " + dataStack.peek());
	}
	
	/**
	 * Evaluates what to do with an operator
	 *
	 * @param operator the current operator
	 * @return a string of the error message if there is one
	 */
	private String evaluateOperator(String operator) {
		String msg = "";
		if (!operStack.empty()) {
			if (compareToken(operator,operStack.peek())) {
				if (!operator.equals(")")) operStack.push(operator); // don't push a )
			}
			else {
				if (operator.equals(")")) { // executes until reaches a (
					while (!operStack.peek().equals("(")) {
						msg = execute();
						if (msg.equals(DIV0_ERROR)) return DIV0_ERROR;
					}
					operStack.pop();
				}
				else {
					msg = execute();
					if (msg.equals(DIV0_ERROR)) return DIV0_ERROR;
					operStack.push(operator);
				}
			}
				
		}
		else
			operStack.push(operator);
		return msg;
	}
	
	/**
	 * Checks for a parenthesis error
	 *
	 * @param list of the tokens or split string
	 * @return true if there is a parenthesis error, false if not
	 */
	private boolean checkParen(String [] tokens) {
		int counter = 0; // ( is a +1 and ) is -1, never should be anything other negative, in end should be 0
		if (tokens.length == 2 && tokens[0].equals("("))
			return true;
		for (String s : tokens) {
			if (s.equals(")"))
				counter--;
			if (s.equals("("))
				counter++;
		}
		if (counter != 0)
			return true;
		return false;
	}
	
	/**
	 * Checks if data entered is in the correct form
	 *
	 * @param list of the tokens or split string
	 * @return true if there is a data error, false if not
	 */
	private boolean checkData(String [] tokens) {
		for (int i = 0; i < tokens.length-1; i++) {
			if (!tokens[i].matches("\\d+\\.*\\d*|[()\\-+*/]")) // consecutive operations
				return true;
			if (tokens[i].matches("\\d+\\.{2,}.+")) // consecutive . i.e. 3...
				return true;
			if (tokens[i].matches("\\d+\\.*\\d*") && tokens[i+1].matches("\\d+\\.*\\d*")) // two consecutive pieces of data
				return true;
		}
		if (!tokens[tokens.length-1].matches("\\d+\\.*\\d*|[()-\\+*/]")) // checks for valid end token
			return true;
		return false;
	}
	
	/**
	 * Checks if there is an error for the operations entered
	 *
	 * @param list of the tokens or split string
	 * @return true if there is a data error, false if not
	 */
	private boolean checkOper(String [] tokens) {
		for (int i = 0; i < tokens.length-2; i++) {
			if (tokens[i].matches("[\\-+*/]") && tokens[i+1].matches("[+*/)]")) // checks for consecutive tokens
				return true;
			if (tokens[i].matches("-") && tokens[i+1].matches("-") && tokens[i+2].matches("-")) // checks triple negatives
				return true;
		}
		if (tokens[0].matches("[)+*/]")) // checks first token
			return true;
		if (tokens[tokens.length-1].matches("[(+*/\\-]")) // checks end tokens
			return true;
		if (tokens[tokens.length-2].matches("[\\-+*/]") && tokens[tokens.length-1].matches("[+*/]")) 
			return true;
		return false;
	}
	
	/**
	 * Executes the top of stack operation
	 *
	 * @return a string if there is a divide by 0 error
	 */
	private String execute() {
		String operation = operStack.pop();
		double d2 = dataStack.pop();
		double d1 = dataStack.pop();
		if (operation.equals("+"))
			dataStack.push(d1+d2);
		else if (operation.equals("-"))
			dataStack.push(d1-d2);
		else if (operation.equals("*"))
			dataStack.push(d1*d2);
		else if (operation.equals("/")) {
			if (d2 == 0)
				return DIV0_ERROR;
			dataStack.push(d1/d2);
		}
		return "";
	}
	
	/**
	 * Compares two operators based on precedence
	 *
	 * @param currOp is the operator being added
	 * @param topOp the operator at the top of the stack
	 * @return true if currOp has higher precedence
	 */
	private boolean compareToken(String currOp, String topOp) {
		if (currOp.equals(")"))
			return false;
		if (currOp.equals("("))
			return true;
		if (topOp.equals("("))
			return true;
		if ((currOp.equals("*")||currOp.equals("/"))&&(topOp.equals("*")||topOp.equals("/")))
			return false;
		if ((currOp.equals("*")||currOp.equals("/"))&&(topOp.equals("+")||topOp.equals("-")))
			return true;
		return false;
	}

}
