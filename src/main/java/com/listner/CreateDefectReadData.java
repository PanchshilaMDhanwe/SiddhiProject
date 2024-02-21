package com.listner;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CreateDefectReadData {
   
    
	public static void main(String[] args) throws FileNotFoundException, IOException {
	    // Call readReportFile first to populate scenarioDataMap
		CreateDefectReadData rd = new CreateDefectReadData();
		rd.readReportFile();

	    // Print the contents of scenarioDataMap
	   // printScenarioDataMap();
		getDefectData();
	}

    public static Map<String, Map<String, String>> scenarioDataMap = new HashMap<>(); // Declare scenarioDataMap as a public static field

    public void readReportFile() throws FileNotFoundException, IOException {
    	 // Get today's date
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        String todayDate = dateFormat.format(today);
        
        String FILE_PATH = System.getProperty("user.dir") + File.separator + "Screenshots_Reports" + File.separator + todayDate + File.separator + "Report.xls";
        String SHEET_NAME = "ExecutionReport";
        try (FileInputStream fis = new FileInputStream(FILE_PATH)) {
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheet(SHEET_NAME);

            if (sheet != null) {
                Row headerRow = sheet.getRow(0); // Assuming first row contains column headers

                // Find the index of the "Scenario ID" column
                int scenarioIdIndex = -1;
                for (int j = 0; j < headerRow.getLastCellNum(); j++) {
                    String columnName = headerRow.getCell(j).getStringCellValue();
                    if (columnName.equals("AutomationTestcaseID")) {
                        scenarioIdIndex = j;
                        break;
                    }
                }

                // Read data from each row and store it in scenarioDataMap
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);

                    // Get the Scenario ID for the current row
                    String automationTestcaseId = row.getCell(scenarioIdIndex).getStringCellValue();

                    // Create a new map to store data for this scenario
                    Map<String, String> scenarioData = new HashMap<>();

                    // Iterate through each column (except Scenario ID) and store data in the map
                    for (int j = 0; j < headerRow.getLastCellNum(); j++) {
                        if (j != scenarioIdIndex) {
                            String columnName = headerRow.getCell(j).getStringCellValue();
                            Cell cell = row.getCell(j);
                            String cellValue = (cell == null) ? "" : cell.getStringCellValue(); // Handle null cells
                            scenarioData.put(columnName, cellValue);
                        }
                    }

                    // Store the scenario data in scenarioDataMap
                   scenarioDataMap.put(automationTestcaseId, scenarioData);
                  
                    
                }
            } else {
                System.out.println("Sheet '" + SHEET_NAME + "' not found in the Excel file.");
            }
        }
    }

    public static void printScenarioDataMap() {
        for (Map.Entry<String, Map<String, String>> entry : scenarioDataMap.entrySet()) {
            String scenarioId = entry.getKey();
            Map<String, String> scenarioData = entry.getValue();
            
            System.out.println("Scenario ID: " + scenarioId);
            for (Map.Entry<String, String> dataEntry : scenarioData.entrySet()) {
                String columnName = dataEntry.getKey();
                String cellValue = dataEntry.getValue();
                System.out.println(columnName + ": " + cellValue);
            }
            System.out.println(); // Add a newline for readability
        }
    }

    public static void getDefectData()
    {
    	
    	
    }
   
    
    
//    String testCaseDescription = scenarioData.get("TestCaseDescription").trim();
//    String[] words = testCaseDescription.split("\\s+");
//    String Description = "Test Case: " + String.join(" ", words);
    
 //   String testCaseDescription = scenarioData.get("TestCaseDescription").trim();
 //   String Description = "Test Case: " + testCaseDescription;
 // String Summary = "Defect Found in "+scenarioData.get("Jira Story Id")+"-" + scenarioData.get("Module") + "-" + scenarioData.get("Submodule") + "-" + scenarioData.get("TestcaseCoveredID") + "-" + scenarioData.get("AutomationTestcaseID");
    //String TestCaseDescription = scenarioData.get("TestCaseDescription");
   // String ExpectedResult = scenarioData.get("ExpectedResult");
}
