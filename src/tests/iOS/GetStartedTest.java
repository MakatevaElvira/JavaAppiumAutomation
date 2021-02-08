package tests.iOS;

import lib.iOSTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;

public class GetStartedTest extends iOSTestCase  {
    public WelcomePageObject WelcomePage;
    public MainPageObject MainPage;

    protected void setUp() throws Exception {
        super.setUp();
        WelcomePage = new WelcomePageObject(driver);
        MainPage = new MainPageObject(driver);
    }

    @Test
    public void testPassThroughWelcome(){
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
        System.out.println("Java+Appium=start test!!!");
        MainPage.waitElementPresentBy(By.xpath("//*[contains(@text,'SKIP')]")).click();
        String searchText = MainPage.waitElementPresentBy(By.xpath("//*[contains(@text,'Search Wikipedia')]")).getText();
        System.out.println(searchText);
    }

}


