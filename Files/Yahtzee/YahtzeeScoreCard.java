/**
 *  
 *
 *  @author Petros Mzikyan
 * 	@since 10/23/2024
*/
public class YahtzeeScoreCard {

	private int[] scorecard;
	private final int NUM_SIDES = 6;
	private final int NUM_DICE = 5;
	public YahtzeeScoreCard() {
		scorecard = new int[13];
		for (int i = 0; i < scorecard.length; i++)
			scorecard[i] = -1;
	}
	
	/**
	 *  Print the scorecard header
	 */
	public void printCardHeader() {
		System.out.println("\n");
		System.out.printf("\t\t\t\t\t       3of  4of  Fll Smll Lrg\n");
		System.out.printf("  NAME\t\t  1    2    3    4    5    6   Knd  Knd  Hse " +
						"Strt Strt Chnc Ytz!\n");
		System.out.printf("+----------------------------------------------------" +
						"---------------------------+\n");
	}
	
	/**
	 *  Prints the player's score
	 */
	public void printPlayerScore(YahtzeePlayer player) {
		System.out.printf("| %-12s |", player.getName());
		for (int i = 1; i < 14; i++) {
			if (getScore(i) > -1)
				System.out.printf(" %2d |", getScore(i));
			else System.out.printf("    |");
		}
		System.out.println();
		System.out.printf("+----------------------------------------------------" +
						"---------------------------+\n");
	}

	/**
	 * Returns the score of the inputted column
	 * @param column	the column of which the score will be returned
	 * @return			the score contained in that column
	 */
	public int getScore(int column) { return scorecard[column - 1]; }

	/**
	 * Returns the sum of all the scores in the array (the total score)
	 * @return	total score
	 */
	public int getTotalScore()
	{
		int out = 0;
		for (int i = 0; i < scorecard.length; i++)
			out += scorecard[i];
		return out;
	}

	/**
	 *  Change the scorecard based on the category choice 1-13.
	 *
	 *  @param choice The choice of the player 1 to 13
	 *  @param dg  The DiceGroup to score
	 *  @return  true if change succeeded. Returns false if choice already taken.
	 */
	public boolean changeScore(int choice, DiceGroup dg)
	{ 
		if (getScore(choice) != -1)
			return false;
		switch (choice) {
			case 1: case 2: case 3: case 4: case 5: case 6:
				numberScore(choice, dg);
				break;
			case 7:
				threeOfAKind(dg);
				break;
			case 8:
				fourOfAKind(dg);
				break;
			case 9:
				fullHouse(dg);
				break;
			case 10:
				smallStraight(dg);
				break;
			case 11:
				largeStraight(dg);
				break;
			case 12:
				chance(dg);
				break;
			case 13:
				yahtzeeScore(dg);
				
		}
		return true;
	}
	
	/**
	 *  Change the scorecard for a number score 1 to 6
	 *
	 *  @param choice The choice of the player 1 to 6
	 *  @param dg  The DiceGroup to score
	 */
	public void numberScore(int choice, DiceGroup dg) 
	{
		scorecard[choice - 1] = dg.getNumberCount(choice) * choice;
	}
	
	/**
	 *	Updates the scorecard for Three Of A Kind choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void threeOfAKind(DiceGroup dg)
	{
		for (int i = 0; i < NUM_SIDES; i++)
			if (dg.getNumberCount(i) >= 3)
				scorecard[6] = dg.getTotal();
		if (scorecard[6] == -1)
			scorecard[6] = 0;
	}

	/**
	 *	Updates the scorecard for Four Of A Kind choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */
	public void fourOfAKind(DiceGroup dg)
	{
		for (int i = 0; i < NUM_SIDES; i++)
			if (dg.getNumberCount(i+1) >= 4)
				scorecard[7] = dg.getTotal();
		if (scorecard[7] == -1)
			scorecard[7] = 0;
	}

	/**
	 *	Updates the scorecard for Full House choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */
	public void fullHouse(DiceGroup dg)
	{
		int correct = 0;
		for (int i = 0; i < NUM_SIDES && correct == 0; i++)
			if (dg.getNumberCount(i) == 3)
				correct++;
		for (int i = 0; i < NUM_SIDES && correct == 1; i++)
			if (dg.getNumberCount(i) == 2)
				correct++;
		if (correct == 2)
			scorecard[8] = 25;
		else
			scorecard[8] = 0;
	}

	/**
	 *	Updates the scorecard for Small Straight choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */
	public void smallStraight(DiceGroup dg)
	{
		for (int i = 1; i < NUM_DICE - 1; i++)
			if (dg.getDieValue(i) != dg.getDieValue(i - 1) + 1)
				scorecard[9] = -2;
		if (scorecard[9] == -1)
			scorecard[9] = 30;

		if (scorecard[9] == -2) {
			for (int i = 2; i < NUM_DICE; i++)
				if (dg.getDieValue(i) != dg.getDieValue(i - 1) + 1)
					scorecard[9] = 0;
			if (scorecard[9] == -2)
				scorecard[9] = 30;
		}
	}

	/**
	 *	Updates the scorecard for Large Straight choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */
	public void largeStraight(DiceGroup dg)
	{
		for (int i = 1; i < NUM_DICE; i++)
			if (dg.getDieValue(i) != dg.getDieValue(i - 1) + 1)
				scorecard[10] = 0;
		if (scorecard[10] == -1)
			scorecard[10] = 40;
	}

	/**
	 *	Updates the scorecard for Chance choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */
	public void chance(DiceGroup dg)
	{
		scorecard[11] = dg.getTotal();
	}

	/**
	 *	Updates the scorecard for Yahtzee choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */
	public void yahtzeeScore(DiceGroup dg)
	{
		for (int i = 0; i < NUM_SIDES; i++)
			if (dg.getNumberCount(i+1) == 5)
				scorecard[12] = 50;
		if (scorecard[12] == -1)
			scorecard[12] = 0;
	}
}
