/**
 *	Test the Prompt class
 *	@author	Mr Greenstein
 */

public class PromptTester {

	public static void main(String[] args) {
		String str = Prompt.getString("Provide me a string");
		System.out.println("Here it is -> " + str);
		
		int a = Prompt.getInt("Give me an integer");
		System.out.println("Here it is -> " + a);
		
		a = Prompt.getInt("Give me an integer", 20, 40);
		System.out.println("Here it is -> " + a);
		
		double d = Prompt.getDouble("Give me a double");
		System.out.println("Here it is -> " + d);
		
		d = Prompt.getDouble("Give me a double", 20, 40);
		System.out.println("Here it is -> " + d);
	}

}
