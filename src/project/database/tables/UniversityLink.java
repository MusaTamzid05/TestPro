package project.database.tables;

public class UniversityLink {
	
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	private int id;
	private String name;
	private String link;
	
	public UniversityLink(int id , String name , String link) {
		
		this.name = name;
		this.link = link;
		
	}
	
	

}
