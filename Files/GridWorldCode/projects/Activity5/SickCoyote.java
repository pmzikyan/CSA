/**
 *  The Coyote critter's state of being knocked out. SickCoyote extends Actor.
 *  SickCoyote replaces a Coyote when the Coyote is knocked out by a RR.
 *  After 10 turns the SickCoyote turns back into a Coyote.
 *
 *	@author	Petros Mzikyan
 *	@since	3/28/2025
 */

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;

import java.awt.Color;

public class SickCoyote extends Actor
{
	private final int THRESHOLD = 10;
	private int lifetime;

	/* Sets its lifetime to the THRESHOLD and sets the color to null */
	public SickCoyote() 
	{ 
		lifetime = THRESHOLD; 
		setColor(null);
	}

	/**
	 * Decrements lifetime and checks if it has reached 0
	 * If it has reached 0, the SickCoyote turns into a Coyote
	 */
	public void act()
	{
		lifetime--; 
		if (lifetime == 0) {
			Location loc = getLocation();
			Grid<Actor> grid = getGrid();
			removeSelfFromGrid();
			Coyote coyote = new Coyote();
			coyote.putSelfInGrid(grid, loc);
		}
	}
}
