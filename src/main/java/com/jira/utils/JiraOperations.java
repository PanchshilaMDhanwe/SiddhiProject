package com.jira.utils;
import java.io.IOException;
import java.util.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.zebrunner.carina.utils.R;

public class JiraOperations {
		
	String JiraURL=R.CONFIG.get("Base.JiraURL");
	String JiraUserName= R.CONFIG.get("Base.JiraUserName");
	String JiraToken= R.CONFIG.get("Base.JiraToken");
	
	//Create jira Issue as bug
	public String CreateJiraIssue(String projectName, String issueSummary, String issueDescription,  String priority, String label, String assignee) throws IOException, ParseException 
	{
		String issueid = null; //to store issue/bug id
		HttpClient httpClient = HttpClientBuilder.create().build();
		String url = JiraURL+"/rest/api/3/issue";
		HttpPost postRequest = new HttpPost(url);
		postRequest.setHeader("Content-Type", "application/json");
		postRequest.setHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString((JiraUserName + ":" + JiraToken).getBytes()));
		StringEntity params = new StringEntity(CreatePayLoadForCreateJiraIssue(projectName, issueSummary, issueDescription,  priority, label, assignee));
		postRequest.setEntity(params);
		HttpResponse response = httpClient.execute(postRequest);
		
		//convert httpresponse to string
		String jsonString = EntityUtils.toString(response.getEntity());
		//convert String to json
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(jsonString);
		//extract issuekey from json
		issueid = (String) json.get("key");
		 if (response.getStatusLine().getStatusCode() == 201) {
             System.out.println("Defect created successfully. Issue key: " + response.getEntity().getContent().toString());
         } else {
             System.out.println("Failed to create defect. Status code: " + response.getStatusLine().getStatusCode());
         }
		
		return issueid;	
	}

	private static String CreatePayLoadForCreateJiraIssue(String projectName, String issueSummary, String issueDescription,
			 String priority, String label, String assignee) {
		
		return "{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \""+projectName+"\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \""+issueSummary+"\",\r\n"
				+ "       \"description\": {\r\n"
				+ "          \"type\": \"doc\",\r\n"
				+ "          \"version\": 1,\r\n"
				+ "          \"content\": [\r\n"
				+ "            {\r\n"
				+ "              \"type\": \"paragraph\",\r\n"
				+ "              \"content\": [\r\n"
				+ "                {\r\n"
				+ "                  \"type\": \"text\",\r\n"
				+ "                  \"text\": \""+issueDescription+"\"\r\n"
				+ "                }\r\n"
				+ "              ]\r\n"
				+ "            }\r\n"
				+ "          ]\r\n"
				+ "        },\r\n"
				+ "    \"priority\": {\r\n"
				+ "      \"name\": \""+priority+"\"\r\n"
				+ "    },\r\n"
				+ "    \r\n"
				+ "    \"labels\": [\r\n"
				+ "      \"bugfix\",\r\n"
				+ "      \"blitz_test\"\r\n"
				+ "    ],\r\n"
				+ "    \"issuetype\": {\r\n"
				+ "        \"name\": \"Bug\"\r\n"
				+ "    },\r\n"
				+ "     \"assignee\": {\r\n"
				+ "      \"name\": \"Panchshila\"\r\n"
				+ "    }\r\n"
				+ "   \r\n"
				+ "    }\r\n"
				+ "}";
	}
	
} 

