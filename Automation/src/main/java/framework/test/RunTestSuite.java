package framework.test;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import utilities.WebDriverListener;

public class RunTestSuite {
    private static final String TEST_PACKAGE = "patient.tests";

    public static void main(String[] args) throws IOException {
        String projDri = System.getProperty("user.dir");
        String fileSeparator = System.getProperty("file.separator");
        String fileExcelName = "Heal_Login.xlsx";
        String fileExcelPath = projDri + fileSeparator + "src" + fileSeparator + "main" + fileSeparator + "java" + fileSeparator + "framework" + fileSeparator + "test" + fileSeparator + fileExcelName;

        ///////////////////////////////////
        //  Read test suite from excel   //
        ///////////////////////////////////


        //Environment oEnv = new Environment();
        //BaseQueries oQuery = new BaseQueries(oEnv);

        //File oExcel = new File("C:\\Users\\zzhen\\IdeaProjects\\Automation\\Automation\\src\\main\\java\\framework\\test\\Heal_Login.xlsx");
        File oExcel = new File(fileExcelPath);
        List<XmlSuite> oSuites = new ArrayList<XmlSuite>();

        XmlSuite suite = readFromExcel(oExcel);

        suite.setParameters(processParameters(oExcel));
        oSuites.add(suite);
        System.out.println(suite.toXml());
        TestNG testng = new TestNG();

        testng.setXmlSuites(oSuites);
        testng.addListener(new WebDriverListener());

        testng.run();
    }

    public static XmlSuite readFromExcel(File excelFile) throws IOException{

        String suiteName = FilenameUtils.getBaseName(excelFile.getName());

        XmlSuite oSuite = new XmlSuite();
        oSuite.setName(suiteName);
        oSuite.setThreadCount(6);
        oSuite.setParallel(XmlSuite.ParallelMode.METHODS);
        oSuite.setVerbose(2);
        oSuite.setDataProviderThreadCount(1);

        //read excel
        FileInputStream  driverExcel = new FileInputStream(excelFile);

        XSSFWorkbook workbook = new XSSFWorkbook(driverExcel);
        Iterator<Row> rowIterator = workbook.getSheet("Scenarios").iterator();

        XmlTest oTest;
        Map<String, XmlTest> oTestMap = new LinkedHashMap<String, XmlTest>();

        List<String> oGroups;

        XmlClass oClass;
        List<XmlClass> oClasses;

        XmlInclude oInclude;
        List<XmlInclude> oIncludes;

        String sTestClass;
        String sTestName;

        ArrayList<String[]> testRows = new ArrayList<String[]>();

        while(rowIterator.hasNext()){

            Row row = rowIterator.next();

            if(row.getCell(0, MissingCellPolicy.RETURN_BLANK_AS_NULL) == null || !row.getCell(0, MissingCellPolicy.RETURN_BLANK_AS_NULL).getStringCellValue().equalsIgnoreCase("Y")){
                continue;
            }

            String[] rowArray = new String[6];
            rowArray[0] = row.getCell(0, MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
            rowArray[1] = row.getCell(1, MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
            rowArray[2] = row.getCell(2, MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
            rowArray[3] = row.getCell(3, MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
            rowArray[4] = row.getCell(4, MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
            rowArray[5] = row.getCell(5, MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
            testRows.add(rowArray);
        }

        for (Object[] oTestRow : testRows)
        {
            sTestClass = (String)oTestRow[1];
            sTestClass = sTestClass.contains(".")?sTestClass:TEST_PACKAGE + "." + sTestClass;
            sTestName = sTestClass.substring(sTestClass.lastIndexOf(".")+1,sTestClass.length());

            // See if Test/Class already exist.  If so, get it from map, else create it.  One Class per Test.
            // Group and Test Case can't be used together.  One Test can contain many Test Cases, but one Test can only contain one Group and nothing else.
            if(oTestRow[3] != null && (oTest = oTestMap.get(sTestName)) != null)
            {
                // Found Test Case.  Insert into existing Test.
                oClass = oTest.getClasses().get(0);
                oInclude = new XmlInclude((String)oTestRow[3]);
                oIncludes = oClass.getIncludedMethods();
                oIncludes.add(oInclude);

                // Found test case parameters.  Insert as TestNG suite parameter using name/value pair with name being the Test Case name.
                if (oTestRow[4] != null)
                {
                    String sTestInput = (String)oTestRow[4];

                    if (sTestInput.toUpperCase().contains("$FILE="))
                    {
                        File inputFile = new File(sTestInput.toUpperCase().replace("$FILE=", ""));
                        if (inputFile.exists())
                        {
                            BufferedReader br = new BufferedReader(new FileReader(inputFile));
                            sTestInput = br.readLine();
                            br.close();
                        }

                    }

                    //processTestParameters((String)oTestRow[3], sTestInput, oPropForListener);
                    //params.put(sTestName + "." + (String)oTestRow[3], sTestInput);
                }
            }
            else  // New Test Class found.  Create it.
            {
                oTest = new XmlTest(oSuite);
                oClass = new XmlClass(sTestClass);

                // Found Group.  Create Test to contain Group only
                if (oTestRow[2] != null && !oTestRow[2].equals(""))
                {
                    oTest.setName(sTestName + "_" + oTestRow[2]);
                    oGroups = new ArrayList<String>();
                    oGroups.add((String)oTestRow[2]);
                    oTest.setIncludedGroups(oGroups);
                }
                // Found Test Case.  Create Test to contain Test Cases only.
                else if (oTestRow[3] != null && !oTestRow[3].equals(""))
                {
                    oTest.setName(sTestName);
                    oInclude = new XmlInclude((String)oTestRow[3]);
                    oIncludes = new ArrayList<XmlInclude>();
                    oIncludes.add(oInclude);
                    oClass.setIncludedMethods(oIncludes);

                    // Found test case parameters.  Insert as TestNG suite parameter using name/value pair with name being the Test Case name.
                    if (oTestRow[4] != null && !oTestRow[4].equals(""))
                    {
                        String sTestInput = (String)oTestRow[4];

                        if (sTestInput.toUpperCase().contains("$FILE="))
                        {
                            File inputFile = new File(sTestInput.toUpperCase().replace("$FILE=", ""));
                            if (inputFile.exists())
                            {
                                BufferedReader br = new BufferedReader(new FileReader(inputFile));
                                sTestInput = br.readLine();
                                br.close();
                            }

                        }

                        //processTestParameters((String)oTestRow[3], sTestInput, oPropForListener);
                        //params.put(sTestName + "." + (String)oTestRow[3], sTestInput);
                    }
                }

                oClasses = new ArrayList<XmlClass>();
                oClasses.add(oClass);
                oTest.setXmlClasses(oClasses);
                oTest.setPreserveOrder("true");
                // Put Test into map so it can be retrieved later if same name Test is found.
                oTestMap.put(oTest.getName(), oTest);
            }
        }
        driverExcel.close();

        return oSuite;
    }

    public static HashMap<String, String> processParameters(File excelFile)  throws IOException{

        HashMap<String, String> params = new HashMap<String, String>();
        FileInputStream  driverExcel = new FileInputStream(excelFile);

        XSSFWorkbook workbook = new XSSFWorkbook(driverExcel);
        Iterator<Row> rowIterator = workbook.getSheet("Parameters").iterator();


        while(rowIterator.hasNext())
        {
            Row row = rowIterator.next();

            if (row.getCell(0).getStringCellValue().equalsIgnoreCase("Name"))
                continue;

            String sName = row.getCell(0,MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
            String sValue = row.getCell(1, MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim();

            params.put(sName, sValue);

        }

        return params;
    }

}