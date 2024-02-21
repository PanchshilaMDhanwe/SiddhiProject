package com.web.pages.LeadManagement;

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

public class LeadPage extends AbstractPage {

	public LeadPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[contains(text(),'Leads')]")
	private ExtendedWebElement Leads;

	@FindBy(xpath = "//*[contains(text(),'Create Lead')]")
	private ExtendedWebElement CreateNewLead;

	@FindBy(xpath = "(//*[text()='Home'])[1]")
	private ExtendedWebElement Home;

	@FindBy(xpath = "(//button[@aria-label='filterIcon'])[1]")
	private ExtendedWebElement MobileIcon;

	@FindBy(xpath = "//p[text()='Leads']")
	private ExtendedWebElement LeadsText;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	CommonUtilities CU = new CommonUtilities();
	public Pagination LeadsPage(HashMap<String, String> args) throws Exception {
		
		String actualResult = args.get("ActualResult");
		try {
			waitUntil(ExpectedConditions.elementToBeClickable(Home.getElement()), EXPLICIT_TIMEOUT);
			
			CU.takeScreenshot(args, driver, "Home Page");
			Leads.scrollTo();
			CU.takeScreenshot(args, driver, "Home Page");
			Leads.click();
			waitUntil(ExpectedConditions.elementToBeClickable(CreateNewLead.getElement()), EXPLICIT_TIMEOUT);
			CU.takeScreenshot(args, driver, "Lead List Page");
			CreateNewLead.scrollTo();
			CU.takeScreenshot(args, driver, "Lead List Page");
			LOGGER.info("Showing List of Leads");
			actualResult +="Showing List of Leads |";
			
			if(!MobileIcon.isVisible()) {
				LOGGER.error("Mobile icon not visible");
				actualResult +="***Mobile icon not visible***";
				args.put("status", "Fail");
			}else {
				LOGGER.info("Mobile Icon is present in lead list page");
				actualResult +="Mobile Icon is present in lead list page |";
			}
			
			actualResult += CU.assertEquals("Create Lead", CreateNewLead.getText(), args);
			actualResult += CU.assertEquals("Leads", LeadsText.getText(), args);
			actualResult +=" this are present in lead list page |";
			LOGGER.info("Create Lead Btn and Leads visible in list page");
			
			args.put("ActualResult",actualResult);
			
		} catch (Exception e) {
			e.getStackTrace();
			LOGGER.error(e.getMessage());
			actualResult +="*** "+ e.getMessage() +" ***";
			args.put("ActualResult", actualResult);
			args.put("sOutput", e.toString());
			CU.takeScreenshot(args, driver, "Error Page");

			if (args.get("Test Case Type").equalsIgnoreCase("Negative")) {
				args.put("status", "Pass");
			} else {
				args.put("status", "Fail");
			}
		}
		return new Pagination(driver);
	}
}
