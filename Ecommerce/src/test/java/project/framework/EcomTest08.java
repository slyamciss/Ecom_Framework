package project.framework;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class EcomTest08 {
	WebDriver driver;

	@Test
	public void changeVerification() throws Exception {
		// click on my account link
		driver.findElement(By.linkText("MY ACCOUNT")).click();
		Thread.sleep(3000);

		// Login in application using previously created credential
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("tinnindem1@gmail.com");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("TininDem");
		driver.findElement(By.id("send2")).click();
		Thread.sleep(3000);

		// click on "REORDER" link
		driver.findElement(By.linkText("REORDER")).click();

		// * Get the Grand Total Price
		String reorderGRAND_TOTAL = driver
				.findElement(By.xpath(".//*[@id='shopping-cart-totals-table']/tfoot/tr/td[2]/strong/span")).getText();
		System.out.println("The reorder total is :"+reorderGRAND_TOTAL);
		Thread.sleep(3000);

		// Change QTY & click Update
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div/form/table/tbody/tr/td[4]/input")).clear();
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div/form/table/tbody/tr/td[4]/input"))
				.sendKeys("10");
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("button.btn-update:nth-child(2)")).click();
		Thread.sleep(3000);

		// Verify Grand Total is changed

		String updateGRAND_TOTAL = driver
				.findElement(
						By.xpath("/html/body/div/div/div[2]/div/div/div/div[3]/div/table/tfoot/tr/td[2]/strong/span"))
				.getText();
		System.out.println("The updated total is :"+updateGRAND_TOTAL);
		if (updateGRAND_TOTAL == reorderGRAND_TOTAL) {
			System.out.println("*** Grand Total price has not changed. ***");
		} else {
			System.out.println("*** Grand Total price has changed. ***");
		}
		Thread.sleep(3000);

		// Complete Billing & Shipping Information
		driver.findElement(By.cssSelector(".method-checkout-cart-methods-onepage-bottom > button:nth-child(1)"))
				.click();
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/ol/li[1]/div[2]/form/div/div/button"))
				.click();
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/ol/li[3]/div[2]/form/div[3]/button")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("p_method_checkmo")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/ol/li[4]/div[2]/div[2]/button")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/ol/li[5]/div[2]/div/div[2]/div/button"))
				.click();
		Thread.sleep(3000);

		// Verify order is generated and note the order number
		String Order_Number = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/p[1]/a")).getText();
		System.out.println("Your order # is:" + Order_Number);
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
		driver.close();
		driver.quit();
	}

}
