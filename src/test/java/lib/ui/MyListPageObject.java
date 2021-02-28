package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.factories.MyListPageObjectFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListPageObject extends MainPageObject {
    public MyListPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    protected static String
            SAVED_TITLES ,
            ARTICLE_ELEMENT ,
            REMOVED_FROM_SAVED_BUTTON_TPL,
            MY_ARTICLE_NAME_TPL ;

    public void openSaved() {
        waitElementPresentBy((SAVED_TITLES)).click();
    }

    public void waiTMyArticlePresentByName(String name) {
        //String locator = getArticleByText(name);
        waitElementPresentBy((getArticleByText(name)));
    }

    public Boolean waiTMyArticleNotPresentByName(String name) {
        return waitElementNotPresentBy((getArticleByText(name)));
    }

    public WebElement findMyArticleByName(String name) {
        return waitElementPresentBy((getArticleByText(name)));
    }
    public WebElement findMyArticleByElementsName(String name) {
        return waitElementPresentBy((getArticleByText(name)));
    }

    public void openMyArticle() {
        waitElementPresentBy((ARTICLE_ELEMENT)).click();
    }
    public void openMyArticleByName(String name) {
        waitElementPresentBy(getArticleByText(name)).click();
    }

    public void swipeMyArticleToDelete(String name, int time) {
        //waitElementPresentBy(name);
        String articleXpath = getArticleByText(name);
        if ((Platform.getInstance().isIOs()) || (Platform.getInstance().isAndroid())){
            swipeUpElementToLeft(getArticleByText(name), time);
        } else {
            String removeLocator = getRemovedButtonByTitle(name);
            waitElementPresentBy(removeLocator).click();
        }
        if (Platform.getInstance().isIOs()){
            clickToElementsUpperCorner(articleXpath);
        }
        if (Platform.getInstance().isMW()){
            driver.navigate().refresh();
        }
    }
    public void swipeBeforeArticleName(String name,int size) {
        String articleXpath =getArticleByText(name);
        if (Platform.getInstance().isAndroid()) {
            swipeUpToFindElement((articleXpath), size);
        } else if (Platform.getInstance().isIOs()) {
            swipeUpElementAppear(articleXpath, size);
        } else { //!isElementPresent(articleXpath)
            scrollWebElementTillNotVisible(articleXpath, size);
        }
    }

    /*TEMPLATES METHODS */
    public static String getArticleByText(String text) {
        return MY_ARTICLE_NAME_TPL.replace("{ArticleName}", text);
    }
    public static String getRemovedButtonByTitle(String text) {
        return REMOVED_FROM_SAVED_BUTTON_TPL.replace("{ArticleTitle}", text);
    }
    /*TEMPLATES METHODS */
}
