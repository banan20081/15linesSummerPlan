import java.util.List;
import java.util.ArrayList;
/**
 *	AnagramMaker - <description goes here>
 *
 *	Requires the WordUtilities, SortMethods, Prompt, and FileUtils classes
 *
 *	@author	Banan Badran
 *	@since	1/19/2025
 */
public class AnagramMaker {
								
	private final String FILE_NAME = "randomWords.txt";	// file containing all words
	
	private WordUtilities wu;	// the word utilities for building the word
								// database, sorting the database,
								// and finding all words that match
								// a string of characters
	
	// variables for constraining the print output of AnagramMaker
	private int numWords;		// the number of words in a phrase to print
	private int maxPhrases;		// the maximum number of phrases to print
	private int numPhrases;		// the number of phrases that have been printed
		
	/*	Initialize the database inside WordUtilities
	 *	The database of words does NOT have to be sorted for AnagramMaker to work,
	 *	but the output will appear in order if you DO sort.
	 */
	public AnagramMaker() {
		wu = new WordUtilities();
		wu.readWordsFromFile(FILE_NAME);
		wu.sortWords();
	}
	
	public static void main(String[] args) {
		AnagramMaker am = new AnagramMaker();
		am.run();
	}
	
	/**	The top routine that prints the introduction and runs the anagram-maker.
	 */
	public void run() {
		printIntroduction();
		runAnagramMaker();
		System.out.println("\nThanks for using AnagramMaker!\n");
	}
	
	/**
	 *	Print the introduction to AnagramMaker
	 */
	public void printIntroduction() {
		System.out.println("\nWelcome to ANAGRAM MAKER");
		System.out.println("\nProvide a word, name, or phrase and out comes their anagrams.");
		System.out.println("You can choose the number of words in the anagram.");
		System.out.println("You can choose the number of anagrams shown.");
		System.out.println("\nLet's get started!");
	}
	
	/**
	 *	Prompt the user for a phrase of characters, then create anagrams from those
	 *	characters.
	 */
	public void runAnagramMaker() {
		String phrase;
		System.out.println();
		do{
			phrase = Prompt.getString("Word(s), name or phrase (q to quit)");
			if(phrase.equalsIgnoreCase("q")) return;
			numWords = Prompt.getInt("Number of Words in anagram");
			maxPhrases = Prompt.getInt("Maximum number of anagrams to print");
			ArrayList<String> anagrams = new ArrayList<String>();
			String numFreeStr ="";
			for(int i =0; i<phrase.length(); i++)
				if(Character.isLetter(phrase.charAt(i))) 
					numFreeStr+=phrase.charAt(i);
					
			numPhrases =0;
			anagramMaker(numFreeStr.toLowerCase(), anagrams);
		}while(!phrase.equalsIgnoreCase("q"));
	}
	
	public void anagramMaker(String phrase, ArrayList<String> anagram){
		
		if(numPhrases >= maxPhrases) return;
		
		if(phrase.isEmpty()) {
			if(anagram.size() == numWords){
				for(int i=0;i< anagram.size();i++){
					System.out.print(anagram.get(i) + " ");
				}
				System.out.println();
				numPhrases++;

			}
			return;
		}
		
		if(anagram.size() >= numWords) return;
		
		ArrayList<String> words = wu.allWords(phrase);

		
		for(int i=0;i<words.size(); i++){
			String newPhrase = phrase;
			anagram.add(words.get(i));
			// add new word to anagram array

			// remove letters 
			for(int k=0; k < words.get(i).length();k++){ // every letter exist in phase , no need to double check for existance 
						newPhrase = newPhrase.substring(0,newPhrase.indexOf(words.get(i).charAt(k))) +
								newPhrase.substring(newPhrase.indexOf(words.get(i).charAt(k))+1);
			}
			anagramMaker(newPhrase, anagram);
			// remove it 
			anagram.remove(anagram.size()-1);
		}
		
		return;
		
	}

	
}
