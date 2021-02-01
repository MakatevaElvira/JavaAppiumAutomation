package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import static java.time.Duration.ofMillis;

public class ArticlePageObject extends MainPageObject {
    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    private static final String
            VIEW_ELEMENT = "//*[@text='View article in browser']",
            ARTICLE_MENU_BOOKMARK = "org.wikipedia.beta:id/article_menu_bookmark",
            ADD_TO_LIST = "//android.widget.Button[contains(@text,'ADD TO LIST')]",
            TOUCH_OUTSIDE = "org.wikipedia.beta:id/touch_outside",
            NAVIGATE_UP = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]";

    public String getArticleTitle(String title) {
        return waitElementPresentBy(By.xpath("//android.view.View[@text='" + title + "']")).getText();
    }
    public String getTitleText(){
        return waitElementPresentBy(By.xpath("//android.view.View")).getAttribute("text");
    }

    public void swipeToViewElement(int size) {
        swipeUpToFindElement(By.xpath(VIEW_ELEMENT), size);
    }

    public void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int startY = (int) (size.width * 0.8);
        int endY = (int) (size.width * 0.2);
        PointOption startPoint = new PointOption().withCoordinates(x, startY);
        PointOption endPoint = new PointOption().withCoordinates(x, endY);
        action.press(startPoint).waitAction(new WaitOptions().withDuration(ofMillis(timeOfSwipe))).moveTo(endPoint).release().perform();
    }

    public void saveArticleToMyList() {
        waitElementPresentBy(By.id(ARTICLE_MENU_BOOKMARK)).click();
    }

    public void continueAddToList() {
        waitElementPresentBy(By.xpath(ADD_TO_LIST)).click();
        waitElementPresentBy(By.id(TOUCH_OUTSIDE)).click();
    }

    public void exitFromArticle() {
        waitElementPresentBy(By.xpath(NAVIGATE_UP)).click();
    }

}
