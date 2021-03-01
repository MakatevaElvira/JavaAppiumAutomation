package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Step;
import lib.Platform;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import static java.time.Duration.ofMillis;
import static lib.ui.MyListPageObject.getArticleByText;

abstract public class ArticlePageObject extends MainPageObject {
    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    protected static String
            VIEW_ELEMENT,
            ARTICLE_MENU_BOOKMARK,
            ARTICLE_TITLE,
            ADD_TO_LIST,
            REMOVE_FROM_MY_LIST,
            TOUCH_OUTSIDE,
            NAVIGATE_UP;
    @Step("Получить название статьи")
    public String getArticleTitle(String title) {
        if (Platform.getInstance().isAndroid()) {
            return waitElementPresentBy("xpath://android.view.View[@text='" + title + "']").getText();
        } else if (Platform.getInstance().isMW()) {
            return waitElementPresentBy(ARTICLE_TITLE).getText();//"css:div.pre-content h1"
        } else {
            return waitElementPresentBy("id:'" + title + "']").getAttribute("name");
        }
    }

    public String getArticleTitleOld(String title) {
        return waitElementPresentBy("xpath://android.view.View[@text='" + title + "']").getText();
    }
    @Step("Получить текст заголовка")
    public String getTitleText() {
        return waitElementPresentBy("xpath://android.view.View").getAttribute("text");
    }

    @Step("Пролистать до элемента")
    public void swipeToViewElement(int size) {
        if (Platform.getInstance().isAndroid()) {
            swipeUpToFindElement((VIEW_ELEMENT), size);
        } else if (Platform.getInstance().isIOs()) {
            swipeUpElementAppear(VIEW_ELEMENT, size);
        } else {
            scrollWebElementTillNotVisible(VIEW_ELEMENT, size);
        }
    }
    @Step("Свайпнуть вниз")
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
    @Step("Добавить статью в избранное")
    public void saveArticleToMyList() {
        waitElementPresentBy(ARTICLE_MENU_BOOKMARK).click();
    }
    @Step("Подтвердить добавление в избранное")
    public void continueAddToList() {
        waitElementPresentBy((ADD_TO_LIST)).click();
        waitElementPresentBy((TOUCH_OUTSIDE)).click();
    }
    @Step("Добавить статью в свой список")
    public void addToList() {
        //removeArticleFromMySaved();
        waitElementPresentBy((ADD_TO_LIST)).click();
    }
    @Step("Добавить статью в сохраненные")
    public void addArticleToMySaved() {//доработать под iOS
        waitElementPresentBy((ADD_TO_LIST)).click();
        waitElementPresentBy((TOUCH_OUTSIDE)).click();
    }
    @Step("Удалить статью из избранного")
    public void removeArticleFromMySaved() {//доработать под iOS
        if (isElementPresent(REMOVE_FROM_MY_LIST)){
            waitElementPresentBy(REMOVE_FROM_MY_LIST).click();
        }
        waitElementPresentBy(ADD_TO_LIST);
    }
    @Step("Выйти из статьи")
    public void exitFromArticle() {
        waitElementPresentBy((NAVIGATE_UP)).click();
    }
    public void closeArticle(){
        if (Platform.getInstance().isMW()){
            //открыть поиск
            System.out.println("Method closeArticle doesn't work for platform = "+Platform.getInstance().getPlatformVar());
        } else {
            // выйти из статьи ?
            exitFromArticle();
        }
    }
    @Step("Добавить статью комплексно для любой платформы")
    public void addToMyList(){
        if (Platform.getInstance().isMW()){
            //далить если уже был добавлен
            removeArticleFromMySaved();
            //добавить в избранное
            addToList();
        }else {
            //нажать SAVE org.wikipedia.beta:id/article_menu_bookmark
            saveArticleToMyList();
            //нажать ADD TO LIST + перейти на активную часть экрана
            continueAddToList();
        }
    }

}
