import java.util.Scanner;

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
	}
	
	public static void main(String[] args){
		Yahtzee yz = new Yahtzee();
		yz.run();
	}
	
	public void run(){
		printHeader();
		promptUser();
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
		String player1 = Prompt.getString("Player 1, please enter your first name ");
		String player2 = Prompt.getString("Player 2, please enter your first name ");
		do{
			Prompt.getString("Let's see who will go first. " + player1+", please hit enter to roll the dice");
			Prompt.getString(player2 + " it's your turn. Please hit enter to roll the dice");
		}while(player1Roll == player2Roll);
	}
}
