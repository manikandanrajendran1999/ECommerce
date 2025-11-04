package com.pageElements;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div/span[@role='button']")
	private WebElement skiploginBtn;
	
	@FindBy(xpath = "//input[@name='q']")
	private WebElement searchBar;
	
	@FindBy (xpath = "//div[@class='KzDlHZ']")
	private List<WebElement> product;
	
	@FindBy (xpath = "//button[text()='Add to cart']")
	private WebElement addToCartBtn;
	
	@FindBy (xpath = "//span[text()='Price details']/following-sibling::div//child::div[4]/child::div")
	private WebElement totalPrice;

	public WebElement getSkiploginBtn() {
		return skiploginBtn;
		
	}

	public WebElement getSearchBar() {
		return searchBar;
	}

	public List<WebElement> getProduct() {
		return product;
	}

	public WebElement getAddToCartBtn() {
		return addToCartBtn;
	}

	public WebElement getTotalPrice() {
		return totalPrice;
	}
	
}
