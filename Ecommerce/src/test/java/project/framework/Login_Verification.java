package project.framework;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;

public class Login_Verification {

	WebDriver driver;

	@BeforeMethod
	public void LaunchBrowser() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\slyam\\Desktop\\Jars Folder\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("http://www.demo.guru99.com/V4/");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
    @Parameters({"username","password"})
	@Test
	public void LoginInfo(String username, String password) throws Exception {
	    driver.findElement(By.name("uid")).sendKeys(username);
	    driver.findElement(By.name("password")).sendKeys(password);
	    driver.findElement(By.name("btnLogin")).click();
	    String EXPECT_TITLE = "Guru99 Bank Manager HomePage";
 
	    try {        
	    	Alert alt = driver.switchTo().alert();
	    	String actualBoxMsg = alt.getText();
	    	alt.accept();
	    	String Expect_ErrorMsg = "User or Password is not valid";
	    	Assert.assertEquals(actualBoxMsg, Expect_ErrorMsg);
	    	System.out.println(actualBoxMsg);
	    	System.out.println("Test case: Passed");
	}
     catch (NoAlertPresentException Ex) {
    	 boolean ManagerId = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[3]/td")).isDisplayed();
	     System.out.println(ManagerId);
    	 String actualTitle = driver.getTitle();
    	 System.out.println(actualTitle);
 	    Assert.assertEquals(actualTitle, EXPECT_TITLE);
 	    System.out.println("Test case: Passed");
     }
    
    }
	@AfterMethod
	public void ClosingBrowser() {
	//	driver.close();
		driver.quit();
	}
}
