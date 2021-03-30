import SQLDatabase.SQLHandler;
import SQLDatabase.loginHandler;
import codeInterpration.*;
import vulnerabilityDetector.*;

public class Tester {
	
	public String[] loginString() {
		String [] smthing = {"user", "password"}; 
		return smthing; 
	}
	
	public void demonstrate() {
	loginHandler con = new loginHandler(loginString()[0],loginString()[1]);
	con.login(2);
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
	newCode.getAttackString();
	}
	
	public static void main(String[] args) {
		loginHandler con = new loginHandler("Steven","Arroyo");
		con.login(3);
		CodeInterpreter code = new CodeInterpreter();
		CodeInterpreter.setUser(loginHandler.getUser());
		
		
		pullinfo(33,code);
		
		pullinfo(211,code);
		
		pullinfo(1059,code);
		
		pullinfo(1088,code);
		
		pullinfo(2207,code);
		
		pullinfo(3536,code);
		
		pullinfo(3744,code);
		
		pullinfo(3901,code);
		
		pullinfo(4076,code);
		
		pullinfo(4241,code);
		
		pullinfo(5504,code);
		
		pullinfo(5560,code);
		
		pullinfo(8520,code);
		
		pullinfo(8816,code);
		
		
	}
	
	public static void pullinfo(int CID, CodeInterpreter code) {
		code.pullfromDataBase(CID);
		System.out.println("CID: " + CodeInterpreter.getCID());
		System.out.println("UID: " + CodeInterpreter.getUser());
		System.out.println("Language: " + CodeInterpreter.getLanguage());
		System.out.println("Code: " + CodeInterpreter.getCode());
		System.out.println("Attack Vectors\n " + cyberAttacks.getString());
	}
	
