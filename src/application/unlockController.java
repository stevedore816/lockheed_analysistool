package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import java.util.ArrayList;

import SQLDatabase.SQLHandler;
import SQLDatabase.loginHandler;

public class unlockController {
	
	@FXML
	private ComboBox<String> users;
	
	
	
	loginHandler login = new loginHandler(User.getUser(), User.getPassword());
	
	ArrayList<String[]> locked = login.getLockedAccts();
	
	@FXML
	public void initialize()
	{
		//users.getItems().addAll(locked[0]);
	}
	 
	

	
	@FXML
	public void unlock(ActionEvent event)
	{
		String username = users.getValue().toString();
		SQLHandler sql = new SQLHandler();
		sql.changeaccesslevel(username, User.getPassword(), 1);
		
	}
}
