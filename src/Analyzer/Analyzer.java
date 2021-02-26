package Analyzer;

public interface Analyzer {
	
	/*
	 * This method should look for input validations inside of the variety of languages we go over
	 * The main method of doing this is by using regex expressions (Review if you need to) to try and detect the instances in the input 
	 * Where the input can be considered at risk or able to be attacked.
	 * 
	 * There should be no return value because you should be adding every type of attack to the cyberAttacks list with the type that it is and the
	 * location (eventually if you can find it focus on the functionality first though)
	 */
	public void checkInputValidation() ; 
}
