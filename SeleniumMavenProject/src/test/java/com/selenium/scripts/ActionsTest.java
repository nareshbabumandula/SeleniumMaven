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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ActionsTest {

	static ExtentTest test;
	static ExtentReports report;

	static WebDriver driver;

	@BeforeClass
	void launchBrowser() throws IOException {

		report = new ExtentReports(System.getProperty("user.dir") + "/Extent Reports/ExtentReportResults.html");
		test = report.startTest("Actions Methods");
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		test.log(LogStatus.PASS, "Launched the browser..!");
	}

	@Test
	void actionsmethods() throws IOException, InterruptedException {
		driver.get("https://jqueryui.com/");
		
		WebElement menu = driver.findElement(By.linkText("Contribute"));
		
		Actions action = new Actions(driver);
		action.moveToElement(menu).perform();
		//action.click(menu).perform(); // Newly added
		Thread.sleep(3000);
		action.clickAndHold(driver.findElement(By.linkText("Support"))).perform();
		Thread.sleep(3000);
		action.contextClick(driver.findElement(By.linkText("Plugins"))).perform();
		Thread.sleep(3000);
		
		// dragAndDropBy
		driver.findElement(By.linkText("Draggable")).click();
		driver.switchTo().frame(0);
		WebElement drag = driver.findElement(By.id("draggable"));
		action.dragAndDropBy(drag, 315, 165).perform();
		Thread.sleep(3000);
		
		// dragAndDrop
		driver.switchTo().defaultContent();
		driver.findElement(By.linkText("Droppable")).click();
		driver.switchTo().frame(0);
		
		WebElement source = driver.findElement(By.id("draggable"));
		WebElement destination = driver.findElement(By.id("droppable"));
		action.dragAndDrop(source, destination).perform();
		Thread.sleep(3000);
		
		
		test.log(LogStatus.PASS, "Successfully performed actions using methods of actions class..!");
		
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
