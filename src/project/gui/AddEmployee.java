package project.gui;

import java.io.File;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import project.crawler.WikiScraper;
import project.database.DataBaseConnector;
import project.database.manager.EmployeeManager;
import project.database.manager.ExamInfoManager;
import project.database.manager.UniversityInfoManager;
import project.database.manager.UniversityLinkManager;
import project.database.tables.Employee;
import project.database.tables.ExamInfo;
import project.database.tables.UniversityInfo;
import project.database.tables.UniversityLink;
import project.util.Helper;

public class AddEmployee extends JFrame
{
	int posX = 120, posY = 20, labelWidth = 100, labelHeight = 50, txtFldWidth = 300, txtFldHeight = 30;
	int buttonWidth = 150, buttonHeight = 40, cmbBoxWidth = 100, cmbBoxHeight = 40;
	
	ExamInfo sscExamInfo;
	ExamInfo hscExamInfo;
	
	private String imagePath = "";
	
	
	
	private JLabel infoMsgLabel, imageLabel;
	private JLabel nameLabel, nameErrorLabel;
	private JLabel idLabel, idErrorLabel;
	private JLabel emailLabel, emailErrorLabel; 
	private JLabel contactLabel, contactErrorLabel;
	private JLabel ageLabel, cityLabel, varsityLabel;
	private JLabel downloadLabel;
	
	private JLabel imageErrorLabel;
	
	
	private JTextField nameFld, emailFld, contactFld;
	private JComboBox ageBox, cityBox, univarsityBox;
	private JButton addButton, backButton, sscInfoButton, hscInfoButton, browseButton;
	
	//private static String age[] = new String[26];
	private static String cityName[] = {"Select One", "Barisal", "Chittagong", "Comilla", "Dhaka", "Dinajpur", "Jessore", "Rajshahi", "Sylhet"};
	private static String varsityName[] = {"Select One", "AIUB", "BRAC", "BUET", "DU", "DIU", "EWU", "IUB", "NSU", "UIU"};
	private static String age2[] = {"Select One", "25", "26", "27", "28", "29", "30","31", "32", "33", "34", "35", "36", "37","38", "39", "40", "41", "42", "43", "44","45", "46", "47", "48", "49", "50"};
	
	private String addEditButtonName;
	
	private Employee employee;

	EventHandler eh = new EventHandler(this);
	
	private boolean isRoot;
	
