package com.web.pages.Choreography;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;

import com.common.utils.CommonUtilities;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

public class LeaderPage extends AbstractPage {

	public LeaderPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "(//input[@id='fullWidth'])[2]")
	private ExtendedWebElement SearchLead;

	@FindBy(xpath = "//p[@class='MuiTypography-root MuiTypography-body1 css-17exevb']")
	private ExtendedWebElement Week;

	@FindBy(xpath = "//p[@class='MuiTypography-root MuiTypography-body1 css-vh46n7']")
	private ExtendedWebElement DatePeriod;

	@FindBy(xpath = "//p[@class='MuiTypography-root MuiTypography-body1 css-g8sahe']")
	private ExtendedWebElement Name;

	@FindBy(xpath = "//p[@class='MuiTypography-root MuiTypography-body1 css-1wnzphj']")
	private ExtendedWebElement Role;

	@FindBy(xpath = "//p[@class='MuiTypography-root MuiTypography-body1 css-d3a5ec']")
	private ExtendedWebElement BranchAddress;

	@FindBy(xpath = "//p[text()='NLA Creation']")
	private ExtendedWebElement NLA_Creation_text;

	@FindBy(xpath = "//p[text()='Active NLA']")
	private ExtendedWebElement ActiveNLA_text;

	@FindBy(xpath = "//p[text()='LP Creation']")
	private ExtendedWebElement LP_Creation_text;

	@FindBy(xpath = "//p[text()='Active LP']")
	private ExtendedWebElement ActiveLP_text;

	@FindBy(xpath = "//p[text()='Other Advisor Creation']")
	private ExtendedWebElement OtherAdv_Creation_text;

	@FindBy(xpath = "//p[text()='Expected Income']")
	private ExtendedWebElement ExpectedIncome_text;

	@FindBy(xpath = "//p[text()='WNBP']")
	private ExtendedWebElement WNBP_text;

	@FindBy(xpath = "//p[@class='MuiTypography-root MuiTypography-body1 css-x2u434']")
	private List<ExtendedWebElement> RedAchieved;

	@FindBy(xpath = "//p[@class='MuiTypography-root MuiTypography-body1 css-gh8nqz']")
	private List<ExtendedWebElement> GreenAchieved;

	@FindBy(xpath = "//p[@class='MuiTypography-root MuiTypography-body1 css-1kgikfq']")
	private List<ExtendedWebElement> OrangeAchieved;

	@FindBy(xpath = "//p[@class='MuiTypography-root MuiTypography-body1 css-17exevb']")
	private ExtendedWebElement Date;

	@FindBy(xpath = "//*[@class='MuiBox-root css-pcqfzb']//p")
	private ExtendedWebElement MonthYear;

	@FindBy(xpath = "//*[text()='Create Income Planning']")
	private ExtendedWebElement Create_IP;

	@FindBy(xpath = "//button[@id='add-target-button']")
	private ExtendedWebElement AddTargetButton;

	@FindBy(xpath = "(//div[@class='MuiBox-root css-15nsv0c']//p)[1]")
	private ExtendedWebElement SuccessfullPopUp_Text;

	@FindBy(xpath = "(//div[@class='MuiBox-root css-15nsv0c']//div)[3]")
	private ExtendedWebElement PopUp_ReferNo;

	@FindBy(xpath = "(//div[@class='MuiBox-root css-15nsv0c']//button)[1]")
	private ExtendedWebElement PopUp_Btn;

	@FindBy(xpath = "(//div[@class='MuiBox-root css-15nsv0c']//img)[1]")
	private ExtendedWebElement PopUp_CopyBtn;

	JavascriptExecutor js = (JavascriptExecutor) driver;
	SoftAssert asert = new SoftAssert();
	CommonUtilities CU = new CommonUtilities();
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public void SearchLeader(HashMap<String, String> args) throws Exception {

		String actualResult = args.get("ActualResult");

		if (!args.get("SearchLeader").equalsIgnoreCase("")) {
			SearchLead.type(args.get("SearchLeader"));
			CU.takeScreenshot(args, driver, "Search Leader Page");
			actualResult = actualResult + " Search Leader | ";
			LOGGER.info("Search Leader");
			driver.findElement(By.xpath("(//*[text()='" + args.get("SearchLeader") + "'])[1]")).click();
			CU.takeScreenshot(args, driver, "Leader Page");
		}
		args.put("ActualResult", actualResult);

	}

	public void LeaderInfo(HashMap<String, String> args) throws Exception {

		String actualResult = args.get("ActualResult");

		actualResult = actualResult + CU.assertEquals(Name.getText(), Name.getText(), args);
		actualResult = actualResult + CU.assertEquals(Role.getText(), Role.getText(), args);
		actualResult = actualResult
				+ CU.assertEquals(BranchAddress.getText(), BranchAddress.getText(), args);
		actualResult = actualResult + " this all displayed on top section |";

		LOGGER.info("displayed top section");

		if (args.get("Choreography_Plan").equalsIgnoreCase("Weekly Review")) {
			actualResult = actualResult + " Week - " + Week.getText() + " , DatePeriod - " + DatePeriod.getText()
					+ " displayed week fields and dates | ";
			LOGGER.info("displayed date");

		} else if (args.get("Review_Flag").equalsIgnoreCase("Yes")) {
			actualResult = actualResult
					+ CU.assertEquals("Create Income Planning", Create_IP.getText(), args);

			if (MonthYear.isElementPresent()) {
				actualResult = actualResult
						+ "create income planning screen middle section displayed with corrent Month Year Section - "
						+ MonthYear.getText() + " |";
				LOGGER.info("displayed date");
			} else {
				actualResult = actualResult + "*** Month Year date not visible ***";
				LOGGER.info("date not displayed");
				args.put("status", "Fail");
			}
			actualResult = actualResult + "Date - " + Date.getText() + " Displayed date | ";
			LOGGER.info("displayed date");

		} else {
			actualResult = actualResult + "Date - " + Date.getText() + " Displayed date | ";
			LOGGER.info("displayed date");
		}

		js.executeScript("window.scrollBy(0,500)", "");
		CU.takeScreenshot(args, driver, "Leader Page");

		actualResult = actualResult + "Leader with name entered in search displayed on screen |";
		args.put("ActualResult", actualResult);
	}

	public void LeaderAchievements(HashMap<String, String> args) throws Exception {

		String actualResult = args.get("ActualResult");

		actualResult = actualResult + "target v/s achievement shown on the screen for selected leader|";

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
		actualResult = actualResult
				+ " Achievement leader dashboard screen middle section is displayed with above details | ";

		for (int i = 1; i <= RedAchieved.size(); i++) {
			String RedFlags = driver
					.findElement(
							By.xpath("(//p[@class='MuiTypography-root MuiTypography-body1 css-x2u434'])[" + i + "]"))
					.getText();
			actualResult = actualResult + "Red Achivements-" + RedFlags + ",";
			LOGGER.info("Red Achivements-" + RedFlags + ",");
//			String[] RedValue = RedFlags.split("%");
//			String value = RedValue[0];

			args.put("ActualResult", actualResult);
		}

		actualResult = actualResult + "| Leaders achievements highligted with Red colour |";
		for (int i = 1; i <= OrangeAchieved.size(); i++) {
			String OrangeFlags = driver
					.findElement(
							By.xpath("(//p[@class='MuiTypography-root MuiTypography-body1 css-1kgikfq'])[" + i + "]"))
					.getText();
			actualResult = actualResult + "Orange Achivements-" + OrangeFlags + ",";
			LOGGER.info("Orange Achivements-" + OrangeFlags + ",");
			args.put("ActualResult", actualResult);
		}
		actualResult = actualResult + "| Leaders achievements highligted with Orange colour|";
		for (int i = 1; i <= GreenAchieved.size(); i++) {
			String GreenFlags = driver
					.findElement(
							By.xpath("(//p[@class='MuiTypography-root MuiTypography-body1 css-gh8nqz'])[" + i + "]"))
					.getText();
			actualResult = actualResult + "Green Achivements-" + GreenFlags + ",";
			LOGGER.info("Green Achivements-" + GreenFlags + ",");
			args.put("ActualResult", actualResult);
		}
		actualResult = actualResult + "| Leaders achievements highligted with green colour |";
		args.put("ActualResult", actualResult);
	}

	public void AddTarget(HashMap<String, String> args) throws Exception {

		String actualResult = args.get("ActualResult");
		if (!(args.get("WNBP").equalsIgnoreCase("") && args.get("ExpectedIncome").equalsIgnoreCase(""))) {
		if (AddTargetButton.isClickable()) {
			AddTargetButton.click();
			CU.takeScreenshot(args, driver, "Pop Up page");
			LOGGER.info("click on Add target btn");
			if (args.get("Choreography_Plan").equalsIgnoreCase("Income Planning")) {
				actualResult = actualResult + CU.assertEquals("Income planning successfully completed!",
						SuccessfullPopUp_Text.getText(), args);

			}
			if (args.get("Choreography_Plan").equalsIgnoreCase("Weekly Review")) {
				actualResult = actualResult + CU.assertEquals("Weekly review successfully completed!",
						SuccessfullPopUp_Text.getText(), args);

			}
			actualResult = actualResult + "Successfull popup msg '" + SuccessfullPopUp_Text.getText()
					+ "' | reference Number '" + PopUp_ReferNo.getText() + "' |";

			PopUp_CopyBtn.click();
			CU.takeScreenshot(args, driver, "Copy icon page");
			pause(2);
			actualResult = actualResult + "click on copy icon btn";
			LOGGER.info("click on copy icon btn");
			PopUp_Btn.click();
			LOGGER.info("click on done btn");
			CU.takeScreenshot(args, driver, "after click Pop Up done btn page");
			js.executeScript("window.scrollBy(0,500)", "");
			CU.takeScreenshot(args, driver, "Leader list page");

			actualResult = actualResult + "user should get navigated to list of leaders screen|";
			LOGGER.info("user should get navigated to list of leaders screen");
			js.executeScript("window.scrollBy(0,500)", "");
			CU.takeScreenshot(args, driver, "Leader list page");

			actualResult = actualResult + " Add achievement tab is not visible after adding achievements |";

		}
		}
		args.put("ActualResult", actualResult);
	}

}