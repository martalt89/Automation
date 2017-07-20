package patient.pages;

import framework.web.CommonWebElement;
import framework.web.WebBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;

/**
 * Created by mihai.muresan on 7/20/2017.
 */
public class CreatePatientPage extends WebBase{
    public static final String URL = "https://patient.qa.heal.com/create-patient";

    ///////////////////
    // Page Elements //
    ///////////////////
    //in progress

    //////////////////
    // Constructors //
    //////////////////

    @Parameters({ "url" })
    public CreatePatientPage(WebDriver oTargetDriver)
    {
        super(oTargetDriver, URL);
    }
    public CreatePatientPage(WebDriver oTargetDriver, String sUrl)
    {
        super(oTargetDriver, sUrl);
    }



}

