package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class ServerMain extends Application {

	prototypeServer server;
	public static void main(String args[]) {
		launch(args);
	}
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		server = new prototypeServer(1234);
		server.listen();
		System.out.println("Listening to port: "+server.getPort());
	}
	
	
}
