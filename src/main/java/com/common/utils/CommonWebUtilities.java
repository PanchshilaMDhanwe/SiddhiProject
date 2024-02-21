package com.common.utils;

import java.io.IOException;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CommonWebUtilities {

	public static String getMonthNumber(String month) {
		String monthName = "";
		switch (month) {
		case "January":
			monthName = "1";
			break;
		case "February":
			monthName = "2";
			break;
		case "March":
			monthName = "3";
			break;
		case "April":
			monthName = "4";
			break;
		case "May":
			monthName = "5";
			break;
		case "June":
			monthName = "6";
			break;
		case "July":
			monthName = "7";
			break;
		case "August":
			monthName = "8";
			break;
		case "September":
			monthName = "9";
			break;
		case "October":
			monthName = "10";
			break;
		case "November":
			monthName = "11";
			break;
		case "December":
			monthName = "12";
			break;
		default:
			break;
		}
		return monthName;
	}
	

	public static void CalendarDate(WebDriver driver, String sTestData) throws IOException {
		try {

			String StartDate = "", EndDate = "";
			if (sTestData.contains("/")) {
				StartDate = sTestData.split("/")[0].trim();
				EndDate = sTestData.split("/")[1].trim();
			}

			// Year
			driver.findElement(By.xpath("//button[@aria-label='calendar view is open, switch to year view']")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[text()='" + sTestData.split("/")[2].trim() + "']")).click();

			String MonText = driver.findElement(By.xpath("//div[@class='MuiPickersCalendarHeader-label css-1v994a0']"))
					.getText();
			String MonthVal = MonText.split(" ")[0].trim();
			String monthNumber = getMonthNumber(MonthVal);

			int MonthDiff = Integer.parseInt(EndDate) - Integer.parseInt(monthNumber);

			if (MonthDiff != 0) {
				if (MonthDiff > 0) {

					for (int i = 0; i < MonthDiff; i++) {

						System.out.println("Year Diff->" + i);

						driver.findElement(By.xpath("//button[@aria-label='Next month']")).click();

					}

				} else if (MonthDiff < 0) {

					for (int i = 0; i < (MonthDiff * (-1)); i++) {

						System.out.println("Year Diff->" + i);

						driver.findElement(By.xpath("//button[@aria-label='Previous month']")).click();

					}
				}
			}

			// Day
			driver.findElement(By.xpath("//button[text()='" + StartDate + "']")).click();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	

	public static String RandomString_SmallLetters(int n) {

		String AlphaNumericString = "abcdefghijklmnopqrstuvxyz";
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; i++) {
			int index = (int) (AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}
		return sb.toString();
	}

	public static String RandomString_CapitalLetters(int n) {

		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; i++) {
			int index = (int) (AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}
		return sb.toString();
	}

	public static String RandomstringNumber(int length) {

		String chraracters = "0123456789";
		String randomString = "";
		Random rand = new Random();
		char[] text = new char[length];
		for (int i = 0; i < length; i++) {
			text[i] = chraracters.charAt(rand.nextInt(chraracters.length()));
		}
		for (int i = 0; i < text.length; i++) {
			randomString += text[i];
		}
		return randomString.toString();
	}

	
	
}
