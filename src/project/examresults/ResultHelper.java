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
	
	private static void  fillText(WebDriver driver , String id , String roll) throws Exception {
		
		WebElement element = driver.findElement(By.id(id));
		element.sendKeys(roll);
		
		
	}

	
	
		
	

	
	public static void showResultPage(String examName, String year , String board) {
		
		 WebDriver driver = new FirefoxDriver();
		 
		 try {
			 
			 driver.get(RESULT_LINK);
			 String html = driver.findElement(By.tagName("html")).getAttribute("innerHTML");
			
			
			 
			 
			selectDropDown(driver , "exam" , "SSC(Vocational)");
			selectDropDown(driver , "year" , year);
			selectDropDown(driver , "board" , board);
			fillText(driver , "roll" , "123");
			fillText(driver , "reg" , "123");
			int solve =CapshaSolver.solve(html); 
			fillText(driver , "value_s" , String.valueOf(solve));
			
			driver.findElement(By.id("button2")).click();
			 
			 
		 }catch(Exception e) {
			 
			 System.err.println("Could not get the result window.");
			 System.err.println(e.getMessage());
		 }
		
	}
	
	
	
	
	public static void main(String[] argv) {
		
		showResultPage("SSC(Vocational)" , "2011" , "Dhaka");
	}

}
