package com.testCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.baseUtils.BaseClass;

public class FindBrokenLinks extends BaseClass {
	int brokenCount = 0;
	@Test
	private void brokenLinks1() throws InterruptedException {
		
		driver.get("https://www.amazon.in/");
		List<WebElement> links = driver.findElements(By.tagName("a"));
		for (WebElement link : links) {
			@Nullable
			String url = link.getAttribute("href");
			if (!verify(url)) {
				brokenCount++;
			}
		}
		System.out.println(brokenCount);
		driver.quit();
	}
	
	private static boolean verify(String url) {
		try {
			URL link = new URL(url);
			HttpURLConnection connection = (HttpURLConnection)link.openConnection();
			connection.setConnectTimeout(3000);
			connection.connect();
			
			int responseCode = connection.getResponseCode();
			if (responseCode >= 400) {
				System.out.println(url + " - " + connection.getResponseMessage());
				return false;
			}else {
				return true;
			}
			
		} catch (Exception e) {
			System.out.println(url + " - " + "Broken API");
			return false;
		}
	}
	
	@Test
	public void brokenLinks() throws Exception {

	    driver.get("https://www.amazon.in/");
	    Thread.sleep(5000);

	    List<WebElement> links = driver.findElements(By.tagName("a"));

	    for (WebElement element : links) {
	        String url = element.getAttribute("href");

	        if (url == null || url.isEmpty()) {
	            continue; // skip empty URLs
	        }

	        if (url.startsWith("javascript:") || url.startsWith("mailto:")) {
	            continue; // skip JS and mail links
	        }

	        if (!url.startsWith("http")) {
	            continue; // skip unsupported links
	        }

	        if (!verifyLink(url)) {
	            brokenCount++;
	        }
	    }

	    System.out.println("Total Broken Links = " + brokenCount);
	    driver.quit();
	}


	private static boolean verifyLink(String url) {
	    try {
	        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
	        connection.setConnectTimeout(3000);
	        connection.connect();

	        int code = connection.getResponseCode();

	        if (code >= 400) {
	            System.out.println(url + " ---> " + code + " BROKEN");
	            return false;
	        } else {
	            System.out.println(url + " ---> " + code + " OK");
	            return true;
	        }

	    } catch (Exception e) {
	        System.out.println(url + " ---> ERROR (Broken)");
	        return false;
	    }
	}
	
	@Test
	private void readExcelData() throws IOException {
		File f = new File(System.getProperty("user.dir") + "/src/test/resources/TestData.xlsx");
		FileInputStream fis = new FileInputStream(f);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
//		int lastRow = sheet.getLastRowNum();
//		for (int i = 0; i <= lastRow; i++) {
			sheet.getRow(0).createCell(2).setCellValue("Name");
			sheet.getRow(1).createCell(2).setCellValue("Mani");
			sheet.getRow(2).createCell(2).setCellValue("Kandan");
			
			System.out.println(sheet.getRow(0).getCell(2));
			System.out.println(sheet.getRow(1).getCell(2));
			System.out.println(sheet.getRow(2).getCell(2));
			
//		}
		FileOutputStream fos = new FileOutputStream(f);
		workbook.write(fos);
		fos.close();
	}
	
	@Test
	private void javap() {
		int[] li = {1,2,3,4,5,6,7};
		String collect = Arrays.stream(li)
		.filter(n -> n%2 != 0)
		.map(n -> n * n)
		.mapToObj(String::valueOf)
		.collect(Collectors.joining(", ", "[", "]"));
		System.out.println(collect);
		driver.quit();
	}
}
