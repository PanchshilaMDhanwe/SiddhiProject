package com.API;

import java.io.BufferedWriter;
import java.util.Date;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.API.pages.LeadManagement.GET_AllLeads;
import com.API.pages.LeadManagement.GET_LeadByLeadID;
import com.API.pages.LeadManagement.GET_Master;
import com.API.pages.LeadManagement.GET_SearchAPI;
import com.API.pages.LeadManagement.GET_SourceHistory;
import com.API.pages.LeadManagement.GET_Timeline;
import com.API.pages.LeadManagement.POST_AddressUpdate;
import com.API.pages.LeadManagement.POST_CreateLead;
import com.API.pages.LeadManagement.POST_CreateNote;
import com.API.pages.LeadManagement.POST_DeleteNote;
import com.API.pages.LeadManagement.POST_EditNote;
import com.API.pages.LeadManagement.POST_HNITag_Update;
import com.API.pages.LeadManagement.POST_LeadFilter;
import com.API.pages.LeadManagement.POST_LeadStatusKill;
import com.API.pages.LeadManagement.POST_LeadStatus_NotContactable;
import com.API.pages.LeadManagement.POST_LeadStatus_StartJourney;
import com.API.pages.LeadManagement.POST_NRITag_Update;
import com.API.pages.LeadManagement.POST_PrimaryEmail;
import com.API.pages.LeadManagement.POST_SMETag_Update;
import com.API.pages.LeadManagement.POST_SecondaryEmail;
import com.API.pages.LeadManagement.POST_SecondaryMobile;
import com.API.pages.LeadManagement.POST_SilverTag_Update;
import com.API.pages.LeadManagement.POST_Source_Update;
import com.API.pages.LeadManagement.POST_SubSource_Update;
import com.API.pages.LeadManagement.POST_UpdateLead;
import com.API.pages.LeadManagement.POST_VerifyLead;
import com.API.pages.LeadManagement.POST_WomenTag_Update;
import com.common.utils.CommonAPIUtilities;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.dataprovider.IAbstractDataProvider;
import com.zebrunner.carina.dataprovider.annotations.XlsDataSourceParameters;

import io.restassured.response.Response;

public class SidhiAPI implements IAbstractTest, IAbstractDataProvider  {

	//HashMap<String, String> testDetail = new HashMap<String, String>();
	//PreConditions cond = new PreConditions();
	Date oStartTime = new Date();
	//ExecutionReport executionReport = cond.initExecReport(testDetail);
	String sOutput = "", sFinalOutput = "", sFlag = "";


	public HashMap<String, String> datamap = new HashMap<String, String>();
	int statusCode;
	BufferedWriter actualwriter = null;//
	DocumentContext response;

	private String requestBody;
	private String responseBody;
	Response getResponse;

	@Test(dataProvider = "SingleDataProvider")
	@XlsDataSourceParameters(path = "data_source/Sidhi_API_TestCases.xlsx", sheet = "LeadManagement", dsUid = "TestCaseID")

