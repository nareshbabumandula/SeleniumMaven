package com.selenium.scripts;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DropdownListboxText {

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
	void handleDropdown() throws IOException, InterruptedException {
		driver.get(ReadPropertyUtil.getProperty("url"));
		driver.findElement(By.linkText("Sample Forms")).click();
		WebElement predefinedCountries = driver.findElement(By.id("q9"));

		System.out.println(predefinedCountries.isDisplayed());
		System.out.println(predefinedCountries.isEnabled());
		
		Select countries = new Select(predefinedCountries);
		List<WebElement> options = countries.getOptions();
		System.out.println("No of countries found inside dropdown are : " + options.size());
		countries.selectByIndex(1); // Select the first option from the dropdown
		Thread.sleep(3000);
		countries.selectByVisibleText("Georgia");
		
		// lambda expression
		options.forEach(country->System.out.println(country.getText()));
		System.out.println("For each loop started..!");
		// Enhanced for loop
		for (WebElement country : options) {
			System.out.println(country.getText());
		}
		
		Thread.sleep(3000);
		test.log(LogStatus.PASS, "Successfully performed actions on a dropdown..!");
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
