package com.web;



import java.util.HashMap;

import org.testng.annotations.Test;

import com.common.utils.CommonUtilities;

import com.jira.pages.HomePage;
import com.jira.pages.SearchPage;
import com.jira.utils.JiraPolicy;
import com.listner.CreateDefectReadData;
import com.listner.TestListner;

import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.dataprovider.IAbstractDataProvider;
import com.zebrunner.carina.dataprovider.annotations.XlsDataSourceParameters;

public class JiraTest implements IAbstractTest, IAbstractDataProvider {
	CreateDefectReadData cr = new CreateDefectReadData();
	CommonUtilities CU = new CommonUtilities();
	@JiraPolicy(logTicketReady = true) //this is specifically for raising a bug in jira can make it false if we dont want to raise a bug
	@Test(testName = "TC001", dataProvider = "DataProvider", groups = { "TC001" }, priority = 1)
	@XlsDataSourceParameters(path = "data_source/Siddhi_Web_TestCases.xlsx", sheet = "Choreography", dsUid = "AutomationTestCaseID", executeColumn = "Executor", executeValue = "Y")

	public void Test(HashMap<String, String> args) throws Exception {
		
		try {
			CU.FolderCreate();			// creating folder with todays date
			CU.TCFolderCreate(args);	//creating folder with testcaseID
			CU.CreateWorkbook();		//creating excel sheet

			//Call page classes
			TestListner tc = new TestListner();
            tc.readTestIssueIdfromExcel(args);
			
			HomePage home;
			SearchPage seacrh;
			home = new HomePage(getDriver());
			home.openURL("", 100);	//launch url
			//home.navigateToW3Schools(args);
			//home.verifyTitle(args);
			
			seacrh = new SearchPage(getDriver());
			seacrh.navigateToContactPage(args);
			seacrh.verifyContactPageHeader(args);
			home.quitDriver();
			
			if (!args.get("sOutput").equalsIgnoreCase("")) {
				throw new Exception(args.get("sOutput"));
			}
			CU.WriteToExcel(args);			//written all data in created excel sheet
				
		} catch (Exception ex) {
            ex.printStackTrace();
            args.put("status", "Fail");
            String actualResult = args.get("ActualResult");
            actualResult += "Test case Execution failed";
            args.put("ActualResult", actualResult);
            args.put("ExecutionResult", "Test Case failed due to error :  " + ex.getMessage());
            if (args.get("Test Case Type").equalsIgnoreCase("Negative")) {
                args.put("status", "Pass");
            }
            CU.WriteToExcel(args);
           
            cr.readReportFile();
            throw new Exception(ex.getMessage());
            
		}
		
	}
}
