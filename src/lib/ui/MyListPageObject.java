package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MyListPageObject extends MainPageObject {
    public MyListPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static final String
            SAVED_TITLES = "xpath://android.widget.TextView[contains(@text,'Saved')]",
            ARTICLE_ELEMENT = "id:org.wikipedia.beta:id/page_list_item_title",
            MY_ARTICLE_NAME_TPL =
                    "xpath://android.view.ViewGroup/*[@resource-id='org.wikipedia.beta:id/page_list_item_title'][@text='{ArticleName}']";

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
        swipeUpElementToLeft("xpath:"+getArticleByText(name), time);
    }

    /*TEMPLATES METHODS */
    public static String getArticleByText(String text) {
        return MY_ARTICLE_NAME_TPL.replace("{ArticleName}", text);
    }
    /*TEMPLATES METHODS */
}
