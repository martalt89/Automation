package patient.pages;

import utilities.library;

/**
 * Created by vahanmelikyan on 6/26/17.
 */
public abstract class _BasePage {

    public static library lib = new library();

    public void setProperties(library lib) throws Exception {
        lib.loadPropertyFile("loginPage.properties");
        lib.loadPropertyFile("homePage.properties");
        lib.loadPropertyFile("menu.properties");
    }
}
