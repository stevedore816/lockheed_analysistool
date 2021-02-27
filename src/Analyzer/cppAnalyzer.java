package Analyzer;

public class cppAnalyzer extends Analyzer {
	
	/* This method should work by looking at inputs of C++ code
	 * Use regex expressions in order to find blocks of information and data for input values and find out if these
	 * instances make the system vulnerable or not
	 */
	public void checkInputValidation() {
		//also for the code your looking through currently use a string value for your example code to test over
		//to add to vector array currently use the code line : cyberAttacks.add(new attackVector(Type.INPUTVALIDATION); 
		//For checking for regex expression over test code use this method CodeInterpreter.searchCode("int x = 10;", regex);
	}
	
	/* This method will look for instances in memory where there is no instance of the memory getting deleted
	 * Using Regex you can find the instances of memory that does not have a following delete key and we can decide from there if 
	 * This makes the system vulnerable or not
	 */
	public void checkMemoryLeak() {
		//cyberAttacks.add(new attackVector(Type.MEMORYLEAK)); 
	}
}
