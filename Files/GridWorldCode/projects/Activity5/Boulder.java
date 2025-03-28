/**
 * 
 * 
 */ 

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;

import java.awt.Color;

public class Boulder extends Actor
{
	private final int THRESHOLD = 3;
	private int steps;
	
	public Boulder() { this((int)(Math.random()*200) + 1); }
	
	public Boulder(int num) 
	{ 
		steps = num;
		if (steps > THRESHOLD)
			setColor(null);
		else
			setColor(Color.RED);
	}
	
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
