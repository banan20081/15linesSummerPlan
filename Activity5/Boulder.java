import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;

import java.util.ArrayList;
import java.awt.Color;

public class Boulder extends Actor{
	private int lifeTime;
	private final int THRESHOLD =3;
	
	public Boulder(){
		setColor(null);
		lifeTime = (int) (Math.random() *200 +1);
	}
	
	public Boulder(int life){
		setColor(null);
		lifeTime = life;
	}
	
	public void act(){
		if(lifeTime==0) {
			Grid<Actor> gr = getGrid();
			Kaboom kb = new Kaboom();
			kb.putSelfInGrid(gr, getLocation());
		}
		else if(lifeTime <THRESHOLD) setColor(Color.RED);
		lifeTime--;
	}
}
