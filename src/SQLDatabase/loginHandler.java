package SQLDatabase;

public class loginHandler {
	private String user = "";
	private String pass = "";
	
	public loginHandler(String user, String pass) {
		super();
		this.user = user;
		this.pass = pass;
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
	public void login () {
		
	}
	
	/*
	 * register and setup the user in the database no sql needed 
	 */
	public void register() {
		
	}
}
