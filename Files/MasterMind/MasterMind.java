/**
 *	Plays the game of MasterMind.
 *	A "master" (or a string of 4 letters - can be A, B, C, D, E, or F)
 * 	is randomized and the user has 10 turns to guess it. A table is printed
 * 	that shows the partial and exact matches for each guess.
 *  
 *	@author	Petros Mzikyan
 *	@since	9/27/2024
 */

public class MasterMind {

	private boolean reveal;			// true = reveal the master combination
	private PegArray[] guesses;		// the array of guessed peg arrays
	private PegArray master;		// the master (key) peg array
	private Prompt ask;
	
	// Constants
	private final int PEGS_IN_CODE = 4;		// Number of pegs in code
	private final int MAX_GUESSES = 10;		// Max number of guesses
	private final int PEG_LETTERS = 6;		// Number of different letters on pegs
											// 6 = A through F
	
	private int guessOn;
	
	public static void main(String[] args)
	{
		MasterMind masterMind = new MasterMind();
		masterMind.run();
	}
	
	/**
	 *	Initiates the field variables to get ready for the game to run
	 */
	private void initiateFields()
	{
		reveal = false;
		guesses = new PegArray[MAX_GUESSES];
		for (int i = 0; i < guesses.length; i++)
		{
			guesses[i] = new PegArray(PEGS_IN_CODE);
		}
		master = new PegArray(PEGS_IN_CODE);
		for (int i = 0; i < PEGS_IN_CODE; i++)
		{
			master.getPeg(i).setLetter((char)
					((int)(Math.random() * PEG_LETTERS) + 'A'));
		}
		
		ask = new Prompt();
		guessOn = 0;
	}
	
	/**
	 *	Runs the game of MasterMind
	 */
	public void run()
	{
		initiateFields();
		printIntroduction();
		
		int exacts = 0;
		ask.getString("Hit the Enter key to start the game");
		do
		{
			printBoard();
			String guess = returnGuess();
			setPegArray(guess);
			
			guesses[guessOn - 1].getPartialMatches(master);
			exacts = guesses[guessOn - 1].getExactMatches(master);
		}
		while (guessOn < MAX_GUESSES && exacts < PEGS_IN_CODE);
		
		reveal = true;
		printBoard();
		if (guessOn < MAX_GUESSES)
		{
			System.out.println("\nNice work! You found the master code in "
								+ guessOn + " guesses.\n");
		}
		else
		{
			System.out.println("Oops. You were unable to " + 
								"find the solution in 10 guesses.");
		}
		
	}
	
	/**
	 * 	Asks the user for a guess and returns the guess as long as it's valid
	 * 
	 * 	@return 	a valid guess
	 */
	public String returnGuess()
	{
		guessOn++;
		System.out.println("\nGuess " + guessOn + "\n");
		boolean valid;
		String out;
		
		do
		{
			valid = true;
			out = ask.getString("Enter the code using (A,B,C,D,E,F). " + 
			"For example, ABCD or abcd from left-to-right").toUpperCase();
			if (out.length() != PEGS_IN_CODE)
				valid = false;
			for (int i = 0; i > out.length() && valid; i++)
			{
				if (!('A' <= out.charAt(i) && out.charAt(i) <= 'F'))
					valid = false;
			}
			if (!valid)
				System.out.println("ERROR: Bad input, try again.");
		}
		while (!valid);
		
		return out;
	}
	
	/**
	 * 	Saves the current guess to the PegArray Peg objects
	 * 
	 * 	@param	guess	the current guess
	 */
	public void setPegArray(String guess)
	{
		for (int i = 0; i < PEGS_IN_CODE; i++)
		{
			guesses[guessOn - 1].getPeg(i).setLetter(guess.charAt(i));
		}
	}
	
