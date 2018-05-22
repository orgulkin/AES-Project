package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class prototypeServer extends AbstractServer {

	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	
	public prototypeServer(int port) {
		super(port);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		getDataFromDB();
		
		if (((String)msg).equals("importQuestions")) {
			try {
				QuestionsM temp = new QuestionsM();
				stmt = con.createStatement();
				rs = stmt.executeQuery("SELECT questionText FROM questions;");
				while (rs.next()) {
						temp.list.add(rs.getString(1));
				}
				try {
					client.sendToClient(temp);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				(temp.list).clear();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (((String)msg).contains("updateQuestion")) {
			String m = (String)msg;
			String arr[] = m.split("|");
			String question = arr[1];
			int correctAnswer = Integer.parseInt(arr[2]);
			PreparedStatement ps;
			try {
				ps = con.prepareStatement("UPDATE questions SET correctAnswer=? WHERE questionText=?;");
				ps.setInt(1, correctAnswer);
				ps.setString(2, question);
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
		
	public void getDataFromDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			/* handle the error */}

		try {
			con = DriverManager.getConnection("jdbc:mysql://braudeprojectdb.cqbxzkh2oemx.us-east-1.rds.amazonaws.com:3306/Project", "yanivsu", "Henry14#");

		} catch (SQLException ex) {/* handle any errors */
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

	}

}
