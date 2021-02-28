package com.easybus.scripts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.collections.CollectionUtils;

import com.easybus.generic.BaseClass;
import com.easybus.generic.CommonUtility;
import com.easybus.pages.Homepage;
import com.easybus.pages.PassengerDetailsPage;

public class TicketBookTask extends BaseClass {
	public static Logger log=LogManager.getLogger();
	CommonUtility wait=new CommonUtility();

	@Test
	public void bookTicket() throws InterruptedException {
		Homepage hp = new Homepage(driver);
		hp.getOnewayBtn().click();
		log.info("onway Radio button clicked successfully");
		hp.setSource("cameron");
		log.debug("source is selected");
		driver.findElement(By.xpath("//span[contains(text(),'Kuala Lumpur')]")).click();
		log.debug("Destination is selected");
		hp.setDepartureDate("March,20");		
		log.debug("Requested date is selected");
		hp.getSearchForBusBtn().click();
		log.info("search button clicked successfully");
		Map<Double,Integer> adultPrice =new TreeMap<Double,Integer>();//to sort the price
		List<WebElement> adultPriceListbox = null;
		boolean flag=true;
		do {
		try {
		 adultPriceListbox = driver.findElements(By.cssSelector("[id='dep-trip-tbl'] .result-list "));
		 flag=false;
		 log.debug("list of bus available successfully captured");
		}
		catch (Exception e) {
			wait.waitUntilElementIsClickable(driver, adultPriceListbox);
		}
		}while(flag);
		
		for (int i = 0; i < adultPriceListbox.size(); i++) {
			WebElement e=adultPriceListbox.get(i).findElement(By.cssSelector("[id='dep-trip-tbl'] .result-list [data-toggle='modal'] div:nth-of-type(1)"));
			log.debug("list of adult price successfully captured");
			Double s=Double.parseDouble(e.getText().split(" ")[1].trim());//to fetch  only price.
			
			adultPrice.put(s, i);
		}
		log.info("price successfully sorted");
		adultPriceListbox.get(0).findElement(By.cssSelector("[id='dep-trip-tbl'] .result-list [href='#bus-choose-seat-dialog']")).click();
		log.info("search button clicked successfully");
		hp.selectSeat(6);//selecting max of 6 seats.
		log.debug("seat sucessfully selected");
		driver.findElement(By.id("btnProceedToPassengerDetail")).click();
		log.info("proceded towards passenger detail page");
		PassengerDetailsPage p=new PassengerDetailsPage(driver);
		p.passengerDetails("Mr", "Ali", "ali@gmail.com", 9999009900l, "India");
		p.getpayBtn().click();
		log.info("pay button successfully clicked");
		Alert alertPopUp = driver.switchTo().alert();
		String actualPopUpText=alertPopUp.getText();
		alertPopUp.dismiss();
		String expectedPopUpText="By clicking OK, you agree to our Booking & Ticketing Policies and confirm that your booking and total amount payable are correct.";
		Assert.assertEquals(actualPopUpText, expectedPopUpText);
		log.trace("Success",actualPopUpText);
	}
}

