package project.database.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project.database.tables.Employee;


public class EmployeeManager extends QueryManager {
	
	
	
	public static Employee  getEmployeeBy( String by , String name) {
			
			Employee bean =  null;
			
			String sql = "SELECT * FROM employee WHERE " + by  +" = ?";
			
			PreparedStatement stmt = getPrepareStatement(sql , false);
			
			
			if(stmt == null)
				return null;
			
			ResultSet rs = null;
			
			try {
				stmt.setString(1 , name);
				
				rs = stmt.executeQuery();
				
				if(rs.next()) {
					
					bean = new Employee();
					
					bean.setName(rs.getString("name"));
					bean.setEmail(rs.getString("password"));
					bean.setContact_no(rs.getString("contact_no"));
					bean.setAge(rs.getInt("age"));
					bean.setCityName("city_name");
					bean.setUniversity_name("university_name");
					
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
	
	boolean insertData(Employee employee) {
		
		return false;
	}
		
}
