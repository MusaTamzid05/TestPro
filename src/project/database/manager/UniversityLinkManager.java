package project.database.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import project.database.tables.Admin;



public class UniversityLinkManager extends QueryManager {
	
	static public String getUniversityLink(String name) {
		
		String link = null;
		
		String sql = "SELECT link FROM  university_link WHERE name = ?";
		PreparedStatement stmt = getPrepareStatement(sql , false);
		
		if(stmt == null) {
			System.out.println("Error searching link of university.");
			return null;
		}
			
		ResultSet rs = null;
	
		try {
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				
				link = rs.getString("link");
				
			}else {
				System.out.println("No university with name" + name);
			}
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		
		
		return link;
	}
	
	static public ArrayList<String> getUniversityNames(){
		
		
		ArrayList<String> names = new ArrayList<String>();
		
		String sql = "SELECT name FROM university_link";
		
		PreparedStatement stmt = getPrepareStatement(sql , false);
		
		if(stmt == null) {
			System.err.println("Error preparing the statemen for getting University names. ");
			return null;
		}
		
		ResultSet rs = null;
		
		try {
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				names.add(rs.getString("name"));
			}
			
		}catch(SQLException e) {
			
		}finally {
			
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					
				}
		}
		
		
		return names;
	}

}
