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

public class BANCA_L2 extends AbstractPage {

	@FindBy(xpath = "//*[text()='Advisor']/following::*[@xmlns='http://www.w3.org/2000/svg'][1]")
	private ExtendedWebElement Advisor;

	@FindBy(xpath = "//*[text()='NH']/following::*[@xmlns='http://www.w3.org/2000/svg'][1]")
	private ExtendedWebElement NH;

	@FindBy(xpath = "(//*[text()='SP']/following::*[@xmlns='http://www.w3.org/2000/svg'][1])[1]")
	private ExtendedWebElement SP;

	@FindBy(xpath = "(//*[text()='L1'])[1]")
	private ExtendedWebElement L1;

	@FindBy(xpath = "//*[text()='L2']")
	private ExtendedWebElement L2;

	@FindBy (xpath="//*[text()='BANCA - (L2)']")
	private ExtendedWebElement BANCAL2;

	@FindBy(xpath = "(//*[text()='RM (Tata AIA - FLS)'])[1]")
	private ExtendedWebElement RM_Tab;

	@FindBy(xpath = "//*[text()='Partner Branch 1']/following::*[@xmlns='http://www.w3.org/2000/svg'][1]")
	private ExtendedWebElement PartnerBranch1;

	public BANCA_L2(WebDriver driver) {
		super(driver);
	}
	CommonUtilities CU = new CommonUtilities();
	ReusableComponents comp = new ReusableComponents(driver);
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public NonSalesPerson Banca_L2(HashMap<String, String> args) throws IOException, InterruptedException {

		String actualResult = args.get("actualResult");
		try {
			if (args.get("FlowName").contains(",") || !args.get("FlowName").contains(",")) {
				String APAdvisor[] = args.get("FlowName").split(",");
				for (String FlowName : APAdvisor) {
					if (FlowName.contains("BANCA_L2")) {
						BANCAL2.click();
						actualResult += " Click on Banca L2 | ";
						LOGGER.info("Click on Banca L2");
						CU.takeScreenshot(args, driver, "Banca L2 page");
						
						L2.click();
						CU.takeScreenshot(args, driver, "Banca L2 page");
						
						L1.click();
						CU.takeScreenshot(args, driver, "Banca L2 page");
						
						RM_Tab.click();
						CU.takeScreenshot(args, driver, "Banca L2 page");
						
						if (FlowName.contains("BANCA_L2_PartnerBranch1")) {
							PartnerBranch1.click();
							actualResult += " Click on PartnerBranch1 | ";
							LOGGER.info("Click on PartnerBranch1");
							CU.takeScreenshot(args, driver, "PartnerBranch1 page");
							
							comp.Details(args);
							actualResult = args.get("actualResult");
							actualResult += " PartnerBranch1 Details| ";
						}
						
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
		return new NonSalesPerson(driver);
	}
}
