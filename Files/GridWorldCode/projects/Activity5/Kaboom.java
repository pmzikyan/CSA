/**
 *  An Actor of an explosion that lasts for 3 turns.
 *  Final stage of Stone -> Boulder -> Kaboom
 *
 *	@author	Petros Mzikyan
 *	@since	3/26/2025
 */

import info.gridworld.actor.Actor;

public class Kaboom extends Actor
{
	private final int THRESHOLD = 3;
	private int steps;

	/* Initalizes steps and sets its color to null */
	public Kaboom() 
	{ 
		steps = 0;
		setColor(null); 
	}
	/**
	 * Increments steps and checks if it has reached the threshold
	 * If it has, it removes itself from the grid.
	 */
	public void act()
	{
		steps++;
		if (steps == THRESHOLD)
			removeSelfFromGrid();
	}
}
