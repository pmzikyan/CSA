import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

/**
 *	Extends ChameleonCrittor and acts like it, except instead of reacting
 * 	to actors in any direction it reacts to actors behind and in front of it
 *
 *	@author	Petros Mzikyan
 *	@since	3/19/2025
 */
public class KingCrab extends CrabCritter
{

    /**
     * Computes the rounded integer distance between two given locations.
     */
    public int distanceFrom(Location loc1, Location loc2)
    {
        int x1 = loc1.getRow();
        int y1 = loc1.getCol();
        int x2 = loc2.getRow();
        int y2 = loc2.getCol();
        double dist = Math.sqrt((x1 - x2)*(x1 - x2) + (y1 - y2)*(y1 - y2)) + .5;
        return (int)Math.floor(dist);
    }

    /*
     * This method moves the Actor to a location that is one location
     * further away from this KingCrab and returns true. If there is no
     * location that is one location further away, the method returns false.
     */
    private boolean moveOneMoreAway(Actor a)
    {
        ArrayList<Location> locs =
                getGrid().getEmptyAdjacentLocations(a.getLocation());

        for(Location loc:locs)
        {
            if(distanceFrom(getLocation(), loc) > 1)
            {
                a.moveTo(loc);
                return true;
            }
        }

        return false;
    }

    /*
     * Each actor in the list actors is told to move one location further
     * away from this KingCrab. If that is not possible, the actor is
     * removed from the grid.
     */
    public void processActors(ArrayList<Actor> actors)
    {
        for (Actor a : actors)
        {
            if (!moveOneMoreAway(a))
            {
                a.removeSelfFromGrid();
            }
        }
    }
}
