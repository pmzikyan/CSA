import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 *	A critter that "eats" rocks next to it, but it otherwise acts like
 *	a critter
 * 
 *	@author	Petros Mzikyan
 *	@since	3/19/2025
 */
public class RockHound extends Critter
{
    /**
     * Processes actors and determines if any are a rock, and if they are
     * the method removes the rock from the grid
     * @param	actors	the list of actors to look in to find rocks from
     */
    public void processActors(ArrayList<Actor> actors)
    {
        for (Actor actor : actors)
            if (actor instanceof Rock)
                actor.removeSelfFromGrid();
    }
}

