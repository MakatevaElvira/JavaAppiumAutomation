package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSearchPageObject extends SearchPageObject {
    public AndroidSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
     static {
         SEARCH_INIT_ELEMENT = "xpath://android.widget.TextView[contains(@text,'Search Wikipedia')]";
                 SEARCH_INPUT = "id:org.wikipedia.beta:id/search_src_text";
                 SEARCH_RESULT = "id:org.wikipedia.beta:id/page_list_item_title";
                 SEARCH_RESULT_BY_TPL = "xpath://*[contains(@text,'{SUBSTRING}']";
                 SEARCH_CANCEL_BUTTON = "id:org.wikipedia.beta:id/search_close_btn";
                 FIRST_ARTICLE_TITLE = "id:org.wikipedia.beta:id/page_list_item_title";
                 RETURN_TO_MAIN = "xpath://android.widget.ImageButton";
     }
}
