package SQLDatabase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import vulnerabilityDetector.attackVector;
public class SQLHandler {
	private static Connection con; 
	private static Statement stmnt;
	public SQLHandler()
	{
		connect();
	}
	
	/*
	 * only part of the code that fully establishes to the database and will be ran behind the scences
	 */
	private static void connect() {
	//tester for now
		try {
		//mysql Driver
		Class.forName("com.mysql.cj.jdbc.Driver");  
		
		//Establishes the connection to the database       link to connect to the database (will be a different ip later),user,password
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/staticanalysistool","javaConnector","Onelongparty!");
		
		stmnt = con.createStatement();
		
		String createTable = "\r\n" + 
				"create table campus(\r\n" + 
				"	campus_name varchar(8) primary key,\r\n" + 
				"	city varchar(25),\r\n" + 
				"	state varchar(23) );";
		stmnt.executeUpdate(createTable);
		
		ResultSet tester = stmnt.executeQuery("select * from test");
		
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
