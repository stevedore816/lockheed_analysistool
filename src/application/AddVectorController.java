package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddVectorController {
	@FXML
	private TextArea regexEntered;
	@FXML
	private Text errorMessage;
	
	@FXML
	public void addVector(ActionEvent event) throws IOException {
		String regex = regexEntered.getText();
		if(regex.isEmpty()) {
			errorMessage.setVisible(true);
		}else {
		User.addRegex(regex);
		String fileName = User.getPrevScreen();
		Parent prevScreen =  FXMLLoader.load(getClass().getResource(fileName));
	    User.setPrevScreen(null);
		Scene prevScene = new Scene(prevScreen);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(prevScene);
		window.show();
		}
	}
	
	@FXML
	public void returnBack(ActionEvent event) throws IOException {
		String fileName = User.getPrevScreen();
		Parent prevScreen =  FXMLLoader.load(getClass().getResource(fileName));
		User.setPrevScreen(null);
		Scene prevScene = new Scene(prevScreen);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(prevScene);
		window.show();
	}
}