	public AddEmployee(boolean isRoot , Employee employee)
	{
		super("Employee Data");
		
		this.employee = employee;
		
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(750, 850);
		//getContentPane().setBackground(Color.LIGHT_GRAY);
		setContentPane(new JLabel(new ImageIcon("C:\\Users\\USER\\Desktop\\Musa\\Java Project\\src\\back.jpg")));
		this.setLayout(null);
		setVisible(true);
		
		infoMsgLabel = createLabel("Enter Employees Information", 120, -10, 200, 0);
		infoMsgLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		imageLabel = createLabel("", 170, 50, 125, 120);
		nameLabel = createLabel("Name", 0, 220, 0, 0);
		nameErrorLabel = createLabel("", 410, 220, 0, 0);
		nameErrorLabel.setForeground(Color.red);
		
		
		emailLabel = createLabel("Email", 0, 320, 0, 0);
		emailErrorLabel = createLabel("", 410, 320, 0, 0);
		emailErrorLabel.setForeground(Color.red);
		contactLabel = createLabel("Contact No.", 0, 370, 0, 0);
		contactErrorLabel = createLabel("", 410, 370, 10, 0);
		contactErrorLabel.setForeground(Color.red);
		ageLabel = createLabel("Age", 0, 425, 0, 0);
		cityLabel = createLabel("City Name", 220, 425, 0, 0);
		varsityLabel = createLabel("Univarsity Name", 0, 495, 0, 0);
		
		ArrayList<String> names = UniversityLinkManager.getUniversityNames();
		univarsityBox = new JComboBox();
		
		for(String name : names) 
			univarsityBox.addItem(name);
		
		nameFld = createTextField(100, 230);
		
		emailFld = createTextField(100, 330);
		contactFld = createTextField(100, 380);
		
		ageBox = new JComboBox(age2);
		ageBox.setSelectedIndex(0);
		ageBox.setBounds(posX+100, posY+430, cmbBoxWidth, cmbBoxHeight);
		ageBox.addActionListener(eh);
		add(ageBox);
		
		
		cityBox = new JComboBox(cityName);
		cityBox.setSelectedIndex(0);
		cityBox.setBounds(posX+300, posY+430, cmbBoxWidth, cmbBoxHeight);
		cityBox.addActionListener(eh);
		add(cityBox);
		
		imageErrorLabel = createLabel("", 0, 10, 0, 0);
		
	
		
	
		
		univarsityBox.setSelectedIndex(0);
		univarsityBox.setBounds(posX+100, posY+500, cmbBoxWidth + 200, cmbBoxHeight);
		univarsityBox.addActionListener(eh);
		add(univarsityBox);
		
		
	
		if(employee != null) {
			
			// for updating employee.
			
			
			System.out.println("We are updating employee.");
			String temp = String.valueOf((employee.getAge()));
			ageBox.setSelectedItem(temp);
			cityBox.setSelectedItem(employee.getCityName());
			univarsityBox.setSelectedItem(employee.getUniversity_name());
			
			nameFld.setText(employee.getName());
			emailFld.setText(employee.getEmail());
			contactFld.setText(employee.getContact_no());
			imageLabel.setIcon(Helper.ResizeImage(employee.getImagePath() , imageLabel));
			imagePath = employee.getImagePath();
			
			addEditButtonName = "Edit Employee";
			addButton = createButton(addEditButtonName,100, 640);
			
			
		}else {
			
			System.out.println("We are adding employee.");
			addEditButtonName = "Add Employee";
			addButton = createButton(addEditButtonName,100, 640);
		}
		
		
		browseButton = createButton("Browse Image", 0, 100);
	
		sscInfoButton = createButton("SSC Info", 100, 550);
		hscInfoButton = createButton("HSC Info", 255, 550);
		addButton = createButton("Add Employee",100, 640);
		backButton = createButton("Back", 255, 640);
	}
	
	private JLabel createLabel(String title, int posXchange, int posYchange, int labelW, int labelH)
	{
		JLabel label = new JLabel(title);
		label.setBounds(posX+posXchange, posY+posYchange, labelWidth+labelW, labelHeight+labelH);
		label.setForeground(Color.blue);
		add(label);
		return label;
	}
	
	private JTextField createTextField(int posXchange, int posYchange)
	{
		JTextField txtFld = new JTextField();
		txtFld.setBounds(posX+posXchange, posY+posYchange, txtFldWidth, txtFldHeight);
		txtFld.addFocusListener(eh);
		add(txtFld);
		return txtFld;
	}
	
	private JButton createButton(String title, int posXchange, int posYchange)
	{
		JButton button = new JButton(title);
		button.setBounds(posX+posXchange, posY+posYchange, buttonWidth, buttonHeight);
		button.addActionListener(eh);
		button.setForeground(Color.red);
		add(button);
		return button;
	}
	
	
	
