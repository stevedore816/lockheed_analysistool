package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AnalysisResult {
	@FXML
	private Text errorCount;
	@FXML
	private GridPane analysisResultPane;
	@FXML
	private Button tryAgainButton;
	@FXML
	private Button submitButton;
	CodeInterpreter cp;
	private int count;
	@FXML
	public void initialize() {
		//Display the value of the error count
		cp = new CodeInterpreter(user); //the necessary params will be obtained from the "static class"
		cp.analyzeCode();
		ArrayList<attackVector> vector = cp.getAttacks();
		count = vector.size();
		errorCount.setText(String.valueOf(count));
		ArrayList<int[]> badLines = new ArrayList<>();
		for(attackVector attack: vector) {
			String line = attack.getVcode();
			int[] pos = cp.getLocation(line);
			badLines.add(pos);
		}
		String code= cp.getCode();
		String s = (badLines.isEmpty())?code:code.substring(0,badLines.get(0)[0]);
		Label l = new Label(s);
		analysisResultPane.add(l,0,0);
		for(int i=0; i<badLines.size();i++) {
			int[] pos = badLines.get(0);
			
			String bad = code.substring(pos[0], pos[1]);
			Label badCode = new Label(bad);
			String resource = vector.get(i).getResource();
	    	badCode.setTooltip( new Tooltip(resource)); //When mouse hovers over it, it will display the resources
	    	badCode.setTextFill(Color.RED);
	    	analysisResultPane.add(badCode,0,2*i+1);
	    	
	    	if(i+1!=badLines.size()) {
	    		int[] pos2 = badLines.get(i+1);
	    		String good = code.substring(pos[1],pos2[0]);
	    		Label goodCode = new Label(good);
	    		analysisResultPane.add(goodCode,0,2*i+2);
	    	}else {
	    		String good = code.substring(pos[1],code.length());
	    		Label goodCode = new Label(good);
	    		analysisResultPane.add(goodCode,0,2*i+2);
	    	}
		}
		if(count>3) {
			tryAgainButton.setVisible(true);
			submitButton.setVisible(false);
		}		
		
	}
	
	@FXML
    private void tryAgain(ActionEvent e) {
		try {
    		String fileName = (cp.getAccess()==2)?"admin.fxml":"developer.fxml";
			AnchorPane screen = (AnchorPane) FXMLLoader.load(getClass().getResource(fileName));
			Scene scene = new Scene(screen);
			Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
			
			window.setScene(scene);
			window.show();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }
	
	@FXML
	private void submit(ActionEvent e) {
		try {
			AnchorPane screen = (AnchorPane) FXMLLoader.load(getClass().getResource("message.fxml"));
			Scene scene = new Scene(screen);
			Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
			
			window.setScene(scene);
			window.show();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
