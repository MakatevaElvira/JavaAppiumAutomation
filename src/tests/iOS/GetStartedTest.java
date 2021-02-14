package tests.iOS;

import lib.CoreTestCase;
import lib.Platform;
import lib.iOSTestCase;
import lib.ui.MainPageObject;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {
    public WelcomePageObject WelcomePage;
    public MainPageObject MainPage;
    public Platform Platform;

    protected void setUp() throws Exception {
        super.setUp();
        WelcomePage = new WelcomePageObject(driver);
        MainPage = new MainPageObject(driver);
        Platform = new Platform();
    }

    @Test
    public void testPassThroughWelcome(){
        if (Platform.isAndroid()){
            return;
        }
        WelcomePage.waitLearnMoreWikipediaLink();
        WelcomePage.clickNextButton();
        WelcomePage.waitNewWaysLink();
        WelcomePage.clickNextButton();
        WelcomePage.waitAddOrEditLanguagesLink();
        WelcomePage.clickNextButton();
        WelcomePage.waitLearnMoreDataCollectedLink();
        WelcomePage.clickGetStartedButton();

    }
    @Test
    public void testFirstSearch() {
        if (Platform.isAndroid()){
            return;
        }
        System.out.println("Java+Appium=start test!!!");
        MainPage.skipStartInformation();
        String searchText = MainPage.findSearchInputField().getText();
        System.out.println(searchText);
    }

}