	private boolean dataBaseOperation(String operation) {
		
		// if the user wants to create a employee.
		if(operation == "insert") {
			
			System.out.println("This is a insert operation.");
			if(EmployeeManager.insert(employee))
			     return true;
			
			return false;
			
		}
		
		// here user is updating the employee record
		
		System.out.println("This is an update operation.");
		if(EmployeeManager.update(employee))
			return true;
	
		return false;
	}
	
	
	private void setUniversityData() {
		
		UniversityLink uniLink = UniversityLinkManager.SearchByName(univarsityBox.getSelectedItem().toString());
		System.out.println(uniLink.getId());
		int id = uniLink.getId();
		
		UniversityInfo info = UniversityInfoManager.searchByID(id);
		
		if(info == null) {
			System.out.println("Data is not available.");
			infoMsgLabel.setText("Downloading university data.");
			
			System.out.println("Getting info from " + uniLink.getLink());
			String link = "https://en.wikipedia.org" + uniLink.getLink();
			info = WikiScraper.getInfoFrom(link);;
			
			if(info == null) 
				JOptionPane.showMessageDialog(null, "Network connection problem!! University Info could be be download.");
			else {
				UniversityInfoManager.insert(info , id);
				JOptionPane.showMessageDialog(null, "Data downloaded successfully.");
			}
				
		
		}
		
	}
	
	
	private void saveResultData(String examName , int id , String operation , ExamInfo examInfo) {
		
		if(operation == "insert") {
			if(!ExamInfoManager.insert(examInfo))
				JOptionPane.showMessageDialog(null, examName + " data could not be inserted");
					
		}else if (operation == "update") {
			
			// if the data exists , only than will the data update.
			
			ExamInfo  temp = ExamInfoManager.getExamInfoOf(examName, id);
			
			if(temp == null) {
				if(!ExamInfoManager.insert(examInfo))
					JOptionPane.showMessageDialog(null,  examName + " data  not be updated");
			}else {
				if(!ExamInfoManager.update(examInfo))
					JOptionPane.showMessageDialog(null, examName + " data could not be updated");
				
			}
		}
		
	}
	
	private void saveResult(String operation) {
		
		ExamInfo examInfo = null;
		
		int id;
		
		if(employee == null) 
			id = EmployeeManager.getLastID();
		else 
			id = employee.getId();
		
			

		
		String year = "";
		String roll = "";
		String board = "";
		String reg = "";
		
		
		
		
		if(sscExamInfo != null) {
			
			year = sscExamInfo.getYear();
			roll = sscExamInfo.getRoll();
			board = sscExamInfo.getBoard();
			reg = sscExamInfo.getReg();
			
			examInfo  =  new ExamInfo("ssc" , id ,
					year , board , roll , reg );
			
			saveResultData("ssc" ,id , operation , examInfo);
		
			
		}
		
		if(hscExamInfo != null) {
			
			year= hscExamInfo.getYear();
			roll = hscExamInfo.getRoll();
			board = hscExamInfo.getBoard();
			reg  = hscExamInfo.getReg();
			
			examInfo  =  new ExamInfo("hsc" , id ,
					year , board , roll , reg );
			
			saveResultData("hsc" ,id , operation , examInfo);
			
			}
				
	}
	
	
	private boolean saveToDataBase() {
		
		
		
		
	    boolean allDataValidated = true;
		
	
		
		String name = nameFld.getText();
		String email = emailFld.getText();
		String contact = contactFld.getText();
		String age = ageBox.getSelectedItem().toString();
		String universityName =  univarsityBox.getSelectedItem().toString();
		String city= cityBox.getSelectedItem().toString();
		
		if(!Validator.validateImage(imagePath)) {
			imageErrorLabel.setText("Invalid image");
			allDataValidated = false;
			
		}
			
		else
			imageErrorLabel.setText("Valid image");
		
		
		boolean shouldCheckDatabase = false;
		
		//if  we are creating a new employee , so we should database for name
		// if the name exists , we wont validate.if we are updating
		// we should check database as the name is already there.
		
		if(employee == null) 
			shouldCheckDatabase = true;	
			
		
		if(!Validator.validateName(name , shouldCheckDatabase)) {
			nameErrorLabel.setText("Failed");
			allDataValidated = false;
			
		}else
			nameErrorLabel.setText("Name valid.");
		
		if(!Validator.validateEmail(email)) {
			emailErrorLabel.setText("Failed");
			allDataValidated = false;
			
		}else
			emailErrorLabel.setText("Email validated");
		
		if(!Validator.validateContact(contact)) {
			contactErrorLabel.setText("failed");
			allDataValidated = false;
		}else 
			contactErrorLabel.setText("valid");
		
		if((ageBox.getSelectedIndex() == 0) || (cityBox.getSelectedIndex() == 1) ) {
			allDataValidated = false;
			
		}
		
		if(!allDataValidated)
			return false;
		
		setUniversityData();
		
		String operation = "";
		
		if(employee == null) {
			
			operation = "insert";
			employee = new Employee();
			employee.setName(name);
			employee.setAge(Integer.parseInt(age));
			employee.setContact_no(contact);
			employee.setCityName(cityBox.getSelectedItem().toString());
			employee.setImagePath(imagePath);
			employee.setEmail(email);
			employee.setUniversityName(univarsityBox.getSelectedItem().toString());
				
		}else {
			
			operation = "update";
			employee.setName(nameFld.getText());
			employee.setAge(Integer.parseInt(ageBox.getSelectedItem().toString()));
			employee.setContact_no(contactFld.getText());
			employee.setCityName(cityBox.getSelectedItem().toString());
			employee.setImagePath(imagePath);
			employee.setUniversityName(univarsityBox.getSelectedItem().toString());
			
		}
		
		
		
		if(dataBaseOperation(operation)) {
			
			saveResult(operation);
			return true;
			
		}
			
		return false;
		
	}
	
	
	private void setImagePath() {
		
		JFileChooser file = new JFileChooser();
	    file.setCurrentDirectory(new File(System.getProperty("user.home")));
	    
	    //filter the files
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
	    file.addChoosableFileFilter(filter);
	    int result = file.showSaveDialog(null);
	    
	    //if the user click on save in Jfilechooser
	    if(result == JFileChooser.APPROVE_OPTION)
	    {
	        File selectedFile = file.getSelectedFile();
	        String path = selectedFile.getAbsolutePath();
	        imagePath = path;
	        imageLabel.setIcon(Helper.ResizeImage(path , imageLabel));
	    }
	}
	

	
	private class EventHandler implements ActionListener, FocusListener
	{
		AddEmployee addEmployee;
		
