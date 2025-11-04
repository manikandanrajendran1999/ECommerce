package org.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks extends BaseUtilsTest {
	
	
	public WebDriver getDeriver() throws IOException {
		if (getConfigData("browser").equalsIgnoreCase("chrome")) {
			return driver;
		}else {
			return driver2;
		}
	}
	
	public void launchBrowser() throws IOException {
		String os = System.getProperty("os") !=null ? System.getProperty("os"):getConfigData("os");
		String browserName = System.getProperty("browser") !=null ? System.getProperty("browser"):getConfigData("browser");
		if (os.contains("mac")) {
			if (browserName.equalsIgnoreCase("chrome") && getDeriver() == null) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				driver.get(getConfigData("url"));
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			}
		}
	}
	
	public void tearDown(){
		if (driver !=null) {
			driver.close();
		}
	}
	
	public String getConfigData(String propName) throws IOException {
		String propValue = "";
		Properties prop = new Properties();
		File f = new File(System.getProperty("user.dir")+"/src/test/resources/config.properties");
		FileInputStream fis = new FileInputStream(f);
		prop.load(fis);
		propValue = prop.getProperty(propName);
		return propValue;
	}
	
}
