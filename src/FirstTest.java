import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.ActionOptions;

import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.List;

import static java.time.Duration.ofMillis;

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
        capabilities.setCapability("orientation", "PORTRAIT");

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
    @Test
    public void swipeTest() throws InterruptedException {
        String toFind = "Google";
        //пропустить
        waitElementPresentBy(By.xpath("//*[contains(@text,'SKIP')]")).click();
        //кликнуть поиск
        //waitElementPresentBy(By.xpath("//*[contains(@text,'Search Wikipedia')]")).click();//@class android.widget.TextView
        waitElementPresentBy(By.xpath("//android.widget.TextView[contains(@text,'Search Wikipedia')]")).click();//org.wikipedia.beta:id/search_container
        //(By.id("//org.wikipedia.beta:id/search_container")).click();//
        //в нов поле ввода ввести Значение
        waitElementPresentBy(By.id("org.wikipedia.beta:id/search_src_text")).sendKeys(toFind);
        //открыть статью
        waitElementPresentBy(By.id("org.wikipedia.beta:id/page_list_item_title")).click();
        //свайпнуть
        swipeUp(2000);
        swipeUp(2000);
        swipeUp(2000);
    }
    @Test
    public void swipeBeforeElementTest() throws InterruptedException {
        String toFind = "Appium";
        //пропустить
        waitElementPresentBy(By.xpath("//*[contains(@text,'SKIP')]")).click();
        //кликнуть поиск
        //waitElementPresentBy(By.xpath("//*[contains(@text,'Search Wikipedia')]")).click();//@class android.widget.TextView
        waitElementPresentBy(By.xpath("//android.widget.TextView[contains(@text,'Search Wikipedia')]")).click();//org.wikipedia.beta:id/search_container
        //(By.id("//org.wikipedia.beta:id/search_container")).click();//
        //в нов поле ввода ввести Значение
        waitElementPresentBy(By.id("org.wikipedia.beta:id/search_src_text")).sendKeys(toFind);
        //открыть статью
        waitElementPresentBy(By.xpath("//*[@resource-id='org.wikipedia.beta:id/page_list_item_title'][@text='Appium']")).click();
        //свайпнуть
        swipeUpToFindElement(By.xpath("//*[@text='View article in browser']"),10);
    }

    @Test
    public void bigTest() throws InterruptedException {
        String toFind = "Appium";
        //пропустить
        waitElementPresentBy(By.xpath("//*[contains(@text,'SKIP')]")).click();
        //кликнуть поиск
        //waitElementPresentBy(By.xpath("//*[contains(@text,'Search Wikipedia')]")).click();//@class android.widget.TextView
        waitElementPresentBy(By.xpath("//android.widget.TextView[contains(@text,'Search Wikipedia')]")).click();//org.wikipedia.beta:id/search_container
        //(By.id("//org.wikipedia.beta:id/search_container")).click();//
        //в нов поле ввода ввести Значение
        waitElementPresentBy(By.id("org.wikipedia.beta:id/search_src_text")).sendKeys(toFind);
        //открыть статью
        waitElementPresentBy(By.xpath("//*[@resource-id='org.wikipedia.beta:id/page_list_item_title'][@text='Appium']")).click();
        //нажать три точки =не надо org.wikipedia.beta:id/article_menu_bookmark
        //waitElementPresentBy(By.xpath("//android.widget.ImageView[@content-desc=\"More options\"]")).click();
        //нажать SAVE org.wikipedia.beta:id/article_menu_bookmark
        waitElementPresentBy(By.id("org.wikipedia.beta:id/article_menu_bookmark")).click();
        //нажать ADD TO LIST
        waitElementPresentBy(By.xpath("//android.widget.Button[contains(@text,'ADD TO LIST')]")).click();
        //КЛИК чтобы закрылась всплывашка //класс android.widget.ListView
        //waitElementPresentBy(By.xpath("//android.widget.ListView")).click();
        //перейти на активную часть экрана org.wikipedia.beta:id/touch_outside
         waitElementPresentBy(By.id("org.wikipedia.beta:id/touch_outside")).click();
        //или перейти напрямую в сохраненные
       // waitElementPresentBy(By.xpath("//android.widget.TextView[contains(@text,'Saved')]")).click();
        //открыть вью лист
        //waitElementPresentBy(By.xpath("//android.widget.Button[contains(@text,'VIEW LIST')]")).click();

        // выйти из статьи ?
        waitElementPresentBy(By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]")).click();
        // выйти из поиска статей?
        //waitElementPresentBy(By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]")).click();
        //закрыть поиск org.wikipedia.beta:id/search_close_btn
        //обратно	android.widget.ImageButton
        waitElementPresentBy(By.xpath("//android.widget.ImageButton")).click();

        //Открыть мои статьи на гл экране
        waitElementPresentBy(By.xpath("//android.widget.FrameLayout[@content-desc=\"My lists\"]")).click();
        //Открыть Сохраненные
        waitElementPresentBy(By.xpath("//android.widget.TextView[contains(@text,'Saved')]")).click();
        //проверить наличие статьи id=
        //By.xpath("//*[@resource-id='org.wikipedia.beta:id/page_list_item_title'][@text='Appium']")
       // By articleLocation = By.xpath("//android.widget.TextView[@text='Appium']");
        By articleLocation = By.xpath("//android.view.ViewGroup/*[@resource-id='org.wikipedia.beta:id/page_list_item_title'][@text='Appium']");
        waitElementPresentBy(articleLocation);
        //свайпнуть и удалить
        swipeUpElementToLeft(articleLocation,200);
        // проверить что статья удалилась, неотображается
        Assert.assertTrue(waitElementNotPresentBy(articleLocation));
    }
    @Test
    public void twoArticleTest() throws InterruptedException {
        String toFind1 = "Appium";
        String toFind2 = "Mandarin";
        //пропустить
        waitElementPresentBy(By.xpath("//*[contains(@text,'SKIP')]")).click();
        //кликнуть поиск
        //waitElementPresentBy(By.xpath("//*[contains(@text,'Search Wikipedia')]")).click();//@class android.widget.TextView
        waitElementPresentBy(By.xpath("//android.widget.TextView[contains(@text,'Search Wikipedia')]")).click();//org.wikipedia.beta:id/search_container
        //(By.id("//org.wikipedia.beta:id/search_container")).click();//
        //в нов поле ввода ввести Значение
        waitElementPresentBy(By.id("org.wikipedia.beta:id/search_src_text")).sendKeys(toFind1);
        //открыть статью
        waitElementPresentBy(By.xpath("//*[@resource-id='org.wikipedia.beta:id/page_list_item_title'][@text='"+toFind1+"']")).click();
        //нажать SAVE org.wikipedia.beta:id/article_menu_bookmark
        waitElementPresentBy(By.id("org.wikipedia.beta:id/article_menu_bookmark")).click();
        //нажать ADD TO LIST
        waitElementPresentBy(By.xpath("//android.widget.Button[contains(@text,'ADD TO LIST')]")).click();
        //перейти на активную часть экрана org.wikipedia.beta:id/touch_outside
        waitElementPresentBy(By.id("org.wikipedia.beta:id/touch_outside")).click();
        // выйти из статьи на Поиск?
        waitElementPresentBy(By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]")).click();
        //в нов поле ввода ввести Значение
        waitElementPresentBy(By.id("org.wikipedia.beta:id/search_src_text")).clear();
        waitElementPresentBy(By.id("org.wikipedia.beta:id/search_src_text")).sendKeys(toFind2);
        //открыть статью
        waitElementPresentBy(By.xpath("//*[@resource-id='org.wikipedia.beta:id/page_list_item_title'][@text='"+toFind2+"']")).click();
        //нажать SAVE org.wikipedia.beta:id/article_menu_bookmark
        waitElementPresentBy(By.id("org.wikipedia.beta:id/article_menu_bookmark")).click();
        //нажать ADD TO LIST
        waitElementPresentBy(By.xpath("//android.widget.Button[contains(@text,'ADD TO LIST')]")).click();
        //перейти на активную часть экрана org.wikipedia.beta:id/touch_outside
        waitElementPresentBy(By.id("org.wikipedia.beta:id/touch_outside")).click();
        // выйти из статьи на Поиск?
        waitElementPresentBy(By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]")).click();
        //обратно	из Поиска
        waitElementPresentBy(By.xpath("//android.widget.ImageButton")).click();
        //Открыть мои статьи на гл экране
        waitElementPresentBy(By.xpath("//android.widget.FrameLayout[@content-desc=\"My lists\"]")).click();
        //Открыть Сохраненные
        waitElementPresentBy(By.xpath("//android.widget.TextView[contains(@text,'Saved')]")).click();
        //проверить наличие статьи id=
        By articleLocation1 = By.xpath("//android.view.ViewGroup/*[@resource-id='org.wikipedia.beta:id/page_list_item_title'][@text='"+toFind1+"']");
        By articleLocation2 = By.xpath("//android.view.ViewGroup/*[@resource-id='org.wikipedia.beta:id/page_list_item_title'][@text='"+toFind2+"']");
        waitElementPresentBy(articleLocation1);
        waitElementPresentBy(articleLocation2);
        //свайпнуть и удалить
        swipeUpElementToLeft(articleLocation2,200);
        // проверить что статья удалилась, неотображается
        Assert.assertTrue(waitElementNotPresentBy(articleLocation2));
        Assert.assertTrue(driver.findElement(articleLocation1).getText().equals(toFind1));
        driver.findElement(By.id("org.wikipedia.beta:id/page_list_item_title")).click();
        String afterSwipeTitle = waitElementPresentBy(By.xpath("//android.view.View[@text='"+toFind1+"']")).getText();
        Assert.assertEquals(toFind1,afterSwipeTitle);
    }

    @Test
    public void rotationTest(){
        String toFind = "How deep is your love";
        //пропустить
        waitElementPresentBy(By.xpath("//*[contains(@text,'SKIP')]")).click();
        //кликнуть поиск
        waitElementPresentBy(By.xpath("//android.widget.TextView[contains(@text,'Search Wikipedia')]")).click();
        //в нов поле ввода ввести Значение
        waitElementPresentBy(By.id("org.wikipedia.beta:id/search_src_text")).sendKeys(toFind);
        //открыть статью
        //waitElementPresentBy(By.id("org.wikipedia.beta:id/page_list_item_title")).click();
        //waitElementPresentBy(By.xpath("//*[@resource-id='org.wikipedia.beta:id/page_list_item_title']//*[contains(@text,'"+toFind+"')]")).click();
        waitElementPresentBy(By.id("org.wikipedia.beta:id/page_list_item_title")).click();
        //проверить заголовок
        By titleLocator = By.xpath("//android.view.View");
        String beforeRotationTitle = findElementAndGetAttribute(titleLocator,"text");
        driver.rotate(ScreenOrientation.LANDSCAPE);
        String afterRotationTitle = findElementAndGetAttribute(titleLocator,"text");
        Assert.assertEquals(beforeRotationTitle,afterRotationTitle);
    }
    @Test
    public void assertTitleTest(){
        String toFind = "Appium";
        //пропустить
        waitElementPresentBy(By.xpath("//*[contains(@text,'SKIP')]")).click();
        //кликнуть поиск
        waitElementPresentBy(By.xpath("//android.widget.TextView[contains(@text,'Search Wikipedia')]")).click();
        //в нов поле ввода ввести Значение
        waitElementPresentBy(By.id("org.wikipedia.beta:id/search_src_text")).sendKeys(toFind);
        //открыть статью
        waitElementPresentBy(By.id("org.wikipedia.beta:id/page_list_item_title")).click();
        //проверить заголовок
        Assert.assertTrue(assertElementPresent(By.xpath("//android.view.View[@text='"+toFind+"')]")));

    }
    @Test
    public void backGroundTest(){
        String toFind = "mandarin";
        //пропустить
        waitElementPresentBy(By.xpath("//*[contains(@text,'SKIP')]")).click();
        //кликнуть поиск
        waitElementPresentBy(By.xpath("//android.widget.TextView[contains(@text,'Search Wikipedia')]")).click();
        //в нов поле ввода ввести Значение
        waitElementPresentBy(By.id("org.wikipedia.beta:id/search_src_text")).sendKeys(toFind);
        //найти статью
        waitElementPresentBy(By.xpath("//android.view.ViewGroup/*[@resource-id='org.wikipedia.beta:id/page_list_item_title'][@text='Mandarin Chinese']"));
        driver.runAppInBackground(ofMillis(25));
        waitElementPresentBy(By.xpath("//android.view.ViewGroup/*[@resource-id='org.wikipedia.beta:id/page_list_item_title'][@text='Mandarin Chinese']"));
    }

    public WebElement waitElementPresentBy(By locator){
        WebDriverWait webDriverWait = new WebDriverWait(driver,5);
        webDriverWait.withMessage("No element present by locator: "+ locator);
        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));

    }

    public Boolean assertElementPresent(By locator){
        WebDriverWait webDriverWait = new WebDriverWait(driver,0);
        webDriverWait.withMessage("No element present by locator: "+ locator);
        List<WebElement> elements= webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        if (elements.size()>0){
            return true;
        }
        return false;

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

    public void swipeUp(int timeOfSwipe)  {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width/2;
        int startY = (int)(size.width * 0.8);
        int endY = (int)(size.width * 0.2);
        PointOption startPoint = new PointOption().withCoordinates(x,startY);
        PointOption endPoint = new PointOption().withCoordinates(x,endY);
        action.press(startPoint).waitAction(new WaitOptions().withDuration(ofMillis(timeOfSwipe))).moveTo(endPoint).release().perform();
    }
    public void swipeQuick() {
        swipeUp(200);
    }
    public void swipeUpToFindElement(By by, int maxSwipes) {
        int alreadySwipe = 0;
        while (driver.findElements(by).size() == 0) {
            if (alreadySwipe > maxSwipes){
                waitElementPresentBy(by);
                return;
            }
            swipeQuick();
            ++alreadySwipe;
        }
    }
    public void swipeUpElementToLeft(By by, int timeOfSwipe) {
        WebElement element = waitElementPresentBy(by);
        int leftX = (int)(element.getLocation().getX()* 0.1);
        int rightX = (int)( (leftX + element.getSize().getWidth()) * 0.9);//прибавили к левой точке ширину элемента и получили правую
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();//прибавили к верхней точке высоту элемента и получили нижнюю
        int middleY = (upperY + lowerY )/2;
        PointOption startPoint = new PointOption().withCoordinates(rightX, middleY);
        PointOption endPoint = new PointOption().withCoordinates(leftX,middleY);
        TouchAction action = new TouchAction(driver);
        action.press(startPoint).waitAction(new WaitOptions().withDuration(ofMillis(timeOfSwipe))).moveTo(endPoint).release().perform();

    }

    public String findElementAndGetAttribute( By locator, String attribute){
        return waitElementPresentBy(locator).getAttribute(attribute);

    }
}
