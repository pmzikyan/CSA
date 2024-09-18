/**
 *	The game of Pig.
 *	(Description here)
 *
 *	@author	Petros Mzikyan
 *	@since 9/13/2024
 */
public class PigGame {
	static Prompt ask;
	
	/** Method that runs the game itself (when user chooses 'p') */
	public void playGame()
	{
		Dice dice = new Dice();
		int pScore, cScore, turnScore;
		pScore = cScore = turnScore = 0;
		boolean cTurn = false;
		char c = ' '; // Temporary char
		
		while (pScore < 100 || cScore < 100)
		{
			if (!cTurn)
			{ // Runs player's turn
				System.out.println("**** USER Turn ***\n");
				System.out.println("Your turn score:\t" + turnScore);
				System.out.println("Your total score:\t" + pScore);
				
				do
					c = ask.getChar("\n(r)oll or (h)old");
				while (!(c == 'r' || c == 'h'));
				
				if (c == 'r')
				{
					dice.roll();
					turnScore += dice.getValue();
				}
				else
				{
					pScore += turnScore;
					turnScore = 0;
				}
				
				cTurn = false;
			}
			else
			{ // Runs computer's turn
				System.out.println("**** COMPUTER's Turn ***\n");
				
				
				cTurn = true;
			}
		}
	}
	
	/** Method that shows statistics (when user chooses 's') */
	public void statistics()
	{
		
	}
	
	/**	Print the introduction to the game */
	public void printIntroduction() {
		System.out.println("\n");
		System.out.println("______ _         _____");
		System.out.println("| ___ (_)       |  __ \\");
		System.out.println("| |_/ /_  __ _  | |  \\/ __ _ _ __ ___   ___");
		System.out.println("|  __/| |/ _` | | | __ / _` | '_ ` _ \\ / _ \\");
		System.out.println("| |   | | (_| | | |_\\ \\ (_| | | | | | |  __/");
		System.out.println("\\_|   |_|\\__, |  \\____/\\__,_|_| |_| |_|\\___|");
		System.out.println("          __/ |");
		System.out.println("         |___/");
		System.out.println("\nThe Pig Game is human vs computer. Each takes a"
							+ " turn rolling a die and the first to score");
		System.out.println("100 points wins. A player can either ROLL or "
							+ "HOLD. A turn works this way:");
		System.out.println("\n\tROLL:\t2 through 6: add points to turn total, "
							+ "player's turn continues");
		System.out.println("\t\t1: player loses turn");
		System.out.println("\tHOLD:\tturn total is added to player's score, "
							+ "turn goes to other player");
		System.out.println("\n");
	}
	
	/** Main method that calls the methods to play the game */
	public static void main(String[] args)
	{
		PigGame pg = new PigGame();
		ask = new Prompt();
		pg.printIntroduction();
		
		// c is a temporary char
		char c = ' ';
		while (!(c == 'p' || c == 's'))
			c = ask.getChar("Play game or Statistics (p or s)");
		
		if (c == 'p')
			pg.playGame();
		else
			pg.statistics();
		
	}
}
