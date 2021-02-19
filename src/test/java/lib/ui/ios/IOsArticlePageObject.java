package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class IOsArticlePageObject extends ArticlePageObject {
    public IOsArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    static {
        VIEW_ELEMENT = "id:View article in browser";
        ARTICLE_MENU_BOOKMARK = "id:org.wikipedia.beta:id/article_menu_bookmark";
        ADD_TO_LIST = "id:ADD TO LIST";
        TOUCH_OUTSIDE = "id:org.wikipedia.beta:id/touch_outside";
        NAVIGATE_UP = "id:Navigate up";
    }
}
