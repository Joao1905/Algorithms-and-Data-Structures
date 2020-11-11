//This algorithm aims to parse math expressions and return the expression's result
//NOTE: THIS ALGORITHM REQUIRES "(" AND ")" FOR EVERY OPERATION i.e.: (((1+2)+(4*2))*3)
import java.util.*; 

public class DjikstraTwoStackAlgorithm {

	public static void main (String[] args) {
		ArrayStack<Character> operatorStack = new ArrayStack<Character>();
		ArrayStack<Double> valueStack = new ArrayStack<Double>();
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		for(char ch : input.toCharArray()) { //While user doesn't press enter twice
			switch (ch) { 
				case '(':		//We must use ' instead of ", otherwise it'd be a string
					break;
				case ' ':
					break;
				case '+': case '-': case '*': case '/':
					operatorStack.push(ch);
					break;
				case ')':
					char operator = operatorStack.pop();
					switch (operator) {
						case '+':
							valueStack.push(valueStack.pop()+valueStack.pop());
							break;
						case '-':
							valueStack.push(valueStack.pop()-valueStack.pop());
							break;
						case '*':
							valueStack.push(valueStack.pop()*valueStack.pop());
							break;
						case '/':
							valueStack.push(valueStack.pop()/valueStack.pop());
							break;
					}
					break;
				default:
					valueStack.push(Double.parseDouble(String.valueOf(ch)));
			}
		}
		System.out.println(valueStack.pop());
	}

}