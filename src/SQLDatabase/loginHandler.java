package SQLDatabase;
public class loginHandler extends SQLHandler {
	private static String user = "";
	private String pass = "";
	public loginHandler(String user, String pass,int maxAttempts, boolean newUser) {
		super ();
		this.user = user;
		this.pass = pass;
		if (newUser) {
			register();
		} else {
			int attempts = 0; 
			while (attempts != maxAttempts) {
				if (login()) {
					System.out.println("You are logged in!");
					break;
				} else {
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
	private boolean login () {
		return super.checkUserExists(user) & super.checkPassExists(user,pass);
	}
	
	/*
	 * register and setup the user in the database no sql needed low level
	 */
	private void register() {
		if (!login()) {
		super.createUser(user, pass);
		} else {System.out.println("user already exists!");}
	}
}
