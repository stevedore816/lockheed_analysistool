package application;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login extends Application {
	@FXML
	private TextField userName;
	@FXML
	private PasswordField password;
	@FXML
	private Text failedLogin;
	@FXML
	private AnchorPane loginPage;
	@FXML
	private AnchorPane loginLimit;
	
	private int loginAttempts = 0;
	
    public void start(Stage primaryStage) {
        try {
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene loginScene = new Scene(root, 388,281);
            primaryStage.setScene(loginScene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void loginSubmitClick(ActionEvent e){
    	
    	loginHandler l = new loginHandler(userName.getText(), password.getText(),3, false);
    	if(!l.login()){
    		if(++loginAttempts>=3) {
    			loginLimit.setVisible(true);
    			failedLogin.setVisible(false);
    		}else {
    			failedLogin.setVisible(true);
    			loginLimit.setVisible(false);
    		}
    		
    	}else {
    		//Log in successful
    		CodeInterpreter cp = new CodeInterpreter(userName.getText());
    		String fileName = (cp.getAccess()==2)?"admin.fxml":"developer.fxml";
    		AnchorPane devScreen = (AnchorPane) FXMLLoader.load(getClass().getResource(fileName));
    		Scene devScene = new Scene(devScreen);
    		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
			
			window.setScene(devScene);
			window.show();
    	}
    }
    
    @FXML
    private void registerSubmitClick(ActionEvent e) {
    	//Take to registration page
    	try {
			AnchorPane registrationScreen = (AnchorPane) FXMLLoader.load(getClass().getResource("REGISTRATIONSCREEN.fxml"));
			Scene registrationScene = new Scene(registrationScreen);
			Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
			
			window.setScene(registrationScene);
			window.show();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }
}