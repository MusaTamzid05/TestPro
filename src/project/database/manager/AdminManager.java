package project.database.manager;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import project.database.tables.Admin;

public class AdminManager extends QueryManager {
	
	
	
	static boolean tableLoaded = false;
	
	public AdminManager() {
		
		
		
	}
	
	
	public static boolean isLoggedInSucessfull(String userName , String password) {
		
		
		
		boolean loggedIn = false;
		
		String sql = "SELECT * from admin WHERE  userNAME = ? AND password = ?";
		
		PreparedStatement stmt = getPrepareStatement(sql , false);
		
		
		if(stmt == null)
			return false;
		
		ResultSet rs = null;
		
		try {
			stmt.setString(1 , userName);
			stmt.setString(2 , password);
			rs = stmt.executeQuery();
			
			if(rs.next())
				loggedIn = true;
			
			
			
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
		
		
		return loggedIn;
	}
	
	
	/*
	public static Admin getRow(int id) {
		
		initTable();
		
		Admin bean = null;
		
		String sql = getRowQuery();
		PreparedStatement stmt = getPrepareStatement(sql , false);
		
		
		if(stmt == null)
			return null;
		
		ResultSet rs = null;
		
		try {
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				
				bean = new Admin();
				bean.setId(id);
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
			
		
		
	}*/
	
	public static boolean insert(Admin admin) {
		
		//initTable();
		
		boolean dataInserted = false;
		
		String sql = "INSERT INTO admin ( userName, password) VALUES ( ? ,?)";
		ResultSet key = null;
		
		
		try {
			
			System.out.println(sql);
			PreparedStatement stmt = getPrepareStatement(sql , true);
			
			if(stmt == null) {
				System.out.println("Statement problem..Admin insert failed!!");
				return dataInserted;
			}
			
			stmt.setString(1 , admin.getUserName());
			stmt.setString(2 , admin.getPassword());
			
			
			int affected= stmt.executeUpdate();
			
			if(affected == 1) {
				
				System.out.println("Data affected " + affected);
				
				// get the new id
				key = stmt.getGeneratedKeys();
				key.next();
				int newKey = key.getInt(1);
				admin.setId(newKey);
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
	
	public static  boolean  update(Admin admin) {
		
		
		boolean dataUpdated = false;
	
		
		String sql = "UPDATE admin SET  userName = ? ,password = ?  WHERE id = ? ";
		System.out.println(sql);
		
		try {
			PreparedStatement stmt = getPrepareStatement(sql , false);
			
			if(stmt == null) {
				System.out.println("Could not create the statement to update admin");
				return dataUpdated;
			}
			
			stmt.setString(1 , admin.getUserName());
			stmt.setString(2 , admin.getPassword());
			stmt.setInt(3 , admin.getID());
			
			int affected = stmt.executeUpdate();
			
			System.out.println("Total row updated " + affected);
			
			if(affected == 1)
				dataUpdated = true;
			
			
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
			
		}
		
		return dataUpdated;
		
	}
	
	
	
	
	public static ArrayList<Admin> getAll(){
			
			ArrayList<Admin> admins = new ArrayList<>();
			String sql = "SELECT * FROM admin";
			
			PreparedStatement stmt = getPrepareStatement(sql , false);
			
			if(stmt == null) {
				System.err.println("Error preparing the statement ");
				return admins;
			}
			
			ResultSet rs = null;
			
			try {
				
				rs = stmt.executeQuery(sql);
				
				while(rs.next()) {
					
					admins.add(new Admin(rs.getInt("id") , rs.getString("userName") , rs.getString("password")));
					
				}
				
			}catch(SQLException e) {
				System.err.println(e.getMessage());
				
			}finally {
				
				if(rs != null)
					try {
						rs.close();
					} catch (SQLException e) {
						
					}
			}
				
			
			
			
			
		
			return admins;
		}
	
	public static  boolean nameExists(String userName){
			
			
			
			boolean exists = false;
			
			String sql = "SELECT * FROM admin WHERE userName = ?";
			
			PreparedStatement stmt = getPrepareStatement(sql , false);
			
			
			if(stmt == null)
				return false;
			
			ResultSet rs = null;
			
			try {
				stmt.setString(1, userName);
				rs = stmt.executeQuery();
				
				if(rs.next())
					exists = true;
				
				
				
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
			
			
			return exists;
				
			
			
		}
	

}
