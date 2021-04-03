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
	String code = "String query = \"SELECT account_balance FROM user_data WHERE user_name = ?\";\r\n"
			+ "try {\r\n"
			+ "  OleDbCommand command = new OleDbCommand(query, connection);\r\n"
			+ "  command.Parameters.Add(new OleDbParameter(\"customerName\", CustomerName Name.Text));\r\n"
			+ "  OleDbDataReader reader = command.ExecuteReader();\r\n"
			+ "  // …\r\n"
			+ "} catch (OleDbException se) {\r\n"
			+ "  // error handling\r\n"
			+ "}";
	String language = "Java";
	CodeInterpreter newCode = new CodeInterpreter(code,language,con.getUser());
	newCode.analyzeCode();
	System.out.println(newCode.getAttackString());
	}
	
	public static void main (String[] args) {
		demonstrate();
	}
	
	
}
