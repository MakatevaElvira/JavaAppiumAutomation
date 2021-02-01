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
            SEARCH_RESULT = "org.wikipedia.beta:id/page_list_item_title",
            SEARCH_RESULT_BY_TPL = "//*[@text='{SUBSTRING}']",
            SEARCH_CANCEL_BUTTON = "org.wikipedia.beta:id/search_close_btn";

    //
    public void initSearchInput() {
        waitElementPresentBy(By.xpath(SEARCH_INIT_ELEMENT)).click();
    }

    public void typeSearchValue(String toFind) {
        waitElementPresentBy(By.id(SEARCH_INPUT)).sendKeys(toFind);
    }

    public void waitSearchResult() {
        waitElementPresentBy(By.id(SEARCH_RESULT));
    }
    public Boolean waitSearchResultNotPresent() {
        return waitElementNotPresentBy(By.id(SEARCH_RESULT));
    }

    public void tabCancelSearchButton() {
        waitElementPresentBy(By.id(SEARCH_CANCEL_BUTTON)).click();
    }
    public void openArticleByTitle(String titel){
        waitElementPresentBy(By.xpath("//*[@resource-id='org.wikipedia.beta:id/page_list_item_title'][@text='"+titel+"']")).click();
    }
    public void openFirstArticle(){
        waitElementPresentBy(By.id("org.wikipedia.beta:id/page_list_item_title")).click();
    }


    /*TEMPLATES METHODS */
    public static String getResultSearchByText(String text) {
        return SEARCH_RESULT_BY_TPL.replace("{SUBSTRING}", text);
    }
    /*TEMPLATES METHODS */

}
