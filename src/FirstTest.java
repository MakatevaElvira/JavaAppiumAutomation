import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia.beta");
        capabilities.setCapability("appActivity","org.wikipedia.main.MainActivity");
        capabilities.setCapability("app","C:\\Users\\79061\\Documents" +
                "\\GitHub\\javaMobile\\apks\\org.wikipedia.beta_50337_apps.evozi.com.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown(){
        driver.quit();
    }

   @Test
    public void firstTest(){
       System.out.println("Java+Appium=start test!!!");
       waitElementPresentBy(By.xpath("//*[contains(@text,'SKIP')]")).click();
       String searchText = waitElementPresentBy(By.xpath("//*[contains(@text,'Search Wikipedia')]")).getText();
       System.out.println(searchText);
       //waitElementPresentBy(By.xpath("//android.widget.HorizontalScrollView[@content-desc=\"Page 1 of 4\"]/android.widget.LinearLayout/android.widget.LinearLayout[4]")).click();
       //waitElementPresentBy(By.id("org.wikipedia.beta:id/bottomOffset")).click();
      System.out.println("Java+Appium=finish test!!!");
    }
    @Test
    public void elementsTextAssertionTest(){
        waitElementPresentBy(By.xpath("//*[contains(@text,'SKIP')]")).click();
       Boolean result =  assertElementHasText(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Search Wikipedia",
                "Elements text is not = Search Wikipedia");
        System.out.println("result= "+result);
       Assert.assertTrue(result);
    }
    @Test
    public void searchCancellationTest(){
        String toFind = "google";
        //пропустить
        waitElementPresentBy(By.xpath("//*[contains(@text,'SKIP')]")).click();
        //кликнуть поиск
        //waitElementPresentBy(By.xpath("//*[contains(@text,'Search Wikipedia')]")).click();//@class android.widget.TextView
        waitElementPresentBy(By.xpath("//android.widget.TextView[contains(@text,'Search Wikipedia')]")).click();//org.wikipedia.beta:id/search_container
        //(By.id("//org.wikipedia.beta:id/search_container")).click();//
        //в нов поле ввода ввести Значение
        waitElementPresentBy(By.id("org.wikipedia.beta:id/search_src_text")).sendKeys(toFind);
        // проверить в результате есть записи
        waitElementsPresentBy(By.id("org.wikipedia.beta:id/page_list_item_title"));
        // нажать крестик-очистить поиск
        waitElementPresentBy(By.id("org.wikipedia.beta:id/search_close_btn")).click();
        // проверить результат = пустой поиск=тоже работает
        //waitElementPresentBy(By.id("org.wikipedia.beta:id/search_empty_image"));
        // либо нотпрезент
        Assert.assertTrue(waitElementNotPresentBy(By.id("org.wikipedia.beta:id/page_list_item_title")));

    }
    @Test
    public void searchCheckingResultTest(){
        String toFind = "Google";
        //пропустить
        waitElementPresentBy(By.xpath("//*[contains(@text,'SKIP')]")).click();
        //кликнуть поиск
        //waitElementPresentBy(By.xpath("//*[contains(@text,'Search Wikipedia')]")).click();//@class android.widget.TextView
        waitElementPresentBy(By.xpath("//android.widget.TextView[contains(@text,'Search Wikipedia')]")).click();//org.wikipedia.beta:id/search_container
        //(By.id("//org.wikipedia.beta:id/search_container")).click();//
        //в нов поле ввода ввести Значение
        waitElementPresentBy(By.id("org.wikipedia.beta:id/search_src_text")).sendKeys(toFind);
        // проверить результата assert
        List <WebElement> results = driver.findElements(By.id("org.wikipedia.beta:id/page_list_item_title"));
        for ( WebElement result: results){
            System.out.println("Result= "+ result.getText());
            Assert.assertTrue(result.getText().contains(toFind));
        }

    }

    public WebElement waitElementPresentBy(By locator){
        WebDriverWait webDriverWait = new WebDriverWait(driver,5);
        webDriverWait.withMessage("No element present by locator: "+ locator);
        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));

    }
    public List <WebElement> waitElementsPresentBy(By locator){
        WebDriverWait webDriverWait = new WebDriverWait(driver,5);
        webDriverWait.withMessage("No element present by locator: "+ locator);
        return webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

    }
    public Boolean waitElementNotPresentBy(By locator){
        WebDriverWait webDriverWait = new WebDriverWait(driver,5);
        webDriverWait.withMessage("No element present by locator: "+ locator);
        return webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));

    }
    public boolean assertElementHasText(By locator, String expectedText,String errorMessage){
        String gettingText = waitElementPresentBy(locator).getAttribute("name");
        System.out.println("expected= "+expectedText);
        System.out.println("getting= " + gettingText);
        if (gettingText.equals(expectedText)){
            return true;
        }
        System.out.println(errorMessage);;
        return false;
    }
}
