/**
 *  A Critter of the Roadrunner. RR goes up to 3 steps in any compass direction
 *  per turn (higher distance is prioritized). If the RR bumps into a Coyote,
 *  the RR goes into the space of the Coyote and a SickCoyote gets placed
 *  on an adjecent space. A Boulder explodes the RR like it does for Coyote.
 *
 *	@author	Petros Mzikyan
 *	@since	3/28/2025
 */

import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;

import java.util.ArrayList;

public class RR extends Critter
{
	/* Sets up the direction of NORTH and the color of null for the RR */
	public RR() 
	{ 
		setDirection(Location.NORTH);
		setColor(null); 
	}

	/**
	 * This method doesn't get used, so it's empty
	 * @return	Empty ArrayList
	 */
	public ArrayList<Actor> getActors() { return new ArrayList(); }

	/* This method doesn't get used, so it's empty */
	public void processActors(ArrayList<Actor> actors) { }

	/**
	 * Gets the ArrayList of the available locations.
	 * It prioritizes moving longer distances, so if it can move 3 steps, it
	 * doesn't check for 2 steps, and if it can move 2, it doesn't check for 1.
	 * The RR can move into empty spaces, Coyotes, and Boulders.
	 * @return	ArrayList of the available locations to move to.
	 */
	public ArrayList<Location> getMoveLocations()
	{
		ArrayList<Location> locs = new ArrayList<Location>();
		int j = 3;
        while (j > 0 && locs.size() == 0)
		{
			int d = Location.NORTH;
			for (int i = 0; i < Location.FULL_CIRCLE / Location.HALF_RIGHT; i++)
			{
				
				Location neighborLoc = getAdjacentLocation(d, j);
				Grid grid = getGrid();
				if (grid.isValid(neighborLoc) && (
						grid.get(neighborLoc) == null ||
						grid.get(neighborLoc) instanceof Coyote ||
						grid.get(neighborLoc) instanceof Boulder))
					locs.add(neighborLoc);
					
				d = d + Location.HALF_RIGHT;
			}
			j--;
		}
		
		return locs;
	}

	/**
	 * Moves to the location loc
	 * If the location has a Boulder, the RR and Boulder explode (Boulder -> Kaboom)
	 * If the location has a Coyote, the Coyote gets knocked out
	 * (The RR goes on the space where the Coyote was, and a SickCoyote gets placed
	 * on an adjacent space)
	 * @param loc
	 */
	public void makeMove(Location loc)
    {
        Grid grid = getGrid();
        if (loc == null)
            removeSelfFromGrid();
        else if (grid.get(loc) == null)
			moveTo(loc);
		else if (grid.get(loc) instanceof Boulder) {
			Actor boulder = getGrid().get(loc);
			boulder.removeSelfFromGrid();
			Kaboom kaboom = new Kaboom();
			kaboom.putSelfInGrid(getGrid(), loc);
			removeSelfFromGrid();
			return;
		}
		else if (grid.get(loc) instanceof Coyote) {
			Actor coyote = getGrid().get(loc);
			coyote.removeSelfFromGrid();
			moveTo(loc);
			
			ArrayList<Location> emptyLocs= grid.getEmptyAdjacentLocations(loc);
			Location coyoteLoc = emptyLocs.get((int)(Math.random() * 
													emptyLocs.size()));
			SickCoyote sickCoyote = new SickCoyote();
			sickCoyote.putSelfInGrid(grid, coyoteLoc);
		}
        
    }


	/**
	 * Returns the location in a num amount of spaces in the direction of direction
	 * @param direction		The direction to return the location of
	 * @param num			Number of spaces away to check
	 * @return				The location num number of spaces away in a direction
	 */
	private Location getAdjacentLocation(int direction, int num)
    {
        // reduce mod 360 and round to closest multiple of 45
        int adjustedDirection = (direction + Location.HALF_RIGHT / 2) % 
												Location.FULL_CIRCLE;
        if (adjustedDirection < 0)
            adjustedDirection += Location.FULL_CIRCLE;

        adjustedDirection = (adjustedDirection / Location.HALF_RIGHT) * 
													Location.HALF_RIGHT;
        int dc = 0;
        int dr = 0;
        if (adjustedDirection == Location.EAST)
            dc = num;
        else if (adjustedDirection == Location.SOUTHEAST)
        {
            dc = num;
            dr = num;
        }
        else if (adjustedDirection == Location.SOUTH)
            dr = num;
        else if (adjustedDirection == Location.SOUTHWEST)
        {
            dc = -num;
            dr = num;
        }
        else if (adjustedDirection == Location.WEST)
            dc = -num;
        else if (adjustedDirection == Location.NORTHWEST)
        {
            dc = -num;
            dr = -num;
        }
        else if (adjustedDirection == Location.NORTH)
            dr = -num;
        else if (adjustedDirection == Location.NORTHEAST)
        {
            dc = num;
            dr = -num;
        }
        return new Location(getLocation().getRow() + dr, getLocation().getCol() + dc);
    }
}
