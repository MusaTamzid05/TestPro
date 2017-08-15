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
	private static void initTable() {
		
		if(tableLoaded)
			return;
		
		tableName = "admin";
		dataName = new ArrayList<String>();
		
		dataName.add("userName");
		dataName.add("password");
		
		tableLoaded = true;
		
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
			
		
		
	}
	
	public static boolean insert(Admin admin) {
		
		initTable();
		
		boolean dataInserted = false;
		
		String sql = getInsertQuery();
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
		initTable();
		
		String sql = getUpdateQuery();
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
			
			if(affected == 1)
				dataUpdated = true;
			
			
			
		}catch(SQLException stmt) {
			
		}
		
		return dataUpdated;
		
	}
	
	public static boolean delete(int id) {
		
		boolean dataDeleted = false;
		
		Admin admin = AdminManager.getRow(id);
		
		if(admin == null) {
			System.out.println("Admin with id " + id + " does not exists");
			return dataDeleted;
		}
		
		String sql = getDeleteQuery();
		PreparedStatement stmt = getPrepareStatement(sql , false);
		System.out.println(sql);
		
		if(stmt == null) {
			System.out.println("The delete statement from Admin could not been created.");
			return dataDeleted;
		}
		
		System.out.println("Delete statement created.");
		
		try {
			stmt.setInt(1 , id);
			
			int affected = stmt.executeUpdate();
			
			if(affected == 1)
				dataDeleted = true;
			else
				dataDeleted = false;
			
			
		} catch (SQLException e) {
			
			System.err.println(e.getMessage());
		
		}
		
		
		
		return dataDeleted;
	}
	
	
	public static ArrayList<Admin> getAll(){
			
			ArrayList<Admin> admins = new ArrayList<>();
			String sql = getAllQuery();
			
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
				
			}finally {
				
				if(rs != null)
					try {
						rs.close();
					} catch (SQLException e) {
						
					}
			}
				
			
			
			
			
		
			return admins;
		}

}
