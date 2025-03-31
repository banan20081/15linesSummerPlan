import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import java.util.Random;

public class CoyoteRunner {
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld(new BoundedGrid<>(10, 10));

        // Add two Coyotes at random positions
        Random rand = new Random();
        for (int i = 0; i < 2; i++) {
            int row = rand.nextInt(10);
            int col = rand.nextInt(10);
            Location loc = new Location(row, col);
            while (world.getGrid().get(loc) != null) { // Ensure it's an empty spot
                row = rand.nextInt(10);
                col = rand.nextInt(10);
                loc = new Location(row, col);
            }
            world.add(loc, new Coyote());
        }

        // Add some Boulders at random positions
        for (int i = 0; i < 5; i++) { // Place 5 Boulders
            int row = rand.nextInt(10);
            int col = rand.nextInt(10);
            Location loc = new Location(row, col);
            while (world.getGrid().get(loc) != null) { // Ensure it's an empty spot
                row = rand.nextInt(10);
                col = rand.nextInt(10);
                loc = new Location(row, col);
            }
            world.add(loc, new Boulder());
        }

        world.show(); // Start the simulation
    }
}
