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

public class AgencyEmployee_BAM_L2 extends AbstractPage {

	@FindBy(xpath = "//*[text()='Leader N']/following::*[@xmlns='http://www.w3.org/2000/svg'][1]")
	private ExtendedWebElement LeaderN;

	@FindBy(xpath = "//*[text()='Advisor']/following::*[@xmlns='http://www.w3.org/2000/svg'][1]")
	private ExtendedWebElement Advisor;

	@FindBy(xpath = "//*[text()='Trainer']/following::*[@xmlns='http://www.w3.org/2000/svg'][1]")
	private ExtendedWebElement Trainer;

	@FindBy(xpath = "//*[text()='BAM - L2']/following::*[@xmlns='http://www.w3.org/2000/svg'][1]")
	private ExtendedWebElement BAM_L2;

	@FindBy(xpath = "//*[text()='Agency Employee - (BAM - L2)']")
	private ExtendedWebElement AgencyEmployeeBAML2;

	@FindBy(xpath = "//*[text()='BAM - LN']")
	private ExtendedWebElement BAM_LN_tab;

	@FindBy(xpath = "//*[text()='BAM - L2']")
	private ExtendedWebElement BAM_L2_tab;

	@FindBy(xpath = "//*[text()='BAM - L1']")
	private ExtendedWebElement BAM_L1_tab;

	@FindBy(xpath = "//*[text()='BAM']")
	private ExtendedWebElement BAM_tab;
	
	public AgencyEmployee_BAM_L2(WebDriver driver) {
		super(driver);
	}

	CommonUtilities CU = new CommonUtilities();
	ReusableComponents comp = new ReusableComponents(driver);
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public BANCA_SP AgencyEmp_BAM_L2(HashMap<String, String> args) throws IOException, InterruptedException {

		String actualResult = args.get("actualResult");
		try {
			if (args.get("FlowName").contains(",") || !args.get("FlowName").contains(",")) {
				String APAdvisor[] = args.get("FlowName").split(",");
				for (String FlowName : APAdvisor) {

					if (FlowName.contains("AgencyEmpBAML2")) {
						AgencyEmployeeBAML2.click();
						CU.takeScreenshot(args, driver, "AgencyEmployee BAML2 page");
						actualResult += " Click on Agency Employee BAM L2 | ";
						LOGGER.info("Click on Agency Employee BAM L2");
						Trainer.scrollTo();
						CU.takeScreenshot(args, driver, "AgencyEmployee BAML2 page");

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
						
						if (FlowName.contains("AgencyEmpBAML2_BAML2_Tab")) {
							
							BAM_L2.click();
							actualResult += " Click on BAM L2 | ";
							LOGGER.info("Click on BAM L2");
							CU.takeScreenshot(args, driver, "AgencyEmployee BAM L2 Page");
							comp.Details(args);
							actualResult = args.get("actualResult");
							actualResult += " BAM L2 Details| ";

//						comp.ClickOnAdvisor(args);
//						comp.ViewBranchHierarchy(args);
//						comp.ClickOnBAM(args);
						}
					}
				}
			}

			actualResult += " | AgencyEmployeeBAML2_Flow Viewed Successfully";
			LOGGER.info("AgencyEmployeeBAML2_Flow Viewed Successfully");
			args.put("actualResult", actualResult);

		} catch (Exception e) {
			e.getStackTrace();
			LOGGER.error(e.getMessage());
			actualResult += "*** " + e.getMessage() + " ***";
			args.put("ActualResult", actualResult);
			args.put("sOutput", e.toString());
			CU.takeScreenshot(args, driver, "Error Page");

			if (args.get("Test Case Type").equalsIgnoreCase("Negative")) {
				args.put("status", "Pass");
			} else {
				args.put("status", "Fail");
			}
		}
		return new BANCA_SP(driver);
	}
}
