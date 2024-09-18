/**
 *	The game of Pig.
 *	(Description here)
 *
 *	@author	
 *	@since	
 */
public class PigGame {
	private int playerTotal;
	private int computerTotal;
	
	public PigGame() {
		playerTotal = computerTotal =0;
	}
	
	public static void main(String[] args){
		PigGame pg = new PigGame();
		pg.runIt();
	}
	
	public void runIt(){
		printIntroduction();
		//~ while(playerTotal < 100 && computerTotal < 100) {
			
		//~ }
		humanTurn();
	}
	
	/**	Print the introduction to the game */
	public void printIntroduction() {
		System.out.println("\n");
		System.out.println("______ _         _____");
		System.out.println("| ___ (_)       |  __ \\");
		System.out.println("| |_/ /_  __ _  | |  \\/ __ _ _ __ ___   ___");
		System.out.println("|  __/| |/ _` | | | __ / _` | '_ ` _ \\ / _ \\");
		System.out.println("| |   | | (_| | | |_\\ \\ (_| | | | | | |  __/");
		System.out.println("\\_|   |_|\\__, |  \\____/\\__,_|_| |_| |_|\\___|");
		System.out.println("          __/ |");
		System.out.println("         |___/");
		System.out.println("\nThe Pig Game is human vs computer. Each takes a"
							+ " turn rolling a die and the first to score");
		System.out.println("100 points wins. A player can either ROLL or "
							+ "HOLD. A turn works this way:");
		System.out.println("\n\tROLL:\t2 through 6: add points to turn total, "
							+ "player's turn continues");
		System.out.println("\t\t1: player loses turn");
		System.out.println("\tHOLD:\tturn total is added to player's score, "
							+ "turn goes to other player");
		System.out.println("\n");
	}
	
	public void humanTurn() {
		System.out.println("*** USER TURN ***");
		char choice = Prompt.getChar("(r)oll or (h)old\t-> ");
		if( choice == 'r'){
			
		}
		else if( choice == 'h') {
			
		}
	}
	
	public void computerTurn() {
		System.out.println("*** COMPUTER TURN ***");
	}
	
}
