package SQLDatabase;
import java.sql.Connection;
import com.jcraft.jsch.*;
import java.sql.*;


import vulnerabilityDetector.attackVector;
public class SQLHandler {
	private static Connection con; 
	private static Statement stmnt;
	//local port being listening in on
	private static int lprt = 3307;
	//port that is remote and getting the information from
	private static int rprt = 3306;
	//remote host server that is being referenced
	private static String rhost = "localhost";
	

	public SQLHandler()
	{
		connectSSHTunnel();
		connectSQL();
	}
	
	/*
	 * Depicition
	 * ip & port	ip@port			port
	 * <Database> -- <SSH Server> -- <Client>
	 * 
	 * this method establishes and creates a tunnel for the information to be listened on
	 */
	private void connectSSHTunnel() {
		//username and password for the ssh server
		String usr = "16097", pass = "bluesky1",host = "71.168.159.51";
		//ssh port
		int port = 22;
		//creates the ssh client
		JSch ssh = new JSch();
		try {
			//creates session starter with information needed to get connected to
			Session session = ssh.getSession(usr,host,port);
			session.setPassword(pass);
			//makes so no auth key is needed
			session.setConfig("StrictHostKeyChecking", "no");
			//establishes the session with the ssh
			session.connect();
			//establishes the ports locally
			int assinged = session.setPortForwardingL(lprt, rhost, rprt);
		} catch (JSchException e) {System.out.println(e);}
		System.out.println("connection established");
	}

	/*
	 * only part of the code that fully establishes to the database and will be ran behind the scences
	 */
	private static void connectSQL() {
	//tester for now
		try {
		//mysql Driver
		Class.forName("com.mysql.cj.jdbc.Driver");  
		
		//Establishes the connection to the database       link to connect to the database (will be a different ip later),user,password
		con = DriverManager.getConnection("jdbc:mysql://"+ rhost + ":" + lprt +"/staticanalysistool","javaConnector","Onelongparty!");
		
		stmnt = con.createStatement();
		
		ResultSet tester = stmnt.executeQuery("select * from user");
		
		while (tester.next()) {
			System.out.println(tester.getString(1)); 
		}
		
		} catch (Exception e) {System.out.println(e);}
	}
	
	public void statement(String sql) {
		try {
			stmnt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/*
	 * Checks if there are any users in the table currently 
	 */
	public boolean checkUsers(){
		try {
			ResultSet tester = stmnt.executeQuery("select * from users");
			if (tester.next()) return true; 
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/*
	 * check if the username exists in side the table
	 */
	public boolean checkUserExists(String user) {
		return false;
	} 
	
	/*
	 * add to the already existing table a username and password
	 */
	public void createUser(String user, String pass) {}	
	
	//adds the code and the proper information asked for to the database
	public void addCode () {}
	
	/*
	 * adds the attack vector to the database
	 * oncreation push to the backlog, if one exists before it delete it
	 */
	public void newattackvector(attackVector vector) {
		
	}
	
	/*
	 * changes access level of user and what they can access in the database
	 */
	public void changeaccesslevel(String user, String password,int access) {
		
	}
}
