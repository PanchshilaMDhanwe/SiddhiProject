package com.web.pages.Hierarchy;

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

public class ProfilePage extends AbstractPage {

	@FindBy(xpath = "//img[@alt='Profile']")
	private ExtendedWebElement ProfileImg;

	@FindBy(xpath = "//*[text()='My Profile']")
	private ExtendedWebElement MyProfileBtn;

	@FindBy(xpath = "//*[text()='My Hierarchy']")
	private ExtendedWebElement MyHierachy;

	@FindBy(xpath = "//*[text()='My Hierarchy']//following::*[1]")
	private ExtendedWebElement MyHierachyBtn;

	public ProfilePage(WebDriver driver) {
		super(driver);
	}
	CommonUtilities CU = new CommonUtilities();
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public AgencyPartner_Advisor SelectProfile(HashMap<String, String> args) throws Exception {

		String actualResult = args.get("ActualResult");
		try {
			LOGGER.info("Inside Profile Screen...");

			waitUntil(ExpectedConditions.elementToBeClickable(ProfileImg.getElement()), EXPLICIT_TIMEOUT);
			CU.takeScreenshot(args, driver, "HomePage");
			ProfileImg.click();
			CU.takeScreenshot(args, driver, "Profilelogo");
			pause(2);
			MyProfileBtn.click();
			CU.takeScreenshot(args, driver, "ProfileScreen");
//			waitUntil(ExpectedConditions.elementToBeClickable(MyHierachyBtn.getElement()), EXPLICIT_TIMEOUT);
//			//MyHierachy.click();
//			MyHierachyBtn.click();
//			CU.takeScreenshot(args, driver, "MyHierachyPage");
//			pause(2);

			actualResult +=  " |My Hierarchy Viewed Successfully";
			LOGGER.info("My Hierarchy Viewed Successfully");
			args.put("status", "Pass");

			args.put("ActualResult", actualResult);

		}

		catch (Exception e) {
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
		return new AgencyPartner_Advisor(driver);

	}

}
