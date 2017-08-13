package project.database.tables;




public class Admin  {
	
	private int id;
	private String userName;
	private String password;
	
	
	public Admin() {
	
		
	}
	
	
	
	public Admin(String userName , String password) {
		
		this.userName = userName;
		this.password = password;
	}
	

	public Admin(int id , String userName , String password) {
		
		this.id = id;
		this.userName = userName;
		this.password = password;
	}
	
	public int getID() {
		return id;
	}


	public void setId(int adminId) {
		this.id = adminId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	




	

	

}