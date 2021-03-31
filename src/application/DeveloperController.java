package application;

import java.io.IOException;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

public class DeveloperController {
	
	ObservableList<String> languageList = FXCollections.observableArrayList("C", "C++", "Java", "Python", "SQL");
	
	@FXML private ComboBox<String> languages;
	@FXML private Button textInput;

	@FXML
	public void initialize() {
		languages.getItems().addAll(languageList);
	}
	
	@FXML
	public void changeScreenToText(ActionEvent event) throws IOException {
		Parent enterTextParent = FXMLLoader.load(getClass().getResource("PopUp.fxml"));
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
}
