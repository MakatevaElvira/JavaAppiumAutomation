package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class iOSTestCase extends TestCase {
    protected AppiumDriver driver;
    String appiumUrl = "http://127.0.0.1:4723/wd/hub";
    private static final String
            ANDROID_PLATFORM = "android",
            iOS_PLATFORM = "iOs";

    @Override
    protected void setUp() throws Exception{
        super.setUp();
        driver = getDriverWithCapabilitiesFor("android");
    }

    protected void oldSetUp() throws Exception{
        super.setUp();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","iOS");
        capabilities.setCapability("deviceName","iPhone SE");
        capabilities.setCapability("platformVersion","11.3");
        capabilities.setCapability("app","C:\\Users\\79061\\Documents" +
                "\\GitHub\\javaMobile\\apks\\wikipedia.app");
        capabilities.setCapability("orientation", "PORTRAIT");
        driver = new AndroidDriver(new URL(appiumUrl), capabilities);// подключить драйвер! iOSDriver !!!!!!!!!!!!!!!!!!!
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }

    private AppiumDriver getDriverWithCapabilitiesFor(String platform) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (platform.equals(ANDROID_PLATFORM)) {
            capabilities.setCapability("platformName","Android");
            capabilities.setCapability("deviceName","AndroidTestDevice");
            capabilities.setCapability("platformVersion","8.0");
            capabilities.setCapability("automationName","Appium");
            capabilities.setCapability("appPackage","org.wikipedia.beta");
            capabilities.setCapability("appActivity","org.wikipedia.main.MainActivity");
            capabilities.setCapability("app","C:\\Users\\79061\\Documents" +
                    "\\GitHub\\javaMobile\\apks\\org.wikipedia.beta_50337_apps.evozi.com.apk");
            capabilities.setCapability("orientation", "PORTRAIT");
           return driver = new AndroidDriver(new URL(appiumUrl), capabilities);
        } else if (platform.equals(iOS_PLATFORM)){
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "iPhone SE");
            capabilities.setCapability("platformVersion", "11.3");
            capabilities.setCapability("app", "C:\\Users\\79061\\Documents" +
                    "\\GitHub\\javaMobile\\apks\\wikipedia.app");
            capabilities.setCapability("orientation", "PORTRAIT");
            return driver = new AndroidDriver(new URL(appiumUrl), capabilities);//  добавить драйвер !!!!!!!!"!"!!!!!!!
        } else new Exception("No platform find by Platform name ="+platform);
        return driver;
    }

}
