package analyzer;

import codeInterpration.CodeInterpreter;


import java.util.ArrayList;

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
		//Look for Mem allocation
		String regex1 = "\\*\\s*.*new";
		String code2 = "int* pt1 = new int(5);\n"+ 
						"delete (pt1);\n"+
						"int *pt2 = new int[5];\n"+
						"delete[] pt2;\n"+
						"int* pt3  = new int(4);";
						
						
		ArrayList<String> results = CodeInterpreter.searchCode(code2, regex1);
		ArrayList<String> varNames = new ArrayList<>();
		// Trim the string, leaving only the varName
		int len = results.size();
		for(int i=0; i<len; i++){
			varNames.add( getVarName( results.get(i) ) );
			
		}
		//Check it's deallocated
		int size = varNames.size();
		for(int i = 0; i<size; i++){
			String varName = varNames.get(i);
			String regex2 = "delete\\s*(\\(|\\[\\])?\\s*"+varName;
			
			if(CodeInterpreter.searchCode(code2,regex2).isEmpty()){
				System.out.println(varName+" has not been deallocated");
			}else{
				System.out.println(varName+" has been deallocated");
			}
		}
	}
	
	private static String getVarName(String s){
		//find location of the '='
		s = s.replaceAll("\\s","");
		int len = s.length();
		int i = 0;
		while(i<len){
			if(s.charAt(i)!='='){}
			else{break;}
			i++;
		}
		return s.substring(1,i);
	}
}
