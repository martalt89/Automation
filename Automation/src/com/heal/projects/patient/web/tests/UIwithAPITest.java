package com.heal.projects.patient.web.tests;

import com.heal.framework.restAPI.AccountAPI;
import com.heal.framework.restAPI.PatientAPI;
import com.heal.framework.restAPI.RestUtils;
import com.heal.framework.test.TestBase;
import com.heal.framework.test.TestData;
import com.heal.framework.web.CommonWebElement;
import com.heal.projects.patient.web.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;


public class UIwithAPITest extends TestBase {


    /**
     * This test demonstrates the usage of the Account and Patient API classes. High level test description below:
     *
     * 1. Create a random user account using AccountAPI and RestUtils classes
     * 2. Login with the newly generated credentials
     * 3. Add a patient to the created account using test data from Excel file
     * 3.1 Check that current patients number increases by 1
     * 3.2 Check that created patient info is correctly displayed on /profile and on /profile/{patientId} pages
     * 4. Add another patient using a given Map
     * 4.1 Check that current patients number increases by 1
     * 4.1 Check that created patient info is correctly displayed on /profile and on /profile/{patientId} pages
     */
    @Test (groups = {"dev", "critical"})
    public void createAccountAndAddPatients() throws Exception {

        //Create a random user account using AccountAPI and RestUtils classes
        TestData accountTestData = new TestData(TestData.ACCOUNT_SHEET);
        RestUtils restUtils = new RestUtils();
        AccountAPI account = new AccountAPI(restUtils.generateUsername(), accountTestData.sPassword, true);
        PatientAPI patientAPI = new PatientAPI(account.sUsername, account.sPassword); //this will be used to add new patients to the newly created account

        //pages
        WebDriver dr = getDriver();
        CommonWebElement.setbMonitorMode(false);
        LoginPage loginPage = new LoginPage(dr);
        HomePage homePage = new HomePage(dr);
        ChooseProfilePage chooseProfilePage = new ChooseProfilePage(dr);
        ManageProfilePage manageProfilePage = new ManageProfilePage(dr);
        Menu menu = new Menu(dr);

        //Login
        loginPage.goTo();
        loginPage.waitForPageLoad();
        loginPage.login(account.sUsername, account.sPassword);


        //Add a patient from Excel test data and verify that patient info is displayed on profiles and manage profile pages
        System.out.println("Patients number after account creation: "  + patientAPI.getPatientsNumber());
        String patientFromExcelID = patientAPI.addPatientFromExcel();
        PatientAPI patientFromExcel = new PatientAPI(account.sUsername,account.sPassword, patientFromExcelID);

        System.out.println("Patients number after adding one patient from Excel: " + patientAPI.getPatientsNumber());
        System.out.println("Patient from Excel firstname: " + patientFromExcel.getPatientFirstname());
        System.out.println("Patient from Excel lastname: " + patientFromExcel.getPatientLastname());
        System.out.println("Patient from Excel email: " + patientFromExcel.getPatientEmail());
        System.out.println("Patient from Excel phone: " + patientFromExcel.getsPatientPhone());
        System.out.println("Patient from Excel date of birth: " + patientFromExcel.getPatientDateOfBirth());
        System.out.println("Patient from Excel relationship: " + patientFromExcel.getPatientRelationship());

        //check patient info in UI
        chooseProfilePage.goTo();
        verifyVisible("Check that patient firstname is displayed on Profiles page", chooseProfilePage.getPatientByText(patientFromExcel.getPatientFirstname()));
        chooseProfilePage.selectProfileByName(patientFromExcel.getPatientFirstname());
        verifyMatches("Firstname ", manageProfilePage.oFirstNameInput.getText(), patientFromExcel.getPatientFirstname());
        verifyMatches("Lastname ", manageProfilePage.oLastNameInput.getText(), patientFromExcel.getPatientLastname());
        verifyMatches("Email ", manageProfilePage.oEmailInput.getText(), patientFromExcel.getPatientEmail());


        //Add a patient from a Map and verify that patient info is displayed on profiles and manage profile pages
        Map<String, Object> patienMap = new HashMap<>();
        patienMap.put("emailRegex", "{}");
        patienMap.put("bHasMedicareMedicaid", false);
        patienMap.put("firstName", "Patient2Firstname");
        patienMap.put("lastName", "Patient2Lastname");
        patienMap.put("email", "patient2Email@heal.com");
        patienMap.put("phone", "+1 201-555-5555");
        patienMap.put("dateOfBirth", "09/09/1988");
        patienMap.put("relationshipId", "0001433013870063-d279fc27ffff816b-0007");
        patienMap.put("genderId", "0001433013870063-d279fc27ffff816b-0001");

        String patientFromMapId = patientAPI.addPatient(patienMap);
        PatientAPI patientFromMap = new PatientAPI(account.sUsername,account.sPassword, patientFromMapId);

        System.out.println("Patients number after adding another patient from a map: " + patientFromExcel.getPatientsNumber());
        System.out.println("Patient from Map firstname: " + patientFromMap.getPatientFirstname());
        System.out.println("Patient from Map lastname: " + patientFromMap.getPatientLastname());
        System.out.println("Patient from Map email: " + patientFromMap.getPatientEmail());
        System.out.println("Patient from Map phone: " + patientFromMap.getsPatientPhone());
        System.out.println("Patient from Map date of birth: " + patientFromMap.getPatientDateOfBirth());
        System.out.println("Patient from Map relationship: " + patientFromMap.getPatientRelationship());

        //check patient info in UI
        chooseProfilePage.goTo();
        verifyVisible("Check that patient firstname is displayed on Profiles page", chooseProfilePage.getPatientByText(patientFromExcel.getPatientFirstname()));
        chooseProfilePage.selectProfileByName(patientFromMap.getPatientFirstname());
        verifyMatches("Firstname ", manageProfilePage.oFirstNameInput.getText(), patientFromMap.getPatientFirstname());
        verifyMatches("Lastname ", manageProfilePage.oLastNameInput.getText(), patientFromMap.getPatientLastname());
        verifyMatches("Email ", manageProfilePage.oEmailInput.getText(), patientFromMap.getPatientEmail());

    }
}
