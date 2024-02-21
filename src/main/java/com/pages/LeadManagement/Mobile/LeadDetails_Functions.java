package com.pages.LeadManagement.Mobile;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.utils.CommonUtilities;
import com.common.utils.CommonWebUtilities;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

public class LeadDetails_Functions extends AbstractPage {

	public LeadDetails_Functions(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[@text()='Diary']")
	private ExtendedWebElement Diary;

	@FindBy(xpath = "//*[@text()='Create New Note']")
	private ExtendedWebElement CreateNewNote;

	@FindBy(xpath = "//*[@text()='Title']")
	private ExtendedWebElement Title;

	@FindBy(xpath = "//*[@text()='Date']")
	private ExtendedWebElement Date;

	@FindBy(xpath = "//*[@text()='Note']")
	private ExtendedWebElement Note;

	@FindBy(xpath = "//*[@text()='Timeline']")
	private ExtendedWebElement Timeline;

	@FindBy(xpath = "//*[@id='save']")
	private ExtendedWebElement Save;

	@FindBy(xpath = "//*[@id='Close']")
	private ExtendedWebElement closeBtn;

	@FindBy(xpath = "//*[@text()='Select']")
	private ExtendedWebElement SelectReasonDrodown;

	@FindBy(xpath = "//*[@text()='Yes']")
	private ExtendedWebElement YesBtn;

	@FindBy(xpath = "//*[@text()='Not Contactable']")
	private ExtendedWebElement NonContactable;

	@FindBy(xpath = "//*[@text()='No']")
	private ExtendedWebElement NoBtn;

	@FindBy(xpath = "(//*[@class='MuiChip-label MuiChip-labelMedium css-11lqbxm'])[1]")
	private ExtendedWebElement InProgress;

	@FindBy(xpath = "//*[@text()='NOT CONTACTABLE']")
	private ExtendedWebElement NonContactableStatus;

	@FindBy(xpath = "//*[@text()='KILL']")
	private ExtendedWebElement KillStatus;

	@FindBy(xpath = "//*[@text()='Not reachable']")
	private ExtendedWebElement NotReachable;

	@FindBy(xpath = "//*[@text()='Invalid phone number']")
	private ExtendedWebElement InvalidPhoneNumber;

	@FindBy(xpath = "//*[@text()='Others']")
	private ExtendedWebElement Others;

	@FindBy(xpath = "//*[@text()='Comments']")
	private ExtendedWebElement Comments;

	@FindBy(xpath = "(//div[@class='MuiStack-root css-1hyoz7m'])[2]//button")
	private ExtendedWebElement Kebab_Menu;

	@FindBy(xpath = "//*[@text()='View Source']")
	private ExtendedWebElement ViewSource_History;

	@FindBy(xpath = "//*[@text()='Source']")
	private ExtendedWebElement Source;

	@FindBy(xpath = "//*[@text()='REFERRAL']")
	private ExtendedWebElement RefferalSource;

	@FindBy(xpath = "//*[@text()='Select referred by']")
	private ExtendedWebElement RefferalText;

	@FindBy(xpath = "//*[@text()='Referral for the lead has been added successfully!']")
	private ExtendedWebElement Refferal_successfulText;

	@FindBy(xpath = "//*[@text()='Select referred by']//following::input[@id='fullWidth']")
	private ExtendedWebElement SearchRefferal;

	@FindBy(xpath = "//*[@text()='Select referred by']//following::*[@tabindex='-1']")
	private ExtendedWebElement RefferalLead;

	@FindBy(xpath = "//*[@id='share-profile']")
	private ExtendedWebElement Done;

	@FindBy(xpath = "//*[@text()='Kill']")
	private ExtendedWebElement KillBtn;

	@FindBy(xpath = "//*[@text()='Policy is already purchased']")
	private ExtendedWebElement Policypurchased;

	@FindBy(xpath = "//*[@text()='Customer is not interested']")
	private ExtendedWebElement CustomerNotInterested;

	@FindBy(xpath = "//button[@text()='Start Journey']")
	private ExtendedWebElement StartJourney;

	@FindBy(xpath = "//*[contains(text(),'Do you want to')]")
	private ExtendedWebElement Popup_Text;

	@FindBy(xpath = "//*[@text()='Select']")
	private ExtendedWebElement Select_Response;

	@FindBy(xpath = "//*[@text()='Later']")
	private ExtendedWebElement LaterBtn;

	@FindBy(xpath = "//button[@text()='Proceed']")
	private ExtendedWebElement Proceed;

	@FindBy(xpath = "//button[@text()='Cancel']")
	private ExtendedWebElement Cancel;

	@FindBy(xpath = "//*[@text()='Full Name']//following::input[1]")
	private ExtendedWebElement Lead_FullName;

	@FindBy(xpath = "//*[@text()='Phone']//following::input[1]")
	private ExtendedWebElement Lead_Phone;

	@FindBy(xpath = "//*[@text()='Reason']//following::input[1]")
	private ExtendedWebElement Reason;

	@FindBy(xpath = "//*[contains(text(),'successfully')]")
	private ExtendedWebElement Successfull_Popup_text;

	@FindBy(xpath = "//button[@text()='Lead Details']")
	private ExtendedWebElement LeadDetailsBtn;

	@FindBy(xpath = "//*[@text()='Create Lead']")
	private ExtendedWebElement CreateNewLead;

	@FindBy(xpath = "(//*[@class='MuiChip-label MuiChip-labelMedium css-11lqbxm'])[2]")
	private ExtendedWebElement LeadStatus2;

	@FindBy(xpath = "(//*[@class='MuiChip-label MuiChip-labelMedium css-11lqbxm'])[1]")
	private ExtendedWebElement LeadStatus1;

	@FindBy(xpath = "//button[@text()='Lead Details']//following::*[@id='tableTitle'][1]")
	private ExtendedWebElement LeadName;

	@FindBy(xpath = "//*[contains(text(),'Do you want to')]//following::button[1]")
	private ExtendedWebElement CrossBtn;

	@FindBy(xpath = "//*[@class='MuiBox-root css-xi606m']")
	private ExtendedWebElement KillPopup_text;

	@FindBy(xpath = "//*[@class='MuiTypography-root MuiTypography-body1 css-yw5m4d']")
	private ExtendedWebElement OfferNdRecommHeading;

	@FindBy(xpath = "//*[@class='MuiBox-root css-1jq8sgq']")
	private ExtendedWebElement OfferBtn;

	@FindBy(xpath = "(//*[@class='MuiStack-root css-1hyoz7m'])[1]")
	private ExtendedWebElement Offer_HeadDate;

	@FindBy(xpath = "(//*[@class='MuiStack-root css-1hyoz7m'])[2]")
	private ExtendedWebElement Offer_DotsIcon;

	@FindBy(xpath = "(//*[@class='MuiBox-root css-i9gxme'])[1]")
	private ExtendedWebElement Offer_InnerHeader1;

	@FindBy(xpath = "(//*[@text()='Campaign Offer'])[1]")
	private ExtendedWebElement Wealth_CampaignOffer;

	@FindBy(xpath = "(//*[@text()='Campaign Offer'])[2]")
	private ExtendedWebElement Savings_CampaignOffer;

	@FindBy(xpath = "(//*[@text()='Campaign Offer'])[3]")
	private ExtendedWebElement Health_CampaignOffer;

	@FindBy(xpath = "(//*[@text()='Campaign Offer'])[4]")
	private ExtendedWebElement Retirement_CampaignOffer;

	@FindBy(xpath = "(//*[@text()='Wealth']//following::button[@text()='View More'][1])[1]")
	private ExtendedWebElement Wealth_ViewMore;

	@FindBy(xpath = "(//*[@text()='Savings']//following::button[@text()='View More'][1])[1]")
	private ExtendedWebElement Savings_ViewMore;

	@FindBy(xpath = "(//*[@text()='Health']//following::button[@text()='View More'][1])[1]")
	private ExtendedWebElement Health_ViewMore;

	@FindBy(xpath = "(//*[@text()='Retirement']//following::button[@text()='View More'][1])[1]")
	private ExtendedWebElement Retirement_ViewMore;

	@FindBy(xpath = "(//*[@text()='View Less'])[1]")
	private ExtendedWebElement ViewLess;

	@FindBy(xpath = "(//*[@text()='Sum Assured']//following::p[1])[1]")
	private ExtendedWebElement WealthSumAssured;

	@FindBy(xpath = "(//*[@text()='Premium Amt.']//following::p[1])[1]")
	private ExtendedWebElement WealthPremiumAMT;

	@FindBy(xpath = "//*[@text()='Sum Assured']//following::p[1]")
	private List<ExtendedWebElement> SumAssured;

	@FindBy(xpath = "(//*[@text()='Maturity Amt.']//following::p[1])[1]")
	private ExtendedWebElement MaturityAMT;

	@FindBy(xpath = "//*[@text()='Premium Amt.']//following::p[1]")
	private List<ExtendedWebElement> PremiumAMT;

	@FindBy(xpath = "(//*[@class='MuiTypography-root MuiTypography-body1 css-1d6au76'])[1]")
	private ExtendedWebElement WealthValidDate;

	@FindBy(xpath = "(//*[@alt='ilterIcon'])[1]")
	private ExtendedWebElement WealthCopyIcon;

	@FindBy(xpath = "(//*[@alt='ShareIcon'])[1]")
	private ExtendedWebElement WealthShareIcon;

	@FindBy(xpath = "//*[@alt='clockIcon']")
	private ExtendedWebElement WealthclockIcon;

	@FindBy(xpath = "//*[@class='MuiTypography-root MuiTypography-body1 css-1d6au76']")
	private List<ExtendedWebElement> ValidDate;

	@FindBy(xpath = "//*[@alt='ilterIcon']")
	private List<ExtendedWebElement> CopyIcon;

	@FindBy(xpath = "//*[@class='Toastify__toast-body']")
	private ExtendedWebElement CopyIconMsg;

	@FindBy(xpath = "//*[@alt='ShareIcon']")
	private List<ExtendedWebElement> ShareIcon;

	@FindBy(xpath = "//*[@alt='clockIcon']")
	private List<ExtendedWebElement> clockIcon;

	@FindBy(xpath = "//*[@text()='Campaign Offer']")
	private ExtendedWebElement HistoryBackBtn;

	@FindBy(xpath = "(//input[@class='PrivateSwitchBase-input css-1m9pwf3'])[1]")
	private ExtendedWebElement Select_WealthProduct;

	@FindBy(xpath = "(//input[@class='PrivateSwitchBase-input css-1m9pwf3'])[2]")
	private ExtendedWebElement Select_SavingsProduct;

	@FindBy(xpath = "(//input[@class='PrivateSwitchBase-input css-1m9pwf3'])[3]")
	private ExtendedWebElement Select_HealthProduct;

	@FindBy(xpath = "(//input[@class='PrivateSwitchBase-input css-1m9pwf3'])[4]")
	private ExtendedWebElement Select_RetirementProduct;

	@FindBy(xpath = "//input[@id='fullWidth']")
	private ExtendedWebElement SearchAnything;

	@FindBy(xpath = "//*[@text()='Source']//following::div[1]")
	private ExtendedWebElement SourceHistory_Cross;

	CommonUtilities CU = new CommonUtilities();
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public void CancelPopup(HashMap<String, String> args) throws Exception {
		String actualResult = args.get("ActualResult");
		Cancel.click();
		actualResult += " cancel page and popup get closed | ";
		LOGGER.info("cancel page and popup get closed");
		args.put("ActualResult", actualResult);
	}

	public void CrossPopup(HashMap<String, String> args) throws Exception {
		String actualResult = args.get("ActualResult");
		CrossBtn.click();
		actualResult += " cross(x) Icon page and popup get closed | ";
		LOGGER.info("cross(x) Icon page and popup get closed");
		args.put("ActualResult", actualResult);
	}
	
	public void Offer(HashMap<String, String> args) throws Exception {

		String actualResult = args.get("ActualResult");
		try {
			
			OfferNdRecommHeading.isVisible();
			LOGGER.info("Offer btn is visible");
			actualResult += CU.assertEquals("Offers & Recommendations", OfferNdRecommHeading.getText(), args);
			CU.takeScreenshot(args, driver, "Offers And Recommendation Page");

			OfferBtn.click();
			CU.takeScreenshot(args, driver, "Offers Available Page");
			actualResult += CU.assertEquals(Offer_HeadDate.getText(), Offer_HeadDate.getText(), args);
			LOGGER.info("Date - "+Offer_HeadDate.getText());

			if (Offer_DotsIcon.isPresent()) {
				actualResult += "Dot Icon button is present |";
				LOGGER.info("Dot Icon button is present");
			} else {
				actualResult += "*** Dot Icon Button not present ***|";
				LOGGER.info("*** Dot Icon Button not present ***");
				args.put("status", "Fail");
				throw new Exception("Dot Icon Button not present | ");
			}

			actualResult += CU.assertEquals("Offers Available", Offer_InnerHeader1.getText(), args);
			Offer_InnerHeader1.scrollTo();

			Wealth_ViewMore.scrollTo();
			Wealth_ViewMore.click();
			Wealth_CampaignOffer.scrollTo();
			actualResult += CU.assertEquals("Campaign Offer", Wealth_CampaignOffer.getText(), args);
			CU.takeScreenshot(args, driver, "Wealth Campaigns Offers Available Page");
			LOGGER.info("Wealth Campaigns Offers");
			String OfferName = driver
					.findElement(By.xpath("(//*[text()='Campaign Offer'])[1]//following::p[1]")).getText();
			actualResult += " Offer Name - " + OfferName + " | ";
			LOGGER.info(" Offer Name - " + OfferName);
			String ProductName = driver
					.findElement(By.xpath("(//*[text()='Campaign Offer'])[1]//following::p[2]")).getText();
			actualResult += " Product Name - " + ProductName + " | ";
			LOGGER.info(" Product Name - " + ProductName);
			actualResult += " Sum Assured - " + WealthSumAssured.getText() + " | ";
			LOGGER.info(" Sum Assured - " + WealthSumAssured.getText());
			actualResult += " Premium Amt - " + WealthPremiumAMT.getText() + " | ";
			LOGGER.info(" Premium Amt - " + WealthPremiumAMT.getText());
			actualResult += " Maturity Amt - " + MaturityAMT.getText() + " | ";
			LOGGER.info(" Maturity Amt - " + MaturityAMT.getText());
			actualResult += " offers Valid date - " + WealthValidDate.getText() + " | ";
			LOGGER.info(" offers Valid date - " + WealthValidDate.getText());
			WealthCopyIcon.click();
			actualResult += CU.assertEquals("Campaign Offer details copied to clipboard", CopyIconMsg.getText(), args);
			CU.takeScreenshot(args, driver, "Offers copy icon Page");
			LOGGER.info(CopyIconMsg.getText());
			WealthclockIcon.click();
			CU.takeScreenshot(args, driver, "Offers History Page");
			actualResult += " click on Clock Icon btn | ";
			LOGGER.info("click on Clock Icon btn");
			HistoryBackBtn.click();
			CU.takeScreenshot(args, driver, "Offers History back Page");
			actualResult += " click on Back btn and back to offers page| ";
			LOGGER.info("click on Back btn and back to offers page");
			Wealth_ViewMore.scrollTo();
			Wealth_ViewMore.click();
			Wealth_CampaignOffer.scrollTo();
			
			WealthShareIcon.isClickable();
			CU.takeScreenshot(args, driver, "Offers Share Icon Page");
			actualResult += " click on Share icon btn ";
			LOGGER.info("click on Share icon btn");
			ViewLess.click();
			CU.takeScreenshot(args, driver, "Offers View Less Page");
			actualResult += " click on View less btn | ";
			LOGGER.info("click on View less btn");
			if (!args.get("OfferProductName").equalsIgnoreCase("")) {

				switch (args.get("OfferProductName")) {
				case "Wealth":
					Select_WealthProduct.scrollTo();
					Select_WealthProduct.click();
					CU.takeScreenshot(args, driver, "Select Wealth Product Page");
					actualResult += "Select Wealth Product | ";
					LOGGER.info("Select Wealth Product");
					break;
					
				case "Savings":
					Select_SavingsProduct.scrollTo();
					Select_SavingsProduct.click();
					CU.takeScreenshot(args, driver, "Select Savings Product Page");
					actualResult += "Select Savings Product | ";
					LOGGER.info("Select Savings Product");
					break;
				case "Health":
					Select_HealthProduct.scrollTo();
					Select_HealthProduct.click();
					CU.takeScreenshot(args, driver, "Select Health Product Page");
					actualResult += "Select Health Product | ";
					LOGGER.info("Select Health Product");
					break;
					
				case "Retirement":
					Select_RetirementProduct.scrollTo();
					Select_RetirementProduct.click();
					CU.takeScreenshot(args, driver, "Select Retirement Product Page");
					actualResult += "Select Retirement Product | ";
					LOGGER.info("Select Retirement Product");
					break;
				}
				
				StartJourney.scrollTo();
				StartJourney.click();
				CU.takeScreenshot(args, driver, "Start journey Page");
				actualResult += "Start journey button is enable | ";
				LOGGER.info("Start journey button is enable");
			}
			
			Offer_HeadDate.scrollTo();
			Offer_HeadDate.click();
			CU.takeScreenshot(args, driver, "Back to lead Details Page");
			actualResult += "Back to lead details page | ";
			LOGGER.info("Back to lead details page");
			
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
	}
	
	/*public void OfferProducts(HashMap<String, String> args,int i) throws Exception {

		String actualResult = args.get("ActualResult");
		
		if (i == 1) {
			Wealth_ViewMore.scrollTo();
			Wealth_ViewMore.click();
			Wealth_CampaignOffer.scrollTo();
			actualResult += 
					+ CU.assertEquals("Campaign Offer", Wealth_CampaignOffer.getText(), args);
			CU.takeScreenshot(args, driver, "Wealth Campaigns Offers Available Page");

		}
		if (i == 2) {
			Savings_ViewMore.scrollTo();
			Savings_ViewMore.click();
			Savings_CampaignOffer.scrollTo();
			actualResult += 
					+ CU.assertEquals("Campaign Offer", Savings_CampaignOffer.getText(), args);
			CU.takeScreenshot(args, driver, "Savings Campaigns Offers Available Page");

		}
		if (i == 3) {
			Health_ViewMore.scrollTo();
			Health_ViewMore.click();
			Health_CampaignOffer.scrollTo();
			actualResult += 
					+ CU.assertEquals("Campaign Offer", Health_CampaignOffer.getText(), args);
			CU.takeScreenshot(args, driver, "Health Campaigns Offers Available Page");

		}
		if (i == 4) {
			Retirement_ViewMore.scrollTo();
			Retirement_ViewMore.click();
			Retirement_CampaignOffer.scrollTo();
			actualResult += 
					+ CU.assertEquals("Campaign Offer", Retirement_CampaignOffer.getText(), args);
			CU.takeScreenshot(args, driver, "Retirement Campaigns Offers Available Page");

		}
	}
	public void Offer(HashMap<String, String> args) throws Exception {

		String actualResult = args.get("ActualResult");
		try {

			OfferNdRecommHeading.isVisible();
			actualResult += 
					+ CU.assertEquals("Offers & Recommendations", OfferNdRecommHeading.getText(), args);
			CU.takeScreenshot(args, driver, "Offers And Recommendation Page");

			OfferBtn.click();
			CU.takeScreenshot(args, driver, "Offers Available Page");
			actualResult += 
					+ CU.assertEquals(Offer_HeadDate.getText(), Offer_HeadDate.getText(), args);

			if (Offer_DotsIcon.isPresent()) {
				actualResult += "Dot Icon button is present";
			} else {
				throw new Exception("Dot Icon Button not present | ");
			}

			actualResult += 
					+ CU.assertEquals("Offers Available", Offer_InnerHeader1.getText(), args);
			Offer_InnerHeader1.scrollTo();
			for (int i = 1; i <= 4; i++) {
				
				OfferProducts(args,i);
				
				String OfferName = driver
						.findElement(By.xpath("(//*[text()='Campaign Offer'])[" + i + "]//following::p[1]")).getText();
				actualResult += i + " Offer Name - " + OfferName + " | ";

				String ProductName = driver
						.findElement(By.xpath("(//*[text()='Campaign Offer'])[" + i + "]//following::p[2]")).getText();
				actualResult += i + " Product Name - " + ProductName + " | ";

				actualResult += i + " Sum Assured - " + SumAssured.get(i).getText() + " | ";
				actualResult += i + " Premium Amt - " + PremiumAMT.get(i).getText() + " | ";
				//actualResult += i + " Maturity Amt - " + MaturityAMT.get(i).getText() + " | ";
				actualResult += i + " offers Valid date - " + ValidDate.get(i).getText() + " | ";

				CopyIcon.get(i).click();
				actualResult += 
						+ CU.assertEquals("Campaign Offer details copied to clipboard", CopyIconMsg.getText(), args);
				CU.takeScreenshot(args, driver, "Offers copy icon Page " + i + "");
				
				clockIcon.get(i).click();
				CU.takeScreenshot(args, driver, "Offers History Page " + i + "");
				actualResult += i + " click on Clock Icon btn | ";
				HistoryBackBtn.click();
				CU.takeScreenshot(args, driver, "Offers History back Page " + i + "");
				actualResult += i + " click on Back btn and back to offers page| ";

				OfferProducts(args,i);

				ShareIcon.get(i).isClickable();
				CU.takeScreenshot(args, driver, "Offers Share Icon Page " + i + "");
				actualResult += i + " click on Share icon btn ";
				
				ViewLess.click();
				CU.takeScreenshot(args, driver, "Offers View Less Page " + i + "");
				actualResult += i + " click on View less btn | ";
			}

			if (!args.get("OfferProductName").equalsIgnoreCase("")) {

				switch (args.get("OfferProductName")) {
				case "Wealth":
					Select_WealthProduct.scrollTo();
					Select_WealthProduct.click();
					CU.takeScreenshot(args, driver, "Select Wealth Product Page");
					actualResult += "Select Wealth Product | ";
					break;
					
				case "Savings":
					Select_SavingsProduct.scrollTo();
					Select_SavingsProduct.click();
					CU.takeScreenshot(args, driver, "Select Savings Product Page");
					actualResult += "Select Savings Product | ";
					break;
				case "Health":
					Select_HealthProduct.scrollTo();
					Select_HealthProduct.click();
					CU.takeScreenshot(args, driver, "Select Health Product Page");
					actualResult += "Select Health Product | ";
					break;
					
				case "Retirement":
					Select_RetirementProduct.scrollTo();
					Select_RetirementProduct.click();
					CU.takeScreenshot(args, driver, "Select Retirement Product Page");
					actualResult += "Select Retirement Product | ";
					break;
				}
				
				StartJourney.scrollTo();
				StartJourney.click();
				CU.takeScreenshot(args, driver, "Start journey Page");
				actualResult += "Start journey button is enable | ";
			}
			
			Offer_HeadDate.scrollTo();
			Offer_HeadDate.click();
			CU.takeScreenshot(args, driver, "Back to lead Details Page");
			actualResult += "Back to lead details page | ";
			
			args.put("ActualResult", actualResult);
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println(e.getMessage());
			actualResult += e.getMessage();
			args.put("ActualResult", actualResult);
			CU.takeScreenshot(args, driver, "Error Page");

			if (args.get("Test Case Type").equalsIgnoreCase("Negative")) {
				args.put("status", "Pass");
			} else {
				args.put("status", "Fail");
			}
		}
	}*/

	public void StartJourney(HashMap<String, String> args) throws Exception {

		String actualResult = args.get("ActualResult");
		try {

			StartJourney.scrollTo();
			actualResult += CU.assertEquals("Start Journey", StartJourney.getText(), args);
			CU.takeScreenshot(args, driver, "Lead Details StartJourney Page");
			StartJourney.click();
			actualResult += CU.assertEquals("Do you want to provide the referral?",
					Popup_Text.getText(), args);
			LOGGER.info("Click on start journey btn and Yes,No,Later options are available");
			CU.takeScreenshot(args, driver, "StartJourney Popup Page");
			Select_Response.click();
			CU.takeScreenshot(args, driver, "Select Response Page");
			actualResult += CU.assertEquals("Yes", YesBtn.getText(), args);
			actualResult += CU.assertEquals("No", NoBtn.getText(), args);
			actualResult += CU.assertEquals("Later", LaterBtn.getText(), args);

			if (args.get("Response").equalsIgnoreCase("Yes")) {
				YesBtn.click();
				CU.takeScreenshot(args, driver, "Select Response Yes Page");
				LOGGER.info("Select response yes");
				if (args.get("CancelFlow_Flag").equalsIgnoreCase("Yes")) {
					CancelPopup(args);
					actualResult = args.get("ActualResult");
					CU.takeScreenshot(args, driver, "StartJourney Page");
					StartJourney.click();
					CU.takeScreenshot(args, driver, "StartJourney Popup Page");
					actualResult += " after cancel page again started start journey flow | ";
					LOGGER.info("after cancel page again started start journey flow");
					Select_Response.click();
					CU.takeScreenshot(args, driver, "Select Response Page");
					YesBtn.click();
					CU.takeScreenshot(args, driver, "Select Response Yes Page");
				}
				if (args.get("CrossFlow_Flag").equalsIgnoreCase("Yes")) {
					CrossPopup(args);
					actualResult = args.get("ActualResult");
					CU.takeScreenshot(args, driver, "StartJourney Page");
					StartJourney.click();
					CU.takeScreenshot(args, driver, "StartJourney Popup Page");
					actualResult += " after Cross page again started start journey flow | ";
					LOGGER.info("after Cross page again started start journey flow");
					Select_Response.click();
					CU.takeScreenshot(args, driver, "Select Response Page");
					YesBtn.click();
					CU.takeScreenshot(args, driver, "Select Response Yes Page");
				}
				String Fullname = CommonWebUtilities.RandomString_CapitalLetters(1)
						+ CommonWebUtilities.RandomString_SmallLetters(4);
				String mobileNumber = CommonWebUtilities.RandomstringNumber(10);
				Lead_FullName.type(Fullname);
				Lead_Phone.type(mobileNumber);
				CU.takeScreenshot(args, driver, "Response Yes add new lead details Page");
		
				actualResult += "Select Response Yes Add new details as FullName - " + Fullname
						+ " And Phone Number - " + mobileNumber + " | ";
				LOGGER.info("Select Response Yes Add new details as FullName - " + Fullname+ " And Phone Number - " + mobileNumber);
				Proceed.click();
				CU.takeScreenshot(args, driver, "Popup Page");
				actualResult += CU.assertEquals("Lead has been added successfully!",
						Successfull_Popup_text.getText(), args);

				actualResult += "After proceed popup page is visible and successfull msg as - "
						+ Successfull_Popup_text.getText() + " | ";
				LOGGER.info(Successfull_Popup_text.getText());
				closeBtn.click();
				CU.takeScreenshot(args, driver, "Done Start Journey Page");
				LeadDetailsBtn.scrollTo();
				CU.takeScreenshot(args, driver, "Done Start Journey Page");
				String LeadNames = LeadName.getText().trim();
				LeadDetailsBtn.click();
				actualResult += " back to list page | ";
				LOGGER.info("back to list page");
				waitUntil(ExpectedConditions.elementToBeClickable(CreateNewLead.getElement()), EXPLICIT_TIMEOUT);
				CU.takeScreenshot(args, driver, "New lead in List Page");

				driver.findElement(By.xpath("(//*[normalize-space(text())='" + Fullname + "'])[1]")).click();
				actualResult += " click on lead created for start journey flow | ";
				LOGGER.info("click on lead created for start journey flow");
				actualResult += CU.assertEquals("REFERRAL", LeadStatus2.getText(), args);
				actualResult += CU.assertEquals("NEW", LeadStatus1.getText(), args);

				CU.takeScreenshot(args, driver, "New lead status Page");
				actualResult += "new Lead Status as " + LeadStatus1.getText() + " and "
						+ LeadStatus2.getText() + " | ";

				LeadDetailsBtn.click();
				actualResult += " back to again list page | ";
				LOGGER.info("back to again list page");
				waitUntil(ExpectedConditions.elementToBeClickable(CreateNewLead.getElement()), EXPLICIT_TIMEOUT);
				CU.takeScreenshot(args, driver, "New lead in List Page");

				driver.findElement(By.xpath("(//*[normalize-space(text())='" + LeadNames + "'])[1]")).click();
				actualResult += " click on old lead | ";
				LOGGER.info("click on old lead");
				actualResult += CU.assertEquals("SELF CREATED", LeadStatus2.getText(), args);
				actualResult += CU.assertEquals("START_JOURNEY", LeadStatus1.getText(), args);

				CU.takeScreenshot(args, driver, "old lead status Page");
				actualResult += "old Lead Status as " + LeadStatus1.getText() + " and "
						+ LeadStatus2.getText() + " | ";

				SourceHistory(args);
				actualResult = args.get("ActualResult");
			}

			if (args.get("Response").equalsIgnoreCase("No")) {
				NoBtn.click();
				CU.takeScreenshot(args, driver, "Select Response No Page");
				LOGGER.info("Select response No");
				if (args.get("CancelFlow_Flag").equalsIgnoreCase("Yes")) {
					CancelPopup(args);
					actualResult = args.get("ActualResult");
					CU.takeScreenshot(args, driver, "StartJourney Page");
					StartJourney.click();
					CU.takeScreenshot(args, driver, "StartJourney Popup Page");
					actualResult += " after cancel page again started start journey flow | ";
					LOGGER.info("after cancel page again started start journey flow");
					Select_Response.click();
					CU.takeScreenshot(args, driver, "Select Response Page");
					NoBtn.click();
					CU.takeScreenshot(args, driver, "Select Response No Page");
				}
				if (args.get("CrossFlow_Flag").equalsIgnoreCase("Yes")) {
					CrossPopup(args);
					actualResult = args.get("ActualResult");
					CU.takeScreenshot(args, driver, "StartJourney Page");
					StartJourney.click();
					CU.takeScreenshot(args, driver, "StartJourney Popup Page");
					actualResult += " after Cross icon flow again started start journey flow | ";
					LOGGER.info("after Cross icon flow again started start journey flow");
					Select_Response.click();
					CU.takeScreenshot(args, driver, "Select Response Page");
					NoBtn.click();
					CU.takeScreenshot(args, driver, "Select Response No Page");
				}
				Reason.type(args.get("Reason"));
				actualResult += "Select Response No and add reason as - " + args.get("Reason") + " | ";
				
				CU.takeScreenshot(args, driver, "Add Reason Page");
				Proceed.click();
				CU.takeScreenshot(args, driver, "Start journey After Proceed Page");
				LOGGER.info("Proceed Start journey flow");
				LeadDetailsBtn.scrollTo();
				CU.takeScreenshot(args, driver, "Done Start Journey Page");
				actualResult += "old Lead Status as " + LeadStatus1.getText() + " and "
						+ LeadStatus2.getText() + " | ";

			}
			if (args.get("Response").equalsIgnoreCase("Later")) {
				LaterBtn.click();
				CU.takeScreenshot(args, driver, "Select Response Later Page");
				LOGGER.info("Select response Later");
				if (args.get("CancelFlow_Flag").equalsIgnoreCase("Yes")) {
					CancelPopup(args);
					actualResult = args.get("ActualResult");
					CU.takeScreenshot(args, driver, "StartJourney Page");
					StartJourney.click();
					CU.takeScreenshot(args, driver, "StartJourney Popup Page");
					actualResult += " after cancel page again started start journey flow | ";
					LOGGER.info("after cancel page again started start journey flow ");
					Select_Response.click();
					CU.takeScreenshot(args, driver, "Select Response Page");
					LaterBtn.click();
					CU.takeScreenshot(args, driver, "Select Response Later Page");
				}
				if (args.get("CrossFlow_Flag").equalsIgnoreCase("Yes")) {
					CrossPopup(args);
					actualResult = args.get("ActualResult");
					CU.takeScreenshot(args, driver, "StartJourney Page");
					StartJourney.click();
					CU.takeScreenshot(args, driver, "StartJourney Popup Page");
					actualResult += " after Cross icon flow again started start journey flow | ";
					LOGGER.info("after Cross icon flow again started start journey flow");
					Select_Response.click();
					CU.takeScreenshot(args, driver, "Select Response Page");
					LaterBtn.click();
					CU.takeScreenshot(args, driver, "Select Response Later Page");
				}
				actualResult += "Select Response Later | ";
				
				Proceed.click();
				CU.takeScreenshot(args, driver, "Start journey After Proceed Page");
				LOGGER.info("Proceed start journey flow");
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
	}

	public void ReferralSources(HashMap<String, String> args) throws Exception {

		String actualResult = args.get("ActualResult");
		try {
			actualResult += CU.assertEquals("REFERRAL", RefferalSource.getText(), args);

			actualResult += " Source as " + RefferalSource.getText() + " | ";
			CU.takeScreenshot(args, driver, "Lead Details Referral Sources Page");
			RefferalSource.click();

			actualResult += CU.assertEquals("Select referred by", RefferalText.getText(), args);

			CU.takeScreenshot(args, driver, "Search Referral Sources Page");

			if (!args.get("SearchRefferal_Lead").equalsIgnoreCase("")) {
				SearchRefferal.type(args.get("SearchRefferal_Lead"));
				CU.takeScreenshot(args, driver, "Search Referral Sources Page");

				if (RefferalLead.isPresent()) {
					CU.takeScreenshot(args, driver, "Search Referral Sources Page");
					RefferalLead.click();

					actualResult += CU.assertEquals("Referral for the lead has been added successfully!",Refferal_successfulText.getText(), args);

					actualResult += " popup msg - " + Refferal_successfulText.getText() + " | ";

					CU.takeScreenshot(args, driver, "Referral Sources successfull Lead Page");
					Done.click();
					CU.takeScreenshot(args, driver, "Referral Sources successfull Lead Page");

					actualResult +=CU.assertEquals("IN PROGRESS", LeadStatus1.getText(), args);
					actualResult += CU.assertEquals("REFERRAL", LeadStatus2.getText(), args);
					
					actualResult += " After referral flow is done status change as - "
							+ LeadStatus2.getText() + " | ";
					LOGGER.info("Referral flow done successfully");
					SourceHistory(args);
				}
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
	}

	public void Sources(HashMap<String, String> args) throws Exception {

		String actualResult = args.get("ActualResult");
		try {
			for (int i = 1; i <= 6; i++) {

				if (!args.get("Sources" + i + "").equalsIgnoreCase("")) {

					driver.findElement(By.xpath("//*[contains(text(),'" + args.get("Sources" + i + "") + "')]"))
							.click();
					pause(4);
					actualResult += "click source +" + args.get("Sources" + i + "") + " |";
					LOGGER.info("click source = " + args.get("Sources" + i + "") );
				}
			}
			CU.takeScreenshot(args, driver, "Lead Details Sources Page");

			if (args.get("UnclickSources").equalsIgnoreCase("Yes")) {
				for (int i = 1; i <= 6; i++) {

					if (!args.get("Sources" + i + "").equalsIgnoreCase("")) {

						driver.findElement(By.xpath("//*[contains(text(),'" + args.get("Sources" + i + "") + "')]"))
								.click();
						pause(4);
						actualResult += "Unclick source = " + args.get("Sources" + i + "") + " |";
					}
				}
				CU.takeScreenshot(args, driver, "Lead Details unclick Sources Page");

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
	}

	public void Timeline(HashMap<String, String> args) throws Exception {

		String actualResult = args.get("ActualResult");
		try {
			Diary.scrollTo();
			WebElement TimelineScroll = driver.findElement(By.xpath("//button[contains(text(),'Timeline')]"));
			TimelineScroll.click();
			CU.takeScreenshot(args, driver, "Engagement Timeline Page");
			Diary.click();
			TimelineScroll.click();
			WebElement FirstTimeLine = driver.findElement(By.xpath("(//*[@class='MuiBox-root css-k008qs'])[1]"));
			FirstTimeLine.click();
			CU.takeScreenshot(args, driver, "Engagement Timeline Page");
			int TimelineCount = driver.findElements(By.xpath("//*[@class='MuiBox-root css-k008qs']")).size();
			WebElement LastTimeline = driver
					.findElement(By.xpath("(//*[@class='MuiBox-root css-k008qs'])[" + TimelineCount + "]"));
			LastTimeline.click();
			CU.takeScreenshot(args, driver, "Engagement Timeline Page");

			actualResult += "Timeline display|";
			LOGGER.info("Timeline display ");
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
	}

	public void CreateNote(HashMap<String, String> args) throws Exception {

		String actualResult = args.get("ActualResult");

		try {

			Diary.scrollTo();
			if (CreateNewNote.isClickable()) {

				actualResult += CU.assertEquals("Create New Note", CreateNewNote.getText(), args);
			
				actualResult += CreateNewNote.getText() + " display on Lead details |";
				CU.takeScreenshot(args, driver, "Lead Details Page");
				Diary.click();
				CU.takeScreenshot(args, driver, "Engagement Diary Page");

				for (int i = 1; i <= 3; i++) {
					CreateNewNote.scrollTo();
					CU.takeScreenshot(args, driver, "Engagement Diary Page");

					if (!args.get("NewNote_Title" + i + "").equalsIgnoreCase("")) {

						CreateNewNote.scrollTo();
						CreateNewNote.click();
						Title.type(args.get("NewNote_Title" + i + ""));
						Date.click();
						CommonWebUtilities.CalendarDate(driver, args.get("NewNote_Date" + i + ""));
						pause(4);
						Note.type(args.get("NewNote_Note" + i + ""));
						CU.takeScreenshot(args, driver, "Added new Note Page");
						Save.click();
						waitUntil(ExpectedConditions.elementToBeClickable(closeBtn.getElement()), SHORT_TIMEOUT);
						CU.takeScreenshot(args, driver, "created successfully New Note Page");
						closeBtn.click();
						CU.takeScreenshot(args, driver, "created successfully New Note Page");

						actualResult += " Added new note as " + args.get("NewNote_Title" + i + "") + " |";
						LOGGER.info(" Added new note as " + args.get("NewNote_Title" + i + ""));
					}

					if (!args.get("EditNote_Title" + i + "").equalsIgnoreCase("")) {

						CreateNewNote.scrollTo();

						driver.findElement(By.xpath(
								"//*[text()='" + args.get("EditNote_Title" + i + "") + "']//following::button[1]"))
								.click();
						Title.type(args.get("EditNote_Title" + i + ""));
						Date.click();
						CommonWebUtilities.CalendarDate(driver, args.get("EditNote_Date" + i + ""));
						pause(4);
						Note.type(args.get("EditNote_Note" + i + ""));
						CU.takeScreenshot(args, driver, "Edit Note Page");
						Save.click();
						CU.takeScreenshot(args, driver, "Edit Note Page");
						closeBtn.click();
						CU.takeScreenshot(args, driver, "Edit successfully Note Page");

						actualResult += " Edit Note " + args.get("EditNote_Title" + i + "") + " |";
						LOGGER.info(" Edit Note " + args.get("EditNote_Title" + i + ""));
					}

					if (!args.get("ClearNote_Title" + i + "").equalsIgnoreCase("")) {

						CreateNewNote.scrollTo();
						CU.takeScreenshot(args, driver, "Before clearing Note Page");

						// clear btn
						driver.findElement(By.xpath(
								"//*[text()='" + args.get("ClearNote_Title" + i + "") + "']//following::button[2]"))
								.click();
						CU.takeScreenshot(args, driver, "After clearing Note Page");
						actualResult += " Clear Note " + args.get("ClearNote_Title" + i + "") + " |";
						LOGGER.info(" Clear Note " + args.get("ClearNote_Title" + i + ""));
					}

				}
				CU.takeScreenshot(args, driver, "Engagement Diary Page");
				actualResult += "Engagement Diary flow done successfully |";
				LOGGER.info("Engagement Diary flow done successfully");
			} else {
				actualResult += " Not able to add New notes |";
				LOGGER.info("Not able to add New notes");
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
	}

	public void Noncontactable(HashMap<String, String> args) throws Exception {

		String actualResult = args.get("ActualResult");
		try {

			NonContactable.scrollTo();
			CU.takeScreenshot(args, driver, "Lead Details Page");
			NonContactable.click();
			CU.takeScreenshot(args, driver, "NonContactablePopup");
			LOGGER.info("click on Non Contactable");
			SelectReasonDrodown.click();
			CU.takeScreenshot(args, driver, "SelectReasonDrodown");

			actualResult += CU.assertEquals("Not reachable", NotReachable.getText(), args);
			actualResult += CU.assertEquals("Invalid phone number", InvalidPhoneNumber.getText(), args);
			actualResult += CU.assertEquals("Others", Others.getText(), args);
			
			WebElement reasonOfNonContactable = driver
					.findElement(By.xpath("//*[text()='" + args.get("NonContactableReason") + "']"));
			reasonOfNonContactable.click();
			CU.takeScreenshot(args, driver, "reasonOfNonContactable");
			if (args.get("NonContactableReason").equalsIgnoreCase("Others")) {
				Comments.type(args.get("Comments"));
				CU.takeScreenshot(args, driver, "comment in non contactable reason");

			}
			CancelPopup(args);
			actualResult = args.get("ActualResult");
			CU.takeScreenshot(args, driver, "cancel flow");
			actualResult += "select cancel btn |";
			LOGGER.info("select cancel btn");
			LeadDetailsBtn.scrollTo();
			CU.takeScreenshot(args, driver, "After Noncontactable select No Page");
			actualResult += " after Noncontactable select No status remains same | ";
			LOGGER.info("after Noncontactable select No status remains same");
			NonContactable.scrollTo();
			CU.takeScreenshot(args, driver, "Lead Details Page");
			NonContactable.click();
			CU.takeScreenshot(args, driver, "NonContactablePopup");

			SelectReasonDrodown.click();
			CU.takeScreenshot(args, driver, "SelectReasonDrodown");

			WebElement reasonNonContactable = driver
					.findElement(By.xpath("//*[text()='" + args.get("NonContactableReason") + "']"));
			reasonNonContactable.click();
			CU.takeScreenshot(args, driver, "reasonOfNonContactable");
			if (args.get("NonContactableReason").equalsIgnoreCase("Others")) {
				Comments.type(args.get("Comments"));
				CU.takeScreenshot(args, driver, "comment in non contactable reason");

			}
			Proceed.click();
			CU.takeScreenshot(args, driver, "After Proceed Page");

			actualResult += " Select Reason flow done | ";
			LOGGER.info("Proceed non contactable");
			closeBtn.click();
			pause(2);
			CU.takeScreenshot(args, driver, "NonContableSuccessMessage");
			LeadDetailsBtn.scrollTo();
			CU.takeScreenshot(args, driver, "Status Updated as Not contactable Page");
			actualResult += CU.assertEquals("NOT CONTACTABLE", NonContactableStatus.getText(), args);
			
			actualResult += " Status Updated as " + NonContactableStatus.getText() + " |";
			LOGGER.info("Status Updated NOT CONTACTABLE");
			args.put("ActualResult", actualResult);

		} catch (Exception e) {
			e.getStackTrace();
			LOGGER.info(e.getMessage());
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
	}

	public void SourceHistory(HashMap<String, String> args) throws Exception {

		String actualResult = args.get("ActualResult");
		try {

			Kebab_Menu.scrollTo();
			Kebab_Menu.click();
			pause(2);
			CU.takeScreenshot(args, driver, "Kebab Menu Page");
			ViewSource_History.click();
			waitUntil(ExpectedConditions.elementToBeClickable(Source.getElement()), EXPLICIT_TIMEOUT);
			CU.takeScreenshot(args, driver, "Kebab Menu Source history Page");
			actualResult += "In kebab menu source history is available | ";
			LOGGER.info("In kebab menu source history is available");
			SourceHistory_Cross.click();
			args.put("ActualResult", actualResult);

		} catch (Exception e) {
			e.getStackTrace();
			LOGGER.info(e.getMessage());
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
	}

	public void KillSelectReason(HashMap<String, String> args) throws Exception {

		String actualResult = args.get("ActualResult");

		KillBtn.scrollTo();
		CU.takeScreenshot(args, driver, "Lead Details Page");
		KillBtn.click();
		CU.takeScreenshot(args, driver, "KillBtnPopup");
		LOGGER.info("click on kill btn");
		SelectReasonDrodown.click();
		CU.takeScreenshot(args, driver, "SelectReasonDrodown");
		WebElement reasonkill = driver.findElement(By.xpath("//*[text()='" + args.get("KillReason") + "']"));
		reasonkill.click();
		CU.takeScreenshot(args, driver, "reasontokill");
		if (args.get("KillReason").equalsIgnoreCase("Others")) {
			Comments.type(args.get("Comments"));
			CU.takeScreenshot(args, driver, "comment in Kill reason");

		}
		Proceed.click();
		CU.takeScreenshot(args, driver, "After Proceed Page");

		actualResult += " Select Kill Reason flow done | ";
		LOGGER.info("Select Kill Reason flow done");
		args.put("ActualResult", actualResult);
	}

	public void Killflow(HashMap<String, String> args) throws Exception {

		String actualResult = args.get("ActualResult");
		try {

			KillBtn.scrollTo();
			CU.takeScreenshot(args, driver, "Lead Details Page");

			KillBtn.click();
			CU.takeScreenshot(args, driver, "KillBtnPopup");
			SelectReasonDrodown.click();
			CU.takeScreenshot(args, driver, "SelectReasonDrodown");
			actualResult += CU.assertEquals("Policy is already purchased", Policypurchased.getText(), args);
			actualResult += CU.assertEquals("Customer is not interested",
					CustomerNotInterested.getText(), args);
			actualResult += CU.assertEquals("Others", Others.getText(), args);
			
			WebElement reasontokill = driver.findElement(By.xpath("//*[text()='" + args.get("KillReason") + "']"));
			reasontokill.click();
			CU.takeScreenshot(args, driver, "reasontokill");
			if (args.get("KillReason").equalsIgnoreCase("Others")) {
				Comments.type(args.get("Comments"));
				CU.takeScreenshot(args, driver, "comment in Kill reason");

			}
			LOGGER.info("Select Kill Reason");
			CancelPopup(args);
			CU.takeScreenshot(args, driver, "cancel flow");
			actualResult += "click on cancel btn |";
			LOGGER.info("click on cancel btn");
			LeadDetailsBtn.scrollTo();
			CU.takeScreenshot(args, driver, "After select KillReason click No btn Page");
			actualResult += "After select KillReason click on No btn status remains same |";
			LOGGER.info("After select KillReason click on No btn status remains same");
			KillSelectReason(args);

			actualResult += CU.assertEquals("Do you want to provide the referral?",
					Popup_Text.getText(), args);
			
			CU.takeScreenshot(args, driver, "StartJourney Popup Page");
			Select_Response.click();
			CU.takeScreenshot(args, driver, "Select Response Page");
			
			if (args.get("Response").equalsIgnoreCase("Yes")) {
				YesBtn.click();
				CU.takeScreenshot(args, driver, "Select Response Yes Page");
				LOGGER.info("Select Response Yes");
				if (args.get("CancelFlow_Flag").equalsIgnoreCase("Yes")) {
					CancelPopup(args);
					actualResult = args.get("ActualResult");
					CU.takeScreenshot(args, driver, "Kill flow Page");
					KillSelectReason(args);
					actualResult += " after cancel page again started Kill flow | ";
					LOGGER.info("After cancel page again started Kill flow");
					Select_Response.click();
					CU.takeScreenshot(args, driver, "Select Response Page");
					YesBtn.click();
					CU.takeScreenshot(args, driver, "Select Response Yes Page");
				}
				if (args.get("CrossFlow_Flag").equalsIgnoreCase("Yes")) {
					CrossPopup(args);
					actualResult = args.get("ActualResult");
					CU.takeScreenshot(args, driver, "Kill Page");
					KillSelectReason(args);
					actualResult += " after cross icon page again started Kill flow | ";
					LOGGER.info("After cross icon page again started Kill flow");
					Select_Response.click();
					CU.takeScreenshot(args, driver, "Select Response Page");
					YesBtn.click();
					CU.takeScreenshot(args, driver, "Select Response Yes Page");
				}
				String Fullname = CommonWebUtilities.RandomString_CapitalLetters(1)
						+ CommonWebUtilities.RandomString_SmallLetters(4);
				String mobileNumber = CommonWebUtilities.RandomstringNumber(10);
				Lead_FullName.type(Fullname);
				Lead_Phone.type(mobileNumber);
				CU.takeScreenshot(args, driver, "Response Yes add new lead details Page");
				actualResult += "Select Response Yes Add new details as FullName - " + Fullname
						+ " And Phone Number - " + mobileNumber + " | ";
				Proceed.click();
				CU.takeScreenshot(args, driver, "Popup Page");
				LOGGER.info("Proceed Kill flow");
				actualResult += CU.assertEquals(
						"Lead has been killed successfully !\n" + "Referral Lead has been saved successfully.",
						KillPopup_text.getText(), args);
			
				actualResult += "After proceed popup page is visible and successfull msg as - "
						+ KillPopup_text.getText() + " | ";

				closeBtn.click();
				CU.takeScreenshot(args, driver, "Done Kill flow Page");
				LeadDetailsBtn.scrollTo();
				CU.takeScreenshot(args, driver, "Done Kill flow Page");
				String LeadNames = LeadName.getText().trim();
				LeadDetailsBtn.click();
				actualResult += " back to list page | ";
				LOGGER.info("back to list page");
				waitUntil(ExpectedConditions.elementToBeClickable(CreateNewLead.getElement()), EXPLICIT_TIMEOUT);
				CU.takeScreenshot(args, driver, "New lead in List Page");

				driver.findElement(By.xpath("(//*[normalize-space(text())='" + Fullname + "'])[1]")).click();
				actualResult += " click on lead created for start journey flow | ";

				actualResult += CU.assertEquals("REFERRAL", LeadStatus2.getText(), args);
				actualResult += CU.assertEquals("NEW", LeadStatus1.getText(), args);
				
				CU.takeScreenshot(args, driver, "New lead status Page");
				actualResult += "new Lead Status as " + LeadStatus1.getText() + " and "
						+ LeadStatus2.getText() + " | ";
				LOGGER.info("check new lead status");
				LeadDetailsBtn.click();
				actualResult += " back to again list page | ";
				LOGGER.info("back to again list page");
				waitUntil(ExpectedConditions.elementToBeClickable(CreateNewLead.getElement()), EXPLICIT_TIMEOUT);
				CU.takeScreenshot(args, driver, "New lead in List Page");

				driver.findElement(By.xpath("(//*[normalize-space(text())='" + LeadNames + "'])[1]")).click();
				actualResult += " click on old lead | ";
				LOGGER.info("click on old lead");
				actualResult += CU.assertEquals("SELF CREATED", LeadStatus2.getText(), args);
				actualResult += CU.assertEquals("KILL", LeadStatus1.getText(), args);
				
				CU.takeScreenshot(args, driver, "old lead status Page");
				actualResult += "old Lead Status as " + LeadStatus1.getText() + " and "
						+ LeadStatus2.getText() + " | ";
				LOGGER.info("check status");
				SourceHistory(args);

			}

			if (args.get("Response").equalsIgnoreCase("No")) {
				NoBtn.click();
				CU.takeScreenshot(args, driver, "Select Response No Page");
				LOGGER.info("Select Response No");
				if (args.get("CancelFlow_Flag").equalsIgnoreCase("Yes")) {
					CancelPopup(args);
					actualResult = args.get("ActualResult");
					CU.takeScreenshot(args, driver, "Kill flow Page");
					KillSelectReason(args);
					actualResult += " after cancel page again started Kill flow | ";
					LOGGER.info("after cancel page again started Kill flow");
					Select_Response.click();
					CU.takeScreenshot(args, driver, "Select Response Page");
					NoBtn.click();
					CU.takeScreenshot(args, driver, "Select Response No Page");
				}
				if (args.get("CrossFlow_Flag").equalsIgnoreCase("Yes")) {
					CrossPopup(args);
					actualResult = args.get("ActualResult");
					CU.takeScreenshot(args, driver, "Kill Page");
					KillSelectReason(args);
					actualResult += " after cross icon page again started Kill flow | ";
					LOGGER.info("after cross icon page again started Kill flow");
					Select_Response.click();
					CU.takeScreenshot(args, driver, "Select Response Page");
					NoBtn.click();
					CU.takeScreenshot(args, driver, "Select Response No Page");
				}
				Reason.type(args.get("Reason"));
				actualResult += "Select Response No and add reason as - " + args.get("Reason") + " | ";
				LOGGER.info("Select Response No and add reason");
				CU.takeScreenshot(args, driver, "Add Reason Page");
				Proceed.click();
				CU.takeScreenshot(args, driver, "KILL After Proceed Page");

				actualResult += CU.assertEquals(
						"Lead has been killed successfully !\n" + "No Referral information provided.",
						KillPopup_text.getText(), args);
				
				LOGGER.info("proceed Kill flow");
				closeBtn.click();
				CU.takeScreenshot(args, driver, "Done Kill flow Page");
				LeadDetailsBtn.scrollTo();
				CU.takeScreenshot(args, driver, "Done Kill flow Page");

				actualResult += CU.assertEquals("SELF CREATED", LeadStatus2.getText(), args);
				actualResult += CU.assertEquals("KILL", LeadStatus1.getText(), args);
				
				actualResult += "old Lead Status as " + LeadStatus1.getText() + " and "
						+ LeadStatus2.getText() + " | ";
				LOGGER.info("check lead status");
			}
			if (args.get("Response").equalsIgnoreCase("Later")) {
				LaterBtn.click();
				CU.takeScreenshot(args, driver, "Select Response Later Page");
				LOGGER.info("Select Response Later");
				if (args.get("CancelFlow_Flag").equalsIgnoreCase("Yes")) {
					CancelPopup(args);
					CU.takeScreenshot(args, driver, "Kill flow Page");
					KillSelectReason(args);
					actualResult += " after cancel page again started Kill flow | ";
					LOGGER.info("after cancel page again started Kill flowckeck lead status");
					Select_Response.click();
					CU.takeScreenshot(args, driver, "Select Response Page");
					LaterBtn.click();
					CU.takeScreenshot(args, driver, "Select Response Later Page");
				}
				if (args.get("CrossFlow_Flag").equalsIgnoreCase("Yes")) {
					CrossPopup(args);
					CU.takeScreenshot(args, driver, "Kill Page");
					KillSelectReason(args);
					actualResult += " after cross icon page again started Kill flow | ";
					LOGGER.info("after cross icon page again started Kill flow");
					Select_Response.click();
					CU.takeScreenshot(args, driver, "Select Response Page");
					LaterBtn.click();
					CU.takeScreenshot(args, driver, "Select Response Later Page");
				}
				actualResult += "Select Response Later | ";
				Proceed.click();
				CU.takeScreenshot(args, driver, "KILL After Proceed Page");
				actualResult += CU.assertEquals("Lead has been killed successfully !",
						KillPopup_text.getText(), args);
				
				LOGGER.info("Proceed Kill flow");
				closeBtn.click();
				CU.takeScreenshot(args, driver, "Done Kill flow Page");
				LeadDetailsBtn.scrollTo();
				CU.takeScreenshot(args, driver, "Done Kill flow Page");

				actualResult += CU.assertEquals("SELF CREATED", LeadStatus2.getText(), args);
				actualResult += CU.assertEquals("KILL", LeadStatus1.getText(), args);
				
				actualResult += "old Lead Status as " + LeadStatus1.getText() + " and "
						+ LeadStatus2.getText() + " | ";
				LOGGER.info("check status");
			}
			actualResult += " Kill flow successfully done | ";
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
	}
}
