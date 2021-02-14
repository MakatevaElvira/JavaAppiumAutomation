package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;

public class CoreTestCase extends TestCase {
    protected AppiumDriver driver;
    protected Platform Platform;

    @Override
    protected void setUp() throws Exception{
        super.setUp();
        Platform = new Platform();
        driver = Platform.getDriverWithCapabilitiesFor("android");
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }

}
