package application;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

import SQLDatabase.loginHandler;

public class RegistrationControl {
	private Button registerButton;
	private TextField password;
	private TextField confirmPassword;
	private TextField username;
	private TextField feedback;
	
	
	
	
	public void register()//method to activate the registration button
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
	
	
	
	
	


