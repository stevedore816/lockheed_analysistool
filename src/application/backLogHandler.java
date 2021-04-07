package application;

import java.io.IOException;
import java.util.ArrayList;

import SQLDatabase.backLog;
import SQLDatabase.loginHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class backLogHandler {

	@FXML private GridPane LoggerPane; 
	ArrayList<backLog> loggerRep;
	@FXML private Button returnButton;
	
	loginHandler login = new loginHandler(User.getUser(),User.getPassword());

	
	public void initialize() {
		loggerRep = login.getLogger(); 
		for (int i = 0; i < loggerRep.size();i++) {
			backLog log = loggerRep.get(i); 
			Label l = new Label(log.toString());
			l.setTooltip(new Tooltip(log.getMsg()));
			LoggerPane.add(l,0,i);
		}
	}
	
	@FXML
	public void returnButton(ActionEvent event) throws IOException {
		Parent enterTextParent = FXMLLoader.load(getClass().getResource("admin.fxml"));
		Scene developerScene = new Scene(enterTextParent, 600, 400);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(developerScene);
		window.show();
	}
}
