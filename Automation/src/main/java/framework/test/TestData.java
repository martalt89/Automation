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

    public Boolean bHasMedicareMedicaid;
    public String sFirstname;
    public String sLastname;
    public String sEmail;
    public String sPassword;
    public String sConfirmPassword;
    public String sPhoneNumber;
    public String sZipCode;
    public String sDateOfBirth;
    public String sRelationship;
    public String sGender;
    public int iExpiryMonth;
    public int iExpiryYear;
    public String sCardNumber;
    public String sCVC;
    public String sCardType;


    public static final String ACCOUNT_SHEET = "Account";
    public static final String PATIENT_SHEET = "Patient";
    public static final String CARD_SHEET = "Card";

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
                //TODO: Add exceptions for cases where Excel file has incorrect Keys or typos. ie. "Firstnam" instead of "Firstname"
                if (sSheetName.equals(ACCOUNT_SHEET)) {
                        sFirstname = testDataFromExcel.get("Firstname");
                        sLastname = testDataFromExcel.get("Lastname");
                        sEmail = testDataFromExcel.get("Email");
                        sPassword = testDataFromExcel.get("Password");
                        sConfirmPassword = testDataFromExcel.get("ConfirmPassword");
                        sPhoneNumber = testDataFromExcel.get("PhoneNumber");
                        sZipCode = testDataFromExcel.get("ZipCode");
                }

                if (sSheetName.equals(PATIENT_SHEET)) {
                        bHasMedicareMedicaid = testDataFromExcel.get("HasMedicareMedicaid").toLowerCase().equals("false") ? false : true;
                        sFirstname = testDataFromExcel.get("Firstname");
                        sLastname = testDataFromExcel.get("Lastname");
                        sEmail = testDataFromExcel.get("Email");
                        sPhoneNumber = testDataFromExcel.get("PhoneNumber");
                        sZipCode = testDataFromExcel.get("ZipCode");
                        sDateOfBirth = testDataFromExcel.get("DateOfBirth");
                        sRelationship = testDataFromExcel.get("Relationship");
                        sGender = testDataFromExcel.get("Gender");
                }

            if (sSheetName.equals(CARD_SHEET)){
                    iExpiryMonth = Integer .parseInt(testDataFromExcel.get("ExpiryMonth"));
                    iExpiryYear = Integer .parseInt(testDataFromExcel.get("ExpiryYear"));
                    sCardNumber = testDataFromExcel.get("CardNumber");
                    sCVC = testDataFromExcel.get("CVC");
                    sCardType = testDataFromExcel.get("CardType");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}