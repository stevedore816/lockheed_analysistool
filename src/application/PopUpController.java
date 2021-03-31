package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class PopUpController{
	private String code;
	
	@FXML private TextArea userInput;
	
	public void initialize() {
		
	}
	
	@FXML
	public void returnClick(ActionEvent event) throws IOException {
		Parent enterTextParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
		Scene developerScene = new Scene(enterTextParent);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(developerScene);
		window.show();
	}
	
	
	@FXML
	public void submitClick(ActionEvent event) {
		if(userInput.getText() != null) {
			code = userInput.getText();
			System.out.print(code);
		}
	}
}
