package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import static java.time.Duration.ofMillis;

abstract public class ArticlePageObject extends MainPageObject {
    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    protected static String
            VIEW_ELEMENT,
            ARTICLE_MENU_BOOKMARK,
            ADD_TO_LIST,
            TOUCH_OUTSIDE,
            NAVIGATE_UP;

    public String getArticleTitle(String title) {
        if (Platform.getInstance().isAndroid()) {
            return waitElementPresentBy("xpath://android.view.View[@text='" + title + "']").getText();
        } else {
            return waitElementPresentBy("id:'" + title + "']").getAttribute("name");
        }
    }

    public String getArticleTitleOld(String title) {
        return waitElementPresentBy("xpath://android.view.View[@text='" + title + "']").getText();
    }

    public String getTitleText() {
        return waitElementPresentBy("xpath://android.view.View").getAttribute("text");
    }

    public void swipeToViewElement(int size) {
        if (Platform.getInstance().isAndroid()) {
            swipeUpToFindElement((VIEW_ELEMENT), size);
        } else if (Platform.getInstance().isIOs()) {
            swipeUpElementAppear(VIEW_ELEMENT, size);
        }
    }

    public void swipeUp(int timeOfSwipe) {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            TouchAction action = new TouchAction(driver);
            Dimension size = driver.manage().window().getSize();
            int x = size.width / 2;
            int startY = (int) (size.width * 0.8);
            int endY = (int) (size.width * 0.2);
            PointOption startPoint = new PointOption().withCoordinates(x, startY);
            PointOption endPoint = new PointOption().withCoordinates(x, endY);
            action.press(startPoint).waitAction(new WaitOptions().withDuration(ofMillis(timeOfSwipe))).moveTo(endPoint).release().perform();

        } else {
            System.out.println("SwipeUp has not for platform= " + Platform.getInstance().getPlatformVar());
        }
    }

    public void saveArticleToMyList() {
        waitElementPresentBy(ARTICLE_MENU_BOOKMARK).click();
    }

    public void continueAddToList() {
        waitElementPresentBy((ADD_TO_LIST)).click();
        waitElementPresentBy((TOUCH_OUTSIDE)).click();
    }

    public void addArticleToMySaved() {//доработать под iOS
        waitElementPresentBy((ADD_TO_LIST)).click();
        waitElementPresentBy((TOUCH_OUTSIDE)).click();
    }

    public void exitFromArticle() {
        waitElementPresentBy((NAVIGATE_UP)).click();
    }

}
