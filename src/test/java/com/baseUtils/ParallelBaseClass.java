package com.baseUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;


public class ParallelBaseClass {
	
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	public static WebDriver getDriver() {
		return driver.get();
	}
	
	@Parameters("browser")
	@BeforeMethod
	public void setUp(String browser) {
		if (browser.equals("chrome")) {
			driver.set(new ChromeDriver());
		}else if (browser.equals("firefox")) {
			driver.set(new FirefoxDriver());
		}
	}
	
	
	public void tearDown() {
		driver.get().quit();
		driver.remove();
	}

}
