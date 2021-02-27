package Analyzer;

import java.util.ArrayList;

import codeInterpration.CodeInterpreter;

public class sqlAnalyzer implements Analyzer {
    //testing something
	private String test; 
	
	public sqlAnalyzer(String test) {
		this.test = test; 
	}
	
	/* This method should work by looking at inputs of sql code specifically if the java code is in SQL ()
	 * Use regex expressions in order to find blocks of information and data for input values and find out if these
	 * instances make the system vulnerable or not
	 */
	
	public void checkInputValidation() {
		//also for the code your looking through currently use a string value for your example code to test over
		//to add to vector array currently use the code line : cyberAttacks.add(new attackVector(Type.INPUTVALIDATION);
		//For checking for regex expression over test code use this method CodeInterpreter.searchCode("int x = 10;", regex);
		
		ArrayList<String> searchQuery = CodeInterpreter.searchCode(test,"('(''|[^'])*')|(;)|(\\b(ALTER|CREATE|DELETE|DROP|EXEC(UTE){0,1}|INSERT( +INTO){0,1}|MERGE|SELECT|UPDATE|UNION( +ALL){0,1})\\b)");
		
		//cyberAttacks.add(new attackVector(Type.INPUTVALIDATION)); 
		
		for (String searches : searchQuery) {
			if (!searches.equals(null))
				System.out.println(searches); 
			
		}
	}

}
