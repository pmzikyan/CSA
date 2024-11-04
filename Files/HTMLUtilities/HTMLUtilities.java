/**
 *	Utilities for handling HTML
 *
 *	@author	Petros Mzikyan
 *	@since	11/1/2024
 */
public class HTMLUtilities {

	/**
	 *	Break the HTML string into tokens. The array returned is
	 *	exactly the size of the number of tokens in the HTML string.
	 *	Example:	HTML string = "Goodnight moon goodnight stars"
	 *				returns { "Goodnight", "moon", "goodnight", "stars" }
	 *	@param str			the HTML string
	 *	@return				the String array of tokens
	 */
	public String[] tokenizeHTMLString(String str) {
		// make the size of the array large to start
		String[] result = new String[20];
		
		String tempToken = "";
		int tokenCount = 0;
		for (int i = 0; i < str.length(); i++)
		{
			char c = str.charAt(i);

			char tokenChar = '\u0000';
			if (tempToken.length() > 0)
				tokenChar = tempToken.charAt(0);
			if (tokenChar == '<' && c == '>')
			{
				tempToken += c;
				result[tokenCount] = tempToken;
				tempToken = "";
				tokenCount++;
			}
			else if (isPunctuation(tokenChar) && !isPunctuation(c))
			{
				result[tokenCount] = tempToken;
				tempToken = "";
				tokenCount++;
			}
			else if (Character.isLetter(tokenChar) && 
								c != '-' && !Character.isLetter(c))
			{
				result[tokenCount] = tempToken;
				tempToken = "";
				tokenCount++;
			}
			
			if (!Character.isWhitespace(c))
			{
				tempToken += c;
			}
		}
		
		// return the correctly sized array
		return result;
	}
	
	public boolean isPunctuation(char c)
	{
		return c == '.' || c == ',' || c == ';' || c == ':' || c == '(' ||
				c == ')' || c == '?' || c == '!' || c == '=' || c == '&' || 
				c == '~' || c == '+' || c == '-';
	}
	
	/**
	 *	Print the tokens in the array to the screen
	 *	Precondition: All elements in the array are valid String objects.
	 *				(no nulls)
	 *	@param tokens		an array of String tokens
	 */
	public void printTokens(String[] tokens) {
		if (tokens == null) return;
		for (int a = 0; a < tokens.length; a++) {
			if (a % 5 == 0) System.out.print("\n  ");
			System.out.print("[token " + a + "]: " + tokens[a] + " ");
		}
		System.out.println();
	}

}