	/**
	 *	Print the introduction screen
	 */
	public void printIntroduction() {
		System.out.println("\n");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("| ___  ___             _              ___  ___ _             _                       |");
		System.out.println("| |  \\/  |            | |             |  \\/  |(_)           | |                      |");
		System.out.println("| | .  . |  __ _  ___ | |_  ___  _ __ | .  . | _  _ __    __| |                      |");
		System.out.println("| | |\\/| | / _` |/ __|| __|/ _ \\| '__|| |\\/| || || '_ \\  / _` |                      |");
		System.out.println("| | |  | || (_| |\\__ \\| |_|  __/| |   | |  | || || | | || (_| |                      |");
		System.out.println("| \\_|  |_/ \\__,_||___/ \\__|\\___||_|   \\_|  |_/|_||_| |_| \\__,_|                      |");
		System.out.println("|                                                                                    |");
		System.out.println("| WELCOME TO MONTA VISTA MASTERMIND!                                                 |");
		System.out.println("|                                                                                    |");
		System.out.println("| The game of MasterMind is played on a four-peg gameboard, and six peg letters can  |");
		System.out.println("| be used.  First, the computer will choose a random combination of four pegs, using |");
		System.out.println("| some of the six letters (A, B, C, D, E, and F).  Repeats are allowed, so there are |");
		System.out.println("| 6 * 6 * 6 * 6 = 1296 possible combinations.  This \"master code\" is then hidden     |");
		System.out.println("| from the player, and the player starts making guesses at the master code.  The     |");
		System.out.println("| player has 10 turns to guess the code.  Each time the player makes a guess for     |");
		System.out.println("| the 4-peg code, the number of exact matches and partial matches are then reported  |");
		System.out.println("| back to the user. If the player finds the exact code, the game ends with a win.    |");
		System.out.println("| If the player does not find the master code after 10 guesses, the game ends with   |");
		System.out.println("| a loss.                                                                            |");
		System.out.println("|                                                                                    |");
		System.out.println("| LET'S PLAY SOME MASTERMIND!                                                        |");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("\n");
	}
	
	/**
	 *	Print the peg board to the screen
	 */
	public void printBoard() {
		// Print header
		System.out.print("+--------+");
		for (int a = 0; a < PEGS_IN_CODE; a++) System.out.print("-------+");
		System.out.println("---------------+");
		System.out.print("| MASTER |");
		for (int a = 0; a < PEGS_IN_CODE; a++)
			if (reveal)
				System.out.printf("   %c   |", master.getPeg(a).getLetter());
			else
				System.out.print("  ***  |");
		System.out.println(" Exact Partial |");
		System.out.print("|        +");
		for (int a = 0; a < PEGS_IN_CODE; a++) System.out.print("-------+");
		System.out.println("               |");
		// Print Guesses
		System.out.print("| GUESS  +");
		for (int a = 0; a < PEGS_IN_CODE; a++) System.out.print("-------+");
		System.out.println("---------------|");
		for (int g = 0; g < MAX_GUESSES - 1; g++) {
			printGuess(g);
			System.out.println("|        +-------+-------+-------+-------+---------------|");
		}
		printGuess(MAX_GUESSES - 1);
		// print bottom
		System.out.print("+--------+");
		for (int a = 0; a < PEGS_IN_CODE; a++) System.out.print("-------+");
		System.out.println("---------------+");
	}
	
	/**
	 *	Print one guess line to screen
	 *	@param t	the guess turn
	 */
	public void printGuess(int t) {
		System.out.printf("|   %2d   |", (t + 1));
		// If peg letter in the A to F range
		char c = guesses[t].getPeg(0).getLetter();
		if (c >= 'A' && c <= 'F')
			for (int p = 0; p < PEGS_IN_CODE; p++)
				System.out.print("   " + guesses[t].getPeg(p).getLetter() + "   |");
		// If peg letters are not A to F range
		else
			for (int p = 0; p < PEGS_IN_CODE; p++)
				System.out.print("       |");
		System.out.printf("   %d      %d    |\n",
							guesses[t].getExact(), guesses[t].getPartial());
	}

}
