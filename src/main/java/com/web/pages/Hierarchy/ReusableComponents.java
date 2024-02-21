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

public class ReusableComponents extends AbstractPage {

		@FindBy(xpath = "//*[@class='MuiStack-root css-1wcgaji']//p[1]")
		private ExtendedWebElement Name;

		@FindBy(xpath = "//*[@class='MuiStack-root css-1wcgaji']//p[2]")
		private ExtendedWebElement Role;

		@FindBy(xpath = "//*[@class='MuiStack-root css-17pcntq']//p[1]")
		private ExtendedWebElement Address;

		@FindBy(xpath = "(//div[@class='MuiBox-root css-3sweu6'][1])[1]")
		private ExtendedWebElement MobileIcon;

		@FindBy(xpath = "//div[@class='MuiBox-root css-3sweu6'][2]")
		private ExtendedWebElement WhatsAppIcon;

		@FindBy(xpath = "(//div[@class='MuiBox-root css-3sweu6'])[3]")
		private ExtendedWebElement EmailIcon;

	
		CommonUtilities CU = new CommonUtilities();
		
		public ReusableComponents(WebDriver driver) {
			super(driver);
		}
		private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

		public void Details(HashMap<String,String> args) throws IOException, InterruptedException{
			String actualResult = args.get("actualResult");
			
			if(!Name.isVisible()) {
				actualResult += "Name Role not visible |";
				LOGGER.info("Name Role not visible");
			}else {
			actualResult += "Leader Name - "+ Name.getText() + " , Leader Role - "+Role.getText() + " , Leader Address - "+Address.getText() + " | ";
			LOGGER.info("Leader Name - "+ Name.getText() + " , Leader Role - "+Role.getText() + " , Leader Address - "+Address.getText() ); 
			}
			if(!MobileIcon.isClickable()) {
				actualResult += "*** Mobile Icon is not clickable ***|";
				LOGGER.info("*** Mobile Icon is not clickable ***");
				args.put("status", "Fail");
			}else {
				actualResult += "Mobile Icon is clickable |";
				LOGGER.info("Mobile Icon is clickable");
			}
			
			if(!WhatsAppIcon.isClickable()) {
				actualResult += "*** WhatsApp Icon is not clickable ***|";
				LOGGER.info("*** WhatsApp Icon is not clickable ***");
				args.put("status", "Fail");
			}else {
				actualResult += "WhatsApp Icon is clickable |";
				LOGGER.info("WhatsApp Icon is clickable");
			}

			if(!EmailIcon.isClickable()) {
				actualResult += "*** Email Icon is not clickable ***|";
				LOGGER.info("*** Email Icon is not clickable ***");
				args.put("status", "Fail");
			}else {
				actualResult += "Email Icon is clickable |";
				LOGGER.info("Email Icon is clickable");
			}
			args.put("actualResult", actualResult);
		}
		
}
