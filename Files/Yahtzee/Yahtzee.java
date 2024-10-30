/**
 *  
 *
 *  @author Petros Mzikyan
 * 	@since 10/23/2024
*/
public class Yahtzee {	
	
	private final int NUM_ROUNDS = 13;
	private final int NUM_PLAYERS = 2;
	
	private YahtzeePlayer player1, player2;
	private Prompt ask;
	private DiceGroup diceGroup;
	
	private boolean player1Turn;
	private int round;
	
	public Yahtzee() {
		player1 = new YahtzeePlayer();
		player2 = new YahtzeePlayer();
		ask = new Prompt();
		diceGroup = new DiceGroup();
		
		player1Turn = true;
		round = 0;
	}
	
	public static void main(String[] args) {
		Yahtzee yahtzee = new Yahtzee();
		yahtzee.printHeader();
		yahtzee.preGame();
		yahtzee.run();
	}
	
	public void run() {
		while (round < 13)
		{
			round++;
			do {
				printScore();
				YahtzeePlayer currentPlayer;
				if (player1Turn) {
					currentPlayer = player1;
					System.out.println("\nRound " + round + " of " + 
									NUM_ROUNDS + " rounds.\n");
				}
				else
					currentPlayer = player2;
				
				ask.getString("\n" + currentPlayer.getName() + 
		", it's your turn to play. Please hit enter to roll the dice");
		
				diceGroup.rollDice();
				diceGroup.printDice();
				
				String diceToChange = "";
				for (int i = 0; i < 3 && !diceToChange.equals("-1"); i++)
				{
					diceToChange = ask.getString("Which di(c)e would you like to keep? "
+ "Enter the values you'd like to 'hold' without spaces. For examples, " 
+ "if you'd like to 'hold' die 1, 2, and 5, enter 125 (enter -1 if you'd like to end the turn)");
					
					if (!diceToChange.equals("-1")) {
						diceGroup.rollDice(diceToChange);
						diceGroup.printDice();
					}
				}
				
				printScore();
				
				boolean validChoice;
				do {
					int choice = ask.getInt(currentPlayer.getName() + 
", now you need to make a choice. Pick a valid integer from the list above", 1, 13);
					validChoice = currentPlayer.getScoreCard().changeScore(choice, diceGroup);
				}
				while (!validChoice);
				
				
				player1Turn = !player1Turn;
			}
			while (player1Turn);
		}
	}
	
	private void printScore() {
		player1.getScoreCard().printCardHeader();
		player1.getScoreCard().printPlayerScore(player1);
		player2.getScoreCard().printPlayerScore(player2);
	}
	
	/**
	 *  Does the pre-game preperation (getting the players' names and
	 * 	getting who goes first and who goes second)
	 */
	public void preGame() {
		String name1, name2;
		name1 = name2 = "";
		
		do
			name1 = ask.getString("Player 1, please enter your first name");
		while (name1.length() < 1 || name1.length() > 12);
		
		System.out.println();
		do
			name2 = ask.getString("Player 2, please enter your first name");
		while (name2.length() < 1 || name2.length() > 12 || name2.equals(name1));
		
		int temp1, temp2;
		temp1 = temp2 = 0;
		
		while (temp1 == temp2)
		{
			ask.getString("\nLet's see who will go first. " + name1 + 
								", please hit enter to roll the dice ");
			diceGroup.rollDice();
			diceGroup.printDice();
			temp1 = diceGroup.getTotal();
			
			ask.getString(name2 + 
					", it's your turn. Please hit enter to roll the dice");
			diceGroup.rollDice();
			diceGroup.printDice();
			temp2 = diceGroup.getTotal();
			
			System.out.println(name1 + ", you rolled a sum of " + temp1 + 
					", and " + name2 + ", you rolled a sum of " + temp2 + ".");
					
			if (temp1 == temp2)
				ask.getString("\nThe sums were a tie! Press enter to roll again");
		}
		
		if (temp1 > temp2) {
			System.out.println(name1 + 
					", since your sum was higher, you'll roll first.");
			player1.setName(name1);
			player2.setName(name2);
		}
		else {
			System.out.println(name2 + 
					", since your sum was higher, you'll roll first.");
			player1.setName(name2);
			player2.setName(name1);
		}
	}
	
	public void printHeader() {
		System.out.println("\n");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("| WELCOME TO MONTA VISTA YAHTZEE!                                                    |");
		System.out.println("|                                                                                    |");
		System.out.println("| There are 13 rounds in a game of Yahtzee. In each turn, a player can roll his/her  |");
		System.out.println("| dice up to 3 times in order to get the desired combination. On the first roll, the |");
		System.out.println("| player rolls all five of the dice at once. On the second and third rolls, the      |");
		System.out.println("| player can roll any number of dice he/she wants to, including none or all of them, |");
		System.out.println("| trying to get a good combination.                                                  |");
		System.out.println("| The player can choose whether he/she wants to roll once, twice or three times in   |");
		System.out.println("| each turn. After the three rolls in a turn, the player must put his/her score down |");
		System.out.println("| on the scorecard, under any one of the thirteen categories. The score that the     |");
		System.out.println("| player finally gets for that turn depends on the category/box that he/she chooses  |");
		System.out.println("| and the combination that he/she got by rolling the dice. But once a box is chosen  |");
		System.out.println("| on the score card, it can't be chosen again.                                       |");
		System.out.println("|                                                                                    |");
		System.out.println("| LET'S PLAY SOME YAHTZEE!                                                           |");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("\n\n");
	}
}
