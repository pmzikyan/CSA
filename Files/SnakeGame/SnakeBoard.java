/**
 *	<Describe the SnakeBoard here>
 *
 *	@author	Petros Mzikyan
 *	@since	5/5/2025
 */
public class SnakeBoard {
	
	/*	fields	*/
	private char[][] board;			// The 2D array to hold the board
	
	/*	Constructor	*/
	public SnakeBoard(int height, int width) 
	{
		board = new char[height][width];
		for (int i = 0; i < board.length; i++)
		{
			for (int j = 0; j < board[0].length; j++)
			{
				boolean iMin = i == 0, iMax = i == height - 1, 
						jMin = j == 0, jMax = j == width - 1;
				
				if ((iMin || iMax) && (jMin || jMax))
					board[i][j] = '+';
				else if ((iMin || iMax) && !(jMin || jMax))
					board[i][j] = '-';
				else if (!(iMin || iMax) && (jMin || jMax))
					board[i][j] = '|';
				else
					board[i][j] = ' ';
			}
		}
	}
	
	/**
	 *	Print the board to the screen.
	 */
	public void printBoard(Snake snake, Coordinate target) {
		Coordinate coord;
		for (int i = 0; i < board.length; i++)
		{
			for (int j = 0; j < board[0].length; j++)
			{
				coord = new Coordinate(i, j);
				if (coord.equals(target))
					System.out.print("+");
				else if (snake.contains(coord))
				{
					if (snake.indexOf(coord) == 0)
						System.out.println("*");
					else
						System.out.print("*");
				}
				else
					System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
	
	/* Helper methods go here	*/
	
	/*	Accessor methods	*/

	
	/********************************************************/
	/********************* For Testing **********************/
	/********************************************************/
	
	public static void main(String[] args) {
		// Create the board
		int height = 10, width = 15;
		SnakeBoard sb = new SnakeBoard(height, width);
		// Place the snake
		Snake snake = new Snake(new Coordinate(3, 3));
		// Place the target
		Coordinate target = new Coordinate(1, 7);
		// Print the board
		sb.printBoard(snake, target);
	}
}
