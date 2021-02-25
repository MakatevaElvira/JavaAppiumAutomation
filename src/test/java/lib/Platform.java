package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Platform {
    protected AppiumDriver driver;
    private static final String
            ANDROID_PLATFORM = "android",
            iOS_PLATFORM = "iOs",
            MOBILE_WEB_PLATFORM = "mobile_web",
            APPIUM_URL = "http://127.0.0.1:4723/wd/hub";

    private static Platform instance;

    private Platform() {
    }

    public static Platform getInstance() {
        if (instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    public RemoteWebDriver getDriverWithCapabilitiesFor(String platform) throws MalformedURLException {
        if (isAndroid()) {
            return driver = new AndroidDriver(new URL(APPIUM_URL), getAndroidCapabilities());
        } else if (isIOs()) {
            return driver = new IOSDriver(new URL(APPIUM_URL), getIOsCapabilities());
        } else if (isMW()) {
            return new ChromeDriver(getMVChromeOptions()) ;
        }else new Exception("No platform find by Platform name =" + platform);
        return driver;
    }

    private DesiredCapabilities getAndroidCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia.beta");
        capabilities.setCapability("appActivity", "org.wikipedia.main.MainActivity");
        capabilities.setCapability("app", "C:\\Users\\79061\\Documents" +
                "\\GitHub\\javaMobile\\apks\\org.wikipedia.beta_50337_apps.evozi.com.apk");
        capabilities.setCapability("orientation", "PORTRAIT");
        return capabilities;
    }

    private DesiredCapabilities getIOsCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone SE");
        capabilities.setCapability("platformVersion", "11.3");
        capabilities.setCapability("app", "C:\\Users\\79061\\Documents" +
                "\\GitHub\\javaMobile\\apks\\wikipedia.app");
       // if (driver instanceof AppiumDriver){ }
        capabilities.setCapability("orientation", "PORTRAIT");
        return capabilities;
    }

    private ChromeOptions getMVChromeOptions(){
        Map<String, Object> deviseMetrics = new HashMap<String, Object>();
        deviseMetrics.put("width",360);
        deviseMetrics.put("height",640);
        deviseMetrics.put("pixelRatio",3.0);

        Map<String, Object> mobileEmulation = new HashMap<String, Object>();
        mobileEmulation.put("deviseMetrics",deviseMetrics);
        mobileEmulation.put("userAgent","Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) " +
                "AppleWebKit/535.19 (KHTML, like Gecko)" +
                "Chrome/18.0.1025.166 Mobile Safari/535.19");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("window-size=340,640");
        return chromeOptions;
    }

    public boolean isAndroid() {
        return isPlatform(ANDROID_PLATFORM);
    }

    public boolean isIOs() {
        return isPlatform(iOS_PLATFORM);
    }
    public boolean isMW() {
        return isPlatform(MOBILE_WEB_PLATFORM);
    }

    private Boolean isPlatform(String myPlatform) {
        return myPlatform.equals(getPlatformVar());
    }

    public String getPlatformVar() {
        return System.getenv("PLATFORM");
    }

    private String getPlatformAndroid() {
        return ANDROID_PLATFORM;
    }
}
