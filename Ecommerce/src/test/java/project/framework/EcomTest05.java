package project.framework;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class EcomTest05 {
	WebDriver driver;
	
  @Test
  public void comparingProduct() throws Exception {
		// click on my account link
		driver.findElement(By.linkText("MY ACCOUNT")).click();
		Thread.sleep(3000);
		
		//click Create Account link and fill New User information except Email ID
		driver.findElement(By.linkText("CREATE AN ACCOUNT")).click();
		driver.findElement(By.id("firstname")).sendKeys("Tinnin2");
		driver.findElement(By.id("lastname")).sendKeys("Dembele2");
		driver.findElement(By.id("email_address")).sendKeys("tinnindem2@gmail.com");
		driver.findElement(By.id("password")).sendKeys("TininDem");
		driver.findElement(By.id("confirmation")).sendKeys("TininDem");
		
		
		//click Register
		driver.findElement(By.cssSelector("button.button:nth-child(2)")).click();
		Thread.sleep(3000);
		
		//Verify registration is done
		String exp_WelcomeMsg = "Thank you for registering with Main Website Store.";
		String actual_WelcomeMsg = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/ul/li/ul/li/span")).getText();
		try {
			Assert.assertEquals(actual_WelcomeMsg, exp_WelcomeMsg);		
		} catch(Exception e) {
			e.printStackTrace();	
		}
		Thread.sleep(3000);
		
		//Go to TV menu
		driver.findElement(By.linkText("TV")).click();
		
		//Add product in your wish list
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div[1]/div[2]/ul/li[1]/div/div[3]/ul/li[1]/a")).click();
		Thread.sleep(3000);
		
		//Click SHARE WISHLIST
		driver.findElement(By.name("save_and_share")).click();
		Thread.sleep(3000);
		
		// In next page enter Email and a message and click SHARE WISHLIST
		driver.findElement(By.id("email_address")).sendKeys("slyamciss@gmail.com");
		driver.findElement(By.id("message")).sendKeys("This is something i want to buy for the house. what do you think honey");
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/form/div[2]/button")).click();
		Thread.sleep(3000);
		
		//Check wishlist is shared
		String exp_wishSharedMsg = "Your Wishlist has been shared.";
		String actual_WishSharedMsg = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div[1]/ul/li/ul/li/span")).getText();
		try {
			Assert.assertEquals(actual_WishSharedMsg, exp_wishSharedMsg);		
		} catch(Exception e) {
			e.printStackTrace();	
		}
		Thread.sleep(3000);
  }
  
  @BeforeMethod
  public void lauchBrowser() {
	  System.setProperty("webdriver.gecko.driver", "C:\\Users\\slyam\\Desktop\\Jars Folder\\geckodriver.exe");
		driver = new FirefoxDriver();		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://live.guru99.com");
  }

  @AfterMethod
  public void closingBrowser() throws Exception {
//		driver.close();
		driver.quit();
  }

}
