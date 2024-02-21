package com.listner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.json.simple.parser.ParseException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.jayway.jsonpath.JsonPath;
import com.jira.utils.JiraOperations;
import com.jira.utils.JiraPolicy;

import net.sf.json.JSONObject;
import java.io.File;

public class TestExecution implements ITestListener {
    

	private static final String xrayEndpoint = "https://xray.cloud.getxray.app/api/v2/graphql";
    private static final String bearerToken= "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnQiOiIxOWYzYWFmZS1iNGI1LTM4MmItOGFiMi05YzNhYzM3ZGQxYWQiLCJhY2NvdW50SWQiOiI2MTdiYWQyNWY2ZGE2YTAwNmEzMGY2YzQiLCJpc1hlYSI6ZmFsc2UsImlhdCI6MTcwNzM5NzkzMywiZXhwIjoxNzA3NDg0MzMzLCJhdWQiOiI4N0VGODcxNDIzMkE0RjlFOEQ5QzUwNEQwMjVDODk0QiIsImlzcyI6ImNvbS54cGFuZGl0LnBsdWdpbnMueHJheSIsInN1YiI6Ijg3RUY4NzE0MjMyQTRGOUU4RDlDNTA0RDAyNUM4OTRCIn0.l86P5SnaeGocjJvLMdd-EvSxDtHno6weHvo349v9TEU";
    AddEvidenceToTest evidence = new AddEvidenceToTest();
    ZipTestCases zc =new ZipTestCases();

   // static String testIssueId;
    static String testRunid;
    static String firstIssueId;
    String emailableReportFilepath ;
    private static HashMap<String, String> testCaseIdIssueIdMap = new HashMap<>(); // Declaration here
 
    
	
