import java.util.ArrayList;
import java.util.List;

/**
 *	AnagramMaker - <description goes here>
 *
 *	Requires the WordUtilities, SortMethods, Prompt, and FileUtils classes
 *
 *	@author	Petros Mzikyan
 *	@since	1/15/2025
 */
public class AnagramMaker {
								
	private final String FILE_NAME = "randomWords.txt";	// file containing all words
	
	private WordUtilities wu;	// the word utilities for building the word
								// database, sorting the database,
								// and finding all words that match
								// a string of characters
	
	// variables for constraining the print output of AnagramMaker
	private int numWords;		// the number of words in a phrase to print
	private int maxPhrases;		// the maximum number of phrases to print
	private int numPhrases;		// the number of phrases that have been printed
	
	private ArrayList<String> anagram;	// list of words that are the anagram
			
	/*	Initialize the database inside WordUtilities
	 *	The database of words does NOT have to be sorted for AnagramMaker to work,
	 *	but the output will appear in order if you DO sort.
	 */
	public AnagramMaker() {
		wu = new WordUtilities();
		wu.readWordsFromFile(FILE_NAME);
		wu.sortWords();
	}
	
	public static void main(String[] args) {
		AnagramMaker am = new AnagramMaker();
		am.run();
	}
	
	/**	The top routine that prints the introduction and runs the anagram-maker.
	 */
	public void run() {
		printIntroduction();
		runAnagramMaker();
		System.out.println("\nThanks for using AnagramMaker!\n");
	}
	
	/**
	 *	Print the introduction to AnagramMaker
	 */
	public void printIntroduction() {
		System.out.println("\nWelcome to ANAGRAM MAKER");
		System.out.println("\nProvide a word, name, or phrase and out comes their anagrams.");
		System.out.println("You can choose the number of words in the anagram.");
		System.out.println("You can choose the number of anagrams shown.");
		System.out.println("\nLet's get started!");
	}
	
	/**
	 *	Prompt the user for a phrase of characters, then create anagrams from those
	 *	characters.
	 */
	public void runAnagramMaker() {
		Prompt ask = new Prompt();
		String input = removeNonAlphabeticCharacters(ask.getString(
				"Word(s), name or phrase (q to quit)").toLowerCase());
		while (!input.equals("q")) {
			numWords = ask.getInt("Number of words in anagram");
			maxPhrases = ask.getInt("Maximum number of anagrams to print");
			
			Str
			
			
			input = removeNonAlphabeticCharacters(ask.getString(
				"Word(s), name or phrase (q to quit)").toLowerCase());
		}
	}
	
	public void anagramFinder(String letters) {
		if (letters.length() == 0) {
			
			return;
		}
	}
	
	/**
	 *	Returns the inputted String, but without characters that aren't letters
	 *	@param	original	the original String
	 *	@return 			the original String but only letters
	 */
	public String removeNonAlphabeticCharacters(String original) {
		String out = "";
		for (int i = 0; i < original.length(); i++)
			if (Character.isLetter(original.charAt(i)))
				out += original.charAt(i);
		return out;
	}
}
