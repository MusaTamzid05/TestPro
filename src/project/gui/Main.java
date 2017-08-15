package project.gui;

import project.database.DataBaseConnector;

public class Main {
	
	public static void main(String[] argv) {
		
		new AdminLogin();
		DataBaseConnector.getInstance().closeConnection();
		
	
	}
}
