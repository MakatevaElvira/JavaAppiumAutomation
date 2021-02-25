package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import lib.ui.factories.WelcomePgeObjectFactory;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CoreTestCase extends TestCase {
    protected RemoteWebDriver driver;


    @Override
    protected void setUp() throws Exception{
        super.setUp();
        driver = Platform.getInstance().getDriverWithCapabilitiesFor("mobile_web");
        skipWelcomePageForIosApp();
        openWikiPageForMobileWeb();
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }

    private void skipWelcomePageForIosApp(){
        if (Platform.getInstance().isIOs()){
            WelcomePageObject welcomePage = WelcomePgeObjectFactory.get(driver);
            welcomePage.skipStartInformation();
        }
    }
    protected void openWikiPageForMobileWeb(){
        if (Platform.getInstance().isMW()){
            driver.get("https://en.m.wikipedia.org");
        } else {
            System.out.println("OpenWikiPageForMobileWeb has not for platform= "+Platform.getInstance().getPlatformVar());
        }
    }

}
