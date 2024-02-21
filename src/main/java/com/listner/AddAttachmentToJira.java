package com.listner;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.FileBody;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import com.zebrunner.carina.utils.R;
import org.apache.hc.core5.http.ParseException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class AddAttachmentToJira {

	public static String JiraURL=R.CONFIG.get("Base.JiraURL");
	public static String JiraUserName= R.CONFIG.get("Base.JiraUserName");
	public static String JiraToken= R.CONFIG.get("Base.JiraToken");
	
    public static void addAttachmentToTestExecution(String testExecutionId, String filePath) throws ParseException {
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
            entityBuilder.addPart("file", new FileBody(new File(filePath), ContentType.APPLICATION_OCTET_STREAM, Paths.get(filePath).getFileName().toString()));
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

    public static void createAndAttachZip(String sourceFolderPath, String destinationFolderPath, String zipFileName, String testExecutionId) throws IOException, ParseException {
        try (FileOutputStream fos = new FileOutputStream(destinationFolderPath + File.separator + zipFileName);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            File sourceFolder = new File(sourceFolderPath);
            zipFile(sourceFolder, sourceFolder.getName(), zos);

            String zippedFilePath = destinationFolderPath + File.separator + zipFileName;
            addAttachmentToTestExecution(testExecutionId, zippedFilePath);
        }
    }

    private static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFile(childFile, fileName + File.separator + childFile.getName(), zipOut);
            }
        } else {
            try (FileInputStream fis = new FileInputStream(fileToZip)) {
                ZipEntry zipEntry = new ZipEntry(fileName);
                zipOut.putNextEntry(zipEntry);
                byte[] bytes = new byte[1024];
                int length;
                while ((length = fis.read(bytes)) >= 0) {
                    zipOut.write(bytes, 0, length);
                }
            }
        }
    }
}
