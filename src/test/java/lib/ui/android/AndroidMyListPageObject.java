package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;

public class AndroidMyListPageObject extends MyListPageObject {
    public AndroidMyListPageObject(AppiumDriver driver) {
        super(driver);
    }

    static {
        SAVED_TITLES = "xpath://android.widget.TextView[contains(@text,'Saved')]";
        ARTICLE_ELEMENT = "id:org.wikipedia.beta:id/page_list_item_title";
        MY_ARTICLE_NAME_TPL =
                "xpath://android.view.ViewGroup/*[@resource-id='org.wikipedia.beta:id/page_list_item_title'][@text='{ArticleName}']";

    }
}
