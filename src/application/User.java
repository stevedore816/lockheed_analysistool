package application;

public class User {
	
	private static String userName;
	private static String passWord;
	private static int accessLvl;
	private static String code;
	
	
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
	
}