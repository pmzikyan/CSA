import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;

public class SickCoyoteRunner
{
    public static void main(String[] args)
    {
        BoundedGrid<Actor> mygrid = new BoundedGrid<Actor>(20,20);
        ActorWorld world = new ActorWorld(mygrid);
        
        // Upper left grid
        for (int row = 2; row < 7; row++)
        	for (int col = 2; col < 7; col++)
        		world.add(new Location(row,col),new SickCoyote());
        // Lower left grid
        for (int row = 13; row < 18; row++)
        	for (int col = 2; col < 7; col++)
        		world.add(new Location(row,col),new SickCoyote());
        // Upper right grid
        for (int row = 2; row < 7; row++)
        	for (int col = 13; col < 18; col++)
        		world.add(new Location(row,col),new SickCoyote());
        // Lower right grid
        for (int row = 13; row < 18; row++)
        	for (int col = 13; col < 18; col++)
        		world.add(new Location(row,col),new SickCoyote());

        world.show();
    }
}
