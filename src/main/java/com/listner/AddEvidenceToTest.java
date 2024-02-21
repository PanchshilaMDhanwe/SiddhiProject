package com.listner;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.json.JSONObject;
import com.zebrunner.carina.utils.R;
public class AddEvidenceToTest {

	private static final String xrayEndpoint = R.CONFIG.get("Base.xrayEndpoint");
    private static final String bearerToken = TokenStorage.readTokenFromFile();
    private static String zipFolderPath = System.getProperty("user.dir")+File.separator+"ZipTestCases"+File.separator;  // Provide the actual folder path
    private static String updateEvidence(String testRunId, String zipFileName, String base64EncodedZip) {
        return "mutation {\n" +
                "    addEvidenceToTestRun(\n" +
                "        id: \"" + testRunId + "\",\n" +
                "        evidence: [\n" +
                "            {\n" +
                "                filename: \"" + zipFileName + "\",\n" +
                "                mimeType: \"application/zip\",\n" +
                "                data: \"" + base64EncodedZip + "\"\n" +
                "            }\n" +
                "        ]\n" +
                "    ) {\n" +
                "        addedEvidence\n" +
                "        warnings\n" +
                "    }\n" +
                "}";
    
}
    public static void addEvidence(String testRunId, String testCaseId) throws ParseException {
        try {
            // Assuming the zip folder path is defined correctly and accessible
            File folder = new File(zipFolderPath);
            File[] zipFiles = folder.listFiles();

            if (zipFiles != null) {
                for (File zipFile : zipFiles) {
                    // Extract the test case ID from the zip file name
                    String zipFileName = zipFile.getName();
                    String extractedTestCaseId = zipFileName.replace(".zip", "");

                    // Check if the zip file name matches the current testCaseId
                    if (extractedTestCaseId.equals(testCaseId)) {
                        // Read the binary data of the zip file
                        byte[] zipData = Files.readAllBytes(zipFile.toPath());

                        // Encode the binary data as base64
                        String base64EncodedZip = Base64.getEncoder().encodeToString(zipData);

                        // Build the GraphQL query dynamically
                        String graphQLQuery = updateEvidence(testRunId, zipFileName, base64EncodedZip);

                        // Create a JSON object with the GraphQL query
                        JSONObject jsonRequest = new JSONObject();
                        jsonRequest.put("query", graphQLQuery);

                        // Create the HTTP client and execute the request
                        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                            HttpPost httpPost = new HttpPost(xrayEndpoint);
                            httpPost.setHeader("Authorization", "Bearer " + bearerToken);
                            httpPost.setHeader("Content-Type", "application/json");
                            httpPost.setEntity(new StringEntity(jsonRequest.toString()));

                            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                                // Print the response status code and body
                                System.out.println("Response Code: " + response.getCode());
                                System.out.println("Response Body: " + EntityUtils.toString(response.getEntity()));
                            }
                        }
                        break; // No need to continue searching for other zip files
                    }
                }
            } else {
                System.out.println("No zip files found in the specified folder: " + zipFolderPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    private static String getZipFilePathByTestCaseId(String testCaseId) {
//        // Assuming the zip file name is the same as the test case ID with a .zip extension
//        String zipFileName = testCaseId + ".zip";
//        File zipFile = new File(zipFolderPath + File.separator + zipFileName);
//        if (zipFile.exists()) {
//            return zipFile.getAbsolutePath();
//        }
//        return null;
//    }

//    public static void main(String[] args) {
//        // Store the provided testRunIds in a list
//        List<String> testRunIds = new ArrayList<>();
//        testRunIds.add("65c1250dea192e5461077aa2");
//        testRunIds.add("65c12510ea192e5461077d1d");
//
//        // Assuming you have a folder path where zip files are stored
//        String zipFolderPath = "C:\\Users\\ec1003au\\Documents\\ZipTest";
//
//        // Loop through testRunIds and trigger the addEvidence method for each testRunId
//        for (String testRunId : testRunIds) {
//            // Get the corresponding zip file for the current testRunId
//            String zipFilePath = getZipFilePath(zipFolderPath);
//
//            if (zipFilePath != null) {
//                // Call the addEvidence method
//            	AddEvidenceToTest.addEvidence(zipFilePath, testRunId);
//            } else {
//                System.out.println("No more zip files found in the specified folder: " + zipFolderPath);
//            }
//        }
//    }
}
