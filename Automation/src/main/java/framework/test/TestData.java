package framework.test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class TestData {

    public String firstname;
    public String lastname;
    public String email;
    public String password;
    public String confirmPassword;
    public String phoneNumber;
    public String zipCode;

    /**
     * Initializes class variables with the values from Excel file
     * @param sSheetName (String) Sheet name from the Excel file
     */
    public TestData(String sSheetName){
        initFromExcel(sSheetName);
    }
    private String projDri = System.getProperty("user.dir");
    private String fileSeparator = System.getProperty("file.separator");
    private String fileExcelName = "test_data.xlsx";
    private String fileExcelPath = projDri + fileSeparator +
            "src" + fileSeparator +
            "main" + fileSeparator +
            "java" + fileSeparator +
            "framework" + fileSeparator +
            "test" + fileSeparator + fileExcelName;


    private void initFromExcel(String sSheetName) {
        Map<String,String> testDataFromExcel = new HashMap<>();
        try {
            String sFilePath = fileExcelPath;
            FileInputStream file = new FileInputStream(sFilePath);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet(sSheetName);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell key = cellIterator.next();
                    key.setCellType(CellType.STRING);
                    Cell value =  cellIterator.next();
                    value.setCellType(CellType.STRING);
                    testDataFromExcel.put(key.getStringCellValue(),value.getStringCellValue());
                }
            }
            file.close();
            firstname = testDataFromExcel.get("Firstname");
            lastname = testDataFromExcel.get("Lastname");
            email = testDataFromExcel.get("Email");
            password = testDataFromExcel.get("Password");
            confirmPassword = testDataFromExcel.get("ConfirmPassword");
            phoneNumber = testDataFromExcel.get("PhoneNumber");
            zipCode = testDataFromExcel.get("ZipCode");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}