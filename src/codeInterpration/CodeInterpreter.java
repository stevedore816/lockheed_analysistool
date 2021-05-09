package codeInterpration;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import SQLDatabase.SQLHandler;
import application.User;
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

	private String CID = "";

	private int accesslevel = 0;

	private cyberAttacks attacks = new cyberAttacks(); 


	public CodeInterpreter (String user) {
		this.user = user;
	}


	public CodeInterpreter (String code, String language, String user) {
		this.code = code;
		this.language = language;
		this.user = user;
	}

	public CodeInterpreter (String code, String language, String user, int accesslevel,String CID) {
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
	
	public void setAttacks(cyberAttacks attacks) {
		this.attacks = attacks;
	}
	
	public cyberAttacks getFieldAttacks() {
		return attacks;
	}
	/**
	 * get start and end positions for each match to regex expression in an array of ints
	 * @param @code is string of code to search over
	 * @param @regex is the expression to search for
	 * @return array of ints with all start/end positions of matches found
	 * 		   or -1 if no matches found
	 */
	public int[] getLocation (String regex) {
		int [] startendpos = new int[2]; 
		startendpos[0] = code.indexOf(regex); 
		startendpos[1] = startendpos[0]+regex.length();
		return startendpos;

	}

	public String getCID() {
		return CID; 
	}

	public void setCID(String name) {
		CID = name;
	}

	public ArrayList<String> getAllCIDS() {
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

	public ArrayList<attackVector> getAttacks() {
		return attacks.getList();
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
		this.CID = "";
		this.language = null;
	}

	public void analyzeCode() {
		if(language.equalsIgnoreCase("c++")) {
			cppAnalyzer anal = new cppAnalyzer(this);
		} else if (language.equalsIgnoreCase("c")) {
			cAnalyzer anal = new cAnalyzer(this);	
		} else if (language.equalsIgnoreCase("python")) {
			pythonAnalyzer anal = new pythonAnalyzer(this);
		} else if (language.equalsIgnoreCase("java")) {
			javaAnalyzer anal = new javaAnalyzer(this);
		} else if (language.equalsIgnoreCase("sql")) {
			sqlAnalyzer anal = new sqlAnalyzer(this);
		}
		ArrayList<String> customVectors = User.getRegexes();
		if(customVectors.size() != 0) {
			for(String regex: customVectors) {
				customeAnalyze(regex);
			}
		}
	}
	
	public void customeAnalyze(String regex) {
		ArrayList<String> vulner = searchCode(regex); 
		for(String v : vulner) {
			addAttack(new attackVector(v,Type.CUSTOMVULNERABILITY,"Detected this with your Regex Input")); 
		}
	}
	public String getAttackString() {
		return attacks.getString();
	}

	/*
	 * pushes the code over to the preestablished database (no sql code needed for this instance)
	 */
	public void pushToDataBase(String msg) {
		super.addCode(this,msg);
		attacks.pushDatabase(this);

	}

	public void pullfromDataBase(String user, String password, String codeID) {
		CodeInterpreter newCode = getCodeInfo(user,password, codeID);
		this.code = newCode.getCode();
		this.language = newCode.getLanguage();
		this.user = newCode.getUser();
		this.CID = newCode.getCID();
		this.accesslevel = newCode.getAccess(); 
	}
}
