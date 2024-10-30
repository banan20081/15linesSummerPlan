
/**
 * @author Banan Badran
 * @since October 23,2024
 */
public class YahtzeeScoreCard {

	private int[] scores = new int[13];
	
	public YahtzeeScoreCard(){
		for(int i =0; i < scores.length; i++){
			scores[i] = -1;
		}
	}
	
	/**
	 *  Print the scorecard header
	 */
	public void printCardHeader() {
		System.out.println("\n");
		System.out.printf("\t\t\t\t\t       3of  4of  Fll Smll Lrg\n");
		System.out.printf("  NAME\t\t  1    2    3    4    5    6   Knd  Knd  Hse " +
						"Strt Strt Chnc Ytz!\n");
		System.out.printf("+----------------------------------------------------" +
						"---------------------------+\n");
	}
	
	public void printCardHeader2(){
		System.out.printf("     \t\t  1    2    3    4    5    6   7    8    9" +
						"    10   11   12   13\n");
	}
	
	/**
	 *  Prints the player's score
	 */
	public void printPlayerScore(YahtzeePlayer player) {
		System.out.printf("| %-12s |", player.getName());
		for (int i = 1; i < 14; i++) {
			if (getScore(i) > -1)
				System.out.printf(" %2d |", getScore(i));
			else System.out.printf("    |");
		}
		System.out.println();
		System.out.printf("+----------------------------------------------------" +
						"---------------------------+\n");
	}


	/**
	 *  Change the scorecard based on the category choice 1-13.
	 *
	 *  @param choice The choice of the player 1 to 13
	 *  @param dg  The DiceGroup to score
	 *  @return  true if change succeeded. Returns false if choice already taken.
	 */
	public boolean changeScore(int choice, DiceGroup dg) {
		if(scores[choice - 1] !=-1){
			return false;
		}
		return true;
	}
	
	/**
	 *  Change the scorecard for a number score 1 to 6
	 *
	 *  @param choice The choice of the player 1 to 6
	 *  @param dg  The DiceGroup to score
	 */
	public void numberScore(int choice, DiceGroup dg) {
		int count = 0;
		for(int i = 0; i < dg.getDiceArray().length; i++){
			if(dg.getDiceValue(i) == choice){
				count++;
			}
		}
		
		int choiceScore = choice * count;
		scores[choice -1] = choiceScore;
	}
	
	/**
	 *	Updates the scorecard for Three Of A Kind choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void threeOfAKind(DiceGroup dg) {
		if(is3OfAKind(dg)){
			scores[6] = dg.getTotal();
		}
	}
	
	public boolean is3OfAKind(DiceGroup dg){
		int count = 0; 
		for(int i = 1 ; i< 6; i++){
			for(int j = 0; j < dg.getDiceArray().length; j++){
				if(dg.getDiceValue(j) == i){
					count++;
				}
			}
			if(count >= 3){
				return true;
			}
		}
		return false;
	}
	
	public void fourOfAKind(DiceGroup dg) {
		int count = 0; 
		for(int i = 1 ; i< 6; i++){
			for(int j = 0; j < dg.getDiceArray().length; j++){
				if(dg.getDiceValue(j) == i){
					count++;
				}
			}
			if(count >= 4){
				scores[7] = dg.getTotal();
			}
		}
		
	}
	
	public void fullHouse(DiceGroup dg) {
		
	}
	
	public void smallStraight(DiceGroup dg) {
		int counter = 0; 
		for(int i =1; i < 6; i++ ){
			for(int j =0; j < dg.getDiceArray().length; j++){
				if(dg.getDiceValue(j) == i){
					counter++;
					break;
				}
			}
		}
		if(counter == 4){
			scores[9] =30;
		}
	} 
	
	public void largeStraight(DiceGroup dg) {
		int counter = 0; 
		for(int i =1; i < 6; i++ ){
			for(int j =0; j < dg.getDiceArray().length; j++){
				if(dg.getDiceValue(j) == i){
					counter++;
					break;
				}
			}
		}
		if(counter ==5){
			scores[10] = 40;
		}
		
	}
	
	public void chance(DiceGroup dg) {
		scores[11] = dg.getTotal();
	}
	
	public void yahtzeeScore(DiceGroup dg) {
		
		int valueToCheck = dg.getDiceValue(0);
		for(int i =1 ; i< dg.getDiceArray().length; i++){
			if(valueToCheck !=  dg.getDiceValue(i)){
				scores[12] =0;
			}
		}		
	}
	
	public int getScore(int index ){
		return scores[index-1];
	}
	
	public int[] getScoreArr(){
		return scores;
	}
}
