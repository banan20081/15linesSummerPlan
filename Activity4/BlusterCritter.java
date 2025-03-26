import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;

import java.util.ArrayList;
import java.awt.Color;

public class BlusterCritter extends Critter{
	private int cCourage;
	
	public BlusterCritter(int c){
		super();
		cCourage = c;
	}
	
	public ArrayList<Actor> getActors(){
		ArrayList<Actor> actors = new ArrayList<Actor>();
		Location loc = getLocation();
		for(int r = loc.getRow() - 2; r <= loc.getRow() + 2; r++ ){
			for(int c = loc.getCol() - 2; c <= loc.getCol() + 2; c++) {
				Location tempLoc = new Location(r,c);
				if(getGrid().isValid(tempLoc)) {
					Actor a = getGrid().get(tempLoc);
					if(a != null && a != this)
					actors.add(a);
				}
			}
		}
		return actors;
	}
	
	public void processActors(ArrayList<Actor> actors){
		int count=0;
		for (Actor a : actors) {
			if (a instanceof Critter)
				count++;
		}

		if(count< cCourage) brighten();
		else darken();
	}
    
    public void brighten(){
		double brightening_Factor = 0.1;
		Color c = getColor();
			int red = (int) (c.getRed() * (1 + brightening_Factor));
			int green = (int) (c.getGreen() * (1 + brightening_Factor));
			int blue = (int) (c.getBlue() * (1 + brightening_Factor));
	}
	
    public void darken(){
		double darkening_Facto= 0.1;
		Color c = getColor();
		int red = (int) (c.getRed() * (1 - darkening_Facto));
		int green = (int) (c.getGreen() * (1 - darkening_Facto));
		int blue = (int) (c.getBlue() * (1 - darkening_Facto));
			
		setColor(new Color(red, green, blue));
	}
}
