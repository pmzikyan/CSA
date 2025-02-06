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
	private int phraseLength;	// the length of the phrase
	
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
		String phrase = ask.getString("Word(s), name or phrase (q to quit)");
		while (!phrase.equals("q")) {
			numWords = ask.getInt("Number of words in anagram");
			maxPhrases = ask.getInt("Maximum number of anagrams to print");
			System.out.println();

			List<String> anagram = new ArrayList<String>();
			phrase = removeNonAlphabeticCharacters(phrase);
			phraseLength = phrase.length();

			numPhrases = 0;
			assembleAnagram(anagram, phrase);

			if (numPhrases < maxPhrases)
				System.out.println("\nEnded at " + numPhrases + " anagrams");
			else
				System.out.println("\nStopped at " + numPhrases + " anagrams");

			phrase = ask.getString("\nWord(s), name or phrase (q to quit)");
		}
	}

	/**
	 * 	Recursive method that builds an anagram - one word per instance
	 * 	The base case is when the phrase runs out of letters
	 *	@param anagram	the list of used words in the anagram
	 *	@param phrase	(originally the input of the user) the unused letter
	 */
	public void assembleAnagram(List<String> anagram, String phrase) {
		int anagramLength = 0;
		for (int i = 0; i < anagram.size(); i++)
			anagramLength += anagram.get(i).length();
		if (anagramLength > phraseLength || anagram.size() > numWords)
			return;
		if (phrase.length() == 0)
		{
			//System.out.println("finish = " + anagram + " and " + phrase);
			if (anagram.size() == numWords && anagramLength == phraseLength) {
				for (int i = 0; i < anagram.size(); i++)
					System.out.print(anagram.get(i) + " ");
				System.out.println();
				numPhrases++;
			}
		}
		else
		{
			List<String> allWords = wu.allWords(phrase);
			for (int i = 0; i < allWords.size(); i++) {
				anagram.add(allWords.get(i));
				String newPhrase = removeLetters(phrase, allWords.get(i));

				/*System.out.print(allWords);
				System.out.println(anagram);
				System.out.println(phrase + " -> " + newPhrase);*/
				if (numPhrases < maxPhrases)
					assembleAnagram(anagram, newPhrase);
				else
					return;

				anagram.remove(anagram.size() - 1);
			}
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

	/**
<<<<<<< Updated upstream
<<<<<<< Updated upstream
	 *	Returns word with the the letters removed
	 *	@param	word	the word/phrase to be changed
	 * 	@param	letters	the letters to remove from word
	 *	@return 		word without the letters from the String letters
=======
=======
>>>>>>> Stashed changes
	 *	Returns word with the letters of the variable letters removed
	 *	@param	word	the original word
	 *	@param	letters	the letters to be removed
	 *	@return 		word with the letters in the String called letters removed
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
	 */
	public String removeLetters(String word, String letters) {
		String out = "";
		for (int a = 0; a < word.length(); a++) {

			boolean letterRemoved = false;
			for (int b = 0; b < letters.length() && !letterRemoved; b++)
				if (word.charAt(a) == letters.charAt(b))
					letterRemoved = true;
			if (!letterRemoved)
				out += word.charAt(a);
		}
		return out;
	}
}
