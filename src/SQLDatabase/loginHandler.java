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
	public int login () {
				if (super.checkUserExists(user) & super.checkPassExists(user,pass)) {
					
					return getAccesslevel();
				} else if(super.checkUserExists(user)) {
					return super.getAccessLevel2(user)==0?0:-1;
				}
				return -1;
	}
	
	/*
	 * register and setup the user in the database no sql needed low level
	 */
	public boolean register() {
		if (!super.checkUserExists(user) & !super.checkPassExists(user,pass)) {
			super.createUser(user, pass);
			return true;
		}
		return false; 
	}
	
	public ArrayList<String> getAllCIDS() {
		return super.getCIDS(user);
	}
	
	public ArrayList<String> getAllUIDS(){
		return super.getUIDS();
	}
	public ArrayList<String[]> getLockedAccts() {
		return super.getLockedAccts();
	}
	public void setAccess(String uid, int access) {
		super.setAccessLevel(uid, access);
	}
	
	/*
     * @return user attempting to be connected
     */
    public int getAccesslevel() {
        return super.getAccessLevel(user, pass);
    }
	
	public CodeInterpreter getInstanceCode(String CID) {
		return super.getCodeInfo(user, pass, CID);
	}
	public void lockAccount() {
		super.lockAccount(user);
	}
}
