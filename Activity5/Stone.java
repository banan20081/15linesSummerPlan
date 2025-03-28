import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import info.gridworld.actor.Rock;
import java.util.ArrayList;
import java.awt.Color;

public class Stone extends Rock{
	private int lifeTime;
	private final int THRESHOLD =3;
	
	public Stone(){
		setColor(null);
		lifeTime = (int)(Math.random()*200 +1);
	}
	
	public Stone(int life){
		setColor(null);
		lifeTime = life;
	}
	
	public void act(){
		if(lifeTime ==0) {
			Boulder bld = new Boulder();
			Grid<Actor> gr = getGrid();
			bld.putSelfInGrid(gr, getLocation());
		}
		else if(lifeTime <THRESHOLD) setColor(Color.GREEN);
		lifeTime--;
	}
}
