package com.jira.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.utils.CommonUtilities;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SearchPage extends AbstractPage {
	CommonUtilities CU = new CommonUtilities();
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());


    @FindBy(xpath = "//input[@placeholder = 'Search our tutorials, e.g. HTML']")
    private ExtendedWebElement SearchTutorials;

    @FindBy(xpath = "//i[@id='learntocode_searchicon']")
    private ExtendedWebElement SearchClickBtn;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToContactPage(HashMap<String, String> args) {
    	String actualResult = args.get("ActualResult");
    	SearchTutorials.type(args.get("SeacrhTuorial"));
    	SearchClickBtn.click();
    	actualResult+= "Clicked on SeacrhTuorial |" ;
    	actualResult+= "Clicked on SearchClickBtn |" ;
        args.put("ActualResult",actualResult);
    }

    public void verifyContactPageHeader(HashMap<String, String> args) throws Exception {
    	String actualResult = args.get("ActualResult");
    	try {
    	
       	actualResult +=  CU.assertEquals("JavaScr Tutorial", driver.getTitle(), args);
        args.put("ActualResult",actualResult);

    	}
    	catch (Exception e) {
    		actualResult =  args.get("ActualResult");
            System.out.println(args.get("ActualResult"));

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
