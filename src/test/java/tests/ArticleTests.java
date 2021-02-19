package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import lib.ui.MyListPageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {
    public MainPageObject MainPageObject;
    public SearchPageObject SearchPageObject;
    public ArticlePageObject ArticlePageObject;
    public MyListPageObject MyListPageObject;

    protected void setUp() throws Exception {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
        SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject = ArticlePageObjectFactory.get(driver);
        MyListPageObject = MyListPageObjectFactory.get(driver);
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
        MyListPageObject.swipeMyArticleToDelete(toFind2, 200);
        // проверить что статья удалилась, неотображается
        Assert.assertTrue(MyListPageObject.waiTMyArticleNotPresentByName(toFind2));
        //Assert.assertTrue(driver.findElement(articleLocation1).getText().equals(toFind1));
        Assert.assertTrue(MyListPageObject.findMyArticleByName(toFind1).getText().equals(toFind1));
        //driver.findElement(By.id("org.wikipedia.beta:id/page_list_item_title")).click();
        MyListPageObject.openMyArticle();
        String afterSwipeTitle = ArticlePageObject.getArticleTitle(toFind1);
        Assert.assertEquals(toFind1, afterSwipeTitle);
    }
}
