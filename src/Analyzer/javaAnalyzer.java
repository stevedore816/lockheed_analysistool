package Analyzer;

public class javaAnalyzer extends Analyzer {

	/* This method should work by looking at inputs of java code specifically if the java code is in SQL
	 * Use regex expressions in order to find blocks of information and data for input values and find out if these
	 * instances make the system vulnerable or not
	 */
	
	public void checkInputValidation() {
		//also for the code your looking through currently use a string value for your example code to test over
		//to add to vector array currently use the code line : cyberAttacks.add(new attackVector(Type.INPUTVALIDATION); 
		//For checking for regex expression over test code use this method CodeInterpreter.searchCode("int x = 10;", regex);
	}

}
