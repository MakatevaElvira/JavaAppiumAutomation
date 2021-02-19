package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class Platform {
    protected AppiumDriver driver;
    private static final String
            ANDROID_PLATFORM = "android",
            iOS_PLATFORM = "iOs",
            APPIUM_URL = "http://127.0.0.1:4723/wd/hub";

    private static Platform instance;

    private Platform(){}
    public static Platform getInstance(){
        if (instance == null){
            instance = new Platform();
        }
        return instance;
    }

    public AppiumDriver getDriverWithCapabilitiesFor(String platform) throws MalformedURLException {
        if (isAndroid()) {
            return driver = new AndroidDriver(new URL(APPIUM_URL), getAndroidCapabilities());
        } else if (isIOs()){
            return driver = new IOSDriver(new URL(APPIUM_URL), getIOsCapabilities());
        } else new Exception("No platform find by Platform name ="+platform);
        return driver;
    }
    private DesiredCapabilities getAndroidCapabilities()  {
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
        return  capabilities;
    }
    private DesiredCapabilities getIOsCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone SE");
        capabilities.setCapability("platformVersion", "11.3");
        capabilities.setCapability("app", "C:\\Users\\79061\\Documents" +
                "\\GitHub\\javaMobile\\apks\\wikipedia.app");
        capabilities.setCapability("orientation", "PORTRAIT");
        return capabilities;
    }
    public boolean isAndroid(){
        return isPlatform(ANDROID_PLATFORM);
    }
    public boolean isIOs(){
        return isPlatform(iOS_PLATFORM);
    }
    private Boolean isPlatform(String myPlatform){
        return myPlatform.equals(getPlatformVar());
    }
    private String  getPlatformVar(){
        return System.getenv("PLATFORM");
    }
    private String  getPlatformAndroid(){
        return ANDROID_PLATFORM;
    }
}
