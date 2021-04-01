package application;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

import SQLDatabase.loginHandler;

public class RegistrationControl {
	@FXML
	private Button registerButton;
	@FXML
	
	private TextField password;
	@FXML
	private TextField confirmPassword;
	@FXML
	private TextField username;
	@FXML
	private TextField feedback;
	
	
	
<<<<<<< HEAD
	@FXML
	public void register(ActionEvent action)//method to activate the registration button
=======
	public void register()//method to activate the registration button
>>>>>>> 3450a34c25224f9112527a2dcff13cc53ea4f896
	{
		if(password.equals(confirmPassword)) {//checks to see if passwords match
			loginHandler login = new loginHandler(username.toString(), password.toString());//create a login button
			boolean isReg = login.register();//register the user and store the return value
			if(isReg) {//if the user is registered
				feedback.setText("Success, you have registered!");//text at the bottom displays a message
			}else {
				feedback.setText("error, it seems your credentials already exist in the database");//text at the bottom displays a message
			}
		}else { 
			feedback.setText("error, passwords do not match! please try again");//if passwords do not match an error message is displayed.
		}
	}
	
	
	
	
	
		 
	}
	
	
	
	
	


