package com.web.pages.Choreography;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.utils.CommonUtilities;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

public class Filters_Page extends AbstractPage {

	public Filters_Page(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[@class='MuiBox-root css-181rq1c']")
	private ExtendedWebElement Filter;

	@FindBy(xpath = "//*[text()='Saved Filters']")
	private ExtendedWebElement saveFilter;

	@FindBy(xpath = "//*[text()='Branch']")
	private ExtendedWebElement Branch;

	@FindBy(xpath = "//*[text()='Leader']")
	private ExtendedWebElement Leader;

	@FindBy(xpath = "//*[text()='Employee']")
	private ExtendedWebElement Employee;

	@FindBy(xpath = "//*[text()='Apply Filters']")
	private ExtendedWebElement ApplyFilter;

	CommonUtilities CU = new CommonUtilities();
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public WeeklyReview_Dashboard filters(HashMap<String, String> args) throws Exception {
		String actualResult = args.get("ActualResult");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {

			if (args.get("Filters_Flag").equalsIgnoreCase("Yes")) {
				js.executeScript("window.scrollBy(0,-500)", "");
				Filter.isPresent();
				CU.takeScreenshot(args, driver, "Income Planning Dashboard Filter Page");
				actualResult +=  " Filter option is present in income planning dashboard|";
				Filter.click();
				CU.takeScreenshot(args, driver, "Income Planning Dashboard Filter Page");
				actualResult +=  " Filter Button is clickable|";
				LOGGER.info("Click on filter btn");
				
				actualResult +=  CU.assertEquals("Saved Filters", saveFilter.getText(), args);
				actualResult +=  CU.assertEquals("Branch", Branch.getText(), args);
				actualResult +=  CU.assertEquals("Leader", Leader.getText(), args);
				actualResult +=  CU.assertEquals("Employee", Employee.getText(), args);
				
				actualResult +=  " this options are visible | ";
				LOGGER.info("Check all filter options");
				if (args.get("FilterType").equalsIgnoreCase("Branch")) {
					Branch.click();
					
					for (int i = 1; i <= 6; i++) {
						if (!args.get("FilterOption" + i + "").equalsIgnoreCase("")) {
							driver.findElement(By.xpath("//*[text()='" + args.get("FilterOption" + i + "") + "']"))
									.click();
						}
						actualResult +=  " Filter option selected from branch "+args.get("FilterOption" + i + "")+" | ";
					}
					CU.takeScreenshot(args, driver, "Income Planning Dashboard Filter Page");
					
				}
				if (args.get("FilterType").equalsIgnoreCase("Leader")) {
					Leader.click();
					for (int i = 1; i <= 6; i++) {
						if (!args.get("FilterOption" + i + "").equalsIgnoreCase("")) {
							driver.findElement(By.xpath("//*[text()='" + args.get("FilterOption" + i + "") + "']"))
									.click();
						}
						actualResult +=  " Filter option selected from branch "+args.get("FilterOption" + i + "")+" | ";
						
					}
					CU.takeScreenshot(args, driver, "Income Planning Dashboard Filter Page");
					
				}
				if (args.get("FilterType").equalsIgnoreCase("Employee")) {
					Employee.click();
					for (int i = 1; i <= 6; i++) {
						if (!args.get("FilterOption" + i + "").equalsIgnoreCase("")) {
							driver.findElement(By.xpath("//*[text()='" + args.get("FilterOption" + i + "") + "']"))
									.click();
						}
						actualResult +=  " Filter option selected from branch "+args.get("FilterOption" + i + "")+" | ";
						
					}
					CU.takeScreenshot(args, driver, "Income Planning Dashboard Filter Page");
					
				}
				
				ApplyFilter.click();
				CU.takeScreenshot(args, driver, "Apply Filter Page");
				actualResult +=  "Apply Filters";
				LOGGER.info("Apply Filters");
			}
			args.put("ActualResult", actualResult);
		} catch (Exception e) {
			e.getStackTrace();
			LOGGER.error(e.getMessage());
			actualResult += "**** "+ e.getMessage() +" ****";
			args.put("ActualResult", actualResult);
			args.put("sOutput", e.toString());
			CU.takeScreenshot(args, driver, "Error Page");

			if (args.get("Test Case Type").equalsIgnoreCase("Negative")) {
				args.put("status", "Pass");
			} else {
				args.put("status", "Fail");
			}
		}
		return new WeeklyReview_Dashboard(driver);

	}
}
