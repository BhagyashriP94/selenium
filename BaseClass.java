package CommonUtils;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseClass {

	
	public WebDriver d;
	
	WebDriverUtil driverUtil = new WebDriverUtil();
	PropertyFileUtil fileUtil = new PropertyFileUtil();

	
	@BeforeSuite
	public void BS() {
		System.out.println("Connect to Data Base");
	}
	
	@BeforeClass
	public void BC() throws IOException {
		
		String url = fileUtil.getDataFromPropertyFile("Url");
		
		WebDriver d = new ChromeDriver();
		driverUtil.maximize(d);                                                 //to maximize the window
		driverUtil.implicitWait(d);												//to apply wait for findElement()
		
		d.get(url);
	}
	
	@BeforeMethod
	public void BM() throws IOException {
		
		String usrnm = fileUtil.getDataFromPropertyFile("Username");			
		String passwd = fileUtil.getDataFromPropertyFile("Password");
		
	//	login to application
		d.findElement(By.name("user_name")).sendKeys(usrnm);
		d.findElement(By.name("user_password")).sendKeys(passwd);
		d.findElement(By.id("submitButton")).click();
		
	}
	
	@AfterMethod()
	public void AM() {
		
//		mouseover on image
		WebElement icon = d.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		driverUtil.mouseHover(d, icon);
		
		d.findElement(By.xpath("//a[text()='Sign Out']")).click();
	}
	
	@AfterClass
	public void AC() {
		
		//@after class is use to close the browser
		d.quit();
	}
	
	@AfterSuite
	public void AS() {
		System.out.println("Disconnect from Data Base");
	}
}
