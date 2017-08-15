package project.gui;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import project.FaceDetector;
import project.database.manager.EmployeeManager;
import project.database.manager.QueryManager;
import project.util.Regex;

public class Validator {
	
	static final String EMAIL_REGEX = ".*@(gmail|hotmail|yahoo).com";
	
	public static boolean validateImage(String imagePath) {
		
		boolean isImageValid = false;
		
		if(imagePath.length() != 0) {
			
			int count = FaceDetector.getFaceCount(imagePath);
			System.out.println("Total face count " + count);
			
			if(count == 1)
				isImageValid = true;
			
		}
		
		
		return isImageValid;
			
		
	}
	
	public static boolean validateName(String name) {
		
		boolean isNameValid = false;
		
		if(name.length() != 0) {
			
			if(EmployeeManager.getEmployeeByName(name) == null)
				isNameValid = true;
		
			
		}
		
		return isNameValid;
	}
	
	public static boolean validateEmail(String email) {
		
		boolean isEmailValid = false;
		
		if(Regex.matchFound(EMAIL_REGEX , email))
			isEmailValid = true;
		
		return isEmailValid;
		
		
	}
	
	public static boolean validateContact(String number) {
		
		boolean isNumberValidated = false;
		
		if(number.length() == 11) {
			
			if(Regex.matchFound("^01(5|6|7|8)\\d\\d\\d\\d\\d\\d\\d\\d", number))
				isNumberValidated = true;
			
		}
		
		
		return isNumberValidated;
	}

}
