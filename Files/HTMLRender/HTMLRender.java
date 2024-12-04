import java.util.Scanner;

/**
 *	HTMLRender
 *	This program renders HTML code into a JFrame window.
 *	It requires your HTMLUtilities class and
 *	the SimpleHtmlRenderer and HtmlPrinter classes.
 *
 *	The tags supported:
 *		<html>, </html> - start/end of the HTML file
 *		<body>, </body> - start/end of the HTML code
 *		<p>, </p> - Start/end of a paragraph.
 *					Causes a newline before and a blank line after. Lines are restricted
 *					to 80 characters maximum.
 *		<hr>	- Creates a horizontal rule on the following line.
 *		<br>	- newline (break)
 *		<b>, </b> - Start/end of bold font print
 *		<i>, </i> - Start/end of italic font print
 *		<q>, </q> - Start/end of quotations
 *		<hX>, </hX> - Start/end of heading with size X = 1, 2, 3, 4, 5, 6
 *		<pre>, </pre> - Preformatted text
 *
 *	@author Petros Mzikyan
 * 	@since 11/18/2024
 *	@version 1.0
 */
 
//Compile .jar file with:	javac -cp ".;SimpleHtmlRenderer.jar" *.java
//Run .jar file with:		java -cp ".;SimpleHtmlRenderer.jar" HTMLRender
public class HTMLRender {
	
	// the array holding all the tokens of the HTML file
	private String [] tokens;
	private final int TOKENS_SIZE = 100000;	// size of array

	// SimpleHtmlRenderer fields
	private SimpleHtmlRenderer render;
	private HtmlPrinter browser;
	
	private boolean inB, inI;
	
	private int lineLength;
	private final int[] FORMAT_MAX_LENGTH = {80, 40, 50, 60, 80, 100, 120, 2147483647};
	private int formatOn;
		
	/**
	 *	Constructor that initilializes non-final field variables
	 */
	public HTMLRender() {
		// Initialize token array
		tokens = new String[TOKENS_SIZE];
		
		// Initialize Simple Browser
		render = new SimpleHtmlRenderer();
		browser = render.getHtmlPrinter();
		
		inB = inI = false;
		
		lineLength = 0;
		
		formatOn = 0;
	}
	
	
	public static void main(String[] args) {
		HTMLRender hf = new HTMLRender();
		if (args.length == 0)
			hf.runSample();
		else
			hf.run(args[0]);
	}
	/**
	 *	Goes through the HTML file, tokenizing it with FileUtils,
	 *	then calls printTokens to print each line of tokens.
	 *	@param fileName		the name of the file that will be rendered
	 */
	public void run(String fileName) {
		FileUtils fileUtils = new FileUtils();
		HTMLUtilities htmlUtils= new HTMLUtilities();
		
		// Open the HTML file
		Scanner input = FileUtils.openToRead(fileName);
		
		// Read each line of the HTML file, tokenize, then print tokens
		while (input.hasNext()) {
			String line = input.nextLine();
			String [] tokens = htmlUtils.tokenizeHTMLString(line);
			printTokens(tokens);
		}
		input.close();
	}
	
