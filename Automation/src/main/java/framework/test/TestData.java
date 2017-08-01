package framework.test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.util.Iterator;


public class TestData
{
    /**
     * Gets test data from a sheet document
     * @param sKey (String) Test data key from the sheet document - e.g. "Firstname"
     * @return (String) Test data value from the sheet document - e.g. "John" for a given "Firstname" key
     */
    private String getTestData(String sKey){
        String testValue = null;
        try {
            String sFilePath = "D:\\Backup Softvision\\Workspace\\heal-qa-automation\\Automation\\src\\main\\java\\framework\\test\\test_data.xlsx";
            FileInputStream file = new FileInputStream(sFilePath);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    cell.setCellType(CellType.STRING);
                    if (cell.getStringCellValue().equals(sKey))
                        testValue = cellIterator.next().getStringCellValue();
                }
            }
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return testValue;
    }

    public String getFirstName(){
        return getTestData("Firstname");
    }
    public String getLastName(){
        return getTestData("Lastname");
    }
    public String getEmail(){
        return getTestData("Email");
    }
    public String getPassword(){
        return getTestData("Password");
    }

}





