import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import info.gridworld.actor.Rock;

import java.util.ArrayList;
import java.awt.Color;

public class KingCrab extends CrabCritter {
	
	public void processActors(ArrayList<Actor> actors){
		
		for (Actor a : actors) {
			if (!(a instanceof Rock) && !(a instanceof Critter))
			a.removeSelfFromGrid();
			else{
				Location actorLoc = a.getLocation();
				Location kingLoc = getLocation();
				
				int direction = kingLoc.getDirectionToward(actorLoc);
				Location newLoc = actorLoc.getAdjacentLocation(direction);
				
				//~ System.out.println(direction);
				//~ System.out.println(newLoc);
				//~ System.out.println("--------------");

				Grid gr = getGrid();
				if (gr.isValid(newLoc) && gr.get(newLoc) == null) {
					a.moveTo(newLoc);
				} else {
					a.removeSelfFromGrid();
				}
			}
		}		
	}
}
