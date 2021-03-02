import Analyzer.*;
import codeInterpration.*;

public class Tester {
	public static void main (String[] args) {
		CodeInterpreter.setCode("Public Sub Test\r\n" + 
				"  Dim commandText As String\r\n" + 
				" \r\n" + 
				"  commandText = \"SELECT ProductID, Name FROM Products WHERE Name LIKE '; SELECT * FROM sysobjects ;--%'\"\r\n" + 
				"  Try\r\n" + 
				"    CommandTextValidator.ValidateStatement(commandText, StatementTypes.Select)\r\n" + 
				"    System.Diagnostics.Debug.WriteLine(\"Validation worked. The semicolon and statement are inside a text block.\")\r\n" + 
				"  Catch ex As System.Exception\r\n" + 
				"    System.Diagnostics.Debug.WriteLine(\"Validation failed. The following error was thrown: \" & ex.message)\r\n" + 
				"  End Try\r\n" + 
				" \r\n" + 
				"  commandText = \"SELECT ProductID, Name FROM Products WHERE Name LIKE ''; SELECT * FROM sysobjects ;--%'\"\r\n" + 
				"  Try\r\n" + 
				"    CommandTextValidator.ValidateStatement(commandText, StatementTypes.Select)\r\n" + 
				"    System.Diagnostics.Debug.WriteLine(\"Validation failed. An error should have been thrown.\")\r\n" + 
				"  Catch ex As System.Exception\r\n" + 
				"    System.Diagnostics.Debug.WriteLine(\"Validation worked. The following error was thrown: \" & ex.message)\r\n" + 
				"  End Try\r\n" + 
				"End Sub");
		
		CodeInterpreter.setLanguage("SQL");
		if (CodeInterpreter.getLanguage().equals("SQL")) {
			sqlAnalyzer sqlLang = new sqlAnalyzer(CodeInterpreter.getCode());
			sqlLang.checkInputValidation();
		}
	}

}
