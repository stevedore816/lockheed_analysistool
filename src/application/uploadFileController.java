package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class uploadFileController
{

	ObservableList<String> languageList = FXCollections.observableArrayList("C", "C++", "Java", "Python", "SQL");
	
	private File file;
	
	private String lang;

	@FXML private TextField filePath;

	@FXML private Text errorMsg;

	@FXML private ComboBox<String> languages;

	@FXML
	public void initialize() {
		languages.getItems().addAll(languageList);
	}


	@FXML
	public void fileExplorer(ActionEvent event) throws IOException
	{
		FileChooser fileChooser = new FileChooser();
		
		lang = languages.getValue();

		if(lang != null) {

			if(lang.equals("Java")) {
				fileChooser.getExtensionFilters().addAll(new ExtensionFilter("java files", "*.java"));
				file = fileChooser.showOpenDialog(null);
			}
			else if(lang.equals("C")) {
				fileChooser.getExtensionFilters().addAll(new ExtensionFilter("c files", "*.c"));
				file = fileChooser.showOpenDialog(null);
			}
			else if(lang.equals("C++")) {
				fileChooser.getExtensionFilters().addAll(new ExtensionFilter("c++ files", "*.CPP"));
				file = fileChooser.showOpenDialog(null);
			}
			else if(lang.equals("Python")) {
				fileChooser.getExtensionFilters().addAll(new ExtensionFilter("python files", "*.py"));
				file = fileChooser.showOpenDialog(null);
			}
			else if(lang.equals("SQL")) {
				fileChooser.getExtensionFilters().addAll(new ExtensionFilter("sql files", "*.SQL"));
				file = fileChooser.showOpenDialog(null);
			}
			if(file != null) {
				filePath.setText(file.getAbsolutePath());
			}
		}
		
	}
	
	@FXML
	public void submitCode(ActionEvent event) throws IOException {
		String path = filePath.getText();
		if(file != null) {
			if(path.equals(file.getAbsolutePath())) {
				String code = "";
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line;
				while((line = br.readLine())!= null) {
					code += line + "\n";
				}
				br.close();
				
				User.setCode(code);
				User.setFile(file);
				User.setLanguage(lang);
				
				Parent enterTextParent = FXMLLoader.load(getClass().getResource("analysisResult.fxml"));
				Scene developerScene = new Scene(enterTextParent,600,400);
				Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
				
				window.setScene(developerScene);
				window.show();
				
			}
			else {
				errorMsg.setVisible(true);
			}
		}
		else {
			errorMsg.setVisible(true);
		}
	}
	
	@FXML
	public void returnClick(ActionEvent event) throws IOException {
		Parent enterTextParent;
		if(User.getAccess() == 2) {
			enterTextParent =  FXMLLoader.load(getClass().getResource("admin.fxml"));
		} else {
			enterTextParent =  FXMLLoader.load(getClass().getResource("sample.fxml"));
	    }
		Scene developerScene = new Scene(enterTextParent);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(developerScene);
		window.show();
	}

}
