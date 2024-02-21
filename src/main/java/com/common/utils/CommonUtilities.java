package com.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zebrunner.carina.core.config.ReportConfiguration;
import com.zebrunner.carina.utils.report.ReportContext;

public class CommonUtilities {

	private static String folderPath;
	private static String texstcaseFolder;
	private static String todaydate;
	private static String executionDate;

	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public void FolderCreate() {

		Date currDate = new Date();
		SimpleDateFormat oDF = new SimpleDateFormat("dd-MMM-yyyy");
		todaydate = oDF.format(currDate);
		String projectPath = System.getProperty("user.dir");

		try {
			
			
			String sFullScreenShotPath = projectPath + File.separator + "Screenshots_Reports" + File.separator
					+ todaydate;
			//String sFullScreenShotPath = ReportConfiguration.getTestLogLink().split("file://")[1].split("/")[0];
			
			if (new File(sFullScreenShotPath).exists()) {
				// for(File file: new File(sFullScreenShotPath).listFiles()) file.delete();
			} else {
				new File(sFullScreenShotPath).mkdirs();
			}
			System.out.println("Folder Created");
			folderPath = sFullScreenShotPath;
			System.out.println(sFullScreenShotPath);

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public void TCFolderCreate(HashMap<String, String> args) {

		try {
			args.put("screenNum", "0");
			args.put("actualResult", "");
			args.put("status", "");
			Date curDate = new Date();
			SimpleDateFormat DF = new SimpleDateFormat("dd-MMM-yyyy");
			todaydate = DF.format(curDate);
			Date currDate = new Date();
			SimpleDateFormat oDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			executionDate = oDF.format(currDate);
			args.put("executionDate", executionDate);

			texstcaseFolder = (folderPath + File.separator + args.get("Module").trim() + File.separator
					+ args.get("Submodule").trim() + File.separator + args.get("AutomationTestcaseID"))
					.replace("/", "_");

			//texstcaseFolder = (folderPath + File.separator + args.get("AutomationTestcaseID"))
			//		.replace("/", "_");
			
			//ReportConfiguration.generateHtmlReport(folderPath + File.separator +todaydate);
			
			
			if (new File(texstcaseFolder).exists()) {
				for (File file : new File(texstcaseFolder).listFiles())
					file.delete();
			} else {
				new File(texstcaseFolder).mkdirs();
			}
			System.out.println("TC Folder Created");
			args.put("folderPath", texstcaseFolder);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void CreateWorkbook() throws Exception {

		File file = new File(folderPath  + "\\Report.xls");
		// Boolean b = file.exists();
		if (file.exists()) {
			// HSSFWorkbook workbook = new HSSFWorkbook();
			// HSSFSheet spreadsheet = workbook.getSheet("ExecutionReport");
			// int rowtot= spreadsheet.getLastRowNum();
			// System.out.println("Number of rows:- "+rowtot);
			// file.delete();

		} else {
			HSSFWorkbook workbook = new HSSFWorkbook();
			/*
			 * HSSFSheet spreadsheet = workbook.createSheet("CompareResult");
			 * 
			 * String [] columnHeadings=
			 * {"TestCaseID","Expected_Source","Actual_Source","Expected_SLA","Actual_SLA",
			 * "Expected_SubSubCategory","Actual_SubSubCategory","Result"}; Row
			 * headerRow=spreadsheet.createRow(0); for(int i=0;i<columnHeadings.length;i++)
			 * { Cell cell=headerRow.createCell(i); cell.setCellValue(columnHeadings[i]);
			 * System.out.println(columnHeadings[i]); }
			 * 
			 * System.out.println("Compare Sheet Created");
			 */
			HSSFSheet spreadsheet1 = workbook.createSheet("ExecutionReport");
			String[] columnHeadings1 = { "Module", "Submodule", "Scenario ID", "Device", "FSD Ref no",
					"AutomationTestcaseID", "TestcaseCoveredID", "TestCaseDescription", "Test Case Type",
					"ExpectedResult", "ActualResult", "ExecutionResult", "Status", "ExecutionDateTime",
					"ExecutionDuration", "ScreenshotPath","Jira Story Id" };
			Row headerRow1 = spreadsheet1.createRow(0);
			CellStyle headerCellStyle = workbook.createCellStyle();
			HSSFFont font = workbook.createFont();
			font.setFontHeightInPoints((short) 10);
			font.setBold(true);
			font.setColor(IndexedColors.WHITE.getIndex());
			headerCellStyle.setFont(font);
			headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
			headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//			for (int i = 0; i < columnHeadings1.length; i++) {
//				Cell cell = headerRow1.createCell(i);
//				cell.setCellValue(columnHeadings1[i]);
//				System.out.println(columnHeadings1[i]);
//
//			}
			for (int i = 0; i < columnHeadings1.length; i++) {
				Cell cell = headerRow1.createCell(i);
				cell.setCellValue(columnHeadings1[i]);
				cell.setCellStyle(headerCellStyle);
				if (i == 0) {
					// Left border for the first cell
					cell.getCellStyle().setBorderLeft(BorderStyle.THIN);
				}
				if (i == columnHeadings1.length - 1) {
					// Right border for the last cell
					cell.getCellStyle().setBorderRight(BorderStyle.THIN);
				}
				// Top and bottom borders for all cells in the header row
				cell.getCellStyle().setBorderTop(BorderStyle.THIN);
				cell.getCellStyle().setBorderBottom(BorderStyle.THIN);

				LOGGER.info(columnHeadings1[i]);

			}
			System.out.println("Execution Sheet Created");

			FileOutputStream fos = new FileOutputStream(file);
			workbook.write(fos);
			fos.close();
			workbook.close();

		}

	}

	public void WriteToExcel(HashMap<String, String> args) throws Exception {
		try {
			System.out.println("inside write");
			Date oRCEndTime = new Date();
			SimpleDateFormat oDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// executionDate = oDF.format(oStartTime);
			String executionDate1 = oDF.format(oRCEndTime);
			Date d1 = null;
			Date d2 = null;
			d1 = oDF.parse(executionDate);
			d2 = oDF.parse(executionDate1);
			long diff = d2.getTime() - d1.getTime();
			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000);
			String ss = Long.toString(diffSeconds);
			String mm = Long.toString(diffMinutes);
			String hh = Long.toString(diffHours);
			String executionTime = hh + ":" + mm + ":" + ss;
			System.out.println(executionTime);
			System.out.println("ExecutionTime:- " + executionTime);
			args.put("executionTime", executionTime);
			String sactualresult = args.get("ActualResult");
			System.out.println("STATUS : " + args.get("status").equals(""));
			if (args.get("status").equals("") || (args.get("status").equalsIgnoreCase("Pass"))) {
				args.put("status", "Pass");
				sactualresult = "Test case executed successfully | " + sactualresult;
			} else {
				args.put("status", "Fail");
				sactualresult = "Test case executed failed | " + sactualresult;
			}
			args.put("ActualResult", sactualresult);
			writeExecutionResult(args);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void writeExecutionResult(HashMap<String, String> args) throws Exception {
		try {

			String ExcelReportPath = System.getProperty("user.dir") + File.separator + "Screenshots_Reports"
					+ File.separator + todaydate;
			
			//String ExcelReportPath = folderPath;
			
			System.out.println("inside execution result ");
			String fNAme = ExcelReportPath + "\\Report.xls";
			String sheetName = "ExecutionReport";
			File file = new File(fNAme);
			FileInputStream inputStream = new FileInputStream(file);
			HSSFWorkbook Workbook = new HSSFWorkbook(inputStream);
			Sheet sheet = Workbook.getSheet(sheetName);
			int totRow = sheet.getLastRowNum();
			HSSFFont font = Workbook.createFont();
			font.setBold(true);
			CellStyle passCellStyle = Workbook.createCellStyle();
			passCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
			passCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			passCellStyle.setFont(font);
			CellStyle failCellStyle = Workbook.createCellStyle();
			failCellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
			failCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			failCellStyle.setFont(font);
			totRow = totRow + 1;
			Row row = sheet.createRow(totRow++);
			row.createCell(0).setCellValue(args.get("Module"));
			row.createCell(1).setCellValue(args.get("Submodule"));
			row.createCell(2).setCellValue(args.get("Scenario ID"));
			row.createCell(3).setCellValue(args.get("Device"));
			row.createCell(4).setCellValue(args.get("FSD Ref no"));
			row.createCell(5).setCellValue(args.get("AutomationTestcaseID"));
			row.createCell(6).setCellValue(args.get("CoveredTestCaseID"));
			row.createCell(7).setCellValue(args.get("TestCaseDescription"));
			row.createCell(8).setCellValue(args.get("Test Case Type"));
			row.createCell(9).setCellValue(args.get("ExpectedResult"));
			row.createCell(10).setCellValue(args.get("ActualResult"));
			row.createCell(11).setCellValue(args.get("ExecutionResult"));
			Cell cell = row.createCell(12);
			cell.setCellValue(args.get("status"));
			if ("Pass".equals(args.get("status"))) {
				cell.setCellStyle(passCellStyle);

			} else if ("Fail".equals(args.get("status"))) {
				cell.setCellStyle(failCellStyle);
			}
			row.createCell(13).setCellValue(args.get("executionDate"));
			row.createCell(14).setCellValue(args.get("executionTime"));
			row.createCell(15).setCellValue(args.get("screenPath"));
			row.createCell(16).setCellValue(args.get("Jira Story Id"));
			inputStream.close();
			FileOutputStream outputStream = new FileOutputStream(file);
			Workbook.write(outputStream);
			Workbook.close();
			outputStream.close();
			LOGGER.info("Completed");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void takeScreenshot(HashMap<String, String> args, WebDriver driver, String pageName)
			throws IOException, InterruptedException {

		String scrPath = "";
		int iScreenNum;
		iScreenNum = Integer.parseInt(args.get("screenNum")) + 1;
		args.put("screenNum", iScreenNum + "");
		// add 3 leading zeros in this number
		String padded = String.format("%05d", iScreenNum);
		System.out.println("Inside CaptureScreenshot");

		scrPath = (texstcaseFolder + "\\" + padded + "-" + pageName + ".jpg").replace("/", "_");

		System.out.println("Screenpath:- " + scrPath);
		args.put("screenPath", texstcaseFolder);

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(scrPath));

	}

	public String assertEquals(String actualValue, String expectedValue, HashMap<String, String> args)
			throws Exception {
    	String actualResult = args.get("ActualResult");
    	System.out.println(actualResult);


		//String msg = null;
		if (actualValue.equals(expectedValue)) {
			actualResult = " | ActualValue [" + expectedValue + "] and ExpectedValue Text [" + actualValue + "] Matched | ";
			System.out.println(
					" | actualValue [" + expectedValue + "] and ExpectedValue [" + actualValue + "] Matched | ");
		} else {
			actualResult+= " |*** ActualValue [" + expectedValue + "] and ExpectedValue [" + actualValue + "] Not Matched ***| ";
			System.err.println("*** | actualValue [" + expectedValue + "] and ExpectedValue [" + actualValue
					+ "] Not Matched ***| ");
			args.put("status", "Fail");
			 args.put("ActualResult",actualResult);
			 String actResult = args.get("ActualResult");
			 System.out.println(actResult);
			 
			throw new Exception("assertfail");
		}
		return actualResult;

	}

}
