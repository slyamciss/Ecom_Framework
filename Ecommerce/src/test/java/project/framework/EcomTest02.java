package project.framework;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class EcomTest02 {
	WebDriver driver;

	@Test
	public void equalCost() throws Exception {
		// click on "MOBILE" menu
		driver.findElement(By.linkText("MOBILE")).click();

		// in the list of all mobile, read the cost of Sony Xperia mobile. Note this
		// value
		String cost1 = driver.findElement(By.cssSelector("#product-price-1 > span:nth-child(1)")).getText();
		System.out.println(cost1);
		Thread.sleep(3000);

		// click on sony Xperia mobile
		driver.findElement(By.linkText("SONY XPERIA")).click();

		// Read the cost of the Sony Xperia mobile from the detail page
		String cost2 = driver.findElement(By.cssSelector(".price")).getText();
		System.out.println(cost2);

		// compare value cost1 & cost2   
		try {
			Assert.assertEquals(cost1, cost2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\slyam\\Desktop\\Jars Folder\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("http://live.guru99.com");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void afterMethod() {
//		driver.close();
		driver.quit();
	}

}
