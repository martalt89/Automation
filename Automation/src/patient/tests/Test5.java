package patient.tests;

/**
 * Created by vahanmelikyan on 6/22/17.
 */
import utilities.DriverManager;
import org.testng.annotations.Test;

public class Test5 {
    @Test
    public void Bing() {
        invokeBrowser("http://www.bing.com");
        invokeBrowser("http://www.yahoo.com");
    }

    @Test
    public void Yahoo() {
        invokeBrowser("http://www.yahoo.com");
    }

    private void invokeBrowser(String url) {

        DriverManager.getDriver().get(url);
    }
}
