package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import lib.ui.MyListPageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;

import static java.time.Duration.ofMillis;

public class FirstTest extends CoreTestCase {
    private MainPageObject MainPageObject;
    public SearchPageObject SearchPageObject;
    public ArticlePageObject ArticlePageObject;
    public MyListPageObject MyListPageObject;

    protected void setUp() throws Exception {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
        SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject = ArticlePageObjectFactory.get(driver);
        MyListPageObject =  MyListPageObjectFactory.get(driver);
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
        if (Platform.getInstance().isAndroid()){
            ArticlePageObject.saveArticleToMyList();
            //нажать ADD TO LIST + перейти на активную часть экрана
            ArticlePageObject.continueAddToList();
        }else { //с учетом особеннстей IOS
            ArticlePageObject.saveArticleToMyList();
            //нажать ADD TO LIST + перейти на активную часть экрана
            ArticlePageObject.addArticleToMySaved();
        }
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

}
