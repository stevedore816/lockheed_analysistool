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
	//CodeInterpreter code = new CodeInterpreter(//put the user info in here)
	if(message.toString().length() <= 256) { //check to see if message is correct length 
		//code.pushToDataBase(message.toString());

	}else {
		feedback.setText("error, please have message be equal or less than 256 characters");

	}
	
}
	

}
