package project.gui;

import javax.swing.*;

import project.database.DataBaseConnector;
import project.database.manager.AdminManager;

import java.awt.*;
import java.awt.event.*;

public class AdminValidation extends JFrame
{
	
	final String ROOT_USER = "root";
	/**
	 * 
	 */
	private static final long serialVersionUID = 4288882918707864438L;
	
	int posX = 150, posY = 20, labelWidth = 300, labelHeight = 50, txtFldWidth = 150, txtFldHeight = 30;
	int buttonWidth = 80, buttonHeight = 40;
	
	private JLabel userNameandPassMsg, userName, password;
	private JTextField userNameTxtFld, passTxtFld;
	private JButton logIn;
	
	EventHandler eh = new EventHandler(this);
	
	public AdminValidation()
	{
		super("Admin Validation");
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		//getContentPane().setBackground(Color.LIGHT_GRAY);
		setContentPane(new JLabel(new ImageIcon("/home/musa/eclipse-workspace/Pro2/resources/background1.jpg")));
		
		setVisible(true);
		
		userNameandPassMsg = createLabel("Enter Username and Password", 0, 0);
		userNameandPassMsg.setFont(new Font("Tahoma", Font.PLAIN, 20));
		userName = createLabel("User Name", 0, 60);
		password = createLabel("Password", 0, 100);
		
		userNameTxtFld = createTextField(80, 70);
		passTxtFld = createTextField(80, 110);
		
		logIn = new JButton("Log In");
		logIn.setBounds(posX+110, posY+150, buttonWidth, buttonHeight);
		logIn.addActionListener(eh);
		logIn.setForeground(Color.red);
		add(logIn);
	}
	
	private JLabel createLabel(String title, int posXchange, int posYchange)
	{
		JLabel label = new JLabel(title);
		label.setBounds(posX+posXchange, posY+posYchange, labelWidth, labelHeight);
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
	
	private class EventHandler implements ActionListener, FocusListener
	{
		AdminValidation av;
		
		public EventHandler(AdminValidation av)
		{
			this.av = av;
		}
		
		@Override
		public void actionPerformed(ActionEvent event)
		{
			String check = event.getActionCommand();
		
			if(check.equals("Log In")){
				
				String userName = userNameTxtFld.getText();
				String password = passTxtFld.getText();
				if(AdminManager.isLoggedInSucessfull(userName, password)){
					av.dispose();
					
					if(userName.equals(ROOT_USER))
						new MainMenu(true);
					else
						new MainMenu(false);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Login Unuccessful..Try again");
					userNameTxtFld.setText("");
					passTxtFld.setText("");
				}
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
