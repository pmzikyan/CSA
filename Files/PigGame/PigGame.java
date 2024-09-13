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
		int pScore, cScore;
		boolean cTurn = false;
		pScore = cScore = 0;
		
		while (pScore < 100 || cScore < 100)
		{
			if (false)
			{
				
				cTurn = false;
			}
			else
			{
				
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
		
		ask.getChar("Play game or Statistics (p or s)");
	}
}
