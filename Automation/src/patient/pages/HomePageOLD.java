package patient.pages;


/**
 * Created by vahanmelikyan on 6/26/17.
 */
public class HomePageOLD extends _BasePage {


    public void openMenu() {
        lib.openMenu(lib.getProp("hamburgerMenu"));
        try {
            lib.waitForElement(lib.getProp("signOut"));
            lib.prinlntWithThreadID("Opened menu...");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void home() {
        openMenu();
        lib.clickElementAndWait(lib.getProp("home"), lib.getProp("loadingScreen"));
//        lib.clickElement(lib.getProp("home"));
    }

    public void bookVisit() {
        openMenu();
        lib.clickElementAndWait(lib.getProp("bookVisit"), lib.getProp("loadingScreen"));
//        lib.clickElement(lib.getProp("bookVisit"));
    }

    public void visits() {
        openMenu();
        lib.clickElementAndWait(lib.getProp("visits"), lib.getProp("loadingScreen"));
//       lib.clickElement(lib.getProp("visits"));
    }

    public void profiles() {
        openMenu();
        lib.clickElementAndWait(lib.getProp("profiles"), lib.getProp("loadingScreen"));
//        lib.clickElement(lib.getProp("profiles"));
    }

    public void paymentMethods() {
        openMenu();
        lib.clickElementAndWait(lib.getProp("paymentMethods"), lib.getProp("loadingScreen"));
//        lib.clickElement(lib.getProp("paymentMethods"));
    }

    public void signOut() {
        openMenu();
        lib.clickElementAndWait(lib.getProp("signOut"), lib.getProp("loadingScreen"));
//        lib.clickElement(lib.getProp("signOut"));
    }

}
