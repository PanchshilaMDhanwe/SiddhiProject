package com.web.pages.LeadManagement;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.utils.CommonUtilities;
import com.common.utils.CommonWebUtilities;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

public class CreateLead extends AbstractPage {

	public CreateLead(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[contains(text(),'Leads')]")
	private ExtendedWebElement Leads;

	@FindBy(xpath = "//*[contains(text(),'Create Lead')]")
	private ExtendedWebElement CreateNewLead;

	@FindBy(xpath = "//*[contains(text(),'Full Name')]//following::input[1]")
	private ExtendedWebElement FullName;

	@FindBy(xpath = "//*[contains(text(),'Full Name')]//following::input[2]")
	private ExtendedWebElement MobileNumber;

	@FindBy(xpath = "//*[contains(text(),'Lead Details')]")
	private ExtendedWebElement LeadDetails;

	@FindBy(id = "Create")
	private ExtendedWebElement CreateBtn;

	@FindBy(xpath = "//div[@class='selected-flag']")
	private ExtendedWebElement selectFlagBtn;

	@FindBy(xpath = "//input[@class='search-box']")
	private ExtendedWebElement SearchCountry;

	@FindBy(xpath = "//*[contains(text(),'View More')]")
	private ExtendedWebElement ViewMore;

	@FindBy(xpath = "(//*[@class='MuiChip-label MuiChip-labelMedium css-11lqbxm'])[1]")
	private ExtendedWebElement NewStatus;

	@FindBy(xpath = "//*[contains(text(),'Reminder')]")
	private ExtendedWebElement Reminder;

	@FindBy(xpath = "//*[contains(text(),'Meet')]")
	private ExtendedWebElement Meet;

	@FindBy(xpath = "//*[contains(text(),'Not Contactable')]")
	private ExtendedWebElement NotContactable;

	@FindBy(xpath = "//*[contains(text(),'Kill')]")
	private ExtendedWebElement Kill;

	@FindBy(xpath = "//button[contains(text(),'Create New Note')]")
	private ExtendedWebElement CreateNewNote;

	@FindBy(xpath = "//button[contains(text(),'Diary')]")
	private ExtendedWebElement Diary;

	@FindBy(xpath = "//button[contains(text(),'Timeline')]")
	private ExtendedWebElement Timeline;

	@FindBy(xpath = "//*[text()='No Notes Yet']")
	private ExtendedWebElement NoNotesYet;

	@FindBy(xpath = "(//*[@class='MuiChip-label MuiChip-labelMedium css-11lqbxm'])[2]")
	private ExtendedWebElement selfCreated;

	@FindBy(xpath = "//div[@id='tableTitle']//p")
	private ExtendedWebElement EngangementTimeline;

	@FindBy(xpath = "//*[text()='Invalid Name']")
	private ExtendedWebElement InvalidName;

	@FindBy(xpath = "//*[@tabindex='-1']")
	private ExtendedWebElement RandomLead;

	@FindBy(xpath = "(//*[@id='tableTitle'])[1]")
	private ExtendedWebElement LeadName;

	@FindBy(xpath = "(//*[@id='tableTitle'])[1]//following::p[1]")
	private ExtendedWebElement LeadMobileNumber;

	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	CommonUtilities CU = new CommonUtilities();

	public LeadDetails LeadCreation(HashMap<String, String> args) throws Exception {

		String actualResult = args.get("ActualResult");
		try {

			if (args.get("CreateLead_Flag").equalsIgnoreCase("ValidLead")) {
				CreateNewLead.click();
				waitUntil(ExpectedConditions.elementToBeClickable(FullName.getElement()), EXPLICIT_TIMEOUT);
				String Fullname = CommonWebUtilities.RandomString_CapitalLetters(1)
						+ CommonWebUtilities.RandomString_SmallLetters(4);
				FullName.type(Fullname);
				actualResult += "Lead Name is editable |";
				LOGGER.info("Lead Name is editable " + Fullname);

				if (!args.get("CountryCode").equalsIgnoreCase("")) {
					selectFlagBtn.click();
					SearchCountry.type(args.get("CountryCode"));
					CU.takeScreenshot(args, driver, "Add Country code Page");
					driver.findElement(By.xpath("//*[text()='" + args.get("CountryCode") + "']")).click();
					actualResult += "Country Code is editable |";
					LOGGER.info("Country Code is editable ");
				}

				String mobileNumber = CommonWebUtilities.RandomstringNumber(10);
				MobileNumber.type(mobileNumber);
				actualResult += "Mobile Number is editable and create  |";
				LOGGER.info("Mobile Number is editable " + mobileNumber);
				CU.takeScreenshot(args, driver, "Add Full name and mobile number Page");
				CreateBtn.click();
				waitUntil(ExpectedConditions.elementToBeClickable(CreateNewLead.getElement()), EXPLICIT_TIMEOUT);
				pause(5);
				actualResult += "Create New Lead flow done successfully |";
				LOGGER.info("Create New Lead flow done successfully");
				CreateNewLead.scrollTo();
				CU.takeScreenshot(args, driver, "All Created Lead Page");
				driver.findElement(By.xpath("(//*[normalize-space(text())='" + Fullname + "'])[1]")).click();

				waitUntil(ExpectedConditions.elementToBeClickable(LeadDetails.getElement()), EXPLICIT_TIMEOUT);
				ViewMore.click();
				CU.takeScreenshot(args, driver, "Lead Details Page");

				actualResult += "Lead Name - " + LeadName.getText() + " , Mobile Number - " + LeadMobileNumber.getText()
						+ " |";
				actualResult += CU.assertEquals("SELF CREATED", selfCreated.getText(), args);
				actualResult += CU.assertEquals("NEW", NewStatus.getText(), args);
				actualResult += " New Lead Status As - " + NewStatus.getText() + " and " + selfCreated.getText()
						+ " | ";
				LOGGER.info("Lead Status As - " + selfCreated.getText() + " And " + NewStatus.getText());
				Kill.scrollTo();
				CU.takeScreenshot(args, driver, "Lead Details Page");

				actualResult += CU.assertEquals("Reminder", Reminder.getText(), args);
				actualResult += CU.assertEquals("Meet", Meet.getText(), args);
				actualResult += CU.assertEquals("Not Contactable", NotContactable.getText(), args);
				actualResult += CU.assertEquals("Kill", Kill.getText(), args);
				actualResult += " this all icons is displayed |";
				LOGGER.info("Reminder,Meet,Not Contactable,Kill this all icons are visible");

				EngangementTimeline.scrollTo();

				actualResult += CU.assertEquals("Engagement Timeline", EngangementTimeline.getText(), args);
				actualResult += CU.assertEquals("Diary", Diary.getText(), args);
				actualResult += CU.assertEquals("Timeline", Timeline.getText(), args);
				actualResult += CU.assertEquals("Create New Note", CreateNewNote.getText(), args);
				actualResult += " this icons display on Lead details |";
				LOGGER.info("Engagement Timeline,Diary,Timeline,Create New Note this all icons are visible");
				CU.takeScreenshot(args, driver, "Lead Details Page");

				LeadDetails.scrollTo();

			} else if (args.get("CreateLead_Flag").equalsIgnoreCase("InValidLead")) {
				CreateNewLead.click();
				waitUntil(ExpectedConditions.elementToBeClickable(FullName.getElement()), EXPLICIT_TIMEOUT);
				String Fullname = CommonWebUtilities.RandomString_CapitalLetters(1)
						+ CommonWebUtilities.RandomString_SmallLetters(30);
				FullName.type(Fullname);
				actualResult += "Lead Name is editable |";
				CU.takeScreenshot(args, driver, "Add Lead Full Name Lead Page");
				actualResult += "validation msg invalid name is diplayed for more thane 20 char - "
						+ InvalidName.getText() + " |";
				LOGGER.info(
						"validation msg invalid name is diplayed for more thane 20 char - " + InvalidName.getText());

			} else if (args.get("CreateLead_Flag").equalsIgnoreCase("InValid MobileNumber")) {
				CreateNewLead.click();
				waitUntil(ExpectedConditions.elementToBeClickable(FullName.getElement()), EXPLICIT_TIMEOUT);
				String Fullname = CommonWebUtilities.RandomString_CapitalLetters(1)
						+ CommonWebUtilities.RandomString_SmallLetters(4);
				FullName.type(Fullname);
				actualResult += "Lead Name is editable |";
				String mobileNumber = CommonWebUtilities.RandomstringNumber(7);
				MobileNumber.type(mobileNumber);
				actualResult += "Invalid mobile number , Create btn is not clickable |";
				LOGGER.info("Invalid mobile number , Create btn is not clickable");
				CU.takeScreenshot(args, driver, "Enter Invalid mobile number Page");

			} else if (args.get("CreateLead_Flag").equalsIgnoreCase("RandomLead")) {
				RandomLead.click();
				waitUntil(ExpectedConditions.elementToBeClickable(LeadDetails.getElement()), EXPLICIT_TIMEOUT);
				ViewMore.click();
				CU.takeScreenshot(args, driver, "Lead Details Page");
				actualResult += "Lead Name - " + LeadName.getText() + " , Mobile Number - " + LeadMobileNumber.getText()
						+ " |";
				LOGGER.info("Lead Name - " + LeadName.getText() + " , Mobile Number - " + LeadMobileNumber.getText());
				actualResult += CU.assertEquals("SELF CREATED", selfCreated.getText(), args);

				Reminder.scrollTo();
				CU.takeScreenshot(args, driver, "Lead Details Page");
				actualResult += CU.assertEquals("Reminder", Reminder.getText(), args);
				actualResult += CU.assertEquals("Meet", Meet.getText(), args);
				actualResult += CU.assertEquals("Not Contactable", NotContactable.getText(), args);
				actualResult += CU.assertEquals("Kill", Kill.getText(), args);
				actualResult += " this all icons is displayed |";
				LOGGER.info("Reminder,Meet,Not Contactable,Kill this all icons are visible");

				EngangementTimeline.scrollTo();
				actualResult += CU.assertEquals("Engagement Timeline", EngangementTimeline.getText(), args);
				actualResult += CU.assertEquals("Diary", Diary.getText(), args);
				actualResult += CU.assertEquals("Timeline", Timeline.getText(), args);
				actualResult += CU.assertEquals("Create New Note", CreateNewNote.getText(), args);
				actualResult += " this icons display on Lead details |";
				LOGGER.info("Engagement Timeline,Diary,Timeline,Create New Note this all icons are visible");
				CU.takeScreenshot(args, driver, "Lead Details Page");

			} else if (args.get("CreateLead_Flag").equalsIgnoreCase("CreateLead_Dedupe")) {
				
				CreateNewLead.click();
				waitUntil(ExpectedConditions.elementToBeClickable(FullName.getElement()), EXPLICIT_TIMEOUT);
				String Fullname = CommonWebUtilities.RandomString_CapitalLetters(1)
						+ CommonWebUtilities.RandomString_SmallLetters(4);
				FullName.type(Fullname);
				actualResult += "Lead Name is editable |";
				LOGGER.info("Lead Name is editable " + Fullname);

				if (!args.get("CountryCode").equalsIgnoreCase("")) {
					selectFlagBtn.click();
					SearchCountry.type(args.get("CountryCode"));
					CU.takeScreenshot(args, driver, "Add Country code Page");
					driver.findElement(By.xpath("//*[text()='" + args.get("CountryCode") + "']")).click();
					actualResult += "Country Code is editable |";
					LOGGER.info("Country Code is editable ");
				}

				String mobileNumber = CommonWebUtilities.RandomstringNumber(10);
				MobileNumber.type(mobileNumber);
				actualResult += "Mobile Number is editable and create  |";
				LOGGER.info("Mobile Number is editable " + mobileNumber);
				CU.takeScreenshot(args, driver, "Add Full name and mobile number Page");
				CreateBtn.click();
				waitUntil(ExpectedConditions.elementToBeClickable(CreateNewLead.getElement()), EXPLICIT_TIMEOUT);
				pause(5);
				actualResult += "Create New Lead flow done successfully |";
				LOGGER.info("Create New Lead flow done successfully");
				
				CreateNewLead.click();
				waitUntil(ExpectedConditions.elementToBeClickable(FullName.getElement()), EXPLICIT_TIMEOUT);
				FullName.type(Fullname);
				MobileNumber.type(mobileNumber);
				CU.takeScreenshot(args, driver, "Add new lead details Page");
				actualResult += "Add new details as FullName - " + Fullname + " And Phone Number - "
						+ mobileNumber + " | ";
				CreateBtn.click();
				LeadDetails_Functions function = new LeadDetails_Functions(getDriver());
				function.Dedupe_Popup(args);
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
		return new LeadDetails(driver);
	}
	
}
