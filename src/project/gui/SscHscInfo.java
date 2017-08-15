package project.gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SscHscInfo extends JFrame
{
	int posX = 50, posY = 20, labelWidth = 400, labelHeight = 50, txtFldWidth = 200, txtFldHeight = 30;
	int buttonWidth = 100, buttonHeight = 30, cmbBoxWidth = 200, cmbBoxHeight = 30;
	
	private static String exam[] = {"Select One", "SSC", "HSC"};
	private static String year[] = {"Select One", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997"};
	private static String board[] = {"Select One", "Barisal", "Chittagong", "Comilla", "Dhaka", "Dinajpur", "Jessore", "Rajshahi", "Sylhet"};
	
	private JLabel infoMsgLabel;
	private JLabel examNameLabel, yearLabel, boardLabel, rollLabel, regLabel;
	
	private JTextField rollFld, regFld;
	private JComboBox examBox, yearBox, boardBox;
	private JButton resetButton,submitButton, backButton;
	
	ExamInfo examInfo;
	
	EventHandler eh = new EventHandler(this);
	
	public SscHscInfo(ExamInfo examInfo)
	{
		super("Admin Validation");
		this.examInfo = examInfo;
		
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		//getContentPane().setBackground(Color.LIGHT_GRAY);
		setContentPane(new JLabel(new ImageIcon("C:\\Users\\USER\\Desktop\\Musa\\Java Project\\src\\back.jpg")));
		setVisible(true);
		
		infoMsgLabel = createLabel("Enter Valid Input to Get Result Info", 100, 0);
		infoMsgLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		yearLabel = createLabel("Year                 :", 130, 90);
		boardLabel = createLabel("Board              :", 130, 130);
		rollLabel = createLabel("Roll                  :", 130, 170);
		regLabel = createLabel("Reg: No.          :", 130, 210);
		
		
		
		yearBox = new JComboBox(year);
		yearBox.setSelectedIndex(0);
		yearBox.setBounds(posX+250, posY+100, cmbBoxWidth, cmbBoxHeight);
		yearBox.addActionListener(eh);
		add(yearBox);
		
		boardBox = new JComboBox(board);
		boardBox.setSelectedIndex(0);
		boardBox.setBounds(posX+250, posY+140, cmbBoxWidth, cmbBoxHeight);
		boardBox.addActionListener(eh);
		add(boardBox);
		
		rollFld = createTextField(250, 180);
		regFld = createTextField(250, 220);
		
		resetButton = createButton("Reset",130, 260);
		submitButton = createButton("Submit", 240, 260);
		backButton = createButton("Back", 350, 260);
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
	
	private JButton createButton(String title, int posXchange, int posYchange)
	{
		JButton button = new JButton(title);
		button.setBounds(posX+posXchange, posY+posYchange, buttonWidth, buttonHeight);
		button.addActionListener(eh);
		button.setForeground(Color.red);
		add(button);
		return button;
	}
	
	private class EventHandler implements ActionListener, FocusListener
	{
		SscHscInfo shi;
		
		public EventHandler(SscHscInfo shi)
		{
			this.shi = shi;
		}
		
		@Override
		public void actionPerformed(ActionEvent event)
		{
			String check = event.getActionCommand();
			
			if(check.equals("Reset"))
			{
				examBox.setSelectedIndex(0);
				yearBox.setSelectedIndex(0);
				boardBox.setSelectedIndex(0);
				rollFld.setText("");
				regFld.setText("");
			}
			else if(check.equals("Back"))
			{
				shi.dispose();
				new AddEmployee();
			}else if (check.equals("Submit")) {
				
				
				
				examInfo.setBoard(boardBox.getSelectedItem().toString());
				examInfo.setYear(yearBox.getSelectedItem().toString());
				examInfo.setRegistration(regFld.getText());
				examInfo.setRoll(rollFld.getText());
				
				
				shi.dispose();
				
				
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
}