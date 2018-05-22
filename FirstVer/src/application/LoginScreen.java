package application;

import java.io.IOException;

import javax.swing.Action;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginScreen {
	
	@FXML
	Button loginbutton;
	@FXML
	TextField pwField;
	@FXML
	TextField userField;
	@FXML
	Label errorLabel;
	public void buttonClick(ActionEvent e) throws IOException {
		if(userField.getText().equals(""))
		{
			errorLabel.setVisible(true);
			errorLabel.setText("*One of the fields is missing");
		}
		if(pwField.getText().equals("")) {
			errorLabel.setVisible(true);
			errorLabel.setText("*One of the fields is missing");
		}
		if(userField.getText().equals("")==false && pwField.getText().equals("")==false) {
		
		errorLabel.setVisible(false);
		Parent mainScreenParent=FXMLLoader.load(getClass().getResource("/application/MainScreen.fxml"));
		Scene mainScreenScene=new Scene(mainScreenParent);
		Stage window= (Stage)((Node) e.getSource()).getScene().getWindow();
		window.setScene(mainScreenScene);
		window.setTitle("Main Screen");
		window.show();
		
		}
		
	}
}
