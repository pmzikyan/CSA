import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;

public class CoyoteRunner
{
    public static void main(String[] args)
    {
        BoundedGrid<Actor> mygrid = new BoundedGrid<Actor>(16,16);
        ActorWorld world = new ActorWorld(mygrid);
        
        world.add(new Location(4,4),new Coyote());
        
        world.add(new Location(11,11),new Coyote());

        world.show();
    }
}
