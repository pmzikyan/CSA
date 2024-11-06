/**
 *	Utilities for handling HTML
 *
 *	@author	Petros Mzikyan
 *	@since	11/1/2024
 */
public class HTMLUtilities {

	// NONE = not nested in a block, COMMENT = inside a comment block
	//PREFORMAT = inside a pre-format block
	private enum TokenState { NONE, COMMENT, PREFORMAT };
	// the current tokenizer state
	private TokenState state; 
	
	/**
	 *	Break the HTML string into tokens. The array returned is
	 *	exactly the size of the number of tokens in the HTML string.
	 *	Example:	HTML string = "Goodnight moon goodnight stars"
	 *				returns { "Goodnight", "moon", "goodnight", "stars" }
	 *	@param str			the HTML string
	 *	@return				the String array of tokens
	 */
	public String[] tokenizeHTMLString(String str) {
		int tokenCount = 0;
		String[] result = new String[30];
		
		str = str.trim();
		while (str.length() >= 1)
		{
			char c = str.charAt(0);
			if (str.length() >= 4 && str.substring(0, 4).equals("<!--"))
				state = TokenState.COMMENT;
			if (str.length() >= 3 && str.indexOf("-->") != -1) {
				state = TokenState.NONE;
				str = str.substring(str.indexOf("-->") + 3);
			}
			else if (Character.isWhitespace(c))
				str = str.substring(1);
			else if (c == '<') {
				if (state == TokenState.NONE) {
					result[tokenCount] = str.substring(0, str.indexOf(">") + 1);
					str = str.substring(str.indexOf(">") + 1);
					tokenCount++;
				}
			}
			else if (Character.isLetter(c))
			{
				for (int i = 0; i < str.length(); i++);
			}
			
				
		}
		
		// return the correctly sized array
		return result;
	}
		/*if (state = TokenState.NONE) {
			result[tokenCount] = ;
			tokenCount++;
		}*/
	
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


/*for (int i = 0; i < str.length(); i++)
		{
			char c = str.charAt(i);
			
			boolean isComment = false;

			char tokenChar = '\u0000';
			if (tempToken.length() > 0)
				tokenChar = tempToken.charAt(0);
			
			if (tempToken.length() >= 4 && tempToken.indexOf("<!--") == 0)
			{
				isComment = true;
				if (tempToken.indexOf("-->") != -1)
				{
					result[tokenCount] = tempToken;
					tempToken = "";
					tokenCount++;
				}
			}
			else if (tokenChar == '<' && c == '>')
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
				i--;
			}
			else if (Character.isLetter(tokenChar) && 
								c != '-' && !Character.isLetter(c))
			{
				result[tokenCount] = tempToken;
				tempToken = "";
				tokenCount++;
				i--;
			}
			
			if (!Character.isWhitespace(c) || isComment)
			{
				tempToken += c;
			}
		}*/
