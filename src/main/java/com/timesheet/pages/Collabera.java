package com.timesheet.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Collabera {
	static WebDriver driver;
	public Collabera(WebDriver driver) {
		Collabera.driver = driver;
	}
	
	private static final String USER_NAME = "//input[@name='Collabera_ID']";
	private static final String PASSWORD = "//input[@name='pwd']";
	private static final String BTN_LOGIN = "//button[contains(.,'Login')]";
	private static final String LINK_HISTORY = "//a[contains(.,'History')]";
	//private static final String USER_NAME = "//input[@name='Collabera_ID']";
	private static final String TIMESHEET_LINK = "//a[contains(.,'%s')]";
	private static final String TIMESHEET_INPUT_FIELDS = "//input[contains(@name,'ST_')]";
	private static final String TIMESHEET_TIME_OFF_sINPUT_FIELDS = "//input[contains(@name,'to_')]";
	private static final String TIMESHEET_DATE_PICKER = "//input[@name='week_end']";
	
	
	public Collabera login() {
		Collabera.driver.findElement(By.xpath(USER_NAME)).click();
//		Collabera.driver.findElement(By.xpath(USER_NAME)).sendKeys("94197");
//		//Collabera.driver.findElement(By.xpath(PASSWORD)).click();
//		Collabera.driver.findElement(By.xpath(PASSWORD)).sendKeys("1234");
		Collabera.driver.findElement(By.xpath(USER_NAME)).sendKeys("94253");
		Collabera.driver.findElement(By.xpath(PASSWORD)).sendKeys("0011");
		Collabera.driver.findElement(By.xpath(BTN_LOGIN)).click();
		this.waitForElement(By.xpath(LINK_HISTORY));
		return this;
	}
	
	public Collabera openTimesheetHistory() {
		Collabera.driver.findElement(By.xpath(LINK_HISTORY)).click();
		this.wait(2);
		return this;
	}
	
	public Collabera openTimesheet(String date) {
		Collabera.driver.findElement(By.xpath(String.format(TIMESHEET_LINK, date))).click();
		this.waitForElement(By.xpath(TIMESHEET_DATE_PICKER));
		return this;
	}
	
	public Collabera enterHours(int dayFieldIndex, double workingHours, double offTimeHours) {
		List<WebElement> workingTimeFields = Collabera.driver.findElements(By.xpath(TIMESHEET_INPUT_FIELDS));
		List<WebElement> offTimeFields = Collabera.driver.findElements(By.xpath(TIMESHEET_TIME_OFF_sINPUT_FIELDS));
		
		workingTimeFields.get(dayFieldIndex).sendKeys(String.valueOf(workingHours));
		if(offTimeHours > 0.0) {
			offTimeFields.get(dayFieldIndex).sendKeys(String.valueOf(offTimeHours));
		}
		this.waitForElement(By.xpath(TIMESHEET_DATE_PICKER));
		return this;
	}
	
	public void waitForElement(By element) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(element));
	}
	
	public void wait(int seconds) {
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			System.out.println("Waiting for ["+ seconds*1000 +"] seconds");
		}
	}
}
