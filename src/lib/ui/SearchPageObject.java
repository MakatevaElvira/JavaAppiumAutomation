package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {
    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static final String
            SEARCH_INIT_ELEMENT = "//android.widget.TextView[contains(@text,'Search Wikipedia')]",
            SEARCH_INPUT = "org.wikipedia.beta:id/search_src_text",
            SEARCH_RESULT_BY = "org.wikipedia.beta:id/page_list_item_title",
            SEARCH_RESULT_BY_TPL = "//*[@text='{SUBSTRING}']";

    //
    public void initSearchInput() {
        waitElementPresentBy(By.xpath(SEARCH_INIT_ELEMENT)).click();
    }

    public void typeSearchValue(String toFind) {
        waitElementPresentBy(By.id(SEARCH_INPUT)).sendKeys(toFind);
    }

    public void waitSearchResult() {
        waitElementPresentBy(By.id(SEARCH_RESULT_BY));
    }
    /*TEMPLATES METHODS */
    public static String  getResultSearchByText(String text) {
        return SEARCH_RESULT_BY_TPL.replace("{SUBSTRING}",text);
    }
    /*TEMPLATES METHODS */

}
