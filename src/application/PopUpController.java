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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class PopUpController{
	
	ObservableList<String> languageList = FXCollections.observableArrayList("C", "C++", "Java", "Python", "SQL");
	
	@FXML private TextArea userInput;
	@FXML private ComboBox<String> languages;
	
	
	@FXML
	public void initialize() {
		languages.getItems().addAll(languageList);
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
	public void submitClick(ActionEvent event) throws IOException {
		if(!languages.getValue().equals("Languages")) {
			User.setCode(userInput.getText());
			User.setLanguage(languages.getValue());
			
			Parent enterTextParent = FXMLLoader.load(getClass().getResource("analysisResult.fxml"));
			Scene developerScene = new Scene(enterTextParent,600,400);
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			
			window.setScene(developerScene);
			window.show();
		}
	}
}
