package application;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class PopUpController {
	
	@FXML Text cidText;
	
	
	public void initialize() {
		cidText.setText(String.valueOf(User.getCID()));
	}
	
}
