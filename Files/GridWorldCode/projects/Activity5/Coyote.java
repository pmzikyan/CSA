/**
 *  A Wile E. Coyote Critter that works by moving 5 steps in a direction, or
 *  until it comes across an obstacle. Then it waits for 5 turns. Then it repeats,
 *  placing a stone where it stopped if it didn't hit an obstacle.
 *
 *	@author	Petros Mzikyan
 *	@since	3/26/2025
 */

import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;

import java.util.ArrayList;

public class Coyote extends Critter
{
	private final int THRESHOLD = 5;
	private int steps;
	private boolean sleeping, placeStone;
	
	/* Constructor that initializes the fields */
	public Coyote()
	{ 
		steps = 0;
		sleeping = false;
		placeStone = false;
		setDirection((int)(Math.random()*8)*45);
		setColor(null); 
	}

	/**
	 * Gets the Actor in front of it to process
	 * (There is no processing, but this is for if there had to be any processing)
	 * @return	the Actor in front of the Coyote
	 */
	public ArrayList<Actor> getActors()
	{
		ArrayList<Actor> out = new ArrayList<Actor>();
		Location loc = getLocation().getAdjacentLocation(getDirection());
		if (getGrid().isValid(loc)) out.add(getGrid().get(loc));
		return out;
	}

	/**
	 * The Coyote doesn't do anything in the processing step
	 * @param actors	The list of actors to process
	 */
	public void processActors(ArrayList<Actor> actors) { }

	/**
	 * Gets the location in front of it if the location is empty or a Boulder
	 * If the location in front of it isn't free, the Coyote goes into sleep.
	 * @return	ArrayList with the Location in front of it if it's valid
	 */
	public ArrayList<Location> getMoveLocations()
	{
		ArrayList<Location> out = new ArrayList<Location>();
		Location loc = getLocation().getAdjacentLocation(getDirection());
		
		if ((getGrid().isValid(loc)) && (getGrid().get(loc) == null || 
			getGrid().get(loc) instanceof Boulder)) 
			out.add(loc);
		else
		{
			sleeping = !sleeping;
			steps = 0;
			do {
				setDirection((int)(Math.random()*8)*45);
			} while (!getGrid().isValid(getLocation().getAdjacentLocation(getDirection())));
		}
		
		return out;
	}

	/**
	 * The Coyote goes to the Location of loc if it's not sleeping.
	 * It checks if the steps have reached 5 and if they have it changes the sleeping boolean.
	 * If it walks into a boulder, it explodes itself with the Boulder.
	 * If placeStone is true, it places a stone in its old location
	 * @param loc	The location for the Coyote to go in
	 */
	public void makeMove(Location loc)
    {
        if (loc == null)
            removeSelfFromGrid();
        else if (!sleeping)
        {
            if (getGrid().get(loc) instanceof Boulder)
            {
				Actor boulder = getGrid().get(loc);
				boulder.removeSelfFromGrid();
				Kaboom kaboom = new Kaboom();
				kaboom.putSelfInGrid(getGrid(), loc);
				removeSelfFromGrid();
				return;
			}
            
            Location oldLoc = getLocation();
            moveTo(loc);
            if (placeStone)
            {
				Stone stone = new Stone();
				stone.putSelfInGrid(getGrid(), oldLoc);
				placeStone = false;
			}
		}
        
        steps++;
        if (steps == THRESHOLD)
        {
			sleeping = !sleeping;
			steps = 0;
			if (sleeping) placeStone = true;
			else {
				do {
				setDirection((int)(Math.random()*8)*45);
				} while (!getGrid().isValid(getLocation().getAdjacentLocation(getDirection())));
			}
		}
    }
}
