package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import lib.ui.factories.WelcomePgeObjectFactory;

public class CoreTestCase extends TestCase {
    protected AppiumDriver driver;


    @Override
    protected void setUp() throws Exception{
        super.setUp();
        driver = Platform.getInstance().getDriverWithCapabilitiesFor("android");
        skipWelcomePageForIosApp();
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

}
