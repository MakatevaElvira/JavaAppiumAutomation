package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.iOSTestCase;
import lib.ui.MainPageObject;
import lib.ui.WelcomePageObject;
import lib.ui.factories.MainPageObjectFactory;
import lib.ui.factories.WelcomePgeObjectFactory;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {
    public WelcomePageObject WelcomePage;
    public MainPageObject MainPage;

    protected void setUp() throws Exception {
        super.setUp();
        WelcomePage = WelcomePgeObjectFactory.get(driver);
        MainPage =  MainPageObjectFactory.get(driver);
    }

    @Test
    public void testPassThroughWelcome(){
        if ((Platform.getInstance().isAndroid()) || (Platform.getInstance().isMW())) {
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
        if ((Platform.getInstance().isAndroid()) || (Platform.getInstance().isMW())){
            return;
        }
        System.out.println("Java+Appium=start test!!!");
        MainPage.skipStartInformation();
        String searchText = MainPage.findSearchInputField().getText();
        System.out.println(searchText);
    }

}


