package com.easybus.generic;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static Logger log=LogManager.getLogger();
	
	
	public static WebDriver driver;
	
	
	@BeforeTest
	public void openBrowser() {
		String browser="chrome";
		WebDriverManager.chromedriver().setup();
		WebDriverManager.firefoxdriver().setup();
		WebDriverManager.edgedriver().setup();
		WebDriverManager.iedriver().setup();
		log.info("WebDriverManger successfully set browser path ");
		if (browser.equals("chrome"))
			driver=new ChromeDriver();
		else if(browser.equals("firefox"))
			driver=new FirefoxDriver();
		else if(browser.equals("IE"))
			driver=new InternetExplorerDriver();
		else if(browser.equals("Edge"))
			driver=new EdgeDriver();
		log.debug("driver successfully initialized with browser");
		driver.get("https://www.easybook.com/bus");
		log.info("Url successfully hit");
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		log.info("Browser successfully maximized");
	}
	@AfterTest
	public void closeBrowser() {
		log.debug("Browser successfully closed");
		driver.close();
	}

}
