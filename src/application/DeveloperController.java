package application;

import java.io.IOException;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class DeveloperController {
	
	
	

	@FXML private Button textInput;

	
	@FXML
	public void changeScreenToText(ActionEvent event) throws IOException {
		Parent enterTextParent = FXMLLoader.load(getClass().getResource("CodeInputScreen.fxml"));
		Scene textViewScene = new Scene(enterTextParent);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(textViewScene);
		window.show();
	}
	
	@FXML
	public void pullClick(ActionEvent event) throws IOException {
		Parent pullParent = FXMLLoader.load(getClass().getResource("PullScene.fxml"));
		Scene pullScene = new Scene(pullParent);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(pullScene);
		window.show();
	}
	
	public void fileInputScene(ActionEvent event) throws IOException {
		Parent fileParent = FXMLLoader.load(getClass().getResource("uploadfile.fxml"));
		Scene pullScene = new Scene(fileParent);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(pullScene);
		window.show();
		window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                Platform.exit();
                System.exit(0);
            }
        });
	}
	
	@FXML 
	public void logoff(ActionEvent event) throws IOException{
		User.clearAll();
		Parent enterTextParent = FXMLLoader.load(getClass().getResource("login.fxml"));
		Scene textViewScene = new Scene(enterTextParent, 400, 250);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(textViewScene);
		window.show();
	}
}
