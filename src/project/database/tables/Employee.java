package project.database.tables;

public class Employee {
	
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact_no() {
		return contactNo;
	}
	public void setContact_no(String contact_no) {
		this.contactNo = contact_no;
	}
	public String getUniversity_name() {
		return universityName;
	}
	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	private String name;
	private String email;
	private String contactNo;
	private String universityName;
	private String cityName;
	private String imagePath;
	
	
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	private int age;
	
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public Employee(int id , String name ,  int age , String email , 
			String contact, String universityName , String cityName , 
			String imagePath ) {
		
		this.id = id;
		this.name = name;
		this.age = age;
		this.email = email;
		this.contactNo = contact;
		this.cityName = cityName;
		this.universityName = universityName;
		this.imagePath = imagePath;
		
	}
	
	public Employee() {
		
	}
	
	
}
