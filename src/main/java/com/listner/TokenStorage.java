package com.listner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TokenStorage {

	private static final String TOKEN_FILE_PATH = System.getProperty("user.dir") + File.separator + "src" + File.separator +
            "test" + File.separator + "resources" + File.separator + "data_source" + File.separator + "token.txt";

    public static String readTokenFromFile() {
        try {
            // Check if the token file exists
            File tokenFile = new File(TOKEN_FILE_PATH);
            if (tokenFile.exists()) {
                String token = new String(Files.readAllBytes(Paths.get(TOKEN_FILE_PATH)));
                return token;
            }
        } catch (IOException e) {
            // Handle file reading exception
            e.printStackTrace();
        }
        // If the file doesn't exist or is empty, return null
        return null;
    }

    public static void writeTokenToFile(String token) {
        try {
            FileWriter writer = new FileWriter(TOKEN_FILE_PATH);
            writer.write(token);
            writer.close();
        } catch (IOException e) {
            // Handle file writing exception
            e.printStackTrace();
        }
    }
}
