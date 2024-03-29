package application;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import SQLDatabase.loginHandler;
import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.stage.WindowEvent;

public class LoginController {
	@FXML
	private TextField userName;
	@FXML
	private PasswordField password;
	@FXML
	private Text failedLogin;
	private String currUser = "";

	private int loginAttempts = 0;


	@FXML
	public void registerSubmitClick(ActionEvent e) throws IOException {
			AnchorPane registrationScreen = (AnchorPane) FXMLLoader.load(getClass().getResource("RegisterScene.fxml"));
		
			Scene registrationScene = new Scene(registrationScreen, 600, 400);
			Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();

			window.setScene(registrationScene);
			window.show();
			window.setOnCloseRequest(new EventHandler<WindowEvent>() {
	            public void handle(WindowEvent we) {
	                Platform.exit();
	            }
	        });
		
	}

	@FXML
	public void loginAttempt(ActionEvent e) throws IOException {
		System.out.println(loginAttempts);
		if(userName.getText() != null && password.getText() != null) {
			if(loginAttempts>=3 && !userName.getText().equals(currUser)) {
				currUser = userName.getText();
				loginAttempts=0;
			}
			if(loginAttempts < 3) {

				String user = userName.getText();
 
				String pass = password.getText();
				loginHandler con= new loginHandler(user, pass);
				currUser = user;
				if(con.checkUserExists(user)) {


					int accessLvl = con.login();
					System.out.println("Access level:"+accessLvl);
					if(accessLvl == -1) {
						loginAttempts++;
						failedLogin.setText("Failed Login, please try again.");
					}else if(accessLvl == 0) {
						failedLogin.setText("Locked. Contact admin.");
					}
					else {

						User.setFull(user, pass);
						User.setAccess(con.getAccesslevel());
						String fileName = User.getAccess()==2?"admin.fxml":"sample.fxml";
						AnchorPane registrationScreen = (AnchorPane) FXMLLoader.load(getClass().getResource(fileName));
						Scene registrationScene = new Scene(registrationScreen, 600, 400);
						Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
						//con.addLogger(user, "User login from "+user, );
						window.setScene(registrationScene);
						window.show();
						window.setOnCloseRequest(new EventHandler<WindowEvent>() {
				            public void handle(WindowEvent we) {
				                Platform.exit();
				                System.exit(0);
				            }
				        });
					}
				}
				else {
					failedLogin.setText("Failed Login, please try again.");
				}
			}
			
			else {
				failedLogin.setText("Locked, contact admin");
				if(loginAttempts++ == 3) {
					String user = userName.getText();
					String pass = password.getText();
					loginHandler con= new loginHandler(user, pass);
					con.lockAccount();
				}
				
			}
		}
	}
}
