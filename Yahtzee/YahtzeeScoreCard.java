
/**
 * @author Banan Badran
 * @since October 23,2024
 */
public class YahtzeeScoreCard {

	private int[] scores = new int[13];
	private boolean[] usedCategories = new boolean[13];
	private int valRepeated;
	
	public YahtzeeScoreCard(){
		for(int i =0; i < scores.length; i++){
			scores[i] = -1;
		}
		valRepeated = 0;
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
		
		switch(choice) {
			case 1: case 2: case 3: case 4: case 5: case 6: numberScore(choice, dg);break;
			case 7: threeOfAKind(dg); break;
			case 8: fourOfAKind(dg); break;
			case 9: fullHouse(dg); break;
			case 10: smallStraight(dg); break;
			case 11: largeStraight(dg); break;
			case 12: chance(dg); break;
			case 13: yahtzeeScore(dg); break;
			default: return false;
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
		if(isOFKind(3,dg)){
			setScore(dg.getTotal(), 7);
		}
		else 
			setScore(0,7);
	}
	
	public void fourOfAKind(DiceGroup dg) {
		if(isOFKind(4,dg))
			setScore(dg.getTotal(), 8);
		else
			setScore(0, 8);
		
	}
	
	public void fullHouse(DiceGroup dg) {
		boolean kind3Exist = false, kind2Exist =false ; 
		int[] tempArr = new int[6];
		for(int i = 0; i < dg.getDiceArray().length; i++){
			int temp = dg.getDiceArray()[i].getValue();
			tempArr[temp-1]++;
		}
		
		for(int i: tempArr){
			if(i ==3) kind3Exist = true;
			if(i ==2) kind2Exist = true;
		}
		
		if(kind3Exist && kind2Exist) setScore(25, 9);
		else setScore(0, 9);
	}
	
	public void smallStraight(DiceGroup dg) {
		if(isTypeStraight(4, dg)){
			setScore(30,10);
		}
		else{
			setScore(0,10);
		}
	} 
	
	public void largeStraight(DiceGroup dg) {
		if(isTypeStraight(5, dg))
			setScore(40,11);
		else
			setScore(0,11);
	}
	
	public void chance(DiceGroup dg) {
		setScore(dg.getTotal(), 12);
	}
	
	public void yahtzeeScore(DiceGroup dg) {
		if(isOFKind(5,dg))
			setScore(50,13);
		else
			setScore(0,13);
	}
	
	public int getScore(int index){
		return scores[index-1];
	}
	
	public void setScore(int score, int category) {
        if (!usedCategories[category - 1]) {
            scores[category - 1] = score;
            usedCategories[category - 1] = true;
        }
    }
	
	public int[] getScoreArr(){
		return scores;
	}
	
	public boolean isOFKind(int checkKind, DiceGroup dg){
		int[] tempArr = new int[6];
		for(int i = 0; i < dg.getDiceArray().length; i++){
			int temp = dg.getDiceArray()[i].getValue();
			tempArr[temp-1]++;
		}
		
		for(int i: tempArr){
			if(i >= checkKind) return true;
		}
		return false;
	}
	
	public boolean isTypeStraight(int straightCheck, DiceGroup dg){
		int counter = 0; 
		for(int i =1; i <= 6; i++ ){
			for(int j =0; j < dg.getDiceArray().length; j++){
				if(dg.getDiceValue(j) == i){
					counter++;
					break;
				}
			}
		}
		if(counter >= straightCheck){
			return true;
		}
		return false;
	}
}
