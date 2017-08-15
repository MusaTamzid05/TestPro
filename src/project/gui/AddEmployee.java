package project.gui;

import java.io.File;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import project.database.DataBaseConnector;
import project.database.manager.UniversityInfoManager;
import project.database.manager.UniversityLinkManager;
import project.database.tables.UniversityInfo;
import project.database.tables.UniversityLink;

public class AddEmployee extends JFrame
{
	int posX = 120, posY = 20, labelWidth = 100, labelHeight = 50, txtFldWidth = 300, txtFldHeight = 30;
	int buttonWidth = 150, buttonHeight = 40, cmbBoxWidth = 100, cmbBoxHeight = 40;
	
	ExamInfo sscExamInfo;
	ExamInfo hscExamInfo;
	
	String imagePath = "";
	
	
	
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
	
	/*private static void setAge()
	{
		int ageIncr = 25;
		
		for(int i=0;i<26;i++)
		{
			age[i] = ageIncr + "";
			ageIncr++;
		}
	}*/

	EventHandler eh = new EventHandler(this);
	
	public AddEmployee()
	{
		super("Add Employee");
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(750, 850);
		//getContentPane().setBackground(Color.LIGHT_GRAY);
		setContentPane(new JLabel(new ImageIcon("C:\\Users\\USER\\Desktop\\Musa\\Java Project\\src\\back.jpg")));
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
		
		
		
		ArrayList<String> names = UniversityLinkManager.getUniversityNames();
		
		univarsityBox = new JComboBox();
		
		for(String name : names) 
			univarsityBox.addItem(name);
		
		univarsityBox.setSelectedIndex(0);
		univarsityBox.setBounds(posX+100, posY+500, cmbBoxWidth + 200, cmbBoxHeight);
		univarsityBox.addActionListener(eh);
		add(univarsityBox);
		
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
	
	public ImageIcon ResizeImage(String ImagePath)
    {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
	
	
	private void setUniversityData() {
		
		UniversityLink uniLink = UniversityLinkManager.SearchByName(univarsityBox.getSelectedItem().toString());
		System.out.println(uniLink.getId());
		int id = uniLink.getId();
		
		UniversityInfo info = UniversityInfoManager.searchByID(id);
		
		if(info == null) {
			System.out.println("Data is not available.");
			infoMsgLabel.setText("Downloading university data.");
		}
		
		
	}
	
	
	
	private void saveToDataBase() {
	
	
	    boolean allDataValidated = true;
		
		String sscYear = "";
		String sscRoll = "";
		String sscBoard = "";
		String sscReg = "";
		
		String hscYear = "";
		String hscRoll = "";
		String hscBoard = "";
		String  hscReg = "";
		
		
		if(sscExamInfo != null) {
			
			sscYear = sscExamInfo.getYear();
			sscRoll = sscExamInfo.getRoll();
			sscBoard = sscExamInfo.getBoard();
			sscReg = sscExamInfo.getRegistration();
			
		}
		
		if(hscExamInfo != null) {
			
			hscYear = hscExamInfo.getYear();
			hscRoll = hscExamInfo.getRoll();
			hscBoard = hscExamInfo.getBoard();
			hscReg = hscExamInfo.getRegistration();
		}
		
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
		
		if(!Validator.validateName(name)) {
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
		
		
		setUniversityData();
			
		
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
	        imageLabel.setIcon(ResizeImage(path));
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
			
			if(check.equals("Add Employee"))
			{
				saveToDataBase();
				
			}else if(check.equals("Browse Image")) {
				setImagePath();
			}
			else if(check.equals("SSC Info") ){
				
				sscExamInfo = new ExamInfo();
				new SscHscInfo(sscExamInfo);
			
			}else if(check.equals("HSC Info") ){
				
				hscExamInfo = new ExamInfo();
				new SscHscInfo(hscExamInfo);
			
			}
				
				
				//addEmployee.dispose();
				
		
			else if(check.equals("Back"))
			{
				addEmployee.dispose();
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

