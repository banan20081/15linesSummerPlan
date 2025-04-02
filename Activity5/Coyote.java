import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import info.gridworld.actor.Rock;

import java.util.ArrayList;
import java.awt.Color;

public class Coyote extends Critter{
	private int steps; 
	
	public Coyote(){
		setColor(null);
		setDirection(dirChooser());
	}
	
	public int dirChooser(){
		return (int) (Math.random() * 8) * 45;
	}
	
	public void makeMove(Location loc){
		Grid gr = getGrid();
		if(gr.get(loc) instanceof Boulder){
			Kaboom kbm = new Kaboom();
			//~ Grid<Actor> gr = getGrid();
			removeSelfFromGrid();
			kbm.putSelfInGrid(gr, getLocation());
			return;
		}
		// handle this in getMoveLocation
		//~ else if(gr.get(loc) instanceof Stone){
			//~ int dir;
			//~ Location adjLoc;
			//~ do{
				//~ dir = dirChooser();
				//~ adjLoc = getGrid().getAdjacentLocation(dir);
			//~ }while(dir == getDirection());
			
		//~ }
		
		
		if(steps >=5 || !gr.isValid(loc) || gr.get(loc)){
			if(steps >=5){
				//Stone st = new Stone();
				dropStone();
			}
			setDirection(dirChooser());
		}
	}
	
	public void dropStone() {
        Grid<Actor> grid = getGrid();
        if (grid == null) return;
        
        ArrayList<Location> emptyLocs = grid.getEmptyAdjacentLocations(getLocation());
        if (!emptyLocs.isEmpty()) {
            grid.put(emptyLocs.get((int) (Math.random() * emptyLocs.size())), new Stone());
        }
    }
}


//~ ///
//~ public class Coyote extends Critter {
    //~ private int steps;
    //~ private int sleepTime;
    //~ private int sleepCounter;

    //~ public Coyote() {
        //~ steps = 0;
        //~ sleepTime = 5;
        //~ sleepCounter = 0;
        //~ setColor(null);
        //~ setDirection(dirChooser());
    //~ }

    //~ public int dirChooser() {
		//~ do{
			//~ int rand= (int) (Math.random() * 8) * 45;
		//~ }while(getLocation().);
    //~ }

    //~ public ArrayList<Location> getMoveLocations() {
        //~ ArrayList<Location> locs = new ArrayList<>();
        //~ if (sleepCounter > 0) {
            //~ sleepCounter--;
            //~ return locs; // Stay in place while sleeping
        //~ }

        //~ Grid<Actor> gr = getGrid();
        //~ if (gr == null) return locs;
        
        //~ Location loc = getLocation();
        //~ for (int i = 1; i <= 5; i++) {
            //~ Location next = loc.getAdjacentLocation(getDirection());
            //~ if (!gr.isValid(next)) break;
            //~ Actor neighbor = gr.get(next);
            //~ if (neighbor instanceof Boulder) {
                //~ neighbor.removeSelfFromGrid(); // Boulder explodes
                //~ removeSelfFromGrid(); // Coyote is removed
                //~ return locs; 
            //~ }
            //~ if (neighbor != null) break;
            //~ locs.add(next);
            //~ loc = next;
        //~ }
        //~ return locs;
    //~ }

    //~ public Location selectMoveLocation(ArrayList<Location> locs) {
        //~ if (locs.isEmpty()) {
            //~ sleepCounter = sleepTime;
            //~ setDirection(dirChooser());
            //~ return getLocation();
        //~ }
        //~ return locs.get(locs.size() - 1); // Move in a straight line as far as possible
    //~ }

    //~ public void makeMove(Location loc) {
        //~ if (loc.equals(getLocation())) return; // Stay in place if sleeping
        
        //~ super.makeMove(loc);
        //~ steps++;
        
        //~ if (steps >= 5) { // Every two steps, drop a stone and change direction
            //~ steps = 0;
            //~ dropStone();
            //~ setDirection(dirChooser());
        //~ }
    //~ }

    //~ private void dropStone() {
        //~ Grid<Actor> gr = getGrid();
        //~ if (gr == null) return;
        
        //~ ArrayList<Location> emptyLocs = gr.getEmptyAdjacentLocations(getLocation());
        //~ if (!emptyLocs.isEmpty()) {
            //~ Location stoneLoc = emptyLocs.get((int) (Math.random() * emptyLocs.size()));
            //~ new Stone().putSelfInGrid(gr, stoneLoc);
        //~ }
    //~ }
}

