package project.database.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import project.database.tables.UniversityInfo;

public class UniversityInfoManager extends QueryManager {
	
	public static UniversityInfo searchByID(int id) {
		
		UniversityInfo bean = null;
		
		String sql = "SELECT * FROM uni_info WHERE  uni_id = ?";
		PreparedStatement stmt = getPrepareStatement(sql , false);
		
		
		if(stmt == null)
			return null;
		
		ResultSet rs = null;
		
		try {
			stmt.setInt(1 , id);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				
			bean = new UniversityInfo();
			bean.setOther_students(rs.getString("Other_student"));
			bean.setCampus(rs.getString("Campus"));
			bean.setUndergraduates(rs.getString("Undergraduates"));
			bean.setPostgraduates(rs.getString("Postgraduates"));
			bean.setDoctralStudents(rs.getString("Doctoral_students"));
			bean.setWebsites(rs.getString("Website"));
			bean.setMotto(rs.getString("Motto_in_English"));
			bean.setAcadamicStuff(rs.getString("Academic_staff"));
			bean.setType(rs.getString("TYPE"));
			bean.setType(rs.getString("Administrative_staff"));
			bean.setType(rs.getString("Students"));
			bean.setViseChans(rs.getString("Vice_Chancellor"));
			bean.setEstablished(rs.getString("Established"));
			bean.setLocation(rs.getString("Location"));
			
				
			
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

}
