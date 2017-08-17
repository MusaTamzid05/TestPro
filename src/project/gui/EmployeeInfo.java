package project.gui;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.*;

import project.Defines;
import project.database.DataBaseConnector;
import project.database.manager.EmployeeManager;
import project.database.tables.Employee;
import project.util.Helper;

public class EmployeeInfo extends JFrame
{
	int posX = 50, posY = 20, labelWidth = 300, labelHeight = 50;
	int buttonWidth = 150, buttonHeight = 40;
	
	private JLabel imageLabel;
	
	private JLabel nameLabel;
	private JLabel ageLabel;
	private JLabel emailLabel;
	private JLabel contactLabel;
	
	
	private String universityButtonName;
	private String backButtonName;
	private String sscButtonName;
	private String hscButtonName;
	private String updateButtonName;
	private String deleteButtonName;
	
	private JButton universityInfoButton;
	private JButton sscResultButton;
	private JButton hscResultButton;
	private JButton backButton;
	private JButton updateButton;
	private JButton deleteButton;
	
	
	
	
	
	
	private boolean isRoot;
	private Employee employee;
	
	EventHandler eh = new EventHandler(this);
	
	public EmployeeInfo(Employee employee , boolean isRoot)
	{
		super("Employees Information");
		
		this.isRoot =isRoot;
		this.employee = employee;
		this.setLayout(null);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(640, 480);
		//getContentPane().setBackground(Color.LIGHT_GRAY);
		//setContentPane(new JLabel(new ImageIcon("C:\\Users\\USER\\Desktop\\Musa\\Java Project\\src\\back.jpg")));
		
		imageLabel = createLabel("" , 10 , 10 , 10 , 50);
		
		loadImage(employee.getImagePath());
		
		nameLabel = createLabel("Name : " + employee.getName() , 10 , 120);
		ageLabel = createLabel("Age : " + employee.getAge() , 10 , 140);
		emailLabel = createLabel("Email : " + employee.getEmail() , 10 , 160);
		contactLabel = createLabel("Contact : " + employee.getContact_no() , 10 , 180);
		
		
		universityButtonName = "University Info";
		universityInfoButton = createButton(universityButtonName , 30 , 300);
		
		sscButtonName = "SSC Info";
		sscResultButton = createButton(sscButtonName , buttonWidth + 20 , 300);
		
		hscButtonName = "HSC Info";
		sscResultButton = createButton(hscButtonName , 2 * buttonWidth + 20 , 300);
		
		backButtonName = "Back";
		backButton = createButton(backButtonName , 30 , 300 + buttonHeight);
		
		updateButtonName = "Edit";
		updateButton = createButton(updateButtonName , buttonWidth + 20 , buttonHeight + 300);
		
		deleteButtonName = "Delete";
		deleteButton = createButton(deleteButtonName , 2 * buttonWidth + 20 , buttonHeight + 300);
		
		
		setVisible(true);
		
		
		
		
	}
	
	private JLabel createLabel(String title, int posXchange, int posYchange)
	{
		JLabel label = new JLabel(title);
		label.setBounds(posX+posXchange, posY+posYchange, labelWidth, labelHeight);
		label.setForeground(Color.blue);
		add(label);
		return label;
	}
	
	private JLabel createLabel(String title, int posXchange, int posYchange, int width , int height)
	{
		JLabel label = new JLabel(title);
		label.setBounds(posX+posXchange, posY+posYchange, labelWidth + width, labelHeight + height);
		label.setForeground(Color.blue);
		add(label);
		return label;
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
	
	private void loadImage(String path) {
		
		File f = new File(path);
		
		if(!f.exists()) {
			imageLabel.setIcon(Helper.ResizeImage("/home/musa/no_user.jpg" , imageLabel));
			return;
		}
		
		imageLabel.setIcon(Helper.ResizeImage(path , imageLabel));
			
		
	
	}
	
	private class EventHandler implements ActionListener
	{
		EmployeeInfo ei;
		
		public EventHandler(EmployeeInfo ei)
		{
			this.ei = ei;
		}
		
		@Override
		public void actionPerformed(ActionEvent event){
			
			String check = event.getActionCommand();
			
			if(check.equals(updateButtonName)){
				
				ei.dispose();
				new AddEmployee(isRoot ,ei.employee );
			}
			else if(check.equals(backButtonName)){
				ei.dispose();
				new MainMenu(isRoot);
			}
			else if(check.equals(deleteButtonName)){
				
				
				int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + ei.employee.getName() + " ?");
				System.out.println("Choice : " + choice);
				
				if(choice == 0) {
					if(EmployeeManager.delete(Defines.EMPLOYEE_TABLE , "id" , ei.employee.getId())) {
						JOptionPane.showMessageDialog(null, "Data deleted");
						ei.dispose();
						new MainMenu(isRoot);
					}else
						JOptionPane.showMessageDialog(null, "Could not delete employee.");
						
				}
			
			}else if(check.equals(universityButtonName)){
				
			
			}
			else if(check.equals(sscButtonName)){
				
				
			}else if(check.equals(hscButtonName)){
				
			
			}
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