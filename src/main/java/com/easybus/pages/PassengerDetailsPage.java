package com.easybus.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.easybus.generic.CommonUtility;

public class PassengerDetailsPage {

	@FindBy(id="TicketCollector_Salutation")
	private WebElement title;
	
	@FindBy(id="TicketCollector_Name")
	private WebElement name;
	
	@FindBy(id="TicketCollector_Email")
	private WebElement email;
	
	@FindBy(id="TicketCollector_RepeatedEmail")
	private WebElement repeatEmail;
	
	
	@FindBy(xpath="(//div[@class='selected-flag'])[1]")
	private WebElement dialcode;
	
	@FindBy(name="TicketCollector.Contact")
	private WebElement contact;
	
	@FindBy(id="TicketCollector_Nationality")
	private WebElement nationality;
	
	@FindBy(id="DepartAdultTotal")
	private WebElement totalAdult;
	
	@FindBy(id="cbInsuranceLonpacNo")
	private WebElement protectionPlanRiskBtn;
	
	@FindBy(id="payNowBtn")
	private WebElement payBtn;
	
	public PassengerDetailsPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
		
	}
	
	public void passengerDetails(String title,String name,String email,long contact_no,String nationality) {
		this.name.sendKeys(name);
		this.email.sendKeys(email);
		this.repeatEmail.sendKeys(email);
		String contact=String.valueOf(contact_no);
		this.contact.sendKeys(contact);
		CommonUtility c=new CommonUtility();
		c.select(title, this.title);
		c.select(nationality, this.nationality);
		protectionPlanRiskBtn.click();
		
	}
	public WebElement getpayBtn() {
		return payBtn;
	}
	
}
