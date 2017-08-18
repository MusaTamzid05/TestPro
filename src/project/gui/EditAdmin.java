package project.gui;


import javax.swing.*;

import project.database.DataBaseConnector;
import project.database.manager.AdminManager;
import project.database.tables.Admin;

import java.awt.*;
import java.awt.event.*;

public class EditAdmin extends JFrame {

	int posX = 150, posY = 50, buttonWidth = 100, buttonHeight = 30, labelWidth = 100, labelHeight = 50, fldWidth = 150, fldHeight = 30;

	private JLabel nameLabel, passLabel;
	private JTextField nameFld, passFld;
	
	private String addUpdateButtonName;
	private JButton addUpdateButton, backButton;
	
	private Admin admin;

	EventHandler eh = new EventHandler(this); 

	public EditAdmin(Admin admin) {

		super("Edit Admin");
		
		this.admin = admin;
		setLayout(null);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		
		setSize(600, 400);
		setVisible(true);

		nameLabel = createLabel("Name      :", 0, 0);
		passLabel = createLabel("Pass       :", 0, 50);

		nameFld = createTextField(70, 10);
		passFld = createTextField(70, 60);
		
		if(admin != null) {
			nameFld.setText(admin.getUserName());
			passFld.setText(admin.getPassword());
			addUpdateButtonName = "Edit";
		}else
			addUpdateButtonName = "Add";

		addUpdateButton  = createButton(addUpdateButtonName, 30, 150);
		backButton = createButton("Back", 140, 150);
		
		System.out.println("Button name " + addUpdateButtonName);
	}

	private JLabel createLabel(String labelName, int posXchange, int posYchange) {

		JLabel label = new JLabel(labelName);
		label.setBounds(posX+posXchange, posY+posYchange, labelWidth, labelHeight);
		add(label);
		return label;
	}

	private JTextField createTextField(int posXchange, int posYchange) {

		JTextField txtFld = new JTextField();
		txtFld.setBounds(posX+posXchange, posY+posYchange, fldWidth, fldHeight);
		txtFld.addFocusListener(eh);
		add(txtFld);
		return txtFld;
	}	

	public JButton createButton(String buttonName,int posXchange, int posYchange) {

		JButton button = new JButton(buttonName);
		button.setBounds(posX+posXchange, posY+posYchange, buttonWidth, buttonHeight);
		button.addActionListener(eh);
		add(button);
		return button;
	}
	
	
	boolean userNameExists() {
		
		if(AdminManager.nameExists(nameFld.getText())) {
			JOptionPane.showMessageDialog(null, "Name already exists !");
			return true;
		}
		
		return false;
	}

	private class EventHandler implements ActionListener, FocusListener {

		EditAdmin ea;
		
		public EventHandler(EditAdmin ea) {

			this.ea = ea;
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {

			String check = event.getActionCommand();

			if(check.equals(addUpdateButtonName)) { 
				
				
				
				if(admin == null) {
					
					// we are trying to create a new admin.
					
					
					if(userNameExists())
						return;
					
					admin = new Admin(nameFld.getText() , passFld.getText());
					
					if(AdminManager.insert(admin)) {
						JOptionPane.showMessageDialog(null, "Data inserted");
						
						ea.dispose();
						new AdminSettings();
						
					}
					
					
				}else {
					System.out.println("Update ..");
					
					// its a update command
					
					if(userNameExists())
						return;
					
					admin.setUserName(nameFld.getText());
					admin.setPassword(passFld.getText());
					
					
					
					if(AdminManager.update(admin)) {
						JOptionPane.showMessageDialog(null, "data updated");
						ea.dispose();
						new AdminSettings();
					}
				}

				ea.dispose();
				new AdminSettings();
			}
			else if(check.equals("Back")) {

				ea.dispose();
				new AdminSettings();
			}
		}

		@Override
		public void focusGained(FocusEvent event) {

			event.getComponent().setBackground(Color.GREEN);
		}
		
		@Override
		public void focusLost(FocusEvent event) {

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