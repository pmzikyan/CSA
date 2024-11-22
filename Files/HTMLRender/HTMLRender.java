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
	
	private boolean inHtml, inBody, inP, inQ, inB, inI;
		
	public HTMLRender() {
		// Initialize token array
		tokens = new String[TOKENS_SIZE];
		
		// Initialize Simple Browser
		render = new SimpleHtmlRenderer();
		browser = render.getHtmlPrinter();
	}
	
	
	public static void main(String[] args) {
		HTMLRender hf = new HTMLRender();
		if (args.length == 0)
			hf.runSample();
		else
			hf.run(args[0]);
	}
	
	public void run(String fileName) {
		FileUtils fileUtils = new FileUtils();
		HTMLUtilities htmlUtils= new HTMLUtilities();
		
		inHtml = inBody = inP = inQ = inB = inI = false;
		
		// Open the HTML file
		Scanner input = FileUtils.openToRead(fileName);
		
		// Read each line of the HTML file, tokenize, then print tokens
		while (input.hasNext()) {
			String line = input.nextLine();
			System.out.println("\n" + line);
			String [] tokens = htmlUtils.tokenizeHTMLString(line);
			printTokens(tokens);
		}
		input.close();
	}
	
	public void printTokens (String[] tokens)
	{
		for (int i = 0; i < tokens.length; i++)
		{
			switch (tokens[i])
			{
				case "<html>":
					inHtml = true;
					break;
				case "</html>":
					inHtml = false;
					break;
				case "<body>":
					inBody = true;
					break;
				case "</body>":
					inBody = false;
					break;
				case "<p>":
					inP = true;
					break;
				case "</p>":
					inP = false;
					break;
				case "<q>":
					inQ = true;
					break;
				case "</q>":
					inQ = false;
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
			}
		}
	}
	
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
