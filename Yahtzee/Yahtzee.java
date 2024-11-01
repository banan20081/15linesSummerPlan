/**
 * The class is responsible for running the Yahtzee game, it gets the 
 * players names and determines who is playing, its responsible for printing 
 * and calling the methods responsible for the calculation and printing 
 * it also determines who won afte the game ends
 * @author Banan Badran
 * @since October 23, 2024
 */

public class Yahtzee{	
	// value rolled to determine who's going first, value is for player1
	private int player1Roll;
	// value rolled to determine who's going first, value is for player2
	private int player2Roll;
	
	/* YahtzeePlayer for players1 and player2 used for calling the correct 
	 * methods to do the calculation for each player
	 */
	private YahtzeePlayer[] yahtzeePlayer;	
	
	
	public Yahtzee(){
		yahtzeePlayer = new YahtzeePlayer[2];
		for( int i = 0; i < yahtzeePlayer.length; i++){
			yahtzeePlayer[i] = new YahtzeePlayer();
		}
	}
	
	public static void main(String[] args){
		Yahtzee yz = new Yahtzee();
		yz.run();
	}
	
	/**
	 * responsible for running the entire game
	 */
	public void run(){
		YahtzeeScoreCard player1 = yahtzeePlayer[0].getScoreCard();
		YahtzeeScoreCard player2 = yahtzeePlayer[1].getScoreCard();
		DiceGroup dgP1 = new DiceGroup(); // used for rolling player1 dice
		DiceGroup dgP2 = new DiceGroup(); // used for rolling player2 dice
		
		printHeader(); // printing the big intro
		promptUser();	// prompt for players names
		
		// print the table without numbers underneath
		printTable(player1, player2, false);
		//final because num of round is fixed and to avoid using magic number
		final int NUM_OF_ROUNDS = 13;
		for(int i =0; i < NUM_OF_ROUNDS; i++){
			System.out.println("\nRound " + (i+1) + " of 13 rounds\n");
			// to determine the order of playing the game 
			if(player1Roll > player2Roll){
				playerTurn(player1 ,player1, player2, yahtzeePlayer[0] , dgP1);
				playerTurn(player2, player1, player2, yahtzeePlayer[1] , dgP2);
			}
			else{
				playerTurn(player2 , player1, player2, yahtzeePlayer[1], dgP2);
				playerTurn(player1 ,player1, player2, yahtzeePlayer[0], dgP1);
			}
		}
		
		/* -- here it checks who won and prints the scores for each player 
		 * 	  and the winner
		 */
		int player1FinalScore =0 ;
		int player2FinalScore = 0;
		for(int i=0; i < NUM_OF_ROUNDS; i++){
			player1FinalScore += player1.getScoreArr()[i];
			player2FinalScore += player2.getScoreArr()[i];
		}
		
		System.out.printf("%-20s score total: %d\n", yahtzeePlayer[0].getName() ,player1FinalScore);
		System.out.printf("%-20s score total: %d\n\n", yahtzeePlayer[1].getName() ,player2FinalScore);
		if(player1FinalScore > player2FinalScore){
			System.out.println("Congratulation " + yahtzeePlayer[0].getName() + " YOU WON!!!\n");
		}
		else if(player2FinalScore > player1FinalScore){
			System.out.println("Congratulation " + yahtzeePlayer[1].getName() + " YOU WON!!!\n");
		}
		else if(player1FinalScore == player2FinalScore){ // if tie 
			System.out.println("Congratulation!! YOU BOTH TIED\n");
		}
	}
	
	/** 
	 * prints who is playing currently
	 */
	public void playerTurn(YahtzeeScoreCard playerCard,YahtzeeScoreCard player1, 
		YahtzeeScoreCard player2, YahtzeePlayer player , DiceGroup dg) {
		Prompt.getString("\n" + player.getName() + " it's your turn to play." +
									" Please hit enter to roll the dice ");
		dg.rollDice();
		dg.printDice();
		String holdVal ="";
		for(int i =1; i<3; i++) {
			holdVal = Prompt.getString("\nWhich di(c)e would you like to keep? Enter the " +
			"values you'd like to 'hold' without\nspaces. For examples, if you'd " +
			"like to 'hold' die 1, 2, and 5, enter 125\n(enter -1 if you'd like to" +
			" end the turn) ");
			if(holdVal.length() == 0){	//rolls everything
				dg.rollDice();
				dg.printDice();
			}
			// user won't roll again but rathe plug in the score onto the table 
			else if(holdVal.trim().equals("-1")){
				break;
			}
			else {
				String strHold = "";
				final int ascii1 = 49; // ASCII value of 1 
				final int ascii5 = 53; // ASCII value of 5
				for(int j =0; j< holdVal.length(); j++){
					if(holdVal.charAt(j) >= ascii1 && holdVal.charAt(j) <= ascii5 ){
						strHold += holdVal.charAt(j);
					}
				}
				/*rolls the dice while holding whatever is valid from the
				 * input that the player enterred, then print the rolled dice 
				 */
				dg.rollDice(strHold); 
				dg.printDice();
			}
		}
		/* print table with the number of each category for the user to 
		 * choose from to inpu thre score the got into the table
		 */
		printTable(player1, player2, true); 
		int choice =0;;
		boolean valid = false;;
		// player here will have to enter a category to input their scores in it
		while(!valid){
			choice = Prompt.getInt("\n"+ player.getName()+ " now you need to "+ 
			"make a choice. Pick a valid integer from the list above (1 -13) ");
			if(choice < 1 || choice > 13){
				System.out.print("invalid number, please try again");
			}
			else if(playerCard.changeScore(choice, dg)){
				valid = true;
			}
			else{
				System.out.print("category is taken, please try again");
			}
		}
		playerCard.getScore(choice); // gets the score
		printTable(player1, player2, false); // prints table with updated score
	}
	
