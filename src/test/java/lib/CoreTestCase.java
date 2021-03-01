package lib;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import lib.ui.factories.WelcomePgeObjectFactory;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileOutputStream;
import java.util.Properties;

public class CoreTestCase  {
    protected RemoteWebDriver driver;


    @Before
    @Step("Run session")
    public void setUp() throws Exception{
        driver = Platform.getInstance().getDriverWithCapabilitiesFor("mobile_web");
        createPropertyFile();
        skipWelcomePageForIosApp();
        openWikiPageForMobileWeb();
    }

    @After
    @Step("Stop session")
    public void tearDown()  {
        driver.quit();
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
    private void createPropertyFile(){
        String path = System.getProperty("allure.results.directory");
        try{
            Properties props = new Properties();
            FileOutputStream fos = new FileOutputStream(path+"/environment.properties");
            props.setProperty("Environment", Platform.getInstance().getPlatformVar());
            props.store(fos,"See https://github.com/allure-framework/allure-app/wiki/Environment");
            fos.close();
        }catch (Exception e){
            System.err.println("IO problem when writing allure properties");
            e.printStackTrace();
        }
    }

}
