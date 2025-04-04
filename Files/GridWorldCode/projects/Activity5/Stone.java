/**
 *  An Actor of a stone that lasts for 1-200 turns (unless specified).
 *  It turns green when it is 3 turns away from changing into a Kaboom
 *  First stage of Stone -> Boulder -> Kaboom
 *
 *	@author	Petros Mzikyan
 *	@since	3/26/2025
 */

import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;

import java.awt.Color;

public class Stone extends Rock
{
	private final int THRESHOLD = 3;
	private int steps;

	/* Sets the steps to a random number between 1-200 if it's not specified */
	public Stone() { this((int)(Math.random()*200) + 1); }

	/* Sets steps to num and sets the color to either green or null depending if steps <= 3 */
	public Stone(int num) 
	{ 
		steps = num;
		if (steps > THRESHOLD)
			setColor(null);
		else
			setColor(Color.GREEN);
	}

	/**
	 * Increments steps and checks if it has reached the threshold or 0
	 * If it has reached the threshold, it turns green
	 * If it has reached 0, the Boulder turns into a Boulder
	 */
	public void act()
	{
		steps--;
		if (steps == 3)
			setColor(Color.GREEN);
		else if (steps == 0) {
			Location loc = getLocation();
			Grid<Actor> grid = getGrid();
			removeSelfFromGrid();
			Boulder boulder = new Boulder();
			boulder.putSelfInGrid(grid, loc);
		}
	}
}
