package project.database.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import project.database.tables.Admin;
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
					bean.setEmail(rs.getString("email"));
					bean.setContact_no(rs.getString("contact_no"));
					bean.setAge(rs.getInt("age"));
					bean.setCityName("city_name");
					bean.setUniversityName("university_name");
					bean.setId(rs.getInt("id"));
					
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
	
	
	public static ArrayList<Employee> getAll(){
		
		String sql = "SELECT * FROM employee";
		ArrayList<Employee> employees= new ArrayList<Employee>();
		
		PreparedStatement stmt = getPrepareStatement(sql , false);
		
		if(stmt == null) {
			System.err.println("Error preparing the statement for getting all employee ");
			return employees;
		}
		
		ResultSet rs = null;
		
		try {
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
			
				employees.add(new Employee(rs.getInt("id") ,
						                  rs.getString("name"),
						                  rs.getInt("age"),
						                  rs.getString("email"),
						                  rs.getString("contact_no"),
						                  rs.getString("university_name"),
						                  rs.getString("city_name"),
						                  rs.getString("image_path")

						
						));
			}
			
		}catch(SQLException e) {
			System.err.println(e);
			
		}finally {
			
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					
			}
		}
			

	
		return employees;
	}

	
		
	public static boolean insert(Employee employee) {
			
			
			
			boolean dataInserted = false;
			
			String sql = "INSERT INTO employee( name, email, contact_no, age, university_name, city_name, image_path) VALUES (?,?,?,?,?,?,?)";
			ResultSet key = null;
			
			
			try {
				
				System.out.println(sql);
				PreparedStatement stmt = getPrepareStatement(sql , true);
				
				if(stmt == null) {
					System.out.println("Statement problem..Employee insert failed!!");
					return dataInserted;
				}
				
				stmt.setString(1 , employee.getName());
				stmt.setString(2 , employee.getEmail());
				stmt.setString(3 , employee.getContact_no());
				stmt.setInt(4, employee.getAge());
				stmt.setString(5, employee.getUniversity_name());
				stmt.setString(6, employee.getCityName());
				stmt.setString(7, employee.getImagePath());
				
				
				
				int affected= stmt.executeUpdate();
				
				if(affected == 1) {
					
					System.out.println("Data affected " + affected);
					
					// get the new id
					key = stmt.getGeneratedKeys();
					key.next();
					int newKey = key.getInt(1);
					employee.setId(newKey);
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
	
	
	public static boolean update(Employee employee) {
		
		
		
		boolean isUpdated = false;
		String sql = "UPDATE employee SET name= ? ,email= ? ,contact_no = ? ,age = ? ,university_name = ? ,city_name = ? ,image_path = ? WHERE id = ? ";
		
		try {
			PreparedStatement stmt = getPrepareStatement(sql , false);
			
			if(stmt == null) {
				System.out.println("Could not create the statement to update employee");
				return false;
			}
			
			stmt.setString(1 , employee.getName());
			stmt.setString(2 ,  employee.getEmail());
			stmt.setString(3 , employee.getContact_no());
			stmt.setInt(4, employee.getAge());
			stmt.setString(5, employee.getUniversity_name());
			stmt.setString(6, employee.getCityName());
			stmt.setString(7, employee.getImagePath());
			stmt.setInt(8, employee.getId());
			
			int affected = stmt.executeUpdate();
			
			if(affected == 1) 
				isUpdated = true;
				
			
				
			else
				System.err.println(affected);
			
			
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
			
		}
		
	
		return isUpdated;
		
	}
	
	public static void main(String[] argv) {
		
		ArrayList<Employee> employees = getAll();
		System.out.println(employees.size());
	}
		
}
