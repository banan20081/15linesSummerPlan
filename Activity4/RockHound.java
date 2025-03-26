//~ import info.gridworld.actor.Actor;
//~ import info.gridworld.actor.ActorWorld;
//~ import info.gridworld.actor.Flower;
//~ import info.gridworld.actor.Rock;
//~ import info.gridworld.grid.BoundedGrid;
//~ import info.gridworld.grid.Location;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import info.gridworld.actor.Rock;


import java.util.ArrayList;
import java.awt.Color;

public class RockHound extends Critter{
	public void processActors(ArrayList<Actor> actors){
        for (Actor a : actors)
        {
            if (a instanceof Rock)
                a.removeSelfFromGrid();
        }
    }
}
