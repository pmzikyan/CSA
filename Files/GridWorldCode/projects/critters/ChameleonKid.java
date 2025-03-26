import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;

import java.util.ArrayList;

/**
 *	Extends ChameleonCrittor and acts like it, except instead of reacting
 * 	to actors in any direction it reacts to actors behind and in front of it
 *
 *	@author	Petros Mzikyan
 *	@since	3/19/2025
 */
public class ChameleonKid extends ChameleonCritter
{
    /**
     * Gets and retuns an ArrayList of the actors in front of and behind
     * the ChameleonKid
     * @return	the list of the actors in front and behind of the Chameleon
     */
    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> out = new ArrayList<Actor>();
        
        int[] checkDirections = {Location.AHEAD, Location.HALF_CIRCLE};
        
        for (Location location : getValidLocations(checkDirections)) {
            Actor actor = getGrid().get(location);
            
            if (actor != null)
                out.add(actor);
        }
        
        return out;
    }

    /**
     * Returns the valid locations for the ChameleonKid
     * @param 	the array of directions to check for locations
     * @return 	the valid locations
     */
    public ArrayList<Location> getValidLocations(int[] directions)
    {
        ArrayList<Location> out = new ArrayList<Location>();
        Grid grid = getGrid();
        Location location = getLocation();

        for (int dir : directions) {
            Location newLoc = 
				location.getAdjacentLocation(getDirection() + dir);
			
            if (grid.isValid(newLoc))
                out.add(newLoc);
        }
        
        return out;
    }
}
