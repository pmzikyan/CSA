/**
 *  
 *
 *  @author Petros Mzikyan
 * 	@since 10/23/2024
*/
public class DiceGroup {
	
	private Dice [] die;	// the array of dice
	
	private final int NUM_DICE = 5;	// number of dice
	
	// Create the seven different line images of a die
	private String [] line = {	" _______ ",
								"|       |",
								"| O   O |",
								"|   O   |",
								"|     O |",
								"| O     |",
								"|_______|" };

	/**
	 * Sets up the die array
	 */
	public DiceGroup() { 
		die = new Dice[NUM_DICE];
		
		for (int i = 0; i < NUM_DICE; i++)
			die[i] = new Dice();
		
	}

	/**
	 * Rolls all the dice in the die array
	 */
	public void rollDice() { 
		for (int i = 0; i < NUM_DICE; i++)
			die[i].roll();
	}
	
	/**	Hold the dice in the rawHold and roll the rest.
	 *	For example: If rawHold is "421", then hold die 1, 2, and 4, and
	 *	roll 3 and 5.
	 *	@param rawHold		the string of dice to hold
	 */
	public void rollDice(String rawHold) 
	{ 
		boolean[] rerollDice = {true, true, true, true, true};
		for (int i = 0; i < NUM_DICE; i++)
			for (int j = 0; j < rawHold.length(); j++)
				if ((rawHold.charAt(j)+"").equals(i + 1 + ""))
					rerollDice[i] = false;
		
		for (int i = 0; i < rerollDice.length; i++)
			if (rerollDice[i])
				die[i].roll();
				
	}
	
	/**	getters - you complete */

	/**
	 * Returns the value of the number die inputted
	 * @param num	which die's value will be returned
	 * @return		the value of the num value die
	 */
	public int getDieValue(int num) { return die[num].getValue(); }

	/**
	 * Returns how many times the inputted number appears in the dice group.
	 * @param number	which number to check
	 * @return			the amount of times number appears in the dice
	 */
	public int getNumberCount(int number)
	{
		int out = 0;
		for (int i = 0; i < NUM_DICE; i++)
			if (getDieValue(i) == number)
				out++;
		
		return out;
	}
	
	/**	@return the total value of the DiceGroup - you complete */
	public int getTotal() {
		int out = 0;
		for (int i = 0; i < NUM_DICE; i++)
			out += die[i].getValue();
		return out;
	}
	
	/**
	 *  Prints out the images of the dice
	 */
	public void printDice() {
		printDiceHeaders();
		for (int i = 0; i < 6; i++) {
			System.out.print(" ");
			for (int j = 0; j < die.length; j++) {
				printDiceLine(die[j].getValue() + 6 * i);
				System.out.print("     ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/**
	 *  Prints the first line of the dice.
	 */
	private void printDiceHeaders() {
		System.out.println();
		for (int i = 1; i < 6; i++) {
			System.out.printf("   # %d        ", i);
		}
		System.out.println();
	}
	
	/**
	 *  Prints one line of the ASCII image of the dice.
	 *
	 *  @param value The index into the ASCII image of the dice.
	 */
	private void printDiceLine(int value) {
		System.out.print(line[getDiceLine(value)]);
	}
	
	/**
	 *  Gets the index number into the ASCII image of the dice.
	 *
	 *  @param value The index into the ASCII image of the dice.
	 */
	private int getDiceLine(int value) {
		if (value < 7) return 0;
		if (value < 14) return 1;
		switch (value) {
			case 20: case 22: case 25:
				return 1;
			case 16: case 17: case 18: case 24: case 28: case 29: case 30:
				return 2;
			case 19: case 21: case 23:
				return 3;
			case 14: case 15:
				return 4;
			case 26: case 27:
				return 5;
			default:	// value > 30
				return 6;
		}
	}
}
