/**
 *	The game of Pig.
 *	The user and computer take turns rolling a six sided die, adding the 
 * 	result to a temporary number. The user can hold their points and add
 *  it to their total at any time because 
 *
 *	@author	Petros Mzikyan
 *	@since 9/13/2024
 */
public class PigGame {
	private static Prompt ask;
	
	/** Method that runs the game itself (when user chooses 'p') */
	public void playGame()
	{
		//p stands for player and c stands for computer in the following variables
		Dice dice = new Dice();
		int pScore, cScore, turnScore;
		pScore = cScore = turnScore = 0;
		boolean cTurn = false; 
		char c = ' '; // Temporary char (doesn't relate to computer)
		
		while (pScore < 100 && cScore < 100)
		{
			turnScore = 0;
			if (!cTurn)
			{ // Runs player's turn
				while (!cTurn)
				{
				
					do
					{
						System.out.println("\nYour turn score:\t" + turnScore);
						System.out.println("Your total score:\t" + pScore);
						c = ask.getChar("(r)oll or (h)old");
					}
					while (!(c == 'r' || c == 'h'));
					
					if (c == 'r')
					{
						dice.roll();
						System.out.println("\nYou ROLL\n");
						dice.printDice();
						if (dice.getValue() != 1)
							turnScore += dice.getValue();
						else
						{
							System.out.println("\nYou LOSE your turn.");
							cTurn = true;
						}
					}
					else
					{
						pScore += turnScore;
						turnScore = 0;
						cTurn = true;
						
						System.out.println("\nYou HOLD");
					}
				}
				System.out.println("Your total score: " + pScore + "\n");
			}
			else
			{ // Runs computer's turn
				while (cTurn)
				{
					System.out.println("Computer turn score:\t" + turnScore);
					System.out.println("Computer total score:\t" + cScore);
					
					ask.getString("Press enter for computer turn");
					
					if (turnScore < 20)
					{
						dice.roll();
						System.out.println("\nComputer will ROLL\n");
						dice.printDice();
						if (dice.getValue() != 1)
							turnScore += dice.getValue();
						else
						{
							System.out.println("\nComputer loses turn");
							cTurn = false;
						}
					}
					else
					{
						cScore += turnScore;
						turnScore = 0;
						cTurn = false;
						
						System.out.println("\nComputer will HOLD");
					}
				}
				System.out.println("Computer total score: " + cScore);
			}
		}
		
		if (pScore > cScore)
		{
			System.out.println("Congratulations!!! YOU WON!!!");
		}
		else
		{
			System.out.println("\nToo bad. COMPUTER WON.");
		}
		System.out.println("\nThanks for playing the Pig Game!!!\n");
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
		System.out.println();
		
		if (c == 'p')
			pg.playGame();
		else
			pg.statistics();
		
	}
	
	/** Method used for statistics to return score after a single turn */
	public int simulateComputerTurn()
	{
		Dice dice = new Dice();
		int score = 0;
		boolean rolledAOne = false
		
		while (score < 20 && !rolledAOne)
		{
			dice.roll();
			if (dice.getValue() != 1)
				score += dice.getValue();
			else
			{
				rolledAOne = true;
				score = 0;
			}
		}
	}
}
