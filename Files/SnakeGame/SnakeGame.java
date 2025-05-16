/**
 *	Snake Game
 *	The classic game of Snake. Uses a Snake, which extends SinglyLinkedList,
 *	to represent the Coordinates of every segment of the snake. The snake moves
 *	by adding and removing Coordinates from the SinglyLinkList Snake. The game
 *	uses the SnakeBoard class to store and print the grid which the snake is on.
 *	Prompt is used to get input from the user, and FileUtils is used to save the
 *	game to a file and restore the game from the file.
 *	
 *	@author	Petros Mzikyan
 *	@since	5/5/2025
 */
public class SnakeGame {

	private final int ROWS = 20;
	private final int COLS = 25;
	private final int BOARD_AREA = ROWS * COLS;
	private final int SCORE_THRESHOLD = 10;
	private final String FILE_NAME = "snakeGameSave.txt";

	private Snake snake;		// the snake in the game
	private SnakeBoard board;	// the game board
	private Coordinate target;	// the target for the snake
	private int score;			// the score of the game
	private boolean running;
	private boolean alive;
	private Prompt ask;
	private FileUtils fileUtils;

	/*	Constructor	*/
	public SnakeGame()
	{
		createSnake();
		board = new SnakeBoard(ROWS, COLS);
		createTarget();
		score = 0;
		running = true;
		alive = true;
		ask = new Prompt();
		fileUtils = new FileUtils();
	}
	
	/*	Main method	*/
	public static void main(String[] args) {
		SnakeGame sg = new SnakeGame();
		sg.runSnakeGame();
	}

	/* Runner */
	public void runSnakeGame()
	{
		printIntroduction();

		while (running)
		{
			board.printBoard(snake, target);
			System.out.println();
			press(ask.getString("Score: " + score + " (w - North, s - South, " +
									"d - East, a - West, h - Help)"));
			System.out.println();
			if (!alive)
			{
				board.printBoard(snake, target);
				System.out.println();

				if (score + SCORE_THRESHOLD >= BOARD_AREA)
					System.out.println("You won!!! (Reached max score)");
				else
					System.out.println("You lost!");

				System.out.println("Final score of " + score + ".\n");

				ask.getString("Press enter to try again");
				System.out.println();

				createSnake();
				createTarget();
				score = 0;
				alive = true;
			}
		}

		System.out.println("Thanks for playing SnakeGame!!!\n");
	}

	/**
	 * Uses the inputted letter to do an action (returns false if it's an invalid input)
	 * @param in	user input
	 * @return		true if the input is valid; false if it's not
	 */
	private boolean press(String in)
	{
		in = in.toLowerCase();
		Coordinate newCoord;
		switch (in)
		{
			case "w": case "s": case "d": case "a":
				if (!move(snake.getAdjacentLocation(in)))
					alive = false;
				break;
			case "h":
				helpMenu();
				System.out.println();
				break;
			case "f":
				saveToFile();
				System.out.println("\nGame saved to " + FILE_NAME);
				break;
			case "r":
				restoreFromFile();
				System.out.println("\nGame restored from " + FILE_NAME);
				break;
			case "q":
				if (ask.getString("Do you really want to quit? (y or n)").equalsIgnoreCase("y"))
					running = false;
				System.out.println();
				break;
			default:
				return false;
		}
		return true;
	}

	/**
	 * Moves the snake to a location.
	 * @param coord	the Coordinate to move the snake to
	 * @return		false if the user can't make any more moves after; else true
	 */
	private boolean move(Coordinate coord)
	{
		if (!validLocation(coord) || snake.contains(coord))
			return false;
		snake.add(0, coord);
		if (coord.equals(target)) {
			score++;
			createTarget();
		}
		else
			snake.removeTail();
		if (snake.isSurrounded(board) || score + SCORE_THRESHOLD >= BOARD_AREA)
			return false;
		return true;
	}

	/* -----  Helper Methods  ----- */

	/**
	 * Saves the current game to the file of name FILE_NAME
	 */
	private void saveToFile()
	{
		java.io.PrintWriter file = fileUtils.openToWrite(FILE_NAME);
		file.println(removeFormatting(target.toString()));
		file.println(removeFormatting(snake.toString()));
		file.close();
	}

