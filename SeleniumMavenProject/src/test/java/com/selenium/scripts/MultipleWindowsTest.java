package com.selenium.scripts;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MultipleWindowsTest {

	static ExtentTest test;
	static ExtentReports report;

	static WebDriver driver;

	@BeforeClass
	void launchBrowser() throws IOException {

		report = new ExtentReports(System.getProperty("user.dir") + "/Extent Reports/ExtentReportResults.html");
		test = report.startTest("Multiple Windows Handling");
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		test.log(LogStatus.PASS, "Launched the browser..!");
	}

	@Test
	void handleMultipleWindows() throws IOException, InterruptedException {
		driver.get(ReadPropertyUtil.getProperty("url"));
				
		// Parent Window Session ID
		String mainWindow = driver.getWindowHandle();
		System.out.println(mainWindow);
		System.out.println(driver.getWindowHandles());
		String n = Keys.chord(Keys.CONTROL,Keys.ENTER);
		driver.findElement(By.linkText("Sample Forms")).sendKeys(n);
		System.out.println(driver.getWindowHandles());
		//driver.switchTo().newWindow(WindowType.TAB);
		test.log(LogStatus.PASS, "Successfully performed actions on a browser..!");

	}


	@AfterClass
	void closeBrowser() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
		test.log(LogStatus.PASS, "Closed the browser..!");
		report.endTest(test);
		report.flush();

	}

}
