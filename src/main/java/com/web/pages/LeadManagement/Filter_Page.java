package com.web.pages.LeadManagement;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.common.utils.CommonUtilities;
import com.common.utils.CommonWebUtilities;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

public class Filter_Page extends AbstractPage {

	public Filter_Page(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[contains(text(),'Create Lead')]")
	private ExtendedWebElement CreateNewLead;

	@FindBy(xpath = "//*[contains(text(),'Filter')]")
	private ExtendedWebElement Filter;

	@FindBy(xpath = "//*[contains(text(),'Today')]")
	private ExtendedWebElement Today;

	@FindBy(xpath = "//*[contains(text(),'This week')]")
	private ExtendedWebElement ThisWeek;

	@FindBy(xpath = "//*[contains(text(),'This month')]")
	private ExtendedWebElement ThisMonth;

	@FindBy(xpath = "//*[contains(text(),'Custom Date Range')]")
	private ExtendedWebElement CustomDateRange;

	@FindBy(xpath = "//*[text()='From']/following::button[1]")
	private ExtendedWebElement FromDate;

	@FindBy(xpath = "//*[text()='To']/following::button[1]")
	private ExtendedWebElement ToDate;

	@FindBy(xpath = "//*[contains(text(),'Meeting Date')]")
	private ExtendedWebElement MeetingDate;

	@FindBy(xpath = "//*[contains(text(),'Modified Date')]")
	private ExtendedWebElement ModifiedDate;

	@FindBy(xpath = "//*[contains(text(),'Source')]")
	private ExtendedWebElement Source;

	@FindBy(xpath = "//p[contains(text(),'Content')]")
	private ExtendedWebElement Content;

	@FindBy(xpath = "//p[contains(text(),'Referral Lead')]")
	private ExtendedWebElement ReferralLead;

	@FindBy(xpath = "//p[contains(text(),'Referral Lead')]/following::p[2]")
	private ExtendedWebElement SelfCreated;

	@FindBy(xpath = "//p[contains(text(),'ECM Campaign')]")
	private ExtendedWebElement ECM_Campaign;

	@FindBy(xpath = "//p[contains(text(),'S2S - Services to Sales')]")
	private ExtendedWebElement S2S;

	@FindBy(xpath = "//p[contains(text(),'Adobe Cross Sell')]")
	private ExtendedWebElement AdobeCrossSell;

	@FindBy(xpath = "//p[contains(text(),'WhatsApp Lead')]")
	private ExtendedWebElement WhatsAppLead;

	@FindBy(xpath = "//p[contains(text(),'Secure-Life')]")
	private ExtendedWebElement Secure_Life;

	@FindBy(xpath = "//*[contains(text(),'Segment')]")
	private ExtendedWebElement Segment;

	@FindBy(xpath = "//*[contains(text(),'Select All')]")
	private ExtendedWebElement SelectAll;

	@FindBy(xpath = "//*[contains(text(),'HNI')]")
	private ExtendedWebElement HNI;

	@FindBy(xpath = "//*[contains(text(),'Women')]")
	private ExtendedWebElement Women;

	@FindBy(xpath = "//*[contains(text(),'Select')]//following::p[contains(text(),'Silver')]")
	private ExtendedWebElement Silver;

	@FindBy(xpath = "//*[text()='NRI']")
	private ExtendedWebElement NRI;

	@FindBy(xpath = "//*[text()='SME']")
	private ExtendedWebElement SME;

	@FindBy(xpath = "//*[text()='Team']")
	private ExtendedWebElement Team;

	@FindBy(xpath = "//*[contains(text(),'Created Date')]")
	private ExtendedWebElement CreatedDate;

	@FindBy(xpath = "//*[contains(text(),'Apply Filter')]")
	private ExtendedWebElement ApplyFilter;

	@FindBy(xpath = "//*[contains(text(),'Direct Team')]")
	private ExtendedWebElement DirectTeam;

	@FindBy(xpath = "//*[contains(text(),'L1 Level')]")
	private ExtendedWebElement L1Level;

	@FindBy(xpath = "//*[contains(text(),'L2 Level')]")
	private ExtendedWebElement L2Level;

	@FindBy(xpath = "//*[contains(text(),'L3 Level')]")
	private ExtendedWebElement L3Level;

	@FindBy(xpath = "//*[contains(text(),'Next week')]")
	private ExtendedWebElement NextWeek;

	@FindBy(xpath = "//*[contains(text(),'Results Found')]")
	private ExtendedWebElement ResultCount;

	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	CommonUtilities CU = new CommonUtilities();

	public Search_Lead filter_Page(HashMap<String, String> args) throws Exception {

		String actualResult = args.get("ActualResult");
		try {

			if (args.get("Filter_Flag").contains("Yes")) {

				actualResult += CU.assertEquals("Filter", Filter.getText(), args);
				actualResult += " Filters option is available next to sort option|";
				Filter.click();
				CU.takeScreenshot(args, driver, "Lead Filter Page");

				actualResult += CU.assertEquals("Created Date", CreatedDate.getText(), args);
				actualResult += CU.assertEquals("Meeting Date", MeetingDate.getText(), args);
				actualResult += CU.assertEquals("Modified Date", ModifiedDate.getText(), args);
				actualResult += CU.assertEquals("Source", Source.getText(), args);
				actualResult += CU.assertEquals("Segment", Segment.getText(), args);
				actualResult += CU.assertEquals("Team", Team.getText(), args);

				LOGGER.info("click on filters and all options are available");
				actualResult += " Check All Filter options |";

				if (args.get("FilterType").equalsIgnoreCase("CreatedDate")) {
					CreatedDate.click();
					CU.takeScreenshot(args, driver, "Created Date filter option Page");
					actualResult += "check Filter created date options |";
					LOGGER.info("check Filter created date options");
					if (args.get("FilterOption1").equalsIgnoreCase("Today")) {
						Today.click();
						CU.takeScreenshot(args, driver, "Todays filter option Page");
						actualResult += "click Today created date filter options |";
						LOGGER.info("click Today created date filter options");
					} else if (args.get("FilterOption1").equalsIgnoreCase("ThisWeek")) {
						ThisWeek.click();
						CU.takeScreenshot(args, driver, "This week filter option Page");
						actualResult += "click This week created date filter options |";
						LOGGER.info("click This week created date filter options");
					} else if (args.get("FilterOption1").equalsIgnoreCase("ThisMonth")) {
						ThisMonth.click();
						CU.takeScreenshot(args, driver, "This month filter option Page");
						actualResult += "click this month created date filter options |";
						LOGGER.info("click this month created date filter options");
					} else if (args.get("FilterOption1").equalsIgnoreCase("CustomDateRange")) {
						CustomDateRange.click();
						CU.takeScreenshot(args, driver, "custom date range Page");
						actualResult += "click custom date range created date filter options |";
						LOGGER.info("click custom date range created date filter options");
						FromDate.click();
						CU.takeScreenshot(args, driver, "select dates Page");
						CommonWebUtilities.CalendarDate(driver, args.get("FromDate"));
						pause(4);
						LOGGER.info("Select from date");
						ToDate.click();
						CU.takeScreenshot(args, driver, "select dates Page");
						CommonWebUtilities.CalendarDate(driver, args.get("ToDate"));
						LOGGER.info("Select To date");
						CU.takeScreenshot(args, driver, "select dates Page");
						actualResult += "select from date and To date from custome date Range filter options |";
					}
				} else if (args.get("FilterType").equalsIgnoreCase("MeetingDate")) {
					MeetingDate.click();
					CU.takeScreenshot(args, driver, "Meeting date Page");
					actualResult += "check Meeting date filter options |";
					LOGGER.info("check Meeting date filter options");
					if (args.get("FilterOption1").equalsIgnoreCase("Today")) {
						Today.click();
						CU.takeScreenshot(args, driver, "Todays filter option Page");
						actualResult += "click Today created date filter options |";
						LOGGER.info("click Today created date filter options");
					} else if (args.get("FilterOption1").equalsIgnoreCase("ThisWeek")) {
						ThisWeek.click();
						CU.takeScreenshot(args, driver, "This week filter option Page");
						actualResult += "click This week created date filter options |";
						LOGGER.info("click This week created date filter options");
					} else if (args.get("FilterOption1").equalsIgnoreCase("NextWeek")) {
						NextWeek.click();
						CU.takeScreenshot(args, driver, "Next week filter option Page");
						actualResult += "click Next week created date filter options |";
						LOGGER.info("click Next week created date filter options");
					} else if (args.get("FilterOption1").equalsIgnoreCase("ThisMonth")) {
						ThisMonth.click();
						CU.takeScreenshot(args, driver, "This month filter option Page");
						actualResult += "click this month created date filter options |";
						LOGGER.info("click this month created date filter options");
					} else if (args.get("FilterOption1").equalsIgnoreCase("CustomDateRange")) {
						CustomDateRange.click();
						CU.takeScreenshot(args, driver, "custom date range Page");
						actualResult += "click custom date range created date filter options |";
						FromDate.click();
						CU.takeScreenshot(args, driver, "select dates Page");
						CommonWebUtilities.CalendarDate(driver, args.get("FromDate"));
						pause(4);
						LOGGER.info("Select To From date");
						ToDate.click();
						CU.takeScreenshot(args, driver, "select dates Page");
						CommonWebUtilities.CalendarDate(driver, args.get("ToDate"));
						LOGGER.info("Select To date");
						CU.takeScreenshot(args, driver, "select dates Page");
						actualResult += "select from date and To date from custome date Range filter options |";
					}
				} else if (args.get("FilterType").equalsIgnoreCase("ModifiedDate")) {
					ModifiedDate.click();
					CU.takeScreenshot(args, driver, "Modified date Page");
					actualResult += "check Modified date filter options |";
					LOGGER.info("check Modified date filter options");
					if (args.get("FilterOption1").equalsIgnoreCase("Today")) {
						Today.click();
						CU.takeScreenshot(args, driver, "Todays filter option Page");
						actualResult += "click on Today from Modified date filter options |";
						LOGGER.info("click on Today from Modified date filter options");
					} else if (args.get("FilterOption1").equalsIgnoreCase("ThisWeek")) {
						ThisWeek.click();
						CU.takeScreenshot(args, driver, "This week filter option Page");
						actualResult += "click on This week from Modified date filter options |";
						LOGGER.info("click on This week from Modified date filter options");
					} else if (args.get("FilterOption1").equalsIgnoreCase("ThisMonth")) {
						ThisMonth.click();
						CU.takeScreenshot(args, driver, "This month filter option Page");
						actualResult += "click on This month from Modified date filter options |";
						LOGGER.info("click on This month from Modified date filter options");
					} else if (args.get("FilterOption1").equalsIgnoreCase("CustomDateRange")) {
						CustomDateRange.click();
						CU.takeScreenshot(args, driver, "whats app lead Page");
						actualResult += "click on Custom Date Range from Modified date filter options |";
						FromDate.click();
						CU.takeScreenshot(args, driver, "select dates Page");
						CommonWebUtilities.CalendarDate(driver, args.get("FromDate"));
						pause(4);
						LOGGER.info("Select To FromDate");
						ToDate.click();
						CU.takeScreenshot(args, driver, "select dates Page");
						CommonWebUtilities.CalendarDate(driver, args.get("ToDate"));
						LOGGER.info("Select To todate");
						CU.takeScreenshot(args, driver, "select dates Page");
						actualResult += "select from date and to date from custome date range filter options |";
					}
				} else if (args.get("FilterType").equalsIgnoreCase("Source")) {
					Source.click();
					CU.takeScreenshot(args, driver, "Source Page");
					actualResult += "check Source filter options |";
					LOGGER.info("check Source filter options");
					for (int i = 1; i <= 8; i++) {
						if (args.get("FilterOption" + i + "").equalsIgnoreCase("Content")) {
							Content.click();
							CU.takeScreenshot(args, driver, "Content filter option Page");
							actualResult += "click on content from source filter options |";
							LOGGER.info("click on content from source filter options");
						}
						if (args.get("FilterOption" + i + "").equalsIgnoreCase("Referral Lead")) {
							ReferralLead.click();
							CU.takeScreenshot(args, driver, "Referral Lead filter option Page");
							actualResult += "click on Referral Lead from source filter options |";
							LOGGER.info("click on Referral Lead from source filter options");
						}
						if (args.get("FilterOption" + i + "").equalsIgnoreCase("Self Created")) {
							SelfCreated.click();
							CU.takeScreenshot(args, driver, "Self created filter option Page");
							actualResult += "click on Self created from source filter options |";
							LOGGER.info("click on Self created from source filter options");
						}
						if (args.get("FilterOption" + i + "").equalsIgnoreCase("ECM Campaign")) {
							ECM_Campaign.click();
							CU.takeScreenshot(args, driver, "ECM Campaign filter option Page");
							actualResult += "click on ECM campaign from source filter options |";
							LOGGER.info("click on ECM campaign from source filter options");
						}
						if (args.get("FilterOption" + i + "").equalsIgnoreCase("S2S - Services to Sales")) {
							S2S.click();
							CU.takeScreenshot(args, driver, "S2S Services to sales filter option Page");
							actualResult += "click on Services to sales from source filter options |";
							LOGGER.info("click on Services to sales from source filter options");
						}
						if (args.get("FilterOption" + i + "").equalsIgnoreCase("Adobe Cross Sell")) {
							AdobeCrossSell.click();
							CU.takeScreenshot(args, driver, "Adobe Cross sell filter option Page");
							actualResult += "click on Adobe cross sell from source filter options |";
							LOGGER.info("click on Adobe cross sell from source filter options");
						}
						if (args.get("FilterOption" + i + "").equalsIgnoreCase("WhatsApp Lead")) {
							WhatsAppLead.click();
							CU.takeScreenshot(args, driver, "Whatsapp Lead filter option Page");
							actualResult += "click on whats app lead from source filter options |";
							LOGGER.info("click on whats app lead from source filter options");
						}
						if (args.get("FilterOption" + i + "").equalsIgnoreCase("Secure-Life")) {
							Secure_Life.click();
							CU.takeScreenshot(args, driver, "Secure Life filter option Page");
							actualResult += "click on secure life from source filter options |";
							LOGGER.info("click on secure life from source filter options");
						}
					}
				} else if (args.get("FilterType").equalsIgnoreCase("Segment")) {
					Segment.click();
					actualResult = "check Segment filter options |";
					LOGGER.info("check Segment filter options");
					for (int i = 1; i <= 5; i++) {
						if (args.get("FilterOption" + i + "").equalsIgnoreCase("Select All")) {
							SelectAll.click();
							CU.takeScreenshot(args, driver, "Select All filter option Page");
							actualResult += "click on Select All from Segment filter options |";
							LOGGER.info("click on Select All from Segment filter options");
						} else {
							if (args.get("FilterOption" + i + "").equalsIgnoreCase("HNI")) {
								HNI.click();
								CU.takeScreenshot(args, driver, "HNI filter option Page");
								actualResult += "click on HNI from Segment filter options |";
								LOGGER.info("click on HNI from Segment filter options");
							}
							if (args.get("FilterOption" + i + "").equalsIgnoreCase("Silver")) {
								Silver.click();
								CU.takeScreenshot(args, driver, "Silver filter option Page");
								actualResult += "click on Silver from Segment filter options |";
								LOGGER.info("click on Silver from Segment filter options");
							}
							if (args.get("FilterOption" + i + "").equalsIgnoreCase("NRI")) {
								NRI.click();
								CU.takeScreenshot(args, driver, "NRI filter option Page");
								actualResult += "click on NRI from Segment filter options |";
								LOGGER.info("click on NRI from Segment filter options");
							}
							if (args.get("FilterOption" + i + "").equalsIgnoreCase("Women")) {
								Women.click();
								CU.takeScreenshot(args, driver, "Women filter option Page");
								actualResult += "click on Women from Segment filter options |";
								LOGGER.info("click on Women from Segment filter options");
							}
							if (args.get("FilterOption" + i + "").equalsIgnoreCase("SME")) {
								SME.click();
								CU.takeScreenshot(args, driver, "SME filter option Page");
								actualResult += "click on SME from Segment filter options |";
								LOGGER.info("click on SME from Segment filter options");
							}
						}
					}
				} else if (args.get("FilterType").equalsIgnoreCase("Team")) {
					Team.click();
					CU.takeScreenshot(args, driver, "Team filter option Page");
					actualResult += "check Team filter options |";
					LOGGER.info("check Team filter options");
					for (int i = 1; i <= 5; i++) {
						if (args.get("FilterOption" + i + "").equalsIgnoreCase("Select All")) {
							SelectAll.click();
							CU.takeScreenshot(args, driver, "Select All filter option Page");
							actualResult += "click on Select All from Team filter options |";
							LOGGER.info("click on Select All from Team filter options");
						} else {
							if (args.get("FilterOption" + i + "").equalsIgnoreCase("Direct Team")) {
								DirectTeam.click();
								CU.takeScreenshot(args, driver, "Direct Team filter option Page");
								actualResult += "click on Direct Team from Team filter options |";
								LOGGER.info("click on Direct Team from Team filter options");
							}
							if (args.get("FilterOption" + i + "").equalsIgnoreCase("L1 Level")) {
								L1Level.click();
								CU.takeScreenshot(args, driver, "L1 Level filter option Page");
								actualResult += "click on L1 Level from Team filter options |";
								LOGGER.info("click on L1 Level from Team filter options");
							}
							if (args.get("FilterOption" + i + "").equalsIgnoreCase("L2 Level")) {
								L2Level.click();
								CU.takeScreenshot(args, driver, "L2 Level filter option Page");
								actualResult += "click on L2 Level from Team filter options |";
								LOGGER.info("click on L2 Level from Team filter options");
							}
							if (args.get("FilterOption" + i + "").equalsIgnoreCase("L3 Level")) {
								L3Level.click();
								CU.takeScreenshot(args, driver, "L3 Level filter option Page");
								actualResult += "click on L3 Level from Team filter options |";
								LOGGER.info("click on L3 Level from Team filter options");
							}
						}
					}
				}

				ApplyFilter.click();
				waitUntil(ExpectedConditions.elementToBeClickable(CreateNewLead.getElement()), EXPLICIT_TIMEOUT);

				if (!ResultCount.isPresent()) {
					LOGGER.info("Result count is not resent ");
				} else {
					actualResult += ResultCount.getText() + " | ";
					LOGGER.info("Result count - " + ResultCount.getText());
				}

				CU.takeScreenshot(args, driver, "Apply filter lead list Page");
				actualResult += "Apply filter flow done successfully |";
				LOGGER.info("Apply filter flow done successfully");
				args.put("ActualResult", actualResult);

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
		return new Search_Lead(driver);
	}
}
