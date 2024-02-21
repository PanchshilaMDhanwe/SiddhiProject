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

public class NonSalesPerson extends AbstractPage {

	@FindBy(xpath = "//*[text()='Non Sales Person']")
	private ExtendedWebElement NonSalesPerson;

	@FindBy(xpath = "//*[text()='Assistant Vice President - Digital Experience']")
	private ExtendedWebElement AssistantVP_DigitalOperations;

	@FindBy(xpath = "//*[text()='Senior Manager - Digital Operations']/following::*[@xmlns='http://www.w3.org/2000/svg'][1]")
	private ExtendedWebElement SeniorManager_DigitalOperations;

	@FindBy(xpath = "//*[contains(text(),'Managing Director')]")
	private ExtendedWebElement ManagingDirector;

	@FindBy(xpath = "//*[contains(text(),'Executive Vice')]")
	private ExtendedWebElement ExecutiveVice;

	@FindBy(xpath = "//*[contains(text(),'Head- Digital Solutions')]")
	private ExtendedWebElement DigitalSolutions;

	@FindBy(xpath = "//*[contains(text(),'Assistant Vice')]")
	private ExtendedWebElement AssistantVice;

	@FindBy(xpath = "//*[contains(text(),'Assistant Manager')]")
	private ExtendedWebElement AssistantManager;

	public NonSalesPerson(WebDriver driver) {
		super(driver);
	}

	CommonUtilities CU = new CommonUtilities();
	ReusableComponents comp = new ReusableComponents(driver);
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public ProfilePage NonSales_Person(HashMap<String, String> args) throws IOException, InterruptedException {

		String actualResult = args.get("actualResult");
		try {

			if (args.get("FlowName").contains(",") || !args.get("FlowName").contains(",")) {
				String APAdvisor[] = args.get("FlowName").split(",");
				for (String FlowName : APAdvisor) {

					if (FlowName.contains("NonSalesPerson")) {

						NonSalesPerson.click();
						CU.takeScreenshot(args, driver, "NonSalesPerson page");
						AssistantVP_DigitalOperations.scrollTo();
						CU.takeScreenshot(args, driver, "AssistantVP_DigitalOperations page");


						if(!ManagingDirector.isVisible()) {
							actualResult += " *** Managing Director and Chief Executive Officer N is not visible *** | ";
							LOGGER.info("*** Managing Director and Chief Executive Officer N is not visible ***");
							args.put("status", "Fail");
						}else {
							actualResult += ManagingDirector.getText() +" is visible | ";
							LOGGER.info(ManagingDirector.getText() + " is visible");
						}
						if(!ExecutiveVice.isVisible()) {
							actualResult += " *** Executive Vice President and Head - Technology is not visible *** | ";
							LOGGER.info("*** Executive Vice President and Head - Technology is not visible ***");
							args.put("status", "Fail");
						}else {
							actualResult += ExecutiveVice.getText() +" is visible | ";
							LOGGER.info(ExecutiveVice.getText() + " is visible");
						}
						if(!DigitalSolutions.isVisible()) {
							actualResult += " *** Head- Digital Solutions is not visible *** | ";
							LOGGER.info("*** Head- Digital Solutions is not visible ***");
							args.put("status", "Fail");
						}else {
							actualResult += DigitalSolutions.getText() +" is visible | ";
							LOGGER.info(DigitalSolutions.getText() + " is visible");
						}
						if(!AssistantVice.isVisible()) {
							actualResult += " *** Assistant Vice President - Digital Experience is not visible *** | ";
							LOGGER.info("*** Assistant Vice President - Digital Experience is not visible ***");
							args.put("status", "Fail");
						}else {
							actualResult += AssistantVice.getText() +" is visible | ";
							LOGGER.info(AssistantVice.getText() + " is visible");
						}
						if(!AssistantManager.isVisible()) {
							actualResult += " *** Assistant Manager - Digital Solutions is not visible *** | ";
							LOGGER.info("*** Assistant Manager - Digital Solutions is not visible ***");
							args.put("status", "Fail");
						}else {
							actualResult += AssistantManager.getText() +" is visible | ";
							LOGGER.info(AssistantManager.getText() + " is visible");
						}
						
						if (FlowName.contains("NonSalesPerson_SeniorManager")) {
							
							SeniorManager_DigitalOperations.click();
							CU.takeScreenshot(args, driver, "SeniorManager_DigitalOperations");
							comp.Details(args);
							actualResult = args.get("actualResult");
							actualResult += " SeniorManager_DigitalOperations Details| ";
						}
					}
				}
			}

			actualResult += " | Non Sales Person flow done Successfully";
			LOGGER.info(" Non Sales Person flow done Successfully");
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
		return new ProfilePage(driver);
	}

}
