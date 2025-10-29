package org.pageElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {

	WebDriver driver;
	
	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//button[text()='Add to cart']")
	private WebElement addToCartBtn;
	
//	@FindBy (xpath = "//span[text()='Price details']/following-sibling::div//child::div[4]/child::div")
	@FindBy (xpath = "//div[normalize-space(text())='Total Amount']/following::span[1]")
	private WebElement totalPrice;

	public WebElement getAddToCartBtn() {
		return addToCartBtn;
	}

	public WebElement getTotalPrice() {
		return totalPrice;
	}



}