	/**
	 * prints the table
	 * @param player1	YahtzeeScoreCard for first player, used to make 
	 * 					sure that the tables for each player don't switch
	 * @param player2	YahtzeeScoreCard for second player, used to make 
	 * 					sure that the tables for each player don't switch
	 *@param isPrintBottom	determines whether to print index of each category
	 */
	public void printTable(YahtzeeScoreCard player1, YahtzeeScoreCard player2, 
													boolean isPrintBottom){
		YahtzeeScoreCard ysc = new YahtzeeScoreCard();
		ysc.printCardHeader();
		player1.printPlayerScore(yahtzeePlayer[0]);
		player2.printPlayerScore(yahtzeePlayer[1]);
		if(isPrintBottom)
			ysc.printCardHeader2();
	}
	
	/**
	 * prints the into 
	 */
	public void printHeader() {
		System.out.println("\n");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("| WELCOME TO MONTA VISTA YAHTZEE!                                                    |");
		System.out.println("|                                                                                    |");
		System.out.println("| There are 13 rounds in a game of Yahtzee. In each turn, a player can roll his/her  |");
		System.out.println("| dice up to 3 times in order to get the desired combination. On the first roll, the |");
		System.out.println("| player rolls all five of the dice at once. On the second and third rolls, the      |");
		System.out.println("| player can roll any number of dice he/she wants to, including none or all of them, |");
		System.out.println("| trying to get a good combination.                                                  |");
		System.out.println("| The player can choose whether he/she wants to roll once, twice or three times in   |");
		System.out.println("| each turn. After the three rolls in a turn, the player must put his/her score down |");
		System.out.println("| on the scorecard, under any one of the thirteen categories. The score that the     |");
		System.out.println("| player finally gets for that turn depends on the category/box that he/she chooses  |");
		System.out.println("| and the combination that he/she got by rolling the dice. But once a box is chosen  |");
		System.out.println("| on the score card, it can't be chosen again.                                       |");
		System.out.println("|                                                                                    |");
		System.out.println("| LET'S PLAY SOME YAHTZEE!                                                           |");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("\n\n");
	}
	
	/**
	 * Prompts the user for their names, sets their names in the approporiate 
	 * yahtzeePlayer[] index, determines who is going first.
	 */
	public void promptUser(){
		yahtzeePlayer[0].setName(Prompt.getString("Player 1, please enter " + 
													"your first name "));
		yahtzeePlayer[1].setName(Prompt.getString("\nPlayer 2, please enter " +
													"your first name "));
		do{
			Prompt.getString("\nLet's see who will go first. " + 
			yahtzeePlayer[0].getName()+", please hit enter to roll the dice");
			DiceGroup dg = new DiceGroup();
			dg.rollDice();
			player1Roll = dg.getTotal();
			dg.printDice();
			Prompt.getString("\n" +yahtzeePlayer[1].getName() + " it's your" +
							" turn. Please hit enter to roll the dice");
			dg.rollDice();
			player2Roll = dg.getTotal();
			dg.printDice();
		}while(player1Roll == player2Roll);
		
		System.out.println(yahtzeePlayer[0].getName()+", you rolled a sum of " 
		+ player1Roll +", and "+ yahtzeePlayer[1].getName() + ", you rolled" +
											" a sum of " +player2Roll + ". ");
		if(player1Roll > player2Roll){
			System.out.println( yahtzeePlayer[0].getName()+", since your "+
									"sum was higher, you'll roll first.");
		}
		else {
			System.out.println(yahtzeePlayer[1].getName() + " since your "+
									"sum was higher, you'll roll first. ");
		}
	}
}
