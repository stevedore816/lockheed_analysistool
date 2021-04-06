package application;

import vulnerabilityDetector.cyberAttacks;

public class User {
	
	private static String userName;
	private static String passWord;
	private static int accessLvl;
	private static String code;
	private static String language;
	private static cyberAttacks attacks;
	private static int cid;

	
	public static void setFull(String name, String pass) {
		userName = name;
		passWord = pass;
	}
	
	public static void setCid(int codeID) {
		cid = codeID;
	}
	
	public static int getCID() {
		return cid;
	}
	
	public static void setAttacks(cyberAttacks attack) {
		attacks = attack;
	}
	
	public static cyberAttacks getAttacks() {
		return attacks;
	}
	
	public static void setUser(String name) {
		userName = name;
	}
	
	public static void setPassWord(String pass) {
		passWord = pass;
	}
	
	public static String getUser() {
		return userName;
	}
	
	public static String getPassword() {
		return passWord;
	}
	
	public static int getAccess() {
		return accessLvl;
	}
	
	public static void setAccess(int lvl) {
		 accessLvl = lvl;
	}
	
	public static String getCode() {
		return code;
	}
	
	public static void setCode(String newCode) {
		 code = newCode;
	}
	
	public static String getLanguage() {
		return language;
	}
	
	public static void setLanguage(String lang) {
		 language = lang;
	}
	
	public static void clearAll() {
		userName = null;
		passWord = null;
		code = null;
		accessLvl = -1;
	}
	
}
