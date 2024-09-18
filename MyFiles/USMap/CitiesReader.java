 import java.io.PrintWriter;
 import java.io.File;
 import java.io.FileNotFoundException;

/*
 *	File utilities for reading and writing
 *
 * @author	Banan
 * @since August 23, 2024
 */
 
 
 public class CitiesReader {
	 
	 /**
	  * opens a file to readf using scanner class.
	  * @param fileName		name of the file to open
	  * @return 			the scanner object to the file
	  */
	 public static java.util.Scanner openToRead(String fileName){
		 java.util.Scanner input = null;
		 try {
			 input = new java.util.Scanner(new java.io.File(fileName));
		 }
		 catch (java.io.FileNotFoundException e) {
			 System.err.println("ERROR : Cannot open " + fileName + " For reading.");
			 System.exit(1);
		 }
		 return input;
	 }
	 
	 /**
	  * opens a file to write using the PrintWriter class.
	  * @param fileName		name of the file to open
	  * @return 			PrintWriter object to the file
	  */
	 public static PrintWriter openToWrite(String fileName) {
		 PrintWriter output = null;
		 try{
			 output = new PrintWriter(new File(fileName));
		 }
		 catch (FileNotFoundException e) {
			 System.err.println("ERROR : Cannot open " + fileName + " For Writing.");
			 System.exit(2);
		 }
		 return output;
	 }
 }
