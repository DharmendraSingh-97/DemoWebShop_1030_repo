package com.tricentis.objectrepository;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	@FindBy(linkText = "Log out")
	private WebElement logoutLink;
	
	@FindBy(partialLinkText = "BOOKS")
	private WebElement booksLink;
	
	@FindBy(xpath = "//input[@value='Add to cart']")
	private List<WebElement> addToCartButtons;
	
	@FindBy(xpath = "//p[contains(text(),'The product has been added')]")
	private WebElement productAddedMsg;
	
	public WebElement getProductAddedMsg() {
		return productAddedMsg;
	}

	public WebElement getBooksLink() {
		return booksLink;
	}

	public List<WebElement> getAddToCartButtons() {
		return addToCartButtons;
	}

	public WebElement getLogoutLink() {
		return logoutLink;
	}	
	
}
