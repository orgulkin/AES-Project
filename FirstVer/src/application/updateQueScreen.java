package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class updateQueScreen implements Initializable {
	String question;
	ToggleGroup group;
	Connection con;
	Statement stmt;
	ResultSet rs;
	@FXML
	TextField f1;
	@FXML
	TextField f2;
	@FXML
	TextField f3;
	@FXML
	TextField f4;
	@FXML
	RadioButton r1;
	@FXML
	RadioButton r2;
	@FXML
	RadioButton r3;
	@FXML
	RadioButton r4;
	@FXML
	ComboBox<String> combo;
	@FXML
	Button updateButton;
	@FXML
	Button cancelButton;

	public void getDataFromDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			/* handle the error */}

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "Root");

		} catch (SQLException ex) {/* handle any errors */
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		getDataFromDB();
		group = new ToggleGroup();
		r1.setToggleGroup(group);
		r2.setToggleGroup(group);
		r3.setToggleGroup(group);
		r4.setToggleGroup(group);

		try {
			ObservableList<String> list = FXCollections.observableArrayList();
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT questionText,questionID FROM questions;");
			while (rs.next()) {
				list.add(rs.getString(1));
			}
			combo.setItems(list);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	public void updateButton(ActionEvent e) throws SQLException, IOException {
		RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
		if (selectedRadioButton != null) {
			int correctAnswer = Integer.parseInt(selectedRadioButton.getText());
			PreparedStatement ps = con.prepareStatement("UPDATE questions SET correctAnswer=? WHERE questionText=?;");
			ps.setInt(1, correctAnswer);
			ps.setString(2, question);
			ps.executeUpdate();
			Parent mainScreenParent = FXMLLoader.load(getClass().getResource("/application/MainScreen.fxml"));
			Scene mainScreenScene = new Scene(mainScreenParent);
			Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
			window.setScene(mainScreenScene);
			window.setTitle("Main Screen");
			window.show();
		}
	}

	public void cancelButton(ActionEvent e) throws IOException {
		Parent mainScreenParent = FXMLLoader.load(getClass().getResource("/application/MainScreen.fxml"));
		Scene mainScreenScene = new Scene(mainScreenParent);
		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		window.setScene(mainScreenScene);
		window.setTitle("Main Screen");
		window.show();
	}

	public void selectedAnswer() throws SQLException {
		question = combo.getValue();
		PreparedStatement ps = con.prepareStatement(
				"SELECT answer1,answer2,answer3,answer4,correctAnswer FROM questions WHERE questionText=?;");
		ps.setString(1, question);
		rs = ps.executeQuery();
		rs.next();
		f1.setText(rs.getString(1));
		f2.setText(rs.getString(2));
		f3.setText(rs.getString(3));
		f4.setText(rs.getString(4));
		if (rs.getInt(5) == 1) {
			r1.setSelected(true);
		}
		if (rs.getInt(5) == 2) {
			r2.setSelected(true);
		}

		if (rs.getInt(5) == 3) {
			r3.setSelected(true);
		}

		if (rs.getInt(5) == 4) {
			r4.setSelected(true);
		}

	}
}
