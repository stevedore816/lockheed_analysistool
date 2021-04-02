package application;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import SQLDatabase.loginHandler;
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

public class LoginController {
	@FXML
	private TextField userName;
	@FXML
	private PasswordField password;
	@FXML
	private Text failedLogin;


	private int loginAttempts = 0;


	@FXML
	public void registerSubmitClick(ActionEvent e) throws IOException {
		if(loginAttempts < 3) {
			AnchorPane registrationScreen = (AnchorPane) FXMLLoader.load(getClass().getResource("RegisterScene.fxml"));
			Scene registrationScene = new Scene(registrationScreen, 600, 400);
			Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();

			window.setScene(registrationScene);
			window.show();
		}
	}

	@FXML
	public void loginAttempt(ActionEvent e) throws IOException {
		if(userName.getText() != null && password.getText() != null) {

			if(loginAttempts != 3) {

				String user = userName.getText();

				String pass = password.getText();
				loginHandler con = new loginHandler(user, pass);

				if(con.checkUserExists(user)) {


					int accessLvl = con.login();

					if(accessLvl == -1) {
						loginAttempts++;
						failedLogin.setText("Failed Login, please try again.");
					}
					else {

						User.setFull(user, pass);
						AnchorPane registrationScreen = (AnchorPane) FXMLLoader.load(getClass().getResource("sample.fxml"));
						Scene registrationScene = new Scene(registrationScreen, 600, 400);
						Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();

						window.setScene(registrationScene);
						window.show();
					}
				}
				else {
					failedLogin.setText("Failed Login, please try again.");
				}
			}
			
			else {
				failedLogin.setText("Locked, contact admin");
			}
		}
	}
}
