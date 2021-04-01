package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import codeInterpration.CodeInterpreter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class PullController implements Initializable {
	
	@FXML private ListView cid_list; 
	
	private ObservableList<Integer> items = FXCollections.observableArrayList();
	
	@FXML
	public void returnClick(ActionEvent event) throws IOException {
		Parent enterTextParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
		Scene developerScene = new Scene(enterTextParent);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(developerScene);
		window.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		CodeInterpreter con = new CodeInterpreter("","C", User.getUser());
		ArrayList<Integer> cid = con.getAllCIDS();
		
		for(int i = 0; i < cid.size(); i++) {
			cid_list.getItems().add(cid.get(i));
		}
		
	}
	
}
