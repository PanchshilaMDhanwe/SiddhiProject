package com.pages.LeadManagement.Mobile;

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

public class Search_Lead extends AbstractPage {

	public Search_Lead(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[@text()='Create Lead']")
	private ExtendedWebElement CreateNewLead;

	@FindBy(xpath = "//*[@text()='Lead Details']")
	private ExtendedWebElement LeadDetails;

	@FindBy(xpath = "(//input[@id='fullWidth'])[2]")
	private ExtendedWebElement searchLead;

	@FindBy(xpath = "//*[@text()='View More']")
	private ExtendedWebElement ViewMore;

	@FindBy(xpath = "//*[@text()='Reminder']")
	private ExtendedWebElement Reminder;

	@FindBy(xpath = "//*[@text()='Meet']")
	private ExtendedWebElement Meet;

	@FindBy(xpath = "//*[@text()='Not Contactable']")
	private ExtendedWebElement NotContactable;

	@FindBy(xpath = "//*[@text()='Kill']")
	private ExtendedWebElement Kill;

	@FindBy(xpath = "//*[@text()='Create New Note']")
	private ExtendedWebElement CreateNewNote;

	@FindBy(xpath = "//*[@text()='Diary']")
	private ExtendedWebElement Diary;

	@FindBy(xpath = "//*[@text()='Timeline']")
	private ExtendedWebElement Timeline;

	@FindBy(xpath = "//*[@text()='No Notes Yet']")
	private ExtendedWebElement NoNotesYet;

	@FindBy(xpath = "//div[@id='tableTitle']//p")
	private ExtendedWebElement EngangementTimeline;

	@FindBy(xpath = "//*[@id='Add']")
	private ExtendedWebElement AddBtn;

	@FindBy(xpath = "//*[@id='Close']")
	private ExtendedWebElement closeBtn;

	@FindBy(xpath = "//*[@id='save']")
	private ExtendedWebElement Save;

	@FindBy(xpath = "//*[@text()='Title']")
	private ExtendedWebElement Title;

	@FindBy(xpath = "//*[@text()='Date']")
	private ExtendedWebElement Date;

	@FindBy(xpath = "//*[@text()='Note']")
	private ExtendedWebElement Note;

	@FindBy(xpath = "//*[@text()='No Lead found']")
	private ExtendedWebElement NoLeadfound;

	@FindBy(xpath = "//*[@class='css-1mx48yd']")
	private ExtendedWebElement MicrophoneBtn;

	@FindBy(xpath = "(//input[@id='fullWidth'])[2]")
	private ExtendedWebElement searchingLead;

	@FindBy(xpath = "(//*[contains(text(),'results found')]//following::*[@tabindex='-1'])[1]")
	private ExtendedWebElement LeadSearch;

	@FindBy(xpath = "(//*[@id='tableTitle'])[1]")
	private ExtendedWebElement LeadName;

	@FindBy(xpath = "(//*[@id='tableTitle'])[1]//following::p[1]")
	private ExtendedWebElement LeadMobileNumber;

	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	CommonUtilities CU = new CommonUtilities();
	public CreateLead Search(HashMap<String, String> args) throws Exception {

		String actualResult = args.get("ActualResult");
		try {

			if (!args.get("searchLead").equalsIgnoreCase("")) {
				searchLead.click();
				searchLead.type(args.get("searchLead"));
				pause(5);
				CU.takeScreenshot(args, driver, "Search Lead Page");
				actualResult +=  "Search Lead flow done successfully |";
				LOGGER.info("Search Lead flow done successfully");
				if (NoLeadfound.isPresent()) {
					actualResult +=  NoLeadfound.getText() + " |";
					LOGGER.info(NoLeadfound.getText() );
				} else if (LeadSearch.isPresent()) {
					LeadSearch.click();
					waitUntil(ExpectedConditions.elementToBeClickable(LeadDetails.getElement()), EXPLICIT_TIMEOUT);
					ViewMore.click();
					CU.takeScreenshot(args, driver, "Lead Details Page");

					actualResult +=  "display all sources field in lead details|";
					actualResult +=  "Lead Name - " + LeadName.getText() + " , Mobile Number - "
							+ LeadMobileNumber.getText() + " |";
					LOGGER.info("Lead Name - " + LeadName.getText() + " , Mobile Number - "+ LeadMobileNumber.getText());
					Kill.scrollTo();
					CU.takeScreenshot(args, driver, "Lead Details Page");
					actualResult +=  CU.assertEquals("Reminder", Reminder.getText(), args);
					actualResult +=  CU.assertEquals("Meet", Meet.getText(), args);
					actualResult +=  CU.assertEquals("Not Contactable", NotContactable.getText(), args);
					actualResult +=  CU.assertEquals("Kill", Kill.getText(), args);
					actualResult +=   " this all icons is displayed |";
					LOGGER.info("Reminder,Meet,Not Contactable,Kill this all icons are visible");
					// engangement timeline

					EngangementTimeline.scrollTo();
					actualResult +=  CU.assertEquals("Engagement Timeline", EngangementTimeline.getText(), args);
					actualResult +=  CU.assertEquals("Diary", Diary.getText(), args);
					actualResult +=  CU.assertEquals("Timeline", Timeline.getText(), args);
					actualResult +=  CU.assertEquals("Create New Note", CreateNewNote.getText(), args);
					actualResult +=   " this all display on Lead details |";
					LOGGER.info("Engagement Timeline,Diary,Timeline,Create New Note this all icons are visible");
					CU.takeScreenshot(args, driver, "Lead Details Page");

				}

			}

			if (args.get("Microphone_Flag").equalsIgnoreCase("Yes")) {
				searchLead.click();
				CU.takeScreenshot(args, driver, "Search Lead Page");
				MicrophoneBtn.isPresent();
				actualResult +=  "Microphone btn is present | ";
				LOGGER.info("Microphone btn is present");
				MicrophoneBtn.click();
				CU.takeScreenshot(args, driver, "Search Lead Microphone Page");
				actualResult +=  "Microphone btn is able to click and get value on search lable as "
						+ searchingLead.getAttribute("value") + " | ";
				LOGGER.info("Microphone btn is able to click and get value on search lable as "+ searchingLead.getAttribute("value"));
			}
			args.put("ActualResult", actualResult);
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
		return new CreateLead(driver);
	}
}
