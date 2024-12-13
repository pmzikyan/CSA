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

	public HTMLUtilities()
	{
		state = TokenState.NONE;
	}

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

		//str = str.trim();
		while (str.length() > 0)
		{
			char c = str.charAt(0);
			if (str.length() >= 4 && str.substring(0, 4).equals("<!--"))
				state = TokenState.COMMENT;
			if (state == TokenState.COMMENT &&
					str.length() >= 3 && str.indexOf("-->") != -1) {
				state = TokenState.NONE;
				str = str.substring(str.indexOf("-->") + 3);
			}

			if (str.length() >= 5 && str.substring(0, 5).equals("<pre>"))
				state = TokenState.PREFORMAT;
			if (state == TokenState.PREFORMAT &&
					str.length() >= 6 && str.indexOf("</pre>") != -1) {
				state = TokenState.NONE;

				result[tokenCount] = str.substring(0, str.indexOf("</pre>") + 6);
				tokenCount++;
				str = str.substring(str.indexOf("</pre>") + 6);
			}

			if (state == TokenState.PREFORMAT)
			{
				result[tokenCount] = str;
				str = "";
				tokenCount++;
			}
			else if (state == TokenState.NONE) {
				if (Character.isWhitespace(c)) {
					str = str.substring(1); }
				else if (c == '<') {
					if (state == TokenState.NONE) {
						result[tokenCount] = str.substring(0, str.indexOf(">") + 1);
						str = str.substring(str.indexOf(">") + 1);
						tokenCount++;
					}
				}
				else if (Character.isLetter(c))
				{
					String word = "";
					boolean loop = true;
					for (int i = 0; i < str.length() && loop; i++)
						if (Character.isLetter(str.charAt(i))
								|| str.charAt(i) == '-') {
							word += str.charAt(i);
						}
						else {
							result[tokenCount] = word;
							str = str.substring(i);
							tokenCount++;
							loop = false;
						}
				}
				else if (Character.isDigit(c) ||
						(c == '-' && Character.isDigit(str.charAt(1)))) {
					String word = "" + str.charAt(0);
					boolean loop = true, e = false, decimal = false;
					for (int i = 1; i < str.length() && loop; i++)
						if (Character.isDigit(str.charAt(i))) {
							word += str.charAt(i);
						}
						else if (!decimal && str.charAt(i) == '.') {
							word += str.charAt(i);
							decimal = true;
						}
						else if (!e && str.charAt(i) == 'e') {
							word += str.charAt(i);
							e = true;
							if (str.charAt(i + 1) == '-') {
								word += str.charAt(i + 1);
								i++;
							}
							if (i == str.length()) tokenCount++;
						}
						else {
							result[tokenCount] = word;
							str = str.substring(i);
							tokenCount++;
							loop = false;
						}
				}
				else if (isPunctuation(c))
				{
					result[tokenCount] = "" + c;
					str = str.substring(1);
					tokenCount++;
				}
				else
					str = str.substring(1);
			}
		}

		// return the correctly sized array

		String[] out = new String[tokenCount];
		for (int i = 0; i < tokenCount; i++)
			out[i] = result[i];
		return out;
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