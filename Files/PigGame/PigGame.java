/**
 *	The game of Pig.
 *	The user and computer take turns rolling a six sided die, adding the 
 * 	result to a temporary number. The user can hold their points and add
 *  it to their total at any time because if they roll a 1 they lose all the
 *  temporary points that round. Same applies for the computer but it only holds
 *  if the temporary score is at least 20.
 *
 *	@author	Petros Mzikyan
 *	@since 9/13/2024
 */
public class PigGame {
	private static Prompt ask;
	private final int COMPUTER_THRESHOLD = 20;
	private final int WINNING_SCORE = 100;
	private final int LOSING_NUMBER = 1;

	/**
	 * Method that runs the game itself (when user chooses 'p')
	 */
	public void playGame()
	{
		Dice dice = new Dice();
		int playerScore, computerScore, turnScore;
		playerScore = computerScore = turnScore = 0;
		boolean computerTurn = false;
		char c = ' '; // Temporary char
		
		while (playerScore < WINNING_SCORE && computerScore < WINNING_SCORE)
		{
			turnScore = 0;
			if (!computerTurn)
			{ // Runs player's turn
				while (!computerTurn)
				{
				
					do
					{
						System.out.println("\nYour turn score:\t" + turnScore);
						System.out.println("Your total score:\t" + playerScore);
						c = ask.getChar("(r)oll or (h)old");
					}
					while (!(c == 'r' || c == 'h'));
					
					if (c == 'r')
					{
						dice.roll();
						System.out.println("\nYou ROLL\n");
						dice.printDice();
						if (dice.getValue() != LOSING_NUMBER)
							turnScore += dice.getValue();
						else
						{
							System.out.println("\nYou LOSE your turn.");
							computerTurn = true;
						}
					}
					else
					{
						playerScore += turnScore;
						turnScore = 0;
						computerTurn = true;
						
						System.out.println("\nYou HOLD");
					}
				}
				System.out.println("Your total score: " + playerScore + "\n");
			}
			else
			{ // Runs computer's turn
				while (computerTurn)
				{
					System.out.println("Computer turn score:\t" + turnScore);
					System.out.println("Computer total score:\t" + computerScore);
					
					ask.getString("Press enter for computer turn");
					
					if (turnScore < COMPUTER_THRESHOLD)
					{
						dice.roll();
						System.out.println("\nComputer will ROLL\n");
						dice.printDice();
						if (dice.getValue() != LOSING_NUMBER)
							turnScore += dice.getValue();
						else
						{
							System.out.println("\nComputer loses turn");
							computerTurn = false;
						}
					}
					else
					{
						computerScore += turnScore;
						turnScore = 0;
						computerTurn = false;
						
						System.out.println("\nComputer will HOLD");
					}
				}
				System.out.println("Computer total score: " + computerScore);
			}
		}
		
		if (playerScore > computerScore)
		{
			System.out.println("Congratulations!!! YOU WON!!!");
		}
		else
		{
			System.out.println("\nToo bad. COMPUTER WON.");
		}
		System.out.println("\nThanks for playing the Pig Game!!!\n");
	}
	
	/**
	 * Method that shows statistics (when user chooses 's')
	 */
	public void showStatistics()
	{

		System.out.println("Run statistical analysis - \"Hold at 20\"\n");

		int turns = ask.getInt("Number of turns", 1000, 10000000);

		int count0, count20, count21, count22, count23, count24, count25;
		count0 = count20 = count21 = count22 = count23 = count24 = count25 = 0;
		for (int i = 0; i < turns; i++)
		{
			switch (simulateComputerTurn())
			{
				case 0:
					count0++;
					break;
				case 20:
					count20++;
					break;
				case 21:
					count21++;
					break;
				case 22:
					count22++;
					break;
				case 23:
					count23++;
					break;
				case 24:
					count24++;
					break;
				case 25:
					count25++;
					break;
				default:
					System.err.println("ERROR: Simulated value of computer's score at the end of turn does not match any of the cases.");
			}
		}
		System.out.println("\nScore\tEstimated Probability");
		System.out.printf("0\t%.6f\n", count0*1.0/turns);
		System.out.printf("20\t%.6f\n", count20*1.0/turns);
		System.out.printf("21\t%.6f\n", count21*1.0/turns);
		System.out.printf("22\t%.6f\n", count22*1.0/turns);
		System.out.printf("23\t%.6f\n", count23*1.0/turns);
		System.out.printf("24\t%.6f\n", count24*1.0/turns);
		System.out.printf("25\t%.6f\n\n", count25*1.0/turns);
	}
	
	/**
	 * Print the introduction to the game
	 */
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
			pg.showStatistics();
		
	}
	
	/**
	 * Method used for statistics to return score after a single turn
	 *
	 * @return The score after the computer rolling enough times
	 */
	public int simulateComputerTurn()
	{
		Dice dice = new Dice();
		int score = 0;
		boolean rolledAOne = false;
		
		while (score < COMPUTER_THRESHOLD && !rolledAOne)
		{
			dice.roll();
			if (dice.getValue() != LOSING_NUMBER)
				score += dice.getValue();
			else
			{
				rolledAOne = true;
				score = 0;
			}
		}
		return score;
	}
}
