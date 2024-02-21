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

public class AgencyEmployee_BAM extends AbstractPage {

	@FindBy(xpath = "(//*[text()='Leader']/following::*[@xmlns='http://www.w3.org/2000/svg'][1])[1]")
	private ExtendedWebElement Leader;

	@FindBy(xpath = "(//*[text()='Advisor']/following::*[@xmlns='http://www.w3.org/2000/svg'][1])[1]")
	private ExtendedWebElement Advisor;

	@FindBy(xpath = "//*[text()='Trainer']/following::*[@xmlns='http://www.w3.org/2000/svg'][1]")
	private ExtendedWebElement Trainer;

	@FindBy(xpath = "//*[text()='Agency Employee - (BAM)']")
	private ExtendedWebElement AgencyEmployeeBAM;

	@FindBy(xpath = "//*[text()='BAM - L1']/following::*[@xmlns='http://www.w3.org/2000/svg'][1]")
	private ExtendedWebElement BAM_L1;

	@FindBy(xpath = "//*[@class='MuiStack-root css-17pcntq']//p[2]")
	private ExtendedWebElement ViewBranch;

	@FindBy(xpath = "//*[text()='BAM - LN']")
	private ExtendedWebElement BAM_LN_tab;

	@FindBy(xpath = "//*[text()='BAM - L2']")
	private ExtendedWebElement BAM_L2_tab;

	@FindBy(xpath = "//*[text()='BAM - L1']")
	private ExtendedWebElement BAM_L1_tab;

	@FindBy(xpath = "//*[text()='BAM']")
	private ExtendedWebElement BAM_tab;

	@FindBy(xpath = "(//*[text()='Leader'])[1]")
	private ExtendedWebElement Leader_tab;

	@FindBy(xpath = "(//*[text()='Advisor'])[1]")
	private ExtendedWebElement Advisor_tab;

	@FindBy(xpath = "(//*[text()='Trainer'])[2]")
	private ExtendedWebElement Trainer_tab;

	public AgencyEmployee_BAM(WebDriver driver) {
		super(driver);
	}
	CommonUtilities CU = new CommonUtilities();
	ReusableComponents comp = new ReusableComponents(driver);
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public AgencyEmployee_BAM_L2 AgencyEmp_BAM(HashMap<String, String> args)
			throws IOException, InterruptedException {

		String actualResult = args.get("actualResult");
		try {
			if (args.get("FlowName").contains(",") || !args.get("FlowName").contains(",")) {
				String APAdvisor[] = args.get("FlowName").split(",");
				for (String FlowName : APAdvisor) {

					if (FlowName.contains("AgencyEmployeeBAM")) {
						AgencyEmployeeBAM.click();
						CU.takeScreenshot(args, driver, "Agency Employee BAM Page");
						actualResult += " Click on Agency Employee BAM | ";
						LOGGER.info("Click on Agency Employee BAM ");
						Trainer.scrollTo();
						CU.takeScreenshot(args, driver, "Agency Employee BAM Page");
						
						if(!BAM_LN_tab.isVisible()) {
							actualResult += " *** BAM LN is not visible *** | ";
							LOGGER.info("*** BAM LN is not visible ***");
							args.put("status", "Fail");
						}else {
							actualResult += " BAM LN is visible | ";
							LOGGER.info("BAM LN is visible");
						}
						if(!BAM_L2_tab.isVisible()) {
							actualResult += " *** BAM L2 is not visible *** | ";
							LOGGER.info("*** BAM L2 is not visible ***");
							args.put("status", "Fail");
						}else {
							actualResult += " BAM L2 is visible | ";
							LOGGER.info("BAM L2 is visible");
						}
						if(!BAM_L1_tab.isVisible()) {
							actualResult += " *** BAM L1 is not visible *** | ";
							LOGGER.info("*** BAM L1 is not visible ***");
							args.put("status", "Fail");
						}else {
							actualResult += " BAM L1 is visible | ";
							LOGGER.info("BAM L1 is visible");
						}
						if(!BAM_tab.isVisible()) {
							actualResult += " *** BAM is not visible *** | ";
							LOGGER.info("*** BAM is not visible ***");
							args.put("status", "Fail");
						}else {
							actualResult += " BAM is visible | ";
							LOGGER.info("BAM is visible");
						}
						if(!Leader_tab.isVisible()) {
							actualResult += " *** Leader is not visible *** | ";
							LOGGER.info("*** Leader is not visible ***");
							args.put("status", "Fail");
						}else {
							actualResult += " Leader is visible | ";
							LOGGER.info("Leader is visible");
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
						
						if (FlowName.contains("AgencyEmployeeBAM_Advisor")) {
							Advisor.click();
							actualResult += " Click on Advisor | ";
							LOGGER.info("Click on Advisor");
							CU.takeScreenshot(args, driver, "AgencyEmployeeBAM Advisor Details Page");
							comp.Details(args);
							actualResult = args.get("actualResult");
							actualResult += " Advisor Details| ";
						}
						
						if (FlowName.contains("AgencyEmployeeBAM_Leader")) {
							Leader.click();
							actualResult += " Click on Leader | ";
							LOGGER.info("Click on Leader");
							CU.takeScreenshot(args, driver, "AgencyEmployeeBAM Leader Details Page");
							comp.Details(args);
							actualResult = args.get("actualResult");
							actualResult += " Leader Details| ";
							
							ViewBranch.click();
							actualResult += " Click on ViewBranch | ";
							LOGGER.info("Click on ViewBranch");
							CU.takeScreenshot(args, driver, "AgencyEmployeeBAM ViewBranch Page");
							comp.Details(args);
							actualResult = args.get("actualResult");
							actualResult += " View Branch Details| ";
							
							BAM_L1.scrollTo();
							CU.takeScreenshot(args, driver, "AgencyEmployeeBAM BAM L1 Page");
							BAM_L1.click();
							CU.takeScreenshot(args, driver, "AgencyEmployeeBAM BAM L1 Page");
							comp.Details(args);
							actualResult = args.get("actualResult");
							actualResult += " BAM Details| ";
						}
//						comp.ClickOnAdvisor(args);
//						comp.ViewBranchHierarchy(args);
//						comp.ClickOnBAM(args);
					}
				}
			}

			actualResult +=  " | AgencyEmployee BAM_Flow Viewed Successfully";
			LOGGER.info("AgencyEmployee BAM_Flow Viewed Successfully");
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
		return new AgencyEmployee_BAM_L2(driver);
	}
}
