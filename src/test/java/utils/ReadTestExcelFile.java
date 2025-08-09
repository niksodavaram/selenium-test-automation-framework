package utils;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Custom method for reading Excel file located in the resource dorectory
 */
public class ReadTestExcelFile {

    public static String excelPath = System.getProperty("user.dir") + "/src/test/resources/testdata/testdata.xlsx";

    //	public  String path;
    public FileInputStream fileInputStream;
    public FileOutputStream fileOutputStream;
    private XSSFWorkbook xssfWorkbook;
    private XSSFSheet xssfSheet;
    private XSSFRow xssfRow;
    private XSSFCell xssfCell;
    private CellStyle cellStyle;

    /**
     * Reading data from the Excel File
     * @param path
     */

    public ReadTestExcelFile(String path) {
        excelPath = path;
        try{
            fileInputStream = new FileInputStream(path);
            xssfWorkbook = new XSSFWorkbook(fileInputStream);
            xssfSheet = xssfWorkbook.getSheetAt(0);
            fileInputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public int getRowCount(String sheetName) throws IOException {
        int index=xssfWorkbook.getSheetIndex(sheetName);
        if(index==1){
            return 0;
        } else {
            xssfSheet = xssfWorkbook.getSheetAt(index);
            int rows = xssfSheet.getLastRowNum();
            return rows;
        }
    }

    public int getCellCount(String sheetName, int rowNum) throws IOException {
        xssfSheet = xssfWorkbook.getSheet(sheetName);
        xssfRow=xssfSheet.getRow(rowNum);
        int cellCount= xssfRow.getLastCellNum();
        xssfWorkbook.close();
        fileInputStream.close();
        return cellCount;
    }

    public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
        xssfSheet = xssfWorkbook.getSheet(sheetName);
        fileInputStream.close();

        xssfRow=xssfSheet.getRow(rowNum);
        xssfCell=xssfRow.getCell(colNum);

        //Formats data String to Int or Vice versa

        DataFormatter dataFormatter=new DataFormatter();
        String data;

        try{
            data= dataFormatter.formatCellValue(xssfCell);
        }catch (Exception e){
            data="";
        }
        xssfWorkbook.close();
        fileInputStream.close();
        return data;
    }

    /**
     * Writing data to an Excel File
     * @param sheetName
     * @param rowNum
     * @param colNum
     * @param data
     * @throws IOException
     */

    public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {
        File xlsxFile=new File(excelPath);
        if(!xlsxFile.exists()) {
            xssfWorkbook = new XSSFWorkbook();
            fileOutputStream = new FileOutputStream(excelPath);
            xssfWorkbook.write(fileOutputStream);
        }
        fileInputStream=new FileInputStream(excelPath);
        xssfWorkbook=new XSSFWorkbook(fileInputStream);

        if(xssfWorkbook.getSheetIndex(sheetName)==-1) // If sheet not exists then create new Sheet
            xssfWorkbook.createSheet(sheetName);

        xssfSheet=xssfWorkbook.getSheet(sheetName);

        if(xssfSheet.getRow(rowNum)==null)   // If row not exists then create new Row
            xssfSheet.createRow(rowNum);
        xssfRow=xssfSheet.getRow(rowNum);

        xssfCell=xssfRow.createCell(colNum);
        xssfCell.setCellValue(data);
        fileOutputStream=new FileOutputStream(excelPath);
        xssfWorkbook.write(fileOutputStream);
        xssfWorkbook.close();
        fileInputStream.close();
        fileOutputStream.close();
    }

    public void fillGreenColour(String sheetName, int rowNum, int colNum) throws IOException {
        xssfRow=xssfSheet.getRow(rowNum);
        xssfCell=xssfRow.getCell(colNum);

        cellStyle=xssfWorkbook.createCellStyle();

        cellStyle.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        xssfCell.setCellStyle(cellStyle);
        xssfWorkbook.write(fileOutputStream);
        xssfWorkbook.close();
        fileInputStream.close();
        fileOutputStream.close();
    }

    public void fillRedColour(String sheetName, int rowNum, int colNum) throws IOException {
        xssfRow=xssfSheet.getRow(rowNum);
        xssfCell=xssfRow.getCell(colNum);

        cellStyle=xssfWorkbook.createCellStyle();

        cellStyle.setFillBackgroundColor(IndexedColors.RED.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        xssfCell.setCellStyle(cellStyle);
        xssfWorkbook.write(fileOutputStream);
        xssfWorkbook.close();
        fileInputStream.close();
        fileOutputStream.close();
    }
}
