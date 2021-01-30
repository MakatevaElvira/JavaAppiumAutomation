import lib.CoreTestCase;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.time.Duration.ofMillis;

public class FirstTest extends CoreTestCase {
    private MainPageObject MainPageObject;
    public SearchPageObject SearchPageObject;

    protected void setUp() throws Exception {
     super.setUp();
     MainPageObject = new MainPageObject(driver);
     SearchPageObject  = new SearchPageObject(driver);
    }

   @Test
    public void testFirstSearch(){
       System.out.println("Java+Appium=start test!!!");
       MainPageObject.waitElementPresentBy(By.xpath("//*[contains(@text,'SKIP')]")).click();
       String searchText = MainPageObject.waitElementPresentBy(By.xpath("//*[contains(@text,'Search Wikipedia')]")).getText();
       System.out.println(searchText);
       //waitElementPresentBy(By.xpath("//android.widget.HorizontalScrollView[@content-desc=\"Page 1 of 4\"]/android.widget.LinearLayout/android.widget.LinearLayout[4]")).click();
       //waitElementPresentBy(By.id("org.wikipedia.beta:id/bottomOffset")).click();
      System.out.println("Java+Appium=finish test!!!");
    }
    @Test
    public void testOfElementsTextAssertion(){
        MainPageObject.waitElementPresentBy(By.xpath("//*[contains(@text,'SKIP')]")).click();
       Boolean result =  MainPageObject.assertElementHasText(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Search Wikipedia",
                "Elements text is not = Search Wikipedia");
        System.out.println("result= "+result);
       Assert.assertTrue(result);
    }
    @Test
    public void testOfSearchCancellation(){
        String toFind = "google";
        //пропустить
        MainPageObject.waitElementPresentBy(By.xpath("//*[contains(@text,'SKIP')]")).click();
        //кликнуть поиск
       // MainPageObject.waitElementPresentBy(By.xpath("//android.widget.TextView[contains(@text,'Search Wikipedia')]")).click();//org.wikipedia.beta:id/search_container
        SearchPageObject.initSearchInput();
        //в нов поле ввода ввести Значение
       // MainPageObject.waitElementPresentBy(By.id("org.wikipedia.beta:id/search_src_text")).sendKeys(toFind);
        SearchPageObject.typeSearchValue(toFind);
        // проверить в результате есть записи
        //MainPageObject.waitElementsPresentBy(By.id("org.wikipedia.beta:id/page_list_item_title"));
        SearchPageObject.waitSearchResult();
        // нажать крестик-очистить поиск
        MainPageObject.waitElementPresentBy(By.id("org.wikipedia.beta:id/search_close_btn")).click();
        // проверить результат = пустой поиск=тоже работает
        //waitElementPresentBy(By.id("org.wikipedia.beta:id/search_empty_image"));
        // либо нотпрезент
        Assert.assertTrue(MainPageObject.waitElementNotPresentBy(By.id("org.wikipedia.beta:id/page_list_item_title")));

    }
    @Test
    public void testSearchResultChecking(){
        String toFind = "Google";
        //пропустить
        MainPageObject.waitElementPresentBy(By.xpath("//*[contains(@text,'SKIP')]")).click();
        //кликнуть поиск
        //MainPageObject.waitElementPresentBy(By.xpath("//android.widget.TextView[contains(@text,'Search Wikipedia')]")).click();//org.wikipedia.beta:id/search_container
        SearchPageObject.initSearchInput();
        //в нов поле ввода ввести Значение
       // MainPageObject.waitElementPresentBy(By.id("org.wikipedia.beta:id/search_src_text")).sendKeys(toFind);
        SearchPageObject.typeSearchValue(toFind);
        // проверить результата assert
        List <WebElement> results = driver.findElements(By.id("org.wikipedia.beta:id/page_list_item_title"));
        for ( WebElement result: results){
            System.out.println("Result= "+ result.getText());
            Assert.assertTrue(result.getText().contains(toFind));
        }
    }
    @Test
    public void testSwiping() throws InterruptedException {
        String toFind = "Google";
        //пропустить
        MainPageObject.waitElementPresentBy(By.xpath("//*[contains(@text,'SKIP')]")).click();
        //кликнуть поиск
        SearchPageObject.initSearchInput();
        //в нов поле ввода ввести Значение
        SearchPageObject.typeSearchValue(toFind);
        //открыть статью
        MainPageObject.waitElementPresentBy(By.id("org.wikipedia.beta:id/page_list_item_title")).click();
        //свайпнуть
        MainPageObject.swipeUp(2000);
        MainPageObject.swipeUp(2000);
        MainPageObject.swipeUp(2000);
    }
    @Test
    public void testBeforeElementSwiping() throws InterruptedException {
        String toFind = "Appium";
        //пропустить
        MainPageObject.waitElementPresentBy(By.xpath("//*[contains(@text,'SKIP')]")).click();
        //кликнуть поиск
        //waitElementPresentBy(By.xpath("//*[contains(@text,'Search Wikipedia')]")).click();//@class android.widget.TextView
        MainPageObject.waitElementPresentBy(By.xpath("//android.widget.TextView[contains(@text,'Search Wikipedia')]")).click();//org.wikipedia.beta:id/search_container
        //(By.id("//org.wikipedia.beta:id/search_container")).click();//
        //в нов поле ввода ввести Значение
        MainPageObject.waitElementPresentBy(By.id("org.wikipedia.beta:id/search_src_text")).sendKeys(toFind);
        //открыть статью
        MainPageObject.waitElementPresentBy(By.xpath("//*[@resource-id='org.wikipedia.beta:id/page_list_item_title'][@text='Appium']")).click();
        //свайпнуть
        MainPageObject.swipeUpToFindElement(By.xpath("//*[@text='View article in browser']"),10);
    }

