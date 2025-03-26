import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import java.awt.Color;
public class BlusterCritter extends Critter
{
    private int courageFactor;

    public BlusterCritter(int c)
    {
        super();
        courageFactor = c;
    }

    /**
     * Gets the actors for processing. The actors must be contained in the
     * same grid as this critter. Implemented to return the actors that
     * occupy neighboring grid locations within two steps of this critter
     * @return a list of actors that are neighbors of this critter
     */
    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = new ArrayList<Actor>();

        Location loc = getLocation();
        for(int r = loc.getRow() - 2; r <= loc.getRow() + 2; r++ )
            for(int c = loc.getCol() - 2; c <= loc.getCol() + 2; c++)
            {
                Location tempLoc = new Location(r,c);
                if(getGrid().isValid(tempLoc))
                {
                    Actor a = getGrid().get(tempLoc);
                    if(a != null && a != this)
                        actors.add(a);
                }
            }
        return actors;
    }
    /**
     * Processes the actors. Implemented to count all the actors within
     * 2 locations of this critter. If there are fewer than courageFactor
     * critters in these locations, this BlusterCritter lightens, otherwise
     * it darkens.
     * Precondition: All objects in <code>actors</code> are contained in the
     * same grid as this critter.
     * @param actors the actors to be processed
     */
    public void processActors(ArrayList<Actor> actors)
    {
        int count = 0;
        for(Actor a: actors)
            if(a instanceof Critter)
                count++;
        if(count < courageFactor)
            lighten();
        else
            darken();
    }

    /**
     * Darkens this critter's color by subtracting 1 from red, green, and
     * blue components if they are greater than 0. To darken the color
     * faster, subtract a slightly larger value.
     */
    private void darken()
    {
        Color c = getColor();
        int red = c.getRed();
        int green = c.getGreen();
        int blue = c.getBlue();
        if(red > 0) red--;
        if(green > 0) green--;
        if(blue > 0) blue--;
        setColor(new Color(red, green, blue));

        // this segment of code uses same logic as the flower class to darken
        // an object's color
        // to use this technique add DARKENING_FACTOR as a class instance
        // variable; then replace the active code for darken with the
        // following five lines of code
        // private static final double DARKENING_FACTOR = 0.05;
        // Color c = getColor();
        // int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
        // int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
        // int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));
        // setColor(new Color(red, green, blue));
    }

    /**
     * Lightens this critter's color by adding 1 to the red, green, and blue
     * components if they are less than 255. To lighten the color faster,
     * add a slightly larger value.
     */
    private void lighten()
    {
        Color c = getColor();
        int red = c.getRed();
        int green = c.getGreen();
        int blue = c.getBlue();
        if(red < 255) red++;
        if(green < 255) green++;
        if(blue < 255) blue++;
        setColor(new Color(red, green, blue));
    }
}