package MediaDatabase;

import java.io.Serializable;

/**
 * Project #2
 * CS 2334, Section 10
 * Feb 29, 2016
 * <P>
 * This Roman class creates a Roman Numeral
 * object for easy translation between 
 * Roman Numeral String and number
 * </P>
 * @author 
 * @version 1.0 (obtained from Project 1)
 */

public class Roman implements Comparable<Roman>, Serializable	{
	
	/** Private variables for Roman Numeral class to convert 
	 *  from romanNumeral to number and vice versa 
	 */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4239767231131014350L;

	/** stores the roman numeral string (I, II, IV) for the Roman object */
	private String romanNumeral = "";
	
	/** stores the number translation for the roman numeral string in Roman object */
	private int number;
	
	/** stores the translation for roman numbers with index value 
	 * equaling the corresponding array string roman numeral
	 */
	private static String[] romanNumeralsToXX = new String[] 
			{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", 
			"XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX"};	
	
	/**
	 * Empty constructor for Roman 
	 * 
	 */
	public Roman (){
		this.romanNumeral = null;
		this.number = 0;
	}
	
	
	/**
	 * Constructor for Roman setting the number translation off roman numeral input
	 * 
	 * @param romanNumeral  string used to create Roman
	 */	
	public Roman(String romanNumeral){
		this.romanNumeral = romanNumeral;
		this.number = translate2Number(romanNumeral);
	}
	
	/**  Constructor for Roman setting the Roman numeral translation off of number input
	 * 
	 * @param number  number used to create Roman
	 */
	public Roman(int number){
		this.number = number;
		this.romanNumeral = translate2Numeral(number);
	}
	
	/**
	 * Get method for roman numeral from Roman object
	 * 
	 * @return String  string to return representing romanNumeral
	 */
	public String getRomanNumeral(){
		return romanNumeral;
	}
	
	/**
	 * Get Method for number from Roman object
	 * 
	 * @return int  number for Roman
	 */
	public int getNumber(){
		return number;
	}
	
	/**
	 * Set Method for RomanNumeral in Roman Object and sets number on translation method
	 * 
	 * @param romanNumeral  string to convert to number
	 * 
	 */
	public void setRomanNumeral(String romanNumeral){
		this.romanNumeral = romanNumeral;
		this.number = translate2Number(romanNumeral);
	}
	
	/**
	 * Set Method for number in Roman Object and sets romanNumeral on translation method
	 * 
	 * @param number  int to be translated to numeral
	 * 
	 */
	public void setNumber(int number){
		this.number = number;
		this.romanNumeral = translate2Numeral(number);
	}
	
	/**
	 * Method to translate romanNumeral to integer number
	 * 
	 * @param romanNumeral  String to be translated to number
	 * @return int
	 */
	public static int translate2Number(String romanNumeral){
		
		int number = 0;
		for (int index = 0; index < 21; index++)
			if(romanNumeralsToXX[index].equals(romanNumeral))
				number = index; 
		
		return number;
		
	}
	
	/**
	 * 
	 * Method to translate number to romanNumeral 
	 *
	 * @param number  number to be translated to RomanNumeral
	 * @return String
	 */
	public static String translate2Numeral(int number){
		String numeral = "";
		numeral = romanNumeralsToXX[number];
		return numeral;
	}
	
	/**
	 * Method to override toString for Roman class
	 * 
	 */
	@Override
	public String toString(){
		return romanNumeral;
	}

	/**
	 * Method to override compareTo method for Roman class
	 * Use number to compare
	 * @param numeral  String to be compared to other RomanNumerals
	 * @return int
	 */
	@Override
	public int compareTo(Roman numeral){
		return 0;
	}
}

