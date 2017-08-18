package project.gui;
import javax.swing.*;

import project.database.DataBaseConnector;

import java.awt.*;
import java.awt.event.*;

public class UnInfoPage extends JFrame {

	int posX = 180, posY = 50, buttonWidth = 100, buttonHeight = 30, labelWidth = 180, labelHeight = 50;

	private JLabel name, otherStudents, campus, underGraduates, postGraduates, doctoralStudents, website, mottoInEnglish;
	private JLabel academicStaff, type, administrativeStaff, students, viceChancellor, established, location, uniId; 
	
	private JLabel name2, otherStudents2, campus2, underGraduates2, postGraduates2, doctoralStudents2, website2, mottoInEnglish2;
	private JLabel academicStaff2, type2, administrativeStaff2, students2, viceChancellor2, established2, location2, uniId2; 

	private JButton backButton;

	public UnInfoPage(String name2Val, String otherStudents2Val, String campus2Val, String underGraduates2Val, 
		String postGraduates2Val, String doctoralStudents2Val, String website2Val, String mottoInEnglish2Val, 
		String academicStaff2Val, String type2Val, String administrativeStaff2Val, String students2Val, 
		String viceChancellor2Val, String established2Val, String location2Val) {

		super("University Information");
		setLayout(null);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		
		setSize(700, 750);
		setVisible(true);

		name = createLabel("Name :", 0, 0);
		name2 = createLabel(name2Val, 150, 0);
		otherStudents = createLabel("Other Students :", 0, 30);
		otherStudents2 = createLabel(otherStudents2Val, 150, 30 , 100 , 0);
		campus = createLabel("Campus :", 0, 60);
		campus2 = createLabel(campus2Val, 150, 60 , 100 , 0);
		underGraduates = createLabel("Undergraguates :", 0, 90);
		underGraduates2 = createLabel(underGraduates2Val, 150, 90 , 100 , 0);
		postGraduates = createLabel("Postgraduates :", 0, 120);
		postGraduates2 = createLabel(postGraduates2Val, 150, 120 , 100 , 0);
		doctoralStudents = createLabel("Doctoral Students :", 0, 150);
		doctoralStudents2 = createLabel(doctoralStudents2Val, 150, 150 , 100 , 0);
		website = createLabel("Website :", 0, 180);
		website2 = createLabel(website2Val, 150, 180 , 100 , 0);
		mottoInEnglish = createLabel("Motto in English :", 0, 210);
		mottoInEnglish2 = createLabel(mottoInEnglish2Val, 150, 210 , 100 , 0);
		academicStaff = createLabel("Academic Staff :", 0, 240);
		academicStaff2 = createLabel(academicStaff2Val, 150, 240 , 100 , 0);
		type = createLabel("Type :", 0, 270);
		type2 = createLabel(type2Val, 150, 270 , 100 , 0);
		administrativeStaff = createLabel("Administrative Staff :", 0, 300);
		administrativeStaff2 = createLabel(administrativeStaff2Val, 150, 300 , 100 , 0);
		students = createLabel("Students :", 0, 330);
		students2 = createLabel(students2Val, 150, 330 , 100 , 0);
		viceChancellor = createLabel("Vice Chancellor :", 0, 360);
		viceChancellor2 = createLabel(viceChancellor2Val, 150, 360 , 100 , 0);
		established = createLabel("Established :", 0, 390);
		established2 = createLabel(established2Val, 150, 390 , 100 , 0);
		location = createLabel("Location :", 0, 420);
		location2 = createLabel(location2Val, 150, 420 , 100 , 0);
	
		

		EventHandler eh = new EventHandler(this);

		backButton = new JButton("Back");
		backButton.setBounds(posX+60, posY+550, buttonWidth, buttonHeight);
		backButton.addActionListener(eh);
		add(backButton);
	}

	private JLabel createLabel(String labelName, int posXchange, int posYchange) {

		JLabel label = new JLabel(labelName);
		label.setBounds(posX+posXchange, posY+posYchange, labelWidth, labelHeight);
		add(label);
		return label;
	}
	
	private JLabel createLabel(String labelName, int posXchange, int posYchange , int width , int height) {

		JLabel label = new JLabel(labelName);
		label.setBounds(posX+posXchange, posY+posYchange, labelWidth + width , labelHeight + height);
		add(label);
		return label;
	}

	private class EventHandler implements ActionListener {

		UnInfoPage ui;
		
		public EventHandler(UnInfoPage ui) {

			this.ui = ui;
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {

			String check = event.getActionCommand();

			if(check.equals("Back")) {

				ui.dispose();
			}
		}
	}
	
	public void processWindowEvent(WindowEvent event) {
		
		if(event.getID() == WindowEvent.WINDOW_CLOSING) 
		dispose();
		
	}
}