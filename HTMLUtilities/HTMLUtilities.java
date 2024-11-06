import java.util.ArrayList;

/**
 *	Utilities for handling HTML
 *
 *	@author	Banan Badran
 *	@since	Novemeber 4, 2024
 */
public class HTMLUtilities {

	/**
	 *	Break the HTML string into tokens. The array returned is
	 *	exactly the size of the number of tokens in the HTML string.
	 *	Example:	HTML string = "Goodnight moon goodnight stars"
	 *				returns { "Goodnight", "moon", "goodnight", "stars" }
	 *	@param str			the HTML string
	 *	@return				the String array of tokens
	 */
	public String[] tokenizeHTMLString(String str) {
		// make the size of the array large to start
		String[] result = new String[10000];
		String word= "";
		ArrayList<String> tempArr = new ArrayList();
		
		//~ for(int i =0; i< str.length()-1 ;i++){
			//~ if(str.charAt(i) == '<' ){
				//~ tempArr.add(str.substring(str.indexOf('<'), str.indexOf('>')+1));
			//~ }
		//~ }
		
		int count =0;
		while(str.length() > 0){
			if(str.indexOf('<') != -1) {
				tempArr.add(str.substring(str.indexOf("<") , str.indexOf(">")+1));
				str = str.substring(str.indexOf(">")+1);
			}
		}
		
		for(int i =0; i < tempArr.size(); i++){
			System.out.println("token [" +i + "] " +tempArr.get(i));
		}
		
		// return the correctly sized array
		return result;
	}
	
	private String tokenizeNumbers(String strToCheck){
		String str = "";
		return str;
	}
	
	/**
	 *	Print the tokens in the array to the screen
	 *	Precondition: All elements in the array are valid String objects.
	 *				(no nulls)
	 *	@param tokens		an array of String tokens
	 */
	public void printTokens(String[] tokens) {
		if (tokens == null) return;
		for (int a = 0; a < tokens.length; a++) {
			if (a % 5 == 0) System.out.print("\n  ");
			System.out.print("[token " + a + "]: " + tokens[a] + " ");
		}
		System.out.println();
	}

}
