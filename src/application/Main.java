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
    	
    	User.setUser("Steven");
    	User.setPassWord("Arroyo");
    	loginHandler con = new loginHandler(User.getUser(),User.getPassword());
    	User.setAccess(con.login());
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("sample.fxml"));
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    } 
}