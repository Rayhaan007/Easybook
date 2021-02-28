package com.easybus.pages;

import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.easybus.generic.CommonUtility;

public class Homepage {
	public static WebDriver driver;
	CommonUtility wait=new CommonUtility();
	
	@FindBy (id="txtSearchOrigin_Bus")
	private WebElement source;
	
	@FindBy (id="txtSearchDestination_Bus")
	private WebElement destination;
	
	@FindBy (id="dpDepartureDate_Bus")
	private WebElement departureDate;
	
	
	@FindBy (xpath="(//th[@class='next'])[1]")
	private WebElement departureDateNextBtn;
	
	@FindBy (xpath="(//input[@id='TripType'])[1]")
	private WebElement onewayBtn;
	
	
	public WebElement getOnewayBtn() {
		return onewayBtn;
	}

	@FindBy (id="dpReturnDate_Bus")
	private WebElement returnDate;
	//select tag 
	@FindBy (id="ddlPax_Bus")
	private WebElement noOfPax;
	
	@FindBy (xpath="(//button[@type='submit'])[1]")
	private WebElement searchForBusBtn;
	
	public Homepage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}

	public void setSource(String keysToSend ) throws InterruptedException {
	 source.sendKeys(keysToSend);
	 Thread.sleep(1000);
	List<WebElement> listsource = driver.findElements(By.xpath("//div[@class='tt-suggestion-optionval with-icon']/span[1]"));
	Optional<WebElement> source = listsource.stream().filter(option -> option.getText().contains("Cameron Highlands")).findFirst();
	source.get().click();
	}

	public WebElement getDestination() {
		return destination;
	}

	public void setDepartureDate(String Date) {
		String month=Date.split(",")[0].trim();
		int Days=Integer.parseInt(Date.split(",")[1]);
		departureDate.click();
		String focusedMonth=driver.findElement(By.xpath("(//th[@class='datepicker-switch'])[1]")).getText().split(" ")[0].trim();
		boolean flag=true;
		do {
		if(focusedMonth.equals(month)) {
		  driver.findElement(By.xpath("//td[text()='"+Days+"']")).click();
			flag=false;
			}
		else
			departureDateNextBtn.click();
		}while(flag);
	}

	public WebElement getReturnDate() {
		return returnDate;
	}

	public WebElement getNoOfPax() {
		return noOfPax;
	}

	public WebElement getSearchForBusBtn() {
		return searchForBusBtn;
	}
	
	public void selectSeat(int max_seat) {
		List<WebElement> seatSelector=null;
		boolean flag=true;
		do {
		try {
			seatSelector = driver.findElements(By.xpath("//div[@class='seat-cell available']"));
			flag=false;
		}
		catch (Exception e) {
			wait.waitUntilElementIsClickable(driver, seatSelector);
		}
		}while(flag);
		for (int i = 0; i<max_seat && i <= seatSelector.size(); i++) {
			seatSelector.get(i).click();
		}
	}
}
