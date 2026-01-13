package com.codezyng.automation.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

	public static Object[][] getTestData(String filePath, String sheetName) {
		List<Object[]> data = new ArrayList<>();

		try (FileInputStream fis = new FileInputStream(filePath); Workbook workbook = new XSSFWorkbook(fis)) {

			Sheet sheet = workbook.getSheet(sheetName);
			int rowCount = sheet.getPhysicalNumberOfRows();

			for (int i = 1; i < rowCount; i++) { // skip header
				Row row = sheet.getRow(i);

				String username = row.getCell(0).getStringCellValue();
				String password = row.getCell(1).getStringCellValue();
				String expected = row.getCell(2).getStringCellValue();

				data.add(new Object[] { username, password, expected });
			}

		} catch (Exception e) {
			throw new RuntimeException("Failed to read Excel file", e);
		}

		return data.toArray(new Object[0][0]);
	}
}
