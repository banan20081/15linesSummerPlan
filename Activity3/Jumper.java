import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;
import info.gridworld.actor.Flower;

public class Jumper extends Bug {
    private int maxSteps;
    private int currentSteps;
    private int blossomLifetime;

    public Jumper() {
        setColor(Color.BLUE);
        maxSteps = 3;
        currentSteps = 0;
        blossomLifetime = 10;
        
    }
    
    public Jumper(int maxSTeps){
		setColor(Color.BLUE);
        maxSteps = maxSTeps;
        currentSteps = 0;
        blossomLifetime = 10;
	}

    public void act() {
		Location oldLocation = getLocation();
        if (canJump()) {
            
            jump();
            leaveBlossom(oldLocation);
            currentSteps++;
        } else {
			leaveBlossom(oldLocation);
            moveOneStep();
            leaveBlossom(oldLocation);
            currentSteps = 0;
        }
        
        // if you dont want to have a restricted amount number of steps before 
        //turning remove this 
        if (currentSteps >= maxSteps) {
            turn();
           // leaveBlossom(oldLocation);
            currentSteps = 0;
            
        }
    }

    public void jump() {
        Grid<Actor> gr = getGrid();
        if (gr == null) return;
        
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Location twoAway = next.getAdjacentLocation(getDirection());
        
        if (gr.isValid(twoAway) && gr.get(twoAway) == null) {
            moveTo(twoAway);
        } else if (gr.isValid(next) && gr.get(next) == null) {
            moveTo(next);
        } else {
            turn();
        }
    }

    public boolean canJump() {
        Grid<Actor> gr = getGrid();
        if (gr == null) return false;
        
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Location twoAway = next.getAdjacentLocation(getDirection());
        
        if (!gr.isValid(next) || !gr.isValid(twoAway)) return false;
        
        Actor oneStepActor = gr.get(next);
        Actor twoStepActor = gr.get(twoAway);
        
        return (oneStepActor == null || oneStepActor instanceof Flower || oneStepActor instanceof Rock) &&
               (twoStepActor == null);
    }

    private void moveOneStep() {
        Grid<Actor> gr = getGrid();
        if (gr == null) return;
        
        Location next = getLocation().getAdjacentLocation(getDirection());
        if (gr.isValid(next) && gr.get(next) == null) {
            moveTo(next);
        } else {
            turn();
        }
    }

    private void leaveBlossom(Location oldLocation) {
        Grid<Actor> gr = getGrid();
        if (gr == null || oldLocation == null) return;
        
        if (gr.get(oldLocation) == null) {
            Blossom blossom = new Blossom(blossomLifetime);
            blossom.putSelfInGrid(gr, oldLocation);
        }
    }
}
