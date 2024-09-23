import java.util.Scanner;
import java.io.PrintWriter;

/**
 *	MVCipher - Asks the user for a keyword to use to encrypt/decrypt
 * 	a text file by shifting the letters by the keyword
 * 
 *	Requires Prompt and FileUtils classes.
 *	
 *	@author	Petros Mzikyan
 *	@since	9/20/2024
 */
public class MVCipher {

	public static void main(String[] args) {
		MVCipher mvc = new MVCipher();
		mvc.run();
	}
	
	/**
	 *	Runs the frontend of the code and asks the user all the
	 * 	info needed (to encrypt or decrypt, old file name, new file name)
	 */
	public void run() {
		Prompt ask = new Prompt();
		FileUtils askFile = new FileUtils();
		
		System.out.println("\n Welcome to the MV Cipher machine!\n");
		
		/* Prompt for a key and change to uppercase
		   Do not let the key contain anything but alpha
		   Use the Prompt class to get user input */
		
		String keyword = "";
		boolean valid = false; 	/* Boolean to be used for any time
								something's validity has to be checked */
		while (!valid)
		{
			keyword = ask.getString("Please input a word " + 
				"to use as key (letters only)").toUpperCase();
			
			valid = keyword.length() >= 3;
			for (int i = 0; i < keyword.length() && valid; i++)
			{
				char c = keyword.charAt(i);
				if (!('A' <= c && c <= 'Z'))
					valid = false;
			}
			
			if (!valid)
				System.out.println("ERROR: Key must be all " + 
				"letters and at least 3 characters long");
		}
		
		
		/* Prompt for encrypt or decrypt */
		
		System.out.println();
		int encryptOrDecrypt = ask.getInt("Encrypt or decrypt?", 1, 2);
		
		/* Prompt for an input file name */
		
		System.out.println();
		
		Scanner fileUsing;
		if (encryptOrDecrypt == 1)
		{
			fileUsing = askFile.openToRead(
				ask.getString("Name of file to encrypt"));
		}
		else
		{
			fileUsing = askFile.openToRead(
				ask.getString("Name of file to decrypt"));
		}
		
		/* Prompt for an output file name */
		
		PrintWriter newFile;
		newFile = askFile.openToWrite(
				ask.getString("Name of output file"));
		
		/* Read input file, encrypt or decrypt, and print to output file */
		
		if (encryptOrDecrypt == 1)
			
		
		/* Don't forget to close your output file */
		
		newFile.close();
	}
	
	public void encryptFile()
	{
		
	}
	
	public 
}
