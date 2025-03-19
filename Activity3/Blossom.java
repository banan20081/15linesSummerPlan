
import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;

import java.awt.Color;

public class Blossom extends Flower{
	private int lifeTime;
	
	public Blossom(){
		super(Color.GREEN);
		lifeTime =10;
	}
	
	public Blossom(int lifeT){
		super(Color.GREEN);
		lifeTime = lifeT;
	}
	
	public void act() {
        super.act();
        lifeTime--;
        if (lifeTime <= 0) {
            removeSelfFromGrid(); // Remove when time is up
        }
    }
}
