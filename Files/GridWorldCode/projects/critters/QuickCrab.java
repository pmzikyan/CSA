import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

/**
 *	A type of CrabCritter that moves 2 spaces instead of 1 whenever possible
 *
 *	@author	Petros Mzikyan
 *	@since	3/19/2025
 */
public class QuickCrab extends CrabCritter
{
    /**
     * @return list of empty locations
     * two locations to the right and two locations to the left
     */
    public ArrayList<Location> getMoveLocations()
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid grid = getGrid();
        Location selfLoc = getLocation();
        
        Location tempLoc = selfLoc.getAdjacentLocation(Location.RIGHT);

        if (grid.isValid(tempLoc) && grid.get(tempLoc) == null)
        {
            Location loc2 = tempLoc.getAdjacentLocation(Location.RIGHT);
            if(grid.isValid(loc2) && grid.get(loc2)== null)
                locs.add(loc2);
        }

        tempLoc = selfLoc.getAdjacentLocation(Location.LEFT);

        if (grid.isValid(tempLoc) && grid.get(tempLoc) == null)
        {
            Location loc2 = tempLoc.getAdjacentLocation(Location.LEFT);
            if(grid.isValid(loc2) && grid.get(loc2)== null)
                locs.add(loc2);
        }

        if (locs.size() == 0)
            return super.getMoveLocations();

        return locs;
    }
}
