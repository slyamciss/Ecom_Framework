package project.framework;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class EcomTest03 {
	WebDriver driver;

	@Test
	public void verifyProduct() throws InterruptedException {
		// click on "MOBILE" menu
		driver.findElement(By.linkText("MOBILE")).click();
		
		// In the list of all mobile, click on "ADD TO CHART" for Sony Xperia mobile
		driver.findElement(By.cssSelector("li.item:nth-child(1) > div:nth-child(2) > div:nth-child(4) > button:nth-child(1)")).click();
		
		// change "QTY" value to 1000 
		driver.findElement(By.cssSelector(".product-cart-actions > input:nth-child(1)")).clear();
		driver.findElement(By.cssSelector(".product-cart-actions > input:nth-child(1)")).sendKeys("1000");
		Thread.sleep(3000);
		// click "UPDATE" button
		driver.findElement(By.cssSelector("button.btn-update:nth-child(2)")).click();
		
        // verify the error message		
		boolean error_Msg = driver.findElement(By.cssSelector(".item-msg")).isDisplayed();
		System.out.println(error_Msg);
		Thread.sleep(3000);
		//Then click on "EMPTY CART" link in the footer of the list of all mobiles
		driver.findElement(By.id("empty_cart_button")).click();
		
		// Verify cart is empty
		boolean error_EmptyCart = driver.findElement(By.cssSelector(".page-title > h1:nth-child(1)")).isDisplayed();
        System.out.println(error_EmptyCart);
        Thread.sleep(3000);
	}

	@BeforeMethod
	public void launchingBrowser() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\slyam\\Desktop\\Jars Folder\\geckodriver.exe");
		driver = new FirefoxDriver();		
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		driver.get("http://live.guru99.com");
	}

	@AfterMethod
	public void closingBrowser() {
//		driver.close();
		driver.quit();
	}

}
