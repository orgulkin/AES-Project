package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainScreen implements Initializable {	

	@FXML
	Button logout;
	@FXML
	Button createQuestion;
	@FXML
	Label lecturerName;
	@FXML
	Label date;
	@FXML
	Button updateQuestion;
	
	
	public void logoutClick(ActionEvent e) throws IOException {

			Parent loginParent = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
			Scene loginScene = new Scene(loginParent);

			Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
			window.setScene(loginScene);
			window.setTitle("User Login");
			window.show();
	
	}
	
	public void updateClick(ActionEvent e) throws IOException {
		
		Parent updateParent = FXMLLoader.load(getClass().getResource("/application/updateQuestion.fxml"));
		Scene updateScene = new Scene(updateParent);

		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		window.setScene(updateScene);
		window.setTitle("Update Question");
		window.show();

}
	public void createQuestionClick(ActionEvent e) throws IOException {
		
		Parent questionParent = FXMLLoader.load(getClass().getResource("/application/CreateQuestion2.fxml"));
		Scene questionScene = new Scene(questionParent);

		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		window.setScene(questionScene);
		window.setTitle("Create new question");
		window.show();

}
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate localDate = LocalDate.now();
		date.setText(dtf.format(localDate));
		
	}
	
}
