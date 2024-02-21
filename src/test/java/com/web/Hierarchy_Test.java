package com.web;

import java.util.HashMap;

import com.common.utils.CommonUtilities;
import com.web.pages.Hierarchy.AgencyEmployee_BAM;
import com.web.pages.Hierarchy.AgencyEmployee_BAM_L2;
import com.web.pages.Hierarchy.AgencyPartner_Advisor;
import com.web.pages.Hierarchy.AgencyPartner_Leader2;
import com.web.pages.Hierarchy.BANCA_L2;
import com.web.pages.Hierarchy.BANCA_RM;
import com.web.pages.Hierarchy.BANCA_SP;
import com.web.pages.Hierarchy.NonSalesPerson;
import com.web.pages.Hierarchy.ProfilePage;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.config.ReportConfiguration;
import com.zebrunner.carina.dataprovider.IAbstractDataProvider;
import com.zebrunner.carina.dataprovider.annotations.XlsDataSourceParameters;
import com.zebrunner.carina.utils.report.ReportContext;

import org.testng.annotations.Test;


public class Hierarchy_Test implements IAbstractTest, IAbstractDataProvider {
	
	CommonUtilities CU = new CommonUtilities();
	
	@Test(testName = "TC001", dataProvider = "DataProvider", groups = { "TC001" }, priority = 1)
	@XlsDataSourceParameters(path = "data_source/Siddhi_Web_TestCases.xlsx", sheet = "Hierarchy", dsUid = "TestCaseID", executeColumn = "Executor", executeValue = "Y")
	public void Test(HashMap<String, String> args) throws Exception {
		
		try {
			CU.FolderCreate();			// creating folder with todays date
			CU.TCFolderCreate(args);	//creating folder with testcaseID
			CU.CreateWorkbook();		//creating excel sheet
			
			
		
			//Call page classes
			ProfilePage profile;
			AgencyPartner_Advisor apadvisor;
			AgencyPartner_Leader2 apleader;
			AgencyEmployee_BAM aebam;
			AgencyEmployee_BAM_L2 aebam2;
			BANCA_SP bancsp;
			BANCA_RM bancarm;
			BANCA_L2 bancaL2;
			NonSalesPerson nsp;
			
			profile = new ProfilePage(getDriver());
			
			profile.openURL("", 100);	//launch url
			
			//call functions
			apadvisor =	profile.SelectProfile(args);
//			apleader  = apadvisor.AP_Advisor(args);
//			aebam     = apleader.AP_Leader2(args);
//			aebam2    = aebam.AgencyEmp_BAM(args);
//			bancsp    = aebam2.AgencyEmp_BAM_L2(args);
//			bancarm   = bancsp.Banca_SP(args);
//			bancaL2   = bancarm.Banca_RM(args);
//			nsp		  = bancaL2.Banca_L2(args);
//				  		nsp.NonSales_Person(args);
				
			profile.quitDriver();
			
//			if (!args.get("sOutput").equalsIgnoreCase("")) {
//				throw new Exception(args.get("sOutput"));
//			}
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


