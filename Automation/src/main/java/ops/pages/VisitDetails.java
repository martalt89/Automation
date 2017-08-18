package ops.pages;

import framework.web.CommonWebElement;
import framework.web.WebBase;
import org.openqa.selenium.WebDriver;

public class VisitDetails extends WebBase{
    public static final String URL = "https://ops.qa.heal.com/dashboard";

    ///////////////////
    // Page Elements //
    ///////////////////

    //////////////////
    // Constructors //
    //////////////////

    public VisitDetails(WebDriver oTargetDriver)
    {
        super(oTargetDriver, URL);
    }
    public VisitDetails(WebDriver oTargetDriver, String sUrl)
    {
        super(oTargetDriver, sUrl);
    }
    public VisitDetails(){
        super();
    }

    /////////////
    // Methods //
    /////////////

}
