import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;

import java.util.ArrayList;
import java.awt.Color;

public class Coyote extends Critter{
	private int steps;
	
	public Coyote(){
		steps =0;
		setColor(null);
		setDirection(dirChooser());
	}
	
	//~ public void act(){
		//~ Location loc = getLocation();
		//~ Location next = loc.getAdjacentLocation(getDirection());
		//~ Grid gr = getGrid();
		//~ if(steps<5 && gr.isValid(next) && gr.get(next) == null){ walk();}
		//~ else if(steps>=5) // dropp stone in adjacent OPEN location 
		//~ else if(!gr.isValid(next) && gr.get(next) != null){
			
		//~ }
	//~ }
	
	public int dirChooser(){
		int dir =0;
		int rand = (int)(Math.random()*8);
		switch(rand){
			case(0): dir =0;break;
			case(1): dir =45;break;
			case(2): dir =90;break;
			case(3): dir =135;break;
			case(4): dir =180;break;
			case(5): dir =225;break;
			case(6): dir =270;break;
			case(7): dir =315;break;
		}
		
		return dir;
	}
}
