package SQLDatabase;
import com.jcraft.jsch.*;

import codeInterpration.CodeInterpreter;

import java.sql.*;
import java.util.Random;

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
		
		try{
			String query = "Select * from user where UID=?";
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1,user);
			ResultSet result = stm.executeQuery();
			if(result.next()) return true;			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	} 
	
	/*
	 * check if the password exists inside the table
	 */
	public boolean checkPassExists(String user, String pass) {
		try{
			String query = "Select * from user where UID=? AND password=?";
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1,user);
			stm.setString(2,pass);
			ResultSet result = stm.executeQuery();
			if(result.next()) return true;			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	/*
	 * add to the already existing table a username and password
	 */
	public void createUser(String user, String pass) {
		try {
			String query = "Insert into user(UID,password) values(?,?)";
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1,user);
			stm.setString(2,pass);
			int result = stm.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}	
	
	//adds the code and the proper information asked for to the database
	public void addCode () {
		try {
			String cdinfo = CodeInterpreter.getCode();
			Random rand = new Random();
			/*Check the randomly generated CID isn't already in the table*/
			int cid;
			while(true) {
				cid = rand.nextInt(9999);
				ResultSet result = con.createStatement().executeQuery("Select CID from coder where CID="+cid);
				if(result.next()){}
				else {break;}
			}
			String query = "Insert into coder(CID,UID,cdinfo) values(?,?,?)";
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, cid);
			stm.setString(2, getUser());
			stm.setString(3, cdinfo);
			int result = con.prepareStatement(query).executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getUser() {
		return "";
	}
	/*
	 * adds the attack vector to the database
	 * oncreation push to the backlog, if one exists before it delete it
	 */
	public void newattackvector(attackVector vector) {
		try{
			String query = "Insert into attackVector(start,end,type,resource,CID,UID) values(?,?,?,?,?,?)";
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1,vector.getStart());
			stm.setInt(2,vector.getEnd());
			stm.setString(3,vector.getType().toString());
			stm.setString(4,vector.getResource());
			String uid = getUser();
			ResultSet result = con.createStatement().executeQuery("Select CID from coder where UID="+uid);
			stm.setInt(5, result.getInt(1) );
			stm.setString(6, uid);
			int res = stm.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * changes access level of user and what they can access in the database
	 */
	public void changeaccesslevel(String user, String password,int access) {
		try{
			String query = "Update user set access = ? where UID=?";
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1,access);
			stm.setString(2,user);
			int result = stm.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
			
	}
}
