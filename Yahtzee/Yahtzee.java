/**
 * 
 * @author Banan Badran
 * @since October 23, 2024
 */

public class Yahtzee{	
	private int player1Roll;
	private int player2Roll;
	
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
	
	public void run(){
		YahtzeeScoreCard player1 = yahtzeePlayer[0].getScoreCard();
		YahtzeeScoreCard player2 = yahtzeePlayer[1].getScoreCard();
		DiceGroup dgP1 = new DiceGroup();
		DiceGroup dgP2 = new DiceGroup();
		
		int counter = 0;
		printHeader();
		promptUser();
		
		printTable(player1, player2, false);
		final int NUM_OF_ROUNDS = 13;
		for(int i =0; i < NUM_OF_ROUNDS; i++){
			System.out.println("\nRound " + (i+1) + " of 13 rounds\n");
			if(player1Roll > player2Roll){
				playerTurn(player1 ,player1, player2, yahtzeePlayer[0] , dgP1);
				playerTurn(player2, player1, player2, yahtzeePlayer[1] , dgP2);
			}
			else{
				playerTurn(player2 , player1, player2, yahtzeePlayer[1], dgP2);
				playerTurn(player1 ,player1, player2, yahtzeePlayer[0], dgP1);
			}
		}
		int player1FinalScore =0 ;
		int player2FinalScore = 0;
		for(int i=0; i < NUM_OF_ROUNDS; i++){
			player1FinalScore += player1.getScoreArr()[i];
			player2FinalScore += player2.getScoreArr()[i];
		}
		
		System.out.printf("%s\t\t score total: %d\n", yahtzeePlayer[0].getName() ,player1FinalScore);
		System.out.printf("%s\t\t score total: %d\n\n", yahtzeePlayer[1].getName() ,player2FinalScore);
		if(player1FinalScore > player2FinalScore){
			System.out.println("Congratulation " + yahtzeePlayer[0].getName() + " YOU WON!!!\n");
		}
		else if(player2FinalScore > player1FinalScore){
			System.out.println("Congratulation " + yahtzeePlayer[1].getName() + " YOU WON!!!\n");
		}
		else if(player1FinalScore == player2FinalScore){
			System.out.println("Congratulation!! YOU BOTH TIED\n");
		}
		
	}
	
	public void playerTurn(YahtzeeScoreCard playerCard, YahtzeeScoreCard player1, YahtzeeScoreCard player2, YahtzeePlayer player , DiceGroup dg) {
		Prompt.getString("\n" + player.getName() + " it's your turn to play. Please hit enter to roll the dice ");
		dg.rollDice();
		dg.printDice();
		String holdVal ="";
		for(int i =1; i<3; i++) {
			holdVal = Prompt.getString("\nWhich di(c)e would you like to keep? Enter the " +
			"values you'd like to 'hold' without\nspaces. For examples, if you'd " +
			"like to 'hold' die 1, 2, and 5, enter 125\n(enter -1 if you'd like to" +
			" end the turn) ");
			if(holdVal.length() == 0){
				dg.rollDice();
				dg.printDice();
			}
			else if(holdVal.trim().equals("-1")){
				break;
			}
			else {
				String temp = "";
				final int ascii1 = 49;
				final int ascii5 = 53;
				for(int j =0; j< holdVal.length(); j++){
					if(holdVal.charAt(j) >= ascii1 && holdVal.charAt(j) <= ascii5 ){
						temp += holdVal.charAt(j);
					}
				}
				dg.rollDice(temp);
				dg.printDice();
			}
		}
		printTable(player1, player2, true);
		int choice =0;;
		boolean valid = false;;
		while(!valid){
			choice = Prompt.getInt("\n"+ player.getName()+ " now you need to make a choice. Pick a valid integer from the list above (1 -13) ");
			if(choice < 1 || choice > 13){
				System.out.println("invalid");
			}
			else if(playerCard.changeScore(choice, dg)){
				valid = true;
			}
			else{
				System.out.print("taken");
			}
		}
			playerCard.getScore(choice);
			printTable(player1, player2, false);
		
	}
	
	public void printTable(YahtzeeScoreCard player1, YahtzeeScoreCard player2, boolean isPrintBottom){
		YahtzeeScoreCard ysc = new YahtzeeScoreCard();
		ysc.printCardHeader();
		player1.printPlayerScore(yahtzeePlayer[0]);
		player2.printPlayerScore(yahtzeePlayer[1]);
		if(isPrintBottom)
			ysc.printCardHeader2();
	}
	
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
	
	public void promptUser(){
		yahtzeePlayer[0].setName(Prompt.getString("Player 1, please enter your first name "));
		yahtzeePlayer[1].setName(Prompt.getString("\nPlayer 2, please enter your first name "));
		do{
			Prompt.getString("\nLet's see who will go first. " + yahtzeePlayer[0].getName()+", please hit enter to roll the dice");
			DiceGroup dgP1 = new DiceGroup();
			dgP1.rollDice();
			player1Roll = dgP1.getTotal();
			dgP1.printDice();
			Prompt.getString("\n" +yahtzeePlayer[1].getName() + " it's your turn. Please hit enter to roll the dice");
			dgP1.rollDice();
			player2Roll = dgP1.getTotal();
			dgP1.printDice();
		}while(player1Roll == player2Roll);
		
		System.out.println(yahtzeePlayer[0].getName() +", you rolled a sum of " + player1Roll +", and "+ yahtzeePlayer[1].getName() + ", you rolled a sum of " +player2Roll + ". ");
		if(player1Roll > player2Roll)
			System.out.println( yahtzeePlayer[0].getName()+", since your sum was higher, you'll roll first.");
		else
			System.out.println(yahtzeePlayer[1].getName() + " since your sum was higher, you'll roll first. ");
	}
}
