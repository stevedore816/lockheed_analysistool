package codeInterpration;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import SQLDatabase.SQLHandler;
import vulnerabilityDetector.*;

/* The Objective of the Code Interpreter is to analyze the code input that comes into
 * the system as primarily regex expressions and to demonstrate how the Interpreter will search for 
 * attack vectors 
*/
public class CodeInterpreter extends SQLHandler{
	
	//current static variable to reference the code input will be input for more paramter protection down the line
	//can be referenced from a global scope using getCode()
	private String code; 
	
	//variable that decides the language
	//can be referenced from a global scope using getLang()
	private String language; 
	
	private String user; 
	
	private Random rand = new Random();
	
	private int CID = -1;
	
	private int accesslevel = 0;
	
	private cyberAttacks attacks = new cyberAttacks(); 
	
	
	public CodeInterpreter (String user) {
		this.user = user;
		accesslevel = 0; 
	}
	
	
	public CodeInterpreter (String code, String language, String user) {
		this.code = code;
		this.language = language;
		this.user = user;
		
		accesslevel = 0; 
	}
	
	public CodeInterpreter (String code, String language, String user, int accesslevel,int CID) {
		this.code = code;
		this.language = language;
		this.user = user;
		this.CID = CID;
		this.accesslevel = accesslevel; 
	}
	
	
	/*
	 * The purpose of the code is to search through lines of code and
	 * see if it lines up with Regex expressions so further down the line searching for
	 * regex expressions that indicate some type of input validation is taking place applies to this
	 * 
	 * @param regex is the expression that you use to search through the code
	 * @return ArrayList of Strings for all the expressions that are being searched for
	 */
	public ArrayList<String> searchCode (String regex) { 
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
	public ArrayList<String> searchCode (String code, String regex) {
		ArrayList<String> temp = new ArrayList<String> (); 
		Pattern string = Pattern.compile(regex);
		Matcher matcher = string.matcher(code); 
		while (matcher.find()) {
			temp.add(matcher.group()); 
		}
		return temp;  
	}
	/**
	 * get start and end positions for each match to regex expression in an array of ints
	 * @param @code is string of code to search over
	 * @param @regex is the expression to search for
	 * @return array of ints with all start/end positions of matches found
	 * 		   or -1 if no matches found
	 */
	public int[] getLocation (String regex) {
		int[] temp = new int[2]; 
		Pattern string = Pattern.compile(regex);
		Matcher matcher = string.matcher(code); 
		if (matcher.find()) {
			temp[0] = matcher.start();
			temp[1] = matcher.end();
		}
		else
			temp[0] = -1;
			temp[1] = -1; 
		return temp;  
	}
	
	public int getCID() {
		return CID; 
	}
	
	public void setCID(int i) {
		CID = i;
	}
	
	public ArrayList<Integer> getAllCIDS() {
		return super.getCIDS(user);
	}
	/*
	 * @return returns the code that is stored in the class
	 */
	public String getCode() {
		return code; 
	}
	
	/*
	 * @param newcode is used whenever we want tochange the code from the original or add new code
	 */
	public void setCode(String newcode) {
		code = newcode; 
	}
	/*
	 * @return the language that the code is in
	 */
	public String getLanguage() {
		return language;
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	/*
	 * @param language is used to set the language of the code interpreted
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public void getAttacks() {
		attacks.getList();
	}
	
	public void addAttack(attackVector attack) {
		attacks.add(attack);
	}
	
	public int getAccess() {
		return accesslevel;
	}
	
	public void clear() {
		this.code = null;
		this.accesslevel = 0;
		this.user = null;
		this.CID = -1; 
		this.language = null;
	}
	
	public void analyzeCode() {
		if(language.equals("c++")) {
			cppAnalyzer anal = new cppAnalyzer(this);
		} else if (language.equals("c")) {
			cAnalyzer anal = new cAnalyzer(this);
			
		} else if (language.equals("python")) {
			pythonAnalyzer anal = new pythonAnalyzer(this);
		} else if (language.equals("java")) {
			javaAnalyzer anal = new javaAnalyzer(this);
		} else if (language.equals("sql")) {
			sqlAnalyzer anal = new sqlAnalyzer(this);
		} else {
		}
	}
	
	public String getAttackString() {
		return attacks.getString();
	}
	
	/*
	 * pushes the code over to the preestablished database (no sql code needed for this instance)
	 */
	public void pushToDataBase() {
		CID = rand.nextInt(9000);
		super.addCode(this);
		attacks.pushDatabase(this);
			
	}
	
	public void pullfromDataBase(String user, String password, int codeID) {
		CodeInterpreter newCode = getCodeInfo(user,password, codeID);
		this.code = newCode.getCode();
		this.language = newCode.getLanguage();
		this.user = newCode.getUser();
		this.CID = newCode.getCID();
		this.accesslevel = newCode.getAccess(); 
	}
}
