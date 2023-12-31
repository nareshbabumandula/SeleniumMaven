package com.selenium.scripts;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TextboxTest {

	static ExtentTest test;
	static ExtentReports report;

	static WebDriver driver;

	@BeforeClass
	void launchBrowser() throws IOException {

		report = new ExtentReports(System.getProperty("user.dir") + "/Extent Reports/ExtentReportResults.html");
		test = report.startTest("Textbox Methods");
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		test.log(LogStatus.PASS, "Launched the browser..!");
	}

	@Test
	void handleTextbox() throws IOException, InterruptedException {
		driver.get(ReadPropertyUtil.getProperty("url"));
		WebElement username = driver.findElement(By.id("user"));
		username.sendKeys("naresh"); // Enter data in textbox
		//<input name="user" type="text" class="txt_log" id="user" value="" tabindex="1">
		System.out.println(username.getAttribute("name"));
		System.out.println(username.getAttribute("type"));
		System.out.println(username.getAttribute("class"));
		System.out.println(username.getAttribute("id"));
		System.out.println(username.getAttribute("value"));
		System.out.println(username.getAttribute("tabindex"));
		System.out.println(username.getTagName());
		username.clear(); // Clear text from the textbox
		
		
		//driver.findElement(By.cssSelector("input[name*='user']")).sendKeys("vignath");
		Thread.sleep(3000);
		test.log(LogStatus.PASS, "Successfully performed actions on a textbox..!");
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
