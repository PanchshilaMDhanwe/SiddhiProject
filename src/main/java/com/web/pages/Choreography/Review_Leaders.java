package com.web.pages.Choreography;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;

import com.common.utils.CommonUtilities;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

public class Review_Leaders extends AbstractPage {

	public Review_Leaders(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[text()='NLA Creation']")
	private ExtendedWebElement NLA_Creation_text;

	@FindBy(xpath = "//*[text()='Active NLA']")
	private ExtendedWebElement ActiveNLA_text;

	@FindBy(xpath = "//*[text()='LP Creation']")
	private ExtendedWebElement LP_Creation_text;

	@FindBy(xpath = "//*[text()='Active LP']")
	private ExtendedWebElement ActiveLP_text;

	@FindBy(xpath = "//*[text()='Other Advisor Creation']")
	private ExtendedWebElement OtherAdv_Creation_text;

	@FindBy(xpath = "//*[text()='Expected Income (₹)']")
	private ExtendedWebElement ExpectedIncome_text;

	@FindBy(xpath = "//*[text()='WNBP (₹)']")
	private ExtendedWebElement WNBP_text;

	@FindBy(xpath = "//a[text()='Review']")
	private ExtendedWebElement Review;

	@FindBy(xpath = "//p[text()='Income Planning']")
	private ExtendedWebElement IP_text;

	@FindBy(xpath = "//*[text()='Income Planning (Open)']")
	private ExtendedWebElement Review_IP_Text;

	@FindBy(xpath = "//*[text()='NLA Creation']//following::input[1]")
	private ExtendedWebElement NLACreation_input;

	@FindBy(xpath = "//*[text()='Active NLA']//following::input[1]")
	private ExtendedWebElement ActiveNLA_input;

	@FindBy(xpath = "//*[text()='LP Creation']//following::input[1]")
	private ExtendedWebElement LPCreation_input;

	@FindBy(xpath = "//*[text()='Active LP']//following::input[1]")
	private ExtendedWebElement ActiveLP_input;

	@FindBy(xpath = "(//*[text()='Other Advisor Creation']//following::input[1])[1]")
	private ExtendedWebElement OtherAdvisor_input;

	@FindBy(xpath = "(//*[text()='Other Advisor Creation']//following::input[1])[2]")
	private ExtendedWebElement WNBP_input;

	@FindBy(xpath = "//*[text()='Expected Income (₹)']//following::input[1]")
	private ExtendedWebElement ExpectedIncome_input;

	@FindBy(xpath = "//*[text()='Create Income Planning']")
	private ExtendedWebElement Create_IP;

	@FindBy(xpath = "//*[text()='Only numbers are allowed']")
	private ExtendedWebElement Input_Error;

	@FindBy(xpath = "//p[@class='MuiTypography-root MuiTypography-body1 css-17exevb']")
	private ExtendedWebElement Date;

	CommonUtilities CU = new CommonUtilities();
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public ChoreographyPage Review(HashMap<String, String> args) throws Exception {

		String actualResult = args.get("ActualResult");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		SoftAssert asert = new SoftAssert();
		try {

			if (args.get("Review_Flag").equalsIgnoreCase("Yes")) {

				js.executeScript("window.scrollBy(0,-700)", "");
				Review.click();
				driver.navigate().refresh();
				LOGGER.info("click on Review");
				waitUntil(ExpectedConditions.elementToBeClickable(IP_text.getElement()), EXPLICIT_TIMEOUT);

				actualResult = actualResult
						+ CU.assertEquals("Income Planning (Open)", Review_IP_Text.getText(), args);

				CU.takeScreenshot(args, driver, "Review Income Planning Page");
				js.executeScript("window.scrollBy(0,300)", "");
				CU.takeScreenshot(args, driver, "Review Income Planning Page");
				js.executeScript("window.scrollBy(0,500)", "");
				CU.takeScreenshot(args, driver, "Review Income Planning Page");

				actualResult = actualResult
						+ "list of all such leaders for whom the Income Planning is not done should be populated on screen |";

				if (!args.get("SearchLeader").equalsIgnoreCase("")) {
					LeaderPage Leader_page = new LeaderPage(getDriver());
					Leader_page.SearchLeader(args);
					actualResult = args.get("ActualResult");
					Leader_page.LeaderInfo(args);
					actualResult = args.get("ActualResult");
				}
				if (!args.get("SearchLeader").equalsIgnoreCase("")) {

					js.executeScript("window.scrollBy(0,600)", "");
					CU.takeScreenshot(args, driver, "Create Income Planning Page");

					if (!NLA_Creation_text.isVisible()) {
						actualResult += "*** NLA creation not visible ***";
						LOGGER.info("*** NLA creation not visible ***");
						args.put("status", "Fail");
					} else {
						actualResult += NLA_Creation_text.getText() + " is visible";
						LOGGER.info(NLA_Creation_text.getText() + " is visible");

					}
					if (!ActiveNLA_text.isVisible()) {
						actualResult += "*** Active NLA not visible ***";
						LOGGER.info("*** Active NLA not visible ***");
						args.put("status", "Fail");
					} else {
						actualResult += ActiveNLA_text.getText() + " is visible";
						LOGGER.info(ActiveNLA_text.getText() + " is visible");

					}
					if (!LP_Creation_text.isVisible()) {
						actualResult += "*** LP creation not visible ***";
						LOGGER.info("*** LP creation not visible ***");
						args.put("status", "Fail");
					} else {
						actualResult += LP_Creation_text.getText() + " is visible";
						LOGGER.info(LP_Creation_text.getText() + " is visible");

					}
					if (!ActiveLP_text.isVisible()) {
						actualResult += "*** Active LP not visible ***";
						LOGGER.info("*** Active LP not visible ***");
						args.put("status", "Fail");
					} else {
						actualResult += ActiveLP_text.getText() + " is visible";
						LOGGER.info(ActiveLP_text.getText() + " is visible");

					}
					if (!OtherAdv_Creation_text.isVisible()) {
						actualResult += "*** Other Advisor Creation not visible ***";
						LOGGER.info("*** Other Advisor Creation not visible ***");
						args.put("status", "Fail");
					} else {
						actualResult += OtherAdv_Creation_text.getText() + " is visible";
						LOGGER.info(OtherAdv_Creation_text.getText() + " is visible");

					}
					if (!ExpectedIncome_text.isVisible()) {
						actualResult += "*** Expected Income not visible ***";
						LOGGER.info("*** Expected Income not visible ***");
						args.put("status", "Fail");
					} else {
						actualResult += ExpectedIncome_text.getText() + " is visible";
						LOGGER.info(ExpectedIncome_text.getText() + " is visible");

					}
					if (!WNBP_text.isVisible()) {
						actualResult += "*** WNBP not visible ***";
						LOGGER.info("*** WNBP not visible ***");
						args.put("status", "Fail");
					} else {
						actualResult += WNBP_text.getText() + " is visible";
						LOGGER.info(WNBP_text.getText() + " is visible");

					}
					actualResult +=  "Create income planning screen should get open with above fields | ";

					if (!args.get("NLA Creation").equalsIgnoreCase("")) {
						NLACreation_input.type(args.get("NLA Creation"));
						CU.takeScreenshot(args, driver, "enter value in field NLA Creation");
						actualResult +=  "enter value " + args.get("NLA Creation")
								+ " in field NLA Creation |";
						LOGGER.info("Enter value in field NLA Creation");
					}
					if (!args.get("Active NLA").equalsIgnoreCase("")) {
						ActiveNLA_input.type(args.get("Active NLA"));
						CU.takeScreenshot(args, driver, "enter value in field Active NLA");
						actualResult +=  "enter value " + args.get("Active NLA")
								+ " in field Active NLA |";
						LOGGER.info("Enter value in field Active NLA");
					}
					CU.takeScreenshot(args, driver, "create income planning page");
					if (!args.get("LP Creation").equalsIgnoreCase("")) {
						LPCreation_input.type(args.get("LP Creation"));
						CU.takeScreenshot(args, driver, "enter value in field LP Creation");
						actualResult +=  "enter value " + args.get("LP Creation")
								+ " in field LP Creation |";
						LOGGER.info("Enter value in field LP Creation");
					}
					if (!args.get("Active LP").equalsIgnoreCase("")) {
						ActiveLP_input.type(args.get("Active LP"));
						CU.takeScreenshot(args, driver, "enter value in field Active LP");
						actualResult +=  "enter value " + args.get("Active LP") + " in field Active LP |";
						LOGGER.info("Enter value in field Active LP");
					}
					CU.takeScreenshot(args, driver, "create income planning page");
					if (!args.get("OtherAdvisor").equalsIgnoreCase("")) {
						OtherAdvisor_input.type(args.get("OtherAdvisor"));
						CU.takeScreenshot(args, driver, "enter value in field Other advisor");
						actualResult +=  "enter value " + args.get("OtherAdvisor")
								+ " in field Other advisor |";
						LOGGER.info("Enter value in field Other Advisor");
					}
					if (!args.get("WNBP").equalsIgnoreCase("")) {
						WNBP_input.type(args.get("WNBP"));
						CU.takeScreenshot(args, driver, "enter value in field WNBP");
						actualResult +=  "enter value " + args.get("WNBP") + " in field WNBP |";
						LOGGER.info("Enter value in field WNBP");
					}
					if (!args.get("ExpectedIncome").equalsIgnoreCase("")) {
						ExpectedIncome_input.type(args.get("ExpectedIncome"));
						CU.takeScreenshot(args, driver, "enter value in field ExpectedIncome");
						actualResult +=  "enter value " + args.get("ExpectedIncome")
								+ " in field ExpectedIncome |";
						LOGGER.info("Enter value in field ExpectedIncome");
					}
					if (Input_Error.isPresent()) {
						actualResult = actualResult
								+ CU.assertEquals("Only numbers are allowed", Input_Error.getText(), args);
						asert.assertEquals(Input_Error.getText(), "Only numbers are allowed");
						asert.assertAll();
						actualResult +=  "enter value in fields getting error as " + Input_Error.getText()
								+ " | ";
						LOGGER.info("enter value in fields getting error as " + Input_Error.getText());
					}

					CU.takeScreenshot(args, driver, "create income planning page");

					LeaderPage Leader_page = new LeaderPage(getDriver());

					Leader_page.AddTarget(args);
					actualResult = args.get("ActualResult");
				}
				args.put("ActualResult", actualResult);

			}

		} catch (Exception e) {
			e.getStackTrace();
			LOGGER.error(e.getMessage());
			actualResult +=  "*** " + e.getMessage() + " ***";
			args.put("ActualResult", actualResult);
			args.put("sOutput", e.toString());
			CU.takeScreenshot(args, driver, "Error Page");

			if (args.get("Test Case Type").equalsIgnoreCase("Negative")) {
				args.put("status", "Pass");
			} else {
				args.put("status", "Fail");
			}
		}
		return new ChoreographyPage(driver);
	}
}