	public static void loadData () {
		
		//SQLHandler database = new SQLHandler();
		loginHandler con = new loginHandler("Steven","Arroyo");
		CodeInterpreter code = new CodeInterpreter();
		
		
		
		System.out.println("\nSQL Sample 1: ");
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
		CodeInterpreter.setUser(con.getUser());
		if (CodeInterpreter.getLanguage().equals("SQL")) {
			sqlAnalyzer sql = new sqlAnalyzer(CodeInterpreter.getCode());
			sql.checkInputValidation();
			System.out.println(cyberAttacks.getString()); 
			System.out.println("Pushing data over to the database..."); 
			code.pushToDataBase();
			cyberAttacks.pushDatabase();
			System.out.println("Successfully pushed to the database");
			cyberAttacks.removeall();
		}
			
		/*
		 * Java SQL Injection possible, no prepared statements 
		 */
		System.out.println("\nJava Sample 1: ");
		CodeInterpreter.setCode(
				"import java.sql.*; " +

				"public class select " +
				" { " + 
				" public static void main(String args[]) " +
				" { " + 
				" String id = \"id1\"; " +
				" String pwd = \"pwd1\"; " +
				"try " +
				" { " +
				"cur.execute(\"SELECT * FROM user WHERE id=%s\" % (id,))" +
				"Connection con = DriverManager.getConnection(" +
				" jdbc:oracle:thin:@localhost:1521:orcl\", \"login1\", \"pwd1\"); " +
				" Statement stmt = con.createStatement(); " +
				" String q1 = \"SELECT * FROM userid WHERE id = '\" + id + " + 
				" \"' AND pwd = '\" + pwd + \"'\"; \n" +
				" ResultSet rs = stmt.executeQuery(q1); " + 
				" if (rs.next()) " +
				"   { " +
				" System.out.println(\"User-Id : \" + rs.getString(1)); " +
				"  System.out.println(\"Full Name :\" + rs.getString(3)); " +
				"  System.out.println(\"E-mail :\" + rs.getString(4)); " +
				" } " +
				"  else" +
				" { " +
				"   System.out.println(\"No such user id is already registered\"); " +
				"   } " +
				"   con.close(); " +
				"    } " +
				"   catch(Exception e) " +
				"    { " +
				"        System.out.println(e); " +
				"  } " +
				"   } " +
				"	} ");
		
		CodeInterpreter.setLanguage("Java");
		if (CodeInterpreter.getLanguage().equals("Java")) {
			javaAnalyzer java = new javaAnalyzer();
			java.checkInputValidation();
			//System.out.println(cyberAttacks.getString()); 
			System.out.println("Pushing data over to the database..."); 
			code.pushToDataBase();
			cyberAttacks.pushDatabase();
			System.out.println("Successfully pushed to the database");
			cyberAttacks.removeall();
		}

		/*
		 * Has prepared Statement
		 */
		System.out.println("\nJava Sample 2: ");
		CodeInterpreter.setCode(
				"public class PreparedStatementSelectTester{ \n" +
						"public static void main(String[] args) { \n" +
						" String JdbcURL = \"jdbc:mysql://localhost:3306/sample?useSSL=false\"; \n" +
						" String Username = \"root\"; \n" +
						" String password = \"123456\"; \n" +
						" Connection con = null; \n" +
						" PreparedStatement pstmt = null; \n" +
						" ResultSet rst = null;\n" +
						" String myQuery = \"SELECT Id,Name,Age FROM JavaPreparedStatement\";\n" +
						"try {\\n" +
						"con = DriverManager.getConnection(JdbcURL, Username, password);\n" +
						"pstmt = con.prepareStatement(myQuery);\\n" +
						"rst = pstmt.executeQuery();\\n" +
						"System.out.println(\"Id\t\tName\t\tAge\n\");\n" +
						"while(rst.next()) {\n" +
						"System.out.print(rst.getInt(1));\\n" +
						"System.out.print(\"\t\t\"+rst.getString(2));\n" +
						"System.out.print(\"\t\t\"+rst.getInt(3));\n" +
						"System.out.println();\n" +
						"}" +
						"} catch(Exception exec) {\n" +
						"exec.printStackTrace();\n" +
						"}\n" +
						"}\n" +
				"}\n" );

		CodeInterpreter.setLanguage("Java");
		if (CodeInterpreter.getLanguage().equals("Java")) {
			javaAnalyzer java = new javaAnalyzer();
			java.checkInputValidation();
			System.out.println(cyberAttacks.getString()); 
			System.out.println("Pushing data over to the database..."); 
			code.pushToDataBase();
			cyberAttacks.pushDatabase();
			System.out.println("Successfully pushed to the database");
			cyberAttacks.removeall();
		}
		System.out.println("\nJava Sample 3: ");
		CodeInterpreter.setCode(
				"\r\n" + 
				"String GenerateReceiptURL(String baseUrl) {\r\n" + 
				"    Random ranGen = new Random();\r\n" + 
				"    ranGen.setSeed((new Date()).getTime());\r\n" + 
				"    return(baseUrl + Gen.nextInt(400000000) + \".html\");\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"c code vulnerable to random number attack\r\n" + 
				"\r\n" + 
				"#include <stdio.h>\r\n" + 
				"#include <stdlib.h>\r\n" + 
				"enum { len = 12 }; \r\n" + 
				"void func(void) {\r\n" + 
				"  /*\r\n" + 
				"   * id will hold the ID, starting with the characters\r\n" + 
				"   *  \"ID\" followed by a random integer.\r\n" + 
				"   */\r\n" + 
				"  char id[len]; \r\n" + 
				"  int r;\r\n" + 
				"  int num;\r\n" + 
				"  /* ... */\r\n" + 
				"  r = rand();  /* Generate a random integer */\r\n" + 
				"  num = snprintf(id, len, \"ID%-d\", r);  /* Generate the ID */\r\n" + 
				"  /* ... */\r\n" + 
				"}" );

		CodeInterpreter.setLanguage("Java");
		if (CodeInterpreter.getLanguage().equals("Java")) {
			javaAnalyzer java = new javaAnalyzer();
			java.checkInputValidation();
			java.findRand();
			System.out.println(cyberAttacks.getString()); 
			System.out.println("Pushing data over to the database..."); 
			code.pushToDataBase();
			cyberAttacks.pushDatabase();
			System.out.println("Successfully pushed to the database");
			cyberAttacks.removeall();
		}
		/*
		 * Python Example (non functional)
		 */
		System.out.println("\nPython Sample 1: ");
		CodeInterpreter.setCode(
				"def login():" +
						" username = request.args.get('username', '')" +
						" password = request.args.get('password', '')" +
						" md5 = hashlib.new('md5', password.encode('utf-8'))" +
						" password = md5.hexdigest()" +
						" c = CONNECTION.cursor()" +
						" cursor.execute(\"SELECT * FROM TEST WHERE ID = '%s'\" % id)\n" +
						" data = c.fetchone()" +
						" if data is None:" +
						"  return 'Incorrect username and password.'" +
						" else:" +
						   "return 'Welcome %s! Your rank is %s.' % (username, data[2])" );

		CodeInterpreter.setLanguage("Python");
		if (CodeInterpreter.getLanguage().equals("Python")) {
			pythonAnalyzer py = new pythonAnalyzer();
			py.checkInputValidation();
			System.out.println(cyberAttacks.getString()); 
			System.out.println("Pushing data over to the database..."); 
			code.pushToDataBase();
			cyberAttacks.pushDatabase();
			System.out.println("Successfully pushed to the database");
			cyberAttacks.removeall();
		}
		
		/*
		 * Python Example, with parameterized statement (functional)
		 */
		System.out.println("\nPython Sample 2: ");
		CodeInterpreter.setCode(
				"def login():" +
						" username = request.args.get('username', '')" +
						" password = request.args.get('password', '')" +
						" md5 = hashlib.new('md5', password.encode('utf-8'))" +
						" password = md5.hexdigest()" +
						" c = CONNECTION.cursor()" +
						" cursor.execute(\"SELECT * FROM TEST WHERE ID = %s\", (id))\n" +
						" data = c.fetchone()" +
						" if data is None:" +
						"  return 'Incorrect username and password.'" +
						" else:" +
						   "return 'Welcome %s! Your rank is %s.' % (username, data[2])" );

		CodeInterpreter.setLanguage("Python");
		if (CodeInterpreter.getLanguage().equals("Python")) {
			pythonAnalyzer py = new pythonAnalyzer();
			py.checkInputValidation();
			System.out.println(cyberAttacks.getString()); 
			System.out.println("Pushing data over to the database..."); 
			code.pushToDataBase();
			cyberAttacks.pushDatabase();
			System.out.println("Successfully pushed to the database");
			cyberAttacks.removeall();
		}
		
		System.out.println("\nPython Sample 3: ");
		CodeInterpreter.setCode(
				"import os, random, string\r\n" + 
				"\r\n" + 
				"length = 13\r\n" + 
				"chars = string.ascii_letters + string.digits + '!@#$%^&*()'\r\n" + 
				"random.seed = (random(1024))\r\n" + 
				"\r\n" + 
				"print ''.join(random.choice(chars) for i in range(length))\r\n" + 
				"\r\n" + 
				"String format vulnerable c code\r\n" + 
				"#include  <stdio.h> \r\n" + 
				"void main(int argc, char **argv)\r\n" + 
				"{\r\n" + 
				"	// This line is safe\r\n" + 
				"	printf(\"%s\\n\", argv[1]);\r\n" + 
				"\r\n" + 
				"	// This line is vulnerable\r\n" + 
				"	printf(argv[1]);\r\n" + 
				"}" );

		CodeInterpreter.setLanguage("Python");
		if (CodeInterpreter.getLanguage().equals("Python")) {
			pythonAnalyzer py = new pythonAnalyzer();
			py.checkInputValidation();
			py.findRand();
			System.out.println(cyberAttacks.getString()); 
			System.out.println("Pushing data over to the database..."); 
			code.pushToDataBase();
			cyberAttacks.pushDatabase();
			System.out.println("Successfully pushed to the database");
			cyberAttacks.removeall();
		}
		
		System.out.println("\nC++ Sample 1: ");
		/*
		 * C++ code
		 */
		CodeInterpreter.setCode( "char* str = new char [30];\n"+ "str = new char [60];\n" + "delete [] str;\n"+ "int main ()\r\n" + 
				"{\r\n" + 
				"    MYSQL *conn; /* pointer to connection handler */\r\n" + 
				"    MYSQL_RES *res; /* holds the result set */\r\n" + 
				"    MYSQL_ROW row;\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"    /* INITIALIZE CONNECTION HANDLER, DO NOT CHANGE */\r\n" + 
				"    conn = mysql_init (NULL);\r\n" + 
				"\r\n" + 
				"    /* THIS CONNECTS TO SERVER, DO NOT CHANGE ANYTHING HERE */\r\n" + 
				"    mysql_real_connect (conn, opt_host_name, opt_user_name, opt_password,\r\n" + 
				"    opt_db_name, opt_port_num, opt_socket_name, opt_flags);\r\n" + 
				"    /* show tables in the database (test for errors also) */\r\n" + 
				"    mysql_query(conn, \"SELECT Password FROM Users WHERE Name = 'MY_NICKNAME'\");\r\n" + 
				"    res = mysql_store_result(conn);");
		CodeInterpreter.setLanguage("C++");
		
		if (CodeInterpreter.getLanguage().equals("C++")) {
			cppAnalyzer cpp = new cppAnalyzer();
			cpp.checkInputValidation();
			cpp.checkMemoryLeak(CodeInterpreter.getCode());
			System.out.println(cyberAttacks.getString()); 
			System.out.println("Pushing data over to the database..."); 
			code.pushToDataBase();
			cyberAttacks.pushDatabase();
			System.out.println("Successfully pushed to the database");
			cyberAttacks.removeall();
		}
		
		System.out.println("\nC++ Sample 2: ");
		/*
		 * C++ Prepared Statement
		 */
		CodeInterpreter.setCode("int* ptr = new int(5);\n" +"int main(void)\r\n" + 
				"{\r\n" + 
				"cout << endl;\r\n" + 
				"cout << \"Let's have MySQL count from 10 to 1...\" << endl;\r\n" + 
				"\r\n" + 
				"try {\r\n" + 
				"  sql::Driver *driver;\r\n" + 
				"  sql::Connection *con;\r\n" + 
				"  sql::Statement *stmt;\r\n" + 
				"  sql::ResultSet *res;\r\n" + 
				"  sql::PreparedStatement *pstmt;\r\n" + 
				"\r\n" + 
				"  /* Create a connection */\r\n" + 
				"  driver = get_driver_instance();\r\n" + 
				"  con = driver->connect(\"tcp://127.0.0.1:3306\", \"root\", \"root\");\r\n" + 
				"  /* Connect to the MySQL test database */\r\n" + 
				"  con->setSchema(\"test\");\r\n" + 
				"\r\n" + 
				"  stmt = con->createStatement();\r\n" + 
				"  stmt->execute(\"DROP TABLE IF EXISTS test\");\r\n" + 
				"  stmt->execute(\"CREATE TABLE test(id INT)\");\r\n" + 
				"  delete stmt;\r\n" + 
				"\r\n" + 
				"  /* '?' is the supported placeholder syntax */\r\n" + 
				"  pstmt = con->prepareStatement(\"INSERT INTO test(id) VALUES (?)\");\r\n" + 
				"  for (int i = 1; i <= 10; i++) {\r\n" + 
				"    pstmt->setInt(1, i);\r\n" + 
				"    pstmt->executeUpdate();\r\n" + 
				"  }\r\n" + 
				"  delete pstmt;\r\n" + 
				"\r\n" + 
				"  /* Select in ascending order */\r\n" + 
				"  pstmt = con->prepareStatement(\"SELECT Password FROM Users WHERE Name = 'MY_NICKNAME'\");\r\n" + 
				"  res = pstmt->executeQuery();\r\n" + 
				"\r\n" + 
				"  /* Fetch in reverse = descending order! */\r\n" + 
				"  res->afterLast();\r\n" + 
				"  while (res->previous())\r\n" + 
				"    cout << \"\\t... MySQL counts: \" << res->getInt(\"id\") << endl;\r\n" + 
				"  delete res;\r\n" + 
				"\r\n" + 
				"  delete pstmt;\r\n" + 
				"  delete con;\r\n" + 
				"\r\n" + 
				"} catch (sql::SQLException &e) {\r\n" + 
				"  cout << \"# ERR: SQLException in \" << __FILE__;\r\n" + 
				"  cout << \"(\" << __FUNCTION__ << \") on line \" »\r\n" + 
				"     << __LINE__ << endl;\r\n" + 
				"  cout << \"# ERR: \" << e.what();\r\n" + 
				"  cout << \" (MySQL error code: \" << e.getErrorCode();\r\n" + 
				"  cout << \", SQLState: \" << e.getSQLState() << »\r\n" + 
				"     \" )\" << endl;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"cout << endl;\r\n" + 
				"\r\n" + 
				"return EXIT_SUCCESS;\r\n" + 
				"}");
		CodeInterpreter.setLanguage("C++");
		if (CodeInterpreter.getLanguage().equals("C++")) {
			cppAnalyzer cpp = new cppAnalyzer();
			cpp.checkInputValidation();
			cpp.checkMemoryLeak(CodeInterpreter.getCode());
			System.out.println(cyberAttacks.getString()); 
			System.out.println("Pushing data over to the database..."); 
			code.pushToDataBase();
			cyberAttacks.pushDatabase();
			System.out.println("Successfully pushed to the database");
			cyberAttacks.removeall();
		}
		
		/*
		 * C sample
		 */
		System.out.println("\nC Sample 1: ");
		CodeInterpreter.setCode("int *ip;\n" + 
				"ip = (int *) malloc ( sizeof(int) 10 );\n" + 
				"ip = (int *) malloc ( sizeof(int) 100 );\n" +"char *errmsg;\r\n" + 
				"int callback(void *arg, int argc, char **argv, char **colName) {/* 1 */\r\n" + 
				"    int i;\r\n" + 
				"    for(i=0; i<argc; i++){\r\n" + 
				"        printf(\"%s = %s\\t\", colName[i], argv[i] ?  : \"NULL\");\r\n" + 
				"    }\r\n" + 
				"    printf(\"\\n\");\r\n" + 
				"    return 0;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"rc = sqlite3_exec(db,/* 2 */\r\n" + 
				"    \"select count(*), avg(age) from demo; SELECT distinct name, age FROM demo WHERE Name = 'UserID';\", \r\n" + 
				"    callback, NULL, &errmsg);\r\n" + 
				"\r\n" + 
				"if (errmsg != NULL) {/* 3 */\r\n" + 
				"    printf(\"Error in sqlite3_exec: %s\\n\", errmsg);\r\n" + 
				"    sqlite3_free(errmsg);\r\n" + 
				"}");
		System.out.println(CodeInterpreter.getCode());
		CodeInterpreter.setLanguage("C");
		if (CodeInterpreter.getLanguage().equals("C")) {
			cAnalyzer cTest = new cAnalyzer();
			cTest.checkMemoryLeak(CodeInterpreter.getCode());
			cTest.checkInputValidation();
			System.out.println(cyberAttacks.getString()); 
			System.out.println("Pushing data over to the database..."); 
			code.pushToDataBase();
			cyberAttacks.pushDatabase();
			System.out.println("Successfully pushed to the database");
			cyberAttacks.removeall();
		}
		
		/*
		 * C sample prep statement 
		 */
		System.out.println("\nC Sample 2: ");
		CodeInterpreter.setCode("// ...\r\n" + 
				"sql::Connection *con;\r\n" + 
				"sql::PreparedStatement  *prep_stmt\r\n" + 
				"// ...\r\n" + 
				"\r\n" + 
				"prep_stmt = con->prepareStatement(\"SELECT * FROM table WHERE customers in ( ? ) and alive = ?\");\r\n" + 
				"\r\n" + 
				"prep_stmt->setInt(1, 1);\r\n" + 
				"prep_stmt->setString(2, \"a\");\r\n" + 
				"prep_stmt->execute();\r\n" + 
				"\r\n" + 
				"prep_stmt->setInt(1, 2);\r\n" + 
				"prep_stmt->setString(2, \"b\");\r\n" + 
				"prep_stmt->execute();\r\n" + 
				"\r\n" + 
				"delete prep_stmt;\r\n" + 
				"delete con;");
		CodeInterpreter.setLanguage("C");
		if (CodeInterpreter.getLanguage().equals("C")) {
			cAnalyzer cTest = new cAnalyzer();
			cTest.checkInputValidation();
			cTest.checkMemoryLeak(CodeInterpreter.getCode());
			System.out.println(cyberAttacks.getString()); 
			System.out.println("Pushing data over to the database..."); 
			code.pushToDataBase();
			cyberAttacks.pushDatabase();
			System.out.println("Successfully pushed to the database");
			cyberAttacks.removeall();
		}
		
		System.out.println("\nC Sample 3: ");
		CodeInterpreter.setCode("#include <stdio.h>\r\n" + 
				"#include <stdlib.h>\r\n" + 
				"enum { len = 12 }; \r\n" + 
				"void func(void) {\r\n" + 
				"  /*\r\n" + 
				"   * id will hold the ID, starting with the characters\r\n" + 
				"   *  \"ID\" followed by a random integer.\r\n" + 
				"   */\r\n" + 
				"  char id[len]; \r\n" + 
				"  int r;\r\n" + 
				"  int num;\r\n" + 
				"  /* ... */\r\n" + 
				"  r = rand();  /* Generate a random integer */\r\n" + 
				"  num = snprintf(id, len, \"ID%-d\", r);  /* Generate the ID */\r\n" + 
				"  /* ... */\r\n" + 
				"}");
		CodeInterpreter.setLanguage("C");
		if (CodeInterpreter.getLanguage().equals("C")) {
			cAnalyzer cTest = new cAnalyzer();
			cTest.checkInputValidation();
			cTest.checkBufferOverflow();
			cTest.checkMemoryLeak(CodeInterpreter.getCode());
			System.out.println(cyberAttacks.getString()); 
			System.out.println("Pushing data over to the database..."); 
			code.pushToDataBase();
			cyberAttacks.pushDatabase();
			System.out.println("Successfully pushed to the database");
			cyberAttacks.removeall();
		}
		
		System.out.println("\nC Sample 4: ");
		CodeInterpreter.setCode("#include  <stdio.h> \r\n" + 
				"void main(int argc, char **argv)\r\n" + 
				"{\r\n" + 
				"	// This line is safe\r\n" + 
				"	printf(\"%s\\n\", argv[1]);\r\n" + 
				"\r\n" + 
				"	// This line is vulnerable\r\n" + 
				"	printf(argv[1]);\r\n" + 
				"}");
		CodeInterpreter.setLanguage("C");
		if (CodeInterpreter.getLanguage().equals("C")) {
			cAnalyzer cTest = new cAnalyzer();
			cTest.checkInputValidation();
			cTest.checkBufferOverflow();
			cTest.checkStringFormat();
			cTest.checkMemoryLeak(CodeInterpreter.getCode());
			System.out.println(cyberAttacks.getString()); 
			System.out.println("Pushing data over to the database..."); 
			code.pushToDataBase();
			cyberAttacks.pushDatabase();
			System.out.println("Successfully pushed to the database");
			cyberAttacks.removeall();
		}
		
		System.out.println("\nC Sample 5: ");
		CodeInterpreter.setCode("#include <stdio.h>\r\n" + 
				"#include <string.h>\r\n" + 
				"\r\n" + 
				"int main(void)\r\n" + 
				"{\r\n" + 
				"    char buff[15];\r\n" + 
				"    int pass = 0;\r\n" + 
				"\r\n" + 
				"    printf(\"\\n Enter the password : \\n\");\r\n" + 
				"    gets(buff);\r\n" + 
				"\r\n" + 
				"    if(strcmp(buff, \"thegeekstuff\"))\r\n" + 
				"    {\r\n" + 
				"        printf (\"\\n Wrong Password \\n\");\r\n" + 
				"    }\r\n" + 
				"    else\r\n" + 
				"    {\r\n" + 
				"        printf (\"\\n Correct Password \\n\");\r\n" + 
				"        pass = 1;\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    if(pass)\r\n" + 
				"    {\r\n" + 
				"       /* Now Give root or admin rights to user*/\r\n" + 
				"        printf (\"\\n Root privileges given to the user \\n\");\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    return 0;\r\n" + 
				"}");
		CodeInterpreter.setLanguage("C");
		if (CodeInterpreter.getLanguage().equals("C")) {
			cAnalyzer cTest = new cAnalyzer();
			cTest.checkInputValidation();
			cTest.checkBufferOverflow();
			cTest.checkStringFormat();
			cTest.checkMemoryLeak(CodeInterpreter.getCode());
			System.out.println(cyberAttacks.getString()); 
			System.out.println("Pushing data over to the database..."); 
			code.pushToDataBase();
			cyberAttacks.pushDatabase();
			System.out.println("Successfully pushed to the database");
			cyberAttacks.removeall();
		}
		
		
		
		
	}

}
