package application;  

import SQLDatabase.loginHandler;
import codeInterpration.CodeInterpreter;
import javafx.application.Application; 
import javafx.fxml.FXMLLoader; 
import javafx.scene.Parent; 
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;  

public class Main extends Application {                

    public static void main(String[] args) {
        Application.launch(args);       
    }          

    public void start(Stage stage) throws Exception {
    	
    	
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("login.fxml"));
        stage.setScene(new Scene(root, 400, 250));
        stage.show();
    
    } 
}