package project.gui;

import javax.swing.*;
import javax.swing.event.*;

import project.database.DataBaseConnector;
import project.database.manager.EmployeeManager;
import project.database.tables.Employee;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MainMenu extends JFrame
{
	int posX = 10, posY = 10;
	int buttonWidth = 140, buttonHeight = 20;
	private JButton addEmpButton,  logOutButton, adminSettingsButton;
	private JList list;
	
	ArrayList<Employee> employees;
	
	private boolean isRoot;

	private static String employeeNames[] = null;

	EventHandler eh = new EventHandler(this);

	public MainMenu(boolean isRoot)
	{
		super("Menu");
		this.isRoot = isRoot;
		//setLayout(null);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		
		//setContentPane(new JLabel(new ImageIcon("C:\\Users\\USER\\Desktop\\Musa\\Java Project\\src\\back.jpg")));
		
		
		if(isRoot)
			adminSettingsButton = createButton("Admin Settings", 0, 0);

		
		addEmpButton = createButton("Add Employee", 0, 0);
		
		logOutButton = createButton("Log Out", 300, 0);
		
		
		employees = EmployeeManager.getAll();
		employeeNames = new String[employees.size()];
		
		for(int i = 0 ; i < employees.size() ; i++)
			employeeNames[i]= employees.get(i).getName();

		list = new JList(employeeNames);
		//list.setBounds(posX+20, posY+50, 50, 50);
		list.setPreferredSize(new Dimension(300, 200));
		list.setVisibleRowCount(5);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(eh);
		add(new JScrollPane(list));
		
		//setContentPane(new JLabel(new ImageIcon("/home/musa/eclipse-workspace/Pro2/resources/background1.jpg")));
		
		setVisible(true);
		
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

	private class EventHandler implements ActionListener, ListSelectionListener
	{
	 	MainMenu menu;

		public EventHandler(MainMenu menu)
		{
			this.menu = menu;
		}

		@Override
		public void actionPerformed(ActionEvent event)
		{
			String check = event.getActionCommand();

			if(check.equals("Admin Settings"))
			{
				menu.dispose();
				new AdminSettings();
				
			}
			else if(check.equals("Add Employee"))
			{
				menu.dispose();
				new AddEmployee(isRoot , null);
			}
			else if(check.equals("Remove Employee"))
			{
				menu.dispose();
				//new RemoveEmployee();
			}
			else if(check.equals("Log Out"))
			{
				menu.dispose();
				Main.main(null);
				
			}
		}

		@Override
		public void valueChanged(ListSelectionEvent event){
			
			Employee employee = employees.get(list.getSelectedIndex());
			menu.dispose();
			new EmployeeInfo(employee , isRoot);
			
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