	/**
	 * Restores the game from the file of name FILE_NAME
	 */
	private void restoreFromFile()
	{
		java.util.Scanner scan = fileUtils.openToRead(FILE_NAME);
		target = new Coordinate(scan.nextInt(), scan.nextInt());
		snake = new Snake(scan);
		score = snake.size() - 5;
	}

	/**
	 * Removes the formatting ('[', ',', and ']') from a String
	 * @param str	String to remove formatting from
	 * @return		str with the removed formatting
	 */
	private String removeFormatting(String str)
	{
		String out = "";
		for (int i = 0; i < str.length(); i++)
			if (str.charAt(i) != '[' && str.charAt(i) != ',' && str.charAt(i) != ']')
				out += str.charAt(i);
		return out;
	}

	/**
	 * Returns true if the inputted location is valid, else false
	 * @param row	the row of the location
	 * @param col	the column of the location
	 * @return		true if location is valid; false otherwise
	 */
	private boolean validLocation(int row, int col)
	{
		return validLocation(new Coordinate(row, col));
	}

	/**
	 * Returns true if the inputted Coordinate is valid, else false
	 * @param coord	Coordinate to check the validity of
	 * @return		true if coord is valid; false otherwise
	 */
	private boolean validLocation(Coordinate coord)
	{
		return (coord.getRow() > 0 && coord.getRow() <= ROWS &&
				coord.getCol() > 0 && coord.getCol() <= COLS);
	}

	/**
	 * Creates a new target in a random valid location
	 * @return	true if a target could be placed (should always return true)
	 */
	private boolean createTarget()
	{
		int row, col;
		do {
			row = (int)(Math.random() * ROWS) + 1;
			col = (int)(Math.random() * COLS) + 1;
		} while (snake.contains(new Coordinate(row, col)));
		return createTarget(row, col);
	}

	/**
	 * Creates a new target Coordinate(row, col)
	 * @param row	row of the target
	 * @param col	column of the target
	 * @return		true if the location is valid; false if it's not
	 */
	private boolean createTarget(int row, int col)
	{
		if (row < 1 || row > ROWS || col < 1 || col > COLS)
			return false;
		target = new Coordinate(row, col);
		return true;
	}

	/**
	 * Assigns a new Snake to snake at a random valid location in the grid
	 * @return	true if the snake was created correctly (should always return true)
	 */
	private boolean createSnake()
	{
		return createSnake((int)(Math.random() * (ROWS - 4)) + 1,
								(int)(Math.random() * COLS) + 1);
	}

	/**
	 * Assigns a new Snake to snake at Coordinate(row, col)
	 * @param row	row of the snake head
	 * @param col	column of the snake head
	 * @return		true if the location is valid; false otherwise
	 */
	private boolean createSnake(int row, int col)
	{
		if (row < 1 || row > ROWS || col < 1 || col > COLS)
			return false;
		snake = new Snake(new Coordinate(row, col));
		return true;
	}
	
	/**	Print the game introduction	*/
	public void printIntroduction() {
		System.out.println("  _________              __            ________");
		System.out.println(" /   _____/ ____ _____  |  | __ ____  /  _____/_____    _____   ____");
		System.out.println(" \\_____  \\ /    \\\\__  \\ |  |/ // __ \\/   \\  ___\\__  \\  /     \\_/ __ \\");
		System.out.println(" /        \\   |  \\/ __ \\|    <\\  ___/\\    \\_\\  \\/ __ \\|  Y Y  \\  ___/");
		System.out.println("/_______  /___|  (____  /__|_ \\\\___  >\\______  (____  /__|_|  /\\___  >");
		System.out.println("        \\/     \\/     \\/     \\/    \\/        \\/     \\/      \\/     \\/");
		System.out.println("\nWelcome to SnakeGame!");
		System.out.println("\nA snake @****** moves around a board " +
							"eating targets \"+\".");
		System.out.println("Each time the snake eats the target it grows " +
							"another * longer.");
		System.out.println("The objective is to grow the longest it can " +
							"without moving into");
		System.out.println("itself or the wall.");
		System.out.println("\n");
	}
	
	/**	Print help menu	*/
	public void helpMenu() {
		System.out.println("\nCommands:\n" +
							"  w - move north\n" +
							"  s - move south\n" +
							"  d - move east\n" +
							"  a - move west\n" +
							"  h - help\n" +
							"  f - save game to file\n" +
							"  r - restore game from file\n" +
							"  q - quit");
		Prompt.getString("Press enter to continue");
	}
	
}
