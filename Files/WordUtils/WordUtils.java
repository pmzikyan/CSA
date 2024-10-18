import java.util.Scanner;

/**
 *	Provides utilities for word games:
 *	1. finds all words in the dictionary that match a list of letters
 *	2. prints an array of words to the screen in tabular format
 *	3. finds the word from an array of words with the highest score
 *	4. calculates the score of a word according to a table
 *
 *	Uses the FileUtils and Prompt classes.
 *	
 *	@author Petros Mzikyan
 *	@since	10/18/2024
 */
 
public class WordUtils
{
	private String[] words;		// the dictionary of words
	
	// File containing dictionary of almost 100,000 words.
	private final String WORD_FILE = "wordList.txt";
	
	/* Constructor */
	public WordUtils() { }
	
	/**	Load all of the dictionary from a file into words array. */
	private void loadWords () 
	{
		Scanner dictionary = FileUtils.openToRead(WORD_FILE);
		
		int length = 0;
		while (dictionary.hasNext()) {
			length++;
			dictionary.next();
		}
		words = new String[length];
		dictionary.close();
		dictionary = FileUtils.openToRead(WORD_FILE);
		
		for (int i = 0; i < length; i++)
			words[i] = dictionary.next();
		dictionary.close();
	}
	
	/**	Find all words that can be formed by a list of letters.
	 *  @param letters	string containing list of letters
	 *  @return			array of strings with all words found.
	 */
	public String [] findAllWords (String letters)
	{
		String[] tempArr = new String[1234]
		int wordAmount = 0;
		
		for (int a = 0; a < words.length; a++)
		{
			String tempLetters = letters;
			for (int b = 0; b < words[a].length(); b++)
			{
				for (int c = 0; c < letters.length(); c++)
				{
					
				}
			}
		}
		
		return new out;
	}
	
	/**	Print the words found to the screen.
	 *  @param words	array containing the words to be printed
	 */
	public void printWords (String [] wordList) 
	{
		for (int i = 0; i < wordList.length; i++)
		{
			printf("%-12s", wordList[i]);
			if (i%5)
				System.out.println();
		}
	}
	
	/**	Finds the highest scoring word according to a score table.
	 *
	 *  @param word  		An array of words to check
	 *  @param scoreTable	An array of 26 integer scores in letter order
	 *  @return   			The word with the highest score
	 */
	public String bestWord (String [] wordList, int [] scoreTable)
	{
		return "";
	}
	
	/**	Calculates the score of one word according to a score table.
	 *
	 *  @param word			The word to score
	 *  @param scoreTable	An array of 26 integer scores in letter order
	 *  @return				The integer score of the word
	 */
	public int getScore (String word, int [] scoreTable)
	{
		return 0;
	}
	
	/***************************************************************/
	/************************** Testing ****************************/
	/***************************************************************/
	public static void main (String [] args)
	{
		WordUtils wu = new WordUtils();
		wu.run();
	}
	
	public void run() {
		String letters = Prompt.getString("Please enter a list of letters, from 3 to 12 letters long, without spaces");
		loadWords();
		String [] word = findAllWords(letters);
		System.out.println();
		printWords(word);
		
		// Score table in alphabetic order according to Scrabble
		int [] scoreTable = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
		String best = bestWord(word,scoreTable);
		System.out.println("\nHighest scoring word: " + best + "\nScore = " 
							+ getScore(best, scoreTable) + "\n");
	}
}
