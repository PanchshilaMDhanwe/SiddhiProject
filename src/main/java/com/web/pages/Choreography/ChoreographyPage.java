package com.web.pages.Choreography;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.utils.CommonUtilities;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;


public class ChoreographyPage extends AbstractPage {

	public ChoreographyPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//p[text()='Choreography']")
	private ExtendedWebElement Choreography;

	@FindBy(xpath = "//p[text()='Income Planning']//following::*[@id='arrow-down'][1]")
	private ExtendedWebElement IncomePlanning;

	@FindBy(xpath = "(//*[text()='Home'])[1]")
	private ExtendedWebElement Home;

	@FindBy(xpath = "//p[text()='Income Planning Dashboard']")
	private ExtendedWebElement IPDashboard;

	@FindBy(xpath = "//p[text()='Income Planning']")
	private ExtendedWebElement IP_text;

	@FindBy(xpath = "//p[text()='Weekly Review']")
	private ExtendedWebElement WR_text;

	CommonUtilities CU = new CommonUtilities();
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public IncomePlanning_Dashboard ChoreoGraphyPage(HashMap<String, String> args) throws Exception {

		String actualResult = args.get("ActualResult");
		try {
			waitUntil(ExpectedConditions.elementToBeClickable(Home.getElement()), EXPLICIT_TIMEOUT);
			CU.takeScreenshot(args, driver, "Home Page");
			Choreography.scrollTo();
			CU.takeScreenshot(args, driver, "Home Page");
			
			Choreography.click();
			waitUntil(ExpectedConditions.elementToBeClickable(Choreography.getElement()), EXPLICIT_TIMEOUT);
			CU.takeScreenshot(args, driver, "Choreography Page");
			LOGGER.info("click on Choreography module");
			actualResult +=  CU.assertEquals("Choreography", Choreography.getText(), args);
			actualResult +=  CU.assertEquals("Income Planning", IP_text.getText(), args);
			actualResult +=  CU.assertEquals("Weekly Review", WR_text.getText(), args);
			LOGGER.info("check Income planning btn and Weekly reviw btn");
			actualResult =  Choreography.getText() +" ,"+IP_text.getText() +" ,"+ WR_text.getText()+" are present in Choreography page |";
			
			actualResult +=  "user is able to click on choreography module from all modules of the home screen | ";
			actualResult +=  "user is able to see the list of two options Income Planning And Weekly Review | ";
			
			args.put("ActualResult",actualResult);
			
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
		return new IncomePlanning_Dashboard(driver);
	}
	
}
