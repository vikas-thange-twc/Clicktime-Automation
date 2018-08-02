package com.xpanxion.timesheet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.tuple.Pair;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.timesheet.pages.Collabera;

import timesheets.clicktime.TimesheetInfo;
import timesheets.clicktime.pojo.UserInfo;

public class TimeSheetAutomation {

	public static void main(String[] args) throws InterruptedException {
		String fromDate = "20180723";
		String toDate = "20180729";
//		Map<String, Map<String, Pair<Double, Double>>> info = TimesheetInfo
//				.getBillableHours(new UserInfo("vikast@xpanxion.co.in", "welcome1"), "20180723", "20180729");
		Map<String, Map<String, Pair<Double, Double>>> info = TimesheetInfo
				.getBillableHours(new UserInfo("anands@xpanxion.co.in", "3bash19"), fromDate, toDate);

		for (Entry<String, Map<String, Pair<Double, Double>>> i : info.entrySet()) {
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			driver.get("https://apps.collabera.com/ts/index.asp");
			Thread.sleep(10000);
			Collabera col = new Collabera(driver);
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
			String date = LocalDate.parse(toDate, format).format(DateTimeFormatter.ofPattern("MM/d/yyyy"));
			col = col.login().openTimesheetHistory().openTimesheet(date);
			int index = 0;
			for (Entry<String, Pair<Double, Double>> j : i.getValue().entrySet()) {
				col.enterHours(index, j.getValue().getLeft(), j.getValue().getRight());
				index++;
			}
		}
		System.out.println("abc");
	}

}
