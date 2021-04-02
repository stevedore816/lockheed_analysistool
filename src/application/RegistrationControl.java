package application;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import SQLDatabase.loginHandler;

public class RegistrationControl {


	@FXML private Button registerButton;
	@FXML private TextField password;
	@FXML private TextField confirmPassword;
	@FXML private TextField username;
	@FXML private Text feedback;


	public void registerButton(ActionEvent action) {
		feedback.setText(null);
		if(password.getText() != null && username.getText() != null && confirmPassword.getText() != null) {
			if(checkPasswords()) {
				loginHandler con = new loginHandler(username.getText(), password.getText());
				if(con.register()) {
					feedback.setText("Success");
				}
				else {
					feedback.setText("Username exits, enter a new username.");
				}
			}
			else {
				feedback.setText("Please enter matching passwords.");
			}
		}
	}


	public boolean checkPasswords() {
		if(password.getText().equals(confirmPassword.getText())) {
			return true;
		}
		return false;
	}

}







