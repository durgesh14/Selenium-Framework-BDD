package com.framework.utility;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {
    private XSSFSheet excelSheet;
    private XSSFWorkbook excelWorkbook;
    private XSSFCell cell;
    private XSSFRow row;


    /**
     * Set the Excel file and sheet to work with.
     *
     * @param sheetPath The path to the Excel file.
     * @param sheetName The name of the Excel sheet.
     * @throws Exception If an error occurs during file setup.
     */
    public void setExcelFile(String sheetPath, String sheetName) throws IOException {

        try {
            FileInputStream fis = new FileInputStream(sheetPath);
            excelWorkbook = new XSSFWorkbook(fis);
            excelSheet = excelWorkbook.getSheet(sheetName);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Get data from a specific cell.
     *
     * @param rowNum The row number of the cell.
     * @param colNum The column number of the cell.
     * @return The data from the cell.
     * @throws Exception If an error occurs while getting cell data.
     */
    public String getCellData(int rowNum, int colNum) throws Exception {

        try {
            cell = excelSheet.getRow(rowNum).getCell(colNum);
            if (cell.getCellType() == CellType.NUMERIC) {
                cell.setCellType(CellType.STRING);
            }
            String cellData = cell.getStringCellValue();
            return cellData;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Set data into a specific cell and save it to the Excel file.
     *
     * @param data          The data to be set in the cell.
     * @param rowNum        The row number of the cell.
     * @param colNum        The column number of the cell.
     * @param excelFilePath The path to the Excel file to save changes.
     * @throws Exception If an error occurs while setting cell data.
     */
    public void setCellData(String data, int rowNum, int colNum, String excelFilePath) throws Exception {
        try {
            cell = excelSheet.getRow(rowNum).getCell(colNum);
            if (cell == null) {
                cell = row.createCell(colNum);
                cell.setCellValue(data);
            } else {
                cell.setCellValue(data);
            }

            FileOutputStream fos = new FileOutputStream(excelFilePath);
            excelWorkbook.write(fos);
            fos.flush();
            fos.close();

        } catch (Exception e) {
            throw e;
        }
    }


    /**
     * Get the row number of a test case by its name.
     *
     * @param testCaseName   The name of the test case.
     * @param testCaseColumn The column index where test case names are stored.
     * @return The row number of the test case.
     * @throws Exception If an error occurs while retrieving the row number.
     */
    private int findTestCaseRow(String testCaseName, int testCaseColumn) throws Exception {
        int row;
        try {
            int totalRow = excelSheet.getLastRowNum();
            for (row = 0; row < totalRow; row++) {
                if (getCellData(row, testCaseColumn).equalsIgnoreCase(testCaseName)) {
                    break;
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return row;

    }


    /**
     * Retrieve data from the Excel sheet and return it as a map.
     *
     * @param testCaseName The name of the test case.
     * @param sheetPath    The path to the Excel file.
     * @param sheetName    The name of the Excel sheet.
     * @return A map containing the data from the specified test case.
     */
    public Map<String, String> getTestData(String testCaseName, String sheetPath, String sheetName) throws Exception {
        Map<String, String> datamap = new HashMap<>();
        try {
            setExcelFile(sheetPath, sheetName);
            int dataRow = findTestCaseRow(testCaseName, 0);
            int totalColumn = excelSheet.getRow(dataRow).getLastCellNum();

            for (int col = 0; col < totalColumn; col++) {
                String cellData = null;
                cell = excelSheet.getRow(dataRow).getCell(col);
                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    cell.setCellType(CellType.STRING);
                }
                if (cell != null) {
                    cellData = cell.getStringCellValue();
                }
                datamap.put(excelSheet.getRow(0).getCell(col).getStringCellValue(), cellData);
            }
        } catch (Exception e) {
            throw e;
        }
        return datamap;
    }


}
