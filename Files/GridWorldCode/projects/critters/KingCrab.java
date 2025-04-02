import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

/**
 *  A type of CrabCritter that "scares off" neighboring actors. If the actor
 *  is able to move away from the KingCrab, it does. If it can't, it gets
 *  removed from the grid.
 *
 *	@author	Petros Mzikyan
 *	@since	3/19/2025
 */
public class KingCrab extends CrabCritter
{
    /**
     * Calculates the hypotnuse of a right triangle with points A and B
     * (AKA it just finds the distance between two points)
     * @param loc1  point A
     * @param loc2  point B
     * @return      hypotnuse (rounded to the nearest integer)
     */
    public int distanceFrom(Location loc1, Location loc2)
    {
        int row1 = loc1.getRow(), col1 = loc1.getCol();
        int row2 = loc2.getRow(), col2 = loc2.getCol();

        double hypotenuse = Math.sqrt((row1 - row2) * (row1 - row2) +
                                        (col1 - col2) * (col1 - col2)) + 0.5;
        return (int) Math.floor(hypotenuse);
    }

    /**
     * Checks every actor near the KingCrab to see if they can move away.
     * If they can move away, they move away, else they get removed from the grid.
     */
    public void processActors(ArrayList<Actor> actors)
    {
        for (Actor actor : actors)
        {
            ArrayList<Location> locs =
                    getGrid().getEmptyAdjacentLocations(actor.getLocation());

            boolean moved = false;
            for(Location loc : locs) {
                if (distanceFrom(getLocation(), loc) > 1) {
                    actor.moveTo(loc);
                    moved = true;
                }
            }
            if (!moved)
                actor.removeSelfFromGrid();
        }
    }
}