	public void LeadManagement_runApi(HashMap<String, String> datamap) throws Exception {
		try {
			//String TUID = datamap.get("TestCaseID");
			//ReportContext.renameTestDirectory(TUID);

			CommonAPIUtilities.FolderCreate();			// creating folder with todays date
			CommonAPIUtilities.TCFolderCreate(datamap);	//creating folder with testcaseID
			CommonAPIUtilities.CreateWorkbook();		//creating excel sheet
			
			String actualResult = datamap.get("ActualResult");
			
			switch (datamap.get("Module")) {
			case "CreateLead":
				POST_CreateLead lead_policy = new POST_CreateLead();
				lead_policy.setRequestPlaceHolder(datamap);
				getResponse = lead_policy.callAPI();
				requestBody = lead_policy.getRequestBody().toString();
				
				actualResult = "Create New Lead flow done successfully |";
				datamap.put("ActualResult", actualResult);
				break;

			case "LeadByLeadID":
				GET_LeadByLeadID ByLead = new GET_LeadByLeadID(datamap);
				getResponse = ByLead.callAPI();
				requestBody = ByLead.getRequestBody().toString();
				
				actualResult = "GET Lead By LeadID done successfully |";
				datamap.put("ActualResult", actualResult);
				break;
			case "LeadFilter":
				POST_LeadFilter leadfilter = new POST_LeadFilter();
				leadfilter.setleadfilter(datamap);
				getResponse = leadfilter.callAPI();
				requestBody = leadfilter.getRequestBody().toString();
				
				actualResult = "Lead Filter done successfully |";
				datamap.put("ActualResult", actualResult);
				break;
			case "AllLead":
				GET_AllLeads allLead = new GET_AllLeads(datamap);
				getResponse = allLead.callAPI();
				requestBody = allLead.getRequestBody().toString();

				actualResult = "All Lead done successfully |";
				datamap.put("ActualResult", actualResult);
				break;
			case "Master":
				GET_Master master = new GET_Master(datamap);
				getResponse = master.callAPI();
				requestBody = master.getRequestBody().toString();
				
				actualResult = "Master API done successfully |";
				datamap.put("ActualResult", actualResult);
				break;

			case "LeadStatusKill":
				POST_LeadStatusKill killstatus = new POST_LeadStatusKill(datamap);
				getResponse = killstatus.callAPI();
				requestBody = killstatus.getRequestBody().toString();
				
				actualResult = "Lead Status Kill API done successfully |";
				datamap.put("ActualResult", actualResult);
				break;

			case "UpdateLead":
				POST_UpdateLead leadUpdate = new POST_UpdateLead(datamap);
				leadUpdate.setleadUpdate(datamap);
				getResponse = leadUpdate.callAPI();
				requestBody = leadUpdate.getRequestBody().toString();
				
				actualResult = "updated lead API done successfully |";
				datamap.put("ActualResult", actualResult);
				break;

			case "createnote":
				POST_CreateNote createnote = new POST_CreateNote(datamap);
				createnote.setCreatenote(datamap);
				getResponse = createnote.callAPI();
				requestBody = createnote.getRequestBody().toString();
				
				actualResult = "Create Note API done successfully |";
				datamap.put("ActualResult", actualResult);
				break;

			case "Editnote":
				POST_EditNote Editnote = new POST_EditNote(datamap);
				Editnote.seteditNote(datamap);
				getResponse = Editnote.callAPI();
				requestBody = Editnote.getRequestBody().toString();
				
				actualResult = "Edit Note API done successfully |";
				datamap.put("ActualResult", actualResult);
				break;

			case "Deletenote":
				POST_DeleteNote Deletenote = new POST_DeleteNote(datamap);
				getResponse = Deletenote.callAPI();
				requestBody = Deletenote.getRequestBody().toString();
				
				actualResult = "Delete Note API done successfully |";
				datamap.put("ActualResult", actualResult);
				break;

			case "PrimaryEmail":
				POST_PrimaryEmail Primaryemail = new POST_PrimaryEmail(datamap);
				Primaryemail.setRequestPlaceHolder(datamap);
				getResponse = Primaryemail.callAPI();
				requestBody = Primaryemail.getRequestBody().toString();
				
				actualResult = "Primary Email API done successfully |";
				datamap.put("ActualResult", actualResult);
				break;

			case "SecondaryMobile":
				POST_SecondaryMobile SecondaryMobile = new POST_SecondaryMobile(datamap);
				SecondaryMobile.setRequestPlaceHolder(datamap);
				getResponse = SecondaryMobile.callAPI();
				requestBody = SecondaryMobile.getRequestBody().toString();
				
				actualResult = "Secondary Mobile API done successfully |";
				datamap.put("ActualResult", actualResult);
				break;

			case "SecondaryEmail":
				POST_SecondaryEmail SecondaryEmail = new POST_SecondaryEmail(datamap);
				SecondaryEmail.setRequestPlaceHolder(datamap);
				getResponse = SecondaryEmail.callAPI();
				requestBody = SecondaryEmail.getRequestBody().toString();
				
				actualResult = "Secondary Email API done successfully |";
				datamap.put("ActualResult", actualResult);
				break;

			case "AddressUpdate":
				POST_AddressUpdate AddressUpdate = new POST_AddressUpdate(datamap);
				AddressUpdate.setleadUpdate(datamap);
				getResponse = AddressUpdate.callAPI();
				requestBody = AddressUpdate.getRequestBody().toString();
				
				actualResult = "Address Update API done successfully |";
				datamap.put("ActualResult", actualResult);
				break;

			case "HNITag_Update":
				POST_HNITag_Update HNITag_Update = new POST_HNITag_Update(datamap);
				getResponse = HNITag_Update.callAPI();
				requestBody = HNITag_Update.getRequestBody().toString();
				
				actualResult = "HNI Tag Update API done successfully |";
				datamap.put("ActualResult", actualResult);
				break;

			case "SMETag_Update":
				POST_SMETag_Update SMETag_Update = new POST_SMETag_Update(datamap);
				getResponse = SMETag_Update.callAPI();
				requestBody = SMETag_Update.getRequestBody().toString();
				
				actualResult = "SME Tag Update API done successfully |";
				datamap.put("ActualResult", actualResult);
				break;

			case "WomenTag_Update":
				POST_WomenTag_Update WomenTag_Update = new POST_WomenTag_Update(datamap);
				getResponse = WomenTag_Update.callAPI();
				requestBody = WomenTag_Update.getRequestBody().toString();
				
				actualResult = "Women tag update API done successfully |";
				datamap.put("ActualResult", actualResult);
				break;

			case "NRITag_Update":
				POST_NRITag_Update NRITag_Update = new POST_NRITag_Update(datamap);
				getResponse = NRITag_Update.callAPI();
				requestBody = NRITag_Update.getRequestBody().toString();
				
				actualResult = "NRI tag update API done successfully |";
				datamap.put("ActualResult", actualResult);
				break;

			case "SilverTag_Update":
				POST_SilverTag_Update SilverTag_Update = new POST_SilverTag_Update(datamap);
				getResponse = SilverTag_Update.callAPI();
				requestBody = SilverTag_Update.getRequestBody().toString();
				
				actualResult = "Silver tag update API done successfully |";
				datamap.put("ActualResult", actualResult);
				break;

			case "Source_Update":
				POST_Source_Update Source_Update = new POST_Source_Update(datamap);
				getResponse = Source_Update.callAPI();
				requestBody = Source_Update.getRequestBody().toString();
				
				actualResult = "Source update API done successfully |";
				datamap.put("ActualResult", actualResult);
				break;

			case "SubSource_Update":
				POST_SubSource_Update SubSource_Update = new POST_SubSource_Update(datamap);
				getResponse = SubSource_Update.callAPI();
				requestBody = SubSource_Update.getRequestBody().toString();
				
				actualResult = "Subsource update API done successfully |";
				datamap.put("ActualResult", actualResult);
				break;

			case "LeadStatus_NotContactable":
				POST_LeadStatus_NotContactable LeadStatus = new POST_LeadStatus_NotContactable(datamap);
				getResponse = LeadStatus.callAPI();
				requestBody = LeadStatus.getRequestBody().toString();
				
				actualResult = "Not Contactable lead status API done successfully |";
				datamap.put("ActualResult", actualResult);
				break;

			case "VerifyLead":
				POST_VerifyLead VerifyLead = new POST_VerifyLead(datamap);
				getResponse = VerifyLead.callAPI();
				requestBody = VerifyLead.getRequestBody().toString();
				
				actualResult = "verify lead API done successfully |";
				datamap.put("ActualResult", actualResult);
				break;

			case "LeadStatus_StartJourney":
				POST_LeadStatus_StartJourney leadStatus = new POST_LeadStatus_StartJourney(datamap);
				getResponse = leadStatus.callAPI();
				requestBody = leadStatus.getRequestBody().toString();
				
				actualResult = "StartJourney lead status API done successfully |";
				datamap.put("ActualResult", actualResult);
				break;

			case "Timeline":
				GET_Timeline Timeline = new GET_Timeline(datamap);
				getResponse = Timeline.callAPI();
				requestBody = Timeline.getRequestBody().toString();
				
				actualResult = "Timeline API done successfully |";
				datamap.put("ActualResult", actualResult);
				break;

			case "SourceHistory":
				GET_SourceHistory SourceHistory = new GET_SourceHistory(datamap);
				getResponse = SourceHistory.callAPI();
				requestBody = SourceHistory.getRequestBody().toString();
				
				actualResult = "Source History API done successfully |";
				datamap.put("ActualResult", actualResult);
				break;
				
			case "SearchAPI":
				GET_SearchAPI searchAPI = new GET_SearchAPI(datamap);
				getResponse = searchAPI.callAPI();
				requestBody = searchAPI.getRequestBody().toString();
				
				actualResult = "Search API done successfully |";
				datamap.put("ActualResult", actualResult);
				break;
			}

			response = JsonPath.parse(getResponse.asString());
			responseBody = getResponse.asString();
			//SaveReport SaveReport = new SaveReport();
			CommonAPIUtilities.SaveReports(datamap, requestBody, responseBody);

			CommonAPIUtilities.WriteToExcel(datamap);

			// statusCode = getResponse.getStatusCode();
//		DocumentContext obj = JsonPath.parse(getResponse);

			/*
			 * if(statusCode==200) { String isExist = response.read("isExist").toString();
			 * 
			 * if(datamap.get("testtype").equalsIgnoreCase("Negative")) {
			 * assertEquals(datamap.get("testtype").equals("Negative"), true,
			 * "Negative case but found positive response"); }
			 * 
			 * }else if(statusCode==400) {
			 * 
			 * if(datamap.get("testtype").equalsIgnoreCase("Positive")) {
			 * assertEquals(datamap.get("testtype").equals("Positive"), true,
			 * "Positive case but found Negative response"); } String reason =
			 * response.read("reason").toString(); String reasonCode =
			 * response.read("reasonCode");
			 * 
			 * }else if(statusCode==401) {
			 * 
			 * if(datamap.get("testtype").equalsIgnoreCase("Negative")) {
			 * assertEquals(datamap.get("testtype").equals("Negative"), false,
			 * "Positive case but found Negative response"); }
			 * 
			 * }else { if(datamap.get("testtype").equalsIgnoreCase("Positive")) {
			 * assertEquals(datamap.get("testtype").equals("Positive"), true,
			 * "Positive case but found Negative response"); }else { assertEquals(true,
			 * false, "POSTPOSGripProposalAI Exception"); } }
			 */

		} catch (Exception ex) {
			datamap.put("status", "Fail");
			datamap.put("ActualResult", "Test case Execution failed");
			datamap.put("ExecutionResult",
					sFinalOutput + " || Test Case failed due to error :  " + datamap.get("Output"));
			ex.printStackTrace();

			CommonAPIUtilities.WriteToExcel(datamap);
			throw new Exception(ex.getMessage());
			// cond.handleException(testDetail, executionReport, ex);

		}
	}



}
