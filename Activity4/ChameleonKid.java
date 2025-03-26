import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;

import java.util.ArrayList;
import java.awt.Color;


public class ChameleonKid extends ChameleonCritter{
	public ArrayList<Actor> getActors() {
		ArrayList<Actor> actors = new ArrayList<Actor>();
		Grid<Actor> gr = getGrid();
		Location front = getLocation().getAdjacentLocation(getDirection());
		Location back = getLocation().getAdjacentLocation(getDirection() + Location.HALF_CIRCLE);

		boolean bothValid =  gr.isValid(front) && gr.isValid(back);
		if(bothValid){
			if(gr.get(front) != null && gr.get(back) != null){
				int chooseRand = (int)(Math.random() *2 +1);
				if(chooseRand ==1) actors.add(gr.get(front));
				else actors.add(gr.get(back));
			}
			else if(gr.get(front) != null){
				actors.add(gr.get(front));
			}
			else{
				if(gr.get(back) != null)
				actors.add(gr.get(back));
			}
		}
		else if(gr.isValid(front)) {
			if(gr.get(front) != null)
				actors.add(gr.get(front));
		}
		else {
			if(gr.get(back) != null)
			actors.add(gr.get(back));
		}
		
		return actors;
	}
	
	public void processActors(ArrayList<Actor> actors)
    {
		double DARKENING_FACTOR = 0.05;
		if(actors.isEmpty()){
			Color c = getColor();
			int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
			int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
			int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));

			setColor(new Color(red, green, blue));
		}else{
			int n = actors.size();
			if (n == 0)
				return;
			int r = (int) (Math.random() * n);

			Actor other = actors.get(r);
			setColor(other.getColor());
		}
    }
	
}
