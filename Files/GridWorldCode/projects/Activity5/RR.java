/**
 * 
 * 
 * test
 */ 

import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;

import java.util.ArrayList;

public class RR extends Critter
{
	public RR() 
	{ 
		setDirection(Location.NORTH);
		setColor(null); 
	}
	
	public ArrayList<Actor> getActors() { return new ArrayList(); }
	
	public void processActors(ArrayList<Actor> actors) { }
	
	public ArrayList<Location> getMoveLocations()
	{
		ArrayList<Location> locs = new ArrayList<Location>();
		int j = 3;
        while (j > 0 && locs.size() == 0)
		{
			int d = Location.NORTH;
			for (int i = 0; i < Location.FULL_CIRCLE / Location.HALF_RIGHT; i++)
			{
				
				Location neighborLoc = getAdjacentLocation(d, j);
				Grid grid = getGrid();
				if (grid.isValid(neighborLoc) && (
						grid.get(neighborLoc) == null ||
						grid.get(neighborLoc) instanceof Coyote ||
						grid.get(neighborLoc) instanceof Boulder))
					locs.add(neighborLoc);
					
				d = d + Location.HALF_RIGHT;
			}
			j--;
		}
		
		return locs;
	}
	
	public void makeMove(Location loc)
    {
        Grid grid = getGrid();
        if (loc == null)
            removeSelfFromGrid();
        else if (grid.get(loc) == null)
			moveTo(loc);
		else if (grid.get(loc) instanceof Boulder) {
			Actor boulder = getGrid().get(loc);
			boulder.removeSelfFromGrid();
			Kaboom kaboom = new Kaboom();
			kaboom.putSelfInGrid(getGrid(), loc);
			removeSelfFromGrid();
			return;
		}
		else if (grid.get(loc) instanceof Coyote) {
			Actor coyote = getGrid().get(loc);
			coyote.removeSelfFromGrid();
			moveTo(loc);
			
			ArrayList<Location> emptyLocs= grid.getEmptyAdjacentLocations(loc);
			Location coyoteLoc = emptyLocs.get((int)(Math.random() * 
													emptyLocs.size()));
			SickCoyote sickCoyote = new SickCoyote();
			sickCoyote.putSelfInGrid(grid, coyoteLoc);
		}
        
    }
    
    
    
    
    
     private Location getAdjacentLocation(int direction, int num)
    {
        // reduce mod 360 and round to closest multiple of 45
        int adjustedDirection = (direction + Location.HALF_RIGHT / 2) % 
												Location.FULL_CIRCLE;
        if (adjustedDirection < 0)
            adjustedDirection += Location.FULL_CIRCLE;

        adjustedDirection = (adjustedDirection / Location.HALF_RIGHT) * 
													Location.HALF_RIGHT;
        int dc = 0;
        int dr = 0;
        if (adjustedDirection == Location.EAST)
            dc = num;
        else if (adjustedDirection == Location.SOUTHEAST)
        {
            dc = num;
            dr = num;
        }
        else if (adjustedDirection == Location.SOUTH)
            dr = num;
        else if (adjustedDirection == Location.SOUTHWEST)
        {
            dc = -num;
            dr = num;
        }
        else if (adjustedDirection == Location.WEST)
            dc = -num;
        else if (adjustedDirection == Location.NORTHWEST)
        {
            dc = -num;
            dr = -num;
        }
        else if (adjustedDirection == Location.NORTH)
            dr = -num;
        else if (adjustedDirection == Location.NORTHEAST)
        {
            dc = num;
            dr = -num;
        }
        return new Location(getLocation().getRow() + dr, getLocation().getCol() + dc);
    }
}
