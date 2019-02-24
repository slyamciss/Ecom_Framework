package project.framework;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class Ecomtest07 {
	WebDriver driver;

	@Test
	public void savingfile() throws Exception {
		// click on my account link
		driver.findElement(By.linkText("MY ACCOUNT")).click();
		Thread.sleep(3000);
		
		//Login in application using previously created credential 
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("tinnindem1@gmail.com");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("TininDem");
		driver.findElement(By.id("send2")).click();
		Thread.sleep(3000);
		
		//Click on 'My Orders'
		driver.findElement(By.linkText("MY ORDERS")).click();
		
		// Click on 'View Order'
		driver.findElement(By.linkText("VIEW ORDER")).click();
		
		//Verify the previously created order is displayed in 'RECENT ORDERS' table and status is Pending
		String exp_Status = "ORDER #100008768 - PENDING";
		String actual_Status = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div[1]/h1")).getText();
		try {
		Assert.assertEquals(actual_Status, exp_Status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Click on 'Print Order' link
		driver.findElement(By.linkText("Print Order")).click();
		Thread.sleep(5000);
/*		
		// switching to new window                                                                               
	    for (String handle : driver.getWindowHandles()) {
	    	driver.switchTo().window(handle);
	    	} 
	  
	
		//Verify Order is saved as PDF
		boolean logo = driver.findElement(By.id("plugin")).isDisplayed();
		System.out.println(logo);
*/
	}

	@BeforeMethod
	public void lauchBrowser() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\slyam\\Desktop\\Jars Folder\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://live.guru99.com");
		driver.manage().window().maximize();

	}

	@AfterMethod
	public void closingBrowser() throws Exception {
//			driver.close();
//		driver.quit();
	}

}
