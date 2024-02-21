package com.listner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipTestCases {
	
    public void zipTestCases(String baseFolder, String destinationFolder) {
        File baseDir = new File(baseFolder);

        // Check if the base directory exists
        if (!baseDir.exists() || !baseDir.isDirectory()) {
            System.out.println("Base directory does not exist or is not a directory.");
            return;
        }

        // Get today's date
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        String todayDate = dateFormat.format(today);

        // Iterate through date folders
        for (File dateFolder : baseDir.listFiles()) {
            if (dateFolder.isDirectory() && dateFolder.getName().equals(todayDate)) {
                // Iterate through module folders
                for (File moduleFolder : dateFolder.listFiles()) {
                    if (moduleFolder.isDirectory()) {
                        // Iterate through submodule folders
                        for (File subModuleFolder : moduleFolder.listFiles()) {
                            if (subModuleFolder.isDirectory()) {
                                // Iterate through testcaseid folders
                                for (File testCaseFolder : subModuleFolder.listFiles()) {
                                    if (testCaseFolder.isDirectory()) {
                                        // Zip the testcaseid folder
                                        zipFolder(testCaseFolder.getAbsolutePath(),
                                                destinationFolder + File.separator + testCaseFolder.getName() + ".zip");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void zipFolder(String sourceFolderPath, String zipFilePath) {
        try (FileOutputStream fos = new FileOutputStream(zipFilePath);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            File sourceFolder = new File(sourceFolderPath);
            zipFile(sourceFolder, sourceFolder.getName(), zos);

            System.out.println("Folder successfully zipped: " + zipFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isDirectory()) {
            for (File childFile : fileToZip.listFiles()) {
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

                zipOut.closeEntry();
            }
        }
    }
    
    public void deleteExistingZips(String destinationFolder) {
        File destDir = new File(destinationFolder);

        // Check if the destination directory exists
        if (!destDir.exists() || !destDir.isDirectory()) {
            System.out.println("Destination directory does not exist or is not a directory.");
            return;
        }

        // Delete existing zip files
        for (File file : destDir.listFiles()) {
            if (file.isFile() && file.getName().endsWith(".zip")) {
                if (file.delete()) {
                    System.out.println("Deleted existing zip file: " + file.getName());
                } else {
                    System.out.println("Failed to delete existing zip file: " + file.getName());
                }
            }
        }
    }
}

