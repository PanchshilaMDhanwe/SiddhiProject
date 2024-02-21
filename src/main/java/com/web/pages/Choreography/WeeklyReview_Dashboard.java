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

public class WeeklyReview_Dashboard extends AbstractPage {

	public WeeklyReview_Dashboard(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//p[text()='Weekly Review']//following::*[@id='arrow-down'][1]")
	private ExtendedWebElement WeeklyReview;

	@FindBy(xpath = "//p[contains(text(),'Weekly Review')]")
	private ExtendedWebElement WRDashboard;

	@FindBy(xpath = "//*[text()='Open']")
	private ExtendedWebElement Open;

	@FindBy(xpath = "//*[text()='Completed']")
	private ExtendedWebElement Completed;

	@FindBy(xpath = "//*[text()='Add Achivements']")
	private ExtendedWebElement AddAchivements;

	@FindBy(xpath = "//*[text()='Achieved NLA Creation']")
	private ExtendedWebElement NLA_Creation_text;

	@FindBy(xpath = "//*[text()='Achieved Active NLA']")
	private ExtendedWebElement ActiveNLA_text;

	@FindBy(xpath = "//*[text()='Achieved LP Creation']")
	private ExtendedWebElement LP_Creation_text;

	@FindBy(xpath = "//*[text()='Achieved Active LP']")
	private ExtendedWebElement ActiveLP_text;

	@FindBy(xpath = "//*[text()='Achieved Other Advisor Creation']")
	private ExtendedWebElement OtherAdv_Creation_text;

	@FindBy(xpath = "//*[text()='Achieved Expected Income (₹)']")
	private ExtendedWebElement ExpectedIncome_text;

	@FindBy(xpath = "//*[text()='Achieved WNBP (₹)']")
	private ExtendedWebElement WNBP_text;

	@FindBy(xpath = "//*[text()='Achieved NLA Creation']//following::input[1]")
	private ExtendedWebElement NLACreation_input;

	@FindBy(xpath = "//*[text()='Achieved Active NLA']//following::input[1]")
	private ExtendedWebElement ActiveNLA_input;

	@FindBy(xpath = "//*[text()='Achieved LP Creation']//following::input[1]")
	private ExtendedWebElement LPCreation_input;

	@FindBy(xpath = "//*[text()='Achieved Active LP']//following::input[1]")
	private ExtendedWebElement ActiveLP_input;

	@FindBy(xpath = "(//*[text()='Achieved Other Advisor Creation']//following::input[1])[1]")
	private ExtendedWebElement OtherAdvisor_input;

	@FindBy(xpath = "//*[text()='Achieved WNBP (₹)']//following::input[1]")
	private ExtendedWebElement WNBP_input;

	@FindBy(xpath = "//*[text()='Achieved Expected Income (₹)']//following::input[1]")
	private ExtendedWebElement ExpectedIncome_input;

	@FindBy(xpath = "//*[text()='Only numbers are allowed']")
	private ExtendedWebElement Input_Error;

	CommonUtilities CU = new CommonUtilities();
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public Review_Leaders dashboard(HashMap<String, String> args) throws Exception {

		String actualResult = args.get("ActualResult");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		SoftAssert asert = new SoftAssert();
		try {

			if (args.get("Choreography_Plan").equalsIgnoreCase("Weekly Review")) {
				WeeklyReview.click();
				driver.navigate().refresh();
				waitUntil(ExpectedConditions.elementToBeClickable(WRDashboard.getElement()), EXPLICIT_TIMEOUT);
				LOGGER.info("click on weekly reviw btn");
				CU.takeScreenshot(args, driver, "Weekly Review Dashboard Page");
				js.executeScript("window.scrollBy(0,500)", "");
				CU.takeScreenshot(args, driver, "Weekly Review Dashboard Page");
				js.executeScript("window.scrollBy(0,-500)", "");
				actualResult +=  WRDashboard.getText() + " |";
				
				if(Open.isVisible()) {
					actualResult += Open.getText() +" tab is visible";
					LOGGER.info(Open.getText() +" tab is visible");
				}else {
					actualResult += "*** Open tab not visible ***";
					LOGGER.info("*** Open tab not visible ***");
					args.put("status", "Fail");
				}
				if(Completed.isVisible()) {
					actualResult += Completed.getText() +" tab is visible";
					LOGGER.info(Completed.getText() +" tab is visible");
				}else {
					actualResult += "*** Completed tab not visible ***";
					LOGGER.info("*** Completed tab not visible ***");
					args.put("status", "Fail");
				}
				js.executeScript("window.scrollBy(0,-500)", "");
				
				if (args.get("WeeklyReview_Section").equalsIgnoreCase("Completed")) {

					Completed.click();
					LOGGER.info("click on Complete tab");
					CU.takeScreenshot(args, driver, "Weekly Review Completed Section Page");
					js.executeScript("window.scrollBy(0,500)", "");
					CU.takeScreenshot(args, driver, "Weekly Review Completed Section Page");

					actualResult +=  " click on Completed section | ";
				}

				if (args.get("WeeklyReview_Section").equalsIgnoreCase("Open")) {
					Open.click();
					LOGGER.info("click on Open tab");
					CU.takeScreenshot(args, driver, "Weekly Review Open Section Page");
					js.executeScript("window.scrollBy(0,500)", "");
					CU.takeScreenshot(args, driver, "Weekly Review Open Section Page");

					actualResult +=  " click on Open section | ";
				}

				if (!args.get("SearchLeader").equalsIgnoreCase("")) {
					LeaderPage Leader_page = new LeaderPage(getDriver());

					Leader_page.SearchLeader(args);
					actualResult = args.get("ActualResult");
					Leader_page.LeaderInfo(args);
					actualResult = args.get("ActualResult");
					Leader_page.LeaderAchievements(args);
					actualResult = args.get("ActualResult");
					if (args.get("AddAchievement_Flag").equalsIgnoreCase("Yes")) {
						AddAchivements.click();
						Leader_page.LeaderInfo(args);
						LOGGER.info("click on Add Achievement tab");
						js.executeScript("window.scrollBy(0,600)", "");
						CU.takeScreenshot(args, driver, "Add Achievement Page");

						if(!NLA_Creation_text.isVisible()) {
							actualResult += "*** Achieved NLA creation not visible ***";
							LOGGER.info("*** Achieved NLA creation not visible ***");
							args.put("status", "Fail");
						}else {
							actualResult += NLA_Creation_text.getText() +" is visible";
							LOGGER.info( NLA_Creation_text.getText() +" is visible");
						}
						if(!ActiveNLA_text.isVisible()) {
							actualResult += "*** Achieved Active NLA not visible ***";
							LOGGER.info("*** Achieved Active NLA not visible ***");
							args.put("status", "Fail");
						}else {
							actualResult += ActiveNLA_text.getText() +" is visible";
							LOGGER.info( ActiveNLA_text.getText() +" is visible");
						}
						if(!LP_Creation_text.isVisible()) {
							actualResult += "*** Achieved LP creation not visible ***";
							LOGGER.info("*** Achieved LP creation not visible ***");
							args.put("status", "Fail");
						}else {
							actualResult += LP_Creation_text.getText() +" is visible";
							LOGGER.info( LP_Creation_text.getText() +" is visible");
							
						}
						if(!ActiveLP_text.isVisible()) {
							actualResult += "*** Achieved Active LP not visible ***";
							LOGGER.info("*** Achieved Active LP not visible ***");
							args.put("status", "Fail");
						}else {
							actualResult += ActiveLP_text.getText() +" is visible";
							LOGGER.info( ActiveLP_text.getText() +" is visible");
							
						}
						if(!OtherAdv_Creation_text.isVisible()) {
							actualResult += "*** Achieved Other Advisor Creation not visible ***";
							LOGGER.info("*** Achieved Other Advisor Creation not visible ***");
							args.put("status", "Fail");
						}else {
							actualResult += OtherAdv_Creation_text.getText() +" is visible";
							LOGGER.info( OtherAdv_Creation_text.getText() +" is visible");
							
						}
						if(!ExpectedIncome_text.isVisible()) {
							actualResult += "*** Achieved Expected Income not visible ***";
							LOGGER.info("*** Achieved Expected Income not visible ***");
							args.put("status", "Fail");
						}else {
							actualResult += ExpectedIncome_text.getText() +" is visible";
							LOGGER.info( ExpectedIncome_text.getText() +" is visible");
							
						}
						if(!WNBP_text.isVisible()) {
							actualResult += "*** Achieved WNBP not visible ***";
							LOGGER.info("*** Achieved WNBP not visible ***");
							args.put("status", "Fail");
						}else {
							actualResult += WNBP_text.getText() +" is visible";
							LOGGER.info( WNBP_text.getText() +" is visible");
							
						}
						actualResult +=  "Add Achievements screen should get open with above fields | ";

						if (!args.get("NLA Creation").equalsIgnoreCase("")) {
							NLACreation_input.type(args.get("NLA Creation"));
							CU.takeScreenshot(args, driver, "enter value in field NLA Creation");
							actualResult +=  "enter value " + args.get("NLA Creation")
									+ " in field NLA Creation |";
							LOGGER.info("Enter value in NLA Creation ");
						}
						if (!args.get("Active NLA").equalsIgnoreCase("")) {
							ActiveNLA_input.type(args.get("Active NLA"));
							CU.takeScreenshot(args, driver, "enter value in field Active NLA");
							actualResult +=  "enter value " + args.get("Active NLA")
									+ " in field Active NLA |";
							LOGGER.info("Enter value in Active NLA ");
						}
						CU.takeScreenshot(args, driver, "Add Achievement page");
						if (!args.get("LP Creation").equalsIgnoreCase("")) {
							LPCreation_input.type(args.get("LP Creation"));
							CU.takeScreenshot(args, driver, "enter value in field LP Creation");
							actualResult +=  "enter value " + args.get("LP Creation")
									+ " in field LP Creation |";
							LOGGER.info("Enter value in LP Creation ");
						}
						if (!args.get("Active LP").equalsIgnoreCase("")) {
							ActiveLP_input.type(args.get("Active LP"));
							CU.takeScreenshot(args, driver, "enter value in field Active LP");
							actualResult +=  "enter value " + args.get("Active LP")
									+ " in field Active LP |";
							LOGGER.info("Enter value in Active LP ");
						}
						CU.takeScreenshot(args, driver, "Add Achievement page");
						if (!args.get("OtherAdvisor").equalsIgnoreCase("")) {
							OtherAdvisor_input.type(args.get("OtherAdvisor"));
							CU.takeScreenshot(args, driver, "enter value in field Other advisor");
							actualResult +=  "enter value " + args.get("OtherAdvisor")
									+ " in field Other advisor |";
							LOGGER.info("Enter value in Other advisor ");
						}
						if (!args.get("WNBP").equalsIgnoreCase("")) {
							WNBP_input.type(args.get("WNBP"));
							CU.takeScreenshot(args, driver, "enter value in field WNBP");
							actualResult +=  "enter value " + args.get("WNBP") + " in field WNBP |";
							LOGGER.info("Enter value in WNBP");
						}
						if (!args.get("ExpectedIncome").equalsIgnoreCase("")) {
							ExpectedIncome_input.type(args.get("ExpectedIncome"));
							CU.takeScreenshot(args, driver, "enter value in field ExpectedIncome");
							actualResult +=  "enter value " + args.get("ExpectedIncome")
									+ " in field ExpectedIncome |";
							LOGGER.info("Enter value in ExpectedIncome");
						}
						if (Input_Error.isPresent()) {
							actualResult +=  CU.assertEquals("Only numbers are allowed", Input_Error.getText(), args);
							asert.assertEquals(Input_Error.getText(), "Only numbers are allowed");
							asert.assertAll();
							actualResult +=  "enter value in fields getting error as "
									+ Input_Error.getText() + " | ";
							LOGGER.info("enter value in fields getting error as "+ Input_Error.getText());
						}

						CU.takeScreenshot(args, driver, "Add Achievment page");

						Leader_page.AddTarget(args);
						actualResult = args.get("ActualResult");
					}
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
		return new Review_Leaders(driver);
	}
}
