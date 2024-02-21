package com.web.pages.Hierarchy;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.utils.CommonUtilities;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

public class AgencyPartner_Advisor extends AbstractPage {

	public AgencyPartner_Advisor(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath = "//*[text()='Agency Partner - (Advisor)']")
	private ExtendedWebElement AgencyPartnerAdvisor;

	@FindBy(xpath = "//*[text()='Leader N']/following::*[@xmlns='http://www.w3.org/2000/svg'][1]")
	private ExtendedWebElement LeaderN;

	@FindBy(xpath = "//*[text()='Advisor']/following::*[@xmlns='http://www.w3.org/2000/svg'][1]")
	private ExtendedWebElement Advisor;

	@FindBy(xpath = "//*[text()='Trainer']/following::*[@xmlns='http://www.w3.org/2000/svg'][1]")
	private ExtendedWebElement Trainer;

	@FindBy(xpath = "//*[text()='BAM']/following::*[@xmlns='http://www.w3.org/2000/svg'][1]")
	private ExtendedWebElement BAM;

	@FindBy(xpath = "//*[@class='MuiStack-root css-17pcntq']//p[2]")
	private ExtendedWebElement ViewBranch;

	@FindBy(xpath = "//*[text()='Leader N']")
	private ExtendedWebElement LeaderN_tab;

	@FindBy(xpath = "//*[text()='Leader 2']")
	private ExtendedWebElement Leader2_tab;

	@FindBy(xpath = "//*[text()='Leader 1']")
	private ExtendedWebElement Leader1_tab;

	@FindBy(xpath = "//*[text()='Advisor']")
	private ExtendedWebElement Advisor_tab;

	@FindBy(xpath = "(//*[text()='Trainer'])[2]")
	private ExtendedWebElement Trainer_tab;

	CommonUtilities CU = new CommonUtilities();
	ReusableComponents comp = new ReusableComponents(driver);
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public AgencyPartner_Leader2 AP_Advisor(HashMap<String, String> args)
			throws IOException, InterruptedException {

		String actualResult = args.get("actualResult");
		try {

			if (args.get("FlowName").contains(",") || !args.get("FlowName").contains(",")) {
				String APAdvisor[] = args.get("FlowName").split(",");
				for (String FlowName : APAdvisor) {

					if (FlowName.contains("APAdvisor")) {

						AgencyPartnerAdvisor.click();
						CU.takeScreenshot(args, driver, "AP_Advisor1 Page");
						actualResult += " Click on Agency Partner Advisor | ";
						LOGGER.info("Click on Agency Partner Advisor");
						Advisor.scrollTo();
						CU.takeScreenshot(args, driver, "AP_Advisor2 Page");
						
						if(!LeaderN_tab.isVisible()) {
							actualResult += " *** Leader N is not visible *** | ";
							LOGGER.info("*** Leader N is not visible ***");
							args.put("status", "Fail");
						}else {
							actualResult += " Leader N is visible | ";
							LOGGER.info("Leader N is visible");
						}
						if(!Leader2_tab.isVisible()) {
							actualResult += " *** Leader 2 is not visible *** | ";
							LOGGER.info("*** Leader 2 is not visible ***");
							args.put("status", "Fail");
						}else {
							actualResult += " Leader 2 is visible | ";
							LOGGER.info("Leader 2 is visible");
						}
						if(!Leader1_tab.isVisible()) {
							actualResult += " *** Leader 1 is not visible *** | ";
							LOGGER.info("*** Leader 1 is not visible ***");
							args.put("status", "Fail");
						}else {
							actualResult += " Leader 1 is visible | ";
							LOGGER.info("Leader 1 is visible");
						}
						if(!Advisor_tab.isVisible()) {
							actualResult += " *** Advisor is not visible *** | ";
							LOGGER.info("*** Advisor is not visible ***");
							args.put("status", "Fail");
						}else {
							actualResult += " Advisor is visible | ";
							LOGGER.info("Advisor is visible");
						}
						if(!Trainer_tab.isVisible()) {
							actualResult += " *** Trainer is not visible *** | ";
							LOGGER.info("*** Trainer is not visible ***");
							args.put("status", "Fail");
						}else {
							actualResult += " Trainer is visible | ";
							LOGGER.info("Trainer is visible");
						}
						if (FlowName.contains("APAdvisor_Trainer")) {
							Trainer.click();
							actualResult += " Click on Trainer | ";
							LOGGER.info("Click on Trainer");
							CU.takeScreenshot(args, driver, "AP Advisor Trainer Details Page");
							comp.Details(args);
							actualResult = args.get("actualResult");
							actualResult += " Trainer Details| ";
						}
						if (FlowName.contains("APAdvisor_LeaderN_to_BAM")) {
							LeaderN.click();
							actualResult += " Click on Leader N | ";
							LOGGER.info("Click on Leader N");
							CU.takeScreenshot(args, driver, "AP Advisor LeaderN Details Page");
							comp.Details(args);
							actualResult = args.get("actualResult");
							actualResult += " Leader N Details| ";
							
							ViewBranch.click();
							actualResult += " Click on ViewBranch | ";
							LOGGER.info("Click on ViewBranch");
							CU.takeScreenshot(args, driver, "AP Advisor ViewBranch Page");
							comp.Details(args);
							actualResult = args.get("actualResult");
							actualResult += " View Branch Details| ";
							
							BAM.scrollTo();
							CU.takeScreenshot(args, driver, "AP Advisor BAM Page");
							BAM.click();
							CU.takeScreenshot(args, driver, "AP Advisor BAM Page");
							comp.Details(args);
							actualResult = args.get("actualResult");
							actualResult += " BAM Details| ";
						}
						
//						comp.ClickOnAdvisor(args);
//						comp.ViewBranchHierarchy(args);
//						comp.ClickOnLeaderN(args);
//						comp.ViewBranchHierarchy(args);
//						comp.ClickOnBAM(args);
					}
				}
			}

			actualResult +=  " | AgencyPartnerAdvisor-Advisor_Flow Viewed Successfully";
			LOGGER.info("AgencyPartnerAdvisor-Advisor_Flow Viewed Successfully");
			args.put("actualResult", actualResult);

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
		return new AgencyPartner_Leader2(driver);
	}
}
