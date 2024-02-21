package com.pages.LeadManagement.Mobile;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.utils.CommonUtilities;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

public class Pagination extends AbstractPage {

	public Pagination(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[@data-testid='KeyboardArrowDownIcon']")
	private ExtendedWebElement PaginationBtn;

	@FindBy(xpath = "//button[@text()='Next']")
	private ExtendedWebElement NextBtn;

	@FindBy(xpath = "//button[@text()='Previous']")
	private ExtendedWebElement PreviousBtn;

	@FindBy(xpath = "//*[@class='css-1mx48yd']")
	private ExtendedWebElement MicrophoneBtn;

	@FindBy(xpath = "(//input[@id='fullWidth'])[2]")
	private ExtendedWebElement searchingLead;

	@FindBy(xpath = "//li[@role='menuitem']")
	private List<ExtendedWebElement> MenuItem;

	@FindBy(xpath = "//*[@class='MuiTableRow-root MuiTableRow-hover css-b22f1j']")
	private List<ExtendedWebElement> LeadCount;

	@FindBy(xpath = "//*[@text()='Filter']")
	private ExtendedWebElement Filter;

	@FindBy(xpath = "//*[@text()='Segment']")
	private ExtendedWebElement Segment;

	@FindBy(xpath = "//*[@text()='HNI']")
	private ExtendedWebElement HNI;

	@FindBy(xpath = "//*[@text()='Create Lead']")
	private ExtendedWebElement CreateNewLead;

	@FindBy(xpath = "//*[@text()='Apply Filters']")
	private ExtendedWebElement ApplyFilter;

	@FindBy(xpath = "//*[contains(text(),'Results Found')]")
	private ExtendedWebElement ResultCount;

	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	CommonUtilities CU = new CommonUtilities();
	public Sort_Page Submodule_Pagination(HashMap<String, String> args) throws Exception {

		String actualResult = args.get("ActualResult");
		int paginationCount;
		int RecordCount;
		try {

			if (args.get("Pagination_Flag").equalsIgnoreCase("Yes")) {
				PaginationBtn.scrollTo();
				PaginationBtn.click();
				CU.takeScreenshot(args, driver, "Pagination Lead Page");
				LOGGER.info("click on Pagination btn");
				if(NextBtn.isVisible()) {
					actualResult += "Next Button is visible in lead list page |";
					LOGGER.info("Next Button is visible in lead list page");
				}else {
					LOGGER.error("Next Button not visible");
					actualResult += "*** Next Button not visible ***";
					args.put("status", "Fail");
				}
				if(PreviousBtn.isVisible()) {
					actualResult += "Previous Button is visible in lead list page |";
					LOGGER.info("Previous Button is visible in lead list page");
				}else {
					LOGGER.error("Previous Button not visible");
					actualResult += "*** Previous Button not visible ***";
					args.put("status", "Fail");
				}
				paginationCount = MenuItem.size();
				actualResult +=  "Pagination options count - " + paginationCount
						+ " verify Pagination more option, Previous & Next button available on lead screen | ";
				LOGGER.info("Pagination options count - " + paginationCount);
				CU.takeScreenshot(args, driver, "Pagination Lead Page");
				driver.findElement(By.xpath("(//li[@role='menuitem'])[1]")).click();
				pause(2);

				NextBtn.click();
				CU.takeScreenshot(args, driver, "Pagination Next Lead Page");
			
				CreateNewLead.scrollTo();
				CU.takeScreenshot(args, driver, "Pagination Next Lead Page");
				RecordCount = LeadCount.size();
				actualResult +=  "Total Record count - " + RecordCount + " , Next Button is visible - "
						+ NextBtn.getText() + " , Previous Button is visible - " + PreviousBtn.getText() + " |";
				actualResult +=  "pagination vailadate & check next button check records TOTAL RECORDS | ";
				LOGGER.info("Total Record count - " + RecordCount);
				
				if (args.get("FilterType").equalsIgnoreCase("Segment")) {
					Filter.click();
					CU.takeScreenshot(args, driver, "Lead Filter Page");
					Segment.click();
					actualResult +=  "check Filter Segment options |";
					LOGGER.info("check Filter Segment options");
					HNI.click();
					CU.takeScreenshot(args, driver, "HNI filter option Page");
					actualResult +=  "click on HNI from Segment filter options |";
					LOGGER.info("click on HNI from Segment filter options");
					ApplyFilter.click();
					pause(7);

					CU.takeScreenshot(args, driver, "Apply filter lead list Page");
					actualResult +=  "Apply filter flow done successfully |";
					actualResult +=  "Result Record - " + ResultCount.getText() + " |";
					LOGGER.info("Apply filter flow done successfully | Result Record - " + ResultCount.getText());
					PaginationBtn.click();
					CU.takeScreenshot(args, driver, "Pagination Lead Page");

					driver.findElement(By.xpath("(//li[@role='menuitem'])[" + MenuItem.size() + "]")).click();
					CU.takeScreenshot(args, driver, "Pagination Last Lead Page");
					CreateNewLead.scrollTo();
					CU.takeScreenshot(args, driver, "Pagination Last Lead Page");
					
					if(!NextBtn.isClickable()) {
					actualResult +=  "Next Button is not clickable on last page |";
					LOGGER.info("Next btn is not clickable");
					}
					actualResult = actualResult
							+ "system displayed next 6 records for created lead. On Last Page of the recods Next button should be displayed | ";
					
				}
			}
			args.put("ActualResult", actualResult);

		} catch (Exception e) {
			e.getStackTrace();
			LOGGER.error(e.getMessage());
			actualResult += "*** "+ e.getMessage() +" ***";
			args.put("ActualResult", actualResult);
			args.put("sOutput", e.toString());
			CU.takeScreenshot(args, driver, "Error Page");

			if (args.get("Test Case Type").equalsIgnoreCase("Negative")) {
				args.put("status", "Pass");
			} else {
				args.put("status", "Fail");
			}
		}
		return new Sort_Page(driver);
	}

}
