/**
 * PigGame.java - The game includes an option to run a simulation to 
 * calculate the probabilities of different outcomes when the computer 
 * "holds at 20" points, or compete against the computer of who wll reach
 * 100 first by rolling a dice.
 * 
 *	The game rules are as follows: 
 *	   - The player can either roll a die or hold their score.
 *     - Rolling a 2 through 6 adds to the turn's total. Rolling a 1 resets 
 *       the turn's score to zero and ends the turn.
 *	   - Holding adds the turn's total to the player's score and switches 
 * 		 turns.
 *     - The computer rolls automatically until it reaches at least 20 points 
 *       or rolls a 1.
 *	   - The first player to reach 100 points wins.
 * 
 * The simulation performs many turns with the computer holding at 20 and 
 * calculates the probabilities of ending a turn with 0, 20, 21, 22, 23,
 * 24, and 25
 *
 *	@author	Banan Badran
 *	@since	September 20, 2024
 */
public class PigGame {
	private int playerTotal;		// player's total score
	private int computerTotal;		// computer's total score
	private final int HOLD_SCORE;	//score computr must hold at when rolling
	private static final int WINNING_SCORE = 100;	// score needed to win
	private Dice dice;				// instance of the Dice class
	
	public PigGame() {
		playerTotal = computerTotal =0;
		HOLD_SCORE = 20;
		dice = new Dice();
	}
	
	public static void main(String[] args){
		PigGame pg = new PigGame();
		pg.runIt();
	}
	
