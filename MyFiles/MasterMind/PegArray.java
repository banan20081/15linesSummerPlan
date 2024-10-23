/**
 *  This class creates and manages one array of pegs from the game MasterMind.
 *	It keeps track of a peg array representing a code guess and provides functionality 
 *  to compare this guess with the master code, returning the number of exact and 
 *  partial matches.
 * 
 *  @author	Banan Badran
 *  @since	October 1st, 2024
*/

public class PegArray {

	// array of pegs
	private Peg [] pegs;

	// the number of exact and partial matches for this array
	// as matched against the master.
	// Precondition: these values are valid after getExactMatches() and getPartialMatches()
	//				are called
	private int exactMatches, partialMatches;
	
	/* boolean arrays to keep track of which pegs have already been matched
	    - excludedMatch: tracks pegs that are exact matches, to exclude them from 
	      further matching
	    - partialMatchIndex: tracks pegs that have already been counted as partial
		  matches.
	*/
	private boolean[] excludedMatch, partialMatchIndex;
		
	/**
	 *	Constructor
	 *	@param numPegs	number of pegs in the array
	 */
	public PegArray(int numPegs) {	
		pegs = new Peg[numPegs];
		for ( int i =0; i < numPegs; i++) {
			pegs[i] = new Peg();
		}
		excludedMatch = new boolean[pegs.length];    // Initially, no pegs are excluded
		partialMatchIndex = new boolean[pegs.length]; // Initially, no partial matches
		exactMatches = partialMatches = 0;  // Initial values for match counts
	}
	
	/**
	 *	Return the peg object
	 *	@param n	The peg index into the array
	 *	@return	pegs[n]	the peg object
	 */
	public Peg getPeg(int n) { return pegs[n]; }
	
	/**
	 *  Finds exact matches between master (key) peg array and this peg array
	 *	Postcondition: field exactMatches contains the matches with the master
	 *  @param master	The master (code) peg array
	 *	@return	exactMatches		The number of exact matches
	 */
	public int getExactMatches(PegArray master) { 
		for (int i = 0; i < pegs.length; i++) {
			if (pegs[i].getLetter() == master.getPeg(i).getLetter()) {
				//if has an exact match it marks that index in this array as true;
				excludedMatch[i] = true;
				exactMatches++;
			}
		}
		return exactMatches;
	}
	
	/**
	 *  Find partial matches between master (key) peg array and this peg array
	 *	Postcondition: field partialMatches contains the matches with the master
	 *  @param master				The master (code) peg array
	 *	@return	partialMatches		The number of partial matches
	 */	
	public int getPartialMatches(PegArray master) { 
		for(int i =0; i < pegs.length; i++){	// iterates through master code
			// checks if peg has no exact value in same spot
			if(!excludedMatch[i] ) { 
				// iterates through user guess
				for(int j =0 ; j < pegs.length; j++){ 
					if(!excludedMatch[j] && !partialMatchIndex[j] && 
					(pegs[j].getLetter() == master.getPeg(i).getLetter())){
						partialMatchIndex[j] = true;
						partialMatches++;
						break;	// Move to the next peg to be checked
					}
				}
			}
		}
		return partialMatches;
	}
	
	// Accessor methods
	// Precondition: getExactMatches() and getPartialMatches() must be called first
	public int getExact() { return exactMatches; }
	public int getPartial() { return partialMatches; }

}
