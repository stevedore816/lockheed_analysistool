package application;

import java.io.IOException;
import java.util.ArrayList;

import SQLDatabase.loginHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class addAdminController {

	@FXML
	private GridPane gridPane;
	@FXML
	private TextField uidField;
	@FXML
	private Text errorText;
	ArrayList<String> uids;
	loginHandler l;
	@FXML
	public void initialize() {
		//populate the gridpane
		l = new loginHandler(User.getUser(),User.getPassword());
		uids = l.getAllUIDS();
		for(int i=0; i<uids.size();i++) {
			Label user = new Label(uids.get(i));
			gridPane.add(user, 0, i);
		}
	}
	@FXML
	public void submitButton(ActionEvent e) {
		String uid = uidField.getText();
		if(uid.isEmpty() || !inList(uid)) {
			errorText.setVisible(true);
		}else {
			l.setAccess(uid, 2);
			AnchorPane root;
			try {
				String fileName = "admin.fxml";
				AnchorPane screen = (AnchorPane) FXMLLoader.load(getClass().getResource(fileName));
				Scene scene = new Scene(screen);
				Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
				window.setScene(scene);
				window.show();
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			
		}
	}
	
	public boolean inList(String uid) {
		for(String u: uids) {
			if(u.equals(uid)) return true;
		}
		return false;
	}
}
