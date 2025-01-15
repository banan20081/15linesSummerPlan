import java.util.ArrayList;

/**
 *	Utilities for handling HTML files by tokenizing words, tags, numbers, etc... 
 *
 *	@author	Banan Badran 
 *	@since	11/13/2024
 */
public class HTMLUtilities {

	// NONE = not nested in a block, COMMENT = inside a comment block
	// PREFORMAT = inside a pre-format block
	private enum TokenState { NONE, COMMENT, PREFORMAT }
	// the current tokenizer state
    private TokenState state = TokenState.NONE;
    
	/**
	 *	Break the HTML string into tokens. The array returned is
	 *	exactly the size of the number of tokens in the HTML string.
	 *	Example:	HTML string = "Goodnight moon goodnight stars"
	 *				returns { "Goodnight", "moon", "goodnight", "stars" }
	 *	@param str			the HTML string
	 *	@return	result			the String array of tokens
	 */
	public String[] tokenizeHTMLString(String str) {
		// make the size of the array large to start
		String[] result = new String[10000];
		
		//temporary to store tokens as they're found
        ArrayList<String> tempArr = new ArrayList<>(); 

		// Loop through the string, character by character
        for(int i=0 ; i < str.length(); i++){
			char c = str.charAt(i);
			
			// Handling comment blocks: ignore everything inside <!-- --> tags
            if (state == TokenState.NONE && i + 3 < str.length() && 
										str.substring(i, i + 4).equals("<!--")) {
                state = TokenState.COMMENT; 
                i += 3;  // ensures tokenizer doesn't process comment tag <!--
            }
            // Exiting comment block: handle the closing -->
            else if (state == TokenState.COMMENT && i + 2 < str.length() && 
										str.substring(i, i + 3).equals("-->")) {
                state = TokenState.NONE;
                i += 2;  // ensures tokenizer doesn't process comment tags -->
            }
            // Handling the start of preformatted text: once <pre> is detected
            else if (state == TokenState.NONE && i + 4 < str.length() && 
									str.substring(i, i + 5).equals("<pre>")) {
				tempArr.add("<pre>");
				/* state is preformate which means that each in file will be 
				 * added as one token until it reaches </pre> */
                state = TokenState.PREFORMAT;
                i += 4; // Skip to the end of "<pre>"
            }
            // Handling the end of preformatted text: once </pre> is detected
            else if (state == TokenState.PREFORMAT && i + 5 < str.length() && 
									str.substring(i, i + 6).equals("</pre>")) {
				tempArr.add("</pre>");
                state = TokenState.NONE;
                i += 5; // Skip to the end of "</pre>"
            }
            // Tokenizing preformatted text as single token until </pre> is found
            else if (state == TokenState.PREFORMAT) {
                int start = i;
                while (i < str.length() && !(i + 5 < str.length() && 
									str.substring(i, i + 6).equals("</pre>"))) {
                    i++;
                }
                tempArr.add(str.substring(start, i));
                i--; // ensure closing tag gets processed in the next iteration
            }
            // its neither COMMENT nor PREFORMATTTED
            else if (state == TokenState.NONE) {
				// Tokenize Tags , everything between and including '<' and '>'
				if(c == '<'){
					tempArr.add(str.substring(i, str.indexOf('>' , i) + 1));
					i = str.indexOf('>' , i); // Move to the end of the tag
				}
				
				// Tokenize Words , letters(continuous) and hyphenated words
				else if( Character.isLetter(c) ){
					int start = i;
					while (i < str.length() && (Character.isLetter(str.charAt(i))
							|| (str.charAt(i) == '-' && i + 1 < str.length() && 
									Character.isLetter(str.charAt(i + 1))))) {
						i++;
					}
					tempArr.add(str.substring(start, i));
					i--; // To ensure tokenizer doesn't skip over next character
				}
				
				// Tokenize Numbers, +/- values, decimals, and exponents
				else if(Character.isDigit(c) || (c == '-' && i + 1 < str.length()
									&& Character.isDigit(str.charAt(i + 1))) ){
					int start =i;
					boolean isDecimal = false, isExponent = false;

					while (i < str.length() && (Character.isDigit(str.charAt(i)) 
							|| str.charAt(i) == '.' || str.charAt(i) == 'e' || 
							str.charAt(i) == 'E' || str.charAt(i) == '-')) {
						if (str.charAt(i) == '.') {
							/* Only 1 decimal allowed in a number */
							if (isDecimal) break; 
							isDecimal = true;
						} 
						else if (str.charAt(i) == 'e' || str.charAt(i) == 'E') {
							/* Only 1 exponent allowed in a number */
							if (isExponent) break;
							isExponent = true;
							
							if (i + 1 < str.length() && str.charAt(i + 1) == '-') 
								i++; // negative exponents
						}
						i++;
					}
					tempArr.add(str.substring(start, i));
					i--; // to allign with the loop increment
				}
				
				// Tokenize Punctuations which is .,;:()?!&~=+-
				else if(i<str.length() && isPunctuation(str.charAt(i)) ){
					tempArr.add(String.valueOf(str.charAt(i)));
				}
			}
		}
		
		result = new String[tempArr.size()]; // correctly sized new array
		for(int i =0 ; i < tempArr.size() ; i++) {result[i] = tempArr.get(i);}

		// return the correctly sized array
        return result;
    }

    /**
     * Check if a character is punctuation
     * @param c the character to check
     * @return true if the character is a punctuation symbol
     * @return false if the character is not a punctuation symbol
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
