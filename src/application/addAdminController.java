package application;

import java.io.IOException;
import java.util.ArrayList;

import SQLDatabase.SQLHandler;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class addAdminController {

	@FXML
	private GridPane gridPane;
	@FXML
	private TextField uidField;
	@FXML
	private Text errorText;
	
	@FXML
	private Button unlock;
	
	ArrayList<String> uids;
	loginHandler l;

	@FXML
	public void initialize() {
		// populate the gridpane
		l = new loginHandler(User.getUser(), User.getPassword());
		uids = l.getAllUIDS();
		for (int i = 0; i < uids.size(); i++) {
			Label user = new Label(uids.get(i));
			user.setTextFill(Color.WHITE);
			gridPane.add(user, 0, i);
		}
	}

	@FXML
	public void submitButton(ActionEvent e) {
		String uid = uidField.getText();
		if (uid.isEmpty() || !inList(uid)) {
			errorText.setText("Please enter a valid user");
			errorText.setVisible(true);
		} else {
			l.setAccess(uid, 2);
			gridPane.getChildren().clear();
			uids = l.getAllUIDS();
			for (int i = 0; i < uids.size(); i++) {
				Label user = new Label(uids.get(i));
				user.setTextFill(Color.WHITE);
				gridPane.add(user, 0, i);
			}
			errorText.setText(uid + " is now an admin.");
			errorText.setVisible(true);
		}
	}
	
	@FXML
	public void removeUser(ActionEvent e) {
		String uid = uidField.getText();
		if (uid.isEmpty() || !inList(uid)) {
			errorText.setText("Please enter a valid user");
			errorText.setVisible(true);
		} else {
			l.removeUser(uid);
			gridPane.getChildren().clear();
			uids = l.getAllUIDS();
			for (int i = 0; i < uids.size(); i++) {
				Label user = new Label(uids.get(i));
				user.setTextFill(Color.WHITE);
				gridPane.add(user, 0, i);
			}
			errorText.setText(uid + " Removed");
			errorText.setVisible(true);
		}
	}

	public void unlock(ActionEvent e) {
		String username = uidField.getText();
		if (username.isEmpty() || !inList(username)) {
			errorText.setVisible(true);
			errorText.setText("Please enter a valid user");
		} else {
			ArrayList<String[]> locked = l.getLockedAccts();
			for (String[] users : locked) {
				if (username.equals(users[0])) {
					l.changeaccesslevel(users[0], users[1], 1);
				}
			}
			errorText.setText(username + " Unlocked");
			errorText.setVisible(true);
		}
	}

	@FXML
	public void returnButton(ActionEvent e) {
		AnchorPane root;
		try {
			String fileName = "admin.fxml";
			AnchorPane screen = (AnchorPane) FXMLLoader.load(getClass().getResource(fileName));
			Scene scene = new Scene(screen);
			Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}

	public boolean inList(String uid) {
		for (String u : uids) {
			if (u.equals(uid))
				return true;
		}
		return false;
	}
}
