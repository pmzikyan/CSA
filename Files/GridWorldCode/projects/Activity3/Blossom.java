import java.awt.Color;
import info.gridworld.actor.Flower;

public class Blossom extends Flower
{
	private static final Color DEFAULT_COLOR = Color.PINK;
    private static final double DARKENING_FACTOR = 0.05;
	
	private int lifetime;
	
	public Blossom() { this(10); }
	
	public Blossom(int num) { lifetime = num; setColor(Color.GREEN); }
	
	/**
     * Causes the color of this flower to darken.
     */
    public void act()
    {
        Color c = getColor();
        int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
        int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
        int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));

        setColor(new Color(red, green, blue));
        lifetime--;
        
        if (lifetime == 0)
			removeSelfFromGrid();
    }
}
