package com.web;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.common.utils.CommonUtilities;
import com.jira.utils.JiraPolicy;
import com.listner.TestExecution;
import com.listner.TestListner;
import com.web.pages.Choreography.Filters_Page;
import com.web.pages.Choreography.ChoreographyPage;
import com.web.pages.Choreography.IncomePlanning_Dashboard;
import com.web.pages.Choreography.Review_Leaders;
import com.web.pages.Choreography.WeeklyReview_Dashboard;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.dataprovider.IAbstractDataProvider;
import com.zebrunner.carina.dataprovider.annotations.XlsDataSourceParameters;

public class Choreography implements IAbstractTest, IAbstractDataProvider {

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
			ChoreographyPage ChoreoPage;
			IncomePlanning_Dashboard IPdashboard;
			Filters_Page filters;
			WeeklyReview_Dashboard WRdashboard;
			Review_Leaders review_page;
			
			ChoreoPage = new ChoreographyPage(getDriver());
			
			ChoreoPage.openURL("", 100);	//launch url
			
			//call functions
			//IPdashboard = ChoreoPage.ChoreoGraphyPage(args);	
			//filters = IPdashboard.dashboard(args);
			//WRdashboard = filters.filters(args);
			//review_page = WRdashboard.dashboard(args);
						  //review_page.Review(args);
			
			ChoreoPage.quitDriver();
			
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
			args.put("ExecutionResult",  "Test Case failed due to error :  " + ex.getMessage());
			if (args.get("Test Case Type").equalsIgnoreCase("Negative")) {
				args.put("status", "Pass");
			}
			CU.WriteToExcel(args);			//written all data in created excel sheet
			throw new Exception(ex.getMessage());

		}
		
	}


}
