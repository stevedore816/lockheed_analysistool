package codeInterpration;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* The Objective of the Code Interpreter is to analyze the code input that comes into
 * the system as primarily regex expressions and to demonstrate how the Interpreter will search for 
 * attack vectors 
*/
public class CodeInterpreter {
	
	//current static variable to reference the code input will be input for more paramter protection down the line
	//can be referenced from a global scope using getCode()
	private static String code; 
	
	//variable that decides the language
	//can be referenced from a global scope using getLang()
	private static String language; 
	
	
	/*
	 * The purpose of the code is to search through lines of code and
	 * see if it lines up with Regex expressions so further down the line searching for
	 * regex expressions that indicate some type of input validation is taking place applies to this
	 * 
	 * @param regex is the expression that you use to search through the code
	 * @return ArrayList of Strings for all the expressions that are being searched for
	 */
	public static ArrayList<String> searchCode (String regex) { 
		ArrayList<String> temp = new ArrayList<String>(); 
		Pattern string = Pattern.compile(regex);
		Matcher matcher = string.matcher(code);  
		while (matcher.find()) {
			temp.add(matcher.group()); 
		}
		return temp;  
	}
	/*
	 * The purpose of the code is to search through lines of code and
	 * see if it lines up with Regex expressions so further down the line searching for
	 * regex expressions that indicate some type of input validation is taking place applies to this
	 * 
	 * @param code is the string that your searching for regular expressions over
	 * @param regex is the expression that you use to search through the code
	 * @return returns the all the expressions that are being searched for
	 */
	public static ArrayList<String> searchCode (String code, String regex) {
		ArrayList<String> temp = new ArrayList<String> (); 
		Pattern string = Pattern.compile(regex);
		Matcher matcher = string.matcher(code); 
		while (matcher.find()) {
			temp.add(matcher.group()); 
		}
		return temp;  
	}
	/*
	 * @return returns the code that is stored in the class
	 */
	public static String getCode() {
		return code; 
	}
	
	/*
	 * @param newcode is used whenever we want tochange the code from the original or add new code
	 */
	public static void setCode(String newcode) {
		code = newcode; 
	}
	/*
	 * @return the language that the code is in
	 */
	public static String getLanguage() {
		return language;
	}
	/*
	 * @param language is used to set the language of the code interpreted
	 */
	public static void setLanguage(String language) {
		CodeInterpreter.language = language;
	}
}
