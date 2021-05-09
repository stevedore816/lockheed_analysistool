package application;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class landingController {
	
	public void login(ActionEvent event) throws IOException 
	{
		Parent enterTextParent = FXMLLoader.load(getClass().getResource("login.fxml"));
		Scene textViewScene = new Scene(enterTextParent, 400, 250);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

		window.setScene(textViewScene);
		window.show();
	}
	public void register(ActionEvent event) throws IOException
	{
		Parent enterTextParent = FXMLLoader.load(getClass().getResource("RegisterScene.fxml"));
		Scene textViewScene = new Scene(enterTextParent, 600, 400);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(textViewScene);
		window.show();
	}
	
	public void pushManual(ActionEvent event) throws IOException
	{
		URL url = new URL("https://71.168.159.51/"); 
		//ternary operator for quicker writing
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop():null; 
		if(desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			try {
				desktop.browse(url.toURI());
			} catch(Exception e) {}
		}
	}
	
}





