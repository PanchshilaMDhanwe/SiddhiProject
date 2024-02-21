package com.listner;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

public class TokenGenerationAutomation {
    private static final String AUTH_ENDPOINT = "https://xray.cloud.getxray.app/api/v2/authenticate";
    private static final String CLIENT_ID = "87EF8714232A4F9E8D9C504D025C894B";
    private static final String CLIENT_SECRET = "0d15af350a517a815a9dd1c8d26577525cf84d55877818f1deee8391454059ef";
    private static String token;

    public static String generateOrRetrieveApiToken() {
        String storedToken = TokenStorage.readTokenFromFile();
        if (storedToken != null && !isTokenExpired(storedToken)) {
            return storedToken;
        } else {
            token = regenerateToken();
            TokenStorage.writeTokenToFile(token);
            return token;
        }
    }

    private static boolean isTokenExpired(String token) {
        try {
            // Decode the JWT token
            DecodedJWT jwt = JWT.decode(token);

            // Extract the expiration claim
            Date expiration = jwt.getExpiresAt();

            // Convert the expiration date to Instant
            Instant expirationInstant = expiration.toInstant();

            // Get the current time
            Instant currentTime = Instant.now();

            // Compare the expiration time with the current time
            return expirationInstant.isBefore(currentTime);
        } catch (JWTDecodeException e) {
            // Handle decoding exception
            e.printStackTrace();
            return true; // Treat decoding failure as token expired
        }
    }

    private static String regenerateToken() {
        // Create HttpClient
        HttpClient httpClient = HttpClients.createDefault();

        // Create HttpPost with URL
        HttpPost httpPost = new HttpPost(AUTH_ENDPOINT);

        // Set headers
        httpPost.setHeader("Content-Type", "application/json");

        // JSON request body
        String requestBody = "{\"client_id\": \"" + CLIENT_ID + "\", \"client_secret\": \"" + CLIENT_SECRET + "\"}";

        try {
            // Set request body
            StringEntity requestEntity = new StringEntity(requestBody);
            httpPost.setEntity(requestEntity);

            // Execute the request
            HttpResponse response = httpClient.execute(httpPost);

            // Get the response body
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, StandardCharsets.UTF_8);

            // Extract the token from the response
            String token = extractTokenFromResponse(responseString);

            // You can save the token to storage for future use

            return token;

        } catch (IOException e) {
            // Handle the exception gracefully, perhaps by returning a default token or rethrowing the exception
            e.printStackTrace();
            return null;
        }
    }

    private static String extractTokenFromResponse(String responseString) {
        // Debugging: Print out the responseString to check its content
        System.out.println("Response String: " + responseString);

        // Remove the surrounding quotes from the response string
        responseString = responseString.replaceAll("^\"|\"$", "");

        try {
            // Decode the JWT token
            DecodedJWT decodedJWT = JWT.decode(responseString);

            // Extract the token from the decoded JWT
            token = decodedJWT.getToken();

            // Return the extracted token
            return token;
        } catch (JWTDecodeException e) {
            // If there's an error decoding the JWT, handle it gracefully
            System.err.println("Error decoding JWT: " + e.getMessage());
            return null;
        }
    }
}
