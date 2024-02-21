package com.listner;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.json.simple.parser.ParseException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.jayway.jsonpath.JsonPath;
import com.jira.utils.JiraOperations;
import com.jira.utils.JiraPolicy;
import com.zebrunner.carina.utils.R;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import net.sf.json.JSONObject;
import java.io.File;
public class TestListner implements ITestListener {

	private static final String xrayEndpoint = R.CONFIG.get("Base.xrayEndpoint");
    private static final String bearerToken = TokenGenerationAutomation.generateOrRetrieveApiToken();
    ZipTestCases zc =new ZipTestCases();

    static String testCaseId ;
    static String testRunid;
    static String firstIssueId;
    String emailableReportFilepath ;
    JiraPolicy jiraPolicy;
    
    private static HashMap<String, String> testCaseIdIssueIdMap = new HashMap<>(); // Declaration here
    private Map<String, String> testResults = new HashMap<>();
    

    @Override
    public void onTestStart(ITestResult result){
    	CheckJiraPolicy(result);
    }
    

    @Override
    public void onTestFailure(ITestResult result) {
        
        if (testCaseId != null) {
            testResults.put(testCaseId, "FAILED");
        } else {
            System.out.println("Test case ID not found for test: " + result.getName());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
      
    	if (testCaseId != null) {
            testResults.put(testCaseId, "PASSED");
        } else {
            System.out.println("Test case ID not found for test: " + result.getName());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("All tests finished");
        try {
			sendTestResultsToXray();
			
		} catch (org.apache.hc.core5.http.ParseException | ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
			
		
 // Send test result To xray
    public void sendTestResultsToXray() throws org.apache.hc.core5.http.ParseException, ParseException, IOException {
        // Call ZipTestcases
        startZipTC();

        // Fetch the first issue ID from the map
        firstIssueId = testCaseIdIssueIdMap.values().stream().findFirst().orElse(null);

        // Create and get the IssueId of the test execution
        String testExecutionId = createTestExecutionAndGetIssueId();
        System.out.println("Stored IssueId: " + testExecutionId);

        // Step 3-6: Add all other test issue IDs to the test execution
        for (String testIssueId : testCaseIdIssueIdMap.values()) {
            if (!testIssueId.equals(firstIssueId)) {
                addTestToTestExecution(testExecutionId, testIssueId);
            }
        }

        // Step 7: Get test run IDs for all test issue IDs
        List<String> testIssueIds = new ArrayList<>(testCaseIdIssueIdMap.values());
        Map<String, String> testRunIdMap = getTestRunIds(testIssueIds, testExecutionId);
     // Update Execution Status for all test run IDs and add evidence
        for (Map.Entry<String, String> entry : testRunIdMap.entrySet()) {
            String testIssueId = entry.getKey();
            String testRunId = entry.getValue();
            String testCaseId = null;

            // Find the testCaseId associated with the testIssueId
            for (Map.Entry<String, String> idEntry : testCaseIdIssueIdMap.entrySet()) {
                if (idEntry.getValue().equals(testIssueId)) {
                    testCaseId = idEntry.getKey();
                    break;
                }
            }

            // Check if testCaseId is present and matches the testCaseId in testResults map
            if (testCaseId != null && testRunId != null && testResults.containsKey(testCaseId)) {
                // Get the status from the testResults map
                String status = testResults.get(testCaseId);

                // Update the execution status based on the status from testResults map
                if ("PASSED".equalsIgnoreCase(status) || "FAILED".equalsIgnoreCase(status)) {
                    UpdateExecutionStatus(testRunId, status);

                    // Add evidence to the test run
                    AddEvidenceToTest.addEvidence(testRunId, testCaseId);

                    // Check if status is FAILED, then create defect in Jira
                    if ("FAILED".equalsIgnoreCase(status)) {
                    	

                    	// Call createDefectInJira to create the defect and get the issue ID
                    	String defectIssueId = createDefectInJira(testRunId, testCaseId);
                    	
                    	// Check if the defectIssueId is not null
                    	if (defectIssueId != null) {
                    	    // Call linkDefectToTestRun to link the defect to the test run
                    	    linkDefectToTestRun(testRunId, defectIssueId);
                    	} else {
                    	    // Handle the case where the defect creation failed
                    	    System.out.println("Failed to create defect in Jira.");
                    	}
                      
                    }
                } else {
                    System.out.println("Invalid test status for testCaseId: " + testCaseId);
                }
            } else {
                System.out.println("Unable to update execution status or add evidence for testIssueId: " + testIssueId);
            }
        }

    
        addXlsReport(testExecutionId);
    }

  
      public void startZipTC() {
    	
        // Replace with your actual base folder path
    	  String baseFolder = System.getProperty("user.dir") + File.separator + "Screenshots_Reports" + File.separator;

        // Replace with your actual destination folder path
        String destinationFolder = System.getProperty("user.dir")+File.separator+"ZipTestCases"+File.separator;;  //change path as per requirement
        
        // Delete existing zip files in the destination folder
        zc.deleteExistingZips(destinationFolder);

        // Zip test cases
        zc.zipTestCases(baseFolder, destinationFolder);
    }
    

  private void addXlsReport(String testExecutionId) throws org.apache.hc.core5.http.ParseException {
      // Get the user directory
      String userDir = System.getProperty("user.dir");

      // Specify the directory where the reports are stored
      String reportsDirectory = userDir + File.separator + "Screenshots_Reports";

      // Get today's date in the required format (assuming folder names are in "dd-MMM-yyyy" format)
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
      String todayDate = dateFormat.format(new Date());

      // Construct the file path for today's report
      String reportFilePath = reportsDirectory + File.separator + todayDate + File.separator + "Report.xls";

      AddAttachmentToJira.addAttachmentToTestExecution(testExecutionId, reportFilePath);
  }
  
    public static void updateTestRunIds(List<String> testIssueIds, String testExecutionId) {
        Map<String, String> testRunIdMap = getTestRunIds(testIssueIds, testExecutionId);

        for (Map.Entry<String, String> entry : testRunIdMap.entrySet()) {
            String testIssueId = entry.getKey();
            String testRunId = entry.getValue();
            String testCaseId = testCaseIdIssueIdMap.get(testIssueId);
            if (testCaseId != null) {
                testCaseIdIssueIdMap.put(testCaseId, testRunId);
            } else {
                // Handle the case where testCaseId is null
            }
        }    
    }
    //ReadIssueId and testCaseId from excel and store in hshamap
    public HashMap<String, String> readTestIssueIdfromExcel(HashMap<String, String> args) {
        //String actualResult = args.get("ActualResult");

        try {
            // Obtain the Test Case ID from the dataMap
            testCaseId = args.get("AutomationTestcaseID");
            System.out.println("Test Case ID from dataMap: " + testCaseId);

            // Obtain the Issue ID from the dataMap
            String issueId = args.get("Issueid");
            System.out.println("Issue ID from dataMap: " + issueId);


            // Store the obtained Test Case ID and Issue ID in the HashMap
            testCaseIdIssueIdMap.put(testCaseId, issueId);

        } catch (Exception e) {
            System.out.println("Exception during reading from Excel: " + e.getMessage());
            // Handle exception, log error, take screenshot, etc.
        }

        return testCaseIdIssueIdMap; // Return the obtained Test Case ID and Issue ID in HashMap
    }

  
  
    private static void addTestToTestExecution(String testExecutionId, String testIssueId) {
        try {
        	
            // Build the GraphQL query to add test to test execution
            String graphQLQuery = "mutation {\r\n"
                    + "    addTestsToTestExecution(\r\n"
                    + "        issueId: \"" + testExecutionId + "\",\r\n"
                    + "        testIssueIds: [\"" + testIssueId + "\"]\r\n"
                    + "    ) {\r\n"
                    + "        addedTests\r\n"
                    + "        warning\r\n"
                    + "    }\r\n"
                    + "}";
            // Create a JSON object with the GraphQL query
	        JSONObject jsonRequest = new JSONObject();
	        jsonRequest.put("query", graphQLQuery);
	        
	     // Create the HTTP client
	        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
	            // Create the HTTP post request
	            HttpPost httpPost = new HttpPost(xrayEndpoint);
	            // Set the request headers
	            httpPost.setHeader("Authorization", "Bearer " + bearerToken);
	            httpPost.setHeader("Content-Type", "application/json");

	            // Set the JSON request as the request body
	            httpPost.setEntity(new StringEntity(jsonRequest.toString()));

	            // Execute the request
	            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
	                // Print the response status code and body
	                System.out.println("Response Code: " + response.getCode());
	                System.out.println("Response Body: " + EntityUtils.toString(response.getEntity()));
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	 }
	
	private static String createTestExecutionAndGetIssueId() {
	        try {
	            // Replace with your actual GraphQL mutation
	            String graphQLQuery = "mutation { " +
	                    "  createTestExecution(" +
	                    "    testIssueIds: [\"" + firstIssueId + "\"]" +
	                    "    testEnvironments: [\"android\"]" +
	                    "    jira: {" +
	                    "      fields: { summary: \"Test Execution for JIR \", project: {key: \"JIR\"} }" +
	                    "    }) {" +
	                    "    testExecution {" +
	                    "      issueId" +
	                    "      jira(fields: [\"key\"]) " +
	                    "    }" +
	                    "    warnings" +
	                    "    createdTestEnvironments" +
	                    "  }" +
	                    "}";

	            // Create a JSON object with the GraphQL query
	            JSONObject jsonRequest = new JSONObject();
	            jsonRequest.put("query", graphQLQuery);

	            // Create the HTTP client
	            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
	                // Create the HTTP post request
	                HttpPost httpPost = new HttpPost(xrayEndpoint);

	                // Set the request headers
	                httpPost.setHeader("Authorization", "Bearer " + bearerToken);
	                httpPost.setHeader("Content-Type", "application/json");

	                // Set the JSON request as the request body
	                httpPost.setEntity(new StringEntity(jsonRequest.toString()));

	                // Execute the request
	                try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
	                    // Process the response as needed
	                    String responseBody = EntityUtils.toString(response.getEntity());

	                    // Parse the actual response JSON
	                    JSONObject jsonResponse = new JSONObject();
	                    jsonResponse.put("response", responseBody);

	                    // Access the JSON properties using the getJSONObject and getString methods
	                    String createdIssueId = jsonResponse.getJSONObject("response").getJSONObject("data")
	                            .getJSONObject("createTestExecution").getJSONObject("testExecution").getString("issueId");

	                    // Print the result
	                    System.out.println("Stored IssueId: " + createdIssueId);

	                    // Return the created issueId
	                    return createdIssueId;
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	 
	private static Map<String, String> getTestRunIds(Collection<String> testIssueIds, String testExecIssueId) {
	    Map<String, String> testRunIdMap = new HashMap<>();

	    try {
	 
	        for (String testIssueId : testIssueIds) {
	            // Replace with your actual GraphQL query
	            String graphQLQuery = "query {" +
	                    "  getTestRun(testIssueId: \"" + testIssueId + "\", testExecIssueId: \"" + testExecIssueId + "\") {" +
	                    "    id" +
	                    "  }" +
	                    "}";

	            // Create a JSON object with the GraphQL query
	            JSONObject jsonRequest = new JSONObject();
	            jsonRequest.put("query", graphQLQuery);

	            // Create the HTTP client
	            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
	                // Create the HTTP post request
	                HttpPost httpPost = new HttpPost(xrayEndpoint);

	                // Set the request headers
	                httpPost.setHeader("Authorization", "Bearer " + bearerToken);
	                httpPost.setHeader("Content-Type", "application/json");

	                // Set the JSON request as the request body
	                httpPost.setEntity(new StringEntity(jsonRequest.toString()));

	                // Execute the request
	                try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
	                    // Process the response as needed
	                    String responseBody = EntityUtils.toString(response.getEntity());
	                    System.out.println("Response Body: " + responseBody);

	                    String testRunId = extractIdFromResponse(responseBody);
	                    if (testRunId != null) {
	                        testRunIdMap.put(testIssueId, testRunId);
	                    }
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return testRunIdMap;
	}

	 private static String extractIdFromResponse(String response) {
	        try {
	            // Print the entire GraphQL response for debugging
	            System.out.println("GraphQL Response: " + response);

	            // Attempt to extract the ID using JsonPath
	            Object idObject = JsonPath.read(response, "$.data.getTestRun.id");

	            // Check if the extraction was successful
	            if (idObject != null) {
	                // Convert the extracted object to a string
	                testRunid = idObject.toString();
	                System.out.println("Extracted ID: " + testRunid);
	                return testRunid;
	            } else {
	                System.out.println("Error: Unable to retrieve 'id' from the response using JsonPath.");
	            }
	        } catch (Exception e) {
	            System.out.println("Error: Unable to extract 'id' using JsonPath.");
	            e.printStackTrace();
	        }
			return null;
	    }
	 
	 
	 public static void UpdateExecutionStatus(String runId, String status) {
		    try {

		        // Build the GraphQL query
		        String graphQLQuery = "mutation {\r\n"
		                + "    updateTestRunStatus( id: \"" + runId + "\", status: \"" + status + "\")\r\n"
		                + "}";

		        // Create a JSON object with the GraphQL query
		        JSONObject jsonRequest = new JSONObject();
		        jsonRequest.put("query", graphQLQuery);
		        // Create the HTTP client
		        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
		            // Create the HTTP post request
		            HttpPost httpPost = new HttpPost(xrayEndpoint);

		            // Set the request headers
		            httpPost.setHeader("Authorization", "Bearer " + bearerToken);
		            httpPost.setHeader("Content-Type", "application/json");

		            // Set the JSON request as the request body
		            httpPost.setEntity(new StringEntity(jsonRequest.toString()));

		            // Execute the request
		            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
		                // Print the response status code and body
		                System.out.println("Response Code: " + response.getCode());
		                System.out.println("Response Body: " + EntityUtils.toString(response.getEntity()));
		            }
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

   	
		 // Implement other ITestListener methods if needed
	    
	    public void CheckJiraPolicy(ITestResult result) {
	        
	            jiraPolicy = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(JiraPolicy.class);
	    } 

         	
	    //Create Defect in Jira by taking data from report
	    
	    public String createDefectInJira(String testRunId, String testCaseId) {
	        String defectIssueId = null;
	        if (jiraPolicy != null) {
	            boolean isTicketReady = jiraPolicy.logTicketReady();
	            if (isTicketReady) {
	                System.out.println("Is Ticket Ready for JIRA: " + isTicketReady);
	                JiraOperations jiraOp = new JiraOperations();
	                String ProjectKey = "JIR";
	                String Priority = "High";
	                String Label = "Automation";
	                String Assignee = "panchshila";

	                // Check if testCaseId is present in the scenarioDataMap
	                if (CreateDefectReadData.scenarioDataMap.containsKey(testCaseId)) {
	                    // Retrieve data from scenarioDataMap based on testCaseId
	                    Map<String, String> scenarioData = CreateDefectReadData.scenarioDataMap.get(testCaseId);
	                    
	                 // Extracting individual components
	                    String jiraStoryId = scenarioData.get("Jira Story Id");
	                    String module = scenarioData.get("Module");
	                    String submodule = scenarioData.get("Submodule");
	                   // String testcaseCoveredId = scenarioData.get("TestcaseCoveredID");

	                    // Creating the summary string
	                    String Summary = "Automation Test Failure-Defect Found in Test Case: " +testCaseId+ ", Jirastory:"+ jiraStoryId + ", Module:" + module + ", Submodule:" + submodule; //+ "-" + testcaseCoveredId;
	                    String Description = "Actual Result: "+scenarioData.get("ActualResult").trim();

	                   // String Description = "Test Case:\n" + scenarioData.get("TestCaseDescription") + "\nExpected Result:\n" + scenarioData.get("ExpectedResult") + "\nActual Result:\n" + scenarioData.get("ActualResult");
	                    try {
	                        // Assuming CreateJiraIssue method returns the issue ID
	                        defectIssueId = jiraOp.CreateJiraIssue(ProjectKey, Summary, Description, Priority, Label, Assignee);
	                    } catch (IOException | ParseException e) {
	                        e.printStackTrace();
	                    }
	                } else {
	                    System.out.println("Test case data not found for testCaseId: " + testCaseId);
	                }
	            }
	        }

	        return defectIssueId;
	    }
	    
	    public void linkDefectToTestRun(String testRunId, String issueId) {
		    try {

		    	String graphQLQuery = "mutation {\n" +
		                "    addDefectsToTestRun(id: \"" + testRunId + "\", issues: [\"" + issueId + "\"]) {\n" +
		                "        addedDefects\n" +
		                "        warnings\n" +
		                "    }\n" +
		                "}";

		        // Create a JSON object with the GraphQL query
		        JSONObject jsonRequest = new JSONObject();
		        jsonRequest.put("query", graphQLQuery);
		        // Create the HTTP client
		        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
		            // Create the HTTP post request
		            HttpPost httpPost = new HttpPost(xrayEndpoint);

		            // Set the request headers
		            httpPost.setHeader("Authorization", "Bearer " + bearerToken);
		            httpPost.setHeader("Content-Type", "application/json");

		            // Set the JSON request as the request body
		            httpPost.setEntity(new StringEntity(jsonRequest.toString()));

		            // Execute the request
		            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
		                // Print the response status code and body
		                System.out.println("Response Code: " + response.getCode());
		                System.out.println("Response Body: " + EntityUtils.toString(response.getEntity()));
		            }
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
}