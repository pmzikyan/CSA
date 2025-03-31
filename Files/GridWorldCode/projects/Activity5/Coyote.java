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
	private boolean sleeping, placeStone;
	
	public Coyote() 
	{ 
		steps = 0;
		sleeping = false;
		placeStone = false;
		setDirection((int)(Math.random()*8)*45);
		setColor(null); 
	}
	
	public ArrayList<Actor> getActors()
	{
		ArrayList<Actor> out = new ArrayList<Actor>();
		Location loc = getLocation().getAdjacentLocation(getDirection());
		if (getGrid().isValid(loc)) out.add(getGrid().get(loc));
		return out;
	}
	
	
	public void processActors(ArrayList<Actor> actors) { }
	
	public ArrayList<Location> getMoveLocations()
	{
		ArrayList<Location> out = new ArrayList<Location>();
		Location loc = getLocation().getAdjacentLocation(getDirection());
		
		if (!getGrid().isValid(loc));
		else if (getGrid().get(loc) == null) out.add(loc);
		else
		{
			sleeping = !sleeping;
			steps = 0;
		}
		
		return out;
	}
	
	public void makeMove(Location loc)
    {
        if (loc == null)
            removeSelfFromGrid();
        else if (!sleeping)
        {
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
			if (!sleeping) placeStone = true;
			else setDirection((int)(Math.random()*8)*45);
		}
    }
}