	/**
	 * Prompts the user to either play the Pig game against the computer 
	 * or run statistical analysis, then calls the approporiate method
	 * based on the user's choice.
	 */
	public void runIt(){
		printIntroduction();
		char choice = Prompt.getChar("Play game or Statistics (p or s)");
		if((choice+ " ").equalsIgnoreCase("s ") )
			statistics();
		else 
			play();
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
	
	/**
	 * Runs the simulation, Prompts the user for the number of simulations,
	 * then calculates the probability of how often the computer ends its
	 * turn with 0, 20, 21, 22, 23, 24, and 25 then displays the estimated 
	 * probabilities in the terminal window.
	 */
	public void statistics() {
		double probOf1 = 0.0;
		double probOf20 = 0.0;
		double probOf21 = 0.0;
		double probOf22 = 0.0;
		double probOf23 = 0.0;
		double probOf24 = 0.0;
		double probOf25 = 0.0;
		
		System.out.println("\nRun statistical analysis - \"Hold at 20\"\n");
		int numChosen  = Prompt.getInt("Number of turns" ,1000 , 10000000);
		
		for (int i =0; i < numChosen; i++) {
			int turnScore = staTurn();
			if(turnScore == 0 )
				probOf1++;
			else if(turnScore == 20)
				probOf20++;
			else if(turnScore == 21)
				probOf21++;
			else if(turnScore == 22)
				probOf22++;
			else if(turnScore == 23)
				probOf23++;
			else if(turnScore == 24)
				probOf24++;
			else if(turnScore == 25)
				probOf25++;
		}
		
		System.out.println("\nScore\tEstimated Probability");
		System.out.printf("%-2d\t%-1.6f\n" ,0 ,probOf1/numChosen );
		System.out.printf("%-2d\t%-1.6f\n" ,20 ,probOf20/numChosen );
		System.out.printf("%-2d\t%-1.6f\n" ,21 ,probOf21/numChosen );
		System.out.printf("%-2d\t%-1.6f\n" ,22 ,probOf22/numChosen );
		System.out.printf("%-2d\t%-1.6f\n" ,23 ,probOf23/numChosen );
		System.out.printf("%-2d\t%-1.6f\n" ,24 ,probOf24/numChosen );
		System.out.printf("%-2d\t%-1.6f\n\n" ,25 ,probOf25/numChosen );
	}
	
	/**
	 *  Simulates one turn for the computer, where it rolls the dice until
	 *  the turn score reaches 20+ or it rolls a 1.
	 * 
	 *  @return The total score in the turn, or 0 if a 1 was rolled.
	 */
	public int staTurn(){
		int valuesAdded =0;
		do{
			dice.roll();
			valuesAdded += dice.getValue();
		}while(dice.getValue() != 1 && valuesAdded < HOLD_SCORE );
		if(valuesAdded >= 20 && valuesAdded <= 25)
			return valuesAdded;
		return 0;
	}
	
	/**
	 * Has a while loop which alternates turns between the player and the 
	 * computer until one of them reaches 100 points. Prints a congrats 
	 * message to the winner
	 */
	public void play(){
		while(playerTotal < WINNING_SCORE && computerTotal < WINNING_SCORE) {
			System.out.println("\n\n*** USER TURN ***");
			humanTurn();
			System.out.println("Your total score is " + playerTotal);
			if(playerTotal < WINNING_SCORE) {
				System.out.println("\n\n*** COMPUTER TURN ***\n");
				computerTurn();
				System.out.println("Computer total score: " + computerTotal);
			}
		}
		if(playerTotal >= WINNING_SCORE )
			System.out.println("\nCongratulations!!! YOU WON!!!");
		if(computerTotal >= WINNING_SCORE) 
			System.out.println("\nToo bad. COMPUTER WON.");
		System.out.println("\nThanks for playing the Pig Game!!!\n");
	}
	
	/**
	 * Manages the player's turn, allowing them to roll or hold. If they
	 * roll a 1, they lose their turn. If they hold, their turn score is
	 * added to their total, and the turn ends. Otherwise the process will
	 * keep repeating
	 */	
	public void humanTurn() {
		int playerTurnScore = 0;	// player's score after each turn
		boolean stop = false;	// control when player's turn end 
								// wether by holding or rolling 1
		while(!stop) {
			char choice= '?';
			while(!(choice+"").equalsIgnoreCase("r") && 
				  !(choice+"").equalsIgnoreCase("h")) {
				System.out.println("\nYour turn score: " + playerTurnScore);
				System.out.println("Your total score: " + playerTotal);
				choice = Prompt.getChar("(r)oll or (h)old ");
			} 
			
			if( (choice+"").equalsIgnoreCase("r") ){
				rollNPrint("\nYou ROLL ");
				if(dice.getValue() == 1) {
					System.out.println("\nYou LOSE your turn");
					stop = true;
				}
				else
					playerTurnScore += dice.getValue();
			}
			else {
				System.out.println("\nYou HOLD");
				playerTotal += playerTurnScore;
				stop = true;
			}
		}
	}
	
	/**
	 * Simulates the computer's turn. The computer rolls until its turn 
	 * score reaches 20+ or it rolls a 1, which ends the turn.
	 */
	public void computerTurn() {
		int compTurnScore = 0; 	// computer's score after each turn
		boolean stop = false;	// control when computer's turn end 
								// wether by holding or rolling 1
		while(!stop) {				
			System.out.println("computer's turn score: " + compTurnScore);
			System.out.println("computer's total score: " + computerTotal);
			Prompt.getChar("Press enter for computer turn ");
			if(compTurnScore < HOLD_SCORE ) {
				rollNPrint("\nComputer will ROLL\n");
				if(dice.getValue() == 1) {
					System.out.println("\nComputer loses turn");
					stop =true;
				}
				else 
					compTurnScore += dice.getValue();
			}
			else {
				System.out.println("\nComputer will HOLD");
				computerTotal += compTurnScore;
				stop = true;
			}
		}
	}
	
	/**
	 * Rolls the dice, displays the result, and processes it for either
	 * the player's or computer's turn.
	 * @param str The string to be printed before rolling the dice.
	 */
	public void rollNPrint(String str){
		System.out.println(str);
		dice.roll();
		dice.printDice();
	}
}