    @Test
    public void testBigUserTask() throws InterruptedException {
        String toFind = "Appium";
        //пропустить
        MainPageObject. waitElementPresentBy(By.xpath("//*[contains(@text,'SKIP')]")).click();
        //кликнуть поиск
        SearchPageObject.initSearchInput();
        //в нов поле ввода ввести Значение
        SearchPageObject.typeSearchValue(toFind);
        //открыть статью
        MainPageObject.waitElementPresentBy(By.xpath("//*[@resource-id='org.wikipedia.beta:id/page_list_item_title'][@text='Appium']")).click();
        //нажать три точки =не надо org.wikipedia.beta:id/article_menu_bookmark
        //waitElementPresentBy(By.xpath("//android.widget.ImageView[@content-desc=\"More options\"]")).click();
        //нажать SAVE org.wikipedia.beta:id/article_menu_bookmark
        MainPageObject.waitElementPresentBy(By.id("org.wikipedia.beta:id/article_menu_bookmark")).click();
        //нажать ADD TO LIST
        MainPageObject.waitElementPresentBy(By.xpath("//android.widget.Button[contains(@text,'ADD TO LIST')]")).click();
        //перейти на активную часть экрана org.wikipedia.beta:id/touch_outside
        MainPageObject.waitElementPresentBy(By.id("org.wikipedia.beta:id/touch_outside")).click();
        // выйти из статьи ?
        MainPageObject.waitElementPresentBy(By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]")).click();
        //обратно
        MainPageObject.waitElementPresentBy(By.xpath("//android.widget.ImageButton")).click();
        //Открыть мои статьи на гл экране
        MainPageObject.waitElementPresentBy(By.xpath("//android.widget.FrameLayout[@content-desc=\"My lists\"]")).click();
        //Открыть Сохраненные
        MainPageObject.waitElementPresentBy(By.xpath("//android.widget.TextView[contains(@text,'Saved')]")).click();
        //проверить наличие статьи id=
        By articleLocation = By.xpath("//android.view.ViewGroup/*[@resource-id='org.wikipedia.beta:id/page_list_item_title'][@text='Appium']");
        MainPageObject.waitElementPresentBy(articleLocation);
        //свайпнуть и удалить
        MainPageObject.swipeUpElementToLeft(articleLocation,200);
        // проверить что статья удалилась, неотображается
        Assert.assertTrue(MainPageObject.waitElementNotPresentBy(articleLocation));
    }
    @Test
    public void testTwoArticleSaving() throws InterruptedException {
        String toFind1 = "Appium";
        String toFind2 = "Mandarin";
        //пропустить
        MainPageObject.waitElementPresentBy(By.xpath("//*[contains(@text,'SKIP')]")).click();
        //кликнуть поиск
        SearchPageObject.initSearchInput();
        //в нов поле ввода ввести Значение
        SearchPageObject.typeSearchValue(toFind1);
        //открыть статью
        MainPageObject.waitElementPresentBy(By.xpath("//*[@resource-id='org.wikipedia.beta:id/page_list_item_title'][@text='"+toFind1+"']")).click();
        //нажать SAVE org.wikipedia.beta:id/article_menu_bookmark
        MainPageObject.waitElementPresentBy(By.id("org.wikipedia.beta:id/article_menu_bookmark")).click();
        //нажать ADD TO LIST
        MainPageObject.waitElementPresentBy(By.xpath("//android.widget.Button[contains(@text,'ADD TO LIST')]")).click();
        //перейти на активную часть экрана org.wikipedia.beta:id/touch_outside
        MainPageObject.waitElementPresentBy(By.id("org.wikipedia.beta:id/touch_outside")).click();
        // выйти из статьи на Поиск?
        MainPageObject.waitElementPresentBy(By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]")).click();
        //в нов поле ввода ввести Значение
        MainPageObject.waitElementPresentBy(By.id("org.wikipedia.beta:id/search_src_text")).clear();
        SearchPageObject.typeSearchValue(toFind2);
        //открыть статью
        MainPageObject.waitElementPresentBy(By.xpath("//*[@resource-id='org.wikipedia.beta:id/page_list_item_title'][@text='"+toFind2+"']")).click();
        //нажать SAVE org.wikipedia.beta:id/article_menu_bookmark
        MainPageObject.waitElementPresentBy(By.id("org.wikipedia.beta:id/article_menu_bookmark")).click();
        //нажать ADD TO LIST
        MainPageObject.waitElementPresentBy(By.xpath("//android.widget.Button[contains(@text,'ADD TO LIST')]")).click();
        //перейти на активную часть экрана org.wikipedia.beta:id/touch_outside
        MainPageObject.waitElementPresentBy(By.id("org.wikipedia.beta:id/touch_outside")).click();
        // выйти из статьи на Поиск?
        MainPageObject.waitElementPresentBy(By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]")).click();
        //обратно	из Поиска
        MainPageObject.waitElementPresentBy(By.xpath("//android.widget.ImageButton")).click();
        //Открыть мои статьи на гл экране
        MainPageObject.waitElementPresentBy(By.xpath("//android.widget.FrameLayout[@content-desc=\"My lists\"]")).click();
        //Открыть Сохраненные
        MainPageObject.waitElementPresentBy(By.xpath("//android.widget.TextView[contains(@text,'Saved')]")).click();
        //проверить наличие статьи id=
        By articleLocation1 = By.xpath("//android.view.ViewGroup/*[@resource-id='org.wikipedia.beta:id/page_list_item_title'][@text='"+toFind1+"']");
        By articleLocation2 = By.xpath("//android.view.ViewGroup/*[@resource-id='org.wikipedia.beta:id/page_list_item_title'][@text='"+toFind2+"']");
        MainPageObject.waitElementPresentBy(articleLocation1);
        MainPageObject.waitElementPresentBy(articleLocation2);
        //свайпнуть и удалить
        MainPageObject.swipeUpElementToLeft(articleLocation2,200);
        // проверить что статья удалилась, неотображается
        Assert.assertTrue(MainPageObject.waitElementNotPresentBy(articleLocation2));
        Assert.assertTrue(driver.findElement(articleLocation1).getText().equals(toFind1));
        driver.findElement(By.id("org.wikipedia.beta:id/page_list_item_title")).click();
        String afterSwipeTitle = MainPageObject.waitElementPresentBy(By.xpath("//android.view.View[@text='"+toFind1+"']")).getText();
        Assert.assertEquals(toFind1,afterSwipeTitle);
    }

