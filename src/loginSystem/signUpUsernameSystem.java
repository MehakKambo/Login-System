package loginSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * User Account
 * Mehakpreet Kambo
 * This program is to create a new user name that should meet all the requirements, and will
 * give error messages according to the user's input. If the user is successful, then it will
 * print all the user names with an updated list to the console as well as to the text file.
 * This is a good practice of file handling in java.
 */

public class signUpUsernameSystem {

	public static ArrayList <String> user = new ArrayList<String>();//to get user names from the text file.
	private static String[] error = new String[8];// for all the error messages .
	static Scanner input = new Scanner(System.in);// to prompt user name from the user
	static String newuser;// to input user name.

	public static void main(String[] args) throws FileNotFoundException{

		readFile(); // reads the text file.
		printfile(); // prints the results from the file.
		loadErrorMessages(); //Contains all the error messages.
		check(); // make certain checks to the user name inputed by the user.
	}

	//This method reads the data from the text file
	//and throws FileNotFoundException.
	public static void readFile() throws FileNotFoundException {
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(new File("users.txt"));
		while(reader.hasNext()) {
			user.add(reader.next());
		}
	}

	//This method prints the data that is in the text file.
	public static void printfile() {
		System.out.println("List of the Usernames: ");
		for (int j = 0; j< user.size(); j++) { 
			System.out.println(user.get(j));
		}
	}

	//This method has all the error messages that are called when the appropriate error is
	//detected.
	public static void loadErrorMessages() {
		error[0] = "Invalid Username.";
		error[1] = "Username must be 4~7 characters long.";
		error[2] = "Username must start with a letter";
		error[3] = "Username is missing a lower case letter.";
		error[4] = "Username is missing a upper case letter.";
		error[5] = "Username is missing a special character.";
		error[6] = "Username is missing a digit in it.";
		error[7] = "User name is already in use.";
	}

	//This method helps run other methods when the checks are made and the user name
	// meets all the requirements.
	public static boolean isValid() {
		return checkLength() && checkStartDigit() &&
				checkLowerCase() && checkUpperCase() && hasDigit() &&
				checkSpecialChar() && nameInUseAlready();
	}

	//This method asks the user the user name, and runs the other methods if the user is 
	//successful to create one user name and if not, it runs all the appropriate error messages.
	public static void check() throws FileNotFoundException {
		while(true) {
			System.out.print("\nCreate a new username: ");
			newuser = input.next();
			
			if (isValid()) {
				AddNewUser();				
				listUsers2File();
				printfile();
				break;
				
			} else {
				System.out.println(error[0]); //invalid user name
				if(!checkLength()) 
				{
					System.out.println(error[1]); // error in length.
				} 
				if(!checkStartDigit()) {
					System.out.println(error[2]); // Does not starts with a letter.
				} 
				if(!checkLowerCase()) {
					System.out.println(error[3]); // missing lower case
				} 
				if(!checkUpperCase()) {
					System.out.println(error[4]); // missing upper case
				} 
				if(!checkSpecialChar()) {
					System.out.println(error[5]); // missing special character.
				} 
				if(!hasDigit()) {
					System.out.println(error[6]); // missing a digit.
				} 
				if(!nameInUseAlready()) {
					System.out.println(error[7]); // user names are duplicate.
				}
			}
		}
	}

	//This method checks if the user name entered matched the pre-existing 
	//user names in the text file.
	public static boolean nameInUseAlready() {
		if(user.contains(newuser)) {
			return false;
		} 
		return true;
	} 

	//This method checks the length of the user name inputed by the user.
	public static boolean checkLength() {
		if (newuser.length() < 4 || newuser.length() > 7) {
			return false;
		} 
		return true;
	}

	//This method checks if the user name starts with something else other than a letter.
	public static boolean checkStartDigit() {	
		if(Character.isAlphabetic(newuser.charAt(0))){
			return true;
		} 
		return false;
	}

	//This method checks if the user name has a lower case letter in it or not.
	public static boolean checkLowerCase() {
		boolean lowerCase = !newuser.equals(newuser.toUpperCase());
		if(!lowerCase) {
			return false;
		}
		return true;
	}

	//This method checks if the user name has a upper case letter in it or not.
	public static boolean checkUpperCase() {
		boolean upperCase = !newuser.equals(newuser.toLowerCase());
		if(!upperCase) {
			return false;
		} 
		return true;	
	}

	//This method checks if the user name contains a special character or not.
	public static boolean checkSpecialChar() {
		if(newuser.matches("[A-Za-z0-9]*")) {
			return false;
		} 
		return true;
	}

	//This method checks if there is any digit in the user name 
	// inputed by the user.
	public static boolean hasDigit() {
		if(!newuser.matches(".*\\d.*")) {
			return false;
		} 
		return true;
	}

	// This method prints the user name to the console if it meets all the requirements.
	// Then it adds that user name to the text file with pre-exisitng user names. 
	public static void AddNewUser() {
		System.out.println("User " + "\"" + newuser + "\"" + " added successfully.\n");
		user.add(newuser);
	}

	// This method prints user names to the text file
	public static void listUsers2File() throws FileNotFoundException {
		PrintStream out = new PrintStream(new File("users.txt"));
		for (int j = 0; j < user.size(); j++) {
			out.println(user.get(j)); 	
		}
	}
}
