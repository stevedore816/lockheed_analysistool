package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import SQLDatabase.loginHandler;
import codeInterpration.CodeInterpreter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class UserFileController {

	@FXML private ListView cid_list; 
	@FXML private Text display;
	@FXML private TextArea test;
	@FXML private TextField userName;
	@FXML private Text header;

	private String uid;
	private Boolean file;
	private ArrayList<String> files;
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

	public void search(ActionEvent event) {
		uid = userName.getText();
		if(userName.getText() == null) {
			display.setVisible(true);
		}
		else {
			if(con.checkUserExists(userName.getText())) {
				uid = userName.getText();
				test.setText(null);
				test.setVisible(false);
				display.setVisible(false);
				files = con.getCIDS(uid);
				cid_list.getItems().clear();
				for(int i = 0; i < files.size(); i++) {
					cid_list.getItems().add(files.get(i));
				}
				cid_list.setVisible(true);
				header.setText(uid + " Files");
				file = false;
			}
			else {
				display.setVisible(false);
			}
		}
	}


	public void remove(ActionEvent event) {
		ObservableList<String> selectedItem = cid_list.getSelectionModel().getSelectedItems();
		if(file == false && uid != null) {
			display.setText(selectedItem.get(0) + " has been removed.");
			display.setVisible(true);
			con.removeCID(uid, selectedItem.get(0));
			cid_list.getItems().clear();
			con = new CodeInterpreter("","C", User.getUser());;
			files = con.getCIDS(uid);
			for(int i = 0; i < files.size(); i++) {
				cid_list.getItems().add(files.get(i));
			}
		}
	}

	public void pullCode(ActionEvent event) throws IOException {
		ObservableList<String> selectedItem = cid_list.getSelectionModel().getSelectedItems();
		if(file == false && uid != null) {
			CodeInterpreter temp = con.getCodeInfo(uid, selectedItem.get(0));
			test.setText(temp.getCode());
			test.setVisible(true);
			cid_list.setVisible(false);
		}
		else if( uid != null){
			DirectoryChooser dirChooser = new DirectoryChooser();
			File file = dirChooser.showDialog(null);
			String path = file.getAbsolutePath() + "\\"+selectedItem.get(0);
			file = new File(path);
			if(!file.exists()) {
				file.createNewFile();
			}
			con.pullCodeFromDatabase(uid, selectedItem.get(0), file.getAbsolutePath());
		}
	}

	@FXML
	public void getFile(ActionEvent event) {
		if(uid != null) {
			test.setText(null);
			test.setVisible(false);
			files = con.getNames(uid);
			cid_list.getItems().clear();
			for(int i = 0; i < files.size(); i++) {
				cid_list.getItems().add(files.get(i));
			}
			cid_list.setVisible(true);
			file = true;
		}
	}

	public void getCID(ActionEvent event) {
		if(uid != null) {
			test.setText(null);
			test.setVisible(false);
			files = con.getCIDS(uid);	
			cid_list.getItems().clear();
			for(int i = 0; i < files.size(); i++) {
				cid_list.getItems().add(files.get(i));
			}
			cid_list.setVisible(true);
			file = false;
		}
	}


}
