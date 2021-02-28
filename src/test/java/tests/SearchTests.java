package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import lib.ui.MyListPageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MainPageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchTests extends CoreTestCase {
    public MainPageObject MainPageObject;
    public SearchPageObject SearchPageObject;

    protected void setUp() throws Exception {
        super.setUp();
        MainPageObject =  MainPageObjectFactory.get(driver);
        SearchPageObject = SearchPageObjectFactory.get(driver);
    }
    @Test
    public void testFirstSearch() {
        System.out.println("Java+Appium=start test!!!");
        if (Platform.getInstance().isMW()){
            System.out.println("No SKIP for platform= "+Platform.getInstance().getPlatformVar());
        }else {
            MainPageObject.skipStartInformation();
        }
        String searchText = MainPageObject.findSearchInputField().getText();
        System.out.println(searchText);
        System.out.println("Java+Appium=finish test!!!");
    }
    @Test
    public void testOfSearchCancellation() {
        String toFind = "google";
        //пропустить
        if (Platform.getInstance().isMW()){
            System.out.println("No SKIP for platform= "+Platform.getInstance().getPlatformVar());
        }else {
            MainPageObject.skipStartInformation();
        }
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
        SearchPageObject.tabCancelSearchButton();
        // проверить результат = пустой поиск=тоже работает waitElementPresentBy(By.id("org.wikipedia.beta:id/search_empty_image"));
        // либо нотпрезент
        Assert.assertTrue(SearchPageObject.waitSearchResultNotPresent());

    }

    @Test
    public void testSearchResultChecking() {
        String toFind = "Google";
        //пропустить
        if (Platform.getInstance().isMW()){
            System.out.println("No SKIP for platform= "+Platform.getInstance().getPlatformVar());
        }else {
            MainPageObject.skipStartInformation();
        }
        //кликнуть поиск
        //MainPageObject.waitElementPresentBy(By.xpath("//android.widget.TextView[contains(@text,'Search Wikipedia')]")).click();//org.wikipedia.beta:id/search_container
        SearchPageObject.initSearchInput();
        //в нов поле ввода ввести Значение
        // MainPageObject.waitElementPresentBy(By.id("org.wikipedia.beta:id/search_src_text")).sendKeys(toFind);
        SearchPageObject.typeSearchValue(toFind);
        // проверить результат assert
        List<WebElement> results = driver.findElements(By.id("org.wikipedia.beta:id/page_list_item_title"));
        for (WebElement result : results) {
            System.out.println("Result= " + result.getText());
            Assert.assertTrue(result.getText().contains(toFind));
        }
    }
    @Test
    public void testSearchResultsAssertion() throws InterruptedException {
        String toFind = "Mandarin";
        String description = "Chinese";
        //пропустить
        if (Platform.getInstance().isMW()){
            System.out.println("No SKIP for platform= "+Platform.getInstance().getPlatformVar());
        }else {
            MainPageObject.skipStartInformation();
        }
        //кликнуть поиск
        SearchPageObject.initSearchInput();
        //в нов поле ввода ввести Значение
        SearchPageObject.typeSearchValue(toFind);
        //SearchPageObject.swipeUp(1500);
        //проверить результаты
        SearchPageObject.searchCount();
    }
}
