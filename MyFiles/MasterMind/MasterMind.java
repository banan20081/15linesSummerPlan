/**
 *	Plays the game of MasterMind.
 *	< MasterMind is a code-breaking game where the user must guess a 4-character 
 * 	code ("master code") consisting of letters from A-F. 
 * 	The computer generates this master code, and the user has 10 attempts to
 * 	guess it. After each guess, the user receives the number of letters palced
 *	in the correct position and how many letters exist but not in correct 
 * 	position (partially correct). >
 * 
 *	@author	Banan Badran
 *	@since	October 1st, 2024
 */

public class MasterMind {
 
	private boolean reveal;			// true = reveal the master combination
	private PegArray[] guesses;		// the array of guessed peg arrays
	private PegArray master;		// the master (key) peg array
	
	// Constants
	private final int PEGS_IN_CODE = 4;		// Number of pegs in code
	private final int MAX_GUESSES = 10;		// Max number of guesses
	private final int PEG_LETTERS = 6;		// Number of different letters on pegs
											// 6 = A through F

	public MasterMind() {	
		reveal = false;	// by default doesn't reveal the master code
		guesses = new PegArray[MAX_GUESSES];
		master = new PegArray(PEGS_IN_CODE);
		
		// Initialize each guess slot with a new PegArray
		for(int i =0; i< guesses.length;i++){
			guesses[i] = new PegArray(PEGS_IN_CODE);
		}
	}
	
	public static void main(String[] args){
		MasterMind mm = new MasterMind();
		mm.run();	// starts the game
	}
	
	/**
	 * Runs the entire game by first printing the introduction, generating the 
	 * master code, and printing the board. It asks the user for input repeatedly 
	 * until they solve the code or reach the maximum number of guesses (10).
	 * prints a message either ( congrats or oops ) depending on whether the player
	 * won or not.
	 */
	public void run(){
		printIntroduction();
		Prompt.getString("Hit the Enter key to start the game");
		makeMasterCode();	// generates the master code
		int tries = 0;		// amount of times the user enters their guess
		String choice ="";
		printBoard();	// the plain board before user enters their guess (1st time)
		do{
			System.out.printf("\nGuess %d\n\n" , tries+1);
			choice = getGuess();	// gets the user input
			// Store the guessed letters in the guesses array for this attempt
			for( int i =0; i < PEGS_IN_CODE; i++ ){ 
				guesses[tries].getPeg(i).setLetter(choice.charAt(i));
			}
			//used to check exact matches to determine if player ever wins
			int exactMatch = guesses[tries].getExactMatches(master);
			guesses[tries].getPartialMatches(master);	// get partial matches
			if(exactMatch == PEGS_IN_CODE){			// if the player won
				reveal = true;
				printBoard();
				System.out.printf("\nNice work! You found the master code in %d " +
									"guesses.\n\n", tries+1);
				break;
			}
			// if player didn't win and is out of turns
			else if(exactMatch != PEGS_IN_CODE && tries == MAX_GUESSES -1) {
				reveal = true;
				printBoard(); 
				System.out.printf("\nOops. You were unable to find the solution " +
									"in %d guesses.", MAX_GUESSES);
				break;
			}
			tries++;
			printBoard();
		}while(!reveal && tries < MAX_GUESSES);
	}
	
	/**
	 * Generates the master code, in the range A-F,  Which is kept hidden from 
	 * the player until they win or run out of turns, and is used to check their 
	 * guesses.
	 */
	public void makeMasterCode(){
		for(int i = 0; i < PEGS_IN_CODE; i++){
			char masterPeg = (char) ('A' + (int) (Math.random() * PEG_LETTERS));
			// Set the peg letter in the master code
			master.getPeg(i).setLetter(masterPeg);
		}
	}
	
	/**
	 * gets the user input ( one turn) , if input was invalid the program will keep asking for
	 * it until its provided with a string of 4 letters between A-F
	 * @return choice	the user's guess
	 */
	public String getGuess(){
		String choice = "";	// user input
		do {
			choice = Prompt .getString("Enter the code using (A,B,C,D,E,F)." +
				" For example, ABCD or abcd from left-to-right").toUpperCase();
			if(!isValid(choice)){
				System.out.println("ERROR: Bad input, try again.");
			}
		}while(!isValid(choice));
		return choice;
	}
	
