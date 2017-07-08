package patient.tests; /**
 * Created by vahanmelikyan on 6/22/17.
 */
import utilities.DriverManager;
import org.testng.annotations.Test;

public class Test6 {
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
//        System.out.println("Thread id = " + Thread.currentThread().getId());
//        System.out.println("Hashcode of webDriver instance = " + utilities.DriverManager.getDriver().hashCode());
        DriverManager.getDriver().get(url);
    }
}
