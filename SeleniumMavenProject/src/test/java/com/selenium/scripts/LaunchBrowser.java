package com.selenium.scripts;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LaunchBrowser {

	static WebDriver driver;
	
	public void browserMethods() throws InterruptedException, IOException {
		
		String strBrowser = ReadPropertyUtil.getProperty("browserType");
		
		switch (strBrowser) {
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
		driver.get(ReadPropertyUtil.getProperty("url"));
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		System.out.println(driver.getWindowHandle());
		Thread.sleep(3000);
		driver.quit();
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		
		LaunchBrowser lb = new LaunchBrowser();
		lb.browserMethods();

	}
}
