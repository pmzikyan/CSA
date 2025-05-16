/**
 *	A SinglyLinkedList of Coordinate objects representing
 *	a snake on a two-dimensional grid.
 *
 *	@author	Petros Mzikyan
 *	@since	5/5/2025
 */
public class Snake extends SinglyLinkedList<Coordinate> {
		
	/**	Constructor for making a Snake that is 5 grids high facing north.
	 *	Places the snake head at Coordinate location and the tail below.
	 *	Precondition: To place the Snake, the board must have at
	 *				least location.getRow() + 4 more rows.
	 */
	public Snake(Coordinate location) 
	{
		for (int i = 0; i < 5; i++)
			add(new Coordinate(location.getRow() + i, location.getCol()));
	}

	/**
	 * Constructor that uses a Scanner to make the saved snake
	 * @param scan	file to scan
	 */
	public Snake(java.util.Scanner scan)
	{
		while (scan.hasNext())
			add(new Coordinate(scan.nextInt(), scan.nextInt()));
	}

	/**
	 * Returns the location adjecent to the snake's head in a direction dir
	 * @param dir	the direction to get the location of
	 * @return		the adjecent coordinate
	 */
	public Coordinate getAdjacentLocation(String dir)
	{
		if (isEmpty())
			return null;
		int row = get(0).getValue().getRow();
		int col = get(0).getValue().getCol();
		switch (dir)
		{
			case "w":
				return new Coordinate(row - 1, col);
			case "s":
				return new Coordinate(row + 1, col);
			case "d":
				return new Coordinate(row, col + 1);
			case "a":
				return new Coordinate(row, col - 1);
		}

		return get(0).getValue();
	}

	/**
	 * Returns true if the snake is surrounded by itself and/or the board; false otherwise
	 * @param snakeBoard	The board that the snake is in
	 * @return				True if snake is surrounded by itself and/or the board
	 * 						Else false
	 */
	public boolean isSurrounded(SnakeBoard snakeBoard)
	{
		char[][] board = snakeBoard.getBoard();
		Coordinate[] coords = {getAdjacentLocation("w"), getAdjacentLocation("s"),
				getAdjacentLocation("d"), getAdjacentLocation("a")};

		for (Coordinate coord : coords)
			if (board[coord.getRow()][coord.getCol()] == ' ' && !contains(coord))
				return false;

		return true;
	}

	/**
	 * Removes the tail of the snake
	 * @return	the tail that got removed
	 */
	public Coordinate removeTail()
	{
		return remove(size() - 1);
	}

	/**
	 * Converts the Snake to a Strng
	 * @return	the Snake as a String
	 */
	@Override
	public String toString()
	{
		if  (isEmpty())
			return "!!! Broken Snake !!!";
		String out = "";
		for (int i = 0; i < size(); i++)
			out += get(i).getValue() + " ";
		return out;
	}
}
