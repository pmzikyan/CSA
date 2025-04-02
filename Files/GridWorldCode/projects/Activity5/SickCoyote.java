/**
 * 
 * 
 */ 

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;

import java.awt.Color;

public class SickCoyote extends Actor
{
	private final int THRESHOLD = 10;
	private int lifetime;
	
	public SickCoyote() 
	{ 
		lifetime = THRESHOLD; 
		setColor(null);
	}
	
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
