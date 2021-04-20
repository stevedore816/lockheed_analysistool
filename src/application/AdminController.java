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

public class AdminController {
	
	
	
	@FXML private ComboBox<String> languages;
	@FXML private Button textInput;

	@FXML
	public void initialize() {
		
	}
	
	@FXML
	public void changeScreenToText(ActionEvent event) throws IOException {
		Parent enterTextParent = FXMLLoader.load(getClass().getResource("CodeInputScreen.fxml"));
		Scene textViewScene = new Scene(enterTextParent);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(textViewScene);
		window.show();
		window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                Platform.exit();
                System.exit(0);
            }
        });
	}
	
	@FXML
	public void submitScreen(ActionEvent event) throws IOException{
		Parent enterTextParent = FXMLLoader.load(getClass().getResource("analysis.fxml"));
		Scene textViewScene = new Scene(enterTextParent);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(textViewScene);
		window.show();
		window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                Platform.exit();
                System.exit(0);
            }
        });
	}
	
	
	@FXML
	public void pullClick(ActionEvent event) throws IOException {
		Parent pullParent = FXMLLoader.load(getClass().getResource("PullScene.fxml"));
		Scene pullScene = new Scene(pullParent);
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
	public void backlogClick(ActionEvent event) throws IOException{
		Parent pullParent = FXMLLoader.load(getClass().getResource("backlog.fxml"));
		Scene pullScene = new Scene(pullParent);
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
	public void unlockClick(ActionEvent event) throws IOException{
		Parent pullParent = FXMLLoader.load(getClass().getResource("unlockScene.fxml"));
		Scene pullScene = new Scene(pullParent);
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
	public void addAdmin(ActionEvent event) throws IOException{
		Parent pullParent = FXMLLoader.load(getClass().getResource("addAdmin.fxml"));
		Scene pullScene = new Scene(pullParent);
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
	public void uploadFile(ActionEvent event) throws IOException {
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
	
	public void userFiles(ActionEvent event) throws IOException {
		Parent userParent = FXMLLoader.load(getClass().getResource("UserFiles.fxml"));
		Scene userFileScene = new Scene(userParent);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(userFileScene);
		window.show();
		window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                Platform.exit();
                System.exit(0);
            }
        });
	}
	@FXML
	public void logout(ActionEvent event) throws IOException{
		Parent pullParent = FXMLLoader.load(getClass().getResource("login.fxml"));
		Scene pullScene = new Scene(pullParent);
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
	
	
}