	/**
	 * checks whether the user input is valid or not
	 * @param choice	user input
	 * @return true		if user input is valid ( 4 characters, betweeen A-F )
	 * @return false	if user input isn't valid(not 4 characters, not betweeen A-F)
	 */
	public boolean isValid(String choice){
		if(choice.length() != 4) { return false; }
		for(int i =0; i< choice.length(); i ++ ){
			if (choice.charAt(i) < 'A' || choice.charAt(i) > 'A' + PEG_LETTERS-1) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 *	Print the introduction screen
	 */
	public void printIntroduction() {
		System.out.println("\n");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("| ___  ___             _              ___  ___ _             _                       |");
		System.out.println("| |  \\/  |            | |             |  \\/  |(_)           | |                      |");
		System.out.println("| | .  . |  __ _  ___ | |_  ___  _ __ | .  . | _  _ __    __| |                      |");
		System.out.println("| | |\\/| | / _` |/ __|| __|/ _ \\| '__|| |\\/| || || '_ \\  / _` |                      |");
		System.out.println("| | |  | || (_| |\\__ \\| |_|  __/| |   | |  | || || | | || (_| |                      |");
		System.out.println("| \\_|  |_/ \\__,_||___/ \\__|\\___||_|   \\_|  |_/|_||_| |_| \\__,_|                      |");
		System.out.println("|                                                                                    |");
		System.out.println("| WELCOME TO MONTA VISTA MASTERMIND!                                                 |");
		System.out.println("|                                                                                    |");
		System.out.println("| The game of MasterMind is played on a four-peg gameboard, and six peg letters can  |");
		System.out.println("| be used.  First, the computer will choose a random combination of four pegs, using |");
		System.out.println("| some of the six letters (A, B, C, D, E, and F).  Repeats are allowed, so there are |");
		System.out.println("| 6 * 6 * 6 * 6 = 1296 possible combinations.  This \"master code\" is then hidden     |");
		System.out.println("| from the player, and the player starts making guesses at the master code.  The     |");
		System.out.println("| player has 10 turns to guess the code.  Each time the player makes a guess for     |");
		System.out.println("| the 4-peg code, the number of exact matches and partial matches are then reported  |");
		System.out.println("| back to the user. If the player finds the exact code, the game ends with a win.    |");
		System.out.println("| If the player does not find the master code after 10 guesses, the game ends with   |");
		System.out.println("| a loss.                                                                            |");
		System.out.println("|                                                                                    |");
		System.out.println("| LET'S PLAY SOME MASTERMIND!                                                        |");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("\n");
	}
	
	/**
	 *	Print the peg board to the screen
	 */
	public void printBoard() {
		// Print header
		System.out.print("+--------+");
		for (int a = 0; a < PEGS_IN_CODE; a++) System.out.print("-------+");
		System.out.println("---------------+");
		System.out.print("| MASTER |");
		for (int a = 0; a < PEGS_IN_CODE; a++)
			if (reveal)
				System.out.printf("   %c   |", master.getPeg(a).getLetter());
			else
				System.out.print("  ***  |");
		System.out.println(" Exact Partial |");
		System.out.print("|        +");
		for (int a = 0; a < PEGS_IN_CODE; a++) System.out.print("-------+");
		System.out.println("               |");
		// Print Guesses
		System.out.print("| GUESS  +");
		for (int a = 0; a < PEGS_IN_CODE; a++) System.out.print("-------+");
		System.out.println("---------------|");
		for (int g = 0; g < MAX_GUESSES - 1; g++) {
			printGuess(g);
			System.out.println("|        +-------+-------+-------+-------+---------------|");
		}
		printGuess(MAX_GUESSES - 1);
		// print bottom
		System.out.print("+--------+");
		for (int a = 0; a < PEGS_IN_CODE; a++) System.out.print("-------+");
		System.out.println("---------------+");
	}
	
	/**
	 *	Print one guess line to screen
	 *	@param t	the guess turn
	 */
	public void printGuess(int t) {
		System.out.printf("|   %2d   |", (t + 1));
		// If peg letter in the A to F range
		char c = guesses[t].getPeg(0).getLetter();
		if (c >= 'A' && c <= 'F')
			for (int p = 0; p < PEGS_IN_CODE; p++)
				System.out.print("   " + guesses[t].getPeg(p).getLetter() + "   |");
		// If peg letters are not A to F range
		else
			for (int p = 0; p < PEGS_IN_CODE; p++)
				System.out.print("       |");
		System.out.printf("   %d      %d    |\n",
							guesses[t].getExact(), guesses[t].getPartial());
	}

}
