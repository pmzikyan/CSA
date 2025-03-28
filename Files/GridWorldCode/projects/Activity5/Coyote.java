/**
 * 
 * 
 * test
 */ 

import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;

import java.util.ArrayList;

public class Coyote extends Critter
{
	private final int THRESHOLD = 5;
	private int steps;
	private boolean sleeping;
	
	public Coyote() 
	{ 
		steps = 0;
		sleeping = false;
		setDirection((int)(Math.random()*8)*45);
		setColor(null); 
	}
	
	public ArrayList<Actor> getActors()
	{
		
		getLocation().getAdjacentLocation(getDirection());
	}
	
	public void processActors(ArrayList<Actor> actors) { }
}
