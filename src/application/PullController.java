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
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PullController {
	
	@FXML private ListView cid_list; 
	@FXML private TextArea test;
	@FXML private Text language;
	
	private CodeInterpreter con;
	
	private ObservableList<Integer> items = FXCollections.observableArrayList();
	
	@FXML
	public void returnClick(ActionEvent event) throws IOException {
		Parent enterTextParent;
		if(User.getAccess() == 2) {
			enterTextParent =  FXMLLoader.load(getClass().getResource("admin.fxml"));
		} else {
			enterTextParent =  FXMLLoader.load(getClass().getResource("sample.fxml"));
	    }
		Scene developerScene = new Scene(enterTextParent, 600, 400);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(developerScene);
		window.show();
	}
	


	public void initialize() {
		con = new CodeInterpreter("","C", User.getUser());
		ArrayList<Integer> cid = con.getAllCIDS();		
		for(int i = 0; i < cid.size(); i++) {
			cid_list.getItems().add(cid.get(i));
		}
		
	}
	
	public void pullCode(ActionEvent event) {
		ObservableList<Integer> selectedItem = cid_list.getSelectionModel().getSelectedItems();
		con.pullfromDataBase(User.getUser(), User.getPassword(), selectedItem.get(0));
		test.setText(con.getCode());
		test.setVisible(true);
		language.setVisible(true);
		language.setText("Language: "+ con.getLanguage());
		cid_list.setVisible(false);
	}
	
	public void getCID(ActionEvent event) {
		test.setText(null);
		test.setVisible(false);
		language.setVisible(false);
		cid_list.setVisible(true);
	}

}
