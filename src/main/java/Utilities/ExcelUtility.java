package Utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtility {

	private static XSSFWorkbook excelWorkbook;
	private static XSSFSheet excelSheet;

	XSSFCell excelCell;
//	String sheetName = "Sheet3";
//	String path = Commons.EXCEL_PATH;

	public static void getExcelFile(String path, String sheetName) throws IOException {

		try {
			FileInputStream excelFile = new FileInputStream(path);
			excelWorkbook = new XSSFWorkbook(excelFile);
			excelSheet = excelWorkbook.getSheet(sheetName);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Object[][] getTestData(String tableName) {
		Object[][] testData = null;
		try {
			DataFormatter formatter = new DataFormatter();
			XSSFCell[] boundaryCells = findTableNameCells(tableName);
			XSSFCell startCell = boundaryCells[0];
			XSSFCell endCell = boundaryCells[1];

			int startRow = startCell.getRowIndex() + 1;
			int endRow = endCell.getRowIndex() - 1;

			int startColumn = startCell.getColumnIndex() + 1;
			int endColumn = endCell.getColumnIndex() - 1;

			testData = new Object[endRow - startRow + 1][endColumn - startColumn + 1];
			for (int i = startRow; i < endRow + 1; i++) {
				for (int j = startColumn; j < endColumn + 1; j++) {

					Cell cell = excelSheet.getRow(i).getCell(j);
					testData[i - startRow][j - startColumn] = formatter.formatCellValue(cell);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return testData;
	}

	private static XSSFCell[] findTableNameCells(String tableName) {
		DataFormatter formatter = new DataFormatter();
		String pos = "begin";
		XSSFCell[] cells = new XSSFCell[2];
		for (Row row : excelSheet) {
			for (Cell cell : row) {
				if (tableName.equals(formatter.formatCellValue(cell))) {
					if (pos.equalsIgnoreCase("begin")) {
						cells[0] = (XSSFCell)cell;
						pos = "end";
					} else {
						cells[1] = (XSSFCell)cell;
						break;
					}
				}
			}

		}
		return cells;
	}
}