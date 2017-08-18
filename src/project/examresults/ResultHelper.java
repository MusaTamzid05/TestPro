package project.examresults;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ResultHelper {
	
	private static final String RESULT_LINK = "http://www.educationboardresults.gov.bd/";
	
	
	private static void   selectDropDown(WebDriver driver , String id , String selectOption) throws Exception{
		
		
		Select element = new Select(driver.findElement(By.id(id)));
		element.selectByVisibleText(selectOption);
		
	}
	
	private static void  fillText(WebDriver driver , String id , String value) throws Exception {
		
		WebElement element = driver.findElement(By.id(id));
		element.sendKeys(value);
		
		
	}

	
	
		
	

	
	public static void showResultPage(String examName, String year , String board , String roll , String reg)  {
		
		 WebDriver driver = new FirefoxDriver();
		 
		 try {
			 
			 driver.get(RESULT_LINK);
			 String html = driver.findElement(By.tagName("html")).getAttribute("innerHTML");
			
			
			 
			 if(examName == "ssc")
				 examName =  "SSC/Dakhil";
			 else if(examName == "hsc")
				 examName = "HSC/Alim";
			 
			 
			selectDropDown(driver , "exam" , examName);
			selectDropDown(driver , "year" , year);
			selectDropDown(driver , "board" , board);
			fillText(driver , "roll" , roll);
			fillText(driver , "reg" , reg);
			int solve =CapshaSolver.solve(html); 
			fillText(driver , "value_s" , String.valueOf(solve));
			
			driver.findElement(By.id("button2")).click();
			 
			 
		 }catch(Exception e) {
			 
			 System.err.println("Could not get the result window.");
			 System.err.println(e.getMessage());
		 }
		
	}
	
	
	
	
	public static void main(String[] argv) {
		
		showResultPage("SSC(Vocational)" , "2011" , "Dhaka" , "222" , "333");
	}

}
