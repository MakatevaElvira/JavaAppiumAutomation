package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOsSearchPageObject extends SearchPageObject {
    public IOsSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
    static {
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@value='Search Wikipedia']";
        SEARCH_RESULT = "xpath://XCUIElementTypeLink";
        SEARCH_RESULT_BY_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{SUBSTRING}')]";
        SEARCH_EMPTY_RESULT = "xpath://XCUIElementTypeStaticText[@name='No results found']";
        SEARCH_CANCEL_BUTTON = "id:Close";
        //FIRST_ARTICLE_TITLE = "id:org.wikipedia.beta:id/page_list_item_title";
       // RETURN_TO_MAIN = "xpath://android.widget.ImageButton";
    }
}