    //public static void main(String[] args) {
	private static List<ITestResult> failedTests = new ArrayList<>();
    private static List<String> testIssueIdList = new ArrayList<>();
	
	

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + result.getMethod().getMethodName());
        failedTests.add(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("All tests finished");
       
        try {
			sendTestResultsToXray(failedTests);
			
		} catch (org.apache.hc.core5.http.ParseException e) {
			
			e.printStackTrace();
		} catch (ParseException e) {
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
        
        if (!failedTests.isEmpty()) {
            createDefectInJira(failedTests);
        }
        
        
    }
    
 // Send test result To xray
    public void sendTestResultsToXray(List<ITestResult> results) throws org.apache.hc.core5.http.ParseException, ParseException, IOException {
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

            if (testCaseId != null && testRunId != null) {
                UpdateExecutionStatus(testRunId);

                // Add evidence to the test run
                AddEvidenceToTest.addEvidence(testRunId, testCaseId);
            } else {
                System.out.println("Unable to update execution status or add evidence for testIssueId: " + testIssueId);
            }
        }
    

        //Add Emailable report logic
        addReportTestExecution(testExecutionId);
        
        // Add attachment to the test execution
        // Integrate zipping and attachment logic here
        addAttachemntToTestExecution(testExecutionId);
    }

  
  public void startZipTC() {
    	
        // Replace with your actual base folder path
        String baseFolder = "C:\\Users\\ec1003au\\eclipse-workspace\\SiddhiProject\\Screenshots_Reports";

        // Replace with your actual destination folder path
        String destinationFolder = "C:\\Users\\ec1003au\\Documents\\ZipTest";
        
        // Delete existing zip files in the destination folder
        zc.deleteExistingZips(destinationFolder);

        // Zip test cases
        zc.zipTestCases(baseFolder, destinationFolder);
    }
    
    // Method to create and attach the zip file
    private void addAttachemntToTestExecution(String testExecutionId) throws org.apache.hc.core5.http.ParseException, ParseException {
        // Replace "path/to/your/source/folder" with the actual path to your source folder
        String sourceFolderPath = "C:\\Users\\ec1003au\\Documents\\ZipTest";

        // Replace "path/to/your/destination/folder" with the actual path to your destination folder
        String destinationFolderPath = "C:\\Users\\ec1003au\\Documents\\ZipTest";

        // Replace "your-zipped-filename.zip" with the desired name for the zipped file
        String zipFileName = "DoneWithZip.zip";

        try {
            // Zip the folder and attach the file
            AddAttachmentToJira.createAndAttachZip(sourceFolderPath, destinationFolderPath, zipFileName, testExecutionId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  
  private void addReportTestExecution(String testExecutionId) throws org.apache.hc.core5.http.ParseException {
	  
	// Get the user directory
	  String userDir = System.getProperty("user.dir");

	  // Construct the file path within the user directory
	  String emailableReportFilepath = userDir + File.separator + "reports" + File.separator + "emailable-report.html";
      AddEmailableReportToJira.addHtmlAttachmentToTestExecution(testExecutionId, emailableReportFilepath);

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
        String actualResult = args.get("ActualResult");

        try {
            // Obtain the Test Case ID from the dataMap
            String testCaseId = args.get("AutomationTestcaseID");
            System.out.println("Test Case ID from dataMap: " + testCaseId);

            // Obtain the Issue ID from the dataMap
            String issueId = args.get("Issueid");
            System.out.println("Issue ID from dataMap: " + issueId);

            // Add any additional verification or logging as needed
            actualResult = actualResult + " | Received testIssueId successfully";
            args.put("ActualResult", actualResult);

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
        	
           // String xrayEndpoint = "https://xray.cloud.getxray.app/api/v2/graphql";
            //String bearerToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnQiOiJmMjQ5OTBiYS01ZjBhLTM2OTUtOWVkZC0yOWJhMDk3NTY1MjQiLCJhY2NvdW50SWQiOiI3MTIwMjA6MTFjOTFlOTktZDQyYy00ODkzLThkODAtMWNmN2Y3OWFhZjUyIiwiaXNYZWEiOmZhbHNlLCJpYXQiOjE3MDY1MTQxNjgsImV4cCI6MTcwNjYwMDU2OCwiYXVkIjoiRjUyQzQxQjY2MzdBNDM1RUEzRjExQkU5RDA0OEIzN0IiLCJpc3MiOiJjb20ueHBhbmRpdC5wbHVnaW5zLnhyYXkiLCJzdWIiOiJGNTJDNDFCNjYzN0E0MzVFQTNGMTFCRTlEMDQ4QjM3QiJ9.T0hlumlExD8uf6h3DghRthLSld-iayeICawf3plg5NY";
            
        
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
	            // Replace with your Xray Cloud endpoint and Bearer token
	            //String xrayEndpoint = "https://xray.cloud.getxray.app/api/v2/graphql";
	           // String bearerToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnQiOiJmMjQ5OTBiYS01ZjBhLTM2OTUtOWVkZC0yOWJhMDk3NTY1MjQiLCJhY2NvdW50SWQiOiI3MTIwMjA6MTFjOTFlOTktZDQyYy00ODkzLThkODAtMWNmN2Y3OWFhZjUyIiwiaXNYZWEiOmZhbHNlLCJpYXQiOjE3MDY1MTQxNjgsImV4cCI6MTcwNjYwMDU2OCwiYXVkIjoiRjUyQzQxQjY2MzdBNDM1RUEzRjExQkU5RDA0OEIzN0IiLCJpc3MiOiJjb20ueHBhbmRpdC5wbHVnaW5zLnhyYXkiLCJzdWIiOiJGNTJDNDFCNjYzN0E0MzVFQTNGMTFCRTlEMDQ4QjM3QiJ9.T0hlumlExD8uf6h3DghRthLSld-iayeICawf3plg5NY";
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
	        // Replace with your Xray Cloud endpoint and Bearer token
	        // String xrayEndpoint = "https://xray.cloud.getxray.app/api/v2/graphql";
	        // String bearerToken = "your_bearer_token_here";

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
	 
	 
	 public static void UpdateExecutionStatus(String runId) {
		    try {
		        // Replace with your Xray Cloud endpoint and Bearer token
		       // String xrayEndpoint = "https://xray.cloud.getxray.app/api/v2/graphql";
	            //String bearerToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnQiOiJmMjQ5OTBiYS01ZjBhLTM2OTUtOWVkZC0yOWJhMDk3NTY1MjQiLCJhY2NvdW50SWQiOiI3MTIwMjA6MTFjOTFlOTktZDQyYy00ODkzLThkODAtMWNmN2Y3OWFhZjUyIiwiaXNYZWEiOmZhbHNlLCJpYXQiOjE3MDY1MTQxNjgsImV4cCI6MTcwNjYwMDU2OCwiYXVkIjoiRjUyQzQxQjY2MzdBNDM1RUEzRjExQkU5RDA0OEIzN0IiLCJpc3MiOiJjb20ueHBhbmRpdC5wbHVnaW5zLnhyYXkiLCJzdWIiOiJGNTJDNDFCNjYzN0E0MzVFQTNGMTFCRTlEMDQ4QjM3QiJ9.T0hlumlExD8uf6h3DghRthLSld-iayeICawf3plg5NY";  // Replace with your actual token

		        // Replace with the desired status (PASS or FAIL)
		        String status = "FAILED";

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
	    
	    public void createDefectInJira(List<ITestResult> results) {
	        for (ITestResult result : results) {
	            JiraPolicy jiraPolicy = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(JiraPolicy.class);
	            if (jiraPolicy != null) {
	                boolean isTicketReady = jiraPolicy.logTicketReady();
	                if (isTicketReady) {
	                    System.out.println("Is Ticket Ready for JIRA: " + isTicketReady);
	                    JiraOperations jiraOp = new JiraOperations();
	                    String ProjectKey = "JIR";
	                    String Summary = "Check whether User able to click on choreography module from all modules of the home screen -" + result.getMethod().getConstructorOrMethod().getMethod().getName();
	                    String Description = "To verify Page title same as expected";
	                    String Priority = "High";
	                    String Label = "Automation";
	                    String Assignee = "panchshila";
	                    try {
	                        jiraOp.CreateJiraIssue(ProjectKey, Summary, Description, Priority,Label, Assignee);
	                    } catch (IOException | ParseException e) {
	                        e.printStackTrace();
	                    }
	                }
	            }
	        }
	    }

	

}