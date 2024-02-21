package com.common.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.invoke.MethodHandles;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zebrunner.carina.utils.report.ReportContext;

public class CommonAPIUtilities {

	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private static String executionDate;
	public static String sPath;
	public static String parent = "";
	private static String folderPath;
	private static String texstcaseFolder;

	private static String todaydate;

	public static String FolderCreate()

	{
		
		Date currDate = new Date();
		SimpleDateFormat oDF = new SimpleDateFormat("dd-MM-yyyy");
		todaydate = oDF.format(currDate);
		String projectPath = System.getProperty("user.dir");
		String sFullScreenShotPath = null;
		
		try {
			sFullScreenShotPath = projectPath + File.separator + "APIReport" + File.separator + todaydate;
			if (new File(sFullScreenShotPath).exists()) {
				// for(File file: new File(sFullScreenShotPath).listFiles()) file.delete();
			} else {
				new File(sFullScreenShotPath).mkdirs();
			}
			System.out.println("Folder Created");

			folderPath = sFullScreenShotPath;
			System.out.println(folderPath);
			System.out.println("");

		} catch (Exception e) {
			e.printStackTrace();

		}
		return sFullScreenShotPath;
	}

	public static String TCFolderCreate(HashMap<String, String> args) {

		try {
			
			Date currDate = new Date();
			SimpleDateFormat oDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			executionDate = oDF.format(currDate);
			args.put("executionDate", executionDate);
			
			texstcaseFolder = (folderPath + "\\" + args.get("Module") + "\\" + args.get("Submodule") + "\\"
					+ args.get("TestCaseID")).replace("/", "_");
			if (new File(texstcaseFolder).exists()) {
				// for(File file: new File(texstcaseFolder).listFiles()) file.delete();
			} else {
				new File(texstcaseFolder).mkdirs();
			}
			System.out.println("Folder Created");
			args.put("folderPath", texstcaseFolder);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return texstcaseFolder;
	}

	public static void CreateWorkbook() throws Exception

	{

		File file = new File(folderPath + "\\Report.xls");
		// Boolean b = file.exists();
		if (file.exists()) {

		} else {
			HSSFWorkbook workbook = new HSSFWorkbook();

			HSSFSheet spreadsheet1 = workbook.createSheet("ExecutionReport");
			// Kajal - 09/05/2023 Changes added to add headers for POS dispatch module
			String[] columnHeadings1 = { "Module", "Submodule", "Scenario ID", "User story Ref.No","TestCaseID", "TestCaseDescription", "Test Case Type", "ExpectedResult",
					"ActualResult", "ExecutionResult", "Status", "ExecutionDateTime", "ExecutionDuration",
					"JsonFileReportPath" };
			Row headerRow1 = spreadsheet1.createRow(0);
			for (int i = 0; i < columnHeadings1.length; i++) {
				Cell cell = headerRow1.createCell(i);
				cell.setCellValue(columnHeadings1[i]);
				System.out.println(columnHeadings1[i]);

			}
			System.out.println("Execution Sheet Created");

			FileOutputStream fos = new FileOutputStream(file);
			workbook.write(fos);
			fos.close();
			workbook.close();

		}

	}

	public static void WriteToExcel(HashMap<String, String> args) throws Exception {
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

	private static void writeExecutionResult(HashMap<String, String> args) throws Exception {
		try {

			String ExcelReportPath = System.getProperty("user.dir") + File.separator + "APIReport"
					+ File.separator + todaydate;

			System.out.println("inside execution result ");
			String fNAme = ExcelReportPath + "\\Report.xls";
			String sheetName = "ExecutionReport";
			// rowNo2=1;
			File file = new File(fNAme);
			FileInputStream inputStream = new FileInputStream(file);
			try (HSSFWorkbook Workbook = new HSSFWorkbook(inputStream)) {
				Sheet sheet = Workbook.getSheet(sheetName);
				int totRow = sheet.getLastRowNum();

				totRow = totRow + 1;
				Row row = sheet.createRow(totRow++);
				row.createCell(0).setCellValue(args.get("Module"));
				row.createCell(1).setCellValue(args.get("Submodule"));
				row.createCell(2).setCellValue(args.get("Scenario ID"));
				row.createCell(3).setCellValue(args.get("User story Ref.No"));
				row.createCell(4).setCellValue(args.get("TestCaseID"));
				row.createCell(5).setCellValue(args.get("TestCaseDescription"));
				row.createCell(6).setCellValue(args.get("Test Case Type"));
				row.createCell(7).setCellValue(args.get("ExpectedResult"));
				row.createCell(8).setCellValue(args.get("ActualResult"));
				row.createCell(9).setCellValue(args.get("ExecutionResult"));
				row.createCell(10).setCellValue(args.get("status"));
				row.createCell(11).setCellValue(args.get("executionDate"));
				row.createCell(12).setCellValue(args.get("executionTime"));
				row.createCell(13).setCellValue(args.get("JsonFileReportPath"));

				inputStream.close();
				FileOutputStream outputStream = new FileOutputStream(file);
				Workbook.write(outputStream);
				outputStream.close();
			}
			System.out.println("Completed");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void SaveReports(HashMap<String, String> datamap, String requestBody, String responseBody) {
		String strReportPath = System.getProperty("user.dir");

		createtxt(ReportContext.getTestDirectory() + "\\Request.json", requestBody);
		createtxt(ReportContext.getTestDirectory() + "\\Response.json", responseBody);

		try {
			BufferedWriter actualwriter = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(ReportContext.getTestDirectory() + "\\VP_Report.txt"), "utf-8"));
			System.out.println(actualwriter);
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			String srcDirPat = (ReportContext.getTestDirectory()).toString();
			srcDirPat = srcDirPat.replace("\\.\\", "\\");
			System.out.println(srcDirPat);

			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();
			String TodayDate = formatter.format(date);
			System.out.println(TodayDate);

			System.out.println(datamap.get("TestCaseID"));
			String destDirPat = strReportPath;
			String folderpath = destDirPat + File.separator + "APIReport" + File.separator + TodayDate + File.separator
					+ datamap.get("Module") + File.separator + datamap.get("Submodule") + File.separator
					+ datamap.get("TestCaseID") + File.separator;
			System.out.println(folderpath);
			datamap.put("JsonFileReportPath", folderpath);
			if (new File(folderpath).exists()) {
				// for(File file: new File(folderpath).listFiles()) file.delete();
				File srcDirFile = new File(srcDirPat);

				File folder = new File(srcDirPat);
				File[] listOfFiles = folder.listFiles();

				for (File file : listOfFiles) {
					if (file.isFile()) {
						File srcx = new File(srcDirFile + File.separator + file.getName());
						File dest = new File(folderpath + File.separator + file.getName());
						if (dest.exists()) {
							dest.delete();

						}
						FileUtils.copyFile(srcx, dest);
					}

				}
			} else {
				new File(folderpath).mkdirs();

				File srcDirFile = new File(srcDirPat);

				File folder = new File(srcDirPat);
				File[] listOfFiles = folder.listFiles();

				for (File file : listOfFiles) {
					if (file.isFile()) {
						File srcx = new File(srcDirFile + File.separator + file.getName());
						File dest = new File(folderpath + File.separator + file.getName());
						if (dest.exists()) {
							dest.delete();

						}
						FileUtils.copyFile(srcx, dest);
					}

				}
			}
			System.out.println("Folder Created");
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.info("Artifacts copy failed : " + ex.getMessage());

		}

	}

	public static void createtxt(String sFilePath, String sFileData) {
		BufferedWriter bw = null;
		FileWriter fw = null;
		// String sExcelFilePath = "";
		System.out.println("Inside Createtxt");
		try {
			File file = new File(sFilePath);
			if (file.createNewFile()) {
				System.out.println("File is created!");
			} else {
				System.out.println("File already exists.");
				file.delete();
				file.createNewFile();
			}
			fw = new FileWriter(sFilePath);
			bw = new BufferedWriter(fw);
			bw.write(sFileData);
			System.out.println("Done");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}
