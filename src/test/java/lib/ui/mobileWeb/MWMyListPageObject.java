package lib.ui.mobileWeb;

import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListPageObject extends MyListPageObject {
    public MWMyListPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    static {
        SAVED_TITLES = "id:'Saved')";
        ARTICLE_ELEMENT = "id:org.wikipedia.beta:id/page_list_item_title";
        REMOVED_FROM_SAVED_BUTTON_TPL = "xpath://h3[contains(text(),'{ArticleTitle}')]/../../a[contains(@class,'watched')]";
        MY_ARTICLE_NAME_TPL = "xpath://h3[contains(text(),'{ArticleName}')]";
    }
}
