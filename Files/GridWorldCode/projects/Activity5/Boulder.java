/**
 *  An Actor of a boulder that lasts for 1-200 turns (unless specified).
 *  It turns red when it is 3 turns away from changing into a Kaboom
 *  Second stage of Stone -> Boulder -> Kaboom
 *
 *	@author	Petros Mzikyan
 *	@since	3/26/2025
 */

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;

import java.awt.Color;

public class Boulder extends Actor
{
	private final int THRESHOLD = 3;
	private int steps;

	/* Sets the steps to a random number between 1-200 if it's not specified */
	public Boulder() { this((int)(Math.random()*200) + 1); }

	/* Sets steps to num and sets the color to either red or null depending if steps <= 3 */
	public Boulder(int num) 
	{ 
		steps = num;
		if (steps > THRESHOLD)
			setColor(null);
		else
			setColor(Color.RED);
	}

	/**
	 * Increments steps and checks if it has reached the threshold or 0
	 * If it has reached the threshold, it turns red
	 * If it has reached 0, the Boulder turns into a Kaboom
	 */
	public void act()
	{
		steps--;
		if (steps == 3)
			setColor(Color.RED);
		else if (steps == 0) {
			Location loc = getLocation();
			Grid<Actor> grid = getGrid();
			removeSelfFromGrid();
			Kaboom kaboom = new Kaboom();
			kaboom.putSelfInGrid(grid, loc);
		}
	}
}
