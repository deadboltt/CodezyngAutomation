package com.codezyng.automation.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {
    
    public static Object[][] getTestData(String filePath, String sheetName) {
        List<Object[]> data = new ArrayList<>();
        Workbook workbook = null;
        
        try (FileInputStream fis = new FileInputStream(filePath)) {
            workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet(sheetName);
            
            System.out.println("Excel file: " + filePath);
            System.out.println("Sheet '" + sheetName + "' found: " + (sheet != null));
            
            if (sheet == null) {
                System.out.println("ERROR: Sheet '" + sheetName + "' not found. Available sheets: ");
                workbook.getAllNames().forEach(name -> System.out.println("- " + name));
                return new Object[0][];
            }
            
            int rowCount = sheet.getPhysicalNumberOfRows();
            System.out.println("Total rows (incl header): " + rowCount);
            
            if (rowCount <= 1) {
                System.out.println("ERROR: No data rows found (need rows 2+).");
                return new Object[0][];
            }
            
            for (int i = 1; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    System.out.println("Row " + i + ": null row skipped");
                    continue;
                }
                
                String username = getCellValue(row.getCell(0));
                String password = getCellValue(row.getCell(1));
                String expected = getCellValue(row.getCell(2));
                
                System.out.println("Row " + i + ": username='" + username + "', password='" + password + "', expected='" + expected + "'");
                data.add(new Object[]{username, password, expected});
            }
            
            Object[][] result = data.toArray(new Object[0][]);
            System.out.println("Final data rows returned: " + result.length);
            return result;
            
        } catch (Exception e) {
            System.err.println("ERROR reading Excel: " + filePath + ", sheet: " + sheetName);
            e.printStackTrace();
            return new Object[0][];
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (Exception e) {
                    System.err.println("Error closing workbook: " + e.getMessage());
                }
            }
        }
    }
    
    private static String getCellValue(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING: return cell.getStringCellValue();
            case NUMERIC: return String.valueOf((long) cell.getNumericCellValue());
            default: return "";
        }
    }
}
