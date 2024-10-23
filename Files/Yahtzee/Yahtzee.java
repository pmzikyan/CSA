/**
 *  
 *
 *  @author Petros Mzikyan
 * 	@since 10/23/2024
*/
public class Yahtzee {	
	
	YahtzeePlayer player1, player2;
	Prompt ask;
	DiceGroup diceGroup;
	
	public Yahtzee() {
		player1 = new YahtzeePlayer();
		player2 = new YahtzeePlayer();
		ask = new Prompt();
		diceGroup = new DiceGroup();
	}
	
	public static void main(String[] args) {
		Yahtzee yahtzee = new Yahtzee();
		yahtzee.printHeader();
		yahtzee.preGame();
		yahtzee.run();
	}
	
	public void preGame() {
		String name1 = ask.getString("Player 1, please enter your first name");
		String name2 = ask.getString("\nPlayer 2, please enter your first name");
		
		int temp1, temp2;
		temp1 = temp2 = 0;
		
		while (temp1 != temp2)
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
	}
	
	public void run() {
		
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
