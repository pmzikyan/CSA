/**
 *  This class creates and manages one array of pegs from the game MasterMind.
 *
 *  @author	Petros Mzikyan
 *	@since	9/27/2024
*/

public class PegArray {

	// array of pegs
	private Peg [] pegs;

	// the number of exact and partial matches for this array
	// as matched against the master.
	// Precondition: these values are valid after getExactMatches() and getPartialMatches()
	//				are called
	private int exactMatches, partialMatches;
	
	// the amount of exact matches for each letter
	private int aExact, bExact, cExact, dExact, eExact, fExact;
		
	/**
	 *	Constructor
	 *	@param numPegs	number of pegs in the array
	 */
	public PegArray(int numPegs) 
	{	
		pegs = new Peg[numPegs];
		for (int i = 0; i < pegs.length; i++)
		{
			pegs[i] = new Peg();
		}
	}
	
	/**
	 *	Return the peg object
	 *	@param n	The peg index into the array
	 *	@return		the peg object
	 */
	public Peg getPeg(int n) { return pegs[n]; }
	
	/**
	 *  Finds exact matches between master (key) peg array and this peg array
	 *	Postcondition: field exactMatches contains the matches with the master
	 *  @param master	The master (code) peg array
	 *	@return			The number of exact matches
	 */
	public int getExactMatches(PegArray master) 
	{
		aExact = bExact = cExact = dExact = eExact = fExact = 0;
		exactMatches = 0;
		for (int i = 0; i < pegs.length; i++)
		{
			if (master.getPeg(i).getLetter() == 
					pegs[i].getLetter())
			{
				exactMatches++;
				
				switch (pegs[i].getLetter())
				{
					case 'A':
						aExact++;
						break;
					case 'B':
						bExact++;
						break;
					case 'C':
						cExact++;
						break;
					case 'D':
						dExact++;
						break;
					case 'E':
						eExact++;
						break;
					case 'F':
						fExact++;
				}
			}
		}
		return exactMatches;
	}
	
	/**
	 *  Find partial matches between master (key) peg array and this peg array
	 *	Postcondition: field partialMatches contains the matches with the master
	 *  @param master	The master (code) peg array
	 *	@return			The number of partial matches
	 */
	public int getPartialMatches(PegArray master) 
	{
		int aOff, bOff, cOff, dOff, eOff, fOff;
		aOff = bOff = cOff = dOff = eOff = fOff = 0;
		partialMatches = 0;
		
		for (int i = 0; i < pegs.length; i++)
		{
			boolean loop = true;
			
			int k = 0;
			switch (pegs[i].getLetter())
				{
					case 'A':
						k = aOff;
						break;
					case 'B':
						k = bOff;
						break;
					case 'C':
						k = cOff;
						break;
					case 'D':
						k = dOff;
						break;
					case 'E':
						k = eOff;
						break;
					case 'F':
						k = fOff;
				}
			while (k < pegs.length && loop)
			{
				if (pegs[i].getLetter() == master.getPeg(k).getLetter())
				{
					switch (pegs[i].getLetter())
					{
					case 'A':
						aOff = k + 1;
						break;
					case 'B':
						bOff = k + 1;
						break;
					case 'C':
						cOff = k + 1;
						break;
					case 'D':
						dOff = k + 1;
						break;
					case 'E':
						eOff = k + 1;
						break;
					case 'F':
						fOff = k + 1;
					}
					loop = false;
					partialMatches++;
				}
				k++;
			}
			
		}
		
		partialMatches -= aExact + bExact + cExact + dExact + eExact + fExact;
		
		return partialMatches;
	}
	
	// Accessor methods
	// Precondition: getExactMatches() and getPartialMatches() must be called first
	public int getExact() { return exactMatches; }
	public int getPartial() { return partialMatches; }

}
