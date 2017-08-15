package project.gui;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import project.FaceDetector;
import project.database.manager.QueryManager;

public class Validator {
	
	public static boolean validateImage(String imagePath) {
		
		boolean isImageValid = false;
		
		if(imagePath.length() != 0) {
			
			int count = FaceDetector.getFaceCount(imagePath);
			System.out.println("Total face count " + count);
			
			if(count == 1)
				isImageValid = true;
			
		}
		
		
		return isImageValid;
			
		
	}
	
	public static boolean validateName(String name) {
		
		boolean isNameValid = false;
		
		if(name.length() != 0) {
			
			String sql = "SELECT * FROM employee WHERE name  = ?";
	
			PreparedStatement stmt = QueryManager.getPrepareStatement(sql , false);
			
			
			if(stmt == null)
				return false;
			
			ResultSet rs = null;
			
			try {
				stmt.setString(1 , name);
				
				rs = stmt.executeQuery();
				
				if(!rs.next())
					isNameValid = true;
				
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
			
			
		
			
		}
		
		return isNameValid;
	}

}
