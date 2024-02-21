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

public class BANCA_RM extends AbstractPage {

	@FindBy(xpath = "//*[text()='Advisor']/following::*[@xmlns='http://www.w3.org/2000/svg'][1]")
	private ExtendedWebElement Advisor;

	@FindBy(xpath = "//*[text()='NH']/following::*[@xmlns='http://www.w3.org/2000/svg'][1]")
	private ExtendedWebElement NH;

	@FindBy(xpath = "(//*[text()='SP']/following::*[@xmlns='http://www.w3.org/2000/svg'][1])[1]")
	private ExtendedWebElement SP;

	@FindBy(xpath = "(//*[text()='L1']/following::*[@xmlns='http://www.w3.org/2000/svg'][1])[1]")
	private ExtendedWebElement L1;

	@FindBy(xpath = "(//*[text()='L2']/following::*[@xmlns='http://www.w3.org/2000/svg'][1])[1]")
	private ExtendedWebElement L2;

	@FindBy(xpath = "//*[text()='BANCA - (RM)']")
	private ExtendedWebElement BANCARM;

	@FindBy(xpath = "//*[text()='L1']")
	private ExtendedWebElement L1Tab;

	@FindBy(xpath = "//*[text()='L2']")
	private ExtendedWebElement L2_Tab;

	@FindBy(xpath = "(//*[text()='RM (Tata AIA - FLS)'])[1]")
	private ExtendedWebElement RM_Tab;

	@FindBy(xpath = "//*[text()='Partner Branch 1']")
	private ExtendedWebElement PartnerBranch1_tab;

	@FindBy(xpath = "//*[text()='Partner Branch 2']")
	private ExtendedWebElement PartnerBranch2_tab;

	@FindBy(xpath = "//*[text()='Partner Branch 3']")
	private ExtendedWebElement PartnerBranch3_tab;

	@FindBy(xpath = "//*[text()='Partner Branch 4']")
	private ExtendedWebElement PartnerBranch4_tab;

	@FindBy(xpath = "//*[text()='Partner Branch 3']/following::*[@xmlns='http://www.w3.org/2000/svg'][1]")
	private ExtendedWebElement PartnerBranch3;

