package com.web;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.common.utils.CommonUtilities;
import com.web.pages.LeadManagement.CreateLead;
import com.web.pages.LeadManagement.Filter_Page;
import com.web.pages.LeadManagement.LeadPage;
import com.web.pages.LeadManagement.LeadDetails;
import com.web.pages.LeadManagement.Pagination;
import com.web.pages.LeadManagement.Search_Lead;
import com.web.pages.LeadManagement.Sort_Page;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.dataprovider.IAbstractDataProvider;
import com.zebrunner.carina.dataprovider.annotations.XlsDataSourceParameters;

public class LeadManagement implements IAbstractTest, IAbstractDataProvider {
	
	CommonUtilities CU = new CommonUtilities();

	@Test(testName = "TC001", dataProvider = "DataProvider", groups = { "TC001" }, priority = 1)
	@XlsDataSourceParameters(path = "data_source/Siddhi_Web_TestCases.xlsx", sheet = "LeadManagement", dsUid = "AutomationTestcase", executeColumn = "Executor", executeValue = "Y")

	public void Test(HashMap<String, String> args) throws Exception {
		
		try {
			
			CU.FolderCreate(); // creating folder with todays date
			CU.TCFolderCreate(args); // creating folder with testcaseID
			CU.CreateWorkbook(); // creating excel sheet

			// Call page classes
			LeadPage leadPage;
			Pagination pagination;
			Sort_Page sorting;
			Filter_Page filters;
			Search_Lead searching;
			CreateLead creation;
			LeadDetails leadDetail;
			
			leadPage = new LeadPage(getDriver());
			
			leadPage.openURL("", 100); 	//launch url
			
			// call functions
			pagination = leadPage.LeadsPage(args); 
			sorting	   = pagination.Submodule_Pagination(args);
			filters    = sorting.sort_Page(args);
			searching  = filters.filter_Page(args);
			creation   = searching.Search(args);
			leadDetail = creation.LeadCreation(args);
						 leadDetail.LeadDetailsPage(args);
			
			leadPage.quitDriver();
			
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
