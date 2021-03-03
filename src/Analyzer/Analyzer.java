package analyzer;

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
	 * This algorithm will be the base case for all the algorithms that I make.
	 * Current only problem with this algorithm is that it double detects which is easy to get rid of 
	 * 
	 * look at the statement with the variable with it, if the statement has string concat or single quote check if theres a preparedstate
	 * if not for now add it to the list of input injections
	 * 
	 * 
	 * 
	 *(String|int|double|char|)\s([a-zA-Z0-9]+) = ('|")SELECT.+FROM.+('|").*..*
	 * 
	 * (prepareStatement|prepare|prepared|mysql_stmt_init)( |)( |)[(]( |)txtSQL( |)[)]
	 */
	public void checkSQLInjection() {
		String regex = "(String|int|double|char|str|)\\s([a-zA-Z0-9]+) = ('|\")SELECT.+FROM.+('|\").*..*";
		ArrayList<String> searchQuery = CodeInterpreter.searchCode(regex);
		//cyberAttacks.add(new attackVector(Type.INPUTVALIDATION)); 
		for (String searches : searchQuery) {
				//Checks for String Concatenation (1 = 1 cases or ""="" cases) & for single quotes
			if (searches.contains("+") || searches.contains("'")) {
				//turns the previous regex into variables
				ArrayList<String> variables = CodeInterpreter.searchCode(searches,"([a-zA-Z0-9]+) =");
				String variable = variables.get(0).replaceAll("\\s", "");
				variable = variable.substring(0, variable.length() - 1); 
				//check if preparedStatements exist
				ArrayList<String>preparedStatement = CodeInterpreter.searchCode(code, "(prepareStatement|prepare|prepared|mysql_stmt_init)( |)( |)[(]( |)"+ variable + "( |)[)]"); 
				if(preparedStatement.size() == 0) cyberAttacks.add(new attackVector(Type.INPUTVALIDATION));
			}
		}
	} 
}
