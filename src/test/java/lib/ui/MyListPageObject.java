package lib.ui;

import io.appium.java_client.AppiumDriver;
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
            MY_ARTICLE_NAME_TPL ;

    public void openSaved() {
        waitElementPresentBy((SAVED_TITLES)).click();
    }

    public void waiTMyArticlePresentByName(String name) {
        waitElementPresentBy((getArticleByText(name)));
    }

    public Boolean waiTMyArticleNotPresentByName(String name) {
        return waitElementNotPresentBy((getArticleByText(name)));
    }

    public WebElement findMyArticleByName(String name) {
        return waitElementPresentBy((getArticleByText(name)));
    }

    public void openMyArticle() {
        waitElementPresentBy((ARTICLE_ELEMENT)).click();
    }

    public void swipeMyArticleToDelete(String name, int time) {
        swipeUpElementToLeft(getArticleByText(name), time);
    }

    /*TEMPLATES METHODS */
    public static String getArticleByText(String text) {
        return MY_ARTICLE_NAME_TPL.replace("{ArticleName}", text);
    }
    /*TEMPLATES METHODS */
}
