package SQLDatabase;
import com.jcraft.jsch.*;

import codeInterpration.CodeInterpreter;

import java.sql.*;
import java.util.Random;

import vulnerabilityDetector.Type;
import vulnerabilityDetector.attackVector;
import vulnerabilityDetector.cyberAttacks;
public class SQLHandler {
	private static Connection con; 
	private static Statement stmnt;
	//local port being listening in on
	private static int lprt = 3307;
	//port that is remote and getting the information from
	private static int rprt = 3306;
	//remote host server that is being referenced
	private static String rhost = "localhost";
	boolean connected = false; 
	

	public SQLHandler()
	{
		if (!connected) {
		connectSSHTunnel();
		connectSQL();
		connected = true;
		}
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
		} catch (JSchException e) { }
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
		
	
		
		} catch (Exception e) {}
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
			String query = "Insert into user(UID,password,access) values(?,?,?)";
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1,user);
			stm.setString(2,pass);
			stm.setInt(3, 1);
			int result = stm.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}	
	
	//adds the code and the proper information asked for to the database
	public void addCode (CodeInterpreter code) {
		try {
			String cdinfo = code.getCode();
			String lang = code.getLanguage();
			int cid = code.getCID();
			String uid = code.getUser();
			//System.out.println(cid +  ", " + uid + ", " + lang + ", " + cdinfo);
			String query = "Insert into coder(CID,UID,lang,cdinfo) values(?,?,?,?)";
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, cid);
			stm.setString(2, uid);
			stm.setString(3, lang);
			stm.setString(4, cdinfo);
			System.out.println("Sent over to the database!"); 
			int result = stm.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * adds the attack vector to the database
	 * oncreation push to the backlog, if one exists before it delete it
	 */
	public void newattackvector(attackVector vector, CodeInterpreter code) {
		try{
			String query = "Insert into attackVector(line,types,reason,CID,UID) values(?,?,?,?,?)";
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1,vector.getVCode());
			stm.setString(2,vector.getType().toString());
			stm.setString(3,vector.getReason());
			String uid = code.getUser();
			int cid = code.getCID();
			stm.setInt(4, cid);
			stm.setString(5, uid);
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
	/*
	 * method that grabs all the information about the code from the database and set the code to it
	 * (Its going to need code)
	 */
	public static CodeInterpreter getCodeInfo(String username, int codeID) {
		try {								//select query needs some more work
			ResultSet query = stmnt.executeQuery("select * from coder where CID = " + codeID + " AND UID = '" + username + "'");
			while (query.next()) {
				String code = (query.getString(3));
				int cid = (query.getInt(1));
				String user = (query.getString(2));
				String lang = (query.getString(4));
				CodeInterpreter newCode = new CodeInterpreter(code, lang ,user,0,cid);
				getVectorInfo(newCode); 
				return newCode;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
			
	}
	private static void getVectorInfo(CodeInterpreter code) {
		cyberAttacks.removeall();
		try {								//select query needs some more work
			ResultSet query = stmnt.executeQuery("select * from attackvector where CID = " + code.getCID() + " AND UID = '" + code.getUser() + "'");
			while (query.next()) {
				String line = query.getString(1);
				String str = query.getString(2);
				Type type = Type.BUFFEROVERFLOW;
				if(str.equals("INPUTVALIDATION")) {
					type = Type.INPUTVALIDATION; 
				} else if (str.equals("MEMORYLEAK")) {
					type = Type.MEMORYLEAK;
				} else if (str.equals("BUFFEROVERFLOW")) {
					type = Type.BUFFEROVERFLOW;
				} else if (str.equals("RANDGEN")) {
					type = Type.RANDGEN;
				} else if (str.equals("STRINGFORMAT")) {
					type = Type.STRINGFORMAT;
				} 
				String reason = query.getString(3);
				int CID = query.getInt(4);
				String UID = query.getString(5);
				
				code.addAttack(new attackVector(line,type,reason));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
