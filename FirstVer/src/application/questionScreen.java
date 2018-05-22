package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class questionScreen implements Initializable{
	ToggleGroup group;
	private static int questionCounter;
	@FXML
	Button cancel;
	@FXML
	Button create;
	@FXML
	Label error1;
	@FXML
	Label error2;
	@FXML
	Label error3;
	@FXML
	Label error4;
	@FXML
	Label missingError;
	@FXML
	Label answerError;
	@FXML
	TextField insField;
	@FXML
	TextField qField;
	@FXML
	TextField ans1;
	@FXML
	TextField ans2;
	@FXML
	TextField ans3;
	@FXML
	TextField ans4;
	@FXML
	RadioButton r1;
	@FXML
	RadioButton r2;
	@FXML
	RadioButton r3;
	@FXML
	RadioButton r4;
	@FXML
	ComboBox<String> prof;

	public void cancelClick(ActionEvent e) throws IOException {

		Parent mainScreenParent = FXMLLoader.load(getClass().getResource("/application/MainScreen.fxml"));
		Scene mainScreenScene = new Scene(mainScreenParent);

		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		window.setScene(mainScreenScene);
		window.setTitle("Main Screen");
		window.show();

	}

	public void createClick(ActionEvent e) throws IOException {
		if (qField.getText().equals("")) {
			missingError.setVisible(true);
			error1.setVisible(true);
		} else {
			error1.setVisible(false);
		
		}
		if (ans1.getText().equals("") || ans2.getText().equals("") || ans3.getText().equals("") || ans4.getText().equals("")) {
			missingError.setVisible(true);
			error2.setVisible(true);
		} else {
			error2.setVisible(false);
		if (insField.getText().equals("")) {
			missingError.setVisible(true);
			error4.setVisible(true);
		} else {
			error4.setVisible(false);
		}
		if (group.getSelectedToggle() == null) {
			 answerError.setVisible(true);
		 }
		else
			answerError.setVisible(false);
		if(prof.getValue()==null)
			error3.setVisible(true);
		else
			error3.setVisible(false);
		if (qField.getText().equals("")==false && ans1.getText().equals("")==false && ans2.getText().equals("")==false && ans3.getText().equals("")==false && ans4.getText().equals("")==false && insField.getText().equals("")==false && group.getSelectedToggle() != null && prof.getValue()!=null) {
			questionCounter++;
			System.out.println(questionCounter);
			RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
			int correctAnswer = Integer.parseInt(selectedRadioButton.getText());
			System.out.println("The correct answer is: "+correctAnswer); //insert the correct answer to DB
			String profession=prof.getValue();
			System.out.println("The selected profession is: "+profession); //insert the profession to DB
			Parent mainScreenParent = FXMLLoader.load(getClass().getResource("/application/MainScreen.fxml"));
			Scene mainScreenScene = new Scene(mainScreenParent);
			Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
			window.setScene(mainScreenScene);
			window.setTitle("Main Screen");
			window.show();
		}

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		prof.getItems().add("Math");
		prof.setPromptText("Please pick one profession");
		group = new ToggleGroup();
		r1.setToggleGroup(group);
		r2.setToggleGroup(group);
		r3.setToggleGroup(group);
		r4.setToggleGroup(group);
	}
	
}
