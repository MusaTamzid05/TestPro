package project;

import project.database.*;
import project.database.manager.AdminManager;
import project.database.tables.Admin;


public class Main {
	
	
	public static void testClassifier() {
		
		int count = FaceDetector.getFaceCount("/home/musa/test3.jpg");	
		System.out.println("total face count " + count);
	}
	
	public static void testConnection() {
		
		DataBaseConnector.getInstance().getConnection();
		DataBaseConnector.getInstance().closeConnection();
		
	}
	
	public static void testDataBase() {
		
		/***********************  Insert Start ************/
	
		/*
		Admin admin = new Admin("Test" , "123");
		
		if(AdminManager.insert(admin))
			System.out.println("Data inserted.");
		else
			System.out.println("Data was not inserted.");
		*/
		
		/*************  Insert Finish *************/
		
		/*************  Update ****************/
		
		/*
		
		Admin admin = AdminManager.getRow(1);
		
		if(admin != null) {
			System.out.println("user name " + admin.getUserName());
			admin.setUserName("Iron Man");
			
			if(AdminManager.update(admin)) {
				System.out.println("user name " + admin.getUserName());
			}else {
				System.out.println("Update failed");
			}
			
		}else {
			System.out.println("id not found");
		}
		
		*/
		
		/************************/
		
		/********** Delete *******/
		
		/*
		if(AdminManager.delete(2)) {
			System.out.println("Admin deleted");
		}else {
			System.out.println("Failed to delete.");
		}
		
		*/
		/************************/
		
		
		
	
		
		
		
		
	}
	
	public static void main(String[] argv) {
		
		testConnection();
		//testClassifier();
	    //testDataBase();
		
	}
	
	
}
