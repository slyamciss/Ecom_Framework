package project.framework;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class EcomTest06 {
	WebDriver driver;

	@Test
	public void purchaseVerification() throws Exception {
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

		// Click on MY WISHLIST link
		driver.findElement(By.linkText("MY WISHLIST")).click();
		Thread.sleep(3000);
		
		// Click on MOBILE
		driver.findElement(By.linkText("MOBILE")).click();
		driver.findElement(By.linkText("SONY XPERIA")).click();
		Thread.sleep(5000);
		// In next page, click ADD TO CART link
		driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div[2]/div[2]/div[1]/form/div[4]/div/div/div[2]/button")).click();
		Thread.sleep(3000);
	

		// Enter general shipping country, state/province and zip for the shipping cost
		
		driver.findElement(By.id("country")).sendKeys("United States");		
		driver.findElement(By.id("region_id")).sendKeys("New York");		
		driver.findElement(By.id("postcode")).sendKeys("542896");
		Thread.sleep(3000);

		// Click Estimate
		driver.findElement(By.cssSelector(".buttons-set > button:nth-child(1)")).click();
		Thread.sleep(3000);

		// Verify Shipping cost generated
		String sFlatRate = "Flat Rate";
		String flatRate = driver.findElement(By.xpath(".//*[@id='co-shipping-method-form']/dl/dt")).getText();
		try {
			System.out.println("sFlatRate = " + sFlatRate);
			System.out.println("flatRate = " + flatRate);
			Assert.assertEquals(sFlatRate, flatRate);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String sFlatRatePrice = "Fixed - $5.00";
		String flatRatePrice = driver.findElement(By.xpath(".//*[@id='co-shipping-method-form']/dl/dd/ul/li/label"))
				.getText();
		try {
			System.out.println("sFlatRatePrice = " + sFlatRatePrice);
			System.out.println("flatRatePrice = " + flatRatePrice);
			Assert.assertEquals(sFlatRatePrice, flatRatePrice);
		} catch (Exception e) {
			e.printStackTrace();
		 }
		Thread.sleep(3000);
		
		//Select Shipping Cost (already selected as default), Update Total 
		
		driver.findElement(By.id("s_method_flatrate_flatrate")).click();
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div/div[2]/div/div/form[2]/div/button")).click();
		Thread.sleep(3000);
		
		//Verify shipping cost is added to total
		String vFlatRatePrice = "$5.00";
	    String shippingCostIncluded = driver.findElement(By.xpath(".//*[@id='shopping-cart-totals-table']/tbody/tr[2]/td[2]/span")).getText();
	    
	    try {
	    	System.out.println("vFlatRatePrice = "+vFlatRatePrice);
	    	System.out.println("shippingCostIncluded = "+shippingCostIncluded);
	    	Assert.assertEquals(vFlatRatePrice, shippingCostIncluded);
		    } catch (Exception e) {
		    	e.printStackTrace();	    	
		    }
	    Thread.sleep(3000);
		
	    // Click PROCEED TO CHECKOUT   
	    driver.findElement(By.cssSelector(".method-checkout-cart-methods-onepage-bottom > button:nth-child(1)")).click();
	    Thread.sleep(3000);
	    
	    // Enter Billing Information
	    
/*
	    
	    try {
	    	Select bAddr = new Select(driver.findElement(By.name("billing_address_id")));
	    	int bAddrSize = bAddr.getOptions().size();
	    	bAddr.selectByIndex(bAddrSize-1); 
		    } catch (Exception e) {
		    	//e.printStackTrace();
		    	System.out.println("No dropdown element present");
		    }
*/		    
		driver.findElement(By.id("billing:street1")).clear();
		driver.findElement(By.id("billing:street1")).sendKeys("ABC");		
		driver.findElement(By.id("billing:city")).sendKeys("New York");		
		driver.findElement(By.id("billing:postcode")).clear();
		driver.findElement(By.id("billing:postcode")).sendKeys("542896");		
		driver.findElement(By.id("billing:country_id")).sendKeys("United States");
		driver.findElement(By.id("billing:telephone")).clear();
		driver.findElement(By.id("billing:telephone")).sendKeys("12345678");
		driver.findElement(By.id("billing:region_id")).sendKeys("New York");
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/ol/li[1]/div[2]/form/div/div/button"))
				.click();
		driver.findElement(By.cssSelector("#billing-buttons-container > button:nth-child(1)")).click();
		Thread.sleep(3000);
		
		//In Shipping Method, Click Continue
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/ol/li[3]/div[2]/form/div[3]/button")).click();
		Thread.sleep(3000);
		
		//In Payment Information select 'Check/Money Order' radio button. Click Continue
		driver.findElement(By.id("p_method_checkmo")).click();
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/ol/li[4]/div[2]/div[2]/button")).click();
		Thread.sleep(3000);
		
		//Click 'PLACE ORDER' button
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/ol/li[5]/div[2]/div/div[2]/div/button")).click();
		Thread.sleep(3000);
		
		//Verify Oder is generated. Note the order number 
		String orderNum = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/p[1]/a")).getText();
		System.out.println("Your order number is:"+orderNum);
		Thread.sleep(3000);
 
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
//		driver.close();
		driver.quit();
	}

}
