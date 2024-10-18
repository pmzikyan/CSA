import java.util.Scanner;

/**
 *  This program prompts for a string of letters, then searches the
 *  wordList.txt database to see which words it matches.
 *
 *  Requires classes FileUtils and Prompt.
 *
 *  @author Petros Mzikyan
 *  @since	10/18/2024
 *
 *	Algorithm
 *
 *	Input string of letters
 *	Open wordList.txt and read each word
 *		test word with letters
 *		if they match, add word to list
 *	Print list to screen
 */

public class EveryWord {
	private final String INFILE_NAME = "wordList.txt";
	private String [][] words;	// words that match letters, 1st dimension
								// is number of letters in the word
	private int [] numWords;	// the number of words found
	
	private final int MAX_LETTERS = 15;	// maximum letters in word to store
	
	public EveryWord() {
		// row = num of letters in words
		words = new String [MAX_LETTERS][1000];
		numWords = new int [MAX_LETTERS];
		clearArrays();
	}
	
	public static void main(String[] args) {
		EveryWord ew = new EveryWord();
		ew.run();
	}
	
	/**	Algorithm:
	 *	Input string of letters
	 *	Find all words that match letters
	 *	Print all the words that match
	 */
	public void run() {
		boolean done = false;
		do {
			String letters = Prompt.getString("List of letters for search (q quit)");
			if (letters.equalsIgnoreCase("q"))
				done = true;
			else
				if (letters.length() >= 3 && letters.length() < MAX_LETTERS) {
					findWords(letters);
					printWords();
					clearArrays();
				}
		} while (! done);
		System.out.println();
	}
	
	/**
	 *  Find all the words that match a string of letters.
	 *	Open wordList.txt and read each word
	 *		for each word, test with letters
	 *		if they match, add word to words array
	 *	@param letters		the letters to match
	 */
	public void findWords(String letters) {
		Scanner input = FileUtils.openToRead(INFILE_NAME);
		while (input.hasNext()) {
			String word = input.next();
			if (isWordMatch(word, letters)) {
				words[word.length()][numWords[word.length()]] = word;
				numWords[word.length()]++ ;
			}
		}
		input.close();
	}
	
	/**
	 *  Decides if a word matches a group of letters.
	 *
	 *  @param word  The word to test.
	 *  @param letters  A string of letters to compare
	 *  @return  true if the word matches the letters, false otherwise
	 */
	public boolean isWordMatch (String word, String letters) {
		for (int a = 0; a < word.length(); a++) {
			char c = word.charAt(a);
			if (letters.indexOf(c) > -1)
				letters = letters.substring(0, letters.indexOf(c))
							+ letters.substring(letters.indexOf(c) + 1);
			else
				return false;
		}
		return true;
	}
	
	/**
	 *  Print the list of words that match to the screen.
	 */
	public void printWords() {
		System.out.println();
		for (int row = 0; row < numWords.length; row++) {
			for (int col = 0; col < numWords[row]; col++) {
				System.out.printf("%-8s", words[row][col]);
				if ((col + 1) % 10 == 0) System.out.println();
			}
			if (numWords[row] > 0) System.out.println("\n");
		}
		System.out.println();
	}
	
	/**
	 *  Set the numWords array to zeros
	 */
	public void clearArrays() {
		for (int i = 0; i < MAX_LETTERS; i++) numWords[i] = 0;
	}
}
