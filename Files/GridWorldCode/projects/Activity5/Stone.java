/**
 * 
 * 
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
	
	public Stone() { this((int)(Math.random()*200) + 1); }
	
	public Stone(int num) 
	{ 
		steps = num;
		if (steps > THRESHOLD)
			setColor(null);
		else
			setColor(Color.GREEN);
	}
	
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
