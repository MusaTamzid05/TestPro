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
			bean.setAdminStuffs(rs.getString("Administrative_staff"));
			bean.setStudents(rs.getString("Students"));
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
	
	
	
	public static boolean  insert(UniversityInfo info, int uniID) {
		
		String sql = "INSERT INTO uni_info(Other_students, Campus, Undergraduates, Postgraduates, Doctoral_students, Website, Motto_in_English, Academic_staff, TYPE, Administrative_staff, Students, Vice_Chancellor, Established, Location, uni_id)"
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		
		boolean dataInserted = false;
		ResultSet key = null;
		
		
		try {
			
		
			PreparedStatement stmt = getPrepareStatement(sql , true);
			
			if(stmt == null) {
				System.out.println("Statement problem..Admin insert failed!!");
				return dataInserted;
			}
			
			stmt.setString(1 , info.getOther_students());
			stmt.setString(2 , info.getCampus());
			stmt.setString(3 ,  info.getUndergraduates());
			stmt.setString(4, info.getUndergraduates());
			stmt.setString(5, info.getPostgraduates());
			stmt.setString(6, info.getDoctralStudents());
			stmt.setString(7, info.getWebsites());
			stmt.setString(8, info.getMotto());
			stmt.setString(9, info.getAcadamicStuff());
			stmt.setString(10 ,  info.getType());
			stmt.setString(11 , info.getAdminStuffs());
			stmt.setString(12 , info.getStudents());
			stmt.setString(13, info.getViseChans());
			stmt.setString(14, info.getEstablished());
			stmt.setString(15, info.getLocation());
			stmt.setInt(16 ,  uniID);
			
	
			
			int affected= stmt.executeUpdate();
			
			if(affected == 1) {
				
				System.out.println("Data affected " + affected);
				
				// get the new id
				key = stmt.getGeneratedKeys();
				key.next();
				int newKey = key.getInt(1);
				info.setId(newKey);
				dataInserted = true;
				System.out.println("Data inserted => " + dataInserted);
				
			}else {
				
				System.out.println("Now rows affected.");
				System.out.println("Affected count " + affected);
				
			}
			
		}catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			
		}finally {
			if(key != null)
				try {
					key.close();
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
		}
		
		
		return dataInserted;
		
		
		
		
	}

}