//~ public class Coyote extends Critter{
	//~ private int steps;
	//~ private int sleepTime;
	//~ private boolean isSleep;
	
	//~ public Coyote(){
		//~ steps =0;
		//~ sleepTime = 5;
		//~ setColor(null);
		//~ setDirection(dirChooser());
	//~ }
	
	//~ public void act(){
		//~ Location loc = getLocation();
		//~ Location next = loc.getAdjacentLocation(getDirection());
		//~ Grid gr = getGrid();
		//~ if(steps<5 && gr.isValid(next) && gr.get(next) == null){
			
		//~ }
	//~ }
	
	//~ public int dirChooser(){
		//~ int dir =0;
		//~ int rand = (int)(Math.random()*8);
		//~ switch(rand){
			//~ case(0): dir =0;break;
			//~ case(1): dir =45;break;
			//~ case(2): dir =90;break;
			//~ case(3): dir =135;break;
			//~ case(4): dir =180;break;
			//~ case(5): dir =225;break;
			//~ case(6): dir =270;break;
			//~ case(7): dir =315;break;
		//~ }
		//~ return dir;
	//~ }
	
//~ }

//~ ///


//~ public class Coyote extends Critter {
    //~ private int steps;
    //~ private int sleepTime;
    //~ private boolean sleeping;
    
    //~ public Coyote() {
        //~ steps = 0;
        //~ sleepTime = 5;
        //~ sleeping = false;
        //~ setColor(null);
        //~ setDirection(dirChooser());
    //~ }
    
    //~ private int dirChooser() {
        //~ return (int) (Math.random() * 8) * 45;
    //~ }
    
    //~ public ArrayList<Location> getMoveLocations() {
        //~ ArrayList<Location> locs = new ArrayList<Location>();
        //~ if (sleeping) return locs;
        
        //~ Grid<Actor> grid = getGrid();
        //~ if (grid == null) return locs;
        
        //~ Location loc = getLocation();
        //~ Location next = loc.getAdjacentLocation(getDirection());
        
        //~ if (grid.isValid(next)) {
			//~ System.out.println("loction " +next);
            //~ Actor neighbor = grid.get(next);
            //~ if (neighbor == null || neighbor instanceof Rock) {
                //~ locs.add(next);
            //~ }
        //~ }
        //~ return locs;
    //~ }
    
    //~ public void makeMove(Location loc) {
        //~ if (sleeping) {
            //~ sleepTime--;
            //~ if (sleepTime == 0) {
                //~ sleeping = false;
                //~ sleepTime = 5;
            //~ }
            //~ return;
        //~ }
        
        //~ if (loc == null || loc.equals(getLocation())) {
            //~ setDirection(dirChooser());
            //~ steps = 0;
            //~ return;
        //~ }
        
        //~ Grid<Actor> grid = getGrid();
        //~ if (grid == null) return;
        
        //~ Actor nextActor = grid.get(loc);
        //~ if (nextActor instanceof Rock) {
            //~ nextActor.removeSelfFromGrid();
            //~ removeSelfFromGrid();
            //~ return;
        //~ }
        
        //~ moveTo(loc);
        //~ steps++;
        
        //~ if (steps >= 5 || getGrid().get(getLocation().getAdjacentLocation(getDirection())) != null) {
            //~ sleeping = true;
            //~ steps = 0;
            //~ dropStone();
            //~ setDirection(dirChooser());
        //~ }
        
        //~ Grid<Actor> grid = getGrid();
        //~ if (grid == null) return;

        //~ for (int i = 0; i < 5; i++) { // Walk up to 5 steps
            //~ Location next = getLocation().getAdjacentLocation(getDirection());

            //~ if (!grid.isValid(next)) { // Hit the wall
                //~ setDirection(dirChooser());
                //~ sleeping = true;
                //~ return;
            //~ }

            //~ Actor neighbor = grid.get(next);
            //~ if (neighbor instanceof Boulder) { // Encounter Boulder
                //~ neighbor.removeSelfFromGrid();
                //~ new Kaboom().putSelfInGrid(grid, next); // Replace with Kaboom
                //~ removeSelfFromGrid(); // Remove Coyote
                //~ sleeping =false;
                //~ return;
            //~ } else if (neighbor != null) { // Encounter another Actor
				//~ sleeping = true;
                //~ return;
            //~ }

            //~ moveTo(next);
		//~ }
    //~ }
    
    //~ public Location selectMoveLocation(ArrayList<Location> locs){
		//~ System.out.println(locs);
		//~ int n = locs.size();
        //~ if (n == 0)
            //~ return getLocation();
        //~ int r = (int) (Math.random() * n);
        //~ System.out.println("selected "+ locs.get(r));
        //~ return locs.get(r);
	//~ }
    
    //~ private void dropStone() {
        //~ Grid<Actor> grid = getGrid();
        //~ if (grid == null) return;
        
        //~ ArrayList<Location> emptyLocs = grid.getEmptyAdjacentLocations(getLocation());
        //~ if (!emptyLocs.isEmpty()) {
            //~ grid.put(emptyLocs.get((int) (Math.random() * emptyLocs.size())), new Rock());
        //~ }
    //~ }
