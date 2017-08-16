package project.database;
import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnector {
	
	private static DataBaseConnector instance = null;
	private final String USERNAME = "dbUser";
	private final String PASSWORD = "dbpassword";
	private final String M_CONN_STRING = "jdbc:mysql://localhost/java_project";
	
	private  Connection conn = null;
	
	private DataBaseConnector() {
		
	}
	
	public static DataBaseConnector getInstance() {
		
		if(instance == null)
			instance= new DataBaseConnector();
		
		return instance;
	}
	
	private  boolean openConnection() {
		
		boolean databaseConnected = false;
		
		try {
			conn = DriverManager.getConnection(M_CONN_STRING , USERNAME , PASSWORD);
			databaseConnected = true;
		}catch(Exception e) {
			
		}
		return databaseConnected;
		
	}
	
	public  Connection getConnection() {
		
		if(conn == null) {
			if(openConnection()) {
				System.out.println("Database is connected.");
				
			}else
				System.out.println("Failed to connect to the database.");
				
			
		}
			
		return conn;
		
	}
	
	public void closeConnection() {
		
		
		if(conn == null) {
			System.out.println("Database is already close.");
			return;
		}
			
	
		System.out.println("Closing the connection.");
		try {
			conn.close();
			conn = null;
			System.out.println("Database connection closed successfully.");
			
		}catch(Exception e) {
			System.err.println(e);
		}
	}
	
	

}
