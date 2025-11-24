package com.testCases;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.baseUtils.BaseClass;

public class WindowsHandling extends BaseClass {
	
	@Test
	private void brokenLinks() throws InterruptedException {
		
		int brokenCount = 0;
		
		driver.get("https://www.amazon.in/");
//		driver.findElement(By.cssSelector("input[placeholder='Enter Email Id']")).sendKeys("digiproductsadmin@digi.com");
//		driver.findElement(By.cssSelector("input[placeholder='Enter Password']")).sendKeys("12345678");
//		Thread.sleep(5000);
//		driver.findElement(By.cssSelector("button[type='submit']")).click();
//		Thread.sleep(5000);
//		driver.findElement(By.cssSelector("div img[alt='DigiClass - Admin']"));
		Thread.sleep(5000);
		List<WebElement> links = driver.findElements(By.tagName("a"));
		for(WebElement link : links) {
			@Nullable
			String url = link.getAttribute("href");
//			System.out.println(url);
			if (!verifyLink(url)) {
				 brokenCount++;
			}
		}
		System.out.println("Total Broken Links = " + brokenCount);
		driver.quit();
	}
	private static boolean verifyLink(String url) {
		try {
			URL link = new URL(url);
			HttpURLConnection connection = (HttpURLConnection)link.openConnection();
			connection.setConnectTimeout(3000);
			connection.connect();
			if (connection.getResponseCode() == 200) {
				System.out.println(url + " - " + connection.getResponseMessage());
				return true;
			}else {
				System.out.println(url + " - " + connection.getResponseMessage());
				return true;
			}
		} catch (Exception e) {
			System.out.println(url + " - " + "is broken link");
			return false;
		}
	}

	@Test
	private void windowsHandlingUsingId() {
		driver.get("https://letcode.in/window");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("home")).click();
		String parentWindow = driver.getWindowHandle();
		Set<String> allWind = driver.getWindowHandles();
		List<String> windList = new ArrayList<String>(allWind);
		System.out.println(driver.getCurrentUrl());
		driver.switchTo().window(windList.get(1));
		System.out.println(driver.getCurrentUrl());
		driver.switchTo().window(windList.get(0));
		driver.close();
		Set<String> allWind2 = driver.getWindowHandles();
		windList.clear();
		windList.addAll(allWind2);
		driver.switchTo().window(windList.get(0));
		System.out.println(driver.getCurrentUrl());
	}

	@Test
	private void windowsHandlingComparingId() {
		driver.get("https://letcode.in/window");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("home")).click();
		String parentWindow = driver.getWindowHandle();
		Set<String> allWind = driver.getWindowHandles();
		for (String win : allWind) {
			if (!win.equals(parentWindow)) {
				driver.switchTo().window(win);
				System.out.println(driver.getCurrentUrl());
				break;
			}
		}
		driver.close();
//		WebElement windowTab = driver.findElement(By.xpath("//p[normalize-space()='Window']"));
//		JavascriptExecutor js = (JavascriptExecutor)driver;
//		js.executeScript("arguments[0].scrollIntoView(true);", windowTab);
//		driver.findElement(By.xpath("//a[normalize-space()='Tabs']")).click();
		driver.switchTo().window(parentWindow);
//		driver.findElement(By.id("home")).click();
		System.out.println(driver.getCurrentUrl());
//		js.executeScript("window.scrollBy()");
	}

	@Test
	private void webTable() {
		driver.get("https://letcode.in/advancedtable");

		WebElement table = driver.findElement(By.id("advancedtable"));

//		List<WebElement> elements = table.findElements(By.tagName("th"));

//		for (WebElement header : elements) {
//			System.out.println(header.getText());
//		}

//		List<WebElement> allRows = table.findElements(By.cssSelector("tbody tr"));
//		System.out.println(allRows.size());
//
//		for (WebElement column : allRows) {
//			List<WebElement> elements2 = column.findElements(By.tagName("td"));
//			WebElement webElement = elements2.get(1);
//			System.out.println(webElement.getText());
//		}
		List<WebElement> elements = driver.findElements(By.xpath("//table[@id='advancedtable']//tbody/tr"));
		for (WebElement webElement : elements) {
			List<WebElement> elements2 = webElement.findElements(By.xpath("td"));
			WebElement webElement2 = elements2.get(1);
			System.out.println(webElement2.getText());
		}
		driver.close();
	}

}
