package com.tricentis.genericutility;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class WebDriverUtility {
	public Actions act;
	public WebDriverUtility(WebDriver driver) {
		act=new Actions(driver);
	}
	
	public void mouseHover(WebElement element) {
		act.moveToElement(element).perform();
	}
	
	public void doubleClick(WebElement element) {
		act.doubleClick(element).perform();
	}
	
	public void switchToWindow(WebDriver driver,String expUrl) {
		Set<String> allWindowIds = driver.getWindowHandles();
		for(String id:allWindowIds) {
			String actUrl = driver.switchTo().window(id).getCurrentUrl();
			if (actUrl.contains(expUrl)) {
				break;
			}
		}
	}
	
	public Alert switchToAlert(WebDriver driver) {
		return driver.switchTo().alert();
	}
	
	public void switchToFrame(WebDriver driver,int index) {
		driver.switchTo().frame(index);
	}
	
	public void switchToFrame(WebDriver driver,String nameOrId) {
		driver.switchTo().frame(nameOrId);
	}
	
	public void switchToFrame(WebDriver driver,WebElement frameElement) {
		driver.switchTo().frame(frameElement);
	}
	
	public void selectByIndex(WebElement dropDownElement,int index) {
		Select dropdownSelect=new Select(dropDownElement);
		dropdownSelect.selectByIndex(index);
	}
	
	public List<WebElement> getAllOptions(WebElement dropdownElement) {
		Select dropdownSelect=new Select(dropdownElement);
		return dropdownSelect.getOptions();
	}
	
	public void jsScroll(WebDriver driver,int x,int y) {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollBy("+x+","+y+")");
	}
	
}
