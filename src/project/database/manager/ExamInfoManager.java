package project.database.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project.database.tables.Admin;
import project.database.tables.Employee;
import project.database.tables.ExamInfo;

public class ExamInfoManager extends QueryManager {
	
	public static ExamInfo getExamInfoOf( String examName  , int employeeID) {
		
		ExamInfo bean =  null;
		
		
	
		
		
		String sql = "SELECT * FROM  exam_info WHERE exam_name = ? AND employee_id = ?";
		PreparedStatement stmt = getPrepareStatement(sql , false);
		
		
		if(stmt == null)
			return null;
		
		ResultSet rs = null;
		
		try {
			stmt.setString(1 , examName);
			stmt.setInt(2, employeeID);
			
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				
				bean = new ExamInfo(examName , rs.getInt("employee_id") ,
						rs.getString("year") , rs.getString("board") , 
						rs.getString("roll") , rs.getString("reg"));
			
			}
				
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			
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
	
	public static boolean insert(ExamInfo examInfo) {
		
		
		
		boolean dataInserted = false;
		
		String sql = "INSERT INTO exam_info( employee_id , year , board , roll  , reg , exam_name ) VALUES (?,?,?,?,?,?)";
		ResultSet key = null;
		
		try {
			
		
			PreparedStatement stmt = getPrepareStatement(sql , true);
			
			if(stmt == null) {
				System.out.println("Statement problem..ExamInfo insert failed!!");
				return dataInserted;
			}
			
			stmt.setInt(1 , examInfo.getEmployeeID());
			stmt.setString(2 , examInfo.getYear());
			stmt.setString(3 , examInfo.getBoard());
			stmt.setString(4, examInfo.getRoll());
			stmt.setString(5, examInfo.getReg());
			stmt.setString(6, examInfo.getExamName());
				
			int affected= stmt.executeUpdate();
			
			if(affected == 1) {
				
				System.out.println("Data affected " + affected);
				
				// get the new id
				
				key = stmt.getGeneratedKeys();
				key.next();
				int newKey = key.getInt(1);
				examInfo.setId(newKey);
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
		
		System.out.println("returing the inserted data "+ dataInserted);
		return dataInserted;
		
	}
	
	public static  boolean  update(ExamInfo info) {
		
		
		boolean dataUpdated = false;
	
		
		String sql = "UPDATE exam_info  SET  year = ? ,board= ? , roll= ? ,reg= ? where exam_name =? AND   employee_id  =  ?";
		System.out.println(sql);
		
		try {
			PreparedStatement stmt = getPrepareStatement(sql , false);
			
			if(stmt == null) {
				System.out.println("Could not create the statement to update admin");
				return dataUpdated;
			}
			
		
			stmt.setString(1 , info.getYear());
			stmt.setString(2 , info.getBoard());
			stmt.setString(3  , info.getRoll());
			stmt.setString(4  , info.getReg());
			stmt.setString(5  , info.getExamName());
			stmt.setInt(6 , info.getEmployeeID());
			
			
			int affected = stmt.executeUpdate();
			
			if(affected == 1)
				dataUpdated = true;
			else
				System.err.println("Affected rows : " + affected);
			
			
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
			
		}
		
		return dataUpdated;
		
	}
	
	
	

}


