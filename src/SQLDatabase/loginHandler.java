package SQLDatabase;
public class loginHandler {
	private String user = "";
	private String pass = "";
	
	public loginHandler(String user, String pass,int maxAttempts, boolean newUser) {
		this.user = user;
		this.pass = pass;
		if (newUser) {
			register();
		} else {
			int attempts = 0; 
			while (attempts != maxAttempts) {
				if (login()) break; else {
					System.out.println("Failed to log you in please retype password and try again!") ; 
					attempts ++; 
				}
			}
			if (attempts == maxAttempts) {System.out.println("Login not accepted! Please register or wait for an admin");} 
		}
	}
	
	/*
	 * @return user attempting to be connected
	 */
	public String getUser() {
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
	private boolean login () {
		return SQLHandler.checkUserExists(user) & SQLHandler.checkPass(user,pass);
	}
	
	/*
	 * register and setup the user in the database no sql needed low level
	 */
	private void register() {
		SQLHandler.createUser(user, pass);
	}
}