		public EventHandler(AddEmployee addEmployee)
		{
			this.addEmployee = addEmployee;
		}
		
		@Override
		public void actionPerformed(ActionEvent event)
		{
			String check = event.getActionCommand();
			
			if(check.equals(addEditButtonName))
			{
				if(saveToDataBase()) {
					
					JOptionPane.showMessageDialog(null, "Data Inserted.");
					addEmployee.dispose();
					new MainMenu(isRoot);
					
				}
						
			}else if(check.equals("Browse Image")) {
				setImagePath();
			}
			else if(check.equals("SSC Info") ){
				
				sscExamInfo = new ExamInfo();
				new SscHscInfo(sscExamInfo , isRoot);
			
			}else if(check.equals("HSC Info") ){
				
				hscExamInfo = new ExamInfo();
				new SscHscInfo(hscExamInfo , isRoot);
			
			}
				
				
				//addEmployee.dispose();
				
		
			else if(check.equals("Back"))
			{
				addEmployee.dispose();
				new MainMenu(isRoot);
				//new ShowList();
			}
		}
	
	
		@Override
		public void focusGained(FocusEvent event)
		{
			event.getComponent().setBackground(Color.GREEN);
		}
		
		@Override
		public void focusLost(FocusEvent event)
		{
			event.getComponent().setBackground(UIManager.getColor("TextField.background"));
		}
	}
	
		@Override
		public void processWindowEvent(WindowEvent event) {
			
			if(event.getID() == WindowEvent.WINDOW_CLOSING) {
				DataBaseConnector.getInstance().closeConnection();
				dispose();
				
			}
				
		
	}
}