//~ }

//~ ///


//~ public class Coyote extends Critter {
    //~ private int steps; // Counts steps taken
    //~ private boolean sleeping; // Indicates if the Coyote is in sleep mode
    //~ private int sleepTimer; // Counts down the sleep duration

    //~ public Coyote() {
        //~ setColor(null);
        //~ setRandomDirection();
        //~ steps = 0;
        //~ sleeping = false;
        //~ sleepTimer = 0;
    //~ }

    //~ public void act() {
        //~ if (sleeping) {
            //~ sleepTimer--;
            //~ if (sleepTimer == 0) {
                //~ sleeping = false; // Wake up after 5 turns
            //~ }
            //~ return; // Skip turn while sleeping
        //~ }

        //~ if (walk()) { // If Coyote is still in the grid after walking
            //~ sleep(); // Sleep for 5 turns
        //~ }
    //~ }

    //~ private boolean walk() {
        //~ Grid<Actor> grid = getGrid();
        //~ if (grid == null) return false;

        //~ for (int i = 0; i < 5; i++) { // Walk up to 5 steps
            //~ Location next = getLocation().getAdjacentLocation(getDirection());

            //~ if (!grid.isValid(next)) { // Hit the wall
                //~ setRandomDirection();
                //~ return true;
            //~ }

            //~ Actor neighbor = grid.get(next);
            //~ if (neighbor instanceof Boulder) { // Encounter Boulder
                //~ neighbor.removeSelfFromGrid();
                //~ new Kaboom().putSelfInGrid(grid, next); // Replace with Kaboom
                //~ removeSelfFromGrid(); // Remove Coyote
                //~ return false;
            //~ } else if (neighbor != null) { // Encounter another Actor
                //~ return true;
            //~ }

            //~ moveTo(next);
        //~ }

        //~ return true;
    //~ }

    //~ private void sleep() {
        //~ sleeping = true;
        //~ sleepTimer = 5;

        //~ Grid<Actor> grid = getGrid();
        //~ if (grid == null) return;

        //~ Location stoneLoc = getLocation().getAdjacentLocation(getDirection());
        //~ if (grid.isValid(stoneLoc) && grid.get(stoneLoc) == null) {
            //~ new Stone().putSelfInGrid(grid, stoneLoc);
        //~ }

        //~ setRandomDirection();
    //~ }

    //~ private void setRandomDirection() {
        //~ int[] directions = {0, 45, 90, 135, 180, 225, 270, 315};
        //~ setDirection(directions[(int)(Math.random() * 8)]);
    //~ }
//~ }
