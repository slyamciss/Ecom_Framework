package project.framework;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;

public class EcomTest04 {
	WebDriver driver;
	public int scc = 0;
  @Test
  public void comparingProduct() throws Exception {
		// click on "MOBILE" menu
		driver.findElement(By.linkText("MOBILE")).click();
		Thread.sleep(3000);
		//In mobile product list, click on "Add to compare" for 2 mobiles
		driver.findElement(By.cssSelector("li.last:nth-child(1) > div:nth-child(2) > div:nth-child(4) > ul:nth-child(2) > li:nth-child(2) > a:nth-child(2)")).click();
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[2]/div/div[3]/ul/li[2]/a")).click();
		
		//click on "COMPARE" button
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[3]/div[1]/div[2]/div/button")).click();
		
		 // switching to new window
	    for (String handle : driver.getWindowHandles()) {
	    	driver.switchTo().window(handle);
	    	}
		Thread.sleep(3000);
		//Verify the pop-up window and check that the products are reflected in it
		boolean PopWindow = driver.findElement(By.cssSelector(".page-title > h1:nth-child(1)")).isDisplayed();
		System.out.println(PopWindow);
		
		scc = (scc + 1);
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String png = ("C:\\Users\\slyam\\Desktop\\Project E_commerce Script\\screen shot\\Mobile Products are sorted2" + scc
				+ ".png");
		FileUtils.copyFile(scrFile, new File(png));
	
		//Close the Popup Windows
		
		driver.findElement(By.cssSelector(".buttons-set > button:nth-child(1)")).click();
		Thread.sleep(3000);
		
  }
  @BeforeMethod
  public void lauchBrowser() {
	  System.setProperty("webdriver.gecko.driver", "C:\\Users\\slyam\\Desktop\\Jars Folder\\geckodriver.exe");
		driver = new FirefoxDriver();		
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		driver.get("http://live.guru99.com");
  }

  @AfterMethod
  public void closingBrowser() throws Exception {
//		driver.close();
		Thread.sleep(3000);
		driver.quit();
  }

}
