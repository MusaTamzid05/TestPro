package project.database.manager;


import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;

import project.database.DataBaseConnector;

public class QueryManager {
	
	
	protected static ArrayList<String> dataName;
	protected static String tableName;
	
	
	

	protected static String getInsertQuery() {
		
		String sql = "INSERT INTO "+ tableName + " (";
		
		int index = 0;
		
		for(String name : dataName) {
			sql += name;
			sql+= " ";
			
			if(index != dataName.size() - 1)
				sql += " , ";
			
			index+=1;
			
		}
		
		sql+= ") VALUES(";
		
		for(int i = 0 ; i < dataName.size(); i++) {
			sql += "?";
			
			if(i != dataName.size() - 1)
				sql+=",";
		}
		
		sql += ");";
			
		return sql;
	}
	
	protected static String getUpdateQuery() {
		
	
		
		String sql = "UPDATE " + tableName + " SET ";
		
		int index = 0;
		
		for(String data : dataName) {
			
			sql+= data + " = ?";
			
			if(index != dataName.size() - 1)
				sql += ",";
			
			index+= 1;
			
		}
		
		sql+= " WHERE id = ?";
		
		return sql;
	}
	
	protected static String getRowQuery() {
		
		String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
		return sql;
	}
	
	protected static String getAllQuery() {
		
		String sql = "SELECT * FROM " + tableName + ";";
		return sql;
	}
	
	
	protected static String getDeleteQuery() {
		
		
		
		return "DELETE FROM "+ tableName + " WHERE id = ?" ;
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
