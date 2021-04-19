package application;

import SQLDatabase.SQLHandler;
import codeInterpration.CodeInterpreter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class landingController {
	public void login(ActionEvent event) 

	{

		Parent enterTextParent = FXMLLoader.load(getClass().getResource("login.fxml"));

		Scene textViewScene = new Scene(enterTextParent);

		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

		

		window.setScene(textViewScene);

		window.show();

		

	}

	

	

	

	public void register(ActionEvent event)

	{

		

		Parent enterTextParent = FXMLLoader.load(getClass().getResource("RegisterScene.fxml"));

		Scene textViewScene = new Scene(enterTextParent);

		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

		

		window.setScene(textViewScene);

		window.show();

	}



}




}
