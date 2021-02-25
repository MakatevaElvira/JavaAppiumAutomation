package lib.ui.mobileWeb;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {
    public MWSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
    static {
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        SEARCH_INPUT = "css:form>input[type='search']";
        SEARCH_RESULT = "css:ul.page-list>li.page-summary";//xpath://ul/li//h3
        SEARCH_RESULT_TITLE_BY_TPL = "xpath://li[contains(@title,'{SUBSTRING}')]";
        SEARCH_RESULT_BY_TPL = "xpath://div[contains(@class,'wikidata-description')][contains(text(),'{SUBSTRING}')]";
        SEARCH_CANCEL_BUTTON = "css:div.header-action button.cancel";
        FIRST_ARTICLE_TITLE = "xpath://ul[@class='page-list thumbs actionable']/li";
        RETURN_TO_MAIN = "xpath://android.widget.ImageButton";
        SEARCH_EMPTY_RESULT= "css:p.without-results";
    }
}
