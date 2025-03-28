/**
 * 
 * 
 * test
 */ 

import info.gridworld.actor.Actor;

public class Kaboom extends Actor
{
	private final int THRESHOLD = 3;
	private int steps;
	
	public Kaboom() 
	{ 
		steps = 0;
		setColor(null); 
	}
	
	public void act()
	{
		steps++;
		if (steps == THRESHOLD)
			removeSelfFromGrid();
	}
}
