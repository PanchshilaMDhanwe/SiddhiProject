package com.pages.LeadManagement.Mobile;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.utils.CommonUtilities;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

public class Sort_Page extends AbstractPage {

	public Sort_Page(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[@text()='Sort']")
	private ExtendedWebElement Sort_btn;

	@FindBy(xpath = "//*[@text()='Fresh Lead']")
	private ExtendedWebElement FreshLead;

	@FindBy(xpath = "//*[@text()='Last Modified']")
	private ExtendedWebElement Last_Modified;

	@FindBy(xpath = "//*[@text()='A to Z']")
	private ExtendedWebElement A_to_Z;

	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	CommonUtilities CU = new CommonUtilities();
	public Filter_Page sort_Page(HashMap<String, String> args) throws Exception {

		String actualResult = args.get("ActualResult");
		try {

			if (args.get("Sort_Flag").contains("Yes")) {

				Sort_btn.click();
				CU.takeScreenshot(args, driver, "Lead sort Page");
				actualResult +=  CU.assertEquals("Sort", Sort_btn.getText(), args);
				actualResult +=  CU.assertEquals("Fresh Lead", FreshLead.getText(), args);
				actualResult +=  CU.assertEquals("Last Modified", Last_Modified.getText(), args);
				actualResult +=  CU.assertEquals("A to Z", A_to_Z.getText(), args);
				
				actualResult +=  "Displayed all sort option under sorting | ";
				LOGGER.info("All sort Options available");
				
				if (args.get("SortType").equalsIgnoreCase("FreshLead")) {
					FreshLead.click();
					CU.takeScreenshot(args, driver, "Lead sort fresh lead option Page");
					actualResult +=  "able to click on Sort fresh lead Options.|";
					LOGGER.info("able to click on Sort fresh lead Options");
					
				} else if (args.get("SortType").equalsIgnoreCase("Last_Modified")) {
					Last_Modified.click();
					CU.takeScreenshot(args, driver, "Lead sort Last Modified option Page");
					actualResult +=  "able to click on Last Modified lead Options.|";
					LOGGER.info("able to click on Last Modified lead Options");
				} else if (args.get("SortType").equalsIgnoreCase("A_to_Z")) {
					A_to_Z.click();
					CU.takeScreenshot(args, driver, "Lead sort A_to_Z option Page");
					actualResult +=  "able to click on A_to_Z lead Options.|";
					LOGGER.info("able to click on A_to_Z lead Options");
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
		return new Filter_Page(driver);
	}
}
