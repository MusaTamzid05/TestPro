package project.database.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class EmployeeManager {
	
	
	/*
	public static Admin  getAdminByName(String name) {
			
			Admin bean =  null;
			
			String sql = "SELECT * FROM admin Employee WHERE name = ?";
			
			
			
			PreparedStatement stmt = getPrepareStatement(sql , false);
			
			
			if(stmt == null)
				return null;
			
			ResultSet rs = null;
			
			try {
				stmt.setString(1 , name);
				
				rs = stmt.executeQuery();
				
				if(rs.next()) {
					
					bean.setUserName(rs.getString("userName"));
					bean.setPassword(rs.getString("password"));
				}
					
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				
				
			}finally {
				if(rs != null)
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			
			
			
			return bean;
		}
		*/
}
