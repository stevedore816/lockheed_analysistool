package application;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;




public class uploadFileController
{
	@FXML 
	private TextField filePath;
	
	
	private String fileName = filePath.toString();
	@FXML
	public void fileExplorer(ActionEvent event) throws IOException
	{
		 FileChooser fileChooser = new FileChooser();
	        File file = fileChooser.showOpenDialog(null);
	        if(file != null) {
	        	
	        	String extension = "";

	    		int i = fileName.lastIndexOf('.');
	    		int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));

	    		if (i > p) {
	    		    extension = fileName.substring(i+1);
	    		}
	    		if(extension.equalsIgnoreCase(User.getLanguage())) {
	    		
	    			StringBuilder fileContents = new StringBuilder((int)file.length());        

	    		    try (Scanner scanner = new Scanner(file)) {
	    		        while(scanner.hasNextLine()) {
	    		            fileContents.append(scanner.nextLine() + System.lineSeparator());
	    		        }
	    		        String code = fileContents.toString();
	    		        User.setCode(code);
	    				User.setLanguage(User.getLanguage());
	    				User.setFile(file);
	    				
	    				Parent enterTextParent = FXMLLoader.load(getClass().getResource("analysisResult.fxml"));
	    				Scene developerScene = new Scene(enterTextParent,600,400);
	    				Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	    				
	    				window.setScene(developerScene);
	    				window.show();
	    		    }
	}
	        }
	}
	
	@FXML
	public void submit(ActionEvent event) throws IOException
	{
		
		String extension = "";

		int i = fileName.lastIndexOf('.');
		int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));

		if (i > p) {
		    extension = fileName.substring(i+1);
		}
		if(extension.equalsIgnoreCase(User.getLanguage())) {
			File file = new File(fileName);
			StringBuilder fileContents = new StringBuilder((int)file.length());        

		    try (Scanner scanner = new Scanner(file)) {
		        while(scanner.hasNextLine()) {
		            fileContents.append(scanner.nextLine() + System.lineSeparator());
		        }
		        String code = fileContents.toString();
		        User.setCode(code);
				User.setLanguage(User.getLanguage());
				User.setFile(file);
				
				Parent enterTextParent = FXMLLoader.load(getClass().getResource("analysisResult.fxml"));
				Scene developerScene = new Scene(enterTextParent,600,400);
				Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
				
				window.setScene(developerScene);
				window.show();
			}
		    
		    
		}
		
		else {
			//set bottom text 
			
		}
		
	}
	
}
