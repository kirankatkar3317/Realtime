package Utilities;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.*;

import org.apache.poi.ss.formula.eval.ErrorEval;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class ExcelReader {
	private String filePath;
	private String sheetName;
	XSSFSheet sheet;

	public ExcelReader(String filePath, String sheetName) {
		this.filePath = filePath;
		this.sheetName = sheetName;
	}

	private XSSFSheet getSheet() throws IOException {
		FileInputStream fis = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		return sheet;
	}

	public Map<String, Map<String, String>> getExcelAsMap() throws IOException {
		DataFormatter formatter = new DataFormatter();
		XSSFSheet sheet = getSheet();
		Map<String, Map<String, String>> completeSheetData = new HashMap<String, Map<String, String>>();
		List<String> columnHeader = new ArrayList<String>();
		Row row = sheet.getRow(0);
		Iterator<Cell> cellIterator = row.cellIterator();
		while (cellIterator.hasNext()) {
			columnHeader.add(cellIterator.next().getStringCellValue());
		}
		int rowCount = sheet.getLastRowNum();
		int columnCount = row.getLastCellNum();
		for (int i = 1; i <= rowCount; i++) {
			Map<String, String> singleRowData = new HashMap<String, String>();
			Row row1 = sheet.getRow(i);
			for (int j = 0; j < columnCount; j++) {
				Cell cell = row1.getCell(j);
				singleRowData.put(columnHeader.get(j), formatter.formatCellValue(cell));
			}
			completeSheetData.put(String.valueOf(i), singleRowData);
		}
		
		return completeSheetData;
	}

	public String formatCellValue(Cell cell, FormulaEvaluator evaluator) {
		return formatCellValue(cell, evaluator);
	}

	public String getSheetName(int index) throws IOException {
		FileInputStream file = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		String sheet = workbook.getSheetName(index);
		return sheet;
	}

	public int getSheetCount() throws IOException {
		FileInputStream file = new FileInputStream(filePath);
		HSSFWorkbook workbook = new HSSFWorkbook(file);
		int sheetCount = workbook.getNumberOfSheets();
		return sheetCount;
	}

	public int totolColumnCount() throws IOException {
		XSSFSheet sheet = getSheet();
		Row row = sheet.getRow(0);
		int lastColumnNum = row.getLastCellNum();
		return lastColumnNum;
	}

	/****************/

	public static Object[][] getTableArray(String FilePath, String SheetName) throws Exception {
		DataFormatter formatter = new DataFormatter();
		FileInputStream fileInputStream = new FileInputStream(FilePath); // Excel sheet file location get mentioned here
		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream); // get my workbook
		XSSFSheet worksheet = workbook.getSheet(SheetName);// get my sheet from workbook
		XSSFRow Row = worksheet.getRow(0); // get my Row which start from 0
		int RowNum = worksheet.getPhysicalNumberOfRows();// count my number of Rows
		int ColNum = Row.getLastCellNum(); // get last ColNum
		Object Data[][] = new Object[RowNum - 1][ColNum]; // pass my count data in array
		for (int i = 0; i < RowNum - 1; i++) // Loop work for Rows
		{
			XSSFRow row = worksheet.getRow(i + 1);
			for (int j = 0; j < ColNum; j++) // Loop work for colNum
			{
				if (row == null)
					Data[i][j] = "";
				else {
					XSSFCell cell = row.getCell(j);
					if (cell == null)
						Data[i][j] = ""; // if it get Null value it pass no data
					else {
						String value = formatter.formatCellValue(cell);
						Data[i][j] = value; // This formatter get my all values as string i.e integer, float all type
						// data value
					}
				}
			}
		}
		fileInputStream.close();
		return Data;
	}

	
}