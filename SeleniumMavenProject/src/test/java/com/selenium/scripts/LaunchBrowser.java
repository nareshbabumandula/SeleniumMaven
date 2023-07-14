package com.selenium.scripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LaunchBrowser {

	static WebDriver driver;

	// Pre-requisites
	// We need to have the respective browser driver.exe file
	// We need to download and integrate Selenium WebDriver jar file
	public static void main(String[] args) throws InterruptedException {
		
		//System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.mycontactform.com/");
		Thread.sleep(3000);
		driver.quit();

	}
}
