import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.awt.Color;

/**
 *	Critter that changes color lightness/darkness depending on how many
 * 	critters there are near it and how "couragous" it is (threshold for changing color)
 *
 *	@author	Petros Mzikyan
 *	@since	3/19/2025
 */
public class BlusterCritter extends Critter
{
    private int courage;

    public BlusterCritter(int num)
    {
        super();
        courage = num;
    }

    /**
     * Gets the actors that are 2 or less steps away and returns them in
     * an ArrayList
     * @return	ArrayList of the actors in a 2 step radius
     */
    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> out = new ArrayList<Actor>();
        Location location = getLocation();
        
        for(int row = location.getRow() - 2; row <= location.getRow() + 2; row++ ) {
            for(int col = location.getCol() - 2; col <= location.getCol() + 2; col++) {
                Location newLoc = new Location(row,col);
                
                if(getGrid().isValid(newLoc))
                {
                    Actor actor = getGrid().get(newLoc);
                    if(actor != null && actor != this)
                        out.add(actor);
                }
            }
		}
		
        return out;
    }
    /**
     * Gets the number of critters near itself and decides to either darken or lighten
     * @param	actors	the list of actors to look from for critters
     */
    public void processActors(ArrayList<Actor> actors)
    {
        int numCritters = 0;
        for(Actor actor: actors)
            if(actor instanceof Critter)
                numCritters++;
        
        if(numCritters < courage)
            lighten();
        else
            darken();
    }

    /**
     * Darkens the color by subracting 1 from the red, green, and blue values
     * of the color of the BlusterCritter
     */
    private void darken()
    {
        Color c = getColor();
        
        int r = c.getRed();
        int g = c.getGreen();
        int b = c.getBlue();
        
        if(r > 0) r--;
        if(g > 0) g--;
        if(b > 0) b--;
        
        setColor(new Color(r, g, b));
    }

    /**
     * Lightens the color by adding 1 to the red, green, and blue values
     * of the color of the BlusterCritter
     */
    private void lighten()
    {
        Color c = getColor();
        
        int r = c.getRed();
        int g = c.getGreen();
        int b = c.getBlue();
        
        if(r < 255) r++;
        if(g < 255) g++;
        if(b < 255) b++;
        
        setColor(new Color(r, g, b));
    }
}
