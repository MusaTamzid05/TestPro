package project.gui;
import javax.swing.*;
import javax.swing.event.*;

import project.database.manager.AdminManager;
import project.database.tables.Admin;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class AdminSettings extends JFrame
{
	int posX = 10, posY = 10;
	int buttonWidth = 140, buttonHeight = 20;

	private static String employeeNames[] = {
			"Musa", "Linkon", "Salman",
			"Ripon", "Zahid", "Maisha",
			"Mushfiq", "Faysal", "Shakib"};

	private JLabel adminSettingsMsgLabel;
	private JButton addAdminButton, backButton;
	private JList list;

	EventHandler eh = new EventHandler(this);
	
	ArrayList<Admin> admins;

	public AdminSettings() {

		super("Admin Settings");
		//setLayout(null);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setVisible(true);

		adminSettingsMsgLabel = new JLabel("Admin Settings");
		adminSettingsMsgLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));

		adminSettingsMsgLabel = new JLabel("Admin Settings");
		adminSettingsMsgLabel.setBounds(posX+150, posY, 300, 200);
		add(adminSettingsMsgLabel);

		addAdminButton = createButton("Add Admin", 0);
		backButton = createButton("Back", 80);
		
		admins = AdminManager.getAll();
		employeeNames = new String[admins.size()];
		
		for(int i = 0 ; i < admins.size(); i++)
			employeeNames[i] = admins.get(i).getUserName();

		list = new JList(employeeNames);
		list.setPreferredSize(new Dimension(200, 100));
		list.setVisibleRowCount(5);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(eh);
		add(new JScrollPane(list));
	}

	private JButton createButton(String title, int posYchange) {

		JButton button = new JButton(title);
		button.setBounds(posX, posY+posYchange, buttonWidth, buttonHeight);
		button.addActionListener(eh);
		button.setForeground(Color.red);
		add(button);
		return button;
	}

	private class EventHandler implements ActionListener, ListSelectionListener {
		
		AdminSettings as;
		
		public EventHandler(AdminSettings as) {

			this.as = as;
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {

			String check = event.getActionCommand();
			if(check.equals("Add Admin")) {
			
				as.dispose();
				new EditAdmin(null);
			}

			else if(check.equals("Back")) {

				as.dispose();
				new MainMenu(true);
			}
		}

		@Override
		public void valueChanged(ListSelectionEvent event) {

			Admin admin = admins.get(list.getSelectedIndex());
			as.dispose();
			new ShowAdmin(admin);
		}
	}
}
