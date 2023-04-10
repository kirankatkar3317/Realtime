package com.wa.ModifiedOnline.Overview;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Utilities.Commons;

public class ExcelUtility {

	public static void main(String[] args){

		XSSFWorkbook excelWorkbook;
		XSSFSheet excelSheet;
		XSSFCell excelCell;

		String sheetName = "Sheet1";
		String path = Commons.BASE_PATH + Commons.EXCEL_PATH;
		try {
			FileInputStream excelFile = new FileInputStream(path);
				excelWorkbook = new XSSFWorkbook(excelFile);
			excelSheet = excelWorkbook.getSheet(sheetName);
			excelCell = excelSheet.getRow(1).getCell(0);

			String excelData = excelCell.getStringCellValue();
			System.out.println(excelData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
