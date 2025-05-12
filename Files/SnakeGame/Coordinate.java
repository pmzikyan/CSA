/**
 *	A coordinate on a grid. Integer row and col values.
 *
 *	@author Petros Mzikyan
 */
public class Coordinate implements Comparable<Coordinate>
{
	private int row, col;
		
	public Coordinate(int myRow, int myCol)
	{
		row = myRow;
		col = myCol;
	}
	
	/* Accessor methods */
	public int getRow() { return row; }
	public int getCol() { return col; }
	
	@Override
	public boolean equals(Object other)
	{
		return compareTo((Coordinate)other) == 0;
	}
	
	/**
	 *	Coordinate is greater when:
	 *	1. row is greater or
	 *	2. row is equal and col is greater
	 *	3. otherwise Coordinates are equal
	 *	@return		negative if less than, 0 if equal, positive if greater than
	 */
	 @Override
	public int compareTo(Coordinate other) {
		if (! (other instanceof Coordinate))
			throw new IllegalArgumentException("compareTo not Coordinate object");
		if (row > ((Coordinate)other).row || row < ((Coordinate)other).row)
			return row - ((Coordinate)other).row;
		if (col > ((Coordinate)other).col || col < ((Coordinate)other).col)
			return col - ((Coordinate)other).col;
		return 0;
	}
	
	public String toString()
	{	return "[ " + row + ", " + col + "]";  }
	
}
