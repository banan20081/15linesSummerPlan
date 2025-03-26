import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;

import java.util.ArrayList;
import java.awt.Color;

public class QuickCrab extends CrabCritter{
	public ArrayList<Location> getMoveLocations()
    {
		ArrayList<Location> locs = new ArrayList<Location>();
		Grid<Actor> gr= getGrid();

		int[] dirs = { Location.LEFT, Location.RIGHT };
		for (int direction : dirs){
			Location loc = getLocation();
			Location next = loc.getAdjacentLocation(getDirection() + direction);
			Location twoAway = next.getAdjacentLocation(getDirection() +direction);
			
			if(gr.isValid(next) && gr.get(next) == null && 
					gr.isValid(twoAway) && gr.get(twoAway) == null){
				locs.add(twoAway);
			}
		}
		
		if(locs.isEmpty()) return super.getMoveLocations();
			
		return locs;
    }
	//~ public ArrayList<Location> getMoveLocations()
    //~ {
        //~ ArrayList<Location> locs = new ArrayList<Location>();
        
        //~ helpwithTwoAaway(locs, getDirection()+ Location.RIGHT );
        //~ helpwithTwoAaway(locs, getDirection()+ Location.LEFT);
        
        //~ if(locs.isEmpty()) return super.getMoveLocations();
        

        //~ return locs;
    //~ }
    
    //~ private void helpwithTwoAaway(ArrayList<Location> locs, int direction){
		//~ Grid<Actor> gr = getGrid();
		
		//~ Location loc = getLocation();
		//~ Location next = loc.getAdjacentLocation(direction);
		
		//~ if(gr.isValid(next) && gr.get(next) == null){
			//~ Location twoAway = next.getAdjacentLocation(direction);
			//~ if(gr.isValid(twoAway) && gr.get(twoAway) == null){
				//~ locs.add(twoAway);
			//~ }
		//~ }
	//~ }
    
    //~ public Location selectMoveLocation(ArrayList<Location> locs){
		//~ if(locs.size() ==2) {
			//~ int rand = (int)(Math.random() *2 +1);
			//~ if(rand ==1) return locs.get(0);
			//~ else return locs.get(1);
		//~ }
		//~ else return locs.get(0);
	//~ }
}
