package application;

import codeInterpration.CodeInterpreter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class MessageController {
	@FXML	
	private TextArea message;
	@FXML
	private Text feedback;

	@FXML
	public void submit(ActionEvent Event)
	{
		CodeInterpreter code = new CodeInterpreter(User.getUser());
		if(message.getText().length() <= 256) { //check to see if message is correct length
			code.setAttacks(User.getAttacks());
			code.setCode(User.getCode());
			code.setLanguage(User.getLanguage());
			code.pushToDataBase(message.toString());

		}
		else {
			feedback.setText("Error, message must be equal or less than 256 characters");

		}

	}
}