    @Test
    public void testRotation(){
        String toFind = "How deep is your love";
        //пропустить
        MainPageObject.waitElementPresentBy(By.xpath("//*[contains(@text,'SKIP')]")).click();
        //кликнуть поиск
        SearchPageObject.initSearchInput();
        //в нов поле ввода ввести Значение
        SearchPageObject.typeSearchValue(toFind);
        //открыть статью
        MainPageObject.waitElementPresentBy(By.id("org.wikipedia.beta:id/page_list_item_title")).click();
        //проверить заголовок
        By titleLocator = By.xpath("//android.view.View");
        String beforeRotationTitle = MainPageObject.findElementAndGetAttribute(titleLocator,"text");
        driver.rotate(ScreenOrientation.LANDSCAPE);
        String afterRotationTitle = MainPageObject.findElementAndGetAttribute(titleLocator,"text");
        Assert.assertEquals(beforeRotationTitle,afterRotationTitle);
    }
    @Test
    public void testTitleAssertion(){
        String toFind = "Appium";
        //пропустить
        MainPageObject.waitElementPresentBy(By.xpath("//*[contains(@text,'SKIP')]")).click();
        //кликнуть поиск
        SearchPageObject.initSearchInput();
        //в нов поле ввода ввести Значение
        SearchPageObject.typeSearchValue(toFind);
        //открыть статью
        MainPageObject.waitElementPresentBy(By.id("org.wikipedia.beta:id/page_list_item_title")).click();
        //проверить заголовок
        Assert.assertTrue(MainPageObject.assertElementPresent(By.xpath("//android.view.View[@text='"+toFind+"')]")));

    }
    @Test
    public void backGroundTest(){
        String toFind = "mandarin";
        //пропустить
        MainPageObject.waitElementPresentBy(By.xpath("//*[contains(@text,'SKIP')]")).click();
        //кликнуть поиск
        SearchPageObject.initSearchInput();
        //в нов поле ввода ввести Значение
        SearchPageObject.typeSearchValue(toFind);
        //найти статью
        MainPageObject.waitElementPresentBy(By.xpath("//android.view.ViewGroup/*[@resource-id='org.wikipedia.beta:id/page_list_item_title'][@text='Mandarin Chinese']"));
        driver.runAppInBackground(ofMillis(25));
        MainPageObject.waitElementPresentBy(By.xpath("//android.view.ViewGroup/*[@resource-id='org.wikipedia.beta:id/page_list_item_title'][@text='Mandarin Chinese']"));
    }

}