	public BANCA_RM(WebDriver driver) {
		super(driver);
	}
	CommonUtilities CU = new CommonUtilities();
	ReusableComponents comp = new ReusableComponents(driver);
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public BANCA_L2 Banca_RM(HashMap<String, String> args) throws IOException, InterruptedException {

		String actualResult = args.get("actualResult");
		try {
			if (args.get("FlowName").contains(",") || !args.get("FlowName").contains(",")) {
				String APAdvisor[] = args.get("FlowName").split(",");
				for (String FlowName : APAdvisor) {
					if (FlowName.contains("BANCA_RM")) {
						BANCARM.click();
						actualResult += " Click on Banca RM | ";
						LOGGER.info("Click on Banca RM");
						CU.takeScreenshot(args, driver, "Banca RM page");
						PartnerBranch3.scrollTo();
						CU.takeScreenshot(args, driver, "Banca RM page");
						
						if(!L2_Tab.isVisible()) {
							actualResult += " *** L2 is not visible *** | ";
							LOGGER.info("*** L2 is not visible ***");
							args.put("status", "Fail");
						}else {
							actualResult += " L2 is visible | ";
							LOGGER.info("L2 is visible");
						}
						if(!L1Tab.isVisible()) {
							actualResult += " *** L1 is not visible *** | ";
							LOGGER.info("*** L1 is not visible ***");
							args.put("status", "Fail");
						}else {
							actualResult += " L1 is visible | ";
							LOGGER.info("L1 is visible");
						}
						if(!RM_Tab.isVisible()) {
							actualResult += " *** RM (Tata AIA - FLS) is not visible *** | ";
							LOGGER.info("*** RM (Tata AIA - FLS) is not visible ***");
							args.put("status", "Fail");
						}else {
							actualResult += " RM (Tata AIA - FLS) is visible | ";
							LOGGER.info("RM (Tata AIA - FLS) is visible");
						}
						if(!PartnerBranch1_tab.isVisible()) {
							actualResult += " *** Partner Branch 1 is not visible *** | ";
							LOGGER.info("*** Partner Branch 1 is not visible ***");
							args.put("status", "Fail");
						}else {
							actualResult += " Partner Branch 1 is visible | ";
							LOGGER.info("Partner Branch 1 is visible");
						}
						if(!PartnerBranch2_tab.isVisible()) {
							actualResult += " *** Partner Branch 2 is not visible *** | ";
							LOGGER.info("*** Partner Branch 2 is not visible ***");
							args.put("status", "Fail");
						}else {
							actualResult += " Partner Branch 2 is visible | ";
							LOGGER.info("Partner Branch 2 is visible");
						}
						if(!PartnerBranch3_tab.isVisible()) {
							actualResult += " *** Partner Branch 3 is not visible *** | ";
							LOGGER.info("*** Partner Branch 3 is not visible ***");
							args.put("status", "Fail");
						}else {
							actualResult += " Partner Branch 3 is visible | ";
							LOGGER.info("Partner Branch 3 is visible");
						}
						if(!PartnerBranch4_tab.isVisible()) {
							actualResult += " *** Partner Branch 4 is not visible *** | ";
							LOGGER.info("*** Partner Branch 4 is not visible ***");
							args.put("status", "Fail");
						}else {
							actualResult += " Partner Branch 4 is visible | ";
							LOGGER.info("Partner Branch 4 is visible");
						}
						
						if (FlowName.contains("BANCA_RM_PartnerBranch3")) {
							PartnerBranch3.click();
							CU.takeScreenshot(args, driver, "Banca RM Partner Branch 3 page");
							actualResult += " Click on PartnerBranch3| ";
							LOGGER.info("Click on PartnerBranch3");
							comp.Details(args);
							actualResult = args.get("actualResult");
							actualResult += " PartnerBranch3 Details| ";
						}
						
						if (FlowName.contains("BANCA_RM_NH")) {
							PartnerBranch3.click();
							CU.takeScreenshot(args, driver, "Banca RM Partner Branch 3 page");
							actualResult += " Click on PartnerBranch3| ";
							LOGGER.info("Click on PartnerBranch3");
							
							NH.click();
							CU.takeScreenshot(args, driver, " PBranch3 NH page");
							actualResult += " Click on NH| ";
							LOGGER.info("Click on NH");
							comp.Details(args);
							actualResult = args.get("actualResult");
							actualResult += " NH Details| ";
						}
						if (FlowName.contains("BANCA_RM_SP")) {
							PartnerBranch3.click();
							CU.takeScreenshot(args, driver, "Banca RM Partner Branch 3 page");
							actualResult += " Click on PartnerBranch3| ";
							LOGGER.info("Click on PartnerBranch3");
							
							SP.click();
							CU.takeScreenshot(args, driver, " PBranch3 SP page");
							actualResult += " Click on NH| ";
							LOGGER.info("Click on SP");
							comp.Details(args);
							actualResult = args.get("actualResult");
							actualResult += " SP Details| ";
						}
						
						if (FlowName.contains("BANCA_RM_L1")) {
							L1.click();
							CU.takeScreenshot(args, driver, "Banca RM L1 page");
							actualResult += " Click on L1| ";
							LOGGER.info("Click on L1");
							comp.Details(args);
							actualResult = args.get("actualResult");
							actualResult += " L1 Details| ";
						}
						
						if (FlowName.contains("BANCA_RM_L2")) {
							L2.click();
							CU.takeScreenshot(args, driver, "Banca RM L2 page");
							actualResult += " Click on L2| ";
							LOGGER.info("Click on L2");
							comp.Details(args);
							actualResult = args.get("actualResult");
							actualResult += " L2 Details| ";
						}
						
//						PartnerBranch1.click();
//						comp.ClickOnNH(args);
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
		return new BANCA_L2(driver);
	}
}
