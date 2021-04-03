import SQLDatabase.SQLHandler;
import SQLDatabase.loginHandler;
import codeInterpration.*;
import vulnerabilityDetector.*;

public class Tester {
	
	public static String[] loginString() {
		String [] smthing = {"user", "password"}; 
		return smthing; 
	}
	
	public static void demonstrate() {
	loginHandler con = new loginHandler(loginString()[0],loginString()[1]);
	con.login();
	String code = "SELECT * FROM table_name;"
			+ "\ntxtUserId = getRequestString(\"UserId\");\r\n" + 
			"txtSQL = \"SELECT * FROM Users WHERE UserId = \" + txtUserId; "
			+ "\nuName = getRequestString(\"username\");\r\n" + 
			"uPass = getRequestString(\"userpassword\");\r\n" + 
			"\r\n" + 
			"sql = 'SELECT * FROM Users WHERE Name =\"' + uName + '\" AND Pass =\"' + uPass + '\"'"
			+ "\n SELECT CustomerName, City FROM Customers;"
			+ "\n String Adams = Adams, John\r\n" + 
			"txtSQL2 = \"SELECT * FROM Users WHERE UserId = \" + txtUserId;\r\n" + 
			"\r\n" + 
			"String txtSQL = \"SELECT * FROM Users WHERE UserId = \" + txtUserId;\r\n" + 
			"\r\n" + 
			"try (PreparedStatement stmt = conn.prepareStatement(txtSQL)\r\n" + 
			"\r\n" + 
			"prepared(txtSQL)\r\n" + 
			"mysql_stmt_init(txtSQL)\r\n" + 
			"\r\n" + 
			"mysql_stmt_init() \r\n" + 
			"";
	String language = "sql";
	CodeInterpreter newCode = new CodeInterpreter(code,language,con.getUser());
	newCode.analyzeCode();
	System.out.println(newCode.getAttackString());
	}
	
	public static void main (String[] args) {
		demonstrate();
	}
	
	
}
