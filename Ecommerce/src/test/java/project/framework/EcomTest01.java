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

public class EcomTest01 {
	WebDriver driver;
	public int scc = 0;

	@Test
	public void itemVerification() throws Exception {
		String demoSite = driver.findElement(By.cssSelector("h2")).getText();
		System.out.println(demoSite);
		Assert.assertEquals(demoSite, "THIS IS DEMO SITE FOR   ");
		driver.findElement(By.linkText("MOBILE")).click();
		String mobilePageTitle = driver.getTitle();
		System.out.println(mobilePageTitle);
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div[1]/div[3]/div[1]/div[1]/div/select"))
				.sendKeys("Name");
		Thread.sleep(3000);
		scc = (scc + 1);
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String png = ("C:\\Users\\slyam\\Desktop\\Project E_commerce Script\\screen shot\\Mobile Products are sorted" + scc
				+ ".png");
		FileUtils.copyFile(scrFile, new File(png));
	}

	@BeforeMethod
	public void launchBrowser() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\slyam\\Desktop\\Jars Folder\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("http://live.guru99.com");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void closingBrowser() {
		// driver.close();
		driver.quit();
	}

}
