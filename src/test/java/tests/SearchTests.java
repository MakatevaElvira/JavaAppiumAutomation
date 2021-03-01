package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
@Epic("Tests for SEARCH!")
public class SearchTests extends CoreTestCase {
    public MainPageObject MainPageObject;
    public SearchPageObject SearchPageObject;

    public void setUp() throws Exception {
        super.setUp();
        MainPageObject =  MainPageObjectFactory.get(driver);
        SearchPageObject = SearchPageObjectFactory.get(driver);
    }
    @Test()
    @Features(value = {@Feature(value ="Main"),@Feature(value = "Search")})
    @DisplayName("First little search test")
    @Description("Тест который проверяет, что вообще что-то работает")
    @Severity(value = SeverityLevel.BLOCKER)
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
    @Features(value = {@Feature(value ="Main"),@Feature(value = "Search")})
    @DisplayName("Test to verify cancellation")
    @Description("Тест который проверяет работу Отмены поиска")
    @Severity(value = SeverityLevel.NORMAL)
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

    @Test()
    @Features(value = {@Feature(value ="Main"),@Feature(value = "Search")})
    @DisplayName("Search test to check result")
    @Description("Тест который проверяет, что в списке результатов искомые статьи")
    @Severity(value = SeverityLevel.CRITICAL)
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
        //List<WebElement> results = driver.findElements(By.id("org.wikipedia.beta:id/page_list_item_title"));
        List<WebElement> results =SearchPageObject.getSearchResultsList(toFind);
        for (WebElement result : results) {
            System.out.println("Result= " + result.getText());
            Assert.assertTrue(result.getText().contains(toFind));
        }
    }
    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Search test to check result count")
    @Description("Тест который проверяет, что в списке резкльтатов есть как минимум 3 значения")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSearchResultsAssertion() throws InterruptedException {
        String toFind = "Mandarin";
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
        //SearchPageObject.takeScreenShot("Search results page");
        //проверить результаты
        Assert.assertTrue(SearchPageObject.searchCountNew(toFind) >2);
    }

}
