package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import lib.ui.MyListPageObject;
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
    public ArticlePageObject ArticlePageObject;
    public MyListPageObject MyListPageObject;

    protected void setUp() throws Exception {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
        SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject = new ArticlePageObject(driver);
        MyListPageObject = new MyListPageObject(driver);
    }

    @Test
    public void testFirstSearch() {
        System.out.println("Java+Appium=start test!!!");
        MainPageObject.skipStartInformation();
        String searchText = MainPageObject.findSearchInputField().getText();
        System.out.println(searchText);
        //waitElementPresentBy(By.xpath("//android.widget.HorizontalScrollView[@content-desc=\"Page 1 of 4\"]/android.widget.LinearLayout/android.widget.LinearLayout[4]")).click();
        //waitElementPresentBy(By.id("org.wikipedia.beta:id/bottomOffset")).click();
        System.out.println("Java+Appium=finish test!!!");
    }

    @Test
    public void testOfElementsTextAssertion() {
        MainPageObject.skipStartInformation();
        Boolean result = MainPageObject.assertElementHasText(("xpath://*[contains(@text,'Search Wikipedia')]"), "Search Wikipedia",
                "Elements text is not = Search Wikipedia");
        System.out.println("result= " + result);
        Assert.assertTrue(result);
    }

    @Test
    public void testOfSearchCancellation() {
        String toFind = "google";
        //пропустить
        MainPageObject.skipStartInformation();
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
        MainPageObject.skipStartInformation();
        //кликнуть поиск
        //MainPageObject.waitElementPresentBy(By.xpath("//android.widget.TextView[contains(@text,'Search Wikipedia')]")).click();//org.wikipedia.beta:id/search_container
        SearchPageObject.initSearchInput();
        //в нов поле ввода ввести Значение
        // MainPageObject.waitElementPresentBy(By.id("org.wikipedia.beta:id/search_src_text")).sendKeys(toFind);
        SearchPageObject.typeSearchValue(toFind);
        // проверить результата assert
        List<WebElement> results = driver.findElements(By.id("org.wikipedia.beta:id/page_list_item_title"));
        for (WebElement result : results) {
            System.out.println("Result= " + result.getText());
            Assert.assertTrue(result.getText().contains(toFind));
        }
    }

    @Test
    public void testSwiping() throws InterruptedException {
        String toFind = "Google";
        //пропустить
        MainPageObject.skipStartInformation();
        //кликнуть поиск
        SearchPageObject.initSearchInput();
        //в нов поле ввода ввести Значение
        SearchPageObject.typeSearchValue(toFind);
        //открыть статью
        SearchPageObject.openFirstArticle();
        //свайпнуть
        ArticlePageObject.swipeUp(2000);
        ArticlePageObject.swipeUp(2000);
        ArticlePageObject.swipeUp(2000);
    }

    @Test
    public void testBeforeElementSwiping() throws InterruptedException {
        String toFind = "Appium";
        //пропустить
        MainPageObject.skipStartInformation();
        SearchPageObject.initSearchInput();
        //в нов поле ввода ввести Значение
        SearchPageObject.typeSearchValue(toFind);
        //открыть статью
        SearchPageObject.openFirstArticle();
        //свайпнуть
        ArticlePageObject.swipeToViewElement(10);
    }

    @Test
    public void testBigUserTask() throws InterruptedException {
        String toFind = "Appium";
        //пропустить
        MainPageObject.skipStartInformation();
        //кликнуть поиск
        SearchPageObject.initSearchInput();
        //в нов поле ввода ввести Значение
        SearchPageObject.typeSearchValue(toFind);
        //открыть статью
        SearchPageObject.openArticleByTitle(toFind);
        //нажать SAVE org.wikipedia.beta:id/article_menu_bookmark
        ArticlePageObject.saveArticleToMyList();
        //нажать ADD TO LIST + перейти на активную часть экрана
        ArticlePageObject.continueAddToList();
        // выйти из статьи ?
        ArticlePageObject.exitFromArticle();
        //обратно
        SearchPageObject.returnToMainPage();
        //Открыть мои статьи на гл экране
        MainPageObject.openMyList();
        //Открыть Сохраненные
        MyListPageObject.openSaved();
        //проверить наличие статьи
        String articleLocation = "xpath://android.view.ViewGroup/*[@resource-id='org.wikipedia.beta:id/page_list_item_title'][@text='Appium']";
        //MainPageObject.waitElementPresentBy(articleLocation);
        MyListPageObject.waitElementPresentBy(articleLocation);
        //свайпнуть и удалить
        //MainPageObject.swipeUpElementToLeft(articleLocation, 200);
        MyListPageObject.swipeMyArticleToDelete(toFind,200);
        // проверить что статья удалилась, неотображается
        Assert.assertTrue(MyListPageObject.waiTMyArticleNotPresentByName(toFind));

    }

    @Test
    public void testTwoArticleSaving() throws InterruptedException {
        String toFind1 = "Appium";
        String toFind2 = "Mandarin";
        //пропустить
        MainPageObject.skipStartInformation();
        //кликнуть поиск
        SearchPageObject.initSearchInput();
        //в нов поле ввода ввести Значение
        SearchPageObject.typeSearchValue(toFind1);
        //открыть статью
        SearchPageObject.openArticleByTitle(toFind1);
        //нажать SAVE org.wikipedia.beta:id/article_menu_bookmark
        ArticlePageObject.saveArticleToMyList();
        //нажать ADD TO LIST + перейти на активную часть экрана
        ArticlePageObject.continueAddToList();
        // выйти из статьи ?
        ArticlePageObject.exitFromArticle();
        //в нов поле ввода ввести Значение
        MainPageObject.waitElementPresentBy(("id:org.wikipedia.beta:id/search_src_text")).clear();
        SearchPageObject.typeSearchValue(toFind2);
        //открыть статью
        SearchPageObject.openArticleByTitle(toFind2);
        //нажать SAVE org.wikipedia.beta:id/article_menu_bookmark
        ArticlePageObject.saveArticleToMyList();
        //нажать ADD TO LIST + перейти на активную часть экрана
        ArticlePageObject.continueAddToList();
        // выйти из статьи ?
        ArticlePageObject.exitFromArticle();
        //обратно
        SearchPageObject.returnToMainPage();
        //Открыть мои статьи на гл экране
        MainPageObject.openMyList();
        //Открыть Сохраненные
        MyListPageObject.openSaved();
        //проверить наличие статьи
        MyListPageObject.waiTMyArticlePresentByName(toFind1);
        MyListPageObject.waiTMyArticlePresentByName(toFind2);
        //свайпнуть и удалить
        MyListPageObject.swipeMyArticleToDelete(toFind2,200);
        // проверить что статья удалилась, неотображается
        Assert.assertTrue(MyListPageObject.waiTMyArticleNotPresentByName(toFind2));
        //Assert.assertTrue(driver.findElement(articleLocation1).getText().equals(toFind1));
        Assert.assertTrue(MyListPageObject.findMyArticleByName(toFind1).getText().equals(toFind1));
        //driver.findElement(By.id("org.wikipedia.beta:id/page_list_item_title")).click();
        MyListPageObject.openMyArticle();
        String afterSwipeTitle = ArticlePageObject.getArticleTitle(toFind1);
        Assert.assertEquals(toFind1, afterSwipeTitle);
    }

    @Test
    public void testRotation() {
        String toFind = "How deep is your love";
        //пропустить
        MainPageObject.skipStartInformation();
        //кликнуть поиск
        SearchPageObject.initSearchInput();
        //в нов поле ввода ввести Значение
        SearchPageObject.typeSearchValue(toFind);
        //открыть статью
        SearchPageObject.openFirstArticle();
        //проверить заголовок
        By titleLocator = By.xpath("//android.view.View");
        //String beforeRotationTitle = MainPageObject.findElementAndGetAttribute(titleLocator, "text");
        String beforeRotationTitle = ArticlePageObject.getTitleText();
        driver.rotate(ScreenOrientation.LANDSCAPE);
       // String afterRotationTitle = MainPageObject.findElementAndGetAttribute(titleLocator, "text");
        String afterRotationTitle = ArticlePageObject.getTitleText();
        Assert.assertEquals(beforeRotationTitle, afterRotationTitle);
    }

    @Test
    public void testTitleAssertion() {
        String toFind = "Appium";
        //пропустить
        MainPageObject.skipStartInformation();
        //кликнуть поиск
        SearchPageObject.initSearchInput();
        //в нов поле ввода ввести Значение
        SearchPageObject.typeSearchValue(toFind);
        //открыть статью
        SearchPageObject.openFirstArticle();
        //проверить заголовок
        //Assert.assertTrue(MainPageObject.assertElementPresent(By.xpath("//android.view.View[@text='" + toFind + "')]")));
        Assert.assertEquals(ArticlePageObject.getArticleTitle(toFind),toFind);

    }

    @Test
    public void testOfBackGround() {
        String toFind = "mandarin";
        //пропустить
        MainPageObject.skipStartInformation();
        //кликнуть поиск
        SearchPageObject.initSearchInput();
        //в нов поле ввода ввести Значение
        SearchPageObject.typeSearchValue(toFind);
        //найти статью
        SearchPageObject.getResultSearchByText("Mandarin Chinese");
        //.waitElementPresentBy(By.xpath("//android.view.ViewGroup/*[@resource-id='org.wikipedia.beta:id/page_list_item_title'][@text='Mandarin Chinese']"));
        driver.runAppInBackground(ofMillis(25));
        SearchPageObject.getResultSearchByText("Mandarin Chinese");
        //MainPageObject.waitElementPresentBy(By.xpath("//android.view.ViewGroup/*[@resource-id='org.wikipedia.beta:id/page_list_item_title'][@text='Mandarin Chinese']"));
    }
    @Test
    public void testAssertTitleAndDescription() throws InterruptedException {
        String toFind = "Mandarin";
        String description = "Chinese";
        //пропустить
        MainPageObject.skipStartInformation();
        //кликнуть поиск
        SearchPageObject.initSearchInput();
        //в нов поле ввода ввести Значение
        SearchPageObject.typeSearchValue(toFind);
        //поискать результат по названию и описанию статьи
        Assert.assertTrue(SearchPageObject.waitForElementByTitleAndDescription(toFind,description));
    }
    @Test
    public void testSearchResultsAssertion() throws InterruptedException {
        String toFind = "Mandarin";
        String description = "Chinese";
        //пропустить
        MainPageObject.skipStartInformation();
        //кликнуть поиск
        SearchPageObject.initSearchInput();
        //в нов поле ввода ввести Значение
        SearchPageObject.typeSearchValue(toFind);
        //SearchPageObject.swipeUp(1500);
        //проверить результаты
        SearchPageObject.searchCount();
    }

}
