import codeInterpration.*;
import vulnerabilityDetector.*;

public class Tester {
	public static void main (String[] args) {
		CodeInterpreter.setCode("SELECT * FROM table_name;"
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
				"");
		
		CodeInterpreter.setLanguage("SQL");
		if (CodeInterpreter.getLanguage().equals("SQL")) {
			sqlAnalyzer sqlLang = new sqlAnalyzer(CodeInterpreter.getCode());
			sqlLang.checkSQLInjection();
		}
	}

}
