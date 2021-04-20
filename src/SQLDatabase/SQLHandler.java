package SQLDatabase;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import codeInterpration.CodeInterpreter;
import vulnerabilityDetector.Type;
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
			//establishes the ports lSteocally
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
	 * Adds information to the backLog in the DB about the code file added the user who added it, and a message from the user
	 * @param cid is the name of the code being added to the DB
	 * @param user is the user who did something to the code being added
	 * @param msg is a message from the user about what they added or changed
	 */
	public void addLogger(String cid, String user, String msg) {
		try {
			String query = "Insert into logger(CID,UID,date,msg) values(?,?,Now(),?)";
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1,cid);
			stm.setString(2,user);
			stm.setString(3, msg);
			int result = stm.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	/*
	 * Adds information to the backLog in the DB about user actions inside of the code file
	 * @param user is the user who did something inside of the application
	 * @param msg is a default message about what was done
	 */
	public void addLogger(String user, String msg) {
		try {
			String query = "Insert into logger(UID,date,msg) values(?,Now(),?)";
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1,user);
			stm.setString(2, msg);
			int result = stm.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* Pushes a code File that a user imported and moves it to the DB
	 * @param user the user that is saving this code inside of the DB 
	 * @oaram name the name the user decides for the file to be pushed to
	 * @param filePath the file path for the code thats being sent to the DB
	 */
	public void pushCodetoDatabase(String user,String name, String filePath) {
		try {
			InputStream inputStream = new FileInputStream(new File(filePath));
			 
			String sql = "INSERT INTO codeFiles values (?,?,?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, name);
			statement.setString(2, user);
			statement.setBlob(3, inputStream);
			statement.executeUpdate();
		} catch (Exception e) {e.printStackTrace();}
	}
	
	/* Pushes a code File that a user imported and moves it to the DB
	 * @param user the user that is saving this code inside of the DB 
	 * @oaram name the name the user decides for the file to be pushed to
	 * @param filePath the file path for the code thats being sent to the DB
	 * @param msg is the message that gets sent to the DB
	 */
	public void pushCodetoDatabase(String user,String name, String filePath,String msg) {
		try {
			InputStream inputStream = new FileInputStream(new File(filePath));
			 
			String sql = "INSERT INTO codeFiles values (?,?,?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, name);
			statement.setString(2, user);
			statement.setBlob(3, inputStream);
			statement.executeUpdate();
			addLogger(user,msg); 
		} catch (Exception e) {e.printStackTrace();}
	}
	/* Gets code File that a user saved in the DB and moves it to their file path
     * @param user the user that is saving this code inside of the DB 
     * @oaram name the name the user decides for the file to be pushed to
     * @param filePath the file path for the code to be output from the DB 
     */
    public void pullCodeFromDatabase(String user,String name, String filePath) {
        try {
            String query = "Select codeFile from codeFiles where Name=? AND UID=?";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setString(1,name);
            stm.setString(2,user);
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                InputStream stream = result.getBlob("codeFile").getBinaryStream();
                OutputStream output = new FileOutputStream(filePath); 
                int readBytes = -1;
                byte[] bytes = new byte[4096]; 
                while((readBytes = stream.read(bytes)) != -1) {
                    output.write(bytes,0,readBytes); 
                }
                output.close();
                stream.close();
            }
        } catch (Exception e) {e.printStackTrace();}
    }
	/* Returns information from the DB about the latest logger information and what users are doing on the DB
	 * @return returns an instance of backlog with the users name, file worked on, message and time the user submitted it at
	 */
	public ArrayList<backLog> getLogger() {
		ArrayList<backLog> list = new ArrayList<backLog>();
		
		try {
			String query = "Select * from logger"; 
			PreparedStatement stm = con.prepareStatement(query);
			ResultSet result = stm.executeQuery(); 
			while(result.next()) {
				int count = 0; 
				String cid = result.getString(1); 
				if (cid == null) { cid = result.getString(2); } 
				String uid = result.getString(3); 
				String date = result.getString(4); 
				String msg = result.getString(5); 
				list.add(new backLog(cid,uid,date,msg)); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return list;
		
	}
	/*
	 * check if the password exists inside the table
	 * @param user the user checking their pass
	 * @param pass the password of the user
	 * @return returns whether or not the pass is in the DB already
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
	/*Removes a user from the DB
	 * @param user user being removed from the DB
	 */
	public void removeUser(String uid) {
		/*
		delete from attackvector where UID = 'Steven';
		delete from coder where UID = 'Steven';
		delete from codeFiles where UID = 'Steven';
		delete from user where UID = 'Steven';
		*/
		int query;
		try {
			query = stmnt.executeUpdate("delete from attackvector where UID ='"+uid+"'");
			query = stmnt.executeUpdate("delete from coder where UID = '"+uid+"'");
			query = stmnt.executeUpdate("delete from codeFiles where UID = '"+uid+"'");
			query = stmnt.executeUpdate("delete from user where UID = '"+uid+"'");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*Removes a txtfile from the DB that the person wants to get rid of
	 * @uid the user removing the file
	 * @CID the name of the file 
	 */
	public void removeCID(String uid,String CID) {
		/*
		delete from attackvector where UID = 'Steven';
		delete from coder where UID = 'Steven';
		delete from codeFiles where UID = 'Steven';
		delete from user where UID = 'Steven';
		*/
		int query;
		try {
			query = stmnt.executeUpdate("delete from coder where UID = '"+uid+"' and CID = '"+CID+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*Removes a file from the DB that the person wants to get rid of
	 * @uid the user removing the file
	 * @CID the name of the file 
	 */
	public void removeName(String uid,String name) {
		/*
		delete from attackvector where UID = 'Steven';
		delete from coder where UID = 'Steven';
		delete from codeFiles where UID = 'Steven';
		delete from user where UID = 'Steven';
		*/
		int query;
		try {
			query = stmnt.executeUpdate("delete from codeFiles where UID = '"+uid+"' and Name = '"+name+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * add to the already existing table a username and password
	 * @param user the user being created or registered to the DB
	 * @param pass the pass being created and associated with said user
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
	
	/*Adds Essential code information in text style format to the datbase
	 * @code essential information like the text file for the code, the name of the code, the language
	 * @msg the message the user would like to send to the backlog
	 */
	public void addCode (CodeInterpreter code, String msg) {
		if (msg.equals("")) {
			System.out.println("Sent data over to this datbase");
		}
		try {
			String cdinfo = code.getCode();
			String lang = code.getLanguage();
			String cid = code.getCID();
			String uid = code.getUser();
			//System.out.println(cid +  ", " + uid + ", " + lang + ", " + cdinfo);
			String query = "Insert into coder(CID,UID,lang,cdinfo) values(?,?,?,?)";
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1, cid);
			stm.setString(2, uid);
			stm.setString(3, lang);
			stm.setString(4, cdinfo);
			int result = stm.executeUpdate();
			//System.out.println("Sent over to the database!"); 
			addLogger(cid, uid, msg);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * adds the attack vector to the database
	 * oncreation push to the backlog, if one exists before it delete it
	 * @vector new attack vector being pushed to the DB 
	 * @code instance of code needed to be inserted into the DB
	 */
	public void newattackvector(attackVector vector, CodeInterpreter code) {
		try{
			String query = "Insert into attackVector(line,type,reason,CID,UID) values(?,?,?,?,?)";
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1,vector.getVCode());
			stm.setString(2,vector.getType().toString());
			stm.setString(3,vector.getReason());
			String uid = code.getUser();
			String cid = code.getCID();
			stm.setString(4, cid);
			stm.setString(5, uid);
			int res = stm.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * changes access level of user and what they can access in the database
	 * @param user the user being changed
	 * @password password of the user being changed
	 * @access the accesslevel being set for the user 
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
	 * Gets the access level of the user to be checked down the line
	 * @username the user that is getting access level checked on
	 * @password the password being checked on
	 */
	public static int getAccessLevel(String username, String password) {
		try {	
			ResultSet query = stmnt.executeQuery("select access from user where UID = '" + username + "' and password = '"+password+"'");
			while (query.next()) {
				return query.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -2;
	}
	/*
	 * Gets the access level of the user to be checked down the line
	 * @username username getting accesslevel checked on 
	 */
	public static int getAccessLevel2(String username) {
		try {	
			ResultSet query = stmnt.executeQuery("select access from user where UID = '" + username +"'");
			while (query.next()) {
				return query.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -2;
	}
	/*Gets the names of all the files with the associated user
	 * @username the user that is requesting their files
	 * @return the name of all the files with a associated user
	 */
	public ArrayList<String> getCIDS(String username) {
		ArrayList<String> ans = new ArrayList<String>();
		ResultSet query;
		try {
			query = stmnt.executeQuery("select CID from coder where UID = '"+username+"'");
			while (query.next()) {
				ans.add(query.getString(1));
			}
			return ans;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Not sure if it was completed some error occured");
		return ans;
	}
	/*Gets the names of all the physical files that were pushed to the DB of an associated user
	 * @param username the user that is requesting the files
	 * @return names of all the files in the table codeFile of the DB 
	 */
	public ArrayList<String> getNames(String username) {
		ArrayList<String> ans = new ArrayList<String>();
		ResultSet query;
		try {
			query = stmnt.executeQuery("select Name from codeFiles where UID = '"+username+"'");
			while (query.next()) {
				ans.add(query.getString(1));
			}
			return ans;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Not sure if it was completed some error occured");
		return ans;
	}
	
	/*Sets the accessLevel of the user
	 * @uid the user getting the access changed
	 * @access the access of the user getting there level changed 
	 */
	public void setAccessLevel(String uid, int access) {
		int query;
		try {
			query = stmnt.executeUpdate("Update user set access='"+access+"'where UID='"+uid+"'");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*returns all the users inside of the DB
	 * @return all the users that are inside the DB
	 */
	public ArrayList<String> getUIDS() {
		ArrayList<String> ans = new ArrayList<>();
		ResultSet query;
		try {
			query = stmnt.executeQuery("select UID from user");
			while (query.next()) {
				ans.add(query.getString(1));
			}
			return ans;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Not sure if it was completed some error occured");
		return ans;
	}
	/*
	 * method that grabs all the information about the code from the database and set the code to it
	 * (Its going to need code)
	 * @param username the username being checked
	 * @param password the password being checked
	 * @codeID the code being pulled from the DB 
	 * @return the information that is needed about the code
	 */
	public CodeInterpreter getCodeInfo(String username,String password, String codeID) {
		try {								//select query needs some more work
			ResultSet query = stmnt.executeQuery("select * from coder where CID = '" + codeID + "' AND UID = '" + username + "'");
			while (query.next()) {
				String code = (query.getString(3));
				String cid = (query.getString(1));
				String user = (query.getString(2));
				String lang = (query.getString(4));
				int accesslevel = getAccessLevel(username,password);
				CodeInterpreter newCode = new CodeInterpreter(code, lang ,user,accesslevel,cid);
				addLogger(cid, user, "Pulled from the database!");
				getVectorInfo(newCode); 
				return newCode;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
			
	}
	
	/*
	 * method that grabs all the information about the code from the database and set the code to it for admins
	 * w/out an accessLevel
	 * (Its going to need code)
	 * @param username the username being checked
	 * @codeID the code being pulled from the DB 
	 * @return the information that is needed about the code
	 */
	public CodeInterpreter getCodeInfo(String username, String codeID) {
		try {								//select query needs some more work
			ResultSet query = stmnt.executeQuery("select * from coder where CID = '" + codeID + "' AND UID = '" + username + "'");
			while (query.next()) {
				String code = (query.getString(3));
				String cid = (query.getString(1));
				String user = (query.getString(2));
				String lang = (query.getString(4));
				CodeInterpreter newCode = new CodeInterpreter(code, lang ,user,1,cid);
				addLogger(cid, user, "Pulled from the database!");
				getVectorInfo(newCode); 
				return newCode;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
			
	}
	/*@return Gets all instances of Code from the DB into a array
	 * 
	 */
	public ArrayList<CodeInterpreter> getAllTxt(){
		ArrayList<CodeInterpreter> all = new ArrayList<CodeInterpreter>(); 
		try {								//select query needs some more work
			ResultSet query = stmnt.executeQuery("select * from coder ");
			while (query.next()) {
				String code = (query.getString(3));
				String cid = (query.getString(1));
				String user = (query.getString(2));
				String lang = (query.getString(4));
				CodeInterpreter newCode = new CodeInterpreter(code, lang ,user,1,cid);
				//addLogger(cid, user, "Pulled from the database!");
				//getVectorInfo(newCode); 
				all.add(newCode); 
			}
			return all;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
		}
	/*returns a list of String arrays of size 2 containing {Name,UID} for each file that exists in the DB
	 * @return String arrays of size 2 containing {Name,UID} for each file that exists in the DB
	 */
	public ArrayList<String[]> getAllFiles(){
		ArrayList<String[]> wtv = new ArrayList<String[]>(); 
 		String[] all = new String[2]; 
		try {								//select query needs some more work
			ResultSet query = stmnt.executeQuery("select * from codeFiles");
			while (query.next()) {
				String Name = (query.getString(1));
				String UID = (query.getString(2));
				all[0] = Name;
				all[1] = UID; 
				wtv.add(all); 
			}
			return wtv;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
		}
	/*
	 * Gets all the locked accts inside of the DB
	 * @return all the names locked in the DB 
	 */
	public ArrayList <String[]> getLockedAccts () {
		ArrayList<String[]> lockedAccts = new ArrayList<String[]>(); 
		try {	
			ResultSet query = stmnt.executeQuery("select UID, password from user where access = 0");
			while (query.next()) {
				String[] userpass = {query.getString(1), query.getString(2)};  
				lockedAccts.add(userpass);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lockedAccts; 
	}
	/*
	 * Locks a user from accessing the DB
	 * @param user user being revoked access from the DB 
	 */
	public void lockAccount(String user) {
		try{
			String query = "Update user set access = 0 where UID=?";
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1,user);
			int result = stm.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
			
	}
	/*
	 * Gets important information about the attack vector from the DB
	 * @param code the code asking for the attack vector
	 */
	private static void getVectorInfo(CodeInterpreter code) {
		try {								//select query needs some more work
			ResultSet query = stmnt.executeQuery("select * from attackvector where CID = '" + code.getCID() + "' AND UID = '" + code.getUser() + "'");
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
				String CID = query.getString(4);
				String UID = query.getString(5);
				
				code.addAttack(new attackVector(line,type,reason));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
