package project;

import project.database.*;


public class Main {
	
	public static void main(String[] argv) {
		
		DataBaseConnector.getInstance().getConnection();
		DataBaseConnector.getInstance().closeConnection();
		
		
		int count = FaceDetector.getFaceCount("/home/musa/test3.jpg");	
		System.out.println("total face count " + count);
		
		
	}
	
	
}
