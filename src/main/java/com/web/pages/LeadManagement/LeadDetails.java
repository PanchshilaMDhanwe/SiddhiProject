package com.web.pages.LeadManagement;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.common.utils.CommonUtilities;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

public class LeadDetails extends AbstractPage {

	public LeadDetails(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[contains(text(),'View More')]")
	private ExtendedWebElement ViewMore;

	@FindBy(xpath = "//*[contains(text(),'Lead Details')]")
	private ExtendedWebElement LeadDetails;

	@FindBy(xpath = "//*[contains(text(),'Email Address')]//following::img[1]")
	private ExtendedWebElement AddEmailAddress;

	@FindBy(xpath = "//*[@id='Email']")
	private ExtendedWebElement EmailInput;

	@FindBy(xpath = "//*[@id='Add']")
	private ExtendedWebElement AddBtn;

	@FindBy(xpath = "//*[@id='Close']")
	private ExtendedWebElement closeBtn;

	@FindBy(xpath = "//*[text()='Address']//following::img[1]")
	private ExtendedWebElement AddAddress;

	@FindBy(xpath = "//*[@id='locality1']")
	private ExtendedWebElement locality1;

	@FindBy(xpath = "//*[@id='locality2']")
	private ExtendedWebElement locality2;

	@FindBy(xpath = "//*[@id='landmark']")
	private ExtendedWebElement Landmark;

	@FindBy(xpath = "//*[@id='zipCode']")
	private ExtendedWebElement PinCode;

	@FindBy(xpath = "//*[text()='Invalid PinCode']")
	private ExtendedWebElement InvalidPinCode;

	@FindBy(xpath = "//*[@id='save']")
	private ExtendedWebElement Save;

	@FindBy(xpath = "//*[@alt='PHONE']")
	private ExtendedWebElement PhoneBtn;

	@FindBy(xpath = "//button[contains(text(),'Create New Note')]")
	private ExtendedWebElement CreateNewNote;

	@FindBy(xpath = "//*[@id='locality1-label']")
	private ExtendedWebElement Locality1_Label;

	@FindBy(xpath = "//*[@id='locality2-label']")
	private ExtendedWebElement Locality2_Label;

	@FindBy(xpath = "//*[@id='landmark-label']")
	private ExtendedWebElement Landmark_Label;

	@FindBy(xpath = "//*[@id='zipCode-label']")
	private ExtendedWebElement Pincode_Label;

	@FindBy(xpath = "//*[@id='city-label']")
	private ExtendedWebElement City_Label;

	@FindBy(xpath = "//*[@id='state-label']")
	private ExtendedWebElement State_Label;

	@FindBy(xpath = "//*[@id='state']")
	private ExtendedWebElement State;

	@FindBy(xpath = "(//div[@class='MuiBox-root css-q02qg6']//p)[3]")
	private ExtendedWebElement AdressDetail;

	@FindBy(xpath = "//*[@id='city']")
	private ExtendedWebElement City;

	@FindBy(xpath = "//*[text()='Invalid Email Address']")
	private ExtendedWebElement InvalidEmail;

	CommonUtilities CU = new CommonUtilities();
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public LeadPage LeadDetailsPage(HashMap<String, String> args) throws Exception {

		String actualResult = args.get("ActualResult");
		try {

			LeadDetails_Functions function = new LeadDetails_Functions(getDriver());

			if (args.get("ReferralSource_Flag").equalsIgnoreCase("Yes")) {
				function.ReferralSources(args);
				actualResult = args.get("ActualResult");
			}

			if (args.get("StartJourney_Flag").equalsIgnoreCase("Yes")) {
				function.StartJourney(args);
				actualResult = args.get("ActualResult");
			}

			if (!args.get("Sources1").equalsIgnoreCase("")) {
				function.Sources(args);
				actualResult = args.get("ActualResult");
			}

			if (!args.get("AddNewEmail").equalsIgnoreCase("")) {

				AddEmailAddress.click();
				waitUntil(ExpectedConditions.elementToBeClickable(EmailInput.getElement()), SHORT_TIMEOUT);
				EmailInput.type(args.get("AddNewEmail"));
				CU.takeScreenshot(args, driver, "Adding new Email ID Page");
				LOGGER.info("Add email address");

				if (!InvalidEmail.isPresent()) {
					LOGGER.info("Error not visible");
				} else {
					actualResult += CU.assertEquals("Invalid Email Address", InvalidEmail.getText(), args);
					LOGGER.info(InvalidEmail.getText());
				}
				if (!AddBtn.isClickable()) {
					LOGGER.info("Add Button is not clickable");
					actualResult += " Add Button is not clickable | ";
				} else {
					AddBtn.click();
					waitUntil(ExpectedConditions.elementToBeClickable(closeBtn.getElement()), SHORT_TIMEOUT);
					CU.takeScreenshot(args, driver, "Added successfully emailId Page");
					closeBtn.click();
					CU.takeScreenshot(args, driver, "Added successfully emailId on lead details Page");
				}

				actualResult += " Email flow done successfully |";
				LOGGER.info("Email flow done successfully");
				args.put("ActualResult", actualResult);
			}

			if (args.get("MobileBtn_Flag").equalsIgnoreCase("Yes")) {
				PhoneBtn.click();
				CU.takeScreenshot(args, driver, "Phone Icon Page");
				actualResult += "Able to click on phone icon btn | ";
				LOGGER.info("Able to click on phone icon btn");
				args.put("ActualResult", actualResult);
			}

			if (!args.get("locality1").equalsIgnoreCase("")) {
				AddAddress.click();
				waitUntil(ExpectedConditions.elementToBeClickable(locality1.getElement()), SHORT_TIMEOUT);

				actualResult += CU.assertEquals("House / Flat / Block No.", Locality1_Label.getText(), args);
				actualResult += CU.assertEquals("Apartment / Road / Area", Locality2_Label.getText(), args);
				actualResult += CU.assertEquals("Landmark", Landmark_Label.getText(), args);
				actualResult += CU.assertEquals("Pin code", Pincode_Label.getText(), args);
				actualResult += CU.assertEquals("City", City_Label.getText(), args);
				actualResult += CU.assertEquals("State", State_Label.getText(), args);

				locality1.type(args.get("locality1"));
				locality2.type(args.get("locality2"));
				Landmark.type(args.get("Landmark"));
				PinCode.type(args.get("PinCode"));

				LOGGER.info("Add address details");

				if (InvalidPinCode.isPresent()) {
					actualResult += Pincode_Label.getText() + " label present in Pincode|";
					actualResult += City_Label.getText() + " label present in City|";
					actualResult += State_Label.getText() + " label present in State|";
					
					if (!args.get("PinCode").equalsIgnoreCase("")) {
						actualResult += "Automated select City - " + City.getAttribute("value") + " And State - "
								+ State.getAttribute("value") + " based on pincode|";
						LOGGER.info("Automated select City - " + City.getAttribute("value") + " And State - "
								+ State.getAttribute("value") + " based on pincode");
					}
					CU.takeScreenshot(args, driver, "Adding new Adress details Page");
					
					if (!Save.isClickable()) {
						LOGGER.info("Save Button is not clickable");
						actualResult += " Save Button is not clickable | ";
					}else {
						Save.click();
						waitUntil(ExpectedConditions.elementToBeClickable(closeBtn.getElement()), SHORT_TIMEOUT);
						CU.takeScreenshot(args, driver, "Added successfully Adress details Page");
						closeBtn.click();
						pause(2);
						ViewMore.click();
						CU.takeScreenshot(args, driver, "Added Lead Details Page");
						actualResult += AdressDetail.getText() + " this adress details added successfully|";
						LOGGER.info(AdressDetail.getText() + " this adress details added successfully");
						
					}
				} else {
					actualResult += "Error msg " + InvalidPinCode.getText() + " |";
					LOGGER.info("Error msg " + InvalidPinCode.getText());
					CU.takeScreenshot(args, driver, "Invalid Pincode Adress details Page");
				}
				args.put("ActualResult", actualResult);
			}

			if (!args.get("OfferFlag").equalsIgnoreCase("")) {
				function.Offer(args);
				actualResult = args.get("ActualResult");
			}
			if (!args.get("NonContactableReason").equalsIgnoreCase("")) {

				function.Noncontactable(args);
				actualResult = args.get("ActualResult");
				
				if (!args.get("Sources1").equalsIgnoreCase("")) {
					function.Sources(args);
					actualResult = args.get("ActualResult");
				}
			}
			if (!args.get("KillReason").equalsIgnoreCase("")) {

				function.Killflow(args);
				actualResult = args.get("ActualResult");
				
				if (!args.get("Sources1").equalsIgnoreCase("")) {
					function.Sources(args);
					actualResult = args.get("ActualResult");
				}
			}
			if (args.get("CreateNote_Flag").equalsIgnoreCase("Yes")) {
				function.CreateNote(args);
				actualResult = args.get("ActualResult");
			}
			if (args.get("SourceHistory_Flag").equalsIgnoreCase("Yes")) {
				function.SourceHistory(args);
				actualResult = args.get("ActualResult");
			}
			if (args.get("Timeline_Flag").equalsIgnoreCase("Yes")) {
				function.Timeline(args);
				actualResult = args.get("ActualResult");
			}

			args.put("ActualResult", actualResult);

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
		return new LeadPage(driver);
	}

}
