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
		String fileToDecrypt , fileToencrypt, keyWord;
		boolean notString = false;
		System.out.println("\n Welcome to the MV Cipher machine!\n");

		/* Prompt for a key and change to uppercase
		Do not let the key contain anything but alpha
		Use the Prompt class to get user input */
		do {
			keyWord = Prompt.getString("Please input a word to use"+
			" as key (letters only)");
			// loop through String?
			for(int i =0; i < keyWord.length(); i++) {
				int chartToASCII = (int)(keyWord.charAt(i));
				if( chartToASCII < 65){ 
					notString = true;
				}
				else if((chartToASCII > 90 && chartToASCII < 97) || chartToASCII > 122){
					notString = true;
				}
			}
			if(notString || keyWord.length() < 3){
				System.out.println("ERROR: Key must be all letters and" +
										"at least 3 characters long");
			}
		} while(keyWord.length() < 3 || notString);



		/* Prompt for encrypt or decrypt */
		int choice = Prompt.getInt("Encrypt or decrypt?", 1, 2);

		/* Prompt for an input file name */
		if (choice == 1 )
			fileToencrypt = Prompt.getString("Name of file to encrypt");
		else 
			fileToDecrypt = Prompt.getString("Name of file to decrypt");

		/* Prompt for an output file name */
		String outputFile = Prompt.getString("Name of output file");

		/* Read input file, encrypt or decrypt, and print to output file */


		/* Don't forget to close your output file */
	}
	
	// other methods go here
	
}
