package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
    public FileInputStream fis = null;
    public FileOutputStream fos = null;
    private XSSFWorkbook workbook= null;
    private XSSFSheet sheet = null;
    private XSSFRow row = null;
    private XSSFCell cell = null;
    String path = null;

    //Constructor
    public ExcelReader() throws IOException {
        path = "/testData.xlsx";
        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheetAt(0);
    }

    //Return total number of columns in sheet
    public int colCount(String sheetName){
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(0);
        return(row.getLastCellNum());
    }

    //return last row number
    public int rowCount(String sheetName){
        sheet = workbook.getSheet(sheetName);
        return(sheet.getLastRowNum());
    }

    //Return cell value
    public String getCellData(String sheetName, int rowNum, int colNum){
        sheet =workbook.getSheetAt(workbook.getSheetIndex(sheetName));
        row = sheet.getRow(rowNum);
        cell = row.getCell(colNum);
        return(cell.getStringCellValue());
    }

    //Return Cell value by col name
    public String getCellData(String sheetName, int rowNum, String colName){
        sheet = workbook.getSheetAt(workbook.getSheetIndex(sheetName));
        int colNum = -1;
        for (int i=0; i< colCount(sheetName);i++){
            row = sheet.getRow(0);
            cell = row.getCell(i);
            if(cell.getStringCellValue().equals(colName)){
                colNum = cell.getColumnIndex();
                break;
            }
        }
        row = sheet.getRow(rowNum);
        cell = row.getCell(colNum);
        return(cell.getStringCellValue());
    }

    public double getCellNumber(String sheetName, int rowNum, String colName){
        sheet = workbook.getSheetAt(workbook.getSheetIndex(sheetName));
        int colNum = -1;
        for (int i=0; i< colCount(sheetName);i++){
            row = sheet.getRow(0);
            cell = row.getCell(i);
            if(cell.getStringCellValue().equals(colName)){
                colNum = cell.getColumnIndex();
                break;
            }
        }
        row = sheet.getRow(rowNum);
        cell = row.getCell(colNum);
        return(cell.getNumericCellValue());
    }

    //set Cell Data
    public void setCellData(String sheetName, int rowNum, int colNum, String str){
        sheet = workbook.getSheetAt(workbook.getSheetIndex(sheetName));
        row =sheet.getRow(rowNum);
        cell = row.createCell(colNum);
        cell.setCellValue(str);
        try {
            fos = new FileOutputStream(path);
            workbook.write(fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setCellData(String sheetName, int rowNum, String colName, String str){
        sheet = workbook.getSheetAt(workbook.getSheetIndex(sheetName));
        int colNum = -1;
        for (int i=0; i< colCount(sheetName);i++){
            row = sheet.getRow(0);
            cell = row.getCell(i);
            if(cell.getStringCellValue().equals(colName)){
                colNum = cell.getColumnIndex();
                break;
            }
        }
        row =sheet.getRow(rowNum);
        cell = row.createCell(colNum);
        cell.setCellValue(str);
        try {
            fos = new FileOutputStream(path);
            workbook.write(fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
