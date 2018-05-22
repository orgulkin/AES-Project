package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class ClientMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root= FXMLLoader.load(getClass().getResource("/application/updateQuestion.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("Update Question");
			primaryStage.setResizable(false);
			primaryStage.sizeToScene();
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}
