package com.easybus.generic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class CommonUtility {
	
	
	public void select(int index,WebElement e)
	{
		Select s=new Select(e);
		s.selectByIndex(index);
	}
	public void select(String visibleText ,WebElement e)
	{
		Select s=new Select(e);
		s.selectByVisibleText(visibleText);
	}
	
	public void waitUntilElementIsClickable(WebDriver driver,List<WebElement> elements) {
	WebDriverWait wait=new WebDriverWait(driver,10);
	wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}
}
