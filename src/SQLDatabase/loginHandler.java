package SQLDatabase;

import java.util.ArrayList;

import codeInterpration.CodeInterpreter;

public class loginHandler extends SQLHandler {
	private static String user = "";
	private String pass = "";
	public loginHandler(String user, String pass) {
		super ();
		this.user = user;
		this.pass = pass;
	}
	
	/*
	 * @return user attempting to be connected
	 */
	public static String getUser() {
		return user;
	}
	/*
	 * @return pass attempting to be connected
	 */
	public String getPass() {
		return pass;
	}
	/*
	 * user attempting to login into the databases will lock after 3 tries
	 * no sql needed refer to the sql handler
	 */
	public boolean login () {
			int attempts = 0; 
				if (super.checkUserExists(user) & super.checkPassExists(user,pass)) {
					System.out.println("You are logged in!");
					return true;
				} 
				return false;
	}
	
	/*
	 * register and setup the user in the database no sql needed low level
	 */
	public boolean register() {
		if (!super.checkUserExists(user) & super.checkPassExists(user,pass)) {
			super.createUser(user, pass);
			return true;
		} else {System.out.println("user already exists!");}
		return false; 
	}
	
	public ArrayList<Integer> getAllCIDS() {
		return super.getCIDS(user);
	}
	
	public CodeInterpreter getInstanceCode(int CID) {
		return super.getCodeInfo(user, pass, CID);
	}
}
