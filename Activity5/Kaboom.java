import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;

import java.util.ArrayList;
import java.awt.Color;

public class Kaboom extends Actor{
	private int lifeTime;
	private final int THRESHOLD =3;
	
	public Kaboom(){
		setColor(null);
		lifeTime = THRESHOLD;
	}
	
	public void act(){
		if(lifeTime<=0) removeSelfFromGrid();
		else lifeTime--;
	}
}
