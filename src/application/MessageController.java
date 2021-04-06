package application;

import java.io.IOException;

import codeInterpration.CodeInterpreter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Popup;

public class MessageController {
	@FXML	
	private TextArea message;
	@FXML
	private Text feedback;
	

	@FXML
	public void submit(ActionEvent Event) throws IOException
	{
		CodeInterpreter code = new CodeInterpreter(User.getUser());
		if(message.getText().length() <= 256) { //check to see if message is correct length
			code.setAttacks(User.getAttacks());
			code.setCode(User.getCode());
			code.setLanguage(User.getLanguage());
			code.pushToDataBase(message.getText());
			
			feedback.setText("Successful push to database. CID: " + code.getCID());

		}
		else {
			feedback.setText("Error, message must be equal or less than 256 characters");

		}

	}
}
