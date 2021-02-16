package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;

public class IOsMyListPageObject extends MyListPageObject {
    public IOsMyListPageObject(AppiumDriver driver) {
        super(driver);
    }

    static {
        SAVED_TITLES = "id:'Saved')";
        ARTICLE_ELEMENT = "id:org.wikipedia.beta:id/page_list_item_title";
        MY_ARTICLE_NAME_TPL = "id:'{ArticleName}'";

    }
}
