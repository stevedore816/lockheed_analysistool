package Analyzer;

import java.util.ArrayList;

import codeInterpration.CodeInterpreter;

public class Analyzer {
	
	// This is where the CodeInterpreter is used and refers to the original deciphered code
	public String code = CodeInterpreter.getCode(); 
	
	
	
	/*
	 * This method should look for input validations inside of the variety of languages we go over
	 * The main method of doing this is by using regex expressions (Review if you need to) to try and detect the instances in the input 
	 * Where the input can be considered at risk or able to be attacked.
	 * 
	 * There should be no return value because you should be adding every type of attack to the cyberAttacks list with the type that it is
	 * and the location (eventually if you can find it focus on the functionality first though)
	 * 
	 */
	public void checkInputValidation() {
		String regex = "(?<!prepare)('|\\\")SELECT.+FROM.+('|\\\").*..*";
		ArrayList<String> searchQuery = CodeInterpreter.searchCode(code, regex);
		
		//cyberAttacks.add(new attackVector(Type.INPUTVALIDATION)); 
		
		for (String searches : searchQuery) {
			if (!searches.equals(null))
				System.out.println(searches); 
			
		}
	} 
	/*
	 * This method will check to see if there are sanitizers inside of the code, if there are not it will count
	 * as a flag inside of the inputValidation method
	 */
	public boolean hasSanitizeMethod() {
		return false; 
	} 
}
