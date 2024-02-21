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

public class BANCA_SP extends AbstractPage {

	@FindBy(xpath = "//*[text()='L1']/following::*[@xmlns='http://www.w3.org/2000/svg'][1]")
	private ExtendedWebElement L1;

	@FindBy(xpath = "(//*[text()='RM (Tata AIA - FLS)']/following::*[@xmlns='http://www.w3.org/2000/svg'][1])[1]")
	private ExtendedWebElement RM;

	@FindBy(xpath = "//*[text()='Trainer']/following::*[@xmlns='http://www.w3.org/2000/svg'][1]")
	private ExtendedWebElement Trainer;

	@FindBy(xpath = "//*[text()='BANCA - (SP)']")
	private ExtendedWebElement BANCA_SP;

	@FindBy(xpath = "//*[text()='L1']")
	private ExtendedWebElement L1Tab;

	@FindBy(xpath = "//*[text()='L2']")
	private ExtendedWebElement L2_Tab;

	@FindBy(xpath = "(//*[text()='RM (Tata AIA - FLS)'])[1]")
	private ExtendedWebElement RM_Tab;

	public BANCA_SP(WebDriver driver) {
		super(driver);
	}
	CommonUtilities CU = new CommonUtilities();
	ReusableComponents comp = new ReusableComponents(driver);
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public BANCA_RM Banca_SP(HashMap<String, String> args) throws IOException, InterruptedException {

		String actualResult = args.get("actualResult");
		try {
			if (args.get("FlowName").contains(",") || !args.get("FlowName").contains(",")) {
				String APAdvisor[] = args.get("FlowName").split(",");
				for (String FlowName : APAdvisor) {
					if (FlowName.contains("BANCA_SP")) {
						BANCA_SP.click();
						CU.takeScreenshot(args, driver, "Banca Sp page");
						actualResult += " Click on Banca SP | ";
						LOGGER.info("Click on Banca SP");
						Trainer.scrollTo();
						CU.takeScreenshot(args, driver, "Banca Sp page");
						L1Tab.click();
						CU.takeScreenshot(args, driver, "Banca Sp Expand L1 tab page");
						
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
						
						if (FlowName.contains("BANCA_SP_L1")) {
							L1.click();
							actualResult += " Click on L1 | ";
							LOGGER.info("Click on L1");
							CU.takeScreenshot(args, driver, "Banca Sp L1 page");
							comp.Details(args);
							actualResult = args.get("actualResult");
							actualResult += " L1 Details| ";
						}
						
						if (FlowName.contains("BANCA_SP_RM")) {
							RM.click();
							actualResult += " Click on RM | ";
							LOGGER.info("Click on RM");
							CU.takeScreenshot(args, driver, "Banca Sp RM page");
							comp.Details(args);
							actualResult = args.get("actualResult");
							actualResult += " RM Details| ";
						}
						
//						comp.ClickOnL1Name(args);
//						comp.ClickOnRMName(args);
						actualResult +=  " | BancaSP_Flow Viewed Successfully";
						LOGGER.info("BancaSP_Flow Viewed Successfully");
						args.put("actualResult", actualResult);
					}
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
		return new BANCA_RM(driver);
	}
}
