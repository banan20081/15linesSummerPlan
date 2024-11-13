import java.util.ArrayList;

/**
 *	Utilities for handling HTML
 *
 *	@author	
 *	@since	
 */
public class HTMLUtilities {

	// NONE = not nested in a block, COMMENT = inside a comment block
	// PREFORMAT = inside a pre-format block
	private enum TokenState { NONE, COMMENT, PREFORMAT };
	// the current tokenizer state
	private TokenState state;
	
	/**
	 *	Break the HTML string into tokens. The array returned is
	 *	exactly the size of the number of tokens in the HTML string.
	 *	Example:	HTML string = "Goodnight moon goodnight stars"
	 *				returns { "Goodnight", "moon", "goodnight", "stars" }
	 *	@param str			the HTML string
	 *	@return				the String array of tokens
	 */
	public String[] tokenizeHTMLString(String str) {
        ArrayList<String> tempArr = new ArrayList<>();

        for(int i=0 ; i < str.length(); i++){
			char c = str.charAt(i);
			// Tokenize Tags
			if(c == '<'){
				if(i+3<str.length() && str.substring(i, i +4).equals("<!--")){
					state = TokenState.COMMENT;
					System.out.println(state);
				}
				else if(i+4<str.length() && str.substring(i, i +5).equals("<pre>")){
					state = TokenState.PREFORMAT;
					System.out.println(state);
				}
				else{
					tempArr.add(str.substring(i, str.indexOf('>' , i) + 1));
					i = str.indexOf('>' , i);
					//System.out.println("i index"+i);
				}
			}
			
			// Tokenize Words
			if( Character.isLetter(c) ){
				int start = i;
				//~ i++;
                while (i < str.length() && (Character.isLetter(str.charAt(i)) || 
                        (str.charAt(i) == '-' && i + 1 < str.length() && Character.isLetter(str.charAt(i + 1))))) {
                    i++;
                }
               // System.out.println("here is the word :: [" + str.substring(start, i)+"]" +i);
                tempArr.add(str.substring(start, i));
			}
			
			// Tokenize Numbers
            //~ if (Character.isDigit(c) || (c == '-' && i + 1 < str.length() && Character.isDigit(str.charAt(i + 1)))) {
                //~ int start = i;
                //~ //i++;
                //~ while (i < str.length() && (Character.isDigit(str.charAt(i)) || str.charAt(i) == '.' || 
                        //~ str.charAt(i) == 'e' || str.charAt(i) == 'E' || str.charAt(i) == '-' || str.charAt(i) == '+')) {
                    //~ i++;
                //~ }
                //~ tempArr.add(str.substring(start, i));
            //~ }
			
			// Tokenize Punctuations
			if(i<str.length() && isPunctuation(str.charAt(i)) ){
				tempArr.add(String.valueOf(str.charAt(i)));
			}
		
		}

        return tempArr.toArray(new String[0]);
    }

    /**
     * Check if a character is punctuation
     * @param c the character to check
     * @return true if the character is a punctuation symbol
     */
    private boolean isPunctuation(char c) {
        return ".,;:()?!&~=+-".indexOf(c) != -1;
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
