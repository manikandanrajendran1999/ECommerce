package com.testCases;

import org.testng.annotations.Test;

import com.baseUtils.BaseClass;
import com.pageElements.HomePage;
import com.pageElements.ProductPage;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;

public class TC_1_Choose_Product extends BaseClass {

	@Test (dataProvider = "getObjectData")
	private void chooseProduct(HashMap<String, String> input) throws InterruptedException, IOException {
		System.out.println(input.get("searchKey"));
		System.out.println(input.get("expectedKey"));
		HomePage page = new HomePage(driver);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		driver.get(getConfigData("url"));
		try {
			page.getSkiploginBtn().click();
		} catch (Exception e) {
			System.out.println("element not found");
		}
		page.getSearchBar().click();
		page.getSearchBar().clear();
		Thread.sleep(2000);
		page.getSearchBar().sendKeys(input.get("searchKey"));
		page.getSearchBar().sendKeys(Keys.ENTER);
		List<WebElement> product = page.getProduct();
		for (int i = 0; i < product.size(); i++) {
			List<WebElement> prod = page.getProduct();
			String text = prod.get(i).getText();
			System.out.println(text);
			if (text.toLowerCase().contains(input.get("expectedKey").toLowerCase())) {
				product.get(i).click();
				break;
			}else {
				js.executeScript("window.scrollBy(0.300);");
			}
		}
		String parentWindow = driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();
		for (String handle : windowHandles) {
			if (!handle.equals(parentWindow)) {
				driver.switchTo().window(handle);
				break;
			}
		}
		Thread.sleep(5000);
		ProductPage page1 = new ProductPage(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement addToCartBtn = wait.until(ExpectedConditions.visibilityOf(page1.getAddToCartBtn()));
		
		js.executeScript("arguments[0].scrollIntoView(true)", addToCartBtn);
		wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();
		System.out.println("Total amount : " + page1.getTotalPrice().getText());
		driver.close();
        driver.switchTo().window(parentWindow);
	}
	
	@DataProvider
	private Object[][] getObjectData() throws IOException {
		List<HashMap<String, String>> data = getData("/src/test/resources/TestData.json");
		return new Object [][] {
			{data.get(0)}, {data.get(1)}
		};
	}
	
}
