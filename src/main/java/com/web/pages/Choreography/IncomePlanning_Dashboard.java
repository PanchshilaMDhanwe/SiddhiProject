package com.web.pages.Choreography;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.utils.CommonUtilities;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

public class IncomePlanning_Dashboard extends AbstractPage {

	public IncomePlanning_Dashboard(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//p[text()='Income Planning']//following::*[@id='arrow-down'][1]")
	private ExtendedWebElement IncomePlanning;

	@FindBy(xpath = "//p[text()='Income Planning Dashboard']")
	private ExtendedWebElement IPDashboard;

	@FindBy(xpath = "//*[contains(text(),'pending for 10 Leaders.')]") // p[@class='MuiTypography-root
	private ExtendedWebElement ReviewSentence;

	@FindBy(xpath = "//button[contains(text(),'View Individual')]")
	private ExtendedWebElement ViewIndividual;

	@FindBy(xpath = "//*[normalize-space(text())='Income Planning']")
	private ExtendedWebElement IP_text;

	@FindBy(xpath = "//p[text()='Weekly Review']//following::*[@id='arrow-down'][1]")
	private ExtendedWebElement WeeklyReview;

	CommonUtilities CU = new CommonUtilities();
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public Filters_Page dashboard(HashMap<String, String> args) throws Exception {

		String actualResult = args.get("ActualResult");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {

			if (args.get("Choreography_Plan").equalsIgnoreCase("Income Planning")) {
				
				driver.navigate().refresh();
				
				//driver.findElement(By.xpath("//p[text()='Income Planning']//following::*[@id='arrow-down'][1]")).click();
				IncomePlanning.click();
				waitUntil(ExpectedConditions.elementToBeClickable(IPDashboard.getElement()), EXPLICIT_TIMEOUT);
				CU.takeScreenshot(args, driver, "Income Planning Dashboard Page");
				LOGGER.info("click on Income planning btn");
				actualResult = actualResult
						+ "User able to get navigated to Income Planning Dashboard where consolidated achievements of all leaders|";

				actualResult +=  CU.assertEquals("Income Planning review is pending for 10 Leaders. Review", ReviewSentence.getText(), args);
				
				actualResult +=  "on top of the dashbord, sentence should display with content("
						+ ReviewSentence.getText() + ") | ";
				ViewIndividual.scrollTo();
				CU.takeScreenshot(args, driver, "Income Planning Dashboard Page");

				LeaderPage Leader_page = new LeaderPage(getDriver());

				Leader_page.LeaderAchievements(args);
				actualResult = args.get("ActualResult");
				
				if (args.get("ViewIndividual_Flag").equalsIgnoreCase("Yes")) {
					ViewIndividual.click();
					driver.navigate().refresh();
					waitUntil(ExpectedConditions.elementToBeClickable(IP_text.getElement()), EXPLICIT_TIMEOUT);
					LOGGER.info("click on view individual btn");
					actualResult +=  CU.assertEquals("Income Planning", IP_text.getText(), args);
					
					CU.takeScreenshot(args, driver, "View Individual Achievment Page");
					js.executeScript("window.scrollBy(0,500)", "");
					CU.takeScreenshot(args, driver, "View Individual Achievment Page");
					js.executeScript("window.scrollBy(0,-500)", "");

					if (!args.get("SearchLeader").equalsIgnoreCase("")) {
						
						Leader_page.SearchLeader(args);
						actualResult = args.get("ActualResult");
						Leader_page.LeaderInfo(args);
						actualResult = args.get("ActualResult");
						Leader_page.LeaderAchievements(args);
						actualResult = args.get("ActualResult");
					}
					args.put("ActualResult", actualResult);
				}

			}

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
		return new Filters_Page(driver);
	}
}
