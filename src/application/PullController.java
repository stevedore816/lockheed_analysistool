package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import SQLDatabase.loginHandler;
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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class PullController {

	@FXML private ListView cid_list; 
	@FXML private TextArea test;
	@FXML private Text language;
	@FXML private TextField cidSearch;
	@FXML private Text display;

	private Boolean file;

	private CodeInterpreter con = new CodeInterpreter("","C", User.getUser());;

	private ObservableList<String> items = FXCollections.observableArrayList();

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
		ArrayList<String> cid = cid = con.getAllCIDS();
		for(int i = 0; i < cid.size(); i++) {
			cid_list.getItems().add(cid.get(i));
		}

		file = false;
	}

	public void search() {
		ArrayList<String> cid = con.getAllCIDS();
		ArrayList<String> names = con.getNames(User.getUser());
		if(cidSearch.getText() == null) {
			display.setText("Please enter a name.");
			display.setVisible(true);
		}
		else {
			int size = cid.size();
			boolean searching = true;
			for(int i = 0; i < size; i++) {
				if(cid.get(i).equals(cidSearch.getText())){
					cid_list.getItems().clear();
					cid_list.getItems().add(cid.get(i));
					file = false;
					searching = false;
				}
			}
			if(searching) {
				size = names.size();
				for(int j = 0; j < size; j++) {
					if(names.get(j).equals(cidSearch.getText())){
						cid_list.getItems().clear();
						cid_list.getItems().add(names.get(j));
						file = true;
					}
				}
			}
			else if(searching){
				display.setText("File not found, try again.");
				display.setVisible(true);
			}
		}
	}

	public void remove(ActionEvent event) {
		ObservableList<String> selectedItem = cid_list.getSelectionModel().getSelectedItems();
		if(file == false && selectedItem.get(0) != null) {
			display.setText(selectedItem.get(0) + " has been removed.");
			display.setVisible(true);
			con.removeCID(User.getUser(), selectedItem.get(0));
			cid_list.getItems().clear();
			ArrayList<String> cid = con.getAllCIDS();
			con = new CodeInterpreter("","C", User.getUser());;
			for(int i = 0; i < cid.size(); i++) {
				cid_list.getItems().add(cid.get(i));
			}
		}
		else if(selectedItem.get(0) != null) {
			display.setText(selectedItem.get(0) + " has been removed");
			display.setVisible(true);
			con.removeName(User.getUser(), selectedItem.get(0));
			ArrayList<String> names = con.getNames(User.getUser());
			cid_list.getItems().clear();
			for(int i = 0; i < names.size(); i++) {
				cid_list.getItems().add(names.get(i));
			}
			file = true;
		}
		else {
			display.setText("Invalid entry.");
			display.setVisible(true);
		}
	}

	public void pullCode(ActionEvent event) throws IOException {
		display.setVisible(false);
		ObservableList<String> selectedItem = cid_list.getSelectionModel().getSelectedItems();
		if(file == false && selectedItem.get(0) != null) {
			con.pullfromDataBase(User.getUser(), User.getPassword(), selectedItem.get(0));
			test.setText(con.getCode());
			test.setVisible(true);
			language.setVisible(true);
			language.setText("Language: "+ con.getLanguage());
			cid_list.setVisible(false);
			loginHandler l = new loginHandler(User.getUser(),User.getPassword());
			String cid = selectedItem.get(0);
			l.addLogger(cid, User.getUser(), "Code "+cid+" pulled from "+User.getUser());
		}
		else if(selectedItem.get(0) != null){
			DirectoryChooser dirChooser = new DirectoryChooser();
			File file = dirChooser.showDialog(null);
			String path = file.getAbsolutePath() + "\\"+selectedItem.get(0);
			file = new File(path);
			if(!file.exists()) {
				file.createNewFile();
			}
			con.pullCodeFromDatabase(User.getUser(), selectedItem.get(0), file.getAbsolutePath());
		}
		else {
			display.setText("Please select a file");
			display.setVisible(true);
		}
	}

	@FXML
	public void getFile(ActionEvent event) {
		display.setVisible(false);
		test.setText(null);
		test.setVisible(false);
		ArrayList<String> names = con.getNames(User.getUser());
		cid_list.getItems().clear();
		for(int i = 0; i < names.size(); i++) {
			cid_list.getItems().add(names.get(i));
		}
		cid_list.setVisible(true);
		file = true;
	}

	public void getCID(ActionEvent event) {
		test.setText(null);
		test.setVisible(false);
		language.setVisible(false);
		display.setVisible(false);
		ArrayList<String> cid = con.getAllCIDS();	
		cid_list.getItems().clear();
		for(int i = 0; i < cid.size(); i++) {
			cid_list.getItems().add(cid.get(i));
		}
		cid_list.setVisible(true);
		file = false;
	}

}


