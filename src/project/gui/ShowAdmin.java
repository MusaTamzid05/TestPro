package project.gui;

import javax.swing.*;

import project.Defines;
import project.database.DataBaseConnector;
import project.database.manager.AdminManager;
import project.database.manager.EmployeeManager;
import project.database.tables.Admin;

import java.awt.*;
import java.awt.event.*;

public class ShowAdmin extends JFrame
{
	int posX = 100, posY = 50, buttonWidth = 100, buttonHeight = 30, labelWidth = 100, labelHeight = 50;

	private JLabel nameLabel, nameLabel2, passLabel, passLabel2;
	private JButton editButton, deleteButton, backButton;
	
	private Admin admin;
	EventHandler eh = new EventHandler(this); 

	public ShowAdmin(Admin admin) {

		super("Show Admin");
		setLayout(null);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		setVisible(true);
		
		this.admin = admin;

		nameLabel = createLabel("Name        :", 0, 0);
		passLabel = createLabel("Pass         :", 0, 50);
		nameLabel2 = createLabel(admin.getUserName(), 100, 0);
		passLabel2 = createLabel(admin.getPassword(), 100, 50);

		editButton = createButton("Edit", 10, 150);
		deleteButton = createButton("Delete", 120, 150);
		backButton = createButton("Back", 230, 150);
	}

	private JLabel createLabel(String labelName, int posXchange, int posYchange) {

		JLabel label = new JLabel(labelName);
		label.setBounds(posX+posXchange, posY+posYchange, labelWidth, labelHeight);
		add(label);
		return label;
	}

	public JButton createButton(String buttonName,int posXchange, int posYchange) {

		JButton button = new JButton(buttonName);
		button.setBounds(posX+posXchange, posY+posYchange, buttonWidth, buttonHeight);
		button.addActionListener(eh);
		add(button);
		return button;
	}

	private class EventHandler implements ActionListener {

		ShowAdmin sa;
		
		public EventHandler(ShowAdmin sa)
		{
			this.sa = sa;
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {

			String check = event.getActionCommand();

			if(check.equals("Edit")) { 
				
				
				if(sa.admin.getUserName().equals("root")) {	
					JOptionPane.showMessageDialog(null, "Cannot edit root");
					return;
					
				}
				
				new EditAdmin(admin);
				sa.dispose();
			}
			else if(check.equals("Delete")) {
				
				if(sa.admin.getUserName().equals("root")) {
					
					JOptionPane.showMessageDialog(null, "Cannot delete root");
					return;
					
				}
				
				
				int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + sa.admin.getUserName() + " ?");
				System.out.println("Choice : " + choice);
				
				if(choice == 0) {
					if(AdminManager.delete(Defines.ADMIN_TABLE , "id" , sa.admin.getID())) {
						JOptionPane.showMessageDialog(null, "Data deleted");
						sa.dispose();
						new AdminSettings();
					}else
						JOptionPane.showMessageDialog(null, "Could not delete Admin.");
					

				}	//sa.dispose();
			}else if(check.equals("Back")) {
				sa.dispose();
				new AdminSettings();
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