	/**
	 *	Prints the line of tokens (in the form of a String array)
	 *	Also does the special actions for the HMTL tags
	 *	@param tokens	the array of tokens
	 */
	public void printTokens (String[] tokens)
	{
		for (int i = 0; i < tokens.length; i++)
		{
			String token = tokens[i];
			String lowerToken = token.toLowerCase();
			boolean freshQuotes = false;
			switch (lowerToken)
			{
				case "<html>": case "</html>": case "<body>": case "</body>":
					break;
				case "<p>": case "</p>":
					if (lineLength != 0) {
						browser.println();
						browser.println();
						lineLength = 0;
					}
					break;
				case "<q>":
					freshQuotes = true;
					print(" \"");
					break;
				case "</q>":
					print("\"");
					break;
				case "<b>":
					inB = true;
					break;
				case "</b>":
					inB = false;
					break;
				case "<i>":
					inI = true;
					break;
				case "</i>":
					inI = false;
					break;
				case "<br>":
					browser.printBreak();
					lineLength = 0;
					break;
				case "<hr>":
					browser.printHorizontalRule();
					lineLength = 0;
					break;
				case "</h1>": case "</h2>": case "</h3>": case "</h4>": 
							case "</h5>": case "</h6>": case "</pre>":
					formatOn = 0;
					break;
				case "<h1>":
					lineLength = 0;
					browser.println();
					formatOn = 1;
					break;
				case "<h2>":
					lineLength = 0;
					browser.println();
					formatOn = 2;
					break;
				case "<h3>":
					lineLength = 0;
					browser.println();
					formatOn = 3;
					break;
				case "<h4>":
					lineLength = 0;
					browser.println();
					formatOn = 4;
					break;
				case "<h5>":
					lineLength = 0;
					browser.println();
					formatOn = 5;
					break;
				case "<h6>":
					lineLength = 0;
					browser.println();
					formatOn = 6;
					break;
				case "<pre>":
					formatOn = 7;
					break;
				default:
					if (lineLength == 0 || token.length() == 0 || freshQuotes)
						freshQuotes = false;
					else if ((lineLength + token.length() + 1 > FORMAT_MAX_LENGTH[formatOn]) || 
							(lineLength + token.length() > 80) &&
								isPunctuation(token.charAt(0))) {
						browser.println();
						lineLength = 0;
					}
					else if (!isPunctuation(token.charAt(0)))
						print(" ");
					
					if (token.length() > 0 && !isPunctuation(token.charAt(0)) 
														&& lineLength != 0)
						lineLength++;
					print(token);
					lineLength += token.length();
					break;
			}
		}
	}
	
	/**
	 *	Prints individual words (tokens) in the correct type of format
	 *	@param word		the word (or token) that is going to be printed
	 */
	private void print(String word)
	{	
		if (word.length() >= 4 && word.substring(0, 4).equals("<!--"));
		else if (inB)
			browser.printBold(word);
		else if (inI)
			browser.printItalic(word);
		else if (formatOn == 1)
			browser.printHeading1(word);
		else if (formatOn == 2)
			browser.printHeading2(word);
		else if (formatOn == 3)
			browser.printHeading3(word);
		else if (formatOn == 4)
			browser.printHeading4(word);
		else if (formatOn == 5)
			browser.printHeading5(word);
		else if (formatOn == 6)
			browser.printHeading6(word);
		else if (formatOn == 7) {
			browser.printPreformattedText(word);
			browser.println();
		}
		else
			browser.print(word);
		
	}
	
	/**
	 *	Returns true if the inputted char is a type of valid punctuation, else false
	 * 	@return		if char c is punctuation
	 *	@param c	character to be checked to be punctuation
	 */
	private boolean isPunctuation(char c)
	{
		return c == '.' || c == ',' || c == ';' || c == ':' || c == '(' ||
				c == ')' || c == '?' || c == '!' || c == '=' || c == '&' ||
				c == '~' || c == '+' || c == '-';
	}
	
	/**
	 *	Sample code that was given already
	 *	Runs when no file is written in the args
	 */
	public void runSample() {
		// Sample renderings from HtmlPrinter class
		
		// Print plain text without line feed at end
		browser.print("First line");
		
		// Print line feed
		browser.println();
		
		// Print bold words and plain space without line feed at end
		browser.printBold("bold words");
		browser.print(" ");
		
		// Print italic words without line feed at end
		browser.printItalic("italic words");
		
		// Print horizontal rule across window (includes line feed before and after)
		browser.printHorizontalRule();
		
		// Print words, then line feed (printBreak)
		browser.print("A couple of words");
		browser.printBreak();
		browser.printBreak();
		
		// Print a double quote
		browser.print("\"");
		
		// Print Headings 1 through 6 (Largest to smallest)
		browser.printHeading1("Heading1");
		browser.printHeading2("Heading2");
		browser.printHeading3("Heading3");
		browser.printHeading4("Heading4");
		browser.printHeading5("Heading5");
		browser.printHeading6("Heading6");
		
		// Print pre-formatted text (optional)
		browser.printPreformattedText("Preformat Monospace\tfont");
		browser.printBreak();
		browser.print("The end");
		
	}
	
	
}
