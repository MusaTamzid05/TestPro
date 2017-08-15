package project.gui;


import javax.swing.*;

import project.database.DataBaseConnector;

import java.awt.*;
import java.awt.event.*;

public class AdminLogin extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7885442579952660836L;
	private JLabel mngtMsgLabel;
	private JButton adminLogin;
	
	public AdminLogin()
	{
		super("Admin Login");
		setLayout(null);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		//getContentPane().setBackground(Color.LIGHT_GRAY);
		setContentPane(new JLabel(new ImageIcon("/home/musa/eclipse-workspace/Pro2/resources/background1.jpg")));
		setVisible(true);
		
		mngtMsgLabel = new JLabel("Employee Management");
		mngtMsgLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		mngtMsgLabel.setBounds(150, 50, 450, 50);
		mngtMsgLabel.setForeground(Color.WHITE);
		add(mngtMsgLabel);
		
		EventHandler eh = new EventHandler(this);
		
		adminLogin = new JButton("Admin Login");
		adminLogin.setBounds(200, 130, 200, 50);
		adminLogin.addActionListener(eh);
		//adminLogin.setBackground(Color.white);
		adminLogin.setForeground(Color.red);
		add(adminLogin);
	}
	
	private class EventHandler implements ActionListener
	{
		AdminLogin al;
		
		public EventHandler(AdminLogin al)
		{
			this.al = al;
		}
		
		@Override
		public void actionPerformed(ActionEvent event)
		{
			String check = event.getActionCommand();
			
			if(check.equals("Admin Login")){
				al.dispose();
				new AdminValidation();
			}	
		}		
	}
	
	
}
