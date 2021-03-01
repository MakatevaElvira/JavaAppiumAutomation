package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MainPageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {
    public MainPageObject MainPageObject;
    public SearchPageObject SearchPageObject;
    public ArticlePageObject ArticlePageObject;
    public MyListPageObject MyListPageObject;
    public AuthorizationPageObject AuthorizationPageObject;
    private static final String login = "ElviraMak";
    private static final String password = "Makateva";

    public void setUp() throws Exception {
        super.setUp();
        MainPageObject =  MainPageObjectFactory.get(driver);
        SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject = ArticlePageObjectFactory.get(driver);
        MyListPageObject = MyListPageObjectFactory.get(driver);
        AuthorizationPageObject = new AuthorizationPageObject(driver);
    }

    /*@BeforeClass()
    public void loginForWeb(){
        if (Platform.getInstance().isMW()){
            AuthorizationPageObject.generalLogin(login,password);
        } else {
            System.out.println("No autorization for platform= "+Platform.getInstance().getPlatformVar());
        }
    }*/

    @Test
    public void testTwoArticleSaving() throws InterruptedException {
        String toFind1 = "Appium";
        String toFind2 = "Mandarin";
        if (Platform.getInstance().isMW()){
          AuthorizationPageObject.generalLogin(login,password);
        } else {
            System.out.println("No autorization for platform= "+Platform.getInstance().getPlatformVar());
        }
        //пропустить
        if (Platform.getInstance().isMW()){
            System.out.println("No SKIP for platform= "+Platform.getInstance().getPlatformVar());
        }else {
            MainPageObject.skipStartInformation();
        }
        //кликнуть поиск
        SearchPageObject.initSearchInput();
        //в нов поле ввода ввести Значение
        SearchPageObject.typeSearchValue(toFind1);
        //открыть статью
        SearchPageObject.openArticleByTitle(toFind1);
        //добавить в избранное
        ArticlePageObject.addToMyList();
        //для моб веб авторизоваться
       // new AuthorizationPageObject(driver).loginToWiki(login,password); //тут потом проблема!
        //выйти из статьи
        ArticlePageObject.closeArticle();
        // выполнить новый поиск
        //кликнуть поиск
        SearchPageObject.initSearchInput();
        //в нов поле ввода ввести Значение
        SearchPageObject.typeSearchValue(toFind2);
        //открыть статью
        SearchPageObject.openArticleByTitle(toFind2);
        //добавить в избранное
        ArticlePageObject.addToMyList();
        //для моб веб авторизоваться
        //new AuthorizationPageObject(driver).loginToWiki(login,password);
        //выйти из статьи
        ArticlePageObject.closeArticle(); //
        //перейти в воч лист
        MainPageObject.openNavigation();//ля веба
        //обратно
        SearchPageObject.returnToMainPage();// кроме веба
        //ОТКРЫТЬ МОИ ИЗБРАННЫЕ СТАТЬИ:
        MainPageObject.openMySavedArticles();
        //проверить наличие статьи
        MyListPageObject.waiTMyArticlePresentByName(toFind1);
        MyListPageObject.swipeBeforeArticleName(toFind2,5);
        MyListPageObject.waiTMyArticlePresentByName(toFind2);
        //найти, свайпнуть и удалить
        MyListPageObject.swipeMyArticleToDelete(toFind2, 200);
        // проверить что статья удалилась, неотображается
        Assert.assertTrue(MyListPageObject.waiTMyArticleNotPresentByName(toFind2));
        //Assert.assertTrue(driver.findElement(articleLocation1).getText().equals(toFind1));
        MyListPageObject.swipeBeforeArticleName(toFind1,5);
        Assert.assertTrue(MyListPageObject.findMyArticleByName(toFind1).getText().equals(toFind1));
        //driver.findElement(By.id("org.wikipedia.beta:id/page_list_item_title")).click();
        MyListPageObject.openMyArticleByName(toFind1);
        String afterSwipeTitle = ArticlePageObject.getArticleTitle(toFind1);
        Assert.assertEquals(toFind1, afterSwipeTitle);
    }
}
