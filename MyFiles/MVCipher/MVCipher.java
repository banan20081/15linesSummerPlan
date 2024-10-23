// imports go here
import java.io.File;
// diff MacBeth.txt decrypted.txt		-- for mac
// diff (cat MacBeth.txt) (cat decrypted.txt) -- window
/**
 *	MVCipher - Add your description here
 *	Requires Prompt and FileUtils classes.
 *	
 *	@author	Banan Badran
 *	@since	September 23, 2024
 */
public class MVCipher {
	
	// fields go here
		
	/** Constructor */
	public MVCipher() {}
	
	public static void main(String[] args) {
		MVCipher mvc = new MVCipher();
		mvc.run();
	}
	
	/**
	 *	Method header goes here
	 */
	public void run() 
	{
		String fileToChange, keyWord;
		boolean notString = false;
		System.out.println("\n Welcome to the MV Cipher machine!\n");

		/* Prompt for a key and change to uppercase
		Do not let the key contain anything but alpha
		Use the Prompt class to get user input */
		do {
			notString = false;
			keyWord = Prompt.getString("Please input a word to use"+
			" as key (letters only)");
			// loop through String?
			keyWord = keyWord.toUpperCase();
			for(int i =0; i < keyWord.length(); i++) {
				int chartToASCII = (int)(keyWord.charAt(i));
				if( chartToASCII < 65 || chartToASCII > 90){ 
					notString = true;
				}
			}
			if(notString || keyWord.length() < 3){
				System.out.println("ERROR: Key must be all letters and" +
										"at least 3 characters long");
			}
		} while(keyWord.length() < 3 || notString);

		/* Prompt for encrypt or decrypt */
		int choice = Prompt.getInt("\nEncrypt or decrypt?", 1, 2);

		/* Prompt for an input file name */
		if (choice == 1 ) {
			fileToChange = Prompt.getString("\nName of file to encrypt");
		}
		else {
			fileToChange = Prompt.getString("\nName of file to decrypt");
		}
		FileUtils.openToRead(fileToChange);

		/* Prompt for an output file name */
		String outputFile = Prompt.getString("\nName of output file");
		FileUtils.openToWrite(outputFile);


		//make string callled wither encrypt otr decrypt have a print statemtn
		/* Read input file, encrypt or decrypt, and print to output file */


		/* Don't forget to close your output file */
	}
	
	// other methods go here
	
}
