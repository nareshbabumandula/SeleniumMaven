package com.selenium.scripts;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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

public class LaunchBrowser {
	
	static ExtentTest test;
	static ExtentReports report;
	
	static WebDriver driver;
		
	//@BeforeClass: The annotated method will be run before the first test method in the current class is invoked.
	@BeforeClass
	void launchBrowser() throws IOException {
		
		report = new ExtentReports(System.getProperty("user.dir") + "/Extent Reports/ExtentReportResults.html");
		test = report.startTest("Browser_Methods");
		
		String strBrowser = ReadPropertyUtil.getProperty("browserType");
		
		switch (strBrowser.trim()) {
		case "google": case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		default:
			System.out.println("Invalid browser..!");
			break;
		}
		driver.manage().window().maximize();
		test.log(LogStatus.PASS, "Launched the browser..!");
	}
	
	//Marks a class or a method as part of the test.
	@Test
	void browserMethods() throws IOException, InterruptedException {
		driver.get(ReadPropertyUtil.getProperty("url"));
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		System.out.println(driver.getWindowHandle());
		driver.findElement(By.linkText("Sample Forms")).click();
		Thread.sleep(2000);
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		System.out.println(driver.getWindowHandle());
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
		System.out.println(driver.getPageSource());
		test.log(LogStatus.PASS, "Successfully performed actions on a browser..!");
		
	}
	
	//@AfterClass: The annotated method will be run after all the test methods in the current class have been run.
	@AfterClass
	void closeBrowser() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
		test.log(LogStatus.PASS, "Closed the browser..!");
		report.endTest(test);
		report.flush();

	}

}
