package application;

import java.io.IOException;

import codeInterpration.CodeInterpreter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class MessageController {
	@FXML	
	private TextArea message;
	@FXML
	private Text feedback;
	@FXML
	private Button submit;


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
			submit.setVisible(false);

		}
		else {
			feedback.setText("Error, message must be equal or less than 256 characters");

		}

	}

	@FXML
	public void returnClick(ActionEvent event) throws IOException {
		if(User.getAccess() == 1) {
			Parent enterTextParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
			Scene developerScene = new Scene(enterTextParent, 600, 400);
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

			window.setScene(developerScene);
			window.show();
		}
		else {
			Parent enterTextParent = FXMLLoader.load(getClass().getResource("admin.fxml"));
			Scene developerScene = new Scene(enterTextParent, 600, 400);
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

			window.setScene(developerScene);
			window.show();
		}
	}
}
