package project.database.manager;


import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;

import project.database.DataBaseConnector;

public class QueryManager {
	
	
	static public boolean delete(String tableName , String keyName , int id) {
		
		
		boolean dataDeleted = false;
		
		String sql = "DELETE FROM " + tableName + " WHERE " + keyName + " = ?";
		
		PreparedStatement stmt = getPrepareStatement(sql , false);
		System.out.println(sql);
		
		if(stmt == null) {
			System.out.println("The delete statement for " + tableName + " could not been created.");
			return dataDeleted;
		}
		
		
		
		try {
			stmt.setInt(1 , id);
			
			int affected = stmt.executeUpdate();
			
			if(affected == 1)
				dataDeleted = true;
			else
				dataDeleted = false;
			
			
		} catch (SQLException e) {
			
			System.err.println(e.getMessage());
		
		}
		
		
		
		return dataDeleted;
		
	}
	

	
	public static PreparedStatement getPrepareStatement(String sql, boolean generateKey) {
		
		PreparedStatement stmt = null;
		
		Connection conn = DataBaseConnector.getInstance().getConnection();
		
		if(conn == null) {
			System.out.println("Could not connect to database when creatinng the statement.");
			return stmt;
		}
		
		
		try {
			if(generateKey)
				stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			else	
				stmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			
			System.out.println("Error preparing the statement.");
		}
		
		return stmt;
	}
	
	
		
		
		
	
}
