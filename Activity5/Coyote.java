import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import info.gridworld.actor.Rock;

import java.util.ArrayList;
import java.awt.Color;

public class Coyote extends Critter{
	private int steps;
	private int sleepTime;
	private boolean isSleep;
	
	public Coyote(){
		steps =0;
		sleepTime = 5;
		setColor(null);
		setDirection(dirChooser());
	}
	
	public void act(){
		Location loc = getLocation();
		Location next = loc.getAdjacentLocation(getDirection());
		Grid gr = getGrid();
		if(steps<5 && gr.isValid(next) && gr.get(next) == null){
			
		}
	}
	
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

///


public class Coyote extends Critter {
    private int steps; // Counts steps taken
    private boolean sleeping; // Indicates if the Coyote is in sleep mode
    private int sleepTimer; // Counts down the sleep duration

    public Coyote() {
        setColor(null);
        setRandomDirection();
        steps = 0;
        sleeping = false;
        sleepTimer = 0;
    }

    public void act() {
        if (sleeping) {
            sleepTimer--;
            if (sleepTimer == 0) {
                sleeping = false; // Wake up after 5 turns
            }
            return; // Skip turn while sleeping
        }

        if (walk()) { // If Coyote is still in the grid after walking
            sleep(); // Sleep for 5 turns
        }
    }

    private boolean walk() {
        Grid<Actor> grid = getGrid();
        if (grid == null) return false;

        for (int i = 0; i < 5; i++) { // Walk up to 5 steps
            Location next = getLocation().getAdjacentLocation(getDirection());

            if (!grid.isValid(next)) { // Hit the wall
                setRandomDirection();
                return true;
            }

            Actor neighbor = grid.get(next);
            if (neighbor instanceof Boulder) { // Encounter Boulder
                neighbor.removeSelfFromGrid();
                new Kaboom().putSelfInGrid(grid, next); // Replace with Kaboom
                removeSelfFromGrid(); // Remove Coyote
                return false;
            } else if (neighbor != null) { // Encounter another Actor
                return true;
            }

            moveTo(next);
        }

        return true;
    }

    private void sleep() {
        sleeping = true;
        sleepTimer = 5;

        Grid<Actor> grid = getGrid();
        if (grid == null) return;

        Location stoneLoc = getLocation().getAdjacentLocation(getDirection());
        if (grid.isValid(stoneLoc) && grid.get(stoneLoc) == null) {
            new Stone().putSelfInGrid(grid, stoneLoc);
        }

        setRandomDirection();
    }

    private void setRandomDirection() {
        int[] directions = {0, 45, 90, 135, 180, 225, 270, 315};
        setDirection(directions[(int)(Math.random() * 8)]);
    }
}

