package com.baseUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.swing.Popup;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;

	@BeforeClass
	public void launchBrowser() throws IOException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
//		driver.get(getConfigData("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterClass
	private void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	public String getConfigData(String key) throws IOException {
		String propValue = "";
		Properties prop = new Properties();
		File f = new File(System.getProperty("user.dir") + "/src/test/resources/config.properties");
		FileInputStream fis = new FileInputStream(f);
		prop.load(fis);
		propValue = prop.getProperty(key);
		return propValue;
	}

	public List<HashMap<String, String>> getData(String filePath) throws IOException {
		String file = FileUtils.readFileToString(new File(System.getProperty("user.dir") + filePath),
				StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> value = mapper.readValue(file,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return value;
	}

	public String getScreenShot(WebDriver driver, String fileName) throws IOException {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String targetFile = System.getProperty("user.dir") + "//reports//" + fileName + ".png";
		FileUtils.copyFile(srcFile, new File(targetFile));
		return targetFile;
	}
	
}
