package project.database.tables;

public class ExamInfo {
	
	private String examName;
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getBoard() {
		return board;
	}
	public void setBoard(String board) {
		this.board = board;
	}
	public String getRoll() {
		return roll;
	}
	public void setRoll(String roll) {
		this.roll = roll;
	}
	public String getReg() {
		return reg;
	}
	public void setReg(String reg) {
		this.reg = reg;
	}
	private int id;
	private int employeeID;
	private String year;
	private String board;
	private String roll;
	private String reg;

	
	public ExamInfo( String examName , int employeeID , String year , String board , String roll ,
			String reg) {
		
		
		this.employeeID =employeeID;
		this.year = year;
		this.board = board;
		this.roll  = roll;
		this.reg  = reg;
		this.examName = examName;
		
		
		
		
	}
	
	public ExamInfo() {
		
	}

}
