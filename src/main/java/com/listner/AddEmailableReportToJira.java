package com.listner;


import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.FileBody;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class AddEmailableReportToJira {

    static String JiraURL = "https://pdhanwe.atlassian.net";
    static String JiraUserName = "pdhanwe@gmail.com";
    static String JiraToken = "ATATT3xFfGF0PtQG7UV5ux8PA22WfVr-pipnY5eGHOLNHV9jIewH36vcU3vM9loETZxN79q0UXHB6kgsHKMkPe-rOCAMO1Lr9x-w5cyoq5flw4lOnsVjmc3_v71WuuF_megd6YxPWJgXyYq76Douyo-a9ew75qdzYFp3OPKGgr7-lcZz_AxcayI=E537FF95";

    public static void addHtmlAttachmentToTestExecution(String testExecutionId, String htmlFilePath) throws ParseException {
        // Replace "YOUR_ISSUE_ID" with the actual Jira issue ID or key
        String testExecutionIssueId = testExecutionId;
        

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String url = JiraURL + "/rest/api/3/issue/" + testExecutionIssueId + "/attachments";
            HttpPost postRequest = new HttpPost(url);

            // Set the authorization header
            String authHeader = "Basic " + Base64.getEncoder().encodeToString((JiraUserName + ":" + JiraToken).getBytes());
            postRequest.setHeader("Authorization", authHeader);
            postRequest.setHeader("X-Atlassian-Token", "nocheck");

            // Build the multipart entity with the file attachment
            MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
            entityBuilder.addPart("file", new FileBody(new File(htmlFilePath), ContentType.APPLICATION_OCTET_STREAM, new File(htmlFilePath).getName()));
            postRequest.setEntity(entityBuilder.build());

            // Execute the request
            try (CloseableHttpResponse response = httpClient.execute(postRequest)) {
                int statusCode = response.getCode();
                String responseBody = EntityUtils.toString(response.getEntity());

                // Handle the response as needed
                System.out.println("Response Code: " + statusCode);
                System.out.println("Response Body: " + responseBody);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  
}
