package com.jira.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.common.utils.CommonUtilities;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import static org.testng.Assert.assertEquals;

import java.util.HashMap;

public class HomePage extends AbstractPage {
	
	CommonUtilities CU = new CommonUtilities();
    
    @FindBy(xpath = "//input[@placeholder = 'Search our tutorials, e.g. HTML']")
    private ExtendedWebElement w3schoolsLink;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateToW3Schools(HashMap<String, String> args) {
    	String actualResult = args.get("ActualResult");
        w3schoolsLink.click();
        actualResult+= "Clicked on w3schoolsLink |" ;
        args.put("ActualResult",actualResult);
        
    }

    public void verifyTitle(HashMap<String, String> args) throws Exception {
    	String actualResult = args.get("ActualResult");
       
		actualResult +=  CU.assertEquals("W3Schools Online Web Tutorials", driver.getTitle(), args);
        args.put("ActualResult",actualResult);
    }
    
    
}
