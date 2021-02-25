package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidArticlePageObject extends ArticlePageObject {
    public AndroidArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    static {
        VIEW_ELEMENT = "xpath://*[@text='View article in browser']";
        ARTICLE_MENU_BOOKMARK = "id:org.wikipedia.beta:id/article_menu_bookmark";
        ADD_TO_LIST = "xpath://android.widget.Button[contains(@text,'ADD TO LIST')]";
        TOUCH_OUTSIDE = "id:org.wikipedia.beta:id/touch_outside";
        NAVIGATE_UP = "xpath://android.widget.ImageButton[@content-desc=\"Navigate up\"]";
    }
}